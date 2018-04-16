/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.website.web;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.security.shiro.session.SessionDAO2;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.*;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Principal;
import com.thinkgem.jeesite.modules.sys.security.FormAuthenticationFilter2;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import com.thinkgem.jeesite.modules.sys.utils.UserInfoUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.user.entity.UserUserinfo;
import com.thinkgem.jeesite.modules.user.service.UserMsmService;
import com.thinkgem.jeesite.modules.user.service.UserUserinfoService;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 网站Controller
 *
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class WebLoginController extends WebBaseController {


    @Resource
    private SessionDAO2 sessionDAO2;
    @Resource
    private UserUserinfoService userUserinfoService;

    @Resource
    private UserMsmService msmService;

    @Resource
    private AreaService areaService;

    @Resource
    private ArticleService articleService;

    @Resource
    private UserMsmService userMsmService;


    @ModelAttribute
    public void init(Model model) {


    }

    /**
     * 管理登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        Principal principal = UserInfoUtils.getPrincipal();

        // 如果已登录，再次访问主页，则退出原账号。
        if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))) {
            CookieUtils.setCookie(response, "LOGINED", "false");
        }

        if(principal != null && principal.isMobileLogin()){
            return  errorLogin("登录失效,请重新登录",response,model);
        }
        // 如果已经登录，则跳转到管理首页
        if (principal != null && !principal.isMobileLogin()) {
            return "redirect:" + frontPath;
        }

        return themesPath + "/login";
    }

    /**
     * 登录失败，真正登录的POST请求由Filter完成
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
        Principal principal = UserInfoUtils.getPrincipal();

        String username = WebUtils.getCleanParam(request, FormAuthenticationFilter2.DEFAULT_USERNAME_PARAM);
        boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter2.DEFAULT_REMEMBER_ME_PARAM);
        boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter2.DEFAULT_MOBILE_PARAM);
        String exception = (String) request.getAttribute(FormAuthenticationFilter2.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String message = (String) request.getAttribute(FormAuthenticationFilter2.DEFAULT_MESSAGE_PARAM);

        // 如果已经登录，则跳转到管理首页
        if (principal != null) {
            if (mobile) {
                return success("登录成功!!", response, model);
            }
            return "redirect:" + frontPath;
        }


        if (StringUtils2.isBlank(message) || StringUtils2.equals(message, "null")) {
            message = "用户或密码错误, 请重试.";
        }

        model.addAttribute(FormAuthenticationFilter2.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute(FormAuthenticationFilter2.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
        model.addAttribute(FormAuthenticationFilter2.DEFAULT_MOBILE_PARAM, mobile);
        model.addAttribute(FormAuthenticationFilter2.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
        model.addAttribute(FormAuthenticationFilter2.DEFAULT_MESSAGE_PARAM, message);

        if (logger.isDebugEnabled()) {
            logger.debug("login fail, active session size: {}, message: {}, exception: {}",
                    sessionDAO2.getActiveSessions(false).size(), message, exception);
        }

        // 非授权异常，登录失败，验证码加1。
        if (!UnauthorizedException.class.getName().equals(exception)) {
            model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
        }

        // 验证失败清空验证码
        request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());

        // 如果是手机登录，则返回JSON字符串
        if (mobile) {
            return error("登录失败,用户名或密码错误!", response, model);
        }

        return themesPath + "/login";
    }


    /**
     * 是否是验证码登录
     *
     * @param useruame 用户名
     * @param isFail   计数加1
     * @param clean    计数清零
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(useruame);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }
        if (isFail) {
            loginFailNum++;
            loginFailMap.put(useruame, loginFailNum);
        }
        if (clean) {
            loginFailMap.remove(useruame);
        }
        return loginFailNum >= 3;
    }


    /**
     * 用户注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpServletRequest request, Model model) {

        String tuijian = request.getParameter("tj");
        UserUserinfo parent = userUserinfoService.getByName(tuijian);
        Area area = new Area();
        area.setType(EnumUtil.AreaType.province.toString());
        List<Area> cityList= areaService.findAllList(area);
        model.addAttribute("areaList",cityList);
        if (parent != null) {
            model.addAttribute("tj", tuijian);
            model.addAttribute("parentName", parent.getUserName());
        }
        return themesPath + "/register";
    }
    /**
     * 用户注册成功跳转
     */
    @RequestMapping(value = "/registerSuccess", method = RequestMethod.GET)
    public String registerSuccess(HttpServletRequest request, Model model) {

        model.addAttribute("message", "注册成功");
        return themesPath + "/registersuccess";
    }

    /**
     * 忘记密码
     * 表名: userinfo
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public String forgetPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
        String newPwd = request.getParameter ("newPwd");
        String newPwds = request.getParameter ("newPwds");
        String mobile = request.getParameter ("mobile");
        String verifyCode = request.getParameter ("code");
        UserUserinfo userinfo = userUserinfoService.getByMobile(mobile);
        if (StringUtils2.isBlank(mobile)) {
            return error ("手机号不能为空!", response, model);
        }
        if(null == userinfo){
            return error ("该用户不存在!", response, model);
        }
        if (StringUtils2.isBlank(verifyCode)) {
            return error ("验证码不能为空!", response, model);
        }else{
            if (!userMsmService.checkVerifyCode(mobile, verifyCode)) {
                return error ("验证码错误!", response, model);
            }
        }
        if (StringUtils2.isBlank(newPwd) || StringUtils2.isBlank(newPwds)) {
            return error ("请输入新密码!", response, model);
        }else{
            if (!newPwds.equals(newPwd)) {
                return error ("两次输入密码不一致!请重新输入!", response, model);
            }
        }

        try {
            userUserinfoService.forgetUserPwd(userinfo.getUserName(),newPwds);
            return success ("恭喜你已成功设置新密码!", response, model);
        } catch (ValidationException e) {
            return error (e.getMessage(), response, model);
        }

    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpServletResponse response, Model model) {

        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String validCode = request.getParameter("validCode");
        String walterName = request.getParameter("walterName");
        String trueName = request.getParameter("trueName");
        UserUserinfo walterUser =  userUserinfoService.getByName(walterName);
        if(null == walterUser){
            return error("当前推荐链接实效, 请联系推荐人索取最新推广地址!", response, model);
        }
        if(StringUtils2.isBlank(mobile)){
            return error("手机号不能为空!", response, model);
        }
        if(StringUtils2.isBlank(trueName)){
            return error("真实姓名不能为空", response, model);
        }
        if(!VerifyUtils.isPassword(password)){
            return error("密码必须大于8位", response, model);
        }
//        if (StringUtils2.isBlank(validCode)) {
//            return error("失败:验证码不能为空!", response, model);
//        }else{
//            if (!msmService.checkVerifyCode(mobile, validCode)) {
//                return error("失败:验证码错误!", response, model);
//            }
//        }

        UserUserinfo userinfo = new UserUserinfo();
        userinfo.setMobile(mobile);
        userinfo.setUserPass(password);
        userinfo.setBankPassword(password);
        userinfo.setWalterName(walterName);
        userinfo.setRenZheng("0");
        userinfo.setUserLevelId("1");
        userinfo.setTrueName(trueName);
        userinfo.setActiveStatus("0");

        try {
            userUserinfoService.save(userinfo);
            return success("注册成功!","",response,model);
        }catch (Exception e) {
            return error("注册失败!"+e.getMessage(), response, model);
        }
    }

    /**
     *
     */
    @RequestMapping(value = "/getArea", method = RequestMethod.POST)
    public String getArea(HttpServletRequest request, HttpServletResponse response, Model model) {
        String pid = request.getParameter("pid");
        if(StringUtils2.isBlank(pid)){
            return error("请选择上级区域",response,model);
        }
        List<Area> areaList = areaService.findListByPid(pid);
        model.addAttribute("areaList",areaList);
        return success("成功!", "", response, model);
    }

    @RequestMapping(value = "/transDetailByKeyword")
    public String userDetailByKeyword(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("agreement",articleService.getArticleDataByKeyword("category_transagreement_article").getArticleData().getContent());
        return themesPath + "/userDetail";
    }

    @RequestMapping(value = "/isLogin")
    public String isLogin(HttpServletRequest request ,HttpServletResponse response, Model model){
        model.addAttribute("isLogin",StringUtils2.isNoneBlank(UserInfoUtils.getUser().getUserName()));
        model.addAttribute("loginName",UserInfoUtils.getUser().getUserName());
        return success("成功",response,model);
    }

    /**用户退出
     * @param response
     * @param model
     * @return
     * @throws ValidationException
     */
    @RequestMapping(value = "logOut")
    public String logOut(HttpServletRequest request, HttpServletResponse response, Model model) {
        UserUtils.getSubject().logout();
        return success("退出成功!", "", response, model);
    }

}

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.website.webapp;

import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils2;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.StringUtils2;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.machine.entity.*;
import com.thinkgem.jeesite.modules.machine.service.*;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserInfoUtils;
import com.thinkgem.jeesite.modules.user.entity.*;
import com.thinkgem.jeesite.modules.user.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户个人信息
 *
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/app/machine")
public class AppMiningMachiController extends BaseController {

    @Autowired
    private MiningMachineService miningMachineService;
    @Autowired
    private UserUserinfoService userinfoService;
    @Autowired
    private UserMsmService userMsmService;
    @Autowired
    private BtcAccountchangeService btcAccountchangeService;
    @Autowired
    private MiningUserService miningUserService;
    @Autowired
    private UserBankChargeService userBankChargeServie;
    @Autowired
    private BtcWithdrawService btcWithdrawService;
    @Autowired
    private UserAccountchangeService userAccountchangeService;
    @Resource
    private UserPhotoService userPhotoService ;
    @Resource
    private RenzhengAuditService renzhengAuditService;
    @Autowired
    private UserWithdrawService userWithdrawService;
    @Autowired
    private BtcBurseService btcBurseService ;
    /**
     * 充值银行信息
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/changeBankInfo", method = RequestMethod.POST)
    public String changeBankInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        UserBankCharge userBankCharge = new UserBankCharge();
        userBankCharge.setStatus(EnumUtil.YesNO.YES.toString());
        model.addAttribute ("userChargeBank", userBankChargeServie.findList(userBankCharge).get(0));
        return success ("成功!!", response, model);
    }

    /**
     * 矿机列表
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/machineList", method = RequestMethod.POST)
    public String userLevelList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String top = request.getParameter("top");
        MiningMachine miningMachine = new MiningMachine();
        try {
            int topLimit = Integer.parseInt(top);
            miningMachine.setTopLimit(topLimit);
        } catch (NumberFormatException e) {

        }
        miningMachine.setStatus(EnumUtil.YesNO.YES.toString());
        model.addAttribute("nowDate", DateUtils2.formatDateTime(new Date()));
        model.addAttribute("machineList",miningMachineService.findList(miningMachine));
        model.addAttribute("powerMoney",Global.getOption("system_help","power_money"));
        model.addAttribute("manageMoney",Global.getOption("system_help","manage_money"));
        return success ("成功!!", response, model);

    }

    /**
     * 矿机列表
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buy(HttpServletRequest request, HttpServletResponse response, Model model) {
        String machineId = request.getParameter("machineId");
        String num = request.getParameter("num");
        UserUserinfo userUserinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userUserinfo == null){
            return error("登录异常请重新登录!", response, model);
        }
        try {
            int hashrate = Integer.parseInt(num);
            miningUserService.buyMining(userUserinfo.getUserName(),machineId,hashrate);
            return success ("成功!!", response, model);
        } catch (ValidationException e) {
            return error (e.getMessage(), response, model);
        }
    }


    /**
     * 购买电量
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/buyPower", method = RequestMethod.POST)
    public String buyPower(HttpServletRequest request, HttpServletResponse response, Model model) {
        String num = request.getParameter("powerNum");
        UserUserinfo userUserinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userUserinfo == null){
            return error("登录异常请重新登录!", response, model);
        }
        try {
            int powerNum = Integer.parseInt(num);
            miningUserService.buyPower(userUserinfo.getUserName(),powerNum);
            return success ("成功!!", response, model);
        } catch (ValidationException e) {
            return error (e.getMessage(), response, model);
        }
    }


    /**
     * 查询账遍记录
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/getAccountLog")
    public String getAccountLog(HttpServletRequest request, HttpServletResponse response, Model model) {
        String changeType = request.getParameter("changeType");
        String moneyType = request.getParameter("moneyType");
        String change = request.getParameter("change");
        String moneyTypeEnd = request.getParameter("moneyTypeEnd");
        String date = request.getParameter ("date");

        UserUserinfo userUserinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userUserinfo == null){
            return error("登录异常请重新登录!", response, model);
        }
        try {
            UserAccountchange userAccountchange = new UserAccountchange();
            userAccountchange.setUserName (userUserinfo.getUserName ());
            userAccountchange.setMoneyType(moneyType);
            userAccountchange.setMoenyTypeEnd(moneyTypeEnd);
            userAccountchange.setChangeType(changeType);
            if(StringUtils2.isNotBlank (date)) {
                Date afterDate = DateUtils2.getDiffDatetime (new Date (),Integer.parseInt (date)*(-1));
                userAccountchange.setCreateDateBegin (afterDate);
            }
            if(StringUtils2.isNotBlank (change)) {
                if(change.equals (EnumUtil.YesNO.YES.toString ())){
                    userAccountchange.setChangeMoneyEnd (new BigDecimal (0));
                }else{
                    userAccountchange.setChangeMoneyBegin (new BigDecimal (0));
                }
            }
            Page<UserAccountchange> page = userAccountchangeService.findPage (new Page<UserAccountchange>(request, response), userAccountchange);
            model.addAttribute("list",page);
            return success ("成功!!", response, model);
        } catch (ValidationException e) {
            return error (e.getMessage(), response, model);
        }
    }

    /**
     * 查询BTC账遍记录
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/getBtcLog", method = RequestMethod.POST)
    public String getBtcLog(HttpServletRequest request, HttpServletResponse response, Model model) {
        UserUserinfo userUserinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userUserinfo == null){
            return error("登录异常请重新登录!", response, model);
        }
        try {
            BtcAccountchange btcAccountchange = new BtcAccountchange();
            btcAccountchange.setUserName(userUserinfo.getUserName());
            Page<BtcAccountchange> page = btcAccountchangeService.findPage (new Page<BtcAccountchange>(request, response), btcAccountchange);
            model.addAttribute("list",page);
            return success ("成功!!", response, model);
        } catch (ValidationException e) {
            return error (e.getMessage(), response, model);
        }
    }

    /**
     * 查询用户矿机
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/getMiningList", method = RequestMethod.POST)
    public String getMiningList(HttpServletRequest request, HttpServletResponse response, Model model) {
        UserUserinfo userUserinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userUserinfo == null){
            return error("登录异常请重新登录!", response, model);
        }
        try {
            MiningUser miningUser = new MiningUser();
            miningUser.setUserName(userUserinfo.getUserName());
            Page<MiningUser> page = miningUserService.findPage (new Page<MiningUser>(request, response), miningUser);
            model.addAttribute("page",page);
            model.addAttribute("nowDate",DateUtils2.formatDateTime(new Date()));


            return success ("成功!!", response, model);
        } catch (ValidationException e) {
            return error (e.getMessage(), response, model);
        }
    }

    /**
     * 查询用户矿机
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/getMiningMachine", method = RequestMethod.POST)
    public String getMiningMachine(HttpServletRequest request, HttpServletResponse response, Model model) {

        String id = request.getParameter("id");
        model.addAttribute("miningInfo", miningMachineService.get(id));
        model.addAttribute("powerMoney",Global.getOption("system_help","power_money"));
        model.addAttribute("manageMoney",Global.getOption("system_help","manage_money"));
        return success ("成功!!", response, model);

    }

    /**
     * 传入changeType moneyType 按照创建时间和用户名分组UserAccountChange
     */
    @RequestMapping(value = "groupByChangeAndMoneyType")
    public String groupByChangeAndMoneyType(HttpServletRequest request, HttpServletResponse response, Model model){

        String changeType = request.getParameter("changeType");
        String moneyType = request.getParameter("moneyType");
        UserUserinfo userUserinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userUserinfo == null){
            return error("登录异常请重新登录!", response, model);
        }
        UserAccountchange userAccountchange = new UserAccountchange();
        userAccountchange.setChangeType(changeType);
        userAccountchange.setMoneyType(moneyType);
        userAccountchange.setUserName(UserInfoUtils.getUser().getUserName());

        Page<UserAccountchange> page = userAccountchangeService.groupByChangeAndMoneyType(new Page<UserAccountchange>(request, response), userAccountchange);
        model.addAttribute("list", page);
        return success("成功",response,model);
    }


    /**
     * 分组查处比特币收益数据
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "groupByChangeAndMoneyTypeBtc")
    public String groupByChangeAndMoneyTypeBtc(HttpServletRequest request, HttpServletResponse response, Model model){

        String changeType = request.getParameter("changeType");
        String moneyType = request.getParameter("moneyType");
        UserUserinfo userUserinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userUserinfo == null){
            return error("登录异常请重新登录!", response, model);
        }
        BtcAccountchange btcAccountchange = new BtcAccountchange();
        btcAccountchange.setChangeType(changeType);
        btcAccountchange.setMoneyType(moneyType);
        btcAccountchange.setRemarks("0");
        btcAccountchange.setUserName(UserInfoUtils.getUser().getUserName());

        Page<BtcAccountchange> page = btcAccountchangeService.findPage(new Page<BtcAccountchange>(request, response), btcAccountchange);
        model.addAttribute("list", page);
        return success("成功",response,model);
    }

    /**
     * 获得比特币帐变
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "findBtcEarn")
    public String findBtcEarn(HttpServletRequest request, HttpServletResponse response, Model model){
        BtcAccountchange btcAccountchange = new BtcAccountchange();
        btcAccountchange.setUserName(UserInfoUtils.getUser().getUserName());
        Page<BtcAccountchange> page = btcAccountchangeService.findBtcEarn(new Page<BtcAccountchange>(request, response), btcAccountchange);
        model.addAttribute("list", page);
        return success("成功",response,model);
    }

    //获取已认证会员信息
    @RequestMapping(value = "/getRenZheng", method = RequestMethod.POST)
    public String getRenZheng(HttpServletRequest request, HttpServletResponse response, Model model) {
        RenzhengAudit renzhengAudit = renzhengAuditService.getByUserName(UserInfoUtils.getUser().getUserName());
        if (renzhengAudit == null) {
            renzhengAudit = new RenzhengAudit();
            model.addAttribute("noData", "-1");
        }
        model.addAttribute("data", renzhengAudit);
        return success("成功!!", response, model);
    }

    //验证身份证号码
    @RequestMapping(value = "/idCardVerify", method = RequestMethod.POST)
    public String idCardVerify(HttpServletRequest request, HttpServletResponse response, Model model) {
        String idCard = request.getParameter("idCard");
        if (IdcardUtils.validateCard(idCard)) {
            return success("成功!!", response, model);
        } else {
            return error("请输入正确的身份证号码!", response, model);
        }

    }

    //社会认证
    @RequestMapping(value = "/secRenZheng", method = RequestMethod.POST)
    public String secRenZheng(RenzhengAudit renzhengAudit, HttpServletResponse response, Model model) {
        try {
            renzhengAudit.setType("2");
            renzhengAudit.setUserName(UserInfoUtils.getUser().getUserName());
            renzhengAudit.setStatus(EnumUtil.CheckType.uncheck.toString());
            renzhengAuditService.save(renzhengAudit);
        } catch (Exception e) {
            return error("失败", response, model);
        }
        return success("成功!!", response, model);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (file == null) {
            return error("上传失败,请更换图片重试", response, model);
        }
        String path = request.getSession().getServletContext().getRealPath("AppUpload");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            return error(e.getMessage(), response, model);
        }
        model.addAttribute("path", request.getContextPath() + "/AppUpload/" + fileName);
        return success("成功!!", response, model);

    }

    //以防重复认证
    @RequestMapping(value = "/reRenZheng", method = RequestMethod.POST)
    public String reRenZheng(HttpServletRequest request, HttpServletResponse response, Model model) {
        RenzhengAudit renzhengAudit = renzhengAuditService.getByUserName(UserInfoUtils.getUser().getUserName());
        String renZhengStatus = "2";
        if (renzhengAudit != null) {
            renZhengStatus = renzhengAudit.getStatus();
        }
        model.addAttribute("renZhengStatus", renZhengStatus);
        return success("成功!!", response, model);
    }

    //根据关键字获得图片
    @RequestMapping(value = "findByKeyWord")
    public String findByKeyWord(HttpServletRequest request, HttpServletResponse response, Model model){

        String keyWord = request.getParameter("keyWord");
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setkeyword(keyWord);

        model.addAttribute("list",userPhotoService.findList(userPhoto));
        return success("成功。",response,model);
    }

    /**
     * 比特币提现
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/appbtcWithdraw", method = RequestMethod.POST)
    public String appbtcWithdraw(HttpServletRequest request, HttpServletResponse response, Model model) {
        String money = request.getParameter("money");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        UserUserinfo userUserinfo = userinfoService.getByName(UserInfoUtils.getUser().getUserName());
        if (userUserinfo == null){
            return error("登陆异常!", response, model);
        }
        if (!SystemService.validatePassword(password,userUserinfo.getBankPassword())) {
            return error("支付密码输入错误", response, model);
        }
        try {
            BigDecimal btcMconey = new BigDecimal(money);
            btcWithdrawService.btcWithdraw(UserInfoUtils.getUser().getUserName(),account,btcMconey);
            return success ("成功!!", response, model);
        } catch (ValidationException e) {
            return error (e.getMessage(), response, model);
        } catch (Exception e){
            return error ("提现失败", response, model);
        }
    }

    /**
     * 比特币提现详情
     * 表名:
     * 条件:
     * 其他:
     */
    @RequestMapping(value = "/btcWithdrawDetail", method = RequestMethod.POST)
    public String btcWithdrawDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String txId = request.getParameter("txId");
        BtcWithdraw btcWithdraw = btcWithdrawService.get(txId);
        if (btcWithdraw == null) {
            return error("提现记录不存在", response, model);
        }
        model.addAttribute("btcChargeOne",Global.getOption("system_help","change_one"));
        model.addAttribute("btcWithdraw", btcWithdraw);
        return success("成功", response, model);
    }

    @RequestMapping(value = "findBtcWithdraw")
    public String findBtcWithdraw(HttpServletRequest request, HttpServletResponse response, Model model){

        BtcWithdraw btcWithdraw = new BtcWithdraw();
        btcWithdraw.setUserName(UserInfoUtils.getUser().getUserName());
        Page<BtcWithdraw> page = btcWithdrawService.findPage(new Page<BtcWithdraw>(request, response), btcWithdraw);
        model.addAttribute("page", page);

        return success("成功",response,model);
    }


    @RequestMapping(value = "getBtcWithdraw")
    public String getBtcWithdraw(HttpServletRequest request, HttpServletResponse response, Model model){
        String id = request.getParameter("id");
        model.addAttribute("btcWithdraw",btcWithdrawService.get(id));
        return success("成功",response,model);
    }

    /**
     * 判断是否有未审核的提现
     * @return
     */
    @RequestMapping(value = "tiXianPass")
    public String tiXianPass(HttpServletRequest request, HttpServletResponse response, Model model){
        UserUserinfo userUserinfo = UserInfoUtils.getUser();
        String statu = "true";
        if(userUserinfo == null){
            throw new ValidationException("您已经掉线,请重新登录.");
        }
        UserWithdraw userWithdraw = new UserWithdraw();
        userWithdraw.setUserName(userUserinfo.getUserName());
        userWithdraw.setStatus(EnumUtil.YesNO.NO.toString());
        List<UserWithdraw> tempList = userWithdrawService.findList(userWithdraw);
        if(tempList.size()!=0){
            statu = "false";
        }
        model.addAttribute("statu",statu);
        return success("成功",response,model);
    }

    @RequestMapping(value = "getBtcChangeMessage")
    public String getBtcChangeMessage(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("shouRecharge",Global.getOption("system_help","change_rmb"));
        model.addAttribute("btclow",Global.getOption("system_help","change_low"));

        return success("成功",response,model);
    }

    /**
     * btc转余额
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "btcChangeRmb")
    public String btcChangeRmb(HttpServletRequest request, HttpServletResponse response, Model model){
        String password = request.getParameter("password");
        String money = request.getParameter("money");

        UserUserinfo userinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userinfo == null){
            return error ("您已掉线,请重新登录", response, model);
        }
        if(!SystemService.validatePassword (password,userinfo.getBankPassword ())){
            return error ("密码输入错误", response, model);
        }

        try {
            btcWithdrawService.btcChangeRmb(userinfo,money);
        } catch (Exception e) {
            return error("转换失败,失败原因:"+e.getMessage(),response,model);
        }

        return success("转换成功",response,model);
    }

    /**
     * 获取认证状态
     */
    @RequestMapping(value = "getRenZhengStatus")
    public String getRenZhengStatus(HttpServletRequest request, HttpServletResponse response, Model model){
        String sta = "3";//状态
        UserUserinfo userUserinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userUserinfo == null){
            return error ("您已掉线,请重新登录", response, model);
        }
        RenzhengAudit userAudit = renzhengAuditService.getByUserName(userUserinfo.getUserName());
        if(userAudit != null){
            sta = userAudit.getStatus();
        }
        model.addAttribute("sta",sta);
        return success("成功",response,model);
    }

    /**
     * 获得比特币汇率
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "getBtcNowPrice")
    public String getBtcNowPrice(HttpServletRequest request, HttpServletResponse response, Model model){

        model.addAttribute("shouRecharge",Global.getOption("system_help","change_rmb"));
        model.addAttribute("btclow",Global.getOption("system_help","change_low"));
        BigDecimal price;

        try {
            price = btcWithdrawService.getBtcChangePrice();
        }catch (ValidationException e){
            return error(e.getMessage(),response,model);
        }catch (Exception e){
            return error("获取BTC最新行情失败",response,model);
        }

        model.addAttribute("price",price);
        return success("成功",response,model);
    }

    /**
     * 获得个人BTC账户列表
     */
    @RequestMapping(value = "getBtcPursetList")
    public String getBtcPursetList(HttpServletRequest request, HttpServletResponse response, Model model){
        UserUserinfo userinfo = userinfoService.get(UserInfoUtils.getUser());

        if(userinfo == null){
            return error("您已掉线,请重新登录.",response,model);
        }

        BtcBurse btcBurse = new BtcBurse();
        btcBurse.setUserName(userinfo.getUserName());
        model.addAttribute("btcChargeOne",Global.getOption("system_help","change_one"));
        model.addAttribute("btcBurses",btcBurseService.findList(btcBurse));
        return success("成功",response,model);
    }

    /**
     * 绑定个人BTC账户列表
     */
    @RequestMapping(value = "updateUserBurse")
    public String updateUserBurse(BtcBurse btcBurse,HttpServletRequest request, HttpServletResponse response, Model model){
        UserUserinfo userinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userinfo == null){
            return error("您已掉线,请重新登录.",response,model);
        }
        btcBurse.setUserName(userinfo.getUserName());
        try {
            btcBurseService.save(btcBurse);
        }catch (ValidationException e) {
            return error(e.getMessage(),response,model);
        } catch (Exception e) {
            return error("绑定失败",response,model);
        }
        return success("绑定成功",response,model);
    }

    /**
     * 绑定个人BTC账户列表
     */
    @RequestMapping(value = "delateBtcBurse")
    public String delateBtcBurse(BtcBurse btcBurse,HttpServletRequest request, HttpServletResponse response, Model model){
        UserUserinfo userinfo = userinfoService.get(UserInfoUtils.getUser());
        if(userinfo == null){
            return error("您已掉线,请重新登录.",response,model);
        }
        BtcBurse userBurse = btcBurseService.get(btcBurse.getId());
        if(userBurse == null){
            return error("您所删除的账户不存在",response,model);
        }
        if(!userinfo.getUserName().equals(userBurse.getUserName())){
            return error("只可以删除自己的账户",response,model);
        }
        btcBurseService.delete(userBurse);
        return success("删除成功",response,model);
    }

}

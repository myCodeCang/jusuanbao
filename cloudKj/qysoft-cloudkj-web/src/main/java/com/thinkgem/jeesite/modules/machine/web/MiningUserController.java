/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.machine.entity.MiningUser;
import com.thinkgem.jeesite.modules.machine.service.MiningUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils2;



/**
 * 用户矿机Controller
 * @author luo
 * @version 2017-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/machine/miningUser")
public class MiningUserController extends BaseController {

	@Autowired
	private MiningUserService miningUserService;
	
	@ModelAttribute
	public MiningUser get(@RequestParam(required=false) String id) {
		MiningUser entity = null;
		if (StringUtils2.isNotBlank(id)){
			entity = miningUserService.get(id);
		}
		if (entity == null){
			entity = new MiningUser();
		}
		return entity;
	}
	
	@RequiresPermissions("user:miningUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(MiningUser miningUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MiningUser> page = miningUserService.findPage(new Page<MiningUser>(request, response), miningUser); 
		model.addAttribute("page", page);
		return "modules/machine/miningUserList";
	}

	@RequiresPermissions("user:miningUser:view")
	@RequestMapping(value = "form")
	public String form(MiningUser miningUser, Model model) {
		model.addAttribute("miningUser", miningUser);
		return "modules/machine/miningUserForm";
	}

	@RequiresPermissions("user:miningUser:edit")
	@RequestMapping(value = "save")
	public String save(MiningUser miningUser, Model model, RedirectAttributes redirectAttributes) {
		try {
			miningUserService.save(miningUser);
		} catch (Exception e) {
			addMessage(redirectAttributes, "失败:"+e.getMessage());
			return form(miningUser, model);
		}
		addMessage(redirectAttributes, "保存用户矿机成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningUser/?repage";
	}
	
	@RequiresPermissions("user:miningUser:edit")
	@RequestMapping(value = "delete")
	public String delete(MiningUser miningUser, RedirectAttributes redirectAttributes) {
		miningUserService.delete(miningUser);
		addMessage(redirectAttributes, "删除用户矿机成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningUser/?repage";
	}

}
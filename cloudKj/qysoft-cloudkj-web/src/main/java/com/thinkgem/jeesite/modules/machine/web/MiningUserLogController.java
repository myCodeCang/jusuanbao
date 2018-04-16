/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.machine.entity.MiningUserLog;
import com.thinkgem.jeesite.modules.machine.service.MiningUserLogService;

/**
 * 用户算力记录Controller
 * @author luo
 * @version 2017-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/machine/miningUserLog")
public class MiningUserLogController extends BaseController {

	@Autowired
	private MiningUserLogService miningUserLogService;
	
	@ModelAttribute
	public MiningUserLog get(@RequestParam(required=false) String id) {
		MiningUserLog entity = null;
		if (StringUtils2.isNotBlank(id)){
			entity = miningUserLogService.get(id);
		}
		if (entity == null){
			entity = new MiningUserLog();
		}
		return entity;
	}
	
	@RequiresPermissions("machine:miningUserLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(MiningUserLog miningUserLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MiningUserLog> page = miningUserLogService.findPage(new Page<MiningUserLog>(request, response), miningUserLog); 
		model.addAttribute("page", page);
		return "modules/machine/miningUserLogList";
	}

	@RequiresPermissions("machine:miningUserLog:view")
	@RequestMapping(value = "form")
	public String form(MiningUserLog miningUserLog, Model model) {
		model.addAttribute("miningUserLog", miningUserLog);
		return "modules/machine/miningUserLogForm";
	}

	@RequiresPermissions("machine:miningUserLog:edit")
	@RequestMapping(value = "save")
	public String save(MiningUserLog miningUserLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, miningUserLog)){
			return form(miningUserLog, model);
		}
		miningUserLogService.save(miningUserLog);
		addMessage(redirectAttributes, "保存用户算力记录成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningUserLog/?repage";
	}
	
	@RequiresPermissions("machine:miningUserLog:edit")
	@RequestMapping(value = "delete")
	public String delete(MiningUserLog miningUserLog, RedirectAttributes redirectAttributes) {
		miningUserLogService.delete(miningUserLog);
		addMessage(redirectAttributes, "删除用户算力记录成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningUserLog/?repage";
	}

}
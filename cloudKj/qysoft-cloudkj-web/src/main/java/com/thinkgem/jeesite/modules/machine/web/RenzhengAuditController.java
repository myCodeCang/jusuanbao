/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils2;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.machine.entity.RenzhengAudit;
import com.thinkgem.jeesite.modules.machine.service.RenzhengAuditService;
import com.thinkgem.jeesite.modules.sys.utils.annotations.RecordLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证审核Controller
 * @author luo
 * @version 2017-08-04
 */
@Controller
@RequestMapping(value = "${adminPath}/machine/renzhengAudit")
public class RenzhengAuditController extends BaseController {

	@Autowired
	private RenzhengAuditService renzhengAuditService;
	
	@ModelAttribute
	public RenzhengAudit get(@RequestParam(required=false) String id) {
		RenzhengAudit entity = null;
		if (StringUtils2.isNotBlank(id)){
			entity = renzhengAuditService.get(id);
		}
		if (entity == null){
			entity = new RenzhengAudit();
		}
		return entity;
	}
	
	@RequiresPermissions("user:renzhengAudit:view")
	@RequestMapping(value = {"list", ""})
	public String list(RenzhengAudit renzhengAudit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RenzhengAudit> page = renzhengAuditService.findPage(new Page<RenzhengAudit>(request, response), renzhengAudit);
		model.addAttribute("page", page);
		return "modules/machine/renzhengAuditList";
	}

	@RequiresPermissions("user:renzhengAudit:view")
	@RequestMapping(value = "form")
	public String form(RenzhengAudit renzhengAudit, Model model) {
		model.addAttribute("renzhengAudit", renzhengAudit);
		return "modules/machine/renzhengAuditForm";
	}

	@RequiresPermissions("user:renzhengAudit:edit")
	@RequestMapping(value = "save")
	public String save(RenzhengAudit renzhengAudit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, renzhengAudit)){
			return form(renzhengAudit, model);
		}
		renzhengAuditService.save(renzhengAudit);
		addMessage(redirectAttributes, "保存审核成功");
		return "redirect:"+ Global.getAdminPath()+"/machine/renzhengAudit/?repage";
	}
	
	@RequiresPermissions("user:renzhengAudit:edit")
	@RequestMapping(value = "delete")
	public String delete(RenzhengAudit renzhengAudit, RedirectAttributes redirectAttributes) {
		renzhengAuditService.delete(renzhengAudit);
		addMessage(redirectAttributes, "删除审核成功");
		return "redirect:"+ Global.getAdminPath()+"/machine/renzhengAudit/?repage";
	}

	@RecordLog(logTitle = "海创配置-认证审核-操作-同意/驳回")
	@RequestMapping(value = "check",method = RequestMethod.POST)
	public String check(RenzhengAudit renzhengAudit,String promValue,String promMsg, Model model, RedirectAttributes redirectAttributes){
		try {
			renzhengAuditService.updateStatus(renzhengAudit,promValue,promMsg);
			addMessage(redirectAttributes, "成功");
		} catch (ValidationException e) {
			addMessage(redirectAttributes, "失败:"+e.getMessage());
		}
		return "redirect:"+ Global.getAdminPath()+"/machine/renzhengAudit/?repage";

	}

}
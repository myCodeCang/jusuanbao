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
import com.thinkgem.jeesite.modules.machine.entity.MiningUserBouns;
import com.thinkgem.jeesite.modules.machine.service.MiningUserBounsService;

/**
 * 奖励统计表Controller
 * @author luo
 * @version 2017-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/machine/miningUserBouns")
public class MiningUserBounsController extends BaseController {

	@Autowired
	private MiningUserBounsService miningUserBounsService;
	
	@ModelAttribute
	public MiningUserBouns get(@RequestParam(required=false) String id) {
		MiningUserBouns entity = null;
		if (StringUtils2.isNotBlank(id)){
			entity = miningUserBounsService.get(id);
		}
		if (entity == null){
			entity = new MiningUserBouns();
		}
		return entity;
	}
	
	@RequiresPermissions("machine:miningUserBouns:view")
	@RequestMapping(value = {"list", ""})
	public String list(MiningUserBouns miningUserBouns, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MiningUserBouns> page = miningUserBounsService.findPage(new Page<MiningUserBouns>(request, response), miningUserBouns); 
		model.addAttribute("page", page);
		return "modules/machine/miningUserBounsList";
	}

	@RequiresPermissions("machine:miningUserBouns:view")
	@RequestMapping(value = "form")
	public String form(MiningUserBouns miningUserBouns, Model model) {
		model.addAttribute("miningUserBouns", miningUserBouns);
		return "modules/machine/miningUserBounsForm";
	}

	@RequiresPermissions("machine:miningUserBouns:edit")
	@RequestMapping(value = "save")
	public String save(MiningUserBouns miningUserBouns, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, miningUserBouns)){
			return form(miningUserBouns, model);
		}
		miningUserBounsService.save(miningUserBouns);
		addMessage(redirectAttributes, "保存奖励统计表成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningUserBouns/?repage";
	}
	
	@RequiresPermissions("machine:miningUserBouns:edit")
	@RequestMapping(value = "delete")
	public String delete(MiningUserBouns miningUserBouns, RedirectAttributes redirectAttributes) {
		miningUserBounsService.delete(miningUserBouns);
		addMessage(redirectAttributes, "删除奖励统计表成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningUserBouns/?repage";
	}

}
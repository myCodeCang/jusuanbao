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
import com.thinkgem.jeesite.modules.machine.entity.BtcBurse;
import com.thinkgem.jeesite.modules.machine.service.BtcBurseService;

/**
 * CTC钱包Controller
 * @author luo
 * @version 2017-12-25
 */
@Controller
@RequestMapping(value = "${adminPath}/machine/btcBurse")
public class BtcBurseController extends BaseController {

	@Autowired
	private BtcBurseService btcBurseService;
	
	@ModelAttribute
	public BtcBurse get(@RequestParam(required=false) String id) {
		BtcBurse entity = null;
		if (StringUtils2.isNotBlank(id)){
			entity = btcBurseService.get(id);
		}
		if (entity == null){
			entity = new BtcBurse();
		}
		return entity;
	}
	
	@RequiresPermissions("machine:btcBurse:view")
	@RequestMapping(value = {"list", ""})
	public String list(BtcBurse btcBurse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BtcBurse> page = btcBurseService.findPage(new Page<BtcBurse>(request, response), btcBurse); 
		model.addAttribute("page", page);
		return "modules/machine/btcBurseList";
	}

	@RequiresPermissions("machine:btcBurse:view")
	@RequestMapping(value = "form")
	public String form(BtcBurse btcBurse, Model model) {
		model.addAttribute("btcBurse", btcBurse);
		return "modules/machine/btcBurseForm";
	}

	@RequiresPermissions("machine:btcBurse:edit")
	@RequestMapping(value = "save")
	public String save(BtcBurse btcBurse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, btcBurse)){
			return form(btcBurse, model);
		}
		btcBurseService.save(btcBurse);
		addMessage(redirectAttributes, "保存BTC钱包成功");
		return "redirect:"+Global.getAdminPath()+"/machine/btcBurse/?repage";
	}
	
	@RequiresPermissions("machine:btcBurse:edit")
	@RequestMapping(value = "delete")
	public String delete(BtcBurse btcBurse, RedirectAttributes redirectAttributes) {
		btcBurseService.delete(btcBurse);
		addMessage(redirectAttributes, "删除BTC钱包成功");
		return "redirect:"+Global.getAdminPath()+"/machine/btcBurse/?repage";
	}

}
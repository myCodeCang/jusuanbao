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
import com.thinkgem.jeesite.modules.machine.entity.BtcAccountchange;
import com.thinkgem.jeesite.modules.machine.service.BtcAccountchangeService;

/**
 * btc提现Controller
 * @author luo
 * @version 2017-11-13
 */
@Controller
@RequestMapping(value = "${adminPath}/machine/btcAccountchange")
public class BtcAccountchangeController extends BaseController {

	@Autowired
	private BtcAccountchangeService btcAccountchangeService;
	
	@ModelAttribute
	public BtcAccountchange get(@RequestParam(required=false) String id) {
		BtcAccountchange entity = null;
		if (StringUtils2.isNotBlank(id)){
			entity = btcAccountchangeService.get(id);
		}
		if (entity == null){
			entity = new BtcAccountchange();
		}
		return entity;
	}
	
	@RequiresPermissions("user:btcAccountchange:view")
	@RequestMapping(value = {"list", ""})
	public String list(BtcAccountchange btcAccountchange, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BtcAccountchange> page = btcAccountchangeService.findPage(new Page<BtcAccountchange>(request, response), btcAccountchange); 
		model.addAttribute("page", page);
		return "modules/machine/btcAccountchangeList";
	}

	@RequiresPermissions("user:btcAccountchange:view")
	@RequestMapping(value = "form")
	public String form(BtcAccountchange btcAccountchange, Model model) {
		model.addAttribute("btcAccountchange", btcAccountchange);
		return "modules/machine/btcAccountchangeForm";
	}

	@RequiresPermissions("user:btcAccountchange:edit")
	@RequestMapping(value = "save")
	public String save(BtcAccountchange btcAccountchange, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, btcAccountchange)){
			return form(btcAccountchange, model);
		}
		btcAccountchangeService.save(btcAccountchange);
		addMessage(redirectAttributes, "保存btc提现成功");
		return "redirect:"+Global.getAdminPath()+"/machine/btcAccountchange/?repage";
	}
	
	@RequiresPermissions("user:btcAccountchange:edit")
	@RequestMapping(value = "delete")
	public String delete(BtcAccountchange btcAccountchange, RedirectAttributes redirectAttributes) {
		btcAccountchangeService.delete(btcAccountchange);
		addMessage(redirectAttributes, "删除btc提现成功");
		return "redirect:"+Global.getAdminPath()+"/machine/btcAccountchange/?repage";
	}

	/**
	 * 比特币帐变
	 * @param btcAccountchange
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "findBtcEarn")
	public String findBtcEarn (BtcAccountchange btcAccountchange, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<BtcAccountchange> page = btcAccountchangeService.findBtcEarn(new Page<BtcAccountchange>(request, response), btcAccountchange);
		model.addAttribute("page", page);
		return "modules/machine/btcAccountEarn";
	}

}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.exception.ValidationException;
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
import com.thinkgem.jeesite.modules.machine.entity.BtcWithdraw;
import com.thinkgem.jeesite.modules.machine.service.BtcWithdrawService;

/**
 * btcController
 * @author luo
 * @version 2017-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/machine/btcWithdraw")
public class BtcWithdrawController extends BaseController {

	@Autowired
	private BtcWithdrawService btcWithdrawService;
	
	@ModelAttribute
	public BtcWithdraw get(@RequestParam(required=false) String id) {
		BtcWithdraw entity = null;
		if (StringUtils2.isNotBlank(id)){
			entity = btcWithdrawService.get(id);
		}
		if (entity == null){
			entity = new BtcWithdraw();
		}
		return entity;
	}
	
	@RequiresPermissions("user:btcWithdraw:view")
	@RequestMapping(value = {"list", ""})
	public String list(BtcWithdraw btcWithdraw, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BtcWithdraw> page = btcWithdrawService.findPage(new Page<BtcWithdraw>(request, response), btcWithdraw); 
		model.addAttribute("page", page);
		return "modules/machine/btcWithdrawList";
	}

	@RequiresPermissions("user:btcWithdraw:view")
	@RequestMapping(value = "form")
	public String form(BtcWithdraw btcWithdraw, Model model) {
		model.addAttribute("btcWithdraw", btcWithdraw);
		return "modules/machine/btcWithdrawForm";
	}

	@RequiresPermissions("user:btcWithdraw:edit")
	@RequestMapping(value = "save")
	public String save(BtcWithdraw btcWithdraw, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, btcWithdraw)){
			return form(btcWithdraw, model);
		}
		btcWithdrawService.save(btcWithdraw);
		addMessage(redirectAttributes, "保存btc提现成功");
		return "redirect:"+Global.getAdminPath()+"/machine/btcWithdraw/?repage";
	}
	
	@RequiresPermissions("user:btcWithdraw:edit")
	@RequestMapping(value = "delete")
	public String delete(BtcWithdraw btcWithdraw, RedirectAttributes redirectAttributes) {
		btcWithdrawService.delete(btcWithdraw);
		addMessage(redirectAttributes, "删除btc提现成功");
		return "redirect:"+Global.getAdminPath()+"/machine/btcWithdraw/?repage";
	}

	@RequestMapping(value = "check")
	public String check(BtcWithdraw userUsercenterLog,String promValue,String promMsg, RedirectAttributes redirectAttributes){
		try {
			btcWithdrawService.updateStatus(userUsercenterLog,promValue,promMsg);
			addMessage(redirectAttributes, "成功");
		} catch (ValidationException e) {
			addMessage(redirectAttributes, "失败:"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/machine/btcWithdraw/?repage";
	}

}
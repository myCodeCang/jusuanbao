/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils2;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.machine.entity.MiningMachine;
import com.thinkgem.jeesite.modules.machine.service.MiningMachineService;
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
 * 矿机信息Controller
 * @author luo
 * @version 2017-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/machine/miningMachine")
public class MiningMachineController extends BaseController {

	@Autowired
	private MiningMachineService miningMachineService;
	
	@ModelAttribute
	public MiningMachine get(@RequestParam(required=false) String id) {
		MiningMachine entity = null;
		if (StringUtils2.isNotBlank(id)){
			entity = miningMachineService.get(id);
		}
		if (entity == null){
			entity = new MiningMachine();
		}
		return entity;
	}
	
	@RequiresPermissions("user:miningMachine:view")
	@RequestMapping(value = {"list", ""})
	public String list(MiningMachine miningMachine, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MiningMachine> page = miningMachineService.findPage(new Page<MiningMachine>(request, response), miningMachine); 
		model.addAttribute("page", page);
		return "modules/machine/miningMachineList";
	}

	@RequiresPermissions("user:miningMachine:view")
	@RequestMapping(value = "form")
	public String form(MiningMachine miningMachine, Model model) {
		model.addAttribute("miningMachine", miningMachine);
		return "modules/machine/miningMachineForm";
	}

	@RequiresPermissions("user:miningMachine:edit")
	@RequestMapping(value = "save")
	public String save(MiningMachine miningMachine, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		try {
			if (miningMachine != null) {
				String content = miningMachine.getRemarks();
				if (!StringUtils2.isBlank(content)) {
					StringBuilder url = new StringBuilder();
					url.append("src=&quot;");
					url.append(request.getScheme()).append("://").append(request.getServerName())
							.append(":").append(request.getServerPort()).append("/").append(request.getContextPath());
					String newContent = content.replace("src=&quot;/userfiles", url.append("/userfiles"));
					miningMachine.setRemarks(newContent);
				}
			}
			miningMachineService.save(miningMachine);
		} catch (ValidationException e) {

			addMessage(model, "失败："+e.getMessage());
			return form(miningMachine, model);
		}
		addMessage(redirectAttributes, "保存矿机成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningMachine/?repage";
	}
	
	@RequiresPermissions("user:miningMachine:edit")
	@RequestMapping(value = "delete")
	public String delete(MiningMachine miningMachine, RedirectAttributes redirectAttributes) {
		miningMachineService.delete(miningMachine);
		addMessage(redirectAttributes, "删除矿机成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningMachine/?repage";
	}

	@RequestMapping(value = "batchSetEarnings" ,method = RequestMethod.GET)
	public String batchSetEarnings(Model model) {

		return "modules/machine/batchSetEarningsForm";
	}

	@RequestMapping(value = "batchSetEarnings" ,method = RequestMethod.POST)
	public String batchSetEarnings(MiningMachine miningMachine, RedirectAttributes redirectAttributes) {
		try {
			miningMachineService.batchSetEarnings(miningMachine);
		} catch (ValidationException e) {
			addMessage(redirectAttributes, "失败: " +e.getMessage());
			return "redirect:"+Global.getAdminPath()+"/machine/miningMachine/batchSetEarnings";
		} catch (Exception e){
			addMessage(redirectAttributes, "批量设置矿机收益失败");
			return "redirect:"+Global.getAdminPath()+"/machine/miningMachine/batchSetEarnings";
		}
		addMessage(redirectAttributes, "批量设置矿机收益成功");
		return "redirect:"+Global.getAdminPath()+"/machine/miningMachine/?repage";
	}

}
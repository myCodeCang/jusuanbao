/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.service;

import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.machine.dao.RenzhengAuditDao;
import com.thinkgem.jeesite.modules.machine.entity.RenzhengAudit;
import com.thinkgem.jeesite.modules.user.dao.UserUserinfoDao;
import com.thinkgem.jeesite.modules.user.entity.UserUserinfo;
import com.thinkgem.jeesite.modules.user.service.UserUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 认证审核Service
 * @author luo
 * @version 2017-08-04
 */
@Service
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class RenzhengAuditService extends CrudService<RenzhengAuditDao, RenzhengAudit> {
	@Autowired
    UserUserinfoDao userUserinfoDao;
	@Autowired
    UserUserinfoService userUserinfoService;
	public RenzhengAudit get(String id) {
		return super.get(id);
	}
	
	public List<RenzhengAudit> findList(RenzhengAudit renzhengAudit) {
		return super.findList(renzhengAudit);
	}
	
	public Page<RenzhengAudit> findPage(Page<RenzhengAudit> page, RenzhengAudit renzhengAudit) {
		return super.findPage(page, renzhengAudit);
	}
	

	public void save(RenzhengAudit renzhengAudit) {
		super.save(renzhengAudit);
	}
	

	public void delete(RenzhengAudit renzhengAudit) {
		super.delete(renzhengAudit);
	}


	public void updateStatus(RenzhengAudit renzhengAudit, String status, String message) {
		if(!renzhengAudit.getStatus ().equals (EnumUtil.CheckType.uncheck.toString ())){
			throw new ValidationException("已审核过,不要重复审核");
		}
		if(status.equals (EnumUtil.YesNO.NO.toString ())){
			renzhengAudit.setStatus(EnumUtil.CheckType.failed.toString ());
			renzhengAudit.setRemarks(message);
		}
		else if(status.equals (EnumUtil.YesNO.YES.toString ())){
			renzhengAudit.setStatus(EnumUtil.CheckType.success.toString ());
			String type = renzhengAudit.getType();
			String userName = renzhengAudit.getUserName();
			UserUserinfo userinfo = userUserinfoService.getByName(userName);
			userinfo.setTrueName(renzhengAudit.getTrueName());
			userinfo.setIdCard(renzhengAudit.getIdCard());
			userinfo.setRenZheng(type);
			userUserinfoService.save(userinfo);
		}
		renzhengAudit.setUpdateDate (new Date());
		dao.updateStatus(renzhengAudit);
	}

	public RenzhengAudit getByUserName(String userName) {
		RenzhengAudit renzhengAudit = dao.getByUserName(userName);
		return renzhengAudit;
	}
}
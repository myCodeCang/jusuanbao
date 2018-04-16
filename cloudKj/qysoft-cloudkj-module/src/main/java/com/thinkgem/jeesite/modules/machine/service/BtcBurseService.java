/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.service;

import java.util.List;

import com.thinkgem.jeesite.common.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.machine.entity.BtcBurse;
import com.thinkgem.jeesite.modules.machine.dao.BtcBurseDao;

/**
 * CTC钱包Service
 * @author luo
 * @version 2017-12-25
 */
@Service
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class BtcBurseService extends CrudService<BtcBurseDao, BtcBurse> {

	public BtcBurse get(String id) {
		return super.get(id);
	}
	
	public List<BtcBurse> findList(BtcBurse btcBurse) {
		return super.findList(btcBurse);
	}
	
	public Page<BtcBurse> findPage(Page<BtcBurse> page, BtcBurse btcBurse) {
		return super.findPage(page, btcBurse);
	}
	

	public void save(BtcBurse btcBurse) {
		BtcBurse userBurse = dao.getByNameAndBankAccount(btcBurse.getUserName(),btcBurse.getBankAccount());
		if(userBurse != null ){
			throw new ValidationException("请不要绑定相同的账户");
		}
		super.save(btcBurse);
	}
	

	public void delete(BtcBurse btcBurse) {
		super.delete(btcBurse);
	}
	
}
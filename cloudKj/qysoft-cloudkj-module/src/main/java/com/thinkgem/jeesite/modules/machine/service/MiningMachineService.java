/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.service;

import java.math.BigDecimal;
import java.util.List;

import com.thinkgem.jeesite.common.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.machine.entity.MiningMachine;
import com.thinkgem.jeesite.modules.machine.dao.MiningMachineDao;

/**
 * 矿机信息Service
 * @author luo
 * @version 2017-11-08
 */
@Service
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class MiningMachineService extends CrudService<MiningMachineDao, MiningMachine> {

	public MiningMachine get(String id) {
		return super.get(id);
	}

	public MiningMachine getLock(String id) {
		return dao.getLock(id);
	}
	
	public List<MiningMachine> findList(MiningMachine miningMachine) {
		return super.findList(miningMachine);
	}
	
	public Page<MiningMachine> findPage(Page<MiningMachine> page, MiningMachine miningMachine) {
		return super.findPage(page, miningMachine);
	}
	

	public void save(MiningMachine miningMachine) {
		super.save(miningMachine);
	}
	

	public void delete(MiningMachine miningMachine) {
		super.delete(miningMachine);
	}

	public void updateNowHashrate(String id, int nowHashrate) {
		dao.updateNowHashrate(id,nowHashrate);
	}

	public void batchSetEarnings(MiningMachine miningMachine) {
		if(miningMachine.getEarnings().compareTo(BigDecimal.ZERO)<=0){
			throw new ValidationException("收益必须大于0");
		}
		dao.batchSetEarnings(miningMachine);
	}
}
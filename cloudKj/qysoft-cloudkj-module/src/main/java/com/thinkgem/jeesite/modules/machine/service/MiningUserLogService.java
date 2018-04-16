/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.machine.entity.MiningUserLog;
import com.thinkgem.jeesite.modules.machine.dao.MiningUserLogDao;

/**
 * 用户算力记录Service
 * @author luo
 * @version 2017-11-15
 */
@Service
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class MiningUserLogService extends CrudService<MiningUserLogDao, MiningUserLog> {

	public MiningUserLog get(String id) {
		return super.get(id);
	}
	
	public List<MiningUserLog> findList(MiningUserLog miningUserLog) {
		return super.findList(miningUserLog);
	}
	
	public Page<MiningUserLog> findPage(Page<MiningUserLog> page, MiningUserLog miningUserLog) {
		return super.findPage(page, miningUserLog);
	}
	

	public void save(MiningUserLog miningUserLog) {
		super.save(miningUserLog);
	}
	

	public void delete(MiningUserLog miningUserLog) {
		super.delete(miningUserLog);
	}

    public void updateMiningLog(String machineId, String userName, int hashrate) {
		MiningUserLog miningUserLog = new MiningUserLog();
		miningUserLog.setUserName(userName);
		miningUserLog.setMachineId(machineId);
		miningUserLog.setCreateDate(new Date());
		List<MiningUserLog> userLogList = this.findList(miningUserLog);
		if(userLogList.size()== 0){
			this.save(miningUserLog);
			userLogList = this.findList(miningUserLog);
		}
		dao.updateHashrate(userLogList.get(0).getId(),hashrate);
	}

	public List<MiningUserLog> findBonusList(MiningUserLog selectMiningUserLog) {
		return dao.findBonusList(selectMiningUserLog);
	}

	public List<MiningUserLog> findBonusToLog(MiningUserLog miningUserLog){
		return dao.findBonusToLog(miningUserLog);
	}
}
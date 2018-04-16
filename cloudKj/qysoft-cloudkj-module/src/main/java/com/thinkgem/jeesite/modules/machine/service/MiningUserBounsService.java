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
import com.thinkgem.jeesite.modules.machine.entity.MiningUserBouns;
import com.thinkgem.jeesite.modules.machine.dao.MiningUserBounsDao;

/**
 * 奖励统计表Service
 * @author luo
 * @version 2017-11-16
 */
@Service
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class MiningUserBounsService extends CrudService<MiningUserBounsDao, MiningUserBouns> {

	public MiningUserBouns get(String id) {
		return super.get(id);
	}
	
	public List<MiningUserBouns> findList(MiningUserBouns miningUserBouns) {
		return super.findList(miningUserBouns);
	}
	
	public Page<MiningUserBouns> findPage(Page<MiningUserBouns> page, MiningUserBouns miningUserBouns) {
		return super.findPage(page, miningUserBouns);
	}

	public void save(MiningUserBouns miningUserBouns) {
		super.save(miningUserBouns);
	}
	

	public void delete(MiningUserBouns miningUserBouns) {
		super.delete(miningUserBouns);
	}

	//根据用户名 机器id查询是否存在记录
	public MiningUserBouns findByUserMid(String userName,String machineId){
		return dao.findByUserMid(userName,machineId);
	}

	//更新用户算力数量
	public void updateHashrate(String id,int hashrate){
		dao.updateHashrate(id,hashrate,new Date());
	}


	
}
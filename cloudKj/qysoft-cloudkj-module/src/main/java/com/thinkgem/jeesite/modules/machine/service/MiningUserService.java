/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.service;

import java.math.BigDecimal;
import java.util.List;

import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.modules.machine.entity.MiningMachine;
import com.thinkgem.jeesite.modules.user.entity.UserUserinfo;
import com.thinkgem.jeesite.modules.user.service.UserUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.machine.entity.MiningUser;
import com.thinkgem.jeesite.modules.machine.dao.MiningUserDao;

/**
 * 用户矿机Service
 * @author luo
 * @version 2017-11-08
 */
@Service
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class MiningUserService extends CrudService<MiningUserDao, MiningUser> {

	@Autowired
	private UserUserinfoService userUserinfoService;
	@Autowired
	private MiningMachineService miningMachineService;
	@Autowired
	private MiningUserLogService miningUserLogService;

	public MiningUser get(String id) {
		return super.get(id);
	}


	public MiningUser getByNameId(String userName, String machineId) {
		return dao.getByUserName(userName,machineId);
	}


	public List<MiningUser> findList(MiningUser miningUser) {
		return super.findList(miningUser);
	}
	
	public Page<MiningUser> findPage(Page<MiningUser> page, MiningUser miningUser) {
		return super.findPage(page, miningUser);
	}
	public void save(MiningUser miningUser) {
		super.save(miningUser);
	}
	

	public void delete(MiningUser miningUser) {
		super.delete(miningUser);
	}


	public void buyMining(String userName,String machineId,int hashrate)throws ValidationException{
		UserUserinfo userUserinfo = userUserinfoService.getByNameLock(userName);
		if(userUserinfo == null){
			throw new ValidationException("用户不存在");
		}
		MiningMachine miningMachine = miningMachineService.getLock(machineId);
		if(miningMachine == null){
			throw new ValidationException("所购买的矿机不存在");
		}
		if(miningMachine.getNowHashrate()< hashrate){
			throw new ValidationException("矿机剩余算力不足,无法购买");
		}
		BigDecimal needMoney = miningMachine.getMoney().multiply(BigDecimal.valueOf(hashrate));
		if(userUserinfo.getMoney().compareTo(needMoney)<0){
			throw new ValidationException("用户余额不足",1001);
		}
		miningMachineService.updateNowHashrate(machineId,hashrate*-1);
		userUserinfoService.updateUserMoney(userUserinfo.getUserName(),needMoney.multiply(BigDecimal.valueOf(-1)),"购买矿机,矿机编号:"+machineId, EnumUtil.MoneyChangeType.BuyWorkProject);
		miningUserLogService.updateMiningLog(machineId,userName,hashrate);
		MiningUser miningUser = this.getByNameId(userName,machineId);
		if(miningUser == null){
		 	miningUser = new MiningUser();
			miningUser.setUserName(userName);
			miningUser.setMachineId(machineId);
		}
		miningUser.setHashrate(miningUser.getHashrate() + hashrate);
		this.save(miningUser);
	}

	public void buyPower(String userName, int powerNum) {
		UserUserinfo userUserinfo = userUserinfoService.getByNameLock(userName);
		if(userUserinfo == null){
			throw new ValidationException("用户不存在");
		}
		BigDecimal powerMoeny;
		try {
			powerMoeny = new BigDecimal(Global.getOption("system_help","power_money"));
		} catch (Exception e) {
			throw new ValidationException("电量单价获取失败");
		}
		BigDecimal needMoney = powerMoeny.multiply(BigDecimal.valueOf(powerNum));
		if(needMoney.compareTo(BigDecimal.ZERO)<=0){
			throw new ValidationException("购买数量错误!");
		}
		if(userUserinfo.getMoney().compareTo(needMoney)<0){
			throw new ValidationException("用户余额不足",1001);
		}
		userUserinfoService.updateUserMoney(userUserinfo.getUserName(),needMoney.multiply(BigDecimal.valueOf(-1)),"购买电量", EnumUtil.MoneyChangeType.expend);
		userUserinfoService.updateUserOtherMoney(userUserinfo.getUserName(),BigDecimal.valueOf(powerNum), EnumUtil.MoneyType.money5,"购买电量", EnumUtil.MoneyChangeType.expend);
    }

    public void updateUserAmountEarings(String userName, String machineId, int count, BigDecimal bounsMoney) {
		dao.updateUserAmountEarings(userName,machineId,count,bounsMoney);
    }
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 奖励统计表Entity
 * @author luo
 * @version 2017-11-16
 */
public class MiningUserBouns extends DataEntity<MiningUserBouns> {
	
	private static final long serialVersionUID = 1L;
	

	
	private String userName;		// 用户名
	private String machineId;		// 矿机编号
	private int hashrate;		// 算力数量

	//拓展字段
	private  MiningMachine miningMachine; //矿机名称
	private BigDecimal powerExcpt;

	//晒选字段
	private Date machineDateEnd; //矿机开始时间
	
	public MiningUserBouns() {
		super();
	}

	public MiningUserBouns(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户名长度必须介于 0 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=11, message="矿机编号长度必须介于 0 和 11 之间")
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public int getHashrate() {
		return hashrate;
	}

	public void setHashrate(int hashrate) {
		this.hashrate = hashrate;
	}

	public MiningMachine getMiningMachine() {
		return miningMachine;
	}

	public void setMiningMachine(MiningMachine miningMachine) {
		this.miningMachine = miningMachine;
	}

	public Date getMachineDateEnd() {
		return machineDateEnd;
	}

	public void setMachineDateEnd(Date machineDateEnd) {
		this.machineDateEnd = machineDateEnd;
	}

	public BigDecimal getPowerExcpt() {
		return powerExcpt;
	}

	public void setPowerExcpt(BigDecimal powerExcpt) {
		this.powerExcpt = powerExcpt;
	}
}
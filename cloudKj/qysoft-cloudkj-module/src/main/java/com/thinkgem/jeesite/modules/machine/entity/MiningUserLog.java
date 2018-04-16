/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户算力记录Entity
 * @author luo
 * @version 2017-11-15
 */
public class MiningUserLog extends DataEntity<MiningUserLog> {
	
	private static final long serialVersionUID = 1L;
	

	
	private String userName;		// 用户名
	private String machineId;		// 矿机编号
	private int hashrate;		// 算力数量

	//拓展字段
	private  MiningMachine miningMachine; //矿机名称
	//晒选字段
	private Date machineDateEnd; //矿机开始时间
	private Date createDateEnd;  //购买截止时间
	
	public MiningUserLog() {
		super();
	}

	public MiningUserLog(String id){
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

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public void setHashrate(int hashrate) {
		this.hashrate = hashrate;
	}


}
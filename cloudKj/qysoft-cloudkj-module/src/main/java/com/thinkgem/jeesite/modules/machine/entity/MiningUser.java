/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.entity;

import com.thinkgem.jeesite.common.exception.ValidationException;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户矿机Entity
 * @author luo
 * @version 2017-11-08
 */
public class MiningUser extends DataEntity<MiningUser> {
	
	private static final long serialVersionUID = 1L;
	

	
	private String userName;		// 用户名
	private String machineId;		// 矿机编号
	private int hashrate;		// 算力数量
	private int count;			//收益发放次数
	private BigDecimal amountEarnings; //累计收益

	@Override
	protected void validateModule(boolean isInsert) throws ValidationException {
		super.validateModule(isInsert);
	}

	@Override
	public void preInsert() throws ValidationException {
		if(amountEarnings == null){
			amountEarnings = BigDecimal.ZERO;
		}
		super.preInsert();
	}

	@Override
	public void preUpdate() throws ValidationException {
		super.preUpdate();
	}

	//拓展字段
	private  MiningMachine miningMachine; //矿机名称

	//晒选字段
	private Date machineDateEnd; //矿机开始时间
	
	public MiningUser() {
		super();
	}

	public MiningUser(String id){
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getAmountEarnings() {
		return amountEarnings;
	}

	public void setAmountEarnings(BigDecimal amountEarnings) {
		this.amountEarnings = amountEarnings;
	}
}
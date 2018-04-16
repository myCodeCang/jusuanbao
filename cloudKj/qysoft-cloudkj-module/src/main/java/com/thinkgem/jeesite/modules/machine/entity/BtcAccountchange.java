/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * btc提现Entity
 * @author luo
 * @version 2017-11-13
 */
public class BtcAccountchange extends DataEntity<BtcAccountchange> {
	
	private static final long serialVersionUID = 1L;
	

	
	private String userName;		// 用户帐号
	private String changeMoney;		// 变化金额
	private String beforeMoney;		// 之前金额
	private String afterMoney;		// 之后金额
	private String commt;		// 备注
	private String status;		// 状态
	private String ischeck;		// 是否统计
	private String changeType;		// 0收入 1支出
	private String moneyType;		// 帐变类型
	private String trueName;   	//真实姓名

	//扩展字段

	private Date createDateBegin;		// 开始时间
	private Date createDateEnd;		// 结束时间


	public Date getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public BtcAccountchange() {
		super();
	}

	public BtcAccountchange(String id){
		super(id);
	}

	@Length(min=0, max=100, message="用户帐号长度必须介于 0 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}
	
	public String getBeforeMoney() {
		return beforeMoney;
	}

	public void setBeforeMoney(String beforeMoney) {
		this.beforeMoney = beforeMoney;
	}
	
	public String getAfterMoney() {
		return afterMoney;
	}

	public void setAfterMoney(String afterMoney) {
		this.afterMoney = afterMoney;
	}
	
	@Length(min=0, max=500, message="备注长度必须介于 0 和 500 之间")
	public String getCommt() {
		return commt;
	}

	public void setCommt(String commt) {
		this.commt = commt;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="是否统计长度必须介于 0 和 1 之间")
	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}
	
	@Length(min=0, max=5, message="0收入 1支出长度必须介于 0 和 5 之间")
	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	
	@Length(min=0, max=5, message="帐变类型长度必须介于 0 和 5 之间")
	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
}
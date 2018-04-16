/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.entity;

import com.thinkgem.jeesite.common.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * btcEntity
 * @author luo
 * @version 2017-11-11
 */
public class BtcWithdraw extends DataEntity<BtcWithdraw> {
	
	private static final long serialVersionUID = 1L;
	

	
	private String userName;		// 用户名
	private String account;		// 账户
	private BigDecimal money;		// BTC
	private String status;		// 状态
	private BigDecimal afterMoney;		// 之后金额
	private BigDecimal beforeMoney;		// 之前金额
	private String userBankAddress;		// 开户行地址
	private String userBankName;		// 银行卡开户名
	private String userBankCode;		// 银行名称
	private String userBankNum;		// 银行卡号
	@Override
	protected void validateModule(boolean isInsert) throws ValidationException {
		super.validateModule(isInsert);
	}

	@Override
	public void preInsert() throws ValidationException {
		if(StringUtils.isBlank(this.status)){
			status = "0";
		}
		super.preInsert();
	}

	@Override
	public void preUpdate() throws ValidationException {
		super.preUpdate();
	}

	public BtcWithdraw() {
		super();
	}

	public BtcWithdraw(String id){
		super(id);
	}

	@Length(min=0, max=255, message="用户名长度必须介于 0 和 255 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="账户长度必须介于 0 和 255 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public BigDecimal getAfterMoney() {
		return afterMoney;
	}

	public void setAfterMoney(BigDecimal afterMoney) {
		this.afterMoney = afterMoney;
	}

	public BigDecimal getBeforeMoney() {
		return beforeMoney;
	}

	public void setBeforeMoney(BigDecimal beforeMoney) {
		this.beforeMoney = beforeMoney;
	}

	public String getUserBankAddress() {
		return userBankAddress;
	}

	public void setUserBankAddress(String userBankAddress) {
		this.userBankAddress = userBankAddress;
	}

	public String getUserBankName() {
		return userBankName;
	}

	public void setUserBankName(String userBankName) {
		this.userBankName = userBankName;
	}

	public String getUserBankCode() {
		return userBankCode;
	}

	public void setUserBankCode(String userBankCode) {
		this.userBankCode = userBankCode;
	}

	public String getUserBankNum() {
		return userBankNum;
	}

	public void setUserBankNum(String userBankNum) {
		this.userBankNum = userBankNum;
	}


}
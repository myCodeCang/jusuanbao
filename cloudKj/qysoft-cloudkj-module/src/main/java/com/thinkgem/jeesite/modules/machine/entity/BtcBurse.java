/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * CTC钱包Entity
 * @author luo
 * @version 2017-12-25
 */
public class BtcBurse extends DataEntity<BtcBurse> {
	
	private static final long serialVersionUID = 1L;
	

	
	private String userName;		// 用户名
	private String bankAccount;		// 账户地址
	private String message;		// 备注
	private String status;		// 状态
	private String trueName;
	
	public BtcBurse() {
		super();
	}

	public BtcBurse(String id){
		super(id);
	}

	@Length(min=0, max=100, message="用户名长度必须介于 0 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=100, message="账户地址长度必须介于 0 和 100 之间")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
}
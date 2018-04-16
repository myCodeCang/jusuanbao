/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.entity;

import com.thinkgem.jeesite.common.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 矿机信息Entity
 * @author luo
 * @version 2017-11-08
 */
public class MiningMachine extends DataEntity<MiningMachine> {
	
	private static final long serialVersionUID = 1L;
	

	
	private String name;		// 矿机名称
	private BigDecimal money;  //单价
	private int allHashrate;		// 总算力
	private int nowHashrate;		// 剩余算力
	private BigDecimal earnings;		// 收益
	private BigDecimal powerExpend;		// 电量消耗
	private String image;		// 图片
	private Date startDate;		// 算力开始时间
	private String status;		// 状态
	private String message;		// 介绍
	private String risk;		// 风险提示


	@Override
	protected void validateModule(boolean isInsert) throws ValidationException {
		if(StringUtils.isBlank(name)){
			throw new ValidationException("矿机名称不能为空");
		}
		if(allHashrate <= 0){
			throw new ValidationException("总算力不能小于等于0");
		}
		if(money.compareTo(BigDecimal.ZERO)<=0){
			throw new ValidationException("单价必须大于0");
		}
		if(nowHashrate > allHashrate){
			throw new ValidationException("总算力不能小于剩余算力");
		}
		if(earnings.compareTo(BigDecimal.ZERO)<=0){
			throw new ValidationException("收益必须大于0");
		}
		if(powerExpend.compareTo(BigDecimal.ZERO)<=0){
			throw new ValidationException("电量消耗必须大于0");
		}
		if(StringUtils.isBlank(image)){
			throw new ValidationException("图片不能为空");
		}

	}

	@Override
	public void preInsert() throws ValidationException {
		validateModule(true);
		if(StringUtils.isBlank(this.status)){
			this.status="1";
		}
		super.preInsert();
	}

	@Override
	public void preUpdate() throws ValidationException {
		validateModule(false);
		super.preUpdate();
	}

	public MiningMachine() {
		super();
	}

	public MiningMachine(String id){
		super(id);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAllHashrate() {
		return allHashrate;
	}

	public void setAllHashrate(Integer allHashrate) {
		this.allHashrate = allHashrate;
	}
	
	public Integer getNowHashrate() {
		return nowHashrate;
	}

	public void setNowHashrate(Integer nowHashrate) {
		this.nowHashrate = nowHashrate;
	}

	public void setAllHashrate(int allHashrate) {
		this.allHashrate = allHashrate;
	}

	public void setNowHashrate(int nowHashrate) {
		this.nowHashrate = nowHashrate;
	}

	public BigDecimal getEarnings() {
		return earnings;
	}

	public void setEarnings(BigDecimal earnings) {
		this.earnings = earnings;
	}

	public BigDecimal getPowerExpend() {
		return powerExpend;
	}

	public void setPowerExpend(BigDecimal powerExpend) {
		this.powerExpend = powerExpend;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
}
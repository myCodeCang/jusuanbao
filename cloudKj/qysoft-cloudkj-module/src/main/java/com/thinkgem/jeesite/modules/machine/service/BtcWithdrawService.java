/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.FileUtils2;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
import com.thinkgem.jeesite.config.EnumBtcUtil;
import com.thinkgem.jeesite.modules.machine.dao.BtcWithdrawDao;
import com.thinkgem.jeesite.modules.machine.entity.BtcBurse;
import com.thinkgem.jeesite.modules.machine.entity.BtcWithdraw;

import com.thinkgem.jeesite.modules.user.entity.UserUserinfo;
import com.thinkgem.jeesite.modules.user.service.UserUserinfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * btcService
 * @author luo
 * @version 2017-11-11
 */
@Service
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class BtcWithdrawService extends CrudService<BtcWithdrawDao, BtcWithdraw> {

	@Resource
	private UserUserinfoService userUserinfoService;

	@Resource
	private BtcBurseService btcBurseService ;

	@Resource
	private BtcAccountchangeService accountchangeService;

	public BtcWithdraw get(String id) {
		return super.get(id);
	}
	
	public List<BtcWithdraw> findList(BtcWithdraw btcWithdraw) {
		return super.findList(btcWithdraw);
	}
	
	public Page<BtcWithdraw> findPage(Page<BtcWithdraw> page, BtcWithdraw btcWithdraw) {
		return super.findPage(page, btcWithdraw);
	}
	

	public void save(BtcWithdraw btcWithdraw) {
		super.save(btcWithdraw);
	}
	

	public void delete(BtcWithdraw btcWithdraw) {
		super.delete(btcWithdraw);
	}

    public void btcWithdraw(String userName, String account, BigDecimal money) {

		BigDecimal btcChargeOne;
		BtcBurse btcBurse = btcBurseService.get(account);
		if(btcBurse == null){
			throw new ValidationException("提现账户不存在,请重新选择.");
		}
		try{
			btcChargeOne = new BigDecimal(Global.getOption("system_help","change_one"));
		}catch (Exception e){
			throw new ValidationException("BTC提现手续费配置有误");
		}
		UserUserinfo userinfo = userUserinfoService.getByNameLock(userName);
		if (userinfo.getRenZheng().equals(EnumUtil.YesNO.NO.toString())) {
			throw new ValidationException("请先实名认证");
		}
		if(userinfo == null){
			throw new ValidationException("用户不存在!");
		}
		if(money.compareTo(btcChargeOne) <= 0 ){
			throw new ValidationException("BTC提现金额过低,必须大于"+btcChargeOne+"BTC");
		}
		if(userinfo.getMoney6().compareTo(money)<0){
			throw new ValidationException("BTC余额不足");
		}

		accountchangeService.insertBtcAccount(userName,money.multiply(BigDecimal.valueOf(-1)), EnumBtcUtil.MoneyChangeType.BtcWithdraw,EnumBtcUtil.MoneyType.BtcMoney,"比特币提现");
		userUserinfoService.updateUserOtherMoney(userName,money.multiply(BigDecimal.valueOf(-1)), EnumUtil.MoneyType.money6,"比特币提现", EnumUtil.MoneyChangeType.none_log);

		BtcWithdraw btcWithdraw = new BtcWithdraw();
		btcWithdraw.setBeforeMoney(userinfo.getMoney6());
		btcWithdraw.setAfterMoney(userinfo.getMoney6().add(money));
		btcWithdraw.setUserName(userName);
		btcWithdraw.setAccount(btcBurse.getBankAccount());
		btcWithdraw.setMoney(money);
		this.save(btcWithdraw);

	}

	//拿到最新兑换价格乘后台配置美元兑人民币汇率
	public BigDecimal getBtcChangePrice() throws ValidationException{

		File path = new File(this.getClass().getResource("/").getPath());
		StringBuffer fileName = new StringBuffer();
		fileName.append(path).append(File.separator).append("btcPrice").append(File.separator).append("btcPrice.json");
		String content;
		try {
			content = FileUtils2.readFileToString(new File(fileName.toString()));
		} catch (IOException e) {
			throw new ValidationException("BTC汇率获取失败");
		}

		HashMap<String,String> hashMap = (HashMap<String, String>) JsonMapper.fromJsonString(content,HashMap.class);
		BigDecimal btcPrice;

		try {
			btcPrice = new BigDecimal(hashMap.get("btcPrice")).multiply(new BigDecimal(Global.getOption("system_help","btc_rate")));
		} catch (Exception e) {
			throw new ValidationException("BTC汇率转换失败");
		}

		return btcPrice;
	}


    public void updateStatus(BtcWithdraw btcWithdraw,  String status, String message) {

		UserUserinfo userUserinfo = userUserinfoService.getByName(btcWithdraw.getUserName());
		if(!btcWithdraw.getStatus ().equals (EnumUtil.CheckType.uncheck.toString ())){
			throw new ValidationException ("已审核过,不要重复审核");
		}
		if(status.equals (EnumUtil.YesNO.NO.toString ())){
			btcWithdraw.setStatus(EnumUtil.CheckType.failed.toString ());
			accountchangeService.insertBtcAccount(btcWithdraw.getUserName(),btcWithdraw.getMoney (), EnumBtcUtil.MoneyChangeType.BtcWithdrawFail,EnumBtcUtil.MoneyType.BtcMoney,"提现驳回退款,驳回原因: "+message);
			userUserinfoService.updateUserOtherMoney(btcWithdraw.getUserName(),btcWithdraw.getMoney (), EnumUtil.MoneyType.money6,"提现驳回退款,驳回原因: "+message, EnumUtil.MoneyChangeType.none_log);
		}
		else if(status.equals (EnumUtil.YesNO.YES.toString ())){
			btcWithdraw.setStatus(EnumUtil.CheckType.success.toString ());
		}
		btcWithdraw.setUpdateDate (new Date());
		btcWithdraw.setRemarks(message);
		dao.updateStatus (btcWithdraw);

    }

    public void btcChangeRmb(UserUserinfo userUserinfo ,String tempMoney){
		UserUserinfo userinfo = userUserinfoService.get(userUserinfo);
		String tempShouXu = Global.getOption("system_help","change_rmb");
		String templow = Global.getOption("system_help","change_low");
		BigDecimal changePrice;
		BigDecimal money;
		BigDecimal shouXu;
		BigDecimal shouXuMoney;
		BigDecimal realMoney;
		BigDecimal low;
		try {
			low = new BigDecimal(templow);
			changePrice = getBtcChangePrice();
			money = new BigDecimal(tempMoney);
			shouXu = new BigDecimal(tempShouXu);
		} catch (Exception e) {
			throw new ValidationException("转换手续费配置错误");
		}

		realMoney = money.multiply(BigDecimal.valueOf(1).subtract(shouXu)).multiply(changePrice).setScale(10,BigDecimal.ROUND_HALF_UP);
		shouXuMoney = money.multiply(changePrice).subtract(realMoney);

		if(money.compareTo(userinfo.getMoney6()) > 0){
			throw new ValidationException("BTC余额不足");
		}
		if (userinfo.getRenZheng().equals(EnumUtil.YesNO.NO.toString()) ) {
			throw new ValidationException("请先实名认证");
		}
		if(realMoney.compareTo(low) <= 0){
			throw new ValidationException("最低转换余额数:"+low+"元");
		}

		accountchangeService.insertBtcAccount(userinfo.getUserName(),money.multiply(BigDecimal.valueOf(-1)),EnumBtcUtil.MoneyChangeType.BtcChangeMoney,EnumBtcUtil.MoneyType.BtcMoney,"BTC转换余额");
		userUserinfoService.updateUserOtherMoney(userinfo.getUserName(),money.multiply(BigDecimal.valueOf(-1)),EnumUtil.MoneyType.money6,"BTC兑换余额",EnumUtil.MoneyChangeType.none_log);
		userUserinfoService.updateUserMoney(userinfo.getUserName(),realMoney,"BTC转换余额,手续费"+shouXuMoney+"元",EnumUtil.MoneyChangeType.poundage);

	}
}
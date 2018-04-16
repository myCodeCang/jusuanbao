/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.user.service;

import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.config.EnumBtcUtil;
import com.thinkgem.jeesite.modules.machine.entity.BtcAccountchange;
import com.thinkgem.jeesite.modules.machine.service.BtcAccountchangeService;
import com.thinkgem.jeesite.modules.user.dao.UserAccountchangeDao;
import com.thinkgem.jeesite.modules.user.dao.UserChargeLogDao;
import com.thinkgem.jeesite.modules.user.dao.UserUserinfoDao;
import com.thinkgem.jeesite.modules.user.entity.UserUserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 会员列表Service
 *
 * @author wyxiang
 * @version 2017-03-25
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"RuntimeException", "Exception", "ValidationException"})
public class UserUserinfoExtYkjService extends CrudService<UserUserinfoDao, UserUserinfo> {

    @Autowired
    private UserUserinfoService userUserinfoService ;
    @Autowired
    private BtcAccountchangeService accountchangeService ;

    public void chargeBtc(String userName, BigDecimal bounsMoney, EnumUtil.MoneyType type, String message, EnumUtil.MoneyChangeType changeType){
        accountchangeService.insertBtcAccount(userName, bounsMoney, EnumBtcUtil.MoneyChangeType.BtcAdminRecharge, EnumBtcUtil.MoneyType.BtcMoney, message);
        //发送比特币
        userUserinfoService.updateUserOtherMoney(userName, bounsMoney, EnumUtil.MoneyType.money6, message, EnumUtil.MoneyChangeType.none_log);
    }
}
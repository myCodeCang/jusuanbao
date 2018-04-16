/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.utils.DateUtils2;
import com.thinkgem.jeesite.config.EnumBtcUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserInfoUtils;
import com.thinkgem.jeesite.modules.user.entity.UserUserinfo;
import com.thinkgem.jeesite.modules.user.service.UserUserinfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.machine.entity.BtcAccountchange;
import com.thinkgem.jeesite.modules.machine.dao.BtcAccountchangeDao;

import javax.annotation.Resource;

/**
 * btc提现Service
 *
 * @author luo
 * @version 2017-11-13
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"RuntimeException", "Exception", "ValidationException"})
public class BtcAccountchangeService extends CrudService<BtcAccountchangeDao, BtcAccountchange> {

    @Resource
    private UserUserinfoService userUserinfoService;

    public BtcAccountchange get(String id) {
        return super.get(id);
    }

    public List<BtcAccountchange> findList(BtcAccountchange btcAccountchange) {
        return super.findList(btcAccountchange);
    }

    public Page<BtcAccountchange> findPage(Page<BtcAccountchange> page, BtcAccountchange btcAccountchange) {
        return super.findPage(page, btcAccountchange);
    }


    public void save(BtcAccountchange btcAccountchange) {
        super.save(btcAccountchange);
    }


    public void delete(BtcAccountchange btcAccountchange) {
        super.delete(btcAccountchange);
    }

    public void insertBtcAccount(String userName, BigDecimal changeMoney, EnumBtcUtil.MoneyChangeType changeType, EnumBtcUtil.MoneyType moneyType, String message) {

        UserUserinfo userUserinfo = userUserinfoService.getByNameLock(userName);

        BtcAccountchange btcAccountchange = new BtcAccountchange();
        //比特币提现
        btcAccountchange.setUserName(userUserinfo.getUserName());
        btcAccountchange.setChangeMoney(changeMoney.toString());
        btcAccountchange.setBeforeMoney(userUserinfo.getMoney6().toString());
        btcAccountchange.setAfterMoney(userUserinfo.getMoney6().add(changeMoney).toString());
        btcAccountchange.setCommt(message);
        btcAccountchange.setIscheck("0");
        btcAccountchange.setStatus("0");
        btcAccountchange.setChangeType(changeType.toString());
        btcAccountchange.setMoneyType(moneyType.toString());
        btcAccountchange.preInsert();
        dao.insert(btcAccountchange);

    }

    public Page<BtcAccountchange> findBtcEarn(Page<BtcAccountchange> page, BtcAccountchange btcAccountchange) {
        btcAccountchange.setPage(page);
        page.setList(dao.findBtcEarn(btcAccountchange));
        return page;
    }

    public List<BtcAccountchange> getEchartsData(String userName,Date createDateBegin) {
        //得到数据
        return dao.getEchartsData(userName,createDateBegin);
    }

}
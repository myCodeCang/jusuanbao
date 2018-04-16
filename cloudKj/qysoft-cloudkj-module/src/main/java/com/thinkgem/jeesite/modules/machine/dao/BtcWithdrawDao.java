/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.machine.entity.BtcWithdraw;

/**
 * btcDAO接口
 * @author luo
 * @version 2017-11-11
 */
@MyBatisDao
public interface BtcWithdrawDao extends CrudDao<BtcWithdraw> {

    void updateStatus(BtcWithdraw btcWithdraw);
}
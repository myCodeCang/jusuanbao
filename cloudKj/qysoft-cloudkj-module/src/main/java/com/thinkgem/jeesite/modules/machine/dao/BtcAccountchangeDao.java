/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.machine.entity.BtcAccountchange;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * btc提现DAO接口
 * @author luo
 * @version 2017-11-13
 */
@MyBatisDao
public interface BtcAccountchangeDao extends CrudDao<BtcAccountchange> {

    List<BtcAccountchange> findBtcEarn(BtcAccountchange btcAccountchange);

    List<BtcAccountchange> getEchartsData(@Param("userName") String userName,@Param("createDateBegin") Date createDateBegin);
}
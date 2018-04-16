/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.machine.entity.MiningMachine;
import org.apache.ibatis.annotations.Param;

/**
 * 矿机信息DAO接口
 * @author luo
 * @version 2017-11-08
 */
@MyBatisDao
public interface MiningMachineDao extends CrudDao<MiningMachine> {

    MiningMachine getLock(String id);

    void updateNowHashrate(@Param("id") String id,@Param("nowHashrate") int nowHashrate);

    void batchSetEarnings(MiningMachine miningMachine);
}
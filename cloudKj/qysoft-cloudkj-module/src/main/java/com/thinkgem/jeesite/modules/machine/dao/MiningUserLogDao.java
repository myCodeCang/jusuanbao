/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.machine.entity.MiningUserLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户算力记录DAO接口
 * @author luo
 * @version 2017-11-15
 */
@MyBatisDao
public interface MiningUserLogDao extends CrudDao<MiningUserLog> {

    void updateHashrate(@Param("id") String id, @Param("hashrate") int hashrate);

    List<MiningUserLog> findBonusList(MiningUserLog selectMiningUserLog);

    List<MiningUserLog> findBonusToLog(MiningUserLog miningUserLog);
}
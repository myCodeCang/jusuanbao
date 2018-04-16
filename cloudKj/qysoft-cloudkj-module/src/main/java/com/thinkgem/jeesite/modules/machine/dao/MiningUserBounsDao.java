/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.machine.entity.MiningUserBouns;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 奖励统计表DAO接口
 * @author luo
 * @version 2017-11-16
 */
@MyBatisDao
public interface MiningUserBounsDao extends CrudDao<MiningUserBouns> {

    MiningUserBouns findByUserMid(@Param("userName") String userName,@Param("machineId") String machineId);

    void updateHashrate(@Param("id") String id,@Param("hashrate") int hashrate,@Param("updateDate") Date updateDate);
}
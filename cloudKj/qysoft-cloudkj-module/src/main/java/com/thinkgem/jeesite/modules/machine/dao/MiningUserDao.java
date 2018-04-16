/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.machine.entity.MiningUser;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 用户矿机DAO接口
 * @author luo
 * @version 2017-11-08
 */
@MyBatisDao
public interface MiningUserDao extends CrudDao<MiningUser> {

    MiningUser getByUserName(@Param("userName") String userName,@Param("machineId") String machineId);

    void updateUserAmountEarings(@Param("userName") String userName,@Param("machineId") String machineId,@Param("count") int count,@Param("amountEarnings") BigDecimal amountEarnings);
}
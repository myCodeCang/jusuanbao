/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.machine.entity.BtcBurse;
import org.apache.ibatis.annotations.Param;

/**
 * CTC钱包DAO接口
 * @author luo
 * @version 2017-12-25
 */
@MyBatisDao
public interface BtcBurseDao extends CrudDao<BtcBurse> {

    BtcBurse getByNameAndBankAccount(@Param("userName") String userName,@Param("bankAccount") String bankAccount);
}
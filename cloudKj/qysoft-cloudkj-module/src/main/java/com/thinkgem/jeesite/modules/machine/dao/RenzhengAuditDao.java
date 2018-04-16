/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.machine.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.machine.entity.RenzhengAudit;


/**
 * 认证审核DAO接口
 * @author luo
 * @version 2017-08-04
 */
@MyBatisDao
public interface RenzhengAuditDao extends CrudDao<RenzhengAudit> {

    public  void updateStatus(RenzhengAudit renzhengAudit);

    public RenzhengAudit getByUserName(String userName);
}
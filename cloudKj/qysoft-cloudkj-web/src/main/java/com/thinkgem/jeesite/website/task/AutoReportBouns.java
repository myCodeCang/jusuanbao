package com.thinkgem.jeesite.website.task;

import com.thinkgem.jeesite.common.utils.DateUtils2;
import com.thinkgem.jeesite.modules.machine.entity.MiningUserBouns;
import com.thinkgem.jeesite.modules.machine.entity.MiningUserLog;
import com.thinkgem.jeesite.modules.machine.service.MiningUserBounsService;
import com.thinkgem.jeesite.modules.machine.service.MiningUserLogService;
import com.thinkgem.jeesite.modules.tasks.entity.UserTaskBusiness;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Lazy(false)
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"RuntimeException", "Exception", "ValidationException"})

public class AutoReportBouns implements UserTaskBusiness {

    protected Logger logger = null;

    @Resource
    private MiningUserLogService miningUserLogService ;

    @Resource
    private MiningUserBounsService miningUserBounsService ;

    @Override
    public boolean doBusiness() {
        logger = getLogger();
        logger.error("start..................");

        //查询记录数据
        MiningUserLog miningUserLog = new MiningUserLog();
        miningUserLog.setCreateDate(DateUtils2.getDiffDatetime(new Date(),-1));
        List<MiningUserLog> miningUserLogs = miningUserLogService.findList(miningUserLog);

        //处理数据
        for (MiningUserLog userBounsLog : miningUserLogs) {
            MiningUserBouns userBouns = miningUserBounsService.findByUserMid(userBounsLog.getUserName(),userBounsLog.getMachineId());
            if(userBouns == null){
                MiningUserBouns bouns = new MiningUserBouns();
                bouns.setUserName(userBounsLog.getUserName());
                bouns.setHashrate(userBounsLog.getHashrate());
                bouns.setMachineId(userBounsLog.getMachineId());
                miningUserBounsService.save(bouns);
            }else{
                miningUserBounsService.updateHashrate(userBouns.getId(),userBounsLog.getHashrate());
            }
        }

        logger.error("end..................");

        return true;
    }
}

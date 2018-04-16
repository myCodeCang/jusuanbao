package com.thinkgem.jeesite.website.task;

import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.utils.DateUtils2;
import com.thinkgem.jeesite.config.EnumBtcUtil;
import com.thinkgem.jeesite.modules.machine.entity.MiningUserBouns;
import com.thinkgem.jeesite.modules.machine.entity.MiningUserLog;
import com.thinkgem.jeesite.modules.machine.service.BtcAccountchangeService;
import com.thinkgem.jeesite.modules.machine.service.MiningUserBounsService;
import com.thinkgem.jeesite.modules.machine.service.MiningUserLogService;
import com.thinkgem.jeesite.modules.machine.service.MiningUserService;
import com.thinkgem.jeesite.modules.tasks.entity.UserTaskBusiness;
import com.thinkgem.jeesite.modules.user.entity.UserUserinfo;
import com.thinkgem.jeesite.modules.user.service.UserUserinfoService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

/**
 *
 */
@Service
@Lazy(false)
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"RuntimeException", "Exception", "ValidationException"})
public class UserBonusTasks implements UserTaskBusiness {

    @Resource
    private MiningUserService miningUserService;
    @Resource
    private BtcAccountchangeService accountchangeService;
    @Resource
    private UserUserinfoService userUserinfoService;
    @Resource
    private MiningUserBounsService miningUserBounsService;

    protected Logger logger = null;

    @Override
    public boolean doBusiness() {
        logger = getLogger();
        logger.error("start..................");

        BigDecimal manageCharge;

        try {
            manageCharge = new BigDecimal(Global.getOption("system_help", "manage_money"));
        } catch (Exception e) {
            logger.error("获取管理费配置失败。");
            return false;
        }

        MiningUserBouns select = new MiningUserBouns();
        select.setMachineDateEnd(DateUtils2.getDiffDatetime(new Date(), -1));
        select.setOrderBy("m.id ASC");
        List<MiningUserBouns> bounsList = miningUserBounsService.findList(select);
        for (MiningUserBouns miningUserLog : bounsList) {
            UserUserinfo userUserinfo = userUserinfoService.getByNameLock(miningUserLog.getUserName());
            if (userUserinfo == null) {
                continue;
            }
            if (miningUserLog.getMiningMachine() == null) {
                continue;
            }
//            if (EnumUtil.YesNO.NO.toString().equals(miningUserLog.getMiningMachine().getStatus())) {
//                continue;
//            }
            BigDecimal reduce = bounsList.stream().filter(p -> p.getUserName().equals(miningUserLog.getUserName())).map(MiningUserBouns::getPowerExcpt).reduce(BigDecimal.ZERO, BigDecimal::add);
            if (reduce.compareTo(userUserinfo.getMoney5()) > 0) {
                logger.error("电量不足,发送失败用户:"+miningUserLog.getUserName()+"矿机编号: "+miningUserLog.getMachineId());
                continue;
            }

            BigDecimal needPower = BigDecimal.valueOf(miningUserLog.getHashrate()).multiply(miningUserLog.getMiningMachine().getPowerExpend());
            if (userUserinfo.getMoney5().compareTo(needPower) < 0) {
                logger.error("电量不足,发送失败用户:"+miningUserLog.getUserName()+"矿机编号: "+miningUserLog.getMachineId());
                continue;
            }
            BigDecimal bounsMoney = BigDecimal.valueOf(miningUserLog.getHashrate()).multiply(miningUserLog.getMiningMachine().getEarnings());

            //插入账变
            accountchangeService.insertBtcAccount(userUserinfo.getUserName(), bounsMoney, EnumBtcUtil.MoneyChangeType.AwardGive, EnumBtcUtil.MoneyType.BtcMoney, "矿机产出收益,矿机编号: " + miningUserLog.getMachineId());
            //发送比特币
            userUserinfoService.updateUserOtherMoney(userUserinfo.getUserName(), bounsMoney, EnumUtil.MoneyType.money6, "矿机产出收益,矿机编号: " + miningUserLog.getMachineId(), EnumUtil.MoneyChangeType.none_log);

            accountchangeService.insertBtcAccount(userUserinfo.getUserName(), bounsMoney.multiply(manageCharge).multiply(BigDecimal.valueOf(-1)), EnumBtcUtil.MoneyChangeType.ManageCharge, EnumBtcUtil.MoneyType.BtcMoney, "扣除管理费,矿机编号: " + miningUserLog.getMachineId());
            //手续费
            userUserinfoService.updateUserOtherMoney(userUserinfo.getUserName(), bounsMoney.multiply(manageCharge).multiply(BigDecimal.valueOf(-1)), EnumUtil.MoneyType.money6, "矿机产出收益,矿机编号: " + miningUserLog.getMachineId(), EnumUtil.MoneyChangeType.none_log);

            //扣除电费
            userUserinfoService.updateUserOtherMoney(userUserinfo.getUserName(), needPower.multiply(BigDecimal.valueOf(-1)), EnumUtil.MoneyType.money5, "矿机产出电费消耗,矿机编号: " + miningUserLog.getMachineId(), EnumUtil.MoneyChangeType.unfreeze);

            //统计奖励
            miningUserService.updateUserAmountEarings(userUserinfo.getUserName(),miningUserLog.getMachineId(),1,bounsMoney);
        }
        logger.error("end..................");

        return true;
    }


}

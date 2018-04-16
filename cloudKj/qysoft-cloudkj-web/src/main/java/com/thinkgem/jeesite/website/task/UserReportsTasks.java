package com.thinkgem.jeesite.website.task;

import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.config.EnumBtcUtil;
import com.thinkgem.jeesite.modules.machine.entity.BtcAccountchange;
import com.thinkgem.jeesite.modules.machine.service.BtcAccountchangeService;
import com.thinkgem.jeesite.modules.tasks.entity.UserTaskBusiness;
import com.thinkgem.jeesite.modules.user.entity.UserAccountchange;
import com.thinkgem.jeesite.modules.user.service.ShopCommOrderService;
import com.thinkgem.jeesite.modules.user.service.UserAccountchangeService;
import com.thinkgem.jeesite.modules.user.service.UserReportService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@Lazy(false)
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class UserReportsTasks implements UserTaskBusiness {


    @Resource
    private UserReportService userReportService;

    @Resource
    private UserAccountchangeService userAccountchangeService;

    @Resource
    private BtcAccountchangeService btcAccountchangeService;

    @Resource
    private ShopCommOrderService shopCommOrderService;

    protected Logger logger = null;
    @Override
    public boolean doBusiness() {
        logger = getLogger();
        logger.error("start..................");

        // 充值提现统计
        UserAccountchange accountchange = new UserAccountchange();
        accountchange.setIscheck (EnumUtil.YesNO.NO.toString ());
        List<UserAccountchange> accountchangeList = userAccountchangeService.findList (accountchange);

        List<UserAccountchange> collect = accountchangeList.stream().filter(p -> p.getMoneyType().equals(EnumUtil.MoneyType.money.toString()) || p.getMoneyType().equals(EnumUtil.MoneyType.money5.toString())).collect(Collectors.toList());
        for (UserAccountchange userchange : collect) {
            //购买矿机
            if (userchange.getChangeType ().equals (EnumUtil.MoneyChangeType.BuyWorkProject.toString ())) {
                userReportService.updateUserReportByType (userchange.getUserName (), "1", new BigDecimal (userchange.getChangeMoney ()));
            }
            //直推收益
            if (userchange.getChangeType ().equals (EnumUtil.MoneyChangeType.ZhituiMoney.toString ())) {
                userReportService.updateUserReportByType (userchange.getUserName (), "2", new BigDecimal (userchange.getChangeMoney ()));
            }
            //间推收益
            if(userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.JiantuiMoney.toString())){
                userReportService.updateUserReportByType(userchange.getUserName (),"3" ,new BigDecimal (userchange.getChangeMoney ()));
            }
            //充值
            if(userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.charget.toString())||userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.WEIXIN_PAY.toString())){
                userReportService.updateUserReportByType(userchange.getUserName (),"4" ,new BigDecimal (userchange.getChangeMoney ()));
            }
            //提现
            if(userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.widthdraw.toString())||userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.widthdrawReject.toString())){
                userReportService.updateUserReportByType(userchange.getUserName (),"5" ,new BigDecimal (userchange.getChangeMoney ()));
            }
            //电量消耗
            if(userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.unfreeze.toString())){
                userReportService.updateUserReportByType(userchange.getUserName (),"6" ,new BigDecimal (userchange.getChangeMoney ()));
            }
            //转账
            if(userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.TRANSFER_ACCOUNTS.toString())){
                userReportService.updateUserReportByType(userchange.getUserName (),"7" ,new BigDecimal (userchange.getChangeMoney ()));
            }
            //消费
            if(userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.CONSUMPTION.toString())){
                userReportService.updateUserReportByType(userchange.getUserName (),"8" ,new BigDecimal (userchange.getChangeMoney ()));
            }
            //手续费
            if(userchange.getChangeType ().equals(EnumUtil.MoneyChangeType.TRANSFER_POUNDAGE.toString())){
                userReportService.updateUserReportByType(userchange.getUserName (),"10" ,new BigDecimal (userchange.getChangeMoney ()));
            }
            userchange.setIscheck (EnumUtil.YesNO.YES.toString ());
            userAccountchangeService.save (userchange);
        }

        //Btc 统计
        BtcAccountchange btcAccountchange = new BtcAccountchange();
        btcAccountchange.setIscheck(EnumUtil.YesNO.NO.toString());
        btcAccountchange.setMoneyType("2");
        List<BtcAccountchange> btcAccountchangeList = btcAccountchangeService.findList (btcAccountchange);
        for(BtcAccountchange  btcChange:btcAccountchangeList){
            //比特币收益
            if (btcChange.getChangeType ().equals (EnumBtcUtil.MoneyChangeType.AwardGive.toString ())) {
                userReportService.updateUserReportByType (btcChange.getUserName (), "13", new BigDecimal (btcChange.getChangeMoney ()));
            }
            //比特币管理费
            if (btcChange.getChangeType ().equals (EnumBtcUtil.MoneyChangeType.ManageCharge.toString ())) {
                userReportService.updateUserReportByType (btcChange.getUserName (), "14", new BigDecimal (btcChange.getChangeMoney ()));
            }
            //比特币提现
            if(btcChange.getChangeType ().equals(EnumBtcUtil.MoneyChangeType.BtcWithdraw.toString())||btcChange.getChangeType ().equals(EnumBtcUtil.MoneyChangeType.BtcWithdrawFail.toString())){
                userReportService.updateUserReportByType(btcChange.getUserName (),"15" ,new BigDecimal (btcChange.getChangeMoney ()));
            }
            btcChange.setIscheck(EnumUtil.YesNO.YES.toString ());
            btcAccountchangeService.save(btcChange);
        }
        logger.error("end..................");

        return true;
    }




}

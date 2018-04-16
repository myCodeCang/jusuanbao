package com.thinkgem.jeesite.modules.user.service;

import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.modules.payment.service.WeixpayCallbackService;
import com.thinkgem.jeesite.modules.user.entity.UserChargeLog;
import com.thinkgem.jeesite.modules.user.entity.UserUserinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
@Service("wxpayCallbackService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"RuntimeException", "Exception", "ValidationException"})
public class WxPayCallbackService implements WeixpayCallbackService {
    @Autowired
    private UserUserinfoService userUserinfoService;
    @Autowired
    private UserChargeLogService userChargeLogService;
    @Override
    public boolean callback(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return false;
        }
        String userName = params.get("attach");
        String money = params.get("total_fee");
        String tradeNo = params.get("out_trade_no");

        UserUserinfo byNameLock = userUserinfoService.getByNameLock(userName);
        if(byNameLock == null){
            return true;
        }
        UserChargeLog chargeLog = userChargeLogService.getByRecordCode(tradeNo);
        if (null != chargeLog) {
            return true;
        }
        try {
            userUserinfoService.updateUserMoney(userName, new BigDecimal(money).divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_HALF_UP), tradeNo, EnumUtil.MoneyChangeType.WEIXIN_PAY);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}

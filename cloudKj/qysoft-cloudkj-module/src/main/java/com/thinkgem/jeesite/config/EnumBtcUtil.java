package com.thinkgem.jeesite.config;

/**
 * Created by Administrator on 2017-05-24.
 */
public class EnumBtcUtil {


    //btc帐变类型
    public static enum MoneyChangeType {

        //BTC奖励分发
        AwardGive(1),
        //BTC提现
        BtcWithdraw(2),
        //BTC提现驳回
        BtcWithdrawFail(4),
        //管理费
        ManageCharge(3),
        //BTC后台充值
        BtcAdminRecharge(5),
        //BTC转换余额
        BtcChangeMoney(6);




        private int nCode;
        private MoneyChangeType(int _nCode) {
            this.nCode = _nCode;
        }
        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }


    //用户类型
    public static enum MoneyType {


        Money(1),
        //帮助人拒绝
        BtcMoney(2);




        private int nCode;
        private MoneyType(int _nCode) {
            this.nCode = _nCode;
        }
        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }

}

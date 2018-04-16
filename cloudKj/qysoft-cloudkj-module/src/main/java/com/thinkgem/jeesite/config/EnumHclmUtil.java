package com.thinkgem.jeesite.config;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Administrator on 2017-05-24.
 */
public class EnumHclmUtil {


    //统计报表
    public static enum HcMateStatus {

        //匹配中
        Mateing(0),
        //匹配成功
        MateSuccess(1),
        //帮助人拒绝
        HelpReject(-1),
        //被帮助人搁置
        HelpedReject(-2),
        //帮助人搁置
        HelpShelve(-3),
        //系统关闭
        SysClose(-4);



        private int nCode;
        private HcMateStatus(int _nCode) {
            this.nCode = _nCode;
        }
        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }


    //用户类型
    public static enum HcUserType {


        help(1),
        //帮助人拒绝
        helped(2);



        private int nCode;
        private HcUserType(int _nCode) {
            this.nCode = _nCode;
        }
        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }

    public static enum ShopType{
        //主商城key
        MainShop("mainShop");

        private String nCode;
        private ShopType(String _nCode) {
            this.nCode = _nCode;
        }
        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }

}

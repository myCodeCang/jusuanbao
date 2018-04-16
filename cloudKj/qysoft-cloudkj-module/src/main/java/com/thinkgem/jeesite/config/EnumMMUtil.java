package com.thinkgem.jeesite.config;

import com.thinkgem.jeesite.common.config.EnumUtil;
import org.apache.commons.lang3.EnumUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Administrator on 2017-05-24.
 */
public class EnumMMUtil {

    public static enum  MoneyChangeType{
        buyMachine(60), //不插入账变
        //充值
        charget(0);


        private int nCode;

        private MoneyChangeType(int _nCode) {
            this.nCode = _nCode;
        }

        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }

        public static MoneyChangeType getChangeTypeByCode(int code) {
            Optional<MoneyChangeType> type = Arrays.stream(MoneyChangeType.values()).filter(changeType -> changeType.nCode == code).findAny();
            if (type.isPresent()) {
                return type.get();
            }

            return null;
        }
    }

}

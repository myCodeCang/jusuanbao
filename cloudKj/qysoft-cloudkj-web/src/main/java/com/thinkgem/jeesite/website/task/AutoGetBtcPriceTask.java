package com.thinkgem.jeesite.website.task;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.EnumUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.ValidationException;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.FileUtils2;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
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
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@Lazy(false)
@Transactional(readOnly = false,propagation= Propagation.REQUIRED,rollbackForClassName={"RuntimeException","Exception","ValidationException"})
public class AutoGetBtcPriceTask implements UserTaskBusiness {



    protected Logger logger = null;
    @Override
    public boolean doBusiness() {
        logger = getLogger();
        logger.error("start..................");
        File path = new File(this.getClass().getResource("/").getPath());
        String authUrl = "https://www.okcoin.com/api/v1/ticker.do?symbol=btc_usd";
        String result;
        try{
            result = HttpClientUtil.sendHttpGet(authUrl);
        }catch (Exception e){
            logger.error("获取最新价格失败。");
            return false;
        }
        HashMap<String, HashMap<String, String>> resMap = (HashMap<String, HashMap<String, String>>) JsonMapper.fromJsonString(result, HashMap.class);

        HashMap<String,String> hashMap = Maps.newHashMap();
        hashMap.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        hashMap.put("btcPrice",resMap.get("ticker").get("last"));

        String jsonPrice = JsonMapper.getInstance().toJson(hashMap);
        StringBuffer fileName = new StringBuffer();
        fileName.append(path).append(File.separator).append("btcPrice").append(File.separator).append("btcPrice.json");
        try {
            FileUtils2.writeToFile(fileName.toString(),jsonPrice,false);
        } catch (Exception e) {
            logger.error("写入价格失败"+e.getMessage());
        }

        logger.error("end..................");

        return true;
    }




}

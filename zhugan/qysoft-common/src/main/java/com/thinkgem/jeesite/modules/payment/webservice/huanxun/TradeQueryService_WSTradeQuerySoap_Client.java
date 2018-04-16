
package com.thinkgem.jeesite.modules.payment.webservice.huanxun;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import javax.xml.namespace.QName;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2017-03-27T13:18:42.997+08:00
 * Generated source version: 2.7.18
 * 
 */
public final class TradeQueryService_WSTradeQuerySoap_Client {

    private static final QName SERVICE_NAME = new QName("http://payat.ips.com.cn/WebService/TradeQuery", "WSTradeQuery");

    private TradeQueryService_WSTradeQuerySoap_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = WSTradeQuery.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        WSTradeQuery ss = new WSTradeQuery(wsdlURL, SERVICE_NAME);
        TradeQueryService port = ss.getWSTradeQuerySoap();

        {
        System.out.println("Invoking getIssuedByBillNo...");
        String _getIssuedByBillNo_getIssuedByBillNo = "";
        String _getIssuedByBillNo__return = port.getIssuedByBillNo(_getIssuedByBillNo_getIssuedByBillNo);
        System.out.println("getIssuedByBillNo.result=" + _getIssuedByBillNo__return);


        }
        {
        System.out.println("Invoking posQuery...");
        String _posQuery_posQuery = "";
        String _posQuery__return = port.posQuery(_posQuery_posQuery);
        System.out.println("posQuery.result=" + _posQuery__return);


        }
        {
        System.out.println("Invoking getIssuedRetrunInfo...");
        String _getIssuedRetrunInfo_getIssuedRetrunInfo = "";
        String _getIssuedRetrunInfo__return = port.getIssuedRetrunInfo(_getIssuedRetrunInfo_getIssuedRetrunInfo);
        System.out.println("getIssuedRetrunInfo.result=" + _getIssuedRetrunInfo__return);


        }
        {
        System.out.println("Invoking getTradeList...");
        String _getTradeList_getTradeList = "";
        String _getTradeList__return = port.getTradeList(_getTradeList_getTradeList);
        System.out.println("getTradeList.result=" + _getTradeList__return);


        }

        System.exit(0);
    }

}

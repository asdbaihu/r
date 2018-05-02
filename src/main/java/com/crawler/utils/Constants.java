package com.crawler.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by hcj on 2018/4/26.
 */
public class Constants {

    public  static SimpleDateFormat sdf = new SimpleDateFormat("MMM-d-yyyy K:m:s a",Locale.ENGLISH);
    public  static SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String  URL_S="https://etherscan.io/";
    /**
     * 总量获取的 url
     */
    public static  final String URL_1="https://etherscan.io/token/";

    /**
     *  获取交易列表的token
     */
    public static final String URL_2="https://etherscan.io/token/generic-tokentxns2?contractAddress=";

    /**
     * 判断用户类型的 url
     */
    public static final String URL_3="https://etherscan.io/address/";

    /**
     * 获取流通量
     */
    public static final String URL_4="https://www.feixiaohao.com/currencies/";

}

package com.crawler.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crawler.utils.httpclient.HttpClientUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcj on 2018/4/27.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String a = "0 txns ".trim().replace(" txns","");
        int s=Integer.parseInt(a);
        System.out.println(s);
        System.out.println(a);
    }

}

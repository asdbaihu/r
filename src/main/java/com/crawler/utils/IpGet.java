package com.crawler.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crawler.utils.httpclient.HttpClientUtil;

import java.io.IOException;

/**
 * Created by hcj on 2018/4/28.
 */
public class IpGet {

    public static JSONObject getOne() throws IOException {
        String a = HttpClientUtil.httpGet("http://api.xdaili.cn/xdaili-api//greatRecharge/getGreatIp?spiderId=6533f337aa1f478a8d842cfd9099d4d7&orderno=YZ20184285105l245m2&returnType=2&count=1");
        JSONObject object = JSONObject.parseObject(a);
        JSONArray array = JSONArray.parseArray(object.get("RESULT").toString());
        JSONObject ob = JSONObject.parseObject(array.get(0).toString());
        return ob;
    }

}

package com.crawler.r.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hcj on 2018/5/2.
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

}

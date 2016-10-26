package com.jasoncai.firstssm.controller;

import com.jasoncai.firstssm.proxy.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by cjs on 2016/10/14.
 */
@Controller
@RequestMapping("/jsonTest")
public class JsonTestController {
    protected static final String RESULT_ACTION = "redirect:/WEB-INF/jsp/result.jsp";
    private static final String RESULT = "result";

    @RequestMapping("/goto")
    public String jasonTest(ModelMap model){
        return "jsonTest";
    }

    @RequestMapping("/requestJson")
    public @ResponseBody ItemsCustom requestJson(ModelMap model, @RequestBody ItemsCustom itemsCustom){
        itemsCustom.setCreatetime(new Date());
        return null;
    }

    @RequestMapping("/responseJson")
    public @ResponseBody ItemsCustom responseJson(ModelMap model,ItemsCustom itemsCustom){
        itemsCustom.setDetail("这里是详情这里是详情");
        return itemsCustom;
    }
}

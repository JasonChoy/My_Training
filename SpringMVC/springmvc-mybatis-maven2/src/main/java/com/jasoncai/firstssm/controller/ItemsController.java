package com.jasoncai.firstssm.controller;

import com.jasoncai.firstssm.exception.CustomException;
import com.jasoncai.firstssm.proxy.ItemsCustom;
import com.jasoncai.firstssm.service.ItemsService;
import com.jasoncai.firstssm.vo.ItemsQueryVo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cjs on 2016/10/10.
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
    @Resource
    ItemsService itemsService;
    /*   使用InitBinder来处理Date类型的参数*/
    //At the time of initialization,convert the type "String" to type "date"
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping("/queryAll")
    public String queryAll(ModelMap model) throws Exception {
        ItemsQueryVo itemsQueryVo = new ItemsQueryVo();
        List<ItemsCustom> itemsCustoms  = itemsService.findItemsList(itemsQueryVo);
        model.put("itemsList",itemsCustoms);
        return "items/itemList";
    }

    @RequestMapping("/editItem")
    public String edit(ModelMap model,Integer id) throws Exception {
        ItemsQueryVo itemsQueryVo = new ItemsQueryVo();
        ItemsCustom itemsCustom  = itemsService.findItemById(id);
        model.put("item",itemsCustom);
        return "items/editItem";
    }

    @RequestMapping(value = "/updateItem",method ={RequestMethod.POST})
    public String updateItem(ModelMap model,ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception {
        //调用service根据商品id查询商品信息
        ItemsCustom itemsQueryVo  = itemsService.findItemById(itemsCustom.getId());

        //判断商品是否为空，根据id没有查询到商品，抛出异常，提示用户商品信息不存在
        if(itemsQueryVo == null){
            throw new CustomException("修改的商品信息不存在!");
        }
        itemsService.updateItem(itemsCustom);
        return "forward:queryAll.action";
    }

    // 批量删除 商品信息
    @RequestMapping("/deleteItems")
    public String deleteItems(ModelMap model,Integer[] items_id) throws Exception{
        itemsService.deleteItems(items_id);
        return "forward:queryAll.action";
    }
}
/*
redirect重定向

        redirect重定向特点：浏览器地址栏中的url会变化。修改提交的request数据无法传到重定向的地址。因为重定向后重新进行request（request无法共享）

forward页面转发

        通过forward进行页面转发，浏览器地址栏url不变，request可以共享。
*/

package com.jason.demo.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * Created by cjs on 2017/3/23.
 */
public class UserAction extends ActionSupport {

    public String add(){
        ActionContext.getContext().getSession().put("username","jasoncai");
        return "index";
    }

    public String del(){
        ActionContext.getContext().getSession().remove("username");
        return "index";
    }

    public String login(){
        return SUCCESS;
    }
}

package com.jason.demo.commons.web.taglib;

import com.jason.demo.filter.RequestFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created with IntelliJ IDEA.
 * User: lzp
 * Date: 12-4-23
 * Time: 下午12:21
 * To change this template use File | Settings | File Templates.
 */
public class FrameEditTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    public static final String FRAME_EDIT="Y";
    public static final String REMOVE_TARGET="Y";

    @Override
    public int doStartTag() throws JspException {
        try{
            pageContext.getOut().print(showFrameHtml());
        }catch (Exception ex){
            throw new JspTagException(ex.getMessage());
        }

        return SKIP_BODY;    //To change body of overridden methods use File | Settings | File Templates.
    }

     //标签结束时调用的处理方法
    public int doEndTag() {
        //继续执行后续的JSP页面内容
        return EVAL_PAGE;
    }
    private String showFrameHtml(){
        StringBuilder html=new StringBuilder();
        //SysUser user= WebContextFactory.getWebContext().getAdminUser();
        //String frameEdit=WebContextFactory.getWebContext().getRequest().getParameter("frameEdit");
        //String removeTarget=WebContextFactory.getWebContext().getRequest().getParameter("removeTarget");
        //String frontPath=WebContextFactory.getContentPath();
        HttpServletRequest request = RequestFilter.threadLocalRequest.get();
        String frameEdit=request.getParameter("frameEdit");
        String removeTarget=request.getParameter("removeTarget");
        String frontPath=request.getContextPath();
        if(/*user!=null && */FRAME_EDIT.equals(frameEdit)){
            html.append("<link href=\"").append(frontPath).append("/commons/frameEdit/framepoint.css\" rel=\"stylesheet\" type=\"text/css\" />");
            html.append("<script type=\"text/javascript\" src=\"").append(frontPath).append("/commons/frameEdit/framepoint.js\"></script>");
            html.append("<div class=\"framepoint_bar\" style=\"display: none;\"> <div class=\"framepoint_bar_bg\"></div> <!--<div class=\"framepoint_bar_tools\"> <a id=\"framepoint_edit\" href=\"javascript:;\">编辑</a> <a href=\"#\">上移</a> <a href=\"#\">下移</a> <a href=\"#\">删除</a> </div>--> <input id=\"frameinfo_cache\" type=\"hidden\" value=\"\" name=\"frameinfo_cache\"> </div>");
            html.append("<div class=\"framepoint_bar_tools\" style=\"display: none;\"> <a id=\"framepoint_edit\" href=\"javascript:;\">编辑</a> </div>");
        }
        if(REMOVE_TARGET.equals(removeTarget)){
            html.append("<script type=\"text/javascript\" src=\"").append(frontPath).append("/commons/frameEdit/removeTarget.js?time=").append(System.currentTimeMillis()).append("\"></script>");
        }
        return html.toString();
    }
}


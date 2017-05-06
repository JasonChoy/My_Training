package com.jason.demo.commons.web.taglib;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Created by IntelliJ IDEA.
 * User: lzp
 * Date: 11-5-16
 * Time: 下午6:22
 * from:http://www.adictosaltrabajo.com/tutoriales/tutoriales.php?pagina=tagsconcuerpo
 * */
public class PageTag extends BodyTagSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String LI_LINK_STR = "<li><a href='";

    private static Logger logger = Logger.getLogger(PageTag.class);

    /**
     * property declaration for tag attribute: TotalPages.
     */
    private int totalPages;
    /**
     * property declaration for tag attribute: CurrentPage.
     */
    private int currentPage;
    /**
     * property declaration for tag attribute: Language.
     */
    private java.lang.String language = "Simplified Chinese";
    /**
     * property declaration for tag attribute: Style.
     */
    private java.lang.String style = "Standard";
    /**
     * Holds value of property totalRecords.
     */
    private int totalRecords;
    private String frontPath;
    private int displayNum;
    private boolean isDisplaySelect;
    private boolean isDisplayGoToPage;
    private String ajaxUrl;

    public String getAjaxUrl() {
        return ajaxUrl;
    }

    public void setAjaxUrl(String ajaxUrl) {
        this.ajaxUrl = ajaxUrl;
    }

    public PageTag() {
        super();
        displayNum = 10;
        isDisplaySelect = true;
        isDisplayGoToPage = false;
    }
    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   User methods.                                          ///
    ///                                                          ///
    ///   Modify these methods to customize your tag handler.    ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    //
    // methods called from doStartTag()
    //
    /**
     *
     * Fill in this method to perform other operations from doStartTag().
     *
     */
    public void otherDoStartTagOperations() {

        //
        // TODO: code that performs other operations in doStartTag
        //       should be placed here.
        //       It will be called after initializing variables,
        //       finding the parent, setting IDREFs, etc, and
        //       before calling theBodyShouldBeEvaluated().
        //
        //       For example, to print something out to the JSP, use the following:
        //
        try {
            JspWriter out = pageContext.getOut();
            out.println(showStandardStyle());
        } catch (java.io.IOException ex) {
            // do something
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     *
     * Fill in this method to determine if the tag body should be evaluated
     * Called from doStartTag().
     *
     */
    public boolean theBodyShouldBeEvaluated() {

        //
        // TODO: code that determines whether the body should be
        //       evaluated should be placed here.
        //       Called from the doStartTag() method.
        //
        return true;

    }
    //
    // methods called from doEndTag()
    //
    /**
     *
     * Fill in this method to perform other operations from doEndTag().
     *
     */
    public void otherDoEndTagOperations() {    //
        // TODO: code that performs other operations in doEndTag
        //       should be placed here.
        //       It will be called after initializing variables,
        //       finding the parent, setting IDREFs, etc, and
        //       before calling shouldEvaluateRestOfPageAfterEndTag().
        //
    }

    /**
     *
     * Fill in this method to determine if the rest of the JSP page
     * should be generated after this tag is finished.
     * Called from doEndTag().
     *
     */
    public boolean shouldEvaluateRestOfPageAfterEndTag() {

        //
        // TODO: code that determines whether the rest of the page
        //       should be evaluated after the tag is processed
        //       should be placed here.
        //       Called from the doEndTag() method.
        //
        return true;

    }
    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   Tag Handler interface methods.                         ///
    ///                                                          ///
    ///   Do not modify these methods; instead, modify the       ///
    ///   methods that they call.                                ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    /**
     * .
     *
     * This method is called when the JSP engine encounters the start tag,
     * after the attributes are processed.
     * Scripting variables (if any) have their values set here.
     * @return EVAL_BODY_INCLUDE if the JSP engine should evaluate the tag body, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */
    public int doStartTag() throws JspException {
        otherDoStartTagOperations();

        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }

    /**
     * .
     *
     *
     * This method is called after the JSP engine finished processing the tag.
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP page, otherwise return SKIP_PAGE.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */
    public int doEndTag() throws JspException {
        otherDoEndTagOperations();

        if (shouldEvaluateRestOfPageAfterEndTag()) {
            return EVAL_PAGE;
        } else {
            return SKIP_PAGE;
        }
    }

    public java.lang.String getLanguage() {
        return language;
    }

    public void setLanguage(java.lang.String value) {
        language = value;
    }

    public java.lang.String getStyle() {
        return style;
    }

    public void setStyle(java.lang.String value) {
        style = value;
    }

    /**
     * .
     * Fill in this method to process the body content of the tag.
     * You only need to do this if the tag's BodyContent property
     * is set to "JSP" or "tagdependent."
     * If the tag's bodyContent is set to "empty," then this method
     * will not be called.
     */
    public void writeTagBodyContent(JspWriter out, BodyContent bodyContent) throws IOException {
        //
        // TODO: insert code to write html before writing the body content.
        //       e.g.  out.println("<B>" + getAttribute1() + "</B>");
        //             out.println("   <BLOCKQUOTE>");

        //
        // write the body content (after processing by the JSP engine) on the output Writer
        //
        bodyContent.writeOut(out);

        //
        // Or else get the body content as a string and process it, e.g.:
        //     String bodyStr = bodyContent.getString();
        //     String result = yourProcessingMethod(bodyStr);
        //     out.println(result);
        //

        // TODO: insert code to write html after writing the body content.
        //       e.g.  out.println("   <BLOCKQUOTE>");

        // clear the body content for the next time through.
        bodyContent.clearBody();
    }

    /**
     * .
     *
     * Handles exception from processing the body content.
     */
    public void handleBodyContentException(Exception ex) throws JspException {
        // Since the doAfterBody method is guarded, place exception handing code here.
        throw new JspException("error in PageSeperatorTag: " + ex);
    }

    /**
     * .
     *
     *
     * This method is called after the JSP engine processes the body content of the tag.
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body again, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */
    public int doAfterBody() throws JspException {
        try {
            //
            // This code is generated for tags whose bodyContent is "JSP"
            //
            BodyContent bodyContent = getBodyContent();
            JspWriter out = bodyContent.getEnclosingWriter();

            writeTagBodyContent(out, bodyContent);
        } catch (Exception ex) {
            handleBodyContentException(ex);
        }

        if (theBodyShouldBeEvaluatedAgain()) {
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    /**
     *
     * Fill in this method to determine if the tag body should be evaluated
     * again after evaluating the body.
     * Use this method to create an iterating tag.
     * Called from doAfterBody().
     *
     */
    public boolean theBodyShouldBeEvaluatedAgain() {
        //
        // TODO: code that determines whether the tag body should be
        //       evaluated again after processing the tag
        //       should be placed here.
        //       You can use this method to create iterating tags.
        //       Called from the doAfterBody() method.
        //
        return false;
    }

    private String getCleanUrl() throws UnsupportedEncodingException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        Map parameters = request.getParameterMap();
        boolean isFirst = true;
        Set entries = parameters.entrySet();
        Iterator it = entries.iterator();
        StringBuilder reqUrlBuilder = new StringBuilder();
        if(null==this.ajaxUrl){
            reqUrlBuilder.append(request.getRequestURI());
        }else{
            reqUrlBuilder.append(this.ajaxUrl);
        }

        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String name = (String) entry.getKey();
            String[] value = (String[]) entry.getValue();

            String[] temp = new String[value.length];
            System.arraycopy(value, 0, temp, 0, value.length);

            for (int i = 0; i < value.length; i++) {
                temp[i] = URLEncoder.encode(temp[i], "utf-8");
            }
            if (!name.equalsIgnoreCase("page")) {
                for (int j = 0; j < temp.length; j++) {
                    if (StringUtils.isNotEmpty(temp[j])) {
                        if (isFirst) {
                            isFirst = false;
                            reqUrlBuilder.append("?" + name + "=" + temp[j]);
                        } else {
                            String param = "&" + name + "=" + temp[j];
                            String param2 = "&" + name + "=" + temp[j] + "&";
                            if (reqUrlBuilder.indexOf(param2) < 0 && !reqUrlBuilder.toString().endsWith(param)) {
                                reqUrlBuilder.append("&" + param);
                            }
                        }
                    }
                }
            }
        }
        return response.encodeURL(reqUrlBuilder.toString());
    }

    private String getCompleteUrl(String reqUrl, int page) {
        if (reqUrl.indexOf('?') > 0) {
            return reqUrl + "&page=" + page;
        } else {
            return reqUrl + "?page=" + page;
        }
    }

    private String getCompleteUrlNoParam(String reqUrl) {
        if (reqUrl.indexOf('?') > 0) {
            return reqUrl + "&";
        } else {
            return reqUrl + "?";
        }
    }

    private void clacCurrentPageStyle(StringBuilder re, String cleanUrl){ //NOSONAR
        int pagenumber = displayNum;
        int pagecenter = pagenumber / 2 - 1;
        int pagebet = pagenumber / 2 + 1;
        int beginPage = 1;
        int endPage = 1;

        if (currentPage < pagebet) {
            beginPage = 1;
        } else {
            beginPage = currentPage - pagecenter;
        }

        if (currentPage + pagecenter > totalPages) {
            endPage = totalPages;
        } else {
            endPage = currentPage + pagecenter;
        }

        if (currentPage + pagecenter < pagenumber) {
            endPage = pagenumber;
        }

        if (endPage - currentPage < pagecenter) {
            beginPage = totalPages - (pagenumber - 1);
            if (beginPage != 1) {
                beginPage += 1;
            }
        }

        if (beginPage <= 0) {
            beginPage = 1;
        }

        if (endPage > totalPages) {
            endPage = totalPages;
        }

//        if (currentPage >= pagebet && beginPage != 1) {
//            re.append(LI_LINK_STR + getCompleteUrl(cleanUrl, 1) +
//                    "' class='everyPage'>1</a></li>");
//            if (currentPage != pagebet) {
//                re.append("<li>...&nbsp;</li>");
//            }
//        }

        for (int i = beginPage; i <= endPage; i++) {
            String item = "";
            if (i != currentPage) {
                item = LI_LINK_STR + getCompleteUrl(cleanUrl, i) +
                        "' class='everyPage'>" + i +
                        "</a></li>";
            } else {
                item = "<li><span class='nowPage'>" + i +
                        "</span></li>";
            }
            re.append(item);
        }
//        if(totalRecords-endPage>=1){
//            if(totalRecords-endPage>1){
//                re.append("<li>...&nbsp;</li>");
//            }
//            re.append(LI_LINK_STR + getCompleteUrl(cleanUrl, totalPages) +
//                    "' class='everyPage'>"+totalPages+"</a></li>");
//        }

    }

    private String showStandardStyle() throws Exception { //NOSONAR
        StringBuilder re = new StringBuilder();
        String cleanUrl = getCleanUrl();
        /*int ItemsDisplayed = 15;
if (totalRecords>0)
{
re = "总共" + totalRecords + "条记录,";
}
re += "总共" + totalPages + "页,当前是第" + currentPage + "页,&nbsp;&nbsp;";*/

        re.append("<div id='infoPage'><ul>");

        if (currentPage > 1) {
            re.append(LI_LINK_STR + getCompleteUrl(cleanUrl, currentPage - 1) +
                    "' class='upPage' title='上一页'>上一页</a></li>");
        }

        //计算显示的页
        if (displayNum == 0) {
            re.append("<li class='pages'><span class='currentPage'>" + currentPage + "</span>/" + totalPages + "</li>");
        } else {
            clacCurrentPageStyle(re, cleanUrl);
        }
        if (currentPage < totalPages) {
            re.append(LI_LINK_STR + getCompleteUrl(cleanUrl, currentPage + 1) +
                    "' class='downPage' title='下一页'>下一页</a></li>");
        }

        boolean isSelect = isDisplaySelect;
        if (isDisplayGoToPage) {
            isSelect = false;
            re.append("<li style='margin-top:5px;'><div id='page-skip'>&nbsp;&nbsp;第&nbsp;<input id='inputPage' value='" + currentPage + "'/>&nbsp;页");
            String script = "javascript:var goPage=this.parentNode.parentNode.getElementsByTagName('input')[0].value;if(isNaN(goPage)||goPage>"
                    + totalPages + "||goPage<1||goPage==" + currentPage + ")return;document.location='" + getCompleteUrlNoParam(cleanUrl)
                    + "page='+goPage;return false;";
            re.append("<button href='javascript:;' onclick=\"" + script + "\" class='goToPage'>确定</button><div></li>");
        }

        if (isSelect) {
            //下拉的翻页可以不显示
            re.append("<li>&nbsp;&nbsp;第&nbsp;<select name='select2' onchange=\"window.location.href='" +
                    getCompleteUrlNoParam(cleanUrl) +
                    "page='+this.options[this.selectedIndex].value + ''\">");
            for (int iCount = 1; iCount <= totalPages; iCount++) {
                String strSelected = "";
                if (iCount == currentPage) {
                    strSelected = "selected";
                }
                re.append("<option value='" + iCount + "' " + strSelected + ">-" +
                        iCount + "-</option>");
            }
            re.append("</select>&nbsp;页</li>");
        }
        re.append("</ul></div>");
        return re.toString();
    }

    /**
     * Getter for property totalRecords.
     * @return Value of property totalRecords.
     */
    public int getTotalRecords() {
        return this.totalRecords;
    }

    /**
     * Setter for property totalRecords.
     * @param totalRecords New value of property totalRecords.
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int value) {
        totalPages = value;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int value) {
        currentPage = value;
    }

    public String getFrontPath() {
        return frontPath;
    }

    public int getDisplayNum() {
        return displayNum;
    }

    public boolean isIsDisplaySelect() {
        return isDisplaySelect;
    }

    public void setFrontPath(String value) {
        frontPath = value;
    }

    public void setDisplayNum(int displayNum) {
        this.displayNum = displayNum;
    }

    public void setIsDisplaySelect(boolean isDisplaySelect) {
        this.isDisplaySelect = isDisplaySelect;
    }

    public boolean isIsDisplayGoToPage() {
        return isDisplayGoToPage;
    }

    public void setIsDisplayGoToPage(boolean isDisplayGoToPage) {
        this.isDisplayGoToPage = isDisplayGoToPage;
    }
}

$(function(){

    /*开启编辑模式，需要屏蔽所有A标签的href*/
    $("a").attr("href","javascript:;");
    $(".emptyCustom").parent().parent().show();
    /*点击编辑时出发的事件*/
    $("#framepoint_edit").live("click",function(){
//        alert($("#frameinfo_cache").val())
        //   top.popupEditWindowJS('innerCode', 'width', 'height');
        var frameinfo=$("#frameinfo_cache").val();
        var frameinfos=frameinfo.split("|");
        alert("弹出一个添加多个图片的框 点击保存 即可装修图片");

        /*具体逻辑逻辑在这里修改*/
/*        if(frameinfos.length==1){
            window.top.popupEditWindowJS(frameinfo, '','');

        }else if(frameinfos.length==2){
            window.top.popupEditWindowJS(frameinfos[0], frameinfos[1],'');
        }else if(frameinfos.length==4){
            window.top.popupEditWindowJS(frameinfos[0], frameinfos[1],frameinfos[2],frameinfos[3]);
        }else{
            window.top.popupEditWindowJS(frameinfos[0], frameinfos[1],frameinfos[2]);
        }*/
    })
    $(".frameEdit").css({
        "min-height":"30px",
        "min-width":"30px"
    });


    /*显示隐藏主体部分编辑工具*/
    $(".frameEdit").mouseover(function(){
//        if(document.getElementById("framepoint_dialog").style.display == "block") return;
        //缓存
        $(":hidden[name='frameinfo_cache']").attr("value", $(this).attr("frameinfo"));
        var frameinfo=$("#frameinfo_cache").val();
        var blockname = $(this).attr("frameinfo").split('|')[1];
        var frameinfos=frameinfo.split("|");
        //显示
        var framepoint_bar = $(".framepoint_bar");
        framepoint_bar.show().css({
            'width' : $(this).width()-4 + 'px',
            'height' : (blockname == 'header') ? $(this).height()-39 + 'px' : $(this).height()-4 + 'px',
            'top' : (blockname == 'header') ? $(this).offset().top+30 + 'px' : $(this).offset().top + 'px',
            'left' : $(this).offset().left + 'px'
        });
        $(".framepoint_bar_tools").show().css({
            'width' : '40px',
            'height' : '30px',
            'top' : (blockname == 'header') ? framepoint_bar.offset().top+30 + 'px' : framepoint_bar.offset().top + 'px',
            'left' : framepoint_bar.offset().left + 'px'
        });

    });
    $(".framepoint_bar").hover(
        function(){
            $(this).show();
            $(this).next(".framepoint_bar_tools").show();
        },
        function(){
            $(this).hide();
            $(this).next(".framepoint_bar_tools").hide();
        }
    );


//重新加载模块
    function loading_block(block_name,mod){
        //alert(block_name); return;
        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"get_block_sound",mod_name:block_name,mod:mod},
            dataType:'text',
            error:function(){
                //alert("error");
            },
            success:function(data){
                //alert(data); return;
                switch(block_name){
                    case "info_notice" :
                        $(".framepoint_notice").html(data);
                        break;
                    case "cust_flash" :
                        $(".framepoint_flash").html(data);
                        break;
                    case "pro_new" :
                        $(".framepoint_pro_new").html(data);
                        break;
                    case "pro_hot" :
                        $(".framepoint_pro_hot").html(data);
                        break;
                    case "pro_best" :
                        $(".framepoint_pro_best").html(data);
                        break;
                    case "brand" :
                        $(".framepoint_brand").html(data);
                        break;
                    case "menu_cat" :
                        $(".framepoint_menu_cat").html(data);
                        break;
                    case "pro_class" :
                        $(".framepoint_pro_class").html(data);
                        break;
                    case "info_promo" :
                        $(".framepoint_info_promo").html(data);
                        break;
                    case "comm_list" :
                        $(".framepoint_comm_list").html(data);
                        break;
                    case "order_list" :
                        $(".framepoint_order_list").html(data);
                        break;
                    case "header" :
                        $(".framepoint_header").html(data);
                        break;
                    case "footer" :
                        $(".framepoint_footer").html(data);
                        break;
                    case "bottom_faq" :
                        $(".framepoint_bottom_faq").html(data);
                        break;
                    case "bottom_link" :
                        $(".framepoint_bottom_link").html(data);
                        break;
                    case "pro_seque" :
                        $(".framepoint_pro_seque").html(data);
                        break;
                    case "pro_recom" :
                        $(".framepoint_pro_recom").html(data);
                        break;
                    case "rec_view" :
                        $(".framepoint_rec_view").html(data);
                        break;
                    case "pro_cuxiao":
                        $(".framepoint_pro_cuxiao").html(data);
                        break;
                    default :
                        break;
                }
            }
        });
    }

//更新产品分类首页显示行数(√)
    $(":button[name='save_pro_class_line_btn']").live("click", function(){
        var pro_class_line = $(this).next().val();

        if(pro_class_line <= 0 || pro_class_line > 5){
            alert("产品分类行数在1-5之间，请确认没超出范围。");
            return;
        }

        framepoint_operation_tips("show", "", "正在编辑分类行数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_pro_class_line",pro_class_line:pro_class_line},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "分类行数编辑失败！");
            },
            success:function(data){
                if(data == "success"){
                    loading_block("pro_class");
                    framepoint_operation_tips("hide", "success", "分类行数编辑成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                    return;
                }
            }
        });
    });

//更新在首页显示的产品分类(√)
    $(":button[name='save_pro_class_isindex_btn']").live("click", function(){
        framepoint_operation_tips("show", "", "正在编辑展示分类！");

        //遍历分类，生成json
        var class_json = "{";
        var i = 0;
        $(":checkbox[name='pro_class_isindex[]']").each(function(){
            var parting = (i != 0) ? "," : "";
            if(this.checked == true){
                class_json += parting + '"'+i+'":"'+$(this).val()+'"';
                i++;
            }
        });
        class_json += "}";
        //alert(class_json); return;
        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_pro_class_isindex",class_json:class_json},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "首页展示分类编辑失败！");
            },
            success:function(data){
                if(data == "success"){
                    loading_block("pro_class");
                    $("#framepoint_block_info").click();
                    framepoint_operation_tips("hide", "success", "首页展示分类编辑成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请通知管理员！");
                    return;
                }
            }
        });
    });

//更新产品分类广告(√)
    $(":button[name='save_pro_class_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");
        var class_id = $(this).attr("class_id");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！");
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_pro_class_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial,class_id:class_id},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("pro_class");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("pro_class");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", e);
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_pro_class_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial,class_id:class_id},
                dataType:'json',
                error:function(){
                    //alert("error");
                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("pro_class");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("pro_class");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }
    });//更新产品分类广告

//flash更新参数(√)
    $(":button[name='save_cust_flash_btn']").live('click', function(){
        var file_id = $(this).next().attr("id");
        var flash_id = $(this).next().attr("flash_id");
        var flash_name = $(this).parent().prev().children(":input").eq(0).val();
        var flash_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(flash_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    //	alert(data); return;
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_flash_info",flash_id:flash_id,path:data.path,flash_url:flash_url,flash_name:flash_name,file_id:file_id},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[flash_id='"+data.id+"']").attr("value", "");
                                        $(":input[flash_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("cust_flash");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' flash_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("cust_flash");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", e);
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_flash_info",flash_id:flash_id,path:'',flash_url:flash_url,flash_name:flash_name},
                dataType:'json',
                error:function(){
                    //alert("error");
                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[flash_id='"+data.id+"']").attr("value", "");
                            $(":input[flash_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("cust_flash");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' flash_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("cust_flash");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }

    });//flash参数更新结束

//公告更新参数(√)
    $(":button[name='save_info_notice_line_btn']").live("click", function(){
        var info_notice_num = $(this).next().val();

        framepoint_operation_tips("show", "", "正在编辑公告参数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_notice_info",info_notice_num:info_notice_num},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "公告参数编辑失败！");
            },
            success:function(data){
                if(data == "success"){
                    loading_block("info_notice");
                    framepoint_operation_tips("hide", "success", "公告参数编辑成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                    return;
                }
            }
        });
    });

//更新品牌参数(√)
    $(":button[name='save_brand_line_btn']").live("click", function(){
        var pro_brand_num = $(this).next().val();

        if(pro_brand_num <= 0 || pro_brand_num > 20){
            alert("品牌个数在1-20之间，请确认没超出范围。");
            return;
        }

        framepoint_operation_tips("show", "", "正在编辑品牌参数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_brand_info",pro_brand_num:pro_brand_num},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "编辑品牌参数出错！");
            },
            success:function(data){
                //alert(data);return;
                if(data == "success"){
                    loading_block("brand");
                    framepoint_operation_tips("hide", "success", "编辑品牌参数成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请通知管理员！");
                    return;
                }
            }
        });
    });

//更新促销信息(√)
    $(":button[name='save_info_promo_line_btn']").live("click", function(){
        var info_promo_num = $(this).next().val();

        framepoint_operation_tips("show", "", "正在编辑促销信息参数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_info_promo_info",info_promo_num:info_promo_num},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "促销信息参数编辑失败！");
            },
            success:function(data){
                if(data == "success"){
                    loading_block("info_promo");
                    framepoint_operation_tips("hide", "success", "促销信息参数编辑成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                    return;
                }
            }
        });
    });

//更新评论列表(√)
    $(":button[name='save_comm_list_line_btn']").live("click", function(){
        var pro_comm_num = $(this).next().val();

        if(pro_comm_num <= 0 || pro_comm_num > 18){
            alert("评论条数在1-18条之间，请确认没有超出范围。");
            return;
        }

        framepoint_operation_tips("show", "", "正在编辑评价参数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_comm_list_info",pro_comm_num:pro_comm_num},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "评价参数编辑失败！");
            },
            success:function(data){
                if(data == "success"){
                    loading_block("comm_list");
                    framepoint_operation_tips("hide", "success", "评价参数编辑成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                    return;
                }
            }
        });
    });

//更新最新产品行数(√)
    $(":button[name='save_pro_new_line_btn']").live("click", function(){
        var pro_line = $(this).next().val();
        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        if(pro_line <= 0 || pro_line > 5){
            alert("最新产品行数在1-5之间，请确定没超出范围。");
            return;
        }

        framepoint_operation_tips("show", "", "正在编辑最新产品行数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_pro_new_line",pro_line:pro_line,mod_type:mod_type},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "编辑最新产品失败！");
            },
            success:function(data){
                //alert(data); return;
                if(data == "success"){
                    loading_block("pro_new",mod_type);
                    framepoint_operation_tips("hide", "success", "编辑最新产品行数成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                    return;
                }
            }
        });
    });//更新最新产品行数

//更新热卖产品行数(√)
    $(":button[name='save_pro_hot_line_btn']").live("click", function(){
        var pro_line = $(this).next().val();
        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        if(pro_line <= 0 || pro_line > 5){
            alert("热卖产品行数在1-5之间，请确认没有超出范围。");
            return;
        }

        framepoint_operation_tips("show", "", "正在编辑热卖产品行数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_pro_hot_line",pro_line:pro_line,mod_type:mod_type},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "编辑热卖产品行数失败！");
            },
            success:function(data){
                //alert(data); return;
                if(data == "success"){
                    loading_block("pro_hot",mod_type);
                    framepoint_operation_tips("hide", "success", "编辑热卖产品行数成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                    return;
                }
            }
        });
    });//更新热卖产品行数

//更新推荐产品行数(√)
    $(":button[name='save_pro_best_line_btn']").live("click", function(){
        var pro_line = $(this).next().val();
        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        if(pro_line <= 0 || pro_line > 5){
            alert("推荐产品行数在1-5之间，请确认没有超出范围。");
            return;
        }

        framepoint_operation_tips("show", "", "正在编辑推荐产品行数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_pro_best_line",pro_line:pro_line,mod_type:mod_type},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "编辑推荐产品行数失败！");
            },
            success:function(data){
                if(data == "success"){
                    loading_block("pro_best",mod_type);
                    framepoint_operation_tips("hide", "success", "编辑热卖产品行数成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                    return;
                }
            }
        });
    });//更新推荐产品行数

//更新推荐产品行数(√)
    $(":button[name='save_pro_cuxiao_line_btn']").live("click", function(){
        var pro_line = $(this).next().val();
        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        if(pro_line <= 0 || pro_line > 5){
            alert("产品行数在1-5之间，请确认没有超出范围。");
            return;
        }

        framepoint_operation_tips("show", "", "正在编辑推荐产品行数！");

        $.ajax({
            type:'POST',
            url:'temp_ajax.php',
            data:{action:"set_pro_cuxiao_line",pro_line:pro_line,mod_type:mod_type},
            dataType:'text',
            error:function(){
                //alert("error");
                framepoint_operation_tips("hide", "error", "编辑推荐产品行数失败！");
            },
            success:function(data){
                if(data == "success"){
                    loading_block("pro_best",mod_type);
                    framepoint_operation_tips("hide", "success", "编辑热卖产品行数成功！");
                }else{
                    //alert("系统错误，请通知管理员！");
                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                    return;
                }
            }
        });
    });//更新推荐产品行数

//更新最新产品广告(√)
    $(":button[name='save_pro_new_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！");
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_pro_new_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                //alert(data);return;
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("pro_new");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("pro_new");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", e);
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_pro_new_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("pro_new");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");

                            loading_block("pro_new");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }
    });//更新最新产品广告

//更新热卖产品广告(√)
    $(":button[name='save_pro_hot_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！");
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_pro_hot_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("pro_hot");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("pro_hot");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", e);
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_pro_hot_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("pro_hot");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("pro_hot");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }
    });//更新热卖产品广告

//更新推荐产品广告(√)
    $(":button[name='save_pro_best_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！");
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_pro_best_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("pro_best");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("pro_best");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", e);
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_pro_best_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("pro_best");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("pro_best");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }
    });//更新推荐产品广告

//更新头部广告(√)
    $(":button[name='save_header_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_header_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        loading_block("header");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        loading_block("header");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_header_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            loading_block("header");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            loading_block("header");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }
    });//更新头部广告

//更新帮助广告(√)
    $(":button[name='save_faq_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_faq_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        loading_block("bottom_faq");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        loading_block("bottom_faq");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_faq_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            loading_block("bottom_faq");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            loading_block("bottom_faq");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }

            });
        }
    });//更新帮助广告

//更新友情链接广告(√)
    $(":button[name='save_bottom_link_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_bottom_link_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        loading_block("bottom_link");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        loading_block("bottom_link");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_bottom_link_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            loading_block("bottom_link");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            loading_block("bottom_link");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }

            });
        }
    });//更新友情链接广告

//更新产品排列广告(√)
    $(":button[name='save_pro_seque_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_pro_seque_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        loading_block("pro_seque");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        loading_block("pro_seque");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_pro_seque_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            loading_block("pro_seque");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            loading_block("pro_seque");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }

            });
        }
    });//更新产品排列广告

//更新分类畅销产品广告(√)
    $(":button[name='save_pro_recom_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_pro_recom_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        loading_block("pro_recom");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        loading_block("pro_recom");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_pro_recom_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            loading_block("pro_recom");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            loading_block("pro_recom");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }

            });
        }
    });//更新分类畅销产品广告

//更新最近浏览产品广告(√)
    $(":button[name='save_rec_view_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_rec_view_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        loading_block("rec_view");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        loading_block("rec_view");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_rec_view_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            loading_block("rec_view");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            loading_block("rec_view");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }

            });
        }
    });//更新最近浏览产品广告

//更新底部广告(√)
    $(":button[name='save_footer_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_footer_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        loading_block("footer");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        loading_block("footer");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_faq_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            loading_block("footer");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            loading_block("footer");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }

                }

            });
        }
    });//更新底部广告

//更新公告广告(√)
    $(":button[name='save_info_notice_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_info_notice_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("info_notice");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("info_notice");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_info_notice_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("info_notice");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("info_notice");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }

                }

            });
        }
    });//更新公告广告

//更新品牌广告(√)
    $(":button[name='save_brand_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_brand_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("brand");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("brand");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_brand_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("brand");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("brand");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }

                }

            });
        }
    });//更新品牌广告

//更新首页分类广告(√)
    $(":button[name='save_menu_cat_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！"
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_menu_cat_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", error);
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("menu_cat");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("menu_cat");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", "上传图片出错！");
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_menu_cat_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("menu_cat");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("menu_cat");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }

                }

            });
        }
    });//更新首页分类广告

//更新促销信息广告(√)
    $(":button[name='save_info_promo_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！");
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_info_promo_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("info_promo");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("info_promo");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", e);
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_info_promo_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("info_promo");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("info_promo");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }
    });//更新促销信息广告

//更新评论广告(√)
    $(":button[name='save_comm_list_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！");
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_comm_list_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("comm_list");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("comm_list");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", e);
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_comm_list_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("comm_list");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("comm_list");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }
    });//更新评论广告

//更新订单广告(√)
    $(":button[name='save_order_list_banner_btn']").live("click", function(){
        var file_id = $(this).next().attr("id");
        var banner_id = $(this).next().attr("banner_id");
        var banner_name = $(this).parent().prev().children(":input").eq(0).val();
        var banner_url = $(this).parent().prev().children(":input").eq(1).val();
        var up_file = $(this).next().val();
        var serial = $(this).attr("serial");

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];

        framepoint_operation_tips("show", "", "正在编辑广告！");

        if(banner_id == '0'){
            if(up_file == ""){
                //alert("请选择要上传的图片！");
                framepoint_operation_tips("hide", "error", "请选择要上传的图片！");
                return;
            }
        }

        if(up_file != ""){//判断是否有上传

            var ext = get_path_ext(up_file);//获取扩展名
            if(ext != "jpg" && ext != "gif" && ext != "png" && ext != "bmp"){//验证上传格式
                //alert("上传的文件不被允许！");
                framepoint_operation_tips("hide", "error", "只能上传JPG、GIF、PNG、BMP格式图片！");
                return;
            }

            //上传
            $.ajaxFileUpload({
                url:'temp_ajax.php?action=upload_file&file_id='+file_id,
                secureuri:true,
                fileElementId:file_id,
                dataType: 'json',
                success: function (data, status){
                    if(data.state != 'undefined' && data.state == 'success'){
                        $.ajax({
                            type:'POST',
                            url:'temp_ajax.php',
                            data:{action:"set_order_list_banner",banner_id:banner_id,mod_type:mod_type,path:data.path,banner_url:banner_url,banner_name:banner_name,file_id:file_id,serial:serial},
                            dataType:'json',
                            error:function(){
                                //alert("error");
                                framepoint_operation_tips("hide", "error", "编辑广告出错！");
                            },
                            success:function(data){
                                if(data.state != 'undefined' && data.state == 'error'){
                                    //alert('error');
                                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                                }else if(data.state != 'undefined' && data.state == 'success'){
                                    if(data.type == 'update'){
                                        $(":input[banner_id='"+data.id+"']").attr("value", "");
                                        $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                                        $("#framepoint_block_info").click();
                                        loading_block("order_list");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }else if(data.type == 'add'){
                                        $(":input[id='"+data.file_id+"']").attr("value", "");
                                        $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                                        $("#framepoint_block_info").click();
                                        loading_block("order_list");
                                        framepoint_operation_tips("hide", "success", "编辑广告成功！");
                                    }
                                }else{
                                    //alert("系统错误，请联系管理员！");
                                    framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                                    return;
                                }
                            }
                        });
                    }
                },
                error: function(data, status, e){
                    //alert(e);
                    framepoint_operation_tips("hide", "error", e);
                }
            });
        }else{//不上传更新网址
            $.ajax({
                type:'POST',
                url:'temp_ajax.php',
                data:{action:"set_order_list_banner",banner_id:banner_id,mod_type:mod_type,path:'',banner_url:banner_url,banner_name:banner_name,serial:serial},
                dataType:'json',
                error:function(){
                    //alert("error");
                    framepoint_operation_tips("hide", "error", "编辑广告出错！");
                },
                success:function(data){
                    if(data.state != 'undefined' && data.state == 'error'){
                        //alert('error');
                        framepoint_operation_tips("hide", "error", "编辑广告出错！");
                    }else if(data.state != 'undefined' && data.state == 'success'){
                        if(data.type == 'update'){
                            $(":input[banner_id='"+data.id+"']").attr("value", "");
                            $(":input[banner_id='"+data.id+"']").parent().next().children().attr("src", data.img_path);
                            $("#framepoint_block_info").click();
                            loading_block("order_list");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }else if(data.type == 'add'){
                            $(":input[id='"+data.file_id+"']").attr("value", "");
                            $(":input[id='"+data.file_id+"']").parent().after("<dd><img src='"+data.img_path+"' banner_id='"+data.id+"' /></dd>");
                            $("#framepoint_block_info").click();
                            loading_block("order_list");
                            framepoint_operation_tips("hide", "success", "编辑广告成功！");
                        }
                    }else{
                        //alert("系统错误，请联系管理员！");
                        framepoint_operation_tips("hide", "error", "系统错误，请联系管理员！");
                        return;
                    }
                }
            });
        }
    });//更新订单广告

//详情页推荐产品管理
    $(":button[name='pro_cuxiao_goods_edit']").live('click', function(){
        $(".plug_goods_info_box ul").qiboot_goods_choose({
            othertype: 'cuxiao',
            returnprocess: 'full',
            returnname: function(data){
                var id = '',
                    str = '';

                for(i in data){
                    if(i == 'containsValue') continue;
                    if(isNaN(parseInt(data[i][0]))) continue;

                    id += (i > 0) ? ',' : '';
                    id += parseInt(data[i][0]);

                    str += "<ol class='list'>";
                    str += "<ol class='img'><img src='"+data[i][1]+"' /></ol>";
                    str += "<ol class='txt'>"+data[i][2]+"</ol>";
                    str += "</ol>";
                }

                $.ajax({
                    type: 'POST',
                    url: 'temp_ajax.php',
                    data: {action:'set_cuxiao_id', cuxiao_id: id},
                    dataType: 'text',
                    error: function(){},
                    success: function(data){
                        if(data == 'success'){
                            $("#pro_cuxiao_goods_list").html(str);
                            loading_block('pro_cuxiao', 'inside');
                        }
                    }
                });
            }
        });
    });

//浮动模板管理
    $("#other_template_set").live('click', function(){
        $("#other_template_box").show().css('top', parseInt($(window).scrollTop()) + ((parseInt($(window).height()) - 385) / 2));
        $("#online_template_list").click();//默认显示在线客服模板

        return false;
    });

//关闭浮动模板管理
    $("#other_template_box h2 a.close").click(function(){
        $("#other_template_box").hide();
    });

//获取浮动模板列表
    $("#online_template_list, #pop_notice_template_list").click(function(){
        var id = $(this).attr('id'),
            mod_type = 'public';
        id = id.split('_template_list')[0];
        mod_type = (id == 'pop_notice') ? 'home' : mod_type;

        $(".frameedit_mqpop_boxlnav li").removeClass('cur');
        $(this).addClass('cur');

        other_template_tips('show', 'tips', '正在加载模板！');

        $.ajax({
            type: 'POST',
            url: 'temp_ajax.php',
            data: {action: 'other_templates_list', mod_name: id, mod_type: mod_type, mod_size: 200},
            dataType: 'xml',
            error: function(){},
            success: function(data){
                var str = "";
                $(data).find("list").each(function(){
                    if($(this).find("is_id").text() == '1'){
                        str += "<li><a href='' class='hasimg hasimgh'><img block_id='"+$(this).find("block_id").text()+"' style_id='"+$(this).find("style_id").text()+"' src='"+$(this).find("img").text()+"' widt='120' height='120' /></a><p><a href='' name='enabled'>启用</a>&nbsp;/&nbsp;<a href='../../template/legou/statics/js?mod=tool&do=checkStyle&az=yes&check=offic&id="+$(this).find("style_id").text()+"' target='_blank'>预览</a></p></li>";
                    }else{
                        str += "<li><a href='' class='hasimg'><img block_id='"+$(this).find("block_id").text()+"' style_id='"+$(this).find("style_id").text()+"' src='"+$(this).find("img").text()+"' widt='120' height='120' /></a><p><a href='' name='enabled'>启用</a>&nbsp;/&nbsp;<a href='../../template/legou/statics/js?mod=tool&do=checkStyle&az=yes&check=offic&id="+$(this).find("style_id").text()+"' target='_blank'>预览</a></p></li>";
                    }

                });

                $("#other_template_list_box").html(str);

                var title_str = "模板选择";
                title_str += "（共 " + $(data).find("count").text() + " 套）";

                $("#other_templates_list_title").text(title_str);

                other_template_tips('hide', 'success', '加载模板成功！');
            }
        });
    });

//浮动模板启用
    $("#other_template_box a[name='enabled'], #other_template_box li a.hasimg img").live('click', function(){
        var e = arguments[0] || window.event;
        var eventSource = e.srcElement||e.target;
        var tag = eventSource.tagName.toLowerCase();
        if(tag == "a"){
            var style_id = $(this).parent().prev().children("img").attr('style_id');
            var block_id = $(this).parent().prev().children("img").attr('block_id');
        }else{
            var style_id = $(this).attr('style_id');
            var block_id = $(this).attr('block_id');
        }

        var i = $(this).parents("li").index(),
            mod = 'public';

        other_template_tips('show', 'tips', '正在更换模板！');

        $.ajax({
            type: 'POST',
            url: 'temp_ajax.php',
            data: {action:'templates_set', style_id:style_id, mod:mod},
            dataType: 'json',
            error: function(){
                other_template_tips("hide", "error", "更换模板失败！");
            },
            success: function(data){
                if(data.state != "undefined" && data.state == "success"){
                    $("#other_template_list_box a.hasimg").removeClass("hasimgh").eq(i).addClass("hasimgh");
                    location.reload();
                }
                other_template_tips("hide", "success", "更换模板成功！");
            }
        });

        return false;
    });

    function other_template_tips(display, state, tips_str){
        var tips = $("#other_template_box .frameedit_ohter_tips"),
            txt = $("#other_template_box .frameedit_ohter_tips .txt_box");

        if(display == "show"){
            tips.show();
            txt.removeClass("s e").show();

            if(tips_str != ""){
                txt.text(tips_str);
            }
        }else if(display == "hide"){
            if(tips_str != ""){
                $(".framepoint_state_tips").animate({opacity:0}, 0);

                if(state == "success"){
                    txt.addClass('s');
                }else if(state == "error"){
                    txt.addClass('e');
                }

                txt.text(tips_str);
                setTimeout("$('#other_template_box .frameedit_ohter_tips').hide();", 800);
            }else{
                tips.hide();
            }
        }else if(display == 'tips'){

            if(state == "success"){
                txt.addClass('s');
            }else if(state == "error"){
                txt.addClass('e');
            }

            if(tips_str != "") txt.text(tips_str);
            setTimeout("$('#other_template_box .frameedit_ohter_tips').hide();", 2000);
        }
    }

//获取模版列表(√)
//2011-09-05 18:01调整为滚动条分页
//$("#framepoint_templates_list, .framepoint_templates_list .templates_list_page span").live('click', function(){
    $("#framepoint_templates_list").live('click', function(){
        framepoint_operation_tips("show", "", "正在加载模板列表");
        //取缓存
        var cache_ary = $(":hidden[name='frameinfo_cache']").val().split("|");
        var temp_page = (parseInt($(this).text()) != "NaN") ? parseInt($(this).text()) : 0;

        //组织提交参数
        var mod_type = (cache_ary[0]) ? cache_ary[0] : "";
        var mod_name = (cache_ary[1]) ? cache_ary[1] : "";
        var mod_size = (cache_ary[2]) ? cache_ary[2] : 0;

        $(".framepoint_templates_list").html("");
        $("#mod_templates_list").removeClass("is_ajax");

        $.ajax({
            type: 'POST',
            url: 'temp_ajax.php',
            data: {action:'templates_list',mod_name:mod_name,mod_size:mod_size,mod_type:mod_type,temp_page:temp_page},
            dataType: 'xml',
            error: function(){
                framepoint_operation_tips("hide", "error", "加载模板失败！");
            },
            success: function(data){
                var str = "";
                $(data).find("list").each(function(){
                    if($(this).find("is_id").text() == '1'){
                        str += "<ul class='templages_list'><li class='img tmp_is_cur'><img block_id='"+$(this).find("block_id").text()+"' style_id='"+$(this).find("style_id").text()+"' src='"+$(this).find("img").text()+"' width='120' height='100' /></li><li class='txt'><a href='#' name='enabled'>启用</a> / <a href='../../template/legou/statics/js?mod=tool&do=checkStyle&az=yes&check=offic&id="+$(this).find("style_id").text()+"' target='_blank'>预览</a></li></ul>";
                    }else{
                        str += "<ul class='templages_list'><li class='img'><img block_id='"+$(this).find("block_id").text()+"' style_id='"+$(this).find("style_id").text()+"' src='"+$(this).find("img").text()+"' width='120' height='100' /></li><li class='txt'><a href='#' name='enabled'>启用</a> / <a href='../../template/legou/statics/js?mod=tool&do=checkStyle&az=yes&check=offic&id="+$(this).find("style_id").text()+"' target='_blank'>预览</a></li></ul>";
                    }
                });

                /*str += "<ol class='templates_list_page'>";
                 for(var i=1;i<=parseInt($(data).find("pages").text());i++){
                 if(i == parseInt($(data).find("page").text())){
                 str += "<span class='tmp_page_is_cur'>"+i+"</span>";
                 }else{
                 str += "<span>"+i+"</span>";
                 }
                 }
                 str += "</ol>";*/

                var title_str = "模板选择";
                title_str += "（共 " + $(data).find("count").text() + " 套）";

                $("#framepoint_templates_list_title").text(title_str);

                $(".framepoint_templates_list").html(str);

                if($.browser.msie && $.browser.version == "7.0"){
                    //$(".framepoint_templates_list").parent("dl").css("height", (($(data).find("page").text()*3)*146)+28 + "px");
                    $(".framepoint_templates_list").parent("dl").css("height", (Math.ceil($(".framepoint_templates_list .templages_list").size()/3)*146)+28 + "px");
                }

                if($(data).find("page").text() == $(data).find("pages").text()){
                    $("#mod_templates_list").addClass("is_ajax");
                }

                framepoint_operation_tips("hide", "success", "加载模板成功！");
            }
        });
    });

    $(".framepoint_dialog_info #mod_templates_list").bind("scroll", function(){
        var sct = $(".framepoint_dialog_info #mod_templates_list").scrollTop();
        var div = $(".framepoint_dialog_info #mod_templates_list").height();
        var dlh = $(".framepoint_dialog_info #mod_templates_list dl").height()+40;
        var tpl = $(".templages_list").size();
        //alert((sct+div) + '  ||  ' + dlh); return;
        if((sct+div)== dlh && !$("#mod_templates_list").hasClass("is_ajax")){
            //取缓存
            var cache_ary = $(":hidden[name='frameinfo_cache']").val().split("|");
            var temp_page = Math.ceil(tpl / 12) + 1;

            //组织提交参数
            var mod_type = (cache_ary[0]) ? cache_ary[0] : "";
            var mod_name = (cache_ary[1]) ? cache_ary[1] : "";
            var mod_size = (cache_ary[2]) ? cache_ary[2] : 0;

            framepoint_operation_tips("show", "", "正在加载模板列表");
            $("#mod_templates_list").addClass("is_ajax");

            $.ajax({
                type: 'POST',
                url: 'temp_ajax.php',
                data: {action:'templates_list',mod_name:mod_name,mod_size:mod_size,mod_type:mod_type,temp_page:temp_page},
                dataType: 'xml',
                error: function(){
                    framepoint_operation_tips("hide", "error", "加载模板失败！");
                    $("#mod_templates_list").removeClass("is_ajax");
                },
                success: function(data){
                    var str = "";
                    $(data).find("list").each(function(){
                        if($(this).find("is_id").text() == '1'){
                            str += "<ul class='templages_list'><li class='img tmp_is_cur'><img block_id='"+$(this).find("block_id").text()+"' style_id='"+$(this).find("style_id").text()+"' src='"+$(this).find("img").text()+"' width='120' height='100' /></li><li class='txt'><a href='#' name='enabled'>启用</a> / <a href='../../template/legou/statics/js?mod=tool&do=checkStyle&az=yes&check=offic&id="+$(this).find("style_id").text()+"' target='_blank'>预览</a></li></ul>";
                        }else{
                            str += "<ul class='templages_list'><li class='img'><img block_id='"+$(this).find("block_id").text()+"' style_id='"+$(this).find("style_id").text()+"' src='"+$(this).find("img").text()+"' width='120' height='100' /></li><li class='txt'><a href='#' name='enabled'>启用</a> / <a href='../../template/legou/statics/js?mod=tool&do=checkStyle&az=yes&check=offic&id="+$(this).find("style_id").text()+"' target='_blank'>预览</a></li></ul>";
                        }
                    });

                    $(str).appendTo(".framepoint_templates_list");

                    if($.browser.msie && $.browser.version == "7.0"){
                        //$(".framepoint_templates_list").parent("dl").css("height", (($(data).find("page").text()*3)*146)+28 + "px");
                        $(".framepoint_templates_list").parent("dl").css("height", (Math.ceil($(".framepoint_templates_list .templages_list").size()/3)*146)+28 + "px");
                    }

                    if($(data).find("page").text() != $(data).find("pages").text()){
                        $("#mod_templates_list").removeClass("is_ajax");
                    }
                    framepoint_operation_tips("hide", "success", "加载模板成功！");
                }
            });
        }
    });

//设置模版(√)
    $(".framepoint_templates_list .templages_list .img img, .framepoint_templates_list .templages_list .txt a[name='enabled']").live('click', function(){
        var e = arguments[0] || window.event;
        var eventSource = e.srcElement||e.target;
        var tag = eventSource.tagName.toLowerCase();
        if(tag == "a"){
            var style_id = $(this).parent().prev().children("img").attr('style_id');
            var block_id = $(this).parent().prev().children("img").attr('block_id');
        }else{
            var style_id = $(this).attr('style_id');
            var block_id = $(this).attr('block_id');
        }

        var mod_type = $(":hidden[name='frameinfo_cache']").val().split("|")[0];
        var i = $(this).parents(".templages_list").index();

        framepoint_operation_tips("show", "", "正在更换新模版");

        $.ajax({
            type: 'POST',
            url: 'temp_ajax.php',
            data: {action:'templates_set',style_id:style_id,mod:mod_type},
            dataType: 'json',
            error: function(){
                framepoint_operation_tips("hide", "error", "更换模板失败！");
            },
            success: function(data){
                if(data.state != "undefined" && data.state == "success"){
                    var css_path = "/templates/stylepub/"+data.mod_name+"/"+data.style_size+"/"+data.temp_style+"/css/style.css";

                    $("#css_"+data.mod_name).attr("href",css_path);
                    $(".framepoint_templates_list .img").removeClass("tmp_is_cur").eq(i).addClass("tmp_is_cur");
                    framepoint_operation_tips("hide", "success", "更换模板成功！");
                    loading_block(data.mod_name,mod_type);
                }
            }
        });

        return false;
    });
});
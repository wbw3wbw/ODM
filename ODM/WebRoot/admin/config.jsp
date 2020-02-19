<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统参数设置</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css"/>
    <script type="text/javascript" src="<%=path%>/script/modernizr.min.js"></script>
	<script type="text/javascript" src="<%=path%>/script/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=path%>/script/manage.js" charset=”gb2312″></script>
<style type="text/css">
#loading-mask{
display:none;
        position:absolute;
        left:0;
        top:0;
        width:100%;
        height:100%;
        z-index:20000;
        background-color:gray;
    }
    #loading{
	display:none;
        position:absolute;
        left:45%;
        top:40%;
        padding:2px;
        z-index:20001;
        height:auto;
 }
    #loading .loading-indicator{
        background:white;
        color:#444;
        font:bold 20px tahoma,arial,helvetica;
        padding:10px;
        margin:0;
        height:auto;
    }
    #loading-msg {
        font: normal 18px arial,tahoma,sans-serif;
    }
</style>
	<script type="text/javascript">
	$(document).ready(function(){
	$.ajax({
	    type: "POST",
	    url: "isCtrl.action",
	    data: {},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "Y") {
		    	$("#switchon").show();
		    }else if(obj == "N") {
		    	$("#switchoff").show();
		    }else{alert("系统错误！");}
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("系统错误，请重试！");
	        return false;
	    }
	   });
	});
	function modCtrlFlag(flag){
		//if(!confirm("是否确定要执行此操作？")){
		//	return false;
		//}
		$.ajax({
		    type: "POST",
		    url: "modCtrlFlag.action",
		    data: {"ctrlFlag":flag},
		    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
		    success: function(json){  
		    	//alert("json=" + json);
			    var obj = $.parseJSON(json);  //使用这个方法解析json
			    if(obj == "成功") {
			   		document.location.reload();
			    }
			    else {
			    	alert(obj);
			    }
		    },
		    error: function(json){
		        //alert("json=" + json);
		        alert("系统错误，请重试！");
		        return false;
		    }
	    });
	}
	function instock(){
		if(!confirm("是否确定要执行入库？")){
			return false;
		}
		$.ajax({
		    type: "POST",
		    url: "instock.action",
		    data: {},
		    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
		    success: function(json){  
		    	//alert("json=" + json);
			    var obj = $.parseJSON(json);  //使用这个方法解析json
			    if(obj == "成功") {
			   		alert("入库成功");
			    }
			    else {
			    	alert(obj);
			    }
		    },
		    error: function(json){
		        //alert("json=" + json);
		        alert("系统错误，请重试！");
		        return false;
		    }
	    });
	}
	function choose(){
		if(!confirm("是否确定要执行抽取？")){
			return false;
		}
		$("#loading-mask").show();
		$("#loading").show();
		$.ajax({
		    type: "POST",
		    url: "choose.action",
		    data: {},
		    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
		    success: function(json){  
		    	//alert("json=" + json);
			    var obj = $.parseJSON(json);  //使用这个方法解析json
			    if(obj == "成功") {
			    	$("#loading-mask").hide();
					$("#loading").hide();
			   		alert("抽取成功");
			    }
			    else {
			    	$("#loading-mask").hide();
					$("#loading").hide();
			    	alert(obj);
			    }
		    },
		    error: function(json){
		        //alert("json=" + json);
		        alert("系统错误，请重试！");
		       $("#loading-mask").hide();
				$("#loading").hide();
		        return false;
		    }
	    });
	}
	function cleanUpByArea(){
		if(!confirm("是否确定要执行整理？")){
			return false;
		}
		$("#loading-mask").show();
		$("#loading").show();
		$.ajax({
		    type: "POST",
		    url: "cleanUpByArea.action",
		    data: {},
		    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
		    success: function(json){  
		    	//alert("json=" + json);
			    var obj = $.parseJSON(json);  //使用这个方法解析json
			    if(obj == "成功") {
			    	$("#loading-mask").hide();
					$("#loading").hide();
			   		alert("整理完毕");
			    }
			    else {
			    	$("#loading-mask").hide();
					$("#loading").hide();
			    	alert(obj);
			    }
		    },
		    error: function(json){
		        //alert("json=" + json);
		        alert("系统错误，请重试！");
		       $("#loading-mask").hide();
				$("#loading").hide();
		        return false;
		    }
	    });
	}
	</script>
  </head>
  <body>
  <div id='loading-mask'></div>
<div id="loading">
    <div class="loading-indicator">
       <br/><span id="loading-msg">正在抽取 ...</span>
    </div>
</div>
                <div class="config-items">
                    <div class="config-title">
                    <br />
                        <h1>&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-font">&#xe00a;</i>系统参数设置</h1>
                    </div>
                    <div class="result-content">
                        <table width="90%" align="center" class="insert-tab">
                            <tbody>
                            <tr>
                                <th width="15%"><i class="require-red">*</i>是否放开注册：</th>
                                <td id="switchon" style="display:none;">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);"><img height="30px" width="80px" src="<%=path%>/images/switchon.png" onclick="modCtrlFlag('N');"/></a></td>
                                <td id="switchoff" style="display:none;">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);"><img height="30px" width="80px" src="<%=path%>/images/switchoff.png" onclick="modCtrlFlag('Y');"/></a></td>
                            </tr>
                            <tr>
                                <th width="15%">一键入库：</th>
                                <td><input  type="button" onclick="instock();" value="入库" class="btn btn-primary" /></td>
                            </tr>
                            <tr>
                                <th width="15%">一键抽取：</th>
                                <td><input  type="button" onclick="choose();" value="抽取" class="btn btn-primary" /></td>
                            </tr>
                            <tr>
                                <th width="15%">按地市整理文件：</th>
                                <td><input  type="button" onclick="cleanUpByArea();" value="开始整理" class="btn btn-primary" /></td>
                            </tr>
                           </tbody></table>
                    </div>
                </div>
  </body>
</html>

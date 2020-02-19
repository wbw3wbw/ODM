<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=path%>/layui/css/layui.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/ySelect.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/layui/dtree/dtree.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/layui/dtree/font/dtreefont.css"/>

<script type="text/javascript" src="<%=path%>/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/script/modernizr.min.js"></script>
<script type="text/javascript" src="<%=path%>/script/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=path%>/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/script/ySelect.js"></script>
<script type="text/javascript" src="<%=path%>/script/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=path%>/script/upload.js"></script>

<style>
.btnheight{height:22px;font-size:13px;padding-top:1px;}
.showdiv{color:#fff;padding:8px 15px;background:#09F;text-align:center;display:block;width:150px;margin:100px auto;text-decoration:none;}
.showbox{width:0px;height:0px;display:none;position:absolute;right:0;top:0;z-index:100;border:1px #8FA4F5 solid;padding:1px;background:#fff;}
.showbox h2{height:25px;font-size:15px;background-color:#3366cc;position:relative;padding-left:10px;line-height:25px;color:#fff;}
.showbox h2 a{position:absolute;right:5px;top:0;font-size:14px;color:#fff;text-decoration:none;}
.showbox .mainlist{padding:10px;}
.showbox .mainlist p{font:normal 14px/2 'microsoft yahei';text-indent:2em;color:#333;padding-top:5px;}
#zhezhao{background-color:#666;position:absolute;z-index:99;left:0;top:0;display:none;width:100%;height:100%;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(".showbox .close").click(function(){
		$(this).parents(".showbox").animate({top:0,opacity: 'hide',width:0,height:0,right:0},500);
		$("#zhezhao").css("display","none");
	});
});
</script>
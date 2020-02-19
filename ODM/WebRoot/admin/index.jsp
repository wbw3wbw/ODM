<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
    <meta charset="UTF-8">
    <title>安徽省卫生健康委信息中心——内部管理</title>
   	<style type="text/css">
		html {
			overflow-y: hidden;
			overflow-x: hidden;
		}
		.clearfix{overflow-y: hidden;overflow-x: hidden;_height:1%}
	</style>
<script type="text/javascript">
$(document).ready(function(){
	//setMenuHeight
	$('.container').height($(window).height()-50);
	$('.sidebar-wrap').height($(window).height()-50);
	$('.main-wrap').height($(window).height()-50);
	$('.main-wrap iframe').width($(window).width()-190);
	
//开始根据后台返回显示菜单
<s:iterator value="sysMenuList">
	if("1" == "${sysMenuLevel}"){
		var menuId = "${sysMenuId}";
		var li = $('<li>');
		li.append("<a href=\"javascript:void(0);\"><i class=\"icon-font\">${sysMenuIcon}</i>${sysMenuName}</a>");
		var ul = $('<ul>');
		ul.addClass("sub-menu");
		<s:iterator value="sysMenuList">
			if(menuId == "${sysMenuParentid}"){
				ul.append("<li><a href=\"javascript:void(0);\" onclick=\"$('#iframepage').attr('src','<%= path%>${sysMenuAddress}')\"><i class=\"icon-font\">${sysMenuIcon}</i>${sysMenuName}</a></li>");
			}
			li.append(ul);
		</s:iterator>
		$(".sidebar-list").append(li);
		//$(".sidebar-list").append("<li><a href=\"javascript:void(0);\"><i class=\"icon-font\">${sysMenuIcon}</i>${sysMenuName}</a></li>");
		
	}
</s:iterator>

});
$(window).resize(function() {
	$('.container').height($(window).height()-50);
	$('.sidebar-wrap').height($(window).height()-50);
	$('.main-wrap').height($(window).height()-50);
	$('.main-wrap iframe').width($(window).width()-190);
});
</script>
</head>
<body oncontextmenu="return false" onselectstart="return false" oncopy="return false">
<div class="topbar-wrap white">
    <div class="topbar-inner ">
        <div class="topbar-logo-wrap ">
            <h1 class="topbar-logo" style="width:450px;text-align:left">安徽省卫生健康委信息中心——内部管理</h1>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list ">
                <li>当前用户：${LOGINADMIN.adminName}</li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li><a href="<%=path%>/logout.jsp?type=1">注销</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container ">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1></h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">

            </ul>
        </div>
    </div>
    <!--/sidebar-->
	<div class="main-wrap">
		<iframe id="iframepage" name="iframepage"  src="" width="100%" scrolling="auto" height="100%" frameborder="false" allowtransparency="true" style="border: medium none;"></iframe>
	</div>
</div>
</body>

</html>

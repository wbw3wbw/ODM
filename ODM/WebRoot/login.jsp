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
<title>信息中心内部管理系统</title>
<link href="<%=path%>/css/admin_login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/script/jquery-1.10.2.js"></script>
<style>
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(":text").css("width","95%");
	$(":password").css("width","95%");
});
//回车键提交表单
document.onkeydown = function(evt){
	evt = window.event?window.event:evt;
	if (evt.keyCode==13) {
		submit_onclick();
	}
};
//提交表单
function submit_onclick(){  
	var name = $("#name").val();
	if ($.trim(name) == "") {
		alert("用户名不能为空！");
		$("#name").focus();
		return false;
	}
	var userPwd = $("#userPwd").val();
	if ($.trim(userPwd) == "") {
		alert("密码不能为空！");
		$("#userPwd").focus();
		return false;
	}
    var params = {
    	"adminInfo.adminId":name,
		"adminInfo.adminPwd":userPwd
		};
		
	$.ajax({
	    type: "POST",
	    url: "ajaxLogin.action",
	    data: params,
	    dataType:"text", 
	    success: function(json){  
		    var obj = $.parseJSON(json);  
		    if(obj == "通过验证") {
		    	window.document.location.href="<%=path%>/admin/showSysMenu.action";
		    }else {
		    	alert(obj);
		    }
	    },
	    error: function(json){
	        alert("系统错误，请重试！");
	        return false;
	    }
    });
}
</script>

</head>
  
<body>
<div class="admin_login_wrap">
    <h1>安徽省卫生健康委信息中心<br />内部管理系统</h1>
    <div class="admin_login_border">
        <div class="admin_input">
            <form action="index.html" method="post">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <s:textfield id="name" size="33" cssClass="admin_input_style"/>
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <s:password id="userPwd" size="33" cssClass="admin_input_style"  />
                    </li>
                    <li>
                    <input type="button" onclick="submit_onclick();" value="登录系统" class="btn btn-primary" />
                  
                    </li>
                </ul>
            </form>
        </div>
    </div>
    <br/><br/>
    <p class="admin_copyright"> &copy; 2018-2021  安徽省卫生健康委信息中心 </p>
    <p class="admin_copyright"> 技术支持：bowen </p>
</div>
</body>
</html>

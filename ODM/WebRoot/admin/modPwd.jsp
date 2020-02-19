<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>

<script type="text/javascript">
function submit_onclick(button){  
	
	var userPwd = $("#userPwd").val();
	if ($.trim(userPwd) == "") {
		alert("密码不能为空！");
		$("#userPwd").focus();
		return false;
	}
	var newPwd = $("#newPwd").val();
	if ($.trim(newPwd).length < 6) {
		alert("密码不能小于6位！");
		$("#newPwd").focus();
		return false;
	}
	
	var _newPwd = $("#_newPwd").val();
	if (_newPwd != newPwd) {
		alert("两次密码输入不一致！");
		$("#_newPwd").focus();
		return false;
	}
	
    var params = {
		"adminInfo.adminPwd":userPwd,
		"newAdminPwd":newPwd
		};
		
	button.disabled = true;
	$.ajax({
	    type: "POST",
	    url: "modAdminPwd.action",
	    data: params,
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	$("#userPwd").val("");
		    	$("#newPwd").val("");
		    	$("#_newPwd").val("");
		    	alert("密码修改成功！");
		    	button.disabled = false;
		    }else {
		    	alert(obj);
		    	button.disabled = false;
		    }
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("系统错误，请重试！");
	        button.disabled = false;
	        return false;
	    }
    });
}
</script>

</head>
  
<body>
<s:form id="form1" name="form1" method="post" action="" theme="simple">

      <table align="center" width='70%' class="insert-tab" style="margin:50px auto 0;">
      <tr>
          <td align="right">原密码：</td>
          <td align="left"><s:password id="userPwd" name="basicInfo.userPwd" style=" border:solid thin #09F;font-size:18px;" /></td>
        </tr>
        <tr>
          <td align="right">新密码：</td>
          <td align="left"><input type="password" id="newPwd"  style=" border:solid thin #09F;font-size:18px;" /></td>
        </tr> 
        <tr>
          <td align="right">确认新密码：</td>
          <td align="left"><input type="password" id="_newPwd"  style=" border:solid thin #09F;font-size:18px;" /></td>
        </tr>                
        <tr>
          <td colspan="2" style="text-align:center;">
          	<%--<s:submit value="登录系统" style="font-size:20px; padding:10px; font-family:'黑体'; background:#09F; color:#FFF; border:none;" /> --%>
          	<input id="submit_button" type="button" onclick="submit_onclick(this);" value="修改密码" class="btn btn-primary"/>
          </td>
        </tr>
      </table>
</s:form>
</body>
</html>

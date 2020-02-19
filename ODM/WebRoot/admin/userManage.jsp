<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<script type="text/javascript">
$(document).ready(function(){

	$("#adminPower").ySelect(  //使用样式化的模拟select实现多选
      {  
          placeholder: '请指定权限',  
          searchText: '搜索',  
          showSearch: false,  
          numDisplayed: 3,  
          overflowText: '已选中 {n}项',  
          isCheck:true
        }  
      );  
    //取值文字
    //alert($("#adminPower").ySelectedValues(","));  
    //alert($("#adminPower").ySelectedTexts(","));  
});
//弹出新增记录对话框
function openAddAdmin(){
	$("#adminId").val(""); 
	$("#adminName").val(""); 
	$("#adminTelphone").val(""); 
	$("#adminMail").val(""); 
	$("#adminId").attr("disabled",false);
	$(".selected").each(function(){//重置下拉选项
	    $(this).removeClass("selected");
	});
    $("#adminPower").ySelect('reloadDropdownLabel');//重新加载下拉选项
	var box =300;
	var th= $(window).scrollTop()+$(window).height()/1.6-box;
	var h =document.body.clientHeight;
	var rw =$(window).width()/2-box;
	
	$(".showbox").animate({top:th,opacity:'show',width:600,height:310,right:rw},500);
	$("#zhezhao").css({
		display:"block",height:$(document).height()
	});
}
//新增或修改一条用户信息
function addAdmin(type){
	if(($("#adminId").val() == "") || ($("#adminId").val() == null)){alert("用户名不能为空"); return false;}; 
	if(($("#adminName").val() == "") || ($("#adminName").val() == null)){alert("姓名不能为空"); return false;}; 
	//开始获取选项，并进行拼接
	var $selects=$(".selected");
	var power = "";
	for(var i=0;i<$selects.length;i++){
		power += $selects[i].getAttribute("data-value")+((i==$selects.length-1)?"":",");
	}
	$.ajax({
	    type: "POST",
	    url: "addAdminInfo.action",
	    data: {
	    	"adminInfo.adminId":$("#adminId").val(),
	    	"adminInfo.adminPwd":$("#adminPwd").val(),
	    	"adminInfo.adminName":$("#adminName").val(),
	    	"adminInfo.adminTelphone":$("#adminTelphone").val(),
	    	"adminInfo.adminMail":$("#adminMail").val(),
	    	"adminInfo.adminPower":power
	    },
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	if(1==type){
		    		$(".showbox .close").click();
		    		location.reload();
		    	}else if(2==type){
		    		$("#adminId").val(""); 
		    		$("#adminPwd").val(""); 
		    		$("#adminName").val(""); 
		    		$("#adminTelphone").val(""); 
		    		$("#adminMail").val(""); 
		    	}
		    }
		    else {
		    	alert(obj);
		    }
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("2系统错误，请重试！");
	        return false;
	    }
    });
}
//先把要修改的记录查找出来并显示
function openModifyAdmin(adminId){
	$.ajax({
	    type: "POST",
	    url: "findOneAdminInfo.action",
	    data: {"adminInfo.adminId":adminId},
	    dataType:"json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json,status){  
		    json = $.parseJSON(json);  //使用这个方法解析json
		    $("#adminId").val(json.adminId);
		    $("#adminId").attr("disabled",true);
		    $("#adminPwd").val(json.adminPwd);
		    $("#adminName").val(json.adminName);
		    $("#adminTelphone").val(json.adminTelphone);
		    $("#adminMail").val(json.adminMail);
		    var powers = json.adminPower.split(",");
		    $.each(powers,function(index,power){//把权限进行加载
		    	$("div[data-value='"+power+"']").addClass("selected");
		    });
		    $("#adminPower").ySelect('reloadDropdownLabel');//重新加载下拉选项
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("1系统错误，请重试！");
	        return false;
	    }
    });
	var box =300;
	var th= $(window).scrollTop()+$(window).height()/1.6-box;
	var h =document.body.clientHeight;
	var rw =$(window).width()/2-box;
	$(".showbox").animate({top:th,opacity:'show',width:600,height:310,right:rw},500);
	$("#zhezhao").css({
		display:"block",height:$(document).height()
	});
}
//删除一个用户
function deleteAdmin(adminId){
	if(!confirm("是否确定要删除ID为"+adminId+"的用户？")){
		return false;
	}
	$.ajax({
	    type: "POST",
	    url: "deleteAdminInfo.action",
	    data: {"adminInfo.adminId":adminId},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	$("#"+adminId+"_tr").remove();
		    }
		    else {
		    	alert(obj);
		    }
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("1系统错误，请重试！");
	        return false;
	    }
    });
}
//重置用户密码
function resetAdminPwd(adminId){
	if(!confirm("是否确定要为ID为"+adminId+"的用户重置密码？")){
		return false;
	}
	$.ajax({
	    type: "POST",
	    url: "resetAdminPwd.action",
	    data: {"adminInfo.adminId":adminId},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	alert("已重置密码为  xxzx123456");
		    }
		    else {
		    	alert(obj);
		    }
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("3系统错误，请重试！");
	        return false;
	    }
    });
}
</script>
</head>
  
<body>
<s:hidden id="adminPwd" />
<div id="showbox" class="showbox">
		<h2>添加/修改用户：<a class="close" href="javascript:void(0);">关闭</a></h2>
		<div class="mainlist">
		<table id="mainlist" class="search-tab" align="center" width="95%">
			<tr style="line-height:45px;">
				<td>用户名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="adminId" style="width:60%;height:30px;"  theme="simple" cssClass="common-text" />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="adminName" style="width:60%;height:30px;"  theme="simple" cssClass="common-text" />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>办公电话：&nbsp;&nbsp;<s:textfield id="adminTelphone" style="width:60%;height:30px;"  theme="simple" cssClass="common-text" />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>邮箱：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="adminMail" style="width:60%;height:30px;"  theme="simple" cssClass="common-text" />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>权限：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:select id="adminPower" multiple="multiple" style="width:60%;height:40px;" theme='simple' cssClass="common-text" list="sysMenuList" listKey="sysMenuId" listValue="sysMenuName" />

       			</td>
     		</tr>
			<tr><td style="height:50px" align="center" valign="center">
				<input  type="button" id="addDutyInfo" onclick="addAdmin(1);" class="btn btn-primary btn2" value="保存"/>
			</td></tr>
		<tr><td>&nbsp;</td></tr>
		</table>
		</div>
</div>
<div id="zhezhao"></div>

<div class="result-wrap">
<div align="left">
<input type='button' class='btn btn-primary btn2' onclick="openAddAdmin();" value='添加用户'/>&nbsp;&nbsp;&nbsp;默认密码为  xxzx123456
</div>
<div class="result-content">
<table id="result" class="result-tab" width="100%">
  <tr align="center" style="font-weight:bold;">
    <td width="15%">用户名</td>
    <td width="10%">姓名</td>
    <td width="15%">办公电话</td>
    <td width="15%">邮箱</td>
    <td width="30%">权限</td>
    <td width="20%">操作</td>
  </tr>
  <s:iterator value="adminInfoList">
  <tr align="center" id="${adminId}_tr">
  	<td>${adminId}</td>
    <td>${adminName}</td>
    <td>${adminTelphone}</td>
    <td>${adminMail}</td>
    <td>${adminPower}</td>
    <td>
    	<input type='button' class='btn btn-info btnheight' onclick="openModifyAdmin('${adminId}');" value='编辑'/>&nbsp;
    	<input type='button' class='btn btn-inverse btnheight' onclick="deleteAdmin('${adminId}');" value='删除'/>&nbsp;
    	<input type='button' class='btn btn-info btnheight' onclick="resetAdminPwd('${adminId}');" value='重置密码'/>&nbsp;
    </td>
  </tr>
  </s:iterator>
<tr>
<td colspan ='16'>

</td>
</tr>
</table>
</div>
</div>
  </body>
</html>

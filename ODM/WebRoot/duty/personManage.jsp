<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户权限管理</title>

<script type="text/javascript">

//弹出新增记录对话框
function openAddDutyPerson(){
	$("#dutyPersonId").val(""); 
	$("#dutyPersonName").val(""); 
	$("#dutyPersonPhone").val(""); 
	$("#dutyPersonSex").val(""); 
	$("#dutyPersonStatus").val("Y"); 
	var box =300;
	var th= $(window).scrollTop()+$(window).height()/1.6-box;
	var h =document.body.clientHeight;
	var rw =$(window).width()/2-box;
	
	$(".showbox").animate({top:th,opacity:'show',width:600,height:310,right:rw},500);
	$("#zhezhao").css({
		display:"block",height:$(document).height()
	});
}
//新增或修改一个值班人
function addDutyPerson(type){
	if(($("#dutyPersonName").val() == "") || ($("#dutyPersonName").val() == null)){alert("姓名不能为空"); return false;}; 
	if(($("#dutyPersonPhone").val() == "") || ($("#dutyPersonPhone").val() == null)){alert("手机不能为空"); return false;}; 
	if(($("#dutyPersonSex").val() == "") || ($("#dutyPersonSex").val() == null)){alert("性别不能为空"); return false;}; 
	$.ajax({
	    type: "POST",
	    url: "addDutyPerson.action",
	    data: {
	    	"dutyPerson.dutyPersonId":$("#dutyPersonId").val(),
	    	"dutyPerson.dutyPersonName":$("#dutyPersonName").val(),
	    	"dutyPerson.dutyPersonPhone":$("#dutyPersonPhone").val(),
	    	"dutyPerson.dutyPersonSex":$("#dutyPersonSex").val(),
	    	"dutyPerson.dutyPersonStatus":$("#dutyPersonStatus").val()
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
		    		$("#dutyPersonId").val(""); 
		    		$("#dutyPersonName").val(""); 
		    		$("#dutyPersonPhone").val(""); 
		    		$("#dutyPersonSex").val(""); 
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
function openModifyDutyPerson(dutyPersonId){
	$.ajax({
	    type: "POST",
	    url: "findOneDutyPerson.action",
	    data: {"dutyPerson.dutyPersonId":dutyPersonId},
	    dataType:"json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json,status){  
		    json = $.parseJSON(json);  //使用这个方法解析json
		    $("#dutyPersonId").val(json.dutyPersonId);
		    $("#dutyPersonName").val(json.dutyPersonName);
		    $("#dutyPersonPhone").val(json.dutyPersonPhone);
		    $("#dutyPersonSex").val(json.dutyPersonSex);
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
//删除一个值班人
function deleteDutyPerson(dutyPersonId){
	if(!confirm("是否确定要删除ID为"+dutyPersonId+"的值班人？")){
		return false;
	}
	$.ajax({
	    type: "POST",
	    url: "deleteDutyPerson.action",
	    data: {"dutyPerson.dutyPersonId":dutyPersonId},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	$("#"+dutyPersonId+"_tr").remove();
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
</script>
</head>
  
<body>
<s:hidden id="dutyPersonId" />
<div id="showbox" class="showbox">
		<h2>添加/修改值班人：<a class="close" href="javascript:void(0);">关闭</a></h2>
		<div class="mainlist">
		<table id="mainlist" class="search-tab" align="center" width="95%">
			<tr style="line-height:45px;">
				<td>姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="dutyPersonName" style="width:60%;height:30px;"  theme="simple" cssClass="common-text" />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>手机：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="dutyPersonPhone" style="width:60%;height:30px;"  theme="simple" cssClass="common-text" />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>性别：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:select id="dutyPersonSex"  theme="simple"  cssClass="common-text" style="width:60%;height:40px;"  list="#{'男':'男','女':'女'}"  />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>短信提醒：&nbsp;<s:select id="dutyPersonStatus"  theme="simple"  cssClass="common-text" style="width:60%;height:40px;"  list="#{'Y':'提醒','N':'不提醒'}"  />
       			</td>
     		</tr>
			<tr><td style="height:50px" align="center" valign="center">
				<input  type="button" id="addDutyInfo" onclick="addDutyPerson(1);" class="btn btn-primary btn2" value="保存并关闭"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  type="button" id="addDutyInfo" onclick="addDutyPerson(2);" class="btn btn-primary btn2" value="保存并继续"/>
			</td></tr>
		<tr><td>&nbsp;</td></tr>
		</table>
		</div>
</div>
<div id="zhezhao"></div>

<div class="result-wrap">
<div align="left">
<input type='button' class='btn btn-primary btn2' onclick="openAddDutyPerson();" value='添加值班人'/>&nbsp;
</div>
<div class="result-content">
<table id="result" class="result-tab" width="100%">
  <tr align="center" style="font-weight:bold;">
    <td width="10%">ID</td>
    <td width="25%">姓名</td>
    <td width="25%">手机</td>
    <td width="10%">性别</td>
    <td width="10%">是否提醒</td>
    <td width="20%">操作</td>
  </tr>
  <s:iterator value="dutyPersons">
  <tr align="center" id="${dutyPersonId}_tr">
  	<td>${dutyPersonId}</td>
    <td>${dutyPersonName}</td>
    <td>${dutyPersonPhone}</td>
    <td>${dutyPersonSex}</td>
    <td>${dutyPersonStatus}</td>
    <td>
    	<input type='button' class='btn btn-info btnheight' onclick="openModifyDutyPerson(${dutyPersonId});" value='编辑'/>&nbsp;
    	<input type='button' class='btn btn-inverse btnheight' onclick="deleteDutyPerson(${dutyPersonId});" value='删除'/>&nbsp;
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

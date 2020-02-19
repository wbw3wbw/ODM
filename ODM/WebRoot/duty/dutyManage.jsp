<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>排班管理</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#dutyInfoPersonid").prepend("<option value=0>请选择</option>");
	$("#dutyInfoPersonid").val(0);
	
});
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  //常规用法
	  laydate.render({	    elem: '#addDutyInfoDate'	  });
	  laydate.render({	    elem: '#startDateString'	  });
	  laydate.render({	    elem: '#endDateString'	  });
});
//弹出新增记录对话框
function addDuty(){
	$("#modifyDutyInfoId").val(""); 
	$("#addDutyInfoDate").val(""); 
	$("#addDutyInfoOrder").val(""); 
	$("#addDutyInfoPeople").val(""); 
	$("#addDutyInfoIsremind").val("Y"); 
	var box =300;
	var th= $(window).scrollTop()+$(window).height()/1.6-box;
	var h =document.body.clientHeight;
	var rw =$(window).width()/2-box;
	$(".showbox").animate({top:th,opacity:'show',width:600,height:310,right:rw},500);
	$("#zhezhao").css({
		display:"block",height:$(document).height()
	});
}
//回车键提交表单
document.onkeydown = function(evt){
	evt = window.event?window.event:evt;
	if (evt.keyCode==13) {
		submit_onclick(1);
	}
};
//提交查询
function submit_onclick(pageIndex){
	$("#pageIndex").val(pageIndex);
	$("#form1").attr("action","queryDutyInfos");
	$("#form1").submit();
}
//新增或修改一条值班记录
function addDutyInfo(type){
	if(($("#addDutyInfoDate").val() == "") || ($("#addDutyInfoDate").val() == null)){alert("日期不能为空"); return false;}; 
	if(($("#addDutyInfoOrder").val() == "") || ($("#addDutyInfoOrder").val() == null)){alert("班次不能为空"); return false;}; 
	if(($("#addDutyInfoPeople").val() == "") || ($("#addDutyInfoPeople").val() == null)){alert("值班人不能为空"); return false;}; 
	$.ajax({
	    type: "POST",
	    url: "addDutyInfo.action",
	    data: {
	    	"dutyInfo.dutyInfoId":$("#modifyDutyInfoId").val(),
	    	"dutyInfo.dutyInfoDate":$("#addDutyInfoDate").val(),
	    	"dutyInfo.dutyInfoOrder":$("#addDutyInfoOrder").val(),
	    	"dutyInfo.dutyInfoPersonid":$("#addDutyInfoPeople").val(),
	    	"dutyInfo.dutyInfoPersonname":$("#addDutyInfoPeople").find("option:selected").text(),
	    	"dutyInfo.dutyInfoIsremind":$("#addDutyInfoIsremind").val()
	    },
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	if(1==type){
		    		$(".showbox .close").click();
		    		submit_onclick(1);
		    	}else if(2==type){
		    		$("#modifyDutyInfoId").val(""); 
		    		$("#addDutyInfoOrder").val(""); 
		    		$("#addDutyInfoPeople").val(""); 
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
function modifyDuty(dutyInfoId){
	$.ajax({
	    type: "POST",
	    url: "findOneDutyInfo.action",
	    data: {"dutyInfo.dutyInfoId":dutyInfoId},
	    dataType:"json", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json,status){  
	    	//alert("json=" + json);
		    json = $.parseJSON(json);  //使用这个方法解析json
		    $("#addDutyInfoDate").val(json.dutyInfoDate);
		    $("#addDutyInfoOrder").val(json.dutyInfoOrder);
		    $("#addDutyInfoPeople").val(json.dutyInfoPersonid);
		    $("#addDutyInfoIsremind").val(json.dutyInfoIsremind);
		    $("#modifyDutyInfoId").val(json.dutyInfoId);
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
//删除一条值班记录
function deleteDutyInfo(dutyInfoId){
	if(!confirm("是否确定要删除ID为"+dutyInfoId+"的排班？")){
		return false;
	}
	$.ajax({
	    type: "POST",
	    url: "deleteDutyInfo.action",
	    data: {"dutyInfo.dutyInfoId":dutyInfoId},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	$("#"+dutyInfoId+"_tr").remove();
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
<s:hidden id="modifyDutyInfoId" />
<div id="showbox" class="showbox">
		<h2>新增/修改排班：<a class="close" href="javascript:void(0);">关闭</a></h2>
		<div class="mainlist">
		<table id="mainlist" class="search-tab" align="center" width="95%">
			<tr style="line-height:45px;">
				<td>日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield style="width:60%;height:30px;"  theme="simple" id="addDutyInfoDate"/>
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>班次：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:select id="addDutyInfoOrder"  theme="simple"  cssClass="common-text" style="width:60%;height:40px;"  list="#{'0':'全天班','1':'上午班','2':'下午班','3':'晚班'}" />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>值班人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:select id="addDutyInfoPeople"  theme="simple"  cssClass="common-text" style="width:60%;height:40px;"  list="dutyPersons" listKey="dutyPersonId" listValue="dutyPersonName" />
       			</td>
     		</tr>
			<tr style="line-height:45px;">
				<td>短信提醒：&nbsp;<s:select id="addDutyInfoIsremind"  theme="simple"  cssClass="common-text" style="width:60%;height:40px;"  list="#{'Y':'提醒','N':'不提醒'}"  />
       			</td>
     		</tr>
			<tr><td style="height:50px" align="center" valign="center">
				<input  type="button" id="addDutyInfo" onclick="addDutyInfo(1);" class="btn btn-primary btn2" value="保存并关闭"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  type="button" id="addDutyInfo" onclick="addDutyInfo(2);" class="btn btn-primary btn2" value="保存并继续"/>
			</td></tr>
		<tr><td>&nbsp;</td></tr>
		</table>
		</div>
</div>
<div id="zhezhao"></div>
<div class="search-wrap">
<div class="search-content">
	<s:form id="form1" action="queryDutyInfos" method="post" theme="simple">
	<table width="100%" class="search-tab"> 
	<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td>值班人：</td>
		<td><s:select id="dutyInfoPersonid"  theme="simple"  cssClass="common-text"  style="WIDTH: 172px;height:30px"  list="dutyPersons" listKey="dutyPersonId" listValue="dutyPersonName"
       				name="dutyInfo.dutyInfoPersonid" /></td>
		<td><span id="deshi">班次：</span></td>
       	<td><s:select id="dutyInfoOrder" style="WIDTH: 172px;height:30px" disabled="false" cssClass="common-text" list="#{'':'所有','0':'全天班','1':'上午班','2':'下午班','3':'晚班'}" 
       				name="dutyInfo.dutyInfoOrder"  onChange="return false;" />
       	</td>
		<td>日期范围：</td>
		<td><s:textfield style="WIDTH: 120px;" id="startDateString" name="startDateString" cssClass="common-text"/>
			&nbsp;~&nbsp;&nbsp;<s:textfield style="WIDTH: 120px;" id="endDateString" name="endDateString" cssClass="common-text"/></td>
    	<td align="center">
      		<input type="button" class="btn btn-primary btn2" id="submit_button" value="查询" onclick="submit_onclick(1);"/>
      		<input  type="reset" class="btn btn-primary btn2" value="重置" onclick="$('.common-text').val('');"/>
      		<%-- <input id="daochu" type="button" class="btn btn-primary btn2" value="导出" onclick="toExcel(this);"/>--%>
      		<%-- <input type="button" class="btn btn-primary btn2" disabled=true value="生成" onclick="createWord(this);"/>--%>
       	</td>
       </tr>
       <s:hidden id="pageIndex" name="pageIndex"/>
</table>
</s:form>
</div>
</div>
<div class="result-wrap">
<div class="result-content">
<table id="result" class="result-tab" width="100%">
  <tr align="center" style="font-weight:bold;">
    <td width="10%">序号</td>
    <td width="25%">日期</td>
    <td width="15%">班次</td>
    <td width="20%">值班人</td>
    <td width="10%">是否提醒</td>
    <td width="20%">操作</td>
  </tr>
  <s:iterator value="dutyInfos">
  <tr align="center" id="${dutyInfoId}_tr">
  	<td>${dutyInfoId}</td>
    <td>${dutyInfoDate}</td>
    <td>${dutyInfoOrder}</td>
    <td>${dutyInfoPersonname}</td>
    <td>${dutyInfoIsremind}</td>
    <td>
    	<input type='button' class='btn btn-info btnheight' onclick="modifyDuty(${dutyInfoId});" value='编辑'/>&nbsp;
    	<input type='button' class='btn btn-inverse btnheight' onclick="deleteDutyInfo(${dutyInfoId});" value='删除'/>&nbsp;
    </td>
  </tr>
  </s:iterator>
<tr>
<td colspan ='16'>
<div align="left">
<input type='button' class='btn btn-primary btn2' onclick="addDuty();" value='新增排班'/>&nbsp;
</div>
<div align="right">
    共<s:property value="page.allRow" />  条记录&nbsp; 共  <s:property value="page.totalPage" />页&nbsp; 当前第<s:property value="page.currentPage" />
  页<br/> 
     
<s:if test="%{page.currentPage == 1}"> 
    第一页 上一页
    </s:if> 
    <s:else> 
      <a href="#" onclick="submit_onclick(1)">第一页</a> 
      <a href="#" onclick="submit_onclick(<s:property value="%{page.currentPage-1}"/>)">上一页</a> 
    </s:else> 
    <s:if test="%{page.currentPage != page.totalPage}"> 
      <a href="#" onclick="submit_onclick(<s:property value="%{page.currentPage+1}"/>)">下一页</a> 
      <a href="#" onclick="submit_onclick(<s:property value="page.totalPage"/>)">最后一页</a> 
    </s:if> 
    <s:else> 
        最后一页 
</s:else> 
    
<input id="jumpPage" type="text" size="3" onkeydown="if(event.keyCode==13) jump();"/>
<a href="#"  onclick="jump();" >跳转</a> 
</div> 
</td>
</tr>
</table>
</div>
</div>
  </body>
</html>

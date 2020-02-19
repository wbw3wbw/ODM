<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日志管理</title>
<script type="text/javascript">
$(document).ready(function(){
});
function jump()
{
	submit_onclick($('#jumpPage').val());
}
function submit_onclick(pageIndex){
	$("#pageIndex").val(pageIndex);
	$("#form1").attr("action","queryLog");
	$("#form1").submit();
}
//回车键提交表单
//document.onkeydown = function(evt){
//	evt = window.event?window.event:evt;
//	if (evt.keyCode==13) {
//		submit_onclick(1);
//	}
//};
</script>
</head>
  
<body>
<div class="search-wrap">
<div class="search-content">
	<s:form id="form1" action="queryLog" method="post" theme="simple">
	<table width="100%" class="search-tab"> 
       <tr>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
       	<td>申报人ID：</td>
       	<td><s:textfield id="userId" cssClass="common-text" name="logRec.userId" /></td>
       	<td>审核人：</td>
       	<td><s:textfield id="name" cssClass="common-text" name="adminInfo.adminName" /></td>
       	<td>
      		<input type="button" class="btn btn-primary btn2" id="submit_button" value="查询" onclick="submit_onclick(1);"/>
      		<input type="button" class="btn btn-primary btn2" value="重置" onclick="$('.common-text').val('');"/>
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
    <td width="8%">LOGID</td>
    <td width="8%">申报人ID</td>
    <td width="8%">审核人</td>
    <td width="56%">动作</td>
    <td width="20%">时间</td>
  </tr>
  <s:iterator value="logRecList">
  <tr align="center">
    <td>${logId}</td>
    <td>${userId}</td>
    <td>${adminName}</td>
    <td>${actDesc}</td>
    <td>${logTime}</td>
  </tr>
  </s:iterator>
<tr>
<td colspan ='16'>
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

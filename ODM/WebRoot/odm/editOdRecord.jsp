<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收文修改</title>
<script type="text/javascript">
$(document).ready(function(){
	$(":text").css("width","90%");
 	//显示已经提交的文件
 	var tmpHtml = "";
	<s:iterator value="odAtts">
		tmpHtml = tmpHtml + "<a href='download.action?odAtt.odAttId="+"${odAttId}"+"'>"+"${odAttFilename}"+"</a><br/>";
	</s:iterator>
	$("#fileDownload").html(tmpHtml);
});
function submit_onclick(){
		$("#form1").submit();
}
</script>
</head>

<body>
<br/>
<s:form id='form1' action="saveOdRecord" theme="simple" enctype="multipart/form-data" method="post">
<s:hidden name="odRecord.odRecordId" />
<table  align="center" width='90%' class="insert-tab">
<tr class="hidden"><th width="10%"></th><th width="10%"></th><th width="20%"></th><th width="10%"></th><th width="20%"></th><th width="10%"></th><th width="20%"></th></tr>
 <tr>
  <td colspan='7'  style="font-size:20px; font-weight:bold;">收文信息修改</td>
 </tr>
 <tr class="bingan">
  <td >基本信息</td>
  <td>文件编号：</td>
  <td><s:textfield name="odRecord.odRecordSerial"/></td>
  <td>来文机关：</td>
   <td colspan='3'><s:textfield name="odRecord.odRecordOrg"/></td>
 </tr>
 <tr class="bingan">
 	<td></td>
 	<td>文件名称：</td>
    <td colspan='5'><s:textfield name="odRecord.odRecordName"/></td>
 </tr>
 <tr class="bingan">
  <td ></td>
  <td>成文日期：</td>
  <td colspan='2'><s:textfield name="odRecord.odRecordDate"/></td>
    <td>收文日期：</td>
  <td colspan='2'><s:textfield name="odRecord.odRecordCometime"/></td>
  </tr>
  <tr class="bingan">
  <td ></td>
  <td >件号：</td>
  <td><s:textfield name="odRecord.odRecordNum"/></td>
  <td>来文年度：</td> <td><s:textfield name="odRecord.odRecordYear" onFocus="WdatePicker({dateFmt:'yyyy',minDate:'1949',maxDate:'%y'})"/></td>
  <td>存档年限：</td> <td>	<s:select name="odRecord.odRecordLimit" list="#{'10年':'10年','30年':'30年','永久':'永久'}"  headerKey="" headerValue="---请选择---"/></td>
  </tr>
  <tr class="bingan">
 	<td></td>
  <td>关键词：</td>
  <td colspan='5'><s:textfield name="odRecord.odRecordKeywords" /></td>
 </tr>
  <tr class="bingan">
 	<td></td>
 	<td>备注：</td>
    <td colspan='5'><s:textfield name="odRecord.odRecordRemark"/></td>
 </tr>
   <tr class="bingan">
 	<td>附件：</td>
    <td colspan='6' id="fileDownload"></td>
 </tr>
 <tr>
  <td colspan="7" style="text-align:center;">
  	<input  type="button" onclick="submit_onclick();" value="保存" class="btn btn-primary" />
  	&nbsp;&nbsp;<input  type="button" onclick="if(!confirm('是否放弃保存？')){return false;} window.location.href='queryOdRecords.action';" value="返回" class="btn btn-primary" />
  </td>
</tr>
</table>
</s:form>
</body>
</html>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收文批量导入</title>
<style>
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(":text").css("width","90%");
 	//显示已经提交的文件
 	var fileRelated = "";
	<s:iterator value="uploadFileList">
		fileRelated = "${fileRelated}";
		$("#"+fileRelated).html("<a href='download.action?uploadFile.fileId="+"${fileId}"+"'>已上传："+"${originalName}"+"</a>");
	</s:iterator>
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
  <td colspan='7'  style="font-size:20px; font-weight:bold;">批量导入</td>
 </tr>
<tr>
	<td>批量导入</td>
	<td colspan='6'>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" class="a-upload">
		<s:file  label="上传文件" id="upload1" name="ajaxUpload" cssClass="blylb" onChange="ajaxUpload1(this);"></s:file>
		点击上传文件</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<span id="blylb"></span>
	</td>
</tr>
<tr>
	<td colspan='7'>
		<a target="_self" href="downloadTemplet.action"><i class="icon-font">&#xe056;</i>收文批量导入模板下载</a>
	</td>
</tr>
<tr>
	<td colspan='7'>
		注意：单次最大导入1000条，请勿重复导入，并严格按模板中的格式填充EXCEL
	</td>
</tr>
 <tr>
  <td colspan="7" style="text-align:center;">
  	<%--<input  type="button" onclick="submit_onclick();" value="导入" class="btn btn-primary" /> --%>
  	&nbsp;&nbsp;<input  type="button" onclick="if(!confirm('是否放弃？')){return false;} window.location.href='manage.jsp';" value="返回" class="btn btn-primary" />
  </td>
</tr>
</table>
</s:form>
</body>
</html>
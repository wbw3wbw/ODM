<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收文管理</title>
	<script type="text/javascript" src="<%=path%>/script/manage.js" charset=”gb2312″ version="v1.0"></script>
<style>
.btnheight{height:22px;font-size:13px;padding-top:1px;}
</style>
<script type="text/javascript">

//回车键提交表单
document.onkeydown = function(evt){
	evt = window.event?window.event:evt;
	if (evt.keyCode==13) {
		submit_onclick(1);
	}
};
</script>
</head>
  
<body>
<div class="search-wrap">
<div class="search-content">
	<s:form id="form1" action="queryOdRecords" method="post" theme="simple">
	<table width="100%" class="search-tab"> 
       <tr>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
       	<td>文件名称：</td>
       	<td><s:textfield id="odRecordName" cssClass="common-text" name="odRecord.odRecordName" /></td>
       	<td>文件编号：</td>
       	<td><s:textfield id="odRecordSerial" cssClass="common-text" name="odRecord.odRecordSerial" /></td>
       	<td>来文机关：</td>
       	<td><s:textfield id="odRecordOrg" cssClass="common-text" name="odRecord.odRecordOrg" /></td>
       	<td>关键词：</td>
       	<td><s:textfield id="odRecordKeywords" cssClass="common-text" name="odRecord.odRecordKeywords" /></td>
       </tr>
       <tr>
         <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
       	<td>来文年度：</td>
       	<td><s:textfield id="odRecordYear" name="odRecord.odRecordYear" onFocus="WdatePicker({dateFmt:'yyyy',minDate:'1949',maxDate:'%y'})"/></td>
       	<td><span id="deshi">存档年限：</span></td>
       	<td><s:select id="odRecordLimit" style="WIDTH: 172px;height:30px" disabled="false" cssClass="common-text" list="#{'':'所有','10年':'10年','30年':'30年','永久':'永久'}" 
       				name="odRecord.odRecordLimit"  onChange="return false;" />
       	</td>
		<td>备注：</td>
		<td><s:textfield id="odRecordRemark" cssClass="common-text" name="odRecord.odRecordRemark" /></td>
		<td>ID：</td>
		<td><s:textfield id="odRecordId" cssClass="common-text" name="odRecord.odRecordId" /></td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td>成文日期：</td>
		<td><s:textfield style="WIDTH: 90px;" name="odRecordDate1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1949',maxDate:'%y-%M-%d'})"/>
			~<s:textfield style="WIDTH: 90px;" name="odRecordDate2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1949',maxDate:'%y-%M-%d'})"/></td>
		<td>收文日期：</td>
		<td><s:textfield style="WIDTH: 90px;" name="odRecordCometime1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1949',maxDate:'%y-%M-%d'})"/>
			~<s:textfield style="WIDTH: 90px;" name="odRecordCometime2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1949',maxDate:'%y-%M-%d'})"/></td>
    	<td>件号：</td>
		<td><s:textfield id="odRecordId" cssClass="common-text" name="odRecord.odRecordNum" /></td>
    	<td colspan="2" align="center">
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
    <td width="4%">ID</td>
    <td width="10%">来文机关</td>
    <td width="21%">文件名称</td>
    <td width="6%">来文年度</td>
    <td width="4%">件号</td>
    <td width="11%">文件编号</td>
    <td width="7%">成文日期</td>
    <td width="7%">存档年限</td>
    <td width="8%">录入时间</td>
    <td width="7%">录入人</td>
    <td width="6%">备注</td>
    <td width="12%">操作</td>
  </tr>
  <s:iterator value="odRecords">
  <tr align="center" id="${odRecordId}_tr">
    <td>${odRecordId}</td>
    <td>${odRecordOrg}</td>
    <td><a href="viewOdRecord.action?odRecord.odRecordId=${odRecordId}" >${odRecordName}</a></td>
    <td>${odRecordYear}</td>
    <td>${odRecordNum}</td>
    <td>${odRecordSerial}</td>
    <td>${odRecordDate}</td>
    <td>${odRecordLimit}</td>
    <td>${odRecordInstime}</td>
    <td>${odRecordAdminid}</td>
    <td>${odRecordRemark}</td>
    <td>
    	<input type='button' class='btn btn-info btnheight' onclick='window.location.href="editOdRecord.action?odRecord.odRecordId=${odRecordId}"' value='编辑'/>&nbsp;
    	<input type='button' class='btn btn-inverse btnheight' onclick="deleteOd(${odRecordId});" value='删除'/>&nbsp;
    </td>
  </tr>
  </s:iterator>
<tr>
<td colspan =16>
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

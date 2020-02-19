<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收文登记</title>
<style>
.btnheight{height:22px;font-size:13px;padding-top:1px;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(":text").css("width","90%");
	$("#jxlr").hide();
 	//显示已经提交的附件
 	if(("${odAtts}").length > 2){
	 	var tmpHtml = "<tr><td>已传附件<br/><a href='zipDownload.action?odRecord.odRecordId="+"${odRecord.odRecordId}"+"'>打包下载</a></td><td colspan='6'>";
		<s:iterator value="odAtts">
			tmpHtml = tmpHtml + "<div id=${odAttId}><a href='download.action?odAtt.odAttId="+"${odAttId}"+"'>"+"${odAttFilename}"+
				"</a>&nbsp;&nbsp;&nbsp;&nbsp;<input  type='button' onclick='deleteOdAtt(${odAttId})'  value='删除' class='btn btn-inverse btnheight' /><br/></div>";
		</s:iterator>
		tmpHtml = tmpHtml + "</td></tr>";
		var $tr = $ ("#form1 tr").eq(-3);
		$tr.after(tmpHtml);
	}
	if("view"=="${message}"){
			$(":input").attr("disabled","disabled");
			$(":button").hide();
			$("#tjfj").hide();
			$("#ljkh").hide();
			$("#tr1").hide();
	}
});
function submit_onclick(){
	//$("#form1").submit();
	if ($.trim($("#odRecordName").val()) == "") {
		alert("文件名称不能为空！");
		$("#odRecordName").focus();
		return false;
	}
	var atts = "";
	$("span[name='attspan']").each(function(){
		atts = atts + this.className+"-=-";//获取所有的附件ID，并用符号分割
	});
	$("#atts").val(atts);
	
	$.ajax({
	    type: "POST",
	    url: "saveOdRecord.action",
	    data: $("#form1").serialize(),
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    alert(obj);
		    if("保存成功"==obj){
			    //添加所有input控件的disabled属性:
				$(":input").attr("disabled","disabled");
				$(":button").hide();
				$("#tjfj").hide();
				$("#ljkh").hide();
            }
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("系统错误，请重试！");
	        return false;
	    }
    });
}
//ajax上传文件，通过ajaxfileupload插件实现
function ajaxUpload2(btn){
	//$("#loading").show();
	var $btn = $(btn);
	var oldHtml = $("#"+btn.className).html();
	var randomId = Math.floor(Math.random()*999+1);
	$("#"+btn.className).text("正在上传文件");
	$("#"+btn.className).after("<img id='"+randomId+"' src='../images/loading.gif' height='30px' width='30px'/>");
    $.ajaxFileUpload({
        url: 'ajaxUploadOdAtt.action', 
        type: 'post',
        secureuri: false, //一般设置为false
        fileElementId: btn.id, // 上传文件的id、name属性名
        dataType: 'json', //返回值类型，一般设置为json、application/json
        data: {'ajaxUploadRelated':btn.className},
        success: function(data, status){ 
        	//data = jQuery.parseJSON(jQuery(data).text());
        	data = jQuery.parseJSON(data);
            setTimeout(function () {
            	$("#"+randomId).remove();
     		   if(data.result == "成功"){
    		   		$("#"+btn.className).html("已上传：<a href='download.action?odAtt.odAttId="+data.attid+"'>"+data.name+"</a>");
    		   		$("#"+btn.className).attr("class",data.attid) ;
    		   		//alert($("#"+btn.className).attr("class"));
    		   		//alert($("#"+btn.className).attr("name"));
    		   }else if(data.result == "失败"){
    		   		$("#"+btn.className).html("上传失败--"+data.reason);
    		   }else if(data.result == "未选择文件"){
    		   		$("#"+btn.className).html(oldHtml);
    		   }else{
    		   		$("#"+btn.className).html("上传失败--原因未知");
    		   }
            }, 1000);
        },
        error: function(data, status, e){ 
        	$("#"+randomId).remove();
            $("#"+btn.className).html("上传失败--未知错误");
        }
    });
}
//删除一条收文附件记录
function deleteOdAtt(odAttId){
	if(!confirm("是否确定要删除此附件？")){
		return false;
	}
	$.ajax({
	    type: "POST",
	    url: "deleteOdAtt.action",
	    data: {"odAtt.odAttId":odAttId},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	$("#"+odAttId).remove();
		    }
		    else {
		    	alert(obj);
		    }
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("系统错误，请重试！");
	        return false;
	    }
    });
}
var counter = 1;
//增加一列新的上传附件行
function addOdAtt(){
	counter++;
	var trHtml = "<tr id='tr"+
			counter+
			"'><td></td><td colspan='6'>	附件 "+
			counter+
		 	" ：&nbsp;&nbsp;&nbsp;&nbsp;		<a href='javascript:void(0);' class='a-upload'>"+
			"<input type='file' name='ajaxUpload' value='' id='upload"+
			counter+
			"' class='ajaxupload"+
			counter+
			"' onChange='ajaxUpload2(this);'/>"+
			"点击上传文件</a>&nbsp;&nbsp;&nbsp;&nbsp;		<span id='ajaxupload"+
			counter+
			"' name='attspan'></span>&nbsp;&nbsp;&nbsp;&nbsp;<input  type='button' onclick=\"	if(!confirm('是否确定要删除此附件？')){return false;}$('#tr"+
			counter+
			"').remove();\" value='删除' class='btn btn-inverse btnheight' /></td></tr>";
	 var $tr = $ ("#form1 tr").eq(-2);
	 $tr.after(trHtml);
}
</script>
</head>

<body>
<br/>
<s:form id='form1' action="" theme="simple" enctype="multipart/form-data" method="post">
<s:hidden id="atts" name="atts" />
<s:hidden name="odRecord.odRecordId" />
<s:hidden name="odRecord.odRecordInstime" />
<s:hidden name="odRecord.odRecordAdminid" />
<table  align="center" width='90%' class="insert-tab">
<tr class="hidden"><th width="10%"></th><th width="10%"></th><th width="20%"></th><th width="10%"></th><th width="15%"></th><th width="10%"></th><th width="20%"></th></tr>
 <tr>
  <td colspan='7'  style="font-size:20px; font-weight:bold;">收文登记</td>
 </tr>
 <tr class="bingan">
  <td >基本信息</td>
  <td>来文机关：</td>
   <td colspan='2'><s:textfield name="odRecord.odRecordOrg"/></td>
  <td>文件编号：<a id="ljkh" href="javascript:void(0);"  onclick="$('#odRecordSerial').val($('#odRecordSerial').val()+'〔〕');">插入〔〕</a></td>
  <td colspan='2'><s:textfield id="odRecordSerial" name="odRecord.odRecordSerial"/></td>
 </tr>
 <tr class="bingan">
 	<td></td>
 	<td>文件名称：</td>
    <td colspan='5'><s:textfield id="odRecordName" name="odRecord.odRecordName"/></td>
 </tr>
 <tr class="bingan">
  <td ></td>
  <td>成文日期：</td>
  <td colspan='2'><s:textfield name="odRecord.odRecordDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1949',maxDate:'%y-%M-%d'})"/></td>
    <td>收文日期：</td>
  <td colspan='2'><s:textfield name="odRecord.odRecordCometime" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1949',maxDate:'%y-%M-%d'})"/></td>
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
<tr id="tjfj" >
	<td colspan='7'>附件上传（<a href="javascript:void(0);"  onclick="addOdAtt();">点我增加新的附件行</a>）</td>
</tr>
<tr id="tr1">
	<td></td>
	<td colspan='6'>
		附件 1 ：&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" class="a-upload">
		<s:file  label="上传文件" id="upload1" name="ajaxUpload" cssClass="ajaxupload1" onChange="ajaxUpload2(this);"></s:file>
		点击上传文件</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<span id="ajaxupload1"  name="attspan"></span>&nbsp;&nbsp;&nbsp;
		<input  type="button" onclick="	if(!confirm('是否确定要删除此附件？')){return false;}$('#tr1').remove();" value="删除" class="btn btn-inverse btnheight" />
	</td>
</tr>
 <tr>
  <td colspan="7" style="text-align:center;">
  	<input id="bc"  type="button" onclick="submit_onclick();" value="保存" class="btn btn-primary" />
  	&nbsp;&nbsp;<input id="bc"  type="button" onclick="if(!confirm('是否放弃保存？')){return false;} window.location.href='manage.jsp';" value="返回" class="btn btn-primary" />
 	<input id="jxlr"  type="button" onclick="window.location.href='addOdRecord.jsp';" value="继续录入" class="btn btn-primary" />
  </td>
</tr>
</table>
</s:form>
</body>
</html>
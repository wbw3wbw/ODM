var gUserId;
function toExcel(button){
	button.disabled = true;
	$("#form1").attr("action","toExcel");
	$("#form1").submit();
	setTimeout("blur();",10000);
}
function blur(){
	$("#daochu").attr("disabled",false);
}
function chooseToExcel(button){
	button.disabled = true;
	$("#form1").attr("action","crToExcel");
	$("#form1").submit();
	setTimeout("blur1();",10000);
}
function blur1(){
	$("#crToExcel").attr("disabled",false);
}
function jump()
{
	submit_onclick($('#jumpPage').val());
	//window.location.href("query.action?pageIndex="+$('#pageIndex').val());
	
}
function submit_onclick(pageIndex){
	$("#pageIndex").val(pageIndex);
	$("#form1").attr("action","queryOdRecords");
	$("#form1").submit();
}
//打回申请
function daHui(){
	var reason = $("#reason").val();
	if($.trim(reason) == ""){
		alert("请填写打回原因");
		return false;
	}
    var params = {
			"basicInfo.userId": gUserId,
	    	"basicInfo.rejectReason": reason
	};
	$.ajax({
	    type: "POST",
	    url: "daHui.action",
	    data: params,
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	$("#"+gUserId+"_status").text("审核拒绝");
		    	alert("打回成功");
		    	$(".showbox .close").click();
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

//弹出打回对话框
function box(userId){
	gUserId = userId;
	$("#reason").val(""); 
	$("#reason1").val(""); 
	var box =300;
	var th= $(window).scrollTop()+$(window).height()/1.6-box;
	var h =document.body.clientHeight;
	var rw =$(window).width()/2-box;
	$("#span1").text(userId);
	$(".showbox").animate({top:th,opacity:'show',width:600,height:210,right:rw},500);
	$("#zhezhao").css({
		display:"block",height:$(document).height()
	});
	//$("#daHui").attr("onclick","javascript:daHui("+userId+");");
}

//删除一条收文记录
function deleteOd(odRecordId){
	if(!confirm("是否确定要删除ID为"+odRecordId+"的收文记录？")){
		return false;
	}
	$.ajax({
	    type: "POST",
	    url: "deleteOdRecord.action",
	    data: {"odRecord.odRecordId":odRecordId},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	$("#"+odRecordId+"_tr").remove();
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

function resetPwd(userId){
	if(!confirm("是否确定要重置ID为"+userId+"的密码？")){
		return false;
	}
	$.ajax({
	    type: "POST",
	    url: "resetPwd.action",
	    data: {"basicInfo.userId":userId},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	alert("已将密码重置为6个1");
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
function createWord(button){
	button.disabled = true;
	button.value = "正在生成...";
	$.ajax({
	    type: "POST",
	    url: "createWord.action",
	    data: {
	    	"basicInfo.userId":$("#userId").val(),
	    	"basicInfo.name":$("#name").val(),
	    	"basicInfo.idCard":$("#idCard").val(),
	    	"basicInfo.phoneNo":$("#phoneNo").val(),
	    	"basicInfo.organ":$("#organ").val(),
	    	"basicInfo.status":$("#status").val()},
	    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
	    success: function(json){  
	    	//alert("json=" + json);
		    var obj = $.parseJSON(json);  //使用这个方法解析json
		    if(obj == "成功") {
		    	alert("生成word成功！");
		    	button.disabled = false;
		    	button.value = "生成";
		    }
		    else {
		    	alert(obj);
		    	button.disabled = false;
		    	button.value = "生成";
		    }
	    },
	    error: function(json){
	        //alert("json=" + json);
	        alert("系统错误，请重试！");
	        button.disabled = false;
	        button.value = "生成";
	        return false;
	    }
    });
}

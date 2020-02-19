//ajax上传文件，通过ajaxfileupload插件实现
function ajaxUpload1(btn){
	//$("#loading").show();
	var $btn = $(btn);
	var randomId = Math.floor(Math.random()*999+1);
	$("#"+btn.className).text("正在上传文件");
	$("#"+btn.className).after("<img id='"+randomId+"' src='../images/loading.gif' height='30px' width='30px'/>");
	//var fname=btn.value.replace("C:\\fakepath\\","");
    $.ajaxFileUpload({
        url: 'ajaxUpload.action', 
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
    		   		$("#"+btn.className).html("已上传："+data.name);
    		   }else if(data.result == "失败"){
    		   		$("#"+btn.className).html("上传失败--"+data.reason);
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
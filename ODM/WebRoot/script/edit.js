$(document).keydown(function (e) { 
var doPrevent; 
if (e.keyCode == 8) { 
var d = e.srcElement || e.target; 
if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') { 
doPrevent = d.readOnly || d.disabled; 
} 
else 
doPrevent = true; 
} 
else 
doPrevent = false; 

if (doPrevent) 
e.preventDefault(); 
}); 

function submit_onclick(){
	//验证必填项
	var flag = 1;
	$(".input2").each(function(){
		if($.trim($(this).val()) == ""){
			alert("带*号为必填项！");
			$(this).focus();
			flag = 0;
			return false;
		}});
	if(0 == flag){return false;}

	$(".select1").each(function(){
		if($.trim($(this).val()) == "-1"){
			alert("带*号为必选项！");
			$(this).focus();
			flag = 0;
			return false;
		}});
	if(0 == flag){return false;}
	
	//验证学历填写
	$(".input3").each(function(){
		if($.trim($(this).val()) == ""){
			alert("申报学历最少完整填写一项！");
			$(this).focus();
			flag = 0;
			return false;
		}});
	if(0 == flag){return false;}
	
	//验证三新专题
	if($("#sblx").val()=="直接申报" || $("#sbjb").val()=="正高"){
		if($.trim($("#sxzttm").val()) == ""){
			alert("申报正高或直接申报者必须填写!");
			$("#sxzttm").focus();
			return false;
		}
	}
	
	//病案与专题报告至少填写其中一个，并且只要填写就必须完整
	var flag1 = 0;
	var flag2 = 0;
	$(".input4").each(function(){
		if($.trim($(this).val()) != ""){
			flag1 = 1;
		}});
	$(".input5").each(function(){
		if($.trim($(this).val()) != ""){
			flag2 = 1;
		}});
	if((0 == flag1) && (0 == flag2)){
		alert("病案与专题报告只能填写其中一个!");
		flag = 0;
	}
	if((1 == flag1) && (1 == flag2)){
		alert("病案与专题报告只能填写其中一个!");
		flag = 0;
	}
	if(0 == flag){return false;}
	if(1 == flag1){
		$(".input4").each(function(){
			if($.trim($(this).val()) == ""){
				alert("病案填写不完整，请填写所有的病案！！");
				$(this).focus();
				flag = 0;
				return false;
			}});
	}
	if(0 == flag){return false;}
	if(1 == flag2){
		$(".input5").each(function(){
			if($.trim($(this).val()) == ""){
				alert("专题报告填写不完整，请填写所有的专题报告！");
				$(this).focus();
				flag = 0;
				return false;
			}});
	}
	if(0 == flag){return false;}
	//检查论文的完整度
	$(".input6").each(function(){
		if($.trim($(this).val()) == ""){
			alert("论文填写不完整，请填写所有的送审论文！");
			$(this).focus();
			flag = 0;
			return false;
		}});
	if(0 == flag){return false;}
	//全部检查完毕才能提交
	if(1 == flag){
		//alert($("#sbzy").find("option:selected").text());
		//$("#sbzy").val($("#sbzy").find("option:selected").text());
		$("form").submit();
	}
}

function changeZydm(obj){
	//alert($("#area").find("option:selected").text());
	$("#sbzy_").val(obj.value);
	$("#zydm").val($("#sbzy_").find("option:selected").text());
}

function changeSheet(obj){
	//病案与专题报告至少填写其中一个，并且只要填写就必须完整
	var flag = 1;
	if(obj.value == "1"){	
		$(".input5").each(function(){
		if($.trim($(this).val()) != ""){
			flag = 0;
		}});
		if(0 == flag){
			alert("您已选择填写专题报告，不能再填写病案!");
			obj.value=2;
			return false;
		}
		$(".bingan").show();
		$(".ztbg").hide();
	}else if(obj.value == "2"){
		$(".input4").each(function(){
		if($.trim($(this).val()) != ""){
			flag = 0;
		}});
		if(0 == flag){
			alert("您已选择填写病案，不能再填写专题报告!");
			obj.value=1;
			return false;
		}
		$(".ztbg").show();
		$(".bingan").hide();	
	}else{
		$(".input4").each(function(){
		if($.trim($(this).val()) != ""){
			flag = 0;
		}});		
		if(0 == flag){
			alert("您已选择填写病案!");
			obj.value=1;
			return false;
		}
		$(".input5").each(function(){
		if($.trim($(this).val()) != ""){
			flag = 0;
		}});		
		if(0 == flag){
			alert("您已选择填写专题报告!");
			obj.value=1;
			return false;
		}
		$(".ztbg").hide();
		$(".bingan").hide();	
	}
}
function changeLunwen(){
	var sblx = $("#sblx").val();
	var sbjb = $("#sbjb").val();
	if(sblx=="直接申报" || sbjb=="正高"){$("#sxzt").show();}
	else{$("#sxzt").hide();}
	if(sblx=="-1" || sbjb=="-1"){showLunwen(0);}
	else if(sblx=="正常申报" && sbjb=="正高"){showLunwen(3);}
	else if(sblx=="申报城市基层评审" && sbjb=="正高"){showLunwen(2);}
	else if(sblx=="申报农村评审" && sbjb=="正高"){showLunwen(2);}
	else if(sblx=="直接申报" && sbjb=="正高"){showLunwen(5);}
	else if(sblx=="正常申报" && sbjb=="副高"){showLunwen(2);}
	else if(sblx=="申报城市基层评审" && sbjb=="副高"){showLunwen(1);}
	else if(sblx=="申报农村评审" && sbjb=="副高"){showLunwen(1);}
	else if(sblx=="直接申报" && sbjb=="副高"){showLunwen(4);}
	else{showLunwen(0);}
}
function showLunwen(mount){
	for(var i=1;i<=mount;i++){
		$(".lwtr"+i).show();
		$(".lw"+i).addClass("input6");
	}
	for(var i=5;i>mount;i--){
		$(".lwtr"+i).hide();
		$(".lw"+i).removeClass("input6");	
	}
}
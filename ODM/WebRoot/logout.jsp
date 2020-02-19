<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%session.invalidate();%>
    <%String type=request.getParameter("type");%>
    <script type="text/javascript" src="<%=path%>/script/jquery-1.10.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		msg = <%=type%>;
		if(msg != 1){
			alert("登录超时，请重新登录");
		}
		window.document.location.href="<%=basePath%>/login.jsp";
		window.parent.window.document.location.href="<%=basePath%>/login.jsp";
	});
</script>
  </head>
  
  <body>
  </body>
</html>

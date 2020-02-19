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
    <meta http-equiv="refresh" content="3; url=<%=basePath%>admin/login.jsp">
  </head>
  
  <body>
  <h1>登录已超时，请重新登录</h1>
  </body>
</html>

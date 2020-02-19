<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="application/msexcel" %>
<!-- 以上这行设定本网页为excel格式的网页 -->
<%
   response.setHeader("Content-disposition","inline; filename=test.xls");
%>
<html>
<head>
	<meta name="Generator" content="Microsoft Excel 11">  
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">  
</head>
<body>
  <table border="1" width="100%">
    <tr>
      <td>姓名</td><td>身份证字号</td><td>生日</td>
    </tr>
    <tr>
      <td>李玟</td><td>N111111111</td><td>1900/11/12</td>
    </tr>
    <tr>
      <td>梁静如</td><td>N222222222</td><td>1923/10/1</td>
    </tr>
    <tr>
      <td>张惠妹</td><td>N333333333</td><td>1934/12/18</td>
    </tr>
  </table>
</body>
</html>

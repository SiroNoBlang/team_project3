<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String code = session.getAttribute("sCode").toString();
String nickname = session.getAttribute("sNickname").toString();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>관리자임</h1>
	<%=code %>
	<%=nickname %>
</body>
</html>
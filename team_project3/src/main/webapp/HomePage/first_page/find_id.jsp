<%@page import="vo.ActionForward"%>
<%@page import="svc.FindIdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String nickname = request.getParameter("find_id_member_nickname");
String email = request.getParameter("find_id_member_email");

FindIdService service = new FindIdService();
String isFindId = service.isFindId(nickname, email); 

if(isFindId.equals("")) {
	response.setContentType("text/html; charset=UTF-8");
	out.println("<script>");
	out.println("alert('아이디가 없습니다!')");
	out.println("document.fi.find_id_member_nickname.focus()");
	out.println("</script>");
} else {
	out.println("아이디는 '" + isFindId + "'입니다.");
}

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
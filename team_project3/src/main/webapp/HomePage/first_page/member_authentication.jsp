<%@page import="java.sql.Connection"%>
<%@page import="dao.MemberDAO"%>
<%@page import="static db.JdbcUtil.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setCharacterEncoding("UTF-8");

String code = request.getParameter("code");
String email = request.getParameter("email");

Connection con = getConnection();
MemberDAO dao = MemberDAO.getInstance();
dao.setConnection(con);
int result = dao.selectAuthInfo(email,code);


String msg = "";
boolean AuthStatusResult = dao.changeAuthStatus(email);
if(!AuthStatusResult){
	msg = "인증을 완료해주세요!";
	
}
if(result == 0){ //인증실패 (잘못된 인증코드)
	msg = "인증실패 (잘못된 인증코드)";
	
}else if (result == -1){ //인증실패 (인증 정보가 존재하지 않음)
	msg = "인증실패 (인증 정보가 존재하지 않음)";
}else{ // 인증성공
	msg = "인증성공";

commit(con);
close(con);	


}

%>

	<script >
	alert("<%=msg%>");
	window.close();
	</script>

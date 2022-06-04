<%@page import="svc.CheckNicknameService"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// CheckNicknameService 객체의 checkNickname() 메서드를 호출하여 아이디 중복 여부 확인 작업 요청
// 마찬가지로 이것도 하나의 파일로 만들어서 닉네임과 아이디 판별을 해야함.
String nickname = request.getParameter("member_nickname");

CheckNicknameService service = new CheckNicknameService();
boolean isDuplicate = service.checkNickname(nickname);

if(isDuplicate) { // 중복
	out.println("이미 사용중인 닉네임");
	response.setContentType("text/html; charset=UTF-8");
	out.println("<script>");
	out.println("var checkNickname = false");
	out.println("</script>");
} else { // 중복 아님
	if(nickname.length()>=8 && nickname.length()<=16 ){
		String[] spec = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=" ,"`" ,"~" ,"[" ,"{" ,"]" ,"}" ,";" ,":" ,"'" ,"\"" ,"," ,"<" ,"." ,">" ,"/" ,"?" ,"\\" ,"|"}; 
		
		for( String s : spec){
			if(nickname.contains(s)){
				if(s!=""){
				out.println(s+" 사용불가");
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script>");
				out.println("var checkNickname = false");
				out.println("</script>");
				}
			}
		}
		
	}else{
		out.println("8자 ~ 16자 닉네임 필수");
		response.setContentType("text/html; charset=UTF-8");
		out.println("<script>");
		out.println("var checkNickname = false");
		out.println("</script>");
	}
	response.setContentType("text/html; charset=UTF-8");
	out.println("<script>");
	out.println("var checkNickname = true");
	out.println("</script>");
}
%>

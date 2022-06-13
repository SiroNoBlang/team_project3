<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
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
boolean result = true;
String str = "";

if(isDuplicate) { // 중복
	str = "이미 사용중인 닉네임";
	result = false;
} else { // 중복 아님
	if (nickname.length() >= 8 && nickname.length() <= 16) {
		String[] spec = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "`", "~", "[", "{", "]",
		"}", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?", "\\", "|" };

		for (String s : spec) {
			if (nickname.contains(s)) {
				if (s != "") {
					str = s + "사용불가";
					result = false;
				}
			} 
		}
		
		if(result){
			str = "닉네임 사용가능";
		}

	} else {
		str = "8자 ~ 16자 닉네임 필수";
		result = false;
	}
}
out.println(str);
response.setContentType("text/html; charset=UTF-8");
out.println("<script>");
out.println("checkNickname = " + result);
out.println("</script>");
%>

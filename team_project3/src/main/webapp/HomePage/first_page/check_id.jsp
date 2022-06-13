<%@page import="java.io.PrintWriter"%>
<%@page import="svc.CheckIdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// CheckIdService 객체의 checkId() 메서드를 호출하여 아이디 중복 여부 확인 작업 요청
// 마찬가지로 이것도 하나의 파일로 만들어서 닉네임과 아이디 판별을 해야함.
String id = request.getParameter("join_member_id");

CheckIdService service = new CheckIdService();
boolean isDuplicate = service.checkId(id);
boolean result = true;
String str = "";

if (isDuplicate) { // 중복
	str = "이미 사용중인 아이디";
	result = false;
} else { // 중복 아님
	if (id.length() >= 8 && id.length() <= 16) {
		String[] spec = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "`", "~", "[", "{", "]",
		"}", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?", "\\", "|" };

		for (String s : spec) {
			if (id.contains(s)) {
				if (s != "") {
					str = s + "사용불가";
					result = false;
				}
			}
		}
		
		if(result){
			str = "아이디 사용가능";
		}

	} else {
		str = "8자 ~ 16자 아이디 필수";
		result = false;
	}
}
	out.println(str);
	response.setContentType("text/html; charset=UTF-8");
	out.println("<script>");
	out.println("checkId = " + result);
	out.println("</script>");
%>


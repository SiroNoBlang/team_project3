<%@page import="svc.CheckIdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// AJAX 를 사용하여 전달받은 id 가져와서 변수에 저장 후
// MemberDAO 객체의 checkId() 메서드를 호출하여 아이디 중복 여부 확인 작업 요청
// // => 파라미터 : 아이디(id)     리턴타입 : boolean(isDuplicate)
String id = request.getParameter("join_member_id");

CheckIdService service = new CheckIdService();
boolean isDuplicate = service.checkId(id);

 
if(isDuplicate) { // 중복
	out.println("이미 사용중인 아이디");
} else { // 중복 아님
	out.println("사용 가능한 아이디");
}
%>
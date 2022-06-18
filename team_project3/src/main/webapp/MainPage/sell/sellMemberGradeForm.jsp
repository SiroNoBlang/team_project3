<%@page import="java.util.ArrayList"%>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%
MemberBean memberbean = (MemberBean)request.getAttribute("memberbean");
ArrayList<MemberBean> gradeArr = (ArrayList<MemberBean>)request.getAttribute("gradeArr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr style="color: dddddd;">
	<div style="text-align:  center; "><h1>${sNickname}님의 등급은 ${memberbean.grade_name}입니다.</h1></div>
	
				<h4>현재 누적금액:${memberbean.member_info_detail_acc_money}원      다음 등급까지 ${(memberbean.highest_acc_money)-(memberbean.member_info_detail_acc_money)} 원</h4>
		
	<hr style="color: dddddd;">
		<table    border="1" style=" border- left:1px solid #000000 ; border- right:1px solid #000000;border- top:1px solid #000000;border- bottom:1px solid #000000;margin-left:auto; margin-right:auto; " >
			<tr>
				<th style="text-align: center;">등급</th><th style="text-align: center;">최소금액</th><th style="text-align: center;">최대금액</th><th style="text-align: center;">할인율</th>
			</tr>
			<c:forEach items="${gradeArr}" var="gradeArr">
			<tr>
				<td style="text-align: center;">
					${gradeArr.grade_name}
				</td>
				<td style="text-align: center;">
					${gradeArr.lowest_acc_money}
				</td>
				<td style="text-align: center;">
					${gradeArr.highest_acc_money}
				</td>
				<td style="text-align: center;">
					${gradeArr.discount_rate}%
				</td>
			</tr>
			</c:forEach>
		</table>
		
</body>
</html>
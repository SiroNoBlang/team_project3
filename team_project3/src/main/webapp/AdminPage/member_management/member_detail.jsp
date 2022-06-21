<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400'>
<link rel='stylesheet' href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
<link rel="stylesheet" href="AdminPage/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<script src="AdminPage/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		<c:if test="${memberDetail.getMember_service_log_status() != '정지'}">$('#reason').hide();</c:if>
		$('#member_status').change(function() {
			var result = $('#member_status option:selected').val();
			if (result == '정지') {
				$('#reason').show();
			} else {
				$('#reason').hide();
			}
		}); 
	}); 
</script>
</head>
<body>
	<div id="logo">
		<span class="big-logo">Admin</span> <span class="small-logo">&nbsp;A</span>
	</div>
	<div id="left-menu">
		<%@ include file="/AdminPage/menu/menu.jsp" %>
	</div>
	<div id="main-content">
		<div id="header">
			<div class="header-left float-left">
				<i id="toggle-left-menu" class="ion-android-menu"></i>
			</div>
			<div class="header-right float-right">
				<jsp:include page="/AdminPage/menu/topMenu.jsp"/>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">가입자 상세 정보</div>
				<form action="MemberUpdate.co?page=${param.page }" name="boardForm" method="post">
				<input type="hidden" name="member_code" value="${member_code }">
					<table>
						<tr>
							<th><label for="board_title">멤버넘버</label></th>
							<td>${memberDetail.getMember_num() }</td>
						</tr>
						<tr>
							<th><label for="board_title">회원 등급</label></th>
							<td>${memberDetail.getGrade_name() }</td>
						</tr>
						<tr>
							<th><label for="board_title">닉네임</label></th>
							<td>${memberDetail.getMember_nickname() }</td>
						</tr>
						<tr>
							<th><label for="board_title">아이디</label></th>
							<td>${memberDetail.getMember_id() }</td>
						</tr>
						<tr>
							<th><label for="board_title">이메일</label></th>
							<td>${memberDetail.getMember_email() }</td>
						</tr>
						<tr>
							<th><label for="board_title">이름</label></th>
							<td>${memberDetail.getMember_info_name() }</td>
						</tr>
						<tr>
							<th><label for="board_title">성별</label></th>
							<td>${memberDetail.getMember_info_gender() }</td>
						</tr>
						<tr>
							<th><label for="board_title">전화번호</label></th>
							<td>${memberDetail.getMember_info_phone() }</td>
						</tr>
						<tr>
							<th><label for="board_title">나이대</label></th>
							<td>${memberDetail.getMember_info_age() }</td>
						</tr>
						<tr>
							<th><label for="board_title">가입날짜</label></th>
							<td>${memberDetail.getMember_service_log_join_date() }</td>
						</tr>
						<tr>
							<th><label for="board_title">패스워드 수정날짜</label></th>
							<td>${memberDetail.getMember_service_log_passwd_change_date() }</td>
						</tr>
						<tr>
							<th><label for="board_title">우편번호</label></th>
							<td>${memberDetail.getMember_info_post_code() }</td>
						</tr>
						<tr>
							<th><label for="board_title">주소</label></th>
							<td>${memberDetail.getMember_info_address() }</td>
						</tr>
						<tr>
							<th><label for="board_title">상세주소</label></th>
							<td>${memberDetail.getMember_info_address_detail() }</td>
						</tr>
						<tr>
							<th><label for="board_title">배송지우편번호</label></th>
							<td>${memberDetail.getMember_info_ship_post_code() }</td>
						</tr>
						<tr>
							<th><label for="board_title">배송지주소</label></th>
							<td>${memberDetail.getMember_info_ship_address() }</td>
						</tr>
						<tr>
							<th><label for="board_title">배송지상세주소</label></th>
							<td>${memberDetail.getMember_info_ship_address_detail() }</td>
						</tr>
						<tr>
							<th><label for="board_title">누적금액</label></th>
							<td>${memberDetail.getMember_info_detail_acc_money() }</td>
						</tr>
						<tr>
							<th><label for="board_title">스타일</label>스타일</th>
							<td>${memberDetail.getMember_info_detail_like_style() }</td>
						</tr>
						<tr>
							<th><label for="board_title">브랜드</label>브랜드</th>
							<td>${memberDetail.getMember_info_detail_like_brand() }</td>
						</tr>
						<tr>
							<th><label for="board_title">관심품목</label>관심품목</th>
							<td>${memberDetail.getMember_info_detail_like_category() }</td>
						</tr>
						<tr>
							<th><label for="board_title">누적 포인트</label></th>
							<td>${memberDetail.getMember_info_detail_point() }</td>
						</tr>
						<tr>
							<th><label for="board_title">회원 상태</label></th>
							<td>
							<select id="member_status" name="member_status">
								<option value="정상" <c:if test="${memberDetail.getMember_service_log_status() eq '정상'}">selected="selected"</c:if>>정상</option>
								<option value="정지" <c:if test="${memberDetail.getMember_service_log_status() eq '정지'}">selected="selected"</c:if>>정지</option>
								<option value="탈퇴" <c:if test="${memberDetail.getMember_service_log_status() eq '탈퇴'}">selected="selected"</c:if>>탈퇴</option>
							</select>
							<select id="reason" name="reason">
								<option value="0" <c:if test="${memberDetail.getReason_num() eq '0'}">selected="selected"</c:if>>정상</option>
								<option value="1" <c:if test="${memberDetail.getReason_num() eq '1'}">selected="selected"</c:if>>정지 3일</option>
								<option value="2" <c:if test="${memberDetail.getReason_num() eq '2'}">selected="selected"</c:if>>정지 7일</option>
								<option value="3" <c:if test="${memberDetail.getReason_num() eq '3'}">selected="selected"</c:if>>정지 15일</option>
								<option value="4" <c:if test="${memberDetail.getReason_num() eq '4'}">selected="selected"</c:if>>정지 30일</option>
								<option value="5" <c:if test="${memberDetail.getReason_num() eq '5'}">selected="selected"</c:if>>정지 45일</option>
								<option value="6" <c:if test="${memberDetail.getReason_num() eq '6'}">selected="selected"</c:if>>정지 60일</option>
								<option value="7" <c:if test="${memberDetail.getReason_num() eq '7'}">selected="selected"</c:if>>정지 180일</option>
								<option value="8" <c:if test="${memberDetail.getReason_num() eq '8'}">selected="selected"</c:if>>정지 365일</option>
								<option value="9" <c:if test="${memberDetail.getReason_num() eq '9'}">selected="selected"</c:if>>영구 정지</option>
							</select>
							</td>
						</tr>
						<tr>
							<th><label for="board_title">회원가입 날짜</label></th>
							<td>${memberDetail.getMember_service_log_join_date() }</td>
						</tr>
						<tr>
							<th><label for="board_title">비밀번호변경 날짜</label></th>
							<td>${memberDetail.getMember_service_log_passwd_change_date() }</td>
						</tr>
						<tr>
							<th><label for="board_title">로그인 날짜</label></th>
							<td>${memberDetail.getMember_service_log_login_date() }</td>
						</tr>
						<tr>
							<th><label for="board_title">구매횟수</label></th>
							<td>${memberDetail.getMember_service_log_order_count() }</td>
						</tr>
					</table>
					<section id="commandCell">
						<input type="submit" value="수정">
						<c:if test="${memberDetail.getMember_service_log_status() eq '탈퇴'}">
							<input type="button" value="회원탈퇴" onclick="location.href='MemberDelete.co?page=${param.page }&member_code=${member_code }'">
						</c:if>
						<input type="button" value="목록" onclick="location.href='MemberManagement.co?page=${param.page }'">
					</section>
				</form>
			</div>
		</div>
	</div>
	<span id="show-lable">Hello</span>
	<!-- partial -->
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js'></script>
	<script src="AdminPage/js/script.js"></script>
</body>
</html>
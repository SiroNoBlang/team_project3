<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%
//세션 객체에 저장된 세션 닉네임("sNickname") 가져와서 변수에 저장
String sNickname = (String)session.getAttribute("sNickname");
	
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검수현황 - 상품상세정보</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css'>
<link rel='stylesheet' href='https://raw.githubusercontent.com/forsigner/magic-check/master/css/magic-check.css'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400'>
<link rel='stylesheet' href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
<link rel="stylesheet" href="AdminPage/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<script src="AdminPage/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="logo">
		<span class="big-logo">Admin</span> <span class="small-logo">&nbsp;A</span>
	</div>
	<div id="left-menu">
		<jsp:include page="/AdminPage/menu/menu.jsp"/>
	</div>
	<div id="main-content">
		<div id="header">
			<div class="header-left float-left">
				<i id="toggle-left-menu" class="ion-android-menu"></i>
			</div>
			<div class="header-right float-right">
				<a href="javascript:void(0)" onclick="confirmLogout()"><i class="icon ion-log-out" ></i></a>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">상품 상세정보</div>
				<form action="#" name="boardForm" method="post">
					<input type="hidden" name="sell_num" id ="sell_num" value="${confirmArticle.getSell_num()}">
					<input type="hidden" name="sNickname" id = "sNickname" value="${sNickname}">
					<table>
						<tr>
							<th><label for="board_title">판매넘버</label></th>
							<td>${confirmArticle.getSell_num() }</td>
						</tr>
						<tr>
							<th><label for="board_title">판매멤버코드</label></th>
							<td>${confirmArticle.getSell_member_code() }</td>
						</tr>
						<tr>
							<th><label for="board_title">상품명</label></th>
							<td>${confirmArticle.getSell_title() }</td>
						</tr>
						<tr>
							<th><label for="board_title">카테고리</label></th>
							<td>${confirmArticle.getSell_category() }</td>
						</tr>
						<tr>
							<th><label for="board_title">세부카테고리</label></th>
							<td>${confirmArticle.getSell_category_detail() }</td>
						</tr>
						<tr>
							<th><label for="board_title">내용</label></th>
							<td>${confirmArticle.getSell_content() }</td>
						</tr>
						<tr>
							<th><label for="board_title">가격</label></th>
							<td>${confirmArticle.getSell_price() }</td>
						</tr>
						<tr>
							<th><label for="board_title">색상</label></th>
							<td>${confirmArticle.getSell_color() }</td>
						</tr>
						<tr>
							<th><label for="board_title">사이즈</label></th>
							<td>${confirmArticle.getSell_size() }</td>
						</tr>
						<tr>
							<th><label for="board_title">브랜드</label></th>
							<td>${confirmArticle.getSell_brand() }</td>
						</tr>
						<tr>
							<th><label for="board_title">판매등록날짜</label></th>
							<td>${confirmArticle.getSell_write_date() }</td>
						</tr>
						<tr>
							<th><label for="board_title">이미지</label></th>
							<td>
							<c:choose>  
								<c:when test="${not empty confirmImgFileList }"> 
									<c:forEach var="confirmImg" items="${confirmImgFileList }">
										<img src="./Upload/sell_img/${confirmImg.getSell_img_real_name() }"> <br>
									</c:forEach>
								</c:when> 
								<c:otherwise>
										등록된 이미지가 없습니다.
								</c:otherwise> 
							</c:choose> 
							</td>
						</tr>
						<tr>
							<th><label for="board_title">상품상태</label></th>
							<td>${confirmArticle.getSell_list_item_status() }</td>
						</tr>
						<tr>
							<th><label for="board_title">반려/승인날짜</label></th>
							<td>${confirmArticle.getSell_list_approve_date() }</td>
						</tr>
						<tr>
							<th><label for="board_title">승인자</label></th>
							<td>${confirmArticle.getSell_list_approve_nickname() }</td>
						</tr>
						<tr>
							<th><label for="board_title">검수 현황</label></th>
							<td>
							
							<c:choose>  
								<c:when test="${confirmArticle.getSell_list_item_status() eq '검수완료' }"> 
										검수가 완료되었습니다.
								</c:when> 
								<c:when test="${confirmArticle.getSell_list_item_status() eq '검수중' }"> 
										검수 진행중입니다.
								</c:when> 
								<c:when test="${confirmArticle.getSell_list_item_status() eq '판매중' }"> 
										판매중입니다.
								</c:when> 
								<c:when test="${confirmArticle.getSell_list_item_status() eq '판매완료' }"> 
										판매완료되었습니다.
								</c:when> 
								<c:otherwise>
										검수 반려되었습니다.
								</c:otherwise> 
							</c:choose> 
							</td>
						</tr>

					</table>
					<section id="commandCell">
							<c:choose>  
								<c:when test="${confirmArticle.getSell_list_item_status() eq '검수중' }"> 
									<input type="button" value="검수완료" onclick="confirmStatus(this.value,sell_num.value,sNickname.value)">
									<input type="button" value="검수반려" onclick="confirmStatus(this.value,sell_num.value,sNickname.value)">
								</c:when> 
								<c:when test="${confirmArticle.getSell_list_item_status() eq '검수반려' }"> 
									<input type="button" value="검수완료" onclick="confirmStatus(this.value,sell_num.value,sNickname.value)">
								</c:when> 
								<c:when test="${confirmArticle.getSell_list_item_status() eq '판매중' }"> 
										<input type="button" value="검수반려" onclick="confirmStatus(this.value,sell_num.value,sNickname.value)">
								</c:when> 
							</c:choose> 
						<input type="button" value="목록" onclick="history.back()">
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
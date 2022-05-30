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
<title>관리자페이지 - 커뮤니티 QnA 상세정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css'>
<link rel='stylesheet' href='https://raw.githubusercontent.com/forsigner/magic-check/master/css/magic-check.css'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400'>
<link rel='stylesheet' href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
<link rel="stylesheet" href="AdminPage/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
</head>
<body>
	<div id="logo">
		<span class="big-logo">Admin</span> <span class="small-logo">&nbsp; A</span>
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
				<jsp:include page="/AdminPage/menu/topMenu.jsp"/>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">QnA 글 상세보기</div>
				
					<form action="#" name="boardForm" method="post" enctype="multipart/form-data">
						<input type="hidden" id="qna_num" name="qna_num" value="${param.qna_num}">
						<input type="hidden" id="page" name="page" value="${param.page}">
						<table >
							<tr>
								<th><label for="qna_title">제목</label></th>
								<td>${qnaArticle.getQna_title() }</td>
							</tr>
							<tr>
								<th><label for="qna_num">실제글번호</label></th>
								<td>${qnaArticle.getQna_num() }</td>
							</tr>
							<tr>
								<th><label for="qna_nickname">작성자</label></th>
								<td><%=sNickname %></td>
							</tr>
							<tr>
								<th><label for="qna_date">작성일</label></th>
								<td>${qnaArticle.getQna_write_date() }</td>
							</tr>
							<tr>
								<th><label for="qna_readcount">조회수</label></th>
								<td>${qnaArticle.getQna_readcount() }</td>
							</tr>
							<tr>
								<th><label for="qna_content">내용</label></th>
								<td>${qnaArticle.getQna_content() } <br>
							</tr>
						</table>
						<section id="commandCell">	
							<input type="button" value="답변" onclick="location.href='QnaReplyForm.co?qna_num=${param.qna_num}&page=${param.page}'">
							<input type="button" value="수정" onclick="location.href='QnaModifyForm.co?qna_num=${param.qna_num}&page=${param.page}'">
							<input type="button" value="삭제"  onclick="confirmDelete()">
							<input type="button" value="목록" onclick="history.back()">
<%-- 							<input type="button" value="목록" onclick="location.href='NoticeList.co?page=${param.page}'"> --%>
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

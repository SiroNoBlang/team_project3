<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 - 커뮤니티 이벤트상세정보</title>
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
				<div class="title">이벤트 글 상세보기</div>
					<form action="./CommunityBoardWritePro.co" name="boardForm" method="post" enctype="multipart/form-data">
						<input type="hidden" id="event_num" name="event_num" value="${param.event_num}">
						<input type="hidden" id="page" name="page" value="${param.page}">
						<table >
							<tr>
								<th>분류</th>
								<td>
									<label><input TYPE='radio' name='communityType' value='notice' />공지사항</label>
									<label><input TYPE='radio' name='communityType' value='event' checked="checked"/>이벤트</label>
								</td>
							</tr>
							<tr>
								<th><label for="board_title">제목</label></th>
								<td>${eventArticle.getEvent_title() }</td>
							</tr>
							<tr>
								<th><label for="board_num">실제글번호</label></th>
								<td>${eventArticle.getEvent_num() }</td>
							</tr>
							<tr>
								<th><label for="board_nickname">작성자</label></th>
								<td>${sNickname }</td>
							</tr>
							<tr>
								<th><label for="board_date">작성일</label></th>
								<td>${eventArticle.getEvent_write_date() }</td>
							</tr>
							<tr>
								<th><label for="board_readcount">조회수</label></th>
								<td>${eventArticle.getEvent_readcount() }</td>
							</tr>
							<tr>
								<th><label for="board_content">내용</label></th>
								<td>${eventArticle.getEvent_content() } <br>
									<c:if test="${not empty eventImgFileList}">
										<c:forEach var="eventImg" items="${eventImgFileList }">
											<img src="./Upload/admin_event_img/${eventImg.getEvent_img_file_real_name() }"> <br>
										</c:forEach>
									</c:if> 
								</td>
							</tr>
							<tr>
								<th><label for="board_file">파일 첨부</label></th>
								<td>
									<c:forEach var="eventImg" items="${eventImgFileList }">
										<a href="./Upload/admin_event_img/${eventImg.getEvent_img_file_real_name() }" download="${eventImg.getEvent_img_file_name() }"> 
										${eventImg.getEvent_img_file_name() }</a><br>
									</c:forEach> 
								</td>
							</tr>
						</table>
						<section id="commandCell">	
							<input type="button" value="수정" onclick="location.href='EventModifyForm.co?event_num=${param.event_num}&page=${param.page}'">
							<input type="button" value="삭제" onclick="eventDelete()">
<%-- 							<input type="button" value="삭제" onclick="location.href='EventDelete.co?event_num=${param.event_num}&page=${param.page}'"> --%>
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

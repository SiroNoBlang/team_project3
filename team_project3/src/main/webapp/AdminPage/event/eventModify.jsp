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

<script src="AdminPage/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready( function() {
		let date = new Date();
		let year = date.getFullYear();
		let month = date.getMonth() + 1;
		let day = date.getDate();
		
		
		let title = $('input[name=board_title]').val();
		$('input[name=board_title]').attr('value',"["+year+""+month+""+day+" 수정] "+title);
	});
</script>
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
				<a href="javascript:void(0)" onclick="confirmLogout()"><i class="icon ion-log-out" ></i></a>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">이벤트 글 수정</div>
				
					<form action="./NoticeModifyPro.co" name="boardForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="event_num" value="${param.event_num}">
						<input type="hidden" name="page" value="${param.page}">
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
								<td><input type="text" name="board_title" required="required" value="${eventArticle.getEvent_title() }">
							</tr>
							<tr>
								<th><label for="board_nickname">작성자</label></th>
								<td><input type="text" name="board_nickname" value="<%=sNickname %>" readonly="readonly"></td>
							</tr>
							<tr>
								<th><label for="board_date">작성일</label></th>
								<td><input type="text" name="board_date" readonly="readonly" value="${eventArticle.getEvent_write_date() }">
							</tr>
							<tr>
								<th><label for="board_content">내용</label></th>
								<td><textarea id="board_content" name="board_content"  rows="20" cols="100">${eventArticle.getEvent_content() }  </textarea></tr>
							<tr>
								<th><label for="board_file">파일 첨부</label></th>
									<td><input type="file" id="file1" name="board_file0"></td>
								</tr>
						  		<tr>
									<th><label for="board_file">파일 첨부</label></th>
									<td><input type="file" id="file1" name="board_file1"></td>
								</tr>
						</table>
						<section id="commandCell">	
							<input type="submit" value="수정">&nbsp;&nbsp;
							<input type="reset" value="다시쓰기">&nbsp;&nbsp;
							<input type="button" value="취소" onclick="history.back()">
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

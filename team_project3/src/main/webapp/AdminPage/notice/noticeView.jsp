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
<title>관리자페이지 - 커뮤니티 글쓰기</title>
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
		<ul>
			<li class="#"><a href="MemberManagement.co"> <i class="ion-ios-person-outline"></i> <span>멤버관리</span></a></li>
			<li class="#"><a href="ProductConfirm.co"> <i class="icon ion-clipboard"></i> <span>검수현황</span></a></li>
			<li class="has-sub"><a href="#"> <i class="ion-ios-chatboxes-outline"></i> <span>커뮤니티</span>
			</a>
				<ul>
					<li><a href="NoticeList.co">공지사항</a></li>
					<li><a href="EventList.co">이벤트</a></li>
					<li><a href="QusetionList.co">Q&#38;A</a></li>
					<li><a href="CommunityWriteForm.co">글쓰기</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div id="main-content">
		<div id="header">
			<div class="header-left float-left">
				<i id="toggle-left-menu" class="ion-android-menu"></i>
			</div>
			<div class="header-right float-right">
				<i class="icon ion-log-out"></i>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">상세 내용</div>
					<form action="./CommunityBoardWritePro.co" name="boardForm" method="post" enctype="multipart/form-data">
						<table >
							<tr>
								<th>분류</th>
								<td>
									<label><input TYPE='radio' name='communityType' value='notice' checked="checked"/>공지사항</label>
									<label><input TYPE='radio' name='communityType' value='event' />이벤트</label>
								</td>
							</tr>
							<tr>
								<th><label for="board_title">제목</label></th>
								<td><input type="text" name="board_title" required="required" value="${noticeArticle.getAdmin_notice_title() }">
								</td>
							</tr>
							<tr>
								<th><label for="board_nickname">작성자</label></th>
								<td><input type="text" name="board_nickname" value="<%=sNickname %>" readonly="readonly"></td>
							</tr>
							<tr>
								<th><label for="board_nickname">작성일</label></th>
								<td><input type="text" name="board_date" readonly="readonly" value="${noticeArticle.getAdmin_notice_write_date() }">
								</td>
							</tr>
							<tr>
								<th><label for="board_nickname">조회수</label></th>
								<td><input type="text" readonly="readonly" value="${noticeArticle.getAdmin_notice_readcount() }"></td>
							</tr>
							<tr>
								<th><label for="board_content">내용</label></th>

								<td>
									<c:choose> 
										<c:when test="${not empty noticeImgFile }">
											${noticeArticle.getAdmin_notice_content() }
											<img src="./upload/${noticeImgFile.getNotice_img_file_real_name() }"> 
										</c:when> 
									<c:otherwise> 
										<textarea id="board_content" name="board_content"  rows="20" cols="100">
											${noticeArticle.getAdmin_notice_content() }
										</textarea>
									</c:otherwise> 
								</c:choose>  
								</td>
							</tr>
							<tr>
								<th><label for="board_file">파일 첨부</label></th>
								<td><input type="file" name="board_file" multiple="multiple" >
								<c:if test="${not empty noticeImgFile }">
									<a href="./upload/${noticeImgFile.getNotice_img_file_real_name() }" download="${noticeImgFile.getNotice_img_file_name() }"> 
									${noticeImgFile.getNotice_img_file_real_name() }</a>
								</c:if>
								</td>
							</tr>
						</table>
						<br>
						<section id="commandCell">	
							<input type="submit" value="수정을 왜해 처음부터 똑ㅂㅏ로..쓰면 도...">&nbsp;&nbsp;
							<input type="reset" value="삭제못하게할거야 ㅃ..">&nbsp;&nbsp;	
							<input type="button" value="목록으로 돌아가자구요 제발좀..." onclick="history.back()">
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
	<script src="AdminPage/js/jquery-3.6.0.js"></script>
</body>
</html>

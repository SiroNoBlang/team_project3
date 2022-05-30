<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

<script src="AdminPage/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready( function() {
		$("#qnaTypeId").change(function(){
			$("#qnaTitleId").val("["+$(this).val()+"] ");
		});
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
				<jsp:include page="/AdminPage/menu/topMenu.jsp"/>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">커뮤니티 글쓰기</div>
					<form action="./CommunityBoardWritePro.co" name="boardForm" method="post" enctype="multipart/form-data">
						<table >
							<tr>
								<th>분류</th>
								<td>
									<label><input type='radio' name='communityType' value='notice' />공지사항</label>
									<label><input type='radio' name='communityType' value='event' />이벤트</label>
								</td>
							</tr>
							<tr>
								<th><label for="board_title">제목</label></th>
								<td><input type="text" name="board_title" required="required"></td>
							</tr>
							<tr>
								<th><label for="board_nickname">작성자</label></th>
								<td><input type="text" name="board_nickname" value="<%=sNickname %>" readonly="readonly"></td>
							</tr>
							<tr>
								<th><label for="board_content">내용</label></th>
								<td> <textarea id="board_content" name="board_content"  rows="20" cols="100"></textarea> </td>
							</tr>
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
							<input type="submit" value="작성완료">&nbsp;&nbsp;
							<input type="reset" value="다시쓰기">&nbsp;&nbsp;	
							<input type="button" value="취소" onclick="history.back()">
						</section>
					</form>
				 </div>
			</div>
		</div>
		
		
		
			<div id="page-container">
			<div class="card">
				<div class="title">QnA 글쓰기</div>
					<form action="./QnaWritePro.co" name="boardForm" method="post">
						<table >
							<tr>
								<th><label for="qna_type">문의유형</label></th>
								<td>
									<select id="qnaTypeId" name="qna_type">
									  <option value="상품문의">상품문의</option>
									  <option value="배송문의">배송문의</option>
									  <option value="교환및반품문의">교환및반품문의</option>
									  <option value="주문변경및취소문의">주문변경및취소문의</option>
									  <option value="입금및결제문의">입금및결제문의</option>
									  <option value="기타문의">기타문의</option>
									</select>
								</td>
							</tr>
							<tr>
								<th><label for="qna_title">제목</label></th>
								<td><input type="text" id="qnaTitleId" name="qna_title" required="required"></td>
							</tr>
							<tr>
								<th><label for="qna_nickname">작성자</label></th>
								<td><input type="text" name="qna_nickname" value="<%=sNickname %>" readonly="readonly"></td>
							</tr>
							<tr>
								<th><label for="qna_content">내용</label></th>
								<td> <textarea id="board_content" name="qna_content"  rows="20" cols="100"></textarea> </td>
							</tr>
						</table>
						<section id="commandCell">	
							<input type="submit" value="작성완료">&nbsp;&nbsp;
							<input type="reset" value="다시쓰기">&nbsp;&nbsp;	
							<input type="button" value="취소" onclick="history.back()">
						</section>
					</form>
				 </div>
			</div>
		
	<span id="show-lable">Hello</span>
	<!-- partial -->
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js'></script>
	<script src="AdminPage/js/script.js"></script>
</body>
</html>

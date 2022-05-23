<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String code = request.getAttribute("member_code").toString();
MemberBean member = (MemberBean) request.getAttribute("memberDetail");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.js"></script>
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
</head>
<body>
	<div id="logo">
		<span class="big-logo">Admin</span> <span class="small-logo">&nbsp;A</span>
	</div>
	<div id="left-menu">
		<ul>
			<li class="#"><a href="MemberManagement.co"><i class="ion-ios-person-outline"></i> <span>멤버관리</span></a></li>
			<li class="#"><a href="ProductConfirm.co"><i class="icon ion-clipboard"></i> <span>검수현황</span></a></li>
			<li class="has-sub"><a href="#"><i class="ion-ios-chatboxes-outline"></i> <span>커뮤니티</span>
			</a>
				<ul>
					<li><a href="NoticeList.co">공지사항</a></li>
					<li><a href="EventList.co">이벤트</a></li>
					<li><a href="QusetionList.co">Q&#38;A</a></li>
					<li><a href="CommunityWriteForm.co">글쓰기</a></li>
				</ul></li>
		</ul>
	</div>
	<div id="main-content">
		<div id="header">
			<div class="header-left float-left">
				<i id="toggle-left-menu" class="ion-android-menu"></i>
			</div>
			<div class="header-right float-right">
				<i class="icon ion-log-out" onclick=""></i>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">가입자 상세 정보</div>
				<form action="MemberUpdate.co" name="boardForm" method="post">
				<input type="hidden" name="member_code" value="<%=code %>">
					<table>
						<tr>
							<th><label for="board_title">멤버넘버</label></th>
							<td><%=member.getMember_num()%></td>
						</tr>
						<tr>
							<th><label for="board_title">회원 등급</label></th>
							<td><%=member.getGrade_name()%></td>
						</tr>
						<tr>
							<th><label for="board_title">멤버코드</label></th>
							<td><%=code %></td>
						</tr>
						<tr>
							<th><label for="board_title">닉네임</label></th>
							<td><%=member.getMember_nickname()%></td>
						</tr>
						<tr>
							<th><label for="board_title">아이디</label></th>
							<td><%=member.getMember_id()%></td>
						</tr>
						<tr>
							<th><label for="board_title">패스워드</label></th>
							<td><%=member.getMember_passwd()%></td>
						</tr>
						<tr>
							<th><label for="board_title">이메일</label></th>
							<td><%=member.getMember_email()%></td>
						</tr>
						<tr>
							<th><label for="board_title">이름</label></th>
							<td><%=member.getMember_info_name()%></td>
						</tr>
						<tr>
							<th><label for="board_title">성별</label></th>
							<td><%=member.getMember_info_gender()%>
						</tr>
						<tr>
							<th><label for="board_title">전화번호</label></th>
							<td><%=member.getMember_info_phone()%></td>
						</tr>
						<tr>
							<th><label for="board_title">나이대</label></th>
							<td><%=member.getMember_info_age()%></td>
						</tr>
						<tr>
							<th><label for="board_title">가입날짜</label></th>
							<td><%=member.getMember_service_log_join_date()%></td>
						</tr>
						<tr>
							<th><label for="board_title">패스워드 수정날짜</label></th>
							<td><%=member.getMember_service_log_passwd_change_date()%></td>
						</tr>
						<tr>
							<th><label for="board_title">우편번호</label></th>
							<td><%=member.getMember_info_post_code()%></td>
						</tr>
						<tr>
							<th><label for="board_title">주소</label></th>
							<td><%=member.getMember_info_address()%></td>
						</tr>
						<tr>
							<th><label for="board_title">상세주소</label></th>
							<td><%=member.getMember_info_address_detail()%></td>
						</tr>
						<tr>
							<th><label for="board_title">배송지우편번호</label></th>
							<td><%=member.getMember_info_ship_post_code()%></td>
						</tr>
						<tr>
							<th><label for="board_title">배송지주소</label></th>
							<td><%=member.getMember_info_ship_address()%></td>
						</tr>
						<tr>
							<th><label for="board_title">배송지상세주소</label></th>
							<td><%=member.getMember_info_ship_address_detail()%></td>
						</tr>
						<tr>
							<th><label for="board_title">누적금액</label></th>
							<td><%=member.getMember_info_detail_acc_money()%></td>
						</tr>
						<tr>
							<th><label for="board_title">스타일</label>스타일</th>
							<td><%=member.getMember_info_detail_like_style()%></td>
						</tr>
						<tr>
							<th><label for="board_title">브랜드</label>브랜드</th>
							<td><%=member.getMember_info_detail_like_brand()%></td>
						</tr>
						<tr>
							<th><label for="board_title">관심품목</label>관심품목</th>
							<td><%=member.getMember_info_detail_like_category()%></td>
						</tr>
						<tr>
							<th><label for="board_title">누적 포인트</label></th>
							<td><%=member.getMember_info_detail_point()%></td>
						</tr>
						<tr>
							<th><label for="board_title">회원 상태</label></th>
							<td>
							<select id="member_status" name="member_status">
								<option value="정상" <%if (member.getMember_service_log_status().equals("정상")) {%>selected="selected" <%}%>>정상</option>
								<option value="정지" <%if (member.getMember_service_log_status().equals("정지")) {%>selected="selected" <%}%>>정지</option>
								<option value="탈퇴" <%if (member.getMember_service_log_status().equals("탈퇴")) {%>selected="selected" <%}%>>탈퇴</option>
							</select>
							<div class="reason_content">
								<select id="reason" name="reason">
									<option value="0" <%if (member.getReason_num().equals("0")) {%>selected="selected" <%}%>>정상</option>
									<option value="1" <%if (member.getReason_num().equals("1")) {%>selected="selected" <%}%>>정지 이유 1</option>
									<option value="2" <%if (member.getReason_num().equals("2")) {%>selected="selected" <%}%>>정지 이유 2</option>
								</select>
							</div>
							</td>
						</tr>
						<tr>
							<th><label for="board_title">회원가입 날짜</label></th>
							<td><%=member.getMember_service_log_join_date()%></td>
						</tr>
						<tr>
							<th><label for="board_title">비밀번호변경 날짜</label></th>
							<td><%=member.getMember_service_log_passwd_change_date()%></td>
						</tr>
						<tr>
							<th><label for="board_title">로그인 날짜</label></th>
							<td><%=member.getMember_service_log_login_date()%></td>
						</tr>
						<tr>
							<th><label for="board_title">구매횟수</label></th>
							<td><%=member.getMember_service_log_order_count()%></td>
						</tr>
					</table>
					<section id="commandCell">
						<input type="submit" value="수정">
						<%if (member.getMember_service_log_status().equals("탈퇴")) {%>
						<input type="button" value="회원탈퇴" onclick="">
						<%} %>
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
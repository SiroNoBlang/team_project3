<%@page import="java.io.File"%>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String code = (String) session.getAttribute("sCode");
String nickname = (String) session.getAttribute("sNickname");
MemberBean member = (MemberBean) request.getAttribute("memberDetail");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mypage</title>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function confirmLogout() {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href = "./Logout.ma";
		}
	}
	function findAddr() { //주소 찾기
		new daum.Postcode(
				{
					oncomplete : function(data) {

						console.log(data);

						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var jibunAddr = data.jibunAddress; // 지번 주소 변수
						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('member_info_post_code').value = data.zonecode;
						if (roadAddr !== '') {
							document.getElementById("member_info_address").value = roadAddr;
						} else if (jibunAddr !== '') {
							document
									.getElementById("member_info_address_detail").value = jibunAddr;
						}
					}
				}).open();
	}

	function findAddr1() { //배송지 주소 찾기
		new daum.Postcode(
				{
					oncomplete : function(data) {

						console.log(data);

						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var jibunAddr = data.jibunAddress; // 지번 주소 변수
						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('member_info_ship_post_code').value = data.zonecode;
						if (roadAddr !== '') {
							document.getElementById("member_info_ship_address").value = roadAddr;
						} else if (jibunAddr !== '') {
							document
									.getElementById("member_info_ship_address_detail").value = jibunAddr;
						}
					}
				}).open();
	}

	function onPassModify() { //비밀번호 수정하기 기능 활성화
		var con = document.getElementById("member_passwd");
		if (con.style.display == 'none') {
			con.style.display = 'block';
		} else {
			con.style.display = 'none';
		}
	}

	function changeImg(obj) { //이미지 수정
		let reader = new FileReader();
		if (!obj.files.length) {
			return;
		}
		document.getElementById("image_section").style.display = "none";
		var a = document.getElementById("preImg");
		if (a != null)
			a.remove();
		for (let i = 0; i < obj.files.length; i++) {
			reader.readAsDataURL(obj.files[i]);
			reader.onload = function(e) {
				let img = $('<img id="preImg" width=300,height=300 />');
				$(img).attr('src', e.target.result);
				$('#previewDiv').append(img);
			}
		}

	}
</script>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="MainPage/css/util.css">
<link rel="stylesheet" type="text/css" href="MainPage/css/main.css">
<!--===============================================================================================-->
</head>
	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<!-- Topbar -->
			<div class="top-bar">
				<div class="content-topbar flex-sb-m h-full container">
					<div class="left-top-bar">Free shipping for standard order
						over $100</div>

					<div class="right-top-bar flex-w h-full">
						<a href="#" class="flex-c-m trans-04 p-lr-25"> Help & FAQs </a> <a
							href="Mypage.ma?member_code=${memberDetail.member_code }"
							class="flex-c-m trans-04 p-lr-25">
							${memberDetail.member_nickname }님 마이페이지 </a> <a
							href="javascript:void(0)" onclick="confirmLogout()"
							class="flex-c-m trans-04 p-lr-25"> 로그아웃 </a>
					</div>
				</div>
			</div>

			<div class="wrap-menu-desktop how-shadow1">
				<nav class="limiter-menu-desktop container">

					<!-- Logo desktop -->
					<a href="#" class="logo"> <img
						src="MainPage/images/icons/logo-01.png" alt="IMG-LOGO">
					</a>

					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
							<li><a href="index.jsp">Home</a>
								<ul class="sub-menu">
									<li><a href="index.jsp">Homepage 1</a></li>
									<li><a href="home_02.jsp">Homepage 2</a></li>
									<li><a href="home_03.jsp">Homepage 3</a></li>
								</ul></li>

							<li><a href="product.jsp">Shop</a></li>

							<li class="label1" data-label1="hot"><a
								href="shoping-cart.jsp">Features</a></li>

							<li><a href="blog.jsp">Blog</a></li>

							<li class="active-menu"><a href="about.jsp">About</a></li>

							<li><a href="contact.jsp">Contact</a></li>
						</ul>
					</div>

					<!-- Icon header -->
					<div class="wrap-icon-header flex-w flex-r-m">
						<div
							class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
							<i class="zmdi zmdi-search"></i>
						</div>

						<div
							class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart"
							data-notify="2">
							<i class="zmdi zmdi-shopping-cart"></i>
						</div>

						<a href="#"
							class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti"
							data-notify="0"> <i class="zmdi zmdi-favorite-outline"></i>
						</a>
					</div>
				</nav>
			</div>
		</div>

		<!-- Header Mobile -->
		<div class="wrap-header-mobile">
			<!-- Logo moblie -->
			<div class="logo-mobile">
				<a href="index.jsp"><img src="MainPage/images/icons/logo-01.png"
					alt="IMG-LOGO"></a>
			</div>

			<!-- Icon header -->
			<div class="wrap-icon-header flex-w flex-r-m m-r-15">
				<div
					class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 js-show-modal-search">
					<i class="zmdi zmdi-search"></i>
				</div>

				<div
					class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart"
					data-notify="2">
					<i class="zmdi zmdi-shopping-cart"></i>
				</div>

				<a href="#"
					class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti"
					data-notify="0"> <i class="zmdi zmdi-favorite-outline"></i>
				</a>
			</div>

			<!-- Button show menu -->
			<div class="btn-show-menu-mobile hamburger hamburger--squeeze">
				<span class="hamburger-box"> <span class="hamburger-inner"></span>
				</span>
			</div>
		</div>


		<!-- Menu Mobile -->
		<div class="menu-mobile">
			<ul class="topbar-mobile">
				<li>
					<div class="left-top-bar">Free shipping for standard order
						over $100</div>
				</li>

				<li>
					<div class="right-top-bar flex-w h-full">
						<a href="#" class="flex-c-m p-lr-10 trans-04"> Help & FAQs </a> <a
							href="#" class="flex-c-m p-lr-10 trans-04"> My Account </a> <a
							href="#" class="flex-c-m p-lr-10 trans-04"> EN </a> <a href="#"
							class="flex-c-m p-lr-10 trans-04"> USD </a>
					</div>
				</li>
			</ul>

			<ul class="main-menu-m">
				<li><a href="index.jsp">Home</a>
					<ul class="sub-menu-m">
						<li><a href="index.jsp">Homepage 1</a></li>
						<li><a href="home_02.jsp">Homepage 2</a></li>
						<li><a href="home_03.jsp">Homepage 3</a></li>
					</ul> <span class="arrow-main-menu-m"> <i
						class="fa fa-angle-right" aria-hidden="true"></i>
				</span></li>

				<li><a href="product.jsp">Shop</a></li>

				<li><a href="shoping-cart.jsp" class="label1 rs1"
					data-label1="hot">Features</a></li>

				<li><a href="blog.jsp">Blog</a></li>

				<li><a href="about.jsp">About</a></li>

				<li><a href="contact.jsp">Contact</a></li>
			</ul>
		</div>

		<!-- Modal Search -->
		<div
			class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
			<div class="container-search-header">
				<button
					class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
					<img src="MainPage/images/icons/icon-close2.png" alt="CLOSE">
				</button>

				<form class="wrap-search-header flex-w p-l-15">
					<button class="flex-c-m trans-04">
						<i class="zmdi zmdi-search"></i>
					</button>
					<input class="plh3" type="text" name="search"
						placeholder="Search...">
				</form>
			</div>
		</div>
	</header>

	<!-- Cart -->
	<div class="wrap-header-cart js-panel-cart">
		<div class="s-full js-hide-cart"></div>

		<div class="header-cart flex-col-l p-l-65 p-r-25">
			<div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2"> Your Cart </span>

				<div
					class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
					<i class="zmdi zmdi-close"></i>
				</div>
			</div>

			<div class="header-cart-content flex-w js-pscroll">
				<ul class="header-cart-wrapitem w-full">
					<li class="header-cart-item flex-w flex-t m-b-12">
						<div class="header-cart-item-img">
							<img src="MainPage/images/item-cart-01.jpg" alt="IMG">
						</div>

						<div class="header-cart-item-txt p-t-8">
							<a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
								White Shirt Pleat </a> <span class="header-cart-item-info"> 1
								x $19.00 </span>
						</div>
					</li>

					<li class="header-cart-item flex-w flex-t m-b-12">
						<div class="header-cart-item-img">
							<img src="MainPage/images/item-cart-02.jpg" alt="IMG">
						</div>

						<div class="header-cart-item-txt p-t-8">
							<a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
								Converse All Star </a> <span class="header-cart-item-info"> 1
								x $39.00 </span>
						</div>
					</li>

					<li class="header-cart-item flex-w flex-t m-b-12">
						<div class="header-cart-item-img">
							<img src="MainPage/images/item-cart-03.jpg" alt="IMG">
						</div>

						<div class="header-cart-item-txt p-t-8">
							<a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
								Nixon Porter Leather </a> <span class="header-cart-item-info">
								1 x $17.00 </span>
						</div>
					</li>
				</ul>

				<div class="w-full">
					<div class="header-cart-total w-full p-tb-40">Total: $75.00</div>

					<div class="header-cart-buttons flex-w w-full">
						<a href="shoping_cart.jsp"
							class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
							View Cart </a> <a href="shoping_cart.jsp"
							class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
							Check Out </a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Title page -->
	<section class="bg-img1 txt-center p-lr-15 p-tb-92"
		style="background-image: url('MainPage/images/bg-01.jpg');">
		<h2 class="ltext-105 cl0 txt-center">MyPage</h2>
	</section>

	<!-- Content page -->
				<table border="1">
					<tr>
						<td><a href="">내가 구매한 목록</a></td>
						<td><a href="LikeList.ma?member_code=${memberDetail.member_code }">찜한 목록</a></td>
						<td><a href="SellList.ma?member_code=${memberDetail.member_code }">판매 목록</a></td>
					</tr>
				</table>

				<form action="ProfileImgUpdate.ma" method="post"
					enctype="multipart/form-data">
					<table border="1">
						<tr>
							<td><input type="hidden" name="member_code" id="member_code"
								value="${memberDetail.member_code }"></td>
						</tr>
						<tr>
							<div id="previewDiv">
								<img id="image_section" alt="" width="300" height="300"
									src="Upload/mypage_img/${memberDetail.member_info_mypage_real_img_name }">
							</div>
						</tr>
						<tr>
							<input type="file" id="member_info_mypage_img_name"
								name="member_info_mypage_img_name" value=""
								onchange="changeImg(this)">
						</tr>
					</table>
					<button type="submit">수정</button>
				</form>

				<form action="Modify_Member.ma" method="post">
					<table border="1">
						<tr>
							<td><input type="hidden" name="member_code" id="member_code"
								value="${memberDetail.member_code }"></td>
						</tr>
						<tr>
							<th>회원 등급</th>
							<td colspan="2"><input type="text" name="grade_name"
								id="grade_name" value="${memberDetail.grade_name }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td colspan="2"><input type="text" name="member_nickname"
								id="member_nickname" value="${memberDetail.member_nickname }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<th>아이디</th>
							<td colspan="2"><input type="text" name="member_id"
								id="member_id" value="${memberDetail.member_id }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<th>패스워드</th>
							<td colspan="2"><a href="javascript:onPassModify();">패스워드
									수정하기</a> <input type="password" name="member_passwd"
								id="member_passwd" value="${memberDetail.member_passwd }"
								style="display: none;"></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td colspan="2"><input type="text" name="member_email1"
								id="member_email1" value="${memberDetail.member_email }"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td colspan="2"><input type="text" name="member_info_name"
								id="member_info_name" value="${memberDetail.member_info_name }"></td>
						</tr>
						<tr>
							<th>성별</th>
							<td colspan="2"><select id="member_info_gender"
								name="member_info_gender">
									<option value="male"
										<c:if test="${memberDetail.member_info_gender eq 'male' }" >selected</c:if>>남자</option>
									<option value="female"
										<c:if test="${memberDetail.member_info_gender eq 'female' }">selected</c:if>>여자</option>
							</select></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td colspan="2"><input type="text" name="member_info_phone"
								id="member_info_phone"
								value="${memberDetail.member_info_phone }"></td>
						</tr>
						<tr>
							<th>나이대</th>
							<td colspan="2"><select id="member_info_age"
								name="member_info_age">
									<option value="19세이하"
										<c:if test="${memberDetail.member_info_age eq '19세이하' }" >selected</c:if>>19세이하</option>
									<option value="20~29"
										<c:if test="${memberDetail.member_info_age eq '20~29' }" >selected</c:if>>20~29</option>
									<option value="30~39"
										<c:if test="${memberDetail.member_info_age eq '30~39' }" >selected</c:if>>30~39</option>
									<option value="40~49"
										<c:if test="${memberDetail.member_info_age eq '40~49' }" >selected</c:if>>40~49</option>
									<option value="50~59"
										<c:if test="${memberDetail.member_info_age eq '50~59' }" >selected</c:if>>50~59</option>
									<option value="60~69"
										<c:if test="${memberDetail.member_info_age eq '60~69' }" >selected</c:if>>60~69</option>
									<option value="70대이상"
										<c:if test="${memberDetail.member_info_age eq '70대이상' }" >selected</c:if>>70대이상</option>
							</select></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td colspan="2"><input type="text"
								name="member_info_post_code" id="member_info_post_code"
								value="${memberDetail.member_info_post_code }"
								readonly="readonly"> <input type="button"
								onclick="findAddr()" value="우편번호 찾기"></td>
						</tr>
						<tr>
							<th>주소</th>
							<td colspan="2"><input type="text"
								name="member_info_address" id="member_info_address"
								value="${memberDetail.member_info_address }" readonly="readonly"></td>
						</tr>
						<tr>
							<th>상세주소</th>
							<td colspan="2"><input type="text"
								name="member_info_address_detail"
								id="member_info_address_detail"
								value="${memberDetail.member_info_address_detail }"></td>
						</tr>
						<tr>
							<th>배송지우편번호</th>
							<td colspan="2"><input type="text"
								name="member_info_ship_post_code"
								id="member_info_ship_post_code"
								value="${memberDetail.member_info_ship_post_code }"
								readonly="readonly"> <input type="button"
								onclick="findAddr1()" value="우편번호 찾기"></td>
						</tr>
						<tr>
							<th>배송지주소</th>
							<td colspan="2"><input type="text"
								name="member_info_ship_address" id="member_info_ship_address"
								value="${memberDetail.member_info_ship_address }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<th>배송지상세주소</th>
							<td colspan="2"><input type="text"
								name="member_info_ship_address_detail"
								id="member_info_ship_address_detail"
								value="${memberDetail.member_info_ship_address_detail }"></td>
						</tr>
						<tr>
							<th>누적금액</th>
							<td colspan="2"><input type="text"
								name="member_info_detail_acc_money"
								id="member_info_detail_acc_money"
								value="${memberDetail.member_info_detail_acc_money }"
								readonly="readonly"></td>
						</tr>
						
						<tr>
							<th>누적 포인트</th>
							<td colspan="2"><input type="text"
								name="member_info_detail_point" id="member_info_detail_point"
								value="${memberDetail.member_info_detail_point }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<th>회원 상태(정상,정지,탈퇴)</th>
							<td colspan="2"><input type="text"
								name="member_service_log_status" id="member_service_log_status"
								value="${memberDetail.member_service_log_status }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<th>회원가입 날짜</th>
							<td colspan="2"><input type="text"
								name="member_service_log_join_date"
								id="member_service_log_join_date"
								value="${memberDetail.member_service_log_join_date }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<th>구매횟수</th>
							<td colspan="2"><input type="text"
								name="member_service_log_order_count"
								id="member_service_log_order_count"
								value="${memberDetail.member_service_log_order_count }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<td colspan="3"><input type="submit" value="수정"></td>
						</tr>

					</table>
					<table border="1">
						<tr>
							<th>스타일</th>
							<td><label for="member_info_detail_like_style"></label> <input
								type="checkbox" name="style" value="귀여움"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_style, '귀여움')}" >checked</c:if>>귀여움
								<input type="checkbox" name="style" value="청순"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_style, '청순') }" >checked</c:if>>청순
								<input type="checkbox" name="style" value="캐주얼"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_style, '캐주얼') }" >checked</c:if>>캐주얼
							</td>
						</tr>
						<tr>
							<th>브랜드</th>
							<td><label for="member_info_detail_like_brand"></label> <input
								type="checkbox" name="brand" value="나이키"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_brand, '나이키')}" >checked</c:if>>나이키
								<input type="checkbox" name="brand" value="아디다스"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_brand, '아디다스')}" >checked</c:if>>아디다스
								<input type="checkbox" name="brand" value="아페쎄"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_brand, '아페쎄')}" >checked</c:if>>아페쎄
								<input type="checkbox" name="brand" value="칼하트"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_brand, '칼하트')}" >checked</c:if>>칼하트
								<input type="checkbox" name="brand" value="샤넬"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_brand, '샤넬')}" >checked</c:if>>샤넬
							</td>
						</tr>
						<tr>
							<th>관심품목</th>
							<td><label for="member_info_detail_like_category"></label>
								<input type="checkbox" name="category" value="상의"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_category, '상의')}" >checked</c:if>>상의
								<input type="checkbox" name="category" value="하의"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_category, '하의')}" >checked</c:if>>하의
								<input type="checkbox" name="category" value="신발"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_category, '신발')}" >checked</c:if>>신발
								<input type="checkbox" name="category" value="잡화"
								<c:if test="${fn:contains(memberDetail.member_info_detail_like_category, '잡화')}" >checked</c:if>>잡화
							</td>
						</tr>
					
					</table>

				</form>

	<!-- Footer -->
	<footer class="bg3 p-t-75 p-b-32">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">Categories</h4>

					<ul>
						<li class="p-b-10"><a href="#"
							class="stext-107 cl7 hov-cl1 trans-04"> Women </a></li>

						<li class="p-b-10"><a href="#"
							class="stext-107 cl7 hov-cl1 trans-04"> Men </a></li>

						<li class="p-b-10"><a href="#"
							class="stext-107 cl7 hov-cl1 trans-04"> Shoes </a></li>

						<li class="p-b-10"><a href="#"
							class="stext-107 cl7 hov-cl1 trans-04"> Watches </a></li>
					</ul>
				</div>

				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">Help</h4>

					<ul>
						<li class="p-b-10"><a href="#"
							class="stext-107 cl7 hov-cl1 trans-04"> Track Order </a></li>

						<li class="p-b-10"><a href="#"
							class="stext-107 cl7 hov-cl1 trans-04"> Returns </a></li>

						<li class="p-b-10"><a href="#"
							class="stext-107 cl7 hov-cl1 trans-04"> Shipping </a></li>

						<li class="p-b-10"><a href="#"
							class="stext-107 cl7 hov-cl1 trans-04"> FAQs </a></li>
					</ul>
				</div>

				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">GET IN TOUCH</h4>

					<p class="stext-107 cl7 size-201">Any questions? Let us know in
						store at 8th floor, 379 Hudson St, New York, NY 10018 or call us
						on (+1) 96 716 6879</p>

					<div class="p-t-27">
						<a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16"> <i
							class="fa fa-facebook"></i>
						</a> <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16"> <i
							class="fa fa-instagram"></i>
						</a> <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16"> <i
							class="fa fa-pinterest-p"></i>
						</a>
					</div>
				</div>

				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">Newsletter</h4>

					<form>
						<div class="wrap-input1 w-full p-b-4">
							<input class="input1 bg-none plh1 stext-107 cl7" type="text"
								name="email" placeholder="email@example.com">
							<div class="focus-input1 trans-04"></div>
						</div>

						<div class="p-t-18">
							<button
								class="flex-c-m stext-101 cl0 size-103 bg1 bor1 hov-btn2 p-lr-15 trans-04">
								Subscribe</button>
						</div>
					</form>
				</div>
			</div>

			<div class="p-t-40">
				<div class="flex-c-m flex-w p-b-18">
					<a href="#" class="m-all-1"> <img
						src="MainPage/images/icons/icon-pay-01.png" alt="ICON-PAY">
					</a> <a href="#" class="m-all-1"> <img
						src="MainPage/images/icons/icon-pay-02.png" alt="ICON-PAY">
					</a> <a href="#" class="m-all-1"> <img
						src="MainPage/images/icons/icon-pay-03.png" alt="ICON-PAY">
					</a> <a href="#" class="m-all-1"> <img
						src="MainPage/images/icons/icon-pay-04.png" alt="ICON-PAY">
					</a> <a href="#" class="m-all-1"> <img
						src="MainPage/images/icons/icon-pay-05.png" alt="ICON-PAY">
					</a>
				</div>

				<p class="stext-107 cl6 txt-center">
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;
					<script>
						document.write(new Date().getFullYear());
					</script>
					All rights reserved | Made with <i class="fa fa-heart-o"
						aria-hidden="true"></i> by <a href="https://colorlib.com"
						target="_blank">Colorlib</a> &amp; distributed by <a
						href="https://themewagon.com" target="_blank">ThemeWagon</a>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->

				</p>
			</div>
		</div>
	</footer>


	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top"> <i
			class="zmdi zmdi-chevron-up"></i>
		</span>
	</div>

	<!--===============================================================================================-->
	<script src="MainPage/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="MainPage/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="MainPage/vendor/bootstrap/js/popper.js"></script>
	<script src="MainPage/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="MainPage/vendor/select2/select2.min.js"></script>
	<script>
		$(".js-select2").each(function() {
			$(this).select2({
				minimumResultsForSearch : 20,
				dropdownParent : $(this).next('.dropDownSelect2')
			});
		})
	</script>
	<!--===============================================================================================-->
	<script
		src="MainPage/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function() {
			$(this).css('position', 'relative');
			$(this).css('overflow', 'hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed : 1,
				scrollingThreshold : 1000,
				wheelPropagation : false,
			});

			$(window).on('resize', function() {
				ps.update();
			})
		});
	</script>
	<!--===============================================================================================-->
	<script src="MainPage/js/main.js"></script>

</body>
</html>
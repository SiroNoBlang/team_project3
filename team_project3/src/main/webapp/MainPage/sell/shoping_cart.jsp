<%@page import="vo.SellerProductDTO"%>
<%@page import="vo.MemberBean"%>
<%@page import="vo.SellerDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
String member_nickname = (String) session.getAttribute("sNickname");
String sell_member_code = (String) session.getAttribute("sCode");
SellerProductDTO sellerDTO = (SellerProductDTO) request.getAttribute("sellerDTO");
MemberBean memberbean = (MemberBean) request.getAttribute("memberBean");

// double member_grade=0;
// if(memberbean.getGrade_name().equals("Basic")){
// 	 member_grade = 0.98;
// }else if(memberbean.getGrade_name().equals("Bronze")){
// 	 member_grade = 0.97;	
// }else if(memberbean.getGrade_name().equals("silver")){
// 	 member_grade = 0.96;	
// }else if(memberbean.getGrade_name().equals("Gold")){
// 	 member_grade = 0.95;
// }else if(memberbean.getGrade_name().equals("Vip")){
// 	 member_grade = 0.94;
// }else if(memberbean.getGrade_name().equals("VVip")){
// 	 member_grade = 0.93;
// }else if(memberbean.getGrade_name().equals("VVVip")){
// 	 member_grade = 0.92;
// }else {
// 	 member_grade=0.9;
// }     //rate 추가완료

// double charge = (double)(sellerDTO.getSell_price() * 0.2); //검수비 :5퍼센트
// int realCharge=(int)Math.round(charge);   //반올림해서 보여줄 값

// double price = (double) (sellerDTO.getSell_price()+realCharge+3000 )*member_grade;     //최종 판매가격= 판매가격+배송비(3000원)+검수비(판매가격의 5퍼센트)-포인트-등급별할인율
// int realPrice=(int)Math.round(price); 

// int member_point = memberbean.getMember_info_detail_point();  //자바스크립트에서 쓸라고 만듦
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Shoping Cart</title>

<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	var checkAddress = false;

	function AddressDaumPostcode() { //----도로명주소-----
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수
				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}
				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					//                     if(extraAddr !== ''){
					//                         extraAddr = ' (' + extraAddr + ')';
					//                     }
					//                     // 조합된 참고항목을 해당 필드에 넣는다.
					//                     document.getElementById("sample6_extraAddress").value = extraAddr;

					//                 } else {
					//                     document.getElementById("sample6_extraAddress").value = '';
					//                 }
					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById("postcode").value = data.zonecode;
					document.getElementById("address1").value = addr;
					// 커서를 상세주소 필드로 이동한다.
					document.getElementById("address2").focus();
					checkAddress = true;
				}
			}
		}).open();
	}
</script>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="MainPage/images/icons/favicon.png" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="MainPage/css/util.css">
<link rel="stylesheet" type="text/css" href="MainPage/css/main.css">
<!--===============================================================================================-->
</head>
<body class="animsition">

	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<!-- Topbar -->
			<div class="wrap-menu-desktop how-shadow1">
				<nav class="limiter-menu-desktop container">

					<!-- Logo desktop -->
					<a href="#" class="logo"> <img
						src="MainPage/images/icons/logo-01.png" alt="IMG-LOGO">
					</a>

					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
							<li><a href="MainPage.pr">Home</a></li>

							<li><a href="Product.pr">Shop</a></li>

							<li><a href="SellForm.pr">Sell</a></li>

							<li><a href="CommunityNotice.ma">Community</a></li>

							<li><a href="Contact.pr">Contact</a></li>
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
				<a href="MainPage.pr"><img
					src="MainPage/images/icons/logo-01.png" alt="IMG-LOGO"></a>
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
			<ul class="main-menu">
				<li><a href="MainPage.pr">Home</a></li>

				<li><a href="Product.pr">Shop</a></li>

				<li class="label1" data-label1="hot"><a
					href="shoping-cart.html">Features</a></li>
				<li><a href="SellForm.pr">Sell</a></li>
				<li><a href="CommunityNotice.ma">Community</a></li>

				<li><a href="contact.html">Contact</a></li>
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
							<img src="images/item-cart-01.jpg" alt="IMG">
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
						<a href="shoping-cart.html"
							class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
							View Cart </a> <a href="shoping-cart.html"
							class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
							Check Out </a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="index.html" class="stext-109 cl8 hov-cl1 trans-04"> Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a> <span class="stext-109 cl4"> Shoping Cart </span>
		</div>
	</div>


	<!-- Shoping Cart -->
	<form action="SucceedProductAction.pr" class="bg0 p-t-75 p-b-85">

		<!-- 		<form action="SucceedLoding.pr" class="bg0 p-t-75 p-b-85">   -->
		<input type="hidden" value="member_nickname" name="member_nickname">
		<input type="hidden" value="${sellerDTO.sell_num}" name="sell_num">
		<input type="hidden"value="${sellerDTO.sell_price}"name="member_info_detail_acc_money">
		<input type="hidden" value="${memberBean.discount_rate }">
		<input type="hidden"value="${sCode}" name="member_code"> 
		<input type="hidden"value="${sellerDTO.sell_price}" name="sell_price">
		
		<!-- 	<input type="hidden" value="member_nickname" name="member_nickname"> -->
		<%-- 	<input type="hidden" value="<%=sellerDTO.getSell_num() %>" name="sell_num"> --%>
		<%-- 	<input type="hidden" value="<%=realPrice %>" name="member_info_detail_acc_money" > --%>
		<%-- 	<input type="hidden" value="<%=sell_member_code %>" name="member_code"> --%>
		<%-- 	<input type="hidden" value="<%=sellerDTO.getSell_price() %>" name="sell_price"> --%>
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">

					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">

							<h4 style="color: lightgrey;">상품 정보</h4>
							<table class="table-shopping-cart" style="border: none;">
								<tr>
									<td rowspan="4" width="210" height="290"><img width="200"
										height="288"
										src="./Upload/sell_img/${sellerDTO.sell_img_real_name}"
										alt="IMG"></td>
								</tr>
								<tr>
									<td style="text-align: center; border-bottom: none;"><h4>Title</h4></td>
									<td style="text-align: left;"><h4>${sellerDTO.sell_title}</h4></td>
								</tr>
								<tr>
									<td style="text-align: center; border-bottom: none;"><h4>Size</h4></td>
									<td style="text-align: left;"><h4>${sellerDTO.sell_size}</h4></td>
								</tr>
								<tr>
									<td style="text-align: center; border-bottom: none;"><h4>Price</h4></td>
									<td style="text-align: left;"><h4>${sellerDTO.sell_price *10000}</h4></td>
								</tr>
								<!-- 									style="" -->

								<%-- 										제목:<td>${sellerDTO.sell_title}<br> --%>
								<%-- 										사이즈:${sellerDTO.sell_size}<br> --%>
								<%-- 										가격:${sellerDTO.sell_price}</td> --%>



							</table>
						</div>
						<div class="wrap-table-shopping-cart">
							<%-- 								<%if(memberbean.getMember_info_address().equals("")||memberbean.getMember_info_address_detail().equals("")||memberbean.getMember_info_postcode.equals("")){ %> --%>
							<table class="table-shopping-cart">
								<tr style="border: none;">
									<td>
										<h4 style="color: lightgrey;">배송 정보</h4>
									</td>
									<td colspan="2"></td>
									<td></td>
									<td><input type="button" onclick="AddressDaumPostcode()"
										value="+새 배송지 추가"
										style="border: none; background: transparent; color: lightgrey;">
										<input type="button" id="btn" onclick="recentPostAddress()"
										value="+최근배송지"
										style="border: none; background: transparent; color: lightgrey;">
									</td>

								</tr>
								<tr style="border: none;">
									<td colspan="2"><input type="text" value=""
										name="address1" id="address1" placeholder="주소"
										required="required"></td>
									<td></td>
									<td><input type="text" value="" name="postcode"
										id="postcode" readonly="readonly" placeholder="우편번호"
										required="required"></td>
								</tr>
								<tr style="border: none;">
									<td><input type="text" value="" name="address2"
										id="address2" placeholder="상세주소" required="required">
									</td>
								</tr>
								<tr style="border: none;">
									<td><input type="text" value="" name="name"
										id="name" placeholder="받는분" required="required"></td>
								</tr>
								<tr style="border: none;">
									<td><input type="text" value="" name="phone"
										id="phone"	placeholder="전화번호(-제외)" required="required"></td>
								</tr>


								<c:if test="${memberBean.member_info_address ne null} ">
									<tr style="border: none;">
										<td colspan="2"><input type="text"
											value="${memberBean.member_info_address}" name="address1"
											id="address1" placeholder="주소" required="required"></td>
										<td></td>
										<td><input type="text"
											value="${memberBean.member_info_post_code}" name="postcode"
											id="postcode" readonly="readonly" placeholder="우편번호"
											required="required"></td>
									</tr>
									<tr style="border: none;">
										<td><input type="text"
											value="${memberBean.member_info_address_detail}"
											name="address2" id="address2" placeholder="상세주소"
											required="required"></td>
									</tr>
									<tr style="border: none;">
										<td><input type="text"
											value="${memberBean.member_info_name}" name="name" id="name"
											placeholder="받는분" required="required"></td>
									</tr>
									<tr style="border: none;">
										<td><input type="text"
											value="${memberBean.member_info_phone}" name="phone"
											id="phone" placeholder="전화번호(-제외)" required="required">
										</td>
									</tr>

								</c:if>
							</table>

							<table class="table-shopping-cart">
								<tr>
									<td>
										<div>
											<h4>배송 방법</h4>
										</div> <img src="./Upload/sell_img/1.jpg" width="75" height="80">

									</td>
									<td>
										<p>일반배송(배송비) 3000원 배송 후 5-7일 도착예정</p>
									</td>
								</tr>

							</table>
						</div>

						<div
							class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">

							Point:<input
								class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5"
								type="text" name="point" id="MemberPoint" value="0"
								onkeyup="this.value=this.value.replace(/[^-0-9]/g,'');">
							<!-- 								onkeyup="inputNumberFormat(this)" -->

							<div id="realPoint"></div>
							<div>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							</div>
							<input
								class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5"
								type="button" value="사용하기" onclick="sub()"><br>
							<p style="color: lightgrey;">현재
								포인트:${memberBean.member_info_detail_point}</p>
							<br>
							<div style="color: lightgrey;">1000원 단위로 사용가능</div>
						</div>

					</div>

				</div>

				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div
						class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-30">주문 정보</h4>

						<div class="flex-w flex-t bor12 p-b-13">
							<div class="size-208">
								<span class="stext-110 cl2"> 제품 가격 </span>
							</div>

							<div class="size-209">
								<span class="mtext-110 cl2"> ₩<span>&nbsp</span>${sellerDTO.sell_price *10000}
								</span>
							</div>
						</div>

						<div class="flex-w flex-t bor12 p-t-15 p-b-30">
							<div class="size-208 w-full-ssm">
								<!-- 								<span class="stext-110 cl2"> -->
								<!-- 									배송비 <span>&nbsp</span> 3000 -->
								<!-- 								</span><br> -->

								<!-- 								<span class="stext-110 cl2"> -->
								<!-- 									검수비 <span>&nbsp</span> free -->
								<!-- 								</span><br> -->

								<span class="stext-110 cl2"> 수수료 <span>&nbsp&nbsp</span>
									₩ ${Math.round((sellerDTO.sell_price *10000) *0.2)}
								</span><br> <span class="stext-110 cl2"> 포인트<span>&nbsp&nbsp&nbsp&nbsp</span>₩
									<span id="point"></span> <!-- 									style="border: none; background: transparent; " -->
								</span><br> <span class="stext-110 cl2"> 등급<span>&nbsp&nbsp</span>${memberBean.grade_name}
									<span><img width="29" height="30" alt="회원등급표"
										src="./Upload/sell_img/sellerpart.jpg"
										onclick="checkMember_grade()"></span>
								</span><br>
							</div>


							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
								<p class="stext-111 cl6 p-t-2"></p>


								<div class="p-t-15">
									<span class="stext-112 cl8"> </span>

								</div>
							</div>
						</div>




						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2"> 최종 금액 </span>
							</div>

							<div class="size-209 p-t-1">

								<span class="mtext-110 cl2" id=realPrice>
									₩ 
								</span>
								<script>  //값 INT형으로 바꾸기
									window.onload =function(){
										var a= document.getElementById('realPrice').innerHTML
										<%--  <c:if test="">	 포인트 사용 클릭시 여기에 값 뿌리기  --%>
										=${((sellerDTO.sell_price*10000) +((sellerDTO.sell_price*10000 *0.2))+3000)*((100-memberBean.discount_rate)/100)};										
									}
								</script>
						
							</div>
						</div>
						<input type="submit" value="구매하기"
							class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
					</div>
					<div></div>
				</div>
			</div>
		</div>
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
	<!--=========================488<포인트차감>==========================================================-->
	<script>
		// 	var sCode = '${sCode}';
		// 	alert(sCode)
		function recentPostAddress() { //최근 배송지 스크립트
			window.open('SellRecentPostAddressAction.pr?member_code=${sCode}',
					"", "width=750,height=450");
			
		}
		function checkMember_grade() {
			window.open('SellMemberGrade.pr?member_code=${sCode}', "",
					"width=750,height=450");
		}
	</script>
	<script>
		function sub() { //포인트 사용 스크립트
			$("#realPoint").empty();
			var finalPrice = ${((sellerDTO.sell_price * 10000)+ ((sellerDTO.sell_price * 10000) * 0.2) + 3000)* ((100 - memberBean.discount_rate) / 100)}
			debugger;
			//------------------------------------------------------------------------------
			var prePoint = ${memberBean.member_info_detail_point} //현재 보유포인트
			var testString = document.getElementById("MemberPoint").value; // 원래 문자열
			var regex = /[^0-9]/g; // 숫자가 아닌 문자열을 선택하는 정규식
			var subPoint = testString.replace(regex, "");//사용 할 포인트		

			document.getElementById("realPoint").value = prePoint - subPoint;//차감후 남은 포인트	
			$("#point").empty(); //최종 결제 포인트에서 보여주는곳
			$("#point").append(subPoint); //최종 결제 포인트에서 보여주는곳

			if ((subPoint % 10000) != 0) { //포인트 10000단위로 사용가능
				$("#point").empty();
				alert("10000원 단위 사용가능!");
			} else if (subPoint > finalPrice) { //포인트가  결제할금액을 초과했을 경우 
				$("#point").empty();
				alert("사용할 포인트가 결제금액보다 많습니다.")
			} else if (subPoint > prePoint) { //현재 보유포인트보다 더 많이 사용 할 경우
				$("#point").empty();
				alert("포인트가 부족합니다. 현재 포인트:" + prePoint);
			} else { //이경우가 아닐경우 화면에 뿌려줌
				// 					finalPrice -=realPoint;
				$("#realPoint").append(
						"남은포인트:" + document.getElementById("realPoint").value);

			}
		}
	</script>
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
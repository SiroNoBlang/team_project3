<%@page import="vo.SellerProductDTO"%>
<%@page import="vo.SellerimgDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.SellerDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Product Detail</title>
<style>
td{
text-align: center;
}

html, body {
	box-sizing: border-box;
	padding: 0;
	margin: 0;
	text-align: center;
}

*, *:before, *:after {
	box-sizing: inherit;
}

.clearfix:after {
	content: '';
	display: block;
	clear: both;
	float: none;
}

.title {
	margin-bottom: 0;
	text-align: center;
	font-size: 30px;
	color: #333;
}

.link, .link:visited {
	display: inline-block;
	margin: 20px 0;
	color: #555;
	text-decoration: none;
	font-weight: bold;
}

.link:hover, .link:focus {
	color: #9fd6c2;
}
/* container - body */
#container {
	width: 1000px;
	margin: auto;
}

.slide_wrap {
	position: relative;
	width: 400px;
	margin: auto;
	padding-bottom: 30px;
}

.slide_box {
	width: 100%;
	margin: auto;
	overflow-x: hidden;
}

.slide_content {
	display: table;
	float: left;
	width: 400px;
	height: 400px;
}

.slide_content>p {
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	font-size: 100px;
	font-weight: bold;
	color: #555;
}

.slide_content.slide01 {
	background: #ffffff;
}

.slide_content.slide02 {
	background: #ffffff;
}

.slide_content.slide03 {
	background: #ffffff;
}

.slide_btn_box>button {
	position: absolute;
	top: 50%;
	margin-top: -45px;
	width: 60px;
	height: 60px;
	font-size: 16px;
	color: #999;
	background: none;
	border: 1px solid #ddd;
	cursor: pointer;
}

.slide_btn_box>.slide_btn_prev {
	left: -100px;
}

.slide_btn_box>.slide_btn_next {
	right: -100px;
}

.slide_pagination {
	position: absolute;
	left: 50%;
	bottom: 0;
	list-style: none;
	margin: 0;
	padding: 0;
	transform: translateX(-50%);
}

.slide_pagination .dot {
	display: inline-block;
	width: 15px;
	height: 15px;
	margin: 0 5px;
	overflow: hidden;
	background: #ddd;
	border-radius: 50%;
	transition: 0.3s;
}

.slide_pagination .dot.dot_active {
	background: #333;
}

.slide_pagination .dot a {
	display: block;
	width: 100%;
	height: 100%;
}
</style>
<script type="text/javascript"> //좋아요 기능을 위하여 sCode 값을 script 위에 뿌려줌
    	var sCode = '${sCode}'	
    	var sellNum = ${param.sell_num};
</script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png"
	href="MainPage/images/icons/favicon.png" />
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/fonts/linearicons-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/slick/slick.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/MagnificPopup/magnific-popup.css">
<link rel="stylesheet" type="text/css"
	href="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="MainPage/css/util.css">
<link rel="stylesheet" type="text/css" href="MainPage/css/main.css">
</head>
<body class="animsition">

	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<div class="wrap-menu-desktop how-shadow1" style="top: 0px;">
				<nav class="limiter-menu-desktop container">
					<jsp:include page="/MainPage/menu/pc_sub_header.jsp" />
				</nav>
			</div>
			</div>
				<jsp:include page="/MainPage/menu/pc_menu.jsp" />
			<!-- Header Mobile -->
			<div class="wrap-header-mobile">
				<!-- Logo moblie -->
				<div class="logo-mobile">
					<a href="MainPage.pr"><img
						src="MainPage/images/icons/logo-01.png" alt="IMG-LOGO"></a>
				</div>

				<!-- Icon header -->
				
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
							<a href="#" class="flex-c-m p-lr-10 trans-04"> Help &#38; FAQs </a> <a
								href="#" class="flex-c-m p-lr-10 trans-04"> My Account </a> <a
								href="#" class="flex-c-m p-lr-10 trans-04"> EN </a> <a href="#"
								class="flex-c-m p-lr-10 trans-04"> USD </a>
						</div>
					</li>
				</ul>

				<ul class="main-menu-m">
					<li><a href="index.html">Home</a>
						<ul class="sub-menu-m">
							<li><a href="index.html">Homepage 1</a></li>
							<li><a href="home-02.html">Homepage 2</a></li>
							<li><a href="home-03.html">Homepage 3</a></li>
						</ul> <span class="arrow-main-menu-m"> <i
							class="fa fa-angle-right" aria-hidden="true"></i>
					</span></li>

					<li><a href="product.pr">Shop</a></li>

					<li><a href="shoping-cart.html" class="label1 rs1"
						data-label1="hot">Features</a></li>

					<li><a href="SellForm.pr">Sell</a></li>

					<li><a href="about.html">About</a></li>

					<li><a href="contact.html">Contact</a></li>
				</ul>
			</div>
			<!-- Modal Search -->
			<div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
				<div class="container-search-header">
					<button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
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
	<jsp:include page="/MainPage/menu/pc_shopping cart.jsp" />
<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="MainPage.pr" class="stext-109 cl8 hov-cl1 trans-04">
				Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<a href="Product.pr" class="stext-109 cl8 hov-cl1 trans-04">
				Shop
 				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<span class="stext-109 cl4">
				category:${sellerdto.sell_category}
			</span>
		</div>
	</div>
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-7 p-b-30">
					<div class="p-l-25 p-r-30 p-lr-0-lg">
						<div class="wrap-slick3 flex-sb flex-w">
							<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>
							<div class="slick3 gallery-lb">
								<!-- ------------------------------------ -->
								<div id="container">
									<div class="slide_wrap">
										<div class="slide_box">
											<div class="slide_list clearfix">

												<c:forEach items="${Sellerdetailimg }" var="sellerimg">

													<div class="slide_content slide01">
														<img
															src="./Upload/sell_img/${sellerimg.getSell_img_real_name() }"
															width="400" height="600"> &nbsp;
													</div>
												</c:forEach>
											</div>

											<!-- // .slide_list -->
										</div>
										<!-- // .slide_box -->
										<div class="slide_btn_box">
											<button type="button" class="slide_btn_prev">이전</button>
											<button type="button" class="slide_btn_next">다음</button>
										</div>
										<!-- // .slide_btn_box -->
										<ul class="slide_pagination"></ul>
										<!-- // .slide_pagination -->
									</div>
									<!-- // .slide_wrap -->
								</div>
							</div>
						</div>

					</div>
				</div>




				<div class="col-md-6 col-lg-5 p-b-30">
					<div class="p-r-50 p-t-5 p-lr-0-lg">
											
						<table>
							<tr>
							<td colspan="2" ><h2 class="mtext-105 cl2 js-name-detail p-b-14">
							제목:${sellerdto.sell_title}</h2></td>
							</tr>
							<tr>
								<td>Brand </td>
								<td style="text-align: left;">${sellerdto.sell_brand}</td>
							</tr>
							<tr>
								<td>Category </td>
								<td style="text-align: left;">${sellerdto.sell_category}</td>
							</tr>
							<tr>
								<td>detail </td>
								<td style="text-align: left;"> ${sellerdto.sell_category_detail}</td>
							</tr>
							<tr>
								<td>Size </td>
								<td style="text-align: left;">${sellerdto.sell_size}</td>
							</tr>
							<tr>
								<td>Color </td>
								<td style="text-align: left;">${sellerdto.sell_color}</td>
							</tr>
							<tr>
								<td>Price</td>
								<td style="text-align: left;"> ${sellerdto.sell_price}원</td>
							</tr>
							
							<tr>
								<td><input style="border: none;"
									class="cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-like" type="button" value="찜하기">
								</td>
								<td><input style="border: none;"
									class="cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail" type="button" value="구매"
									onclick="location.href='ShopingPro.pr?sell_num=${sellerdto.getSell_num() }&member_code=${sCode }'">
								</td>
							</tr>

						</table>

	<div class="flex-w flex-m p-l-100 p-t-40 respon7">
					<c:if test="${ sCode ne null }">
						<li class="w3-button w3-black w3-round" id="rec_update">
							<i class="fa fa-heart " style="font-size:16px;color:red"></i>
							&nbsp;<span class="rec_count">${sellerdto.sell_likecount}</span>
						</li> 
					</c:if>
				</div>
	  <br>
	<br>

	<table>
		<tr>
		<c:forEach items="${Sellerdetailimg }" var="sellerimg">
			
				<td><img
					src="./Upload/sell_img/${sellerimg.sell_img_real_name}" width="150"
					height="150"> &nbsp;</td>
		
		</c:forEach>
		</tr>
	</table>
	</div>
	</div>
	<!-- --------------------------------------------------------- -->
			 <div class="bor10 m-t-50 p-t-43 p-b-40">
				<!-- Tab01 -->
				<div class="tab01">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item p-b-10">
							<a class="nav-link active" data-toggle="tab" href="#description" role="tab">상세내용</a>
						</li>

						<li class="nav-item p-b-10">
							<a class="nav-link" data-toggle="tab" href="#information" role="tab">이런상품은 어떠세요?</a>
						</li>

						<li class="nav-item p-b-10">
							<a class="nav-link" data-toggle="tab" href="#reviews" role="tab">판매자 리뷰</a>
						</li>
					</ul>
<!-- 					Tab panes -->
					<div class="tab-content p-t-43">
						<div class="tab-pane fade show active" id="description" role="tabpanel">
							<div class="how-pos2 p-lr-15-md">
								<div style="text-align: center;">
									<h4>content</h4>
								</div>
									<h5>${sellerdto.sell_content}</h5> 
						   </div>
					    </div>
 <!-- 이러한 상품은 어떠세요? -->
						<div class="tab-pane fade" id="information" role="tabpanel">
							<ul class="p-lr-28 p-lr-15-sm">
								<li class="flex-w flex-t p-b-7">
								
									<table border="1" style="border: none; background: white;">
										<tr>
										 <c:forEach items="${Relationdto}" var="ProductRe">
										 	<td style="border: none;">
											<span style="text-align: center;">	
												<a href="ProductDetailPro.pr?sell_num=${ProductRe.getSell_num()}&sell_brand=${ProductRe.getSell_brand() }"><img src="./Upload/sell_img/${ProductRe.getSell_img_real_name() } "onerror="this.style.display='none'" width="130px"height="160px" alt="판매사진"/></a><br>
													${ProductRe.getSell_title() }<br>
												brand:${ProductRe.getSell_brand() }
												<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</a>
											</span>
										</c:forEach>
										</tr>
									</table>
								</li>
							</ul>
						</div>
	<!-- 리뷰 입니다. -->					
						<div class="tab-pane fade sell_box" id="reviews" role="tabpanel" style="text-align: left;">
<!-- 							<div class="row"> -->
								<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto" style="width: 1733.04px;">
									<div class="p-b-30 m-lr-15-sm">
										<!-- Review -->
										<div class="flex-w flex-t p-b-68">
											<div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
												<img src="MainPage/images/avatar-01.jpg" alt="AVATAR">
											</div>

											<div class="size-207">
												<div class="flex-w flex-sb-m p-b-17">
													<span class="mtext-107 cl2 p-r-20">
														Ariana Grande
													</span>

													<span class="fs-18 cl11">
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star-half"></i>
													</span>
												</div>

												<p>
													Quod autem in homine praestantissimum atque optimum est, id deseruit. Apud ceteros autem philosophos
												</p>
											</div>
										</div>
										
										<!-- Add review -->
										<form class="w-full">
											<h5 class="mtext-108 cl2 p-b-7">
												Add a review
											</h5>

											<p >
												Your email address will not be published. Required fields are marked *
											</p>

											<div>
												<span>
													<br>Your Rating
												</span>

												<span class="wrap-rating fs-18 cl11 pointer">
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<input class="dis-none" type="number" name="rating">
												</span>
											</div>
											
											<br><br>
											<div class="row p-b-25">
												<div class="col-12 p-b-5">
													<label for="review">Your review</label>
													<textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="review"></textarea>
												</div>

												<div class="col-sm-6 p-b-5">
													<label for="name">Name</label>
													<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="name" type="text" name="name">
												</div>

												<div class="col-sm-6 p-b-5">
													<label for="email">Email</label>
													<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="email" type="text" name="email">
												</div>
											</div>

											<button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">
												Submit
											</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<div class="bg6 flex-c-m flex-w size-302 m-t-73 p-tb-15">
		<span class="stext-107 cl6 p-lr-25"> SKU: JAK-01 </span> <span
			class="stext-107 cl6 p-lr-25"> Categories: Jacket, Men </span>
	</div>
	
	
	<!-- Footer영역과 상단 이동 버튼-->
	<jsp:include page="/MainPage/menu/footer.jsp" />

	<!-- Modal1 -->
	<div class="wrap-modal1 js-modal1 p-t-60 p-b-20">
		<div class="overlay-modal1 js-hide-modal1"></div>

		<div class="container">
			<div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
				<button class="how-pos3 hov3 trans-04 js-hide-modal1">
					<img src="MainPage/images/icons/icon-close.png" alt="CLOSE">
				</button>

				<div class="row">
					<div class="col-md-6 col-lg-7 p-b-30">
						<div class="p-l-25 p-r-30 p-lr-0-lg">
							<div class="wrap-slick3 flex-sb flex-w">
								<div class="wrap-slick3-dots"></div>
								<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

								<div class="slick3 gallery-lb">
									<div class="item-slick3"
										data-thumb="MainPage/images/product-detail-01.jpg">
										<div class="wrap-pic-w pos-relative">
											<img src="MainPage/images/product-detail-01.jpg"
												alt="IMG-PRODUCT"> <a
												class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
												href="MainPage/images/product-detail-01.jpg"> <i
												class="fa fa-expand"></i>
											</a>
										</div>
									</div>

									<div class="item-slick3"
										data-thumb="MainPage/images/product-detail-02.jpg">
										<div class="wrap-pic-w pos-relative">
											<img src="MainPage/images/product-detail-02.jpg"
												alt="IMG-PRODUCT"> <a
												class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
												href="MainPage/images/product-detail-02.jpg"> <i
												class="fa fa-expand"></i>
											</a>
										</div>
									</div>

									<div class="item-slick3"
										data-thumb="MainPage/images/product-detail-03.jpg">
										<div class="wrap-pic-w pos-relative">
											<img src="MainPage/images/product-detail-03.jpg"
												alt="IMG-PRODUCT"> <a
												class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
												href="MainPage/images/product-detail-03.jpg"> <i
												class="fa fa-expand"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-6 col-lg-5 p-b-30">
						<div class="p-r-50 p-t-5 p-lr-0-lg">
							<h4 class="mtext-105 cl2 js-name-detail p-b-14">Lightweight
								Jacket</h4>

							<span class="mtext-106 cl2"> $58.79 </span>

							<p class="stext-102 cl3 p-t-23">Nulla eget sem vitae eros
								pharetra viverra. Nam vitae luctus ligula. Mauris consequat
								ornare feugiat.</p>

							<!--  -->
							<div class="p-t-33">
								<div class="flex-w flex-r-m p-b-10">
									<div class="size-203 flex-c-m respon6">Size</div>

									<div class="size-204 respon6-next">
										<div class="rs1-select2 bor8 bg0">
											<select class="js-select2" name="time">
												<option>Choose an option</option>
												<option>Size S</option>
												<option>Size M</option>
												<option>Size L</option>
												<option>Size XL</option>
											</select>
											<div class="dropDownSelect2"></div>
										</div>
									</div>
								</div>

								<div class="flex-w flex-r-m p-b-10">
									<div class="size-203 flex-c-m respon6">Color</div>

									<div class="size-204 respon6-next">
										<div class="rs1-select2 bor8 bg0">
											<select class="js-select2" name="time">
												<option>Choose an option</option>
												<option>Red</option>
												<option>Blue</option>
												<option>White</option>
												<option>Grey</option>
											</select>
											<div class="dropDownSelect2"></div>
										</div>
									</div>
								</div>

								<div class="flex-w flex-r-m p-b-10">
									<div class="size-204 flex-w flex-m respon6-next">
										<div class="wrap-num-product flex-w m-r-20 m-tb-10">
											<div
												class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-minus"></i>
											</div>

											<input class="mtext-104 cl3 txt-center num-product"
												type="number" name="num-product" value="1">

											<div
												class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-plus"></i>
											</div>
										</div>

										<button
											class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
											Add to cart</button>
									</div>
								</div>
							</div>
							
							<!--  -->
							<div class="flex-w flex-m p-l-100 p-t-40 respon7">
								<div class="flex-m bor9 p-r-10 m-r-11">
									<a href="#"
										class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100"
										data-tooltip="Add to Wishlist"> <i
										class="zmdi zmdi-favorite"></i>
									</a>
								</div>

								<a href="#"
									class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100"
									data-tooltip="Facebook"> <i class="fa fa-facebook"></i>
								</a> <a href="#"
									class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100"
									data-tooltip="Twitter"> <i class="fa fa-twitter"></i>
								</a> <a href="#"
									class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100"
									data-tooltip="Google Plus"> <i class="fa fa-google-plus"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="MainPage/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="MainPage/vendor/animsition/js/animsition.min.js"></script>
	<script src="MainPage/vendor/bootstrap/js/popper.js"></script>
	<script src="MainPage/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="MainPage/vendor/select2/select2.min.js"></script>
	<script>
		$(".js-select2").each(function(){
			$(this).select2({
				minimumResultsForSearch: 20,
				dropdownParent: $(this).next('.dropDownSelect2')
			});
		})
	</script>
	<script src="MainPage/vendor/daterangepicker/moment.min.js"></script>
	<script src="MainPage/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="MainPage/vendor/slick/slick.min.js"></script>
	<script src="MainPage/js/slick-custom.js"></script>
	<script src="MainPage/vendor/parallax100/parallax100.js"></script>
	<script>
        $('.parallax100').parallax100();
	</script>
	<script
		src="MainPage/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<script>
		$('.gallery-lb').each(function() { // the containers for all your galleries
			$(this).magnificPopup({
		        delegate: 'a', // the selector for gallery item
		        type: 'image',
		        gallery: {
		        	enabled:true
		        },
		        mainClass: 'mfp-fade'
		    });
		});
	</script>
	<script src="MainPage/vendor/isotope/isotope.pkgd.min.js"></script>
	<script src="MainPage/vendor/sweetalert/sweetalert.min.js"></script>
	<script>
		$('.js-addwish-b2, .js-addwish-detail').on('click', function(e){
			e.preventDefault();
		});

		$('.js-addwish-b2').each(function(){
			var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
			$(this).on('click', function(){
				swal(nameProduct, "is added to wishlist !", "success");

				$(this).addClass('js-addedwish-b2');
				$(this).off('click');
			});
		});

		$('.js-addwish-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

			$(this).on('click', function(){
				swal(nameProduct, "is added to wishlist !", "success");

				$(this).addClass('js-addedwish-detail');
				$(this).off('click');
			});
		});

		$('.js-addcart-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
			$(this).on('click', function(){
				swal(nameProduct, "구매페이지로 이동합니다.", "success");
			});
		});
		
		$('.js-addcart-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
			$(this).on('click', function(){
				swal(nameProduct, "구매페이지로 이동합니다.", "success");
			});
		});
		
	
	</script>
	<script
		src="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			$(this).css('position','relative');
			$(this).css('overflow','hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed: 1,
				scrollingThreshold: 1000,
				wheelPropagation: false,
			});

			$(window).on('resize', function(){
				ps.update();
			})
		});
	</script>
	<script src="MainPage/js/main.js"></script>
	<script type="text/javascript">
(function () {
    const slideList = document.querySelector('.slide_list');  // Slide parent dom
    const slideContents = document.querySelectorAll('.slide_content');  // each slide dom
    const slideBtnNext = document.querySelector('.slide_btn_next'); // next button
    const slideBtnPrev = document.querySelector('.slide_btn_prev'); // prev button
    const pagination = document.querySelector('.slide_pagination');
    const slideLen = slideContents.length;  // slide length
    const slideWidth = 400; // slide width
    const slideSpeed = 300; // slide speed
    const startNum = 0; // initial slide index (0 ~ 4)
    
    slideList.style.width = slideWidth * (slideLen + 2) + "px";
    
    // Copy first and last slide
    let firstChild = slideList.firstElementChild;
    let lastChild = slideList.lastElementChild;
    let clonedFirst = firstChild.cloneNode(true);
    let clonedLast = lastChild.cloneNode(true);

    // Add copied Slides
    slideList.appendChild(clonedFirst);
    slideList.insertBefore(clonedLast, slideList.firstElementChild);

    // Add pagination dynamically
    let pageChild = '';
    for (var i = 0; i < slideLen; i++) {
      pageChild += '<li class="dot';
      pageChild += (i === startNum) ? ' dot_active' : '';
      pageChild += '" data-index="' + i + '"><a href="#"></a></li>';
    }
    pagination.innerHTML = pageChild;
    const pageDots = document.querySelectorAll('.dot'); // each dot from pagination

    slideList.style.transform = "translate3d(-" + (slideWidth * (startNum + 1)) + "px, 0px, 0px)";

    let curIndex = startNum; // current slide index (except copied slide)
    let curSlide = slideContents[curIndex]; // current slide dom
    curSlide.classList.add('slide_active');

    /** Next Button Event */
    slideBtnNext.addEventListener('click', function() {
      if (curIndex <= slideLen - 1) {
        slideList.style.transition = slideSpeed + "ms";
        slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 2)) + "px, 0px, 0px)";
      }
      if (curIndex === slideLen - 1) {
        setTimeout(function() {
          slideList.style.transition = "0ms";
          slideList.style.transform = "translate3d(-" + slideWidth + "px, 0px, 0px)";
        }, slideSpeed);
        curIndex = -1;
      }
      curSlide.classList.remove('slide_active');
      pageDots[(curIndex === -1) ? slideLen - 1 : curIndex].classList.remove('dot_active');
      curSlide = slideContents[++curIndex];
      curSlide.classList.add('slide_active');
      pageDots[curIndex].classList.add('dot_active');
    });

    /** Prev Button Event */
    slideBtnPrev.addEventListener('click', function() {
      if (curIndex >= 0) {
        slideList.style.transition = slideSpeed + "ms";
        slideList.style.transform = "translate3d(-" + (slideWidth * curIndex) + "px, 0px, 0px)";
      }
      if (curIndex === 0) {
        setTimeout(function() {
          slideList.style.transition = "0ms";
          slideList.style.transform = "translate3d(-" + (slideWidth * slideLen) + "px, 0px, 0px)";
        }, slideSpeed);
        curIndex = slideLen;
      }
      curSlide.classList.remove('slide_active');
      pageDots[(curIndex === slideLen) ? 0 : curIndex].classList.remove('dot_active');
      curSlide = slideContents[--curIndex];
      curSlide.classList.add('slide_active');
      pageDots[curIndex].classList.add('dot_active');
    });

    /** Pagination Button Event */
    let curDot;
    Array.prototype.forEach.call(pageDots, function (dot, i) {
      dot.addEventListener('click', function (e) {
        e.preventDefault();
        curDot = document.querySelector('.dot_active');
        curDot.classList.remove('dot_active');
        
        curDot = this;
        this.classList.add('dot_active');

        curSlide.classList.remove('slide_active');
        curIndex = Number(this.getAttribute('data-index'));
        curSlide = slideContents[curIndex];
        curSlide.classList.add('slide_active');
        slideList.style.transition = slideSpeed + "ms";
        slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 1)) + "px, 0px, 0px)";
      });
    });
  })();
</script>
	
<script type="text/javascript">// 추천버튼 클릭시(추천 추가 또는 추천 제거)

$('.js-like').each(function(){
	//var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
	var rec_count =document.getElementsByClassName("rec_count")
	$(this).on('click', function(){		
		var thisRow = $(this).closest('div');  //하트표시 줄의 부모(div)를 찾아  thisRow값에 저장
		var thisplace = thisRow.find('span')[0];
		
		$.ajax({
			url: "ProductRecUpdate.pr",
            type: "POST",
            data: {
         		sell_num : sellNum,    
                id: sCode					 
            },
            success: function (data) {
            	debugger;   //JSON.parse(data).result
            	thisplace.innerHTML = JSON.parse(data).likeCount;
            	if(JSON.parse(data).result == 0){
    				swal("LIKE!", "해당 제품을 찜 하셨습니다." );
    			}else{
    				swal("LIKE!", "해당 제품을 찜 취소하셨습니다" );
    				
    			}
            }
		})

	});
});
</script>
</body>
</html>
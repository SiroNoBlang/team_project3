<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!DOCTYPE html>
<html lang="en">
<head>
<title>Home 03</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="MainPage/assets/js/jquery-3.6.0.js"></script>
<style type="text/css">
.selectbox {
	text-align: right;
	position: relative;
	width: 200px; /* 너비설정 */
	border: 1px solid #999; /* 테두리 설정 */
	z-index: 1;
}

a:link {
	color: black;
}

</style>
<script type="text/javascript">
var sCode = '${sCode}' //좋아요 기능을 위하여 sCode 값을 script 위에 뿌려줌
var sell_num

	function filter(value) {

		if (value == "brand") {
			var tagSpan = document.getElementById("checkbox2");
			tagSpan.innerHTML = "<a href='ProductBrandPro.pr?sell_brand=Hermes' > Hermes </a> &nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Louis Vuitton' > Louis Vuitton </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Bottega Veneta'> Bottega Veneta </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Prada' > Prada </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Rolex'> Rolex </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Dior' > Dior </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Celine' > Celine </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Balenciaga' > Balenciaga </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Cartier' > Cartier </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Fendi' > Fendi </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Goyard' > Goyard </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=MiuMiu' > MiuMiu </a>"
					+ "<a href='ProductBrandPro.pr?sell_brand=Lemaire' > Lemaire </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Loewe' > Loewe </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Marni' > Marni </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Off-White' > Off-White </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Supreme' > Supreme </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Jordan' > Jordan </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Jordan' > Nike </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Jordan' > Chanel </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Jordan' > Moncler </a>&nbsp;&nbsp;"
					+ "<a href='ProductBrandPro.pr?sell_brand=Jil Sander'> Jil Sander </a>";
		} else if (value == "category") {
			var tagSpan = document.getElementById("checkbox2");

			tagSpan.innerHTML = "<a></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_category=상의' style='font-size: 1.0em'> 상의 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_category=하의' style='font-size: 1.0em'> 하의 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_category=신발' style='font-size: 1.0em'> 신발 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_category=잡화' style='font-size: 1.0em'> 잡화 </a>";
		} else if (value == "price") {
			var tagSpan = document.getElementById("checkbox2");

			tagSpan.innerHTML = "<a></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_price=10000' style='font-size: 1.0em'> 10만원 이하 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_price=100000' style='font-size: 1.0em'> 10만원 - 50만원 이하 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_price=1000000' style='font-size: 1.0em'> 50만원 -100만원 이하 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_price=1000000' style='font-size: 1.0em'> 100만원 - 500만원 이하 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductCategoryPro.pr?sell_price=10000000' style='font-size: 1.0em'> 500만원 이상- 1000만원 이하 </a>";
		}

	}
</script>

<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="MainPage/images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/slick/slick.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/MagnificPopup/magnific-popup.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="MainPage/css/util.css">
	<link rel="stylesheet" type="text/css" href="MainPage/css/main.css">
	
<!--===============================================================================================-->
</head>
<body class="animsition">

	<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
	<c:set var="startPage" value="${pageInfo.getStartPage() }" />
	<c:set var="endPage" value="${pageInfo.getEndPage() }" />
	<c:set var="listCount" value="${pageInfo.getListCount() }" />
	
	<!-- Header -->
	<header class="header-v3">
		<!-- Header desktop -->
		<div class="container-menu-desktop trans-03">
<!-- 		<!-- Topbar --> 
		<!-- pc_header 카테고리 및 오른쪽 더보기 버튼페이지-->
			<jsp:include page="/MainPage/menu/pc_header.jsp"/>
		</div>

		<!-- Header Mobile -->
		<jsp:include page="/MainPage/menu/mobile_header.jsp"/>
		
		<!-- Modal Search -->
		<div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
			<button class="flex-c-m btn-hide-modal-search trans-04">
				<i class="zmdi zmdi-close"></i>
			</button>

			<form class="container-search-header">
				<div class="wrap-search-header">
					<input class="plh0" type="text" name="search" placeholder="Search...">

					<button class="flex-c-m trans-04">
						<i class="zmdi zmdi-search"></i>
					</button>
				</div>
			</form>
		</div>
	</header>


	<!-- PC_menu_Sidebar -->
	<jsp:include page="/MainPage/menu/pc_menu.jsp"/>

	<!-- Cart -->
	<div class="wrap-header-cart js-panel-cart">
		<div class="s-full js-hide-cart"></div>
		
		<div class="sidebar flex-col-l p-t-22 p-b-25">
			<div class="flex-r w-full p-b-30 p-r-27">
				<div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-sidebar">
					<i class="zmdi zmdi-close"></i>
				</div>
			</div>
		</div>

	
	<!--Mobile_menu_Sidebar -->
	<jsp:include page="/MainPage/menu/mobile_menu.jsp"/>	
	</div>

	<!-- Slider -->
	<section class="section-slide">
		<div class="wrap-slick1 rs2-slick1">
			<div class="slick1">
				<div class="item-slick1 bg-overlay1" style="background-image: url(MainPage/images/slide-05.jpg);" data-thumb="MainPage/images/thumb-01.jpg" data-caption="Women’s Wear">
					<div class="container h-full">
						<div class="flex-col-c-m h-full p-t-100 p-b-60 respon5">
							<div class="layer-slick1 animated visible-false" data-appear="fadeInDown" data-delay="0">
								<span class="ltext-202 txt-center cl0 respon2">
									Women Collection 2018
								</span>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="fadeInUp" data-delay="800">
								<h2 class="ltext-104 txt-center cl0 p-t-22 p-b-40 respon1">
									New arrivals
								</h2>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="zoomIn" data-delay="1600">
								<a href="product.html" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn2 p-lr-15 trans-04">
									Shop Now
								</a>
							</div>
						</div>
					</div>
				</div>

				<div class="item-slick1 bg-overlay1" style="background-image: url(MainPage/images/slide-06.jpg);" data-thumb="MainPage/images/thumb-02.jpg" data-caption="Men’s Wear">
					<div class="container h-full">
						<div class="flex-col-c-m h-full p-t-100 p-b-60 respon5">
							<div class="layer-slick1 animated visible-false" data-appear="rollIn" data-delay="0">
								<span class="ltext-202 txt-center cl0 respon2">
									Men New-Season
								</span>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="lightSpeedIn" data-delay="800">
								<h2 class="ltext-104 txt-center cl0 p-t-22 p-b-40 respon1">
									Jackets & Coats
								</h2>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="slideInUp" data-delay="1600">
								<a href="product.html" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn2 p-lr-15 trans-04">
									Shop Now
								</a>
							</div>
						</div>
					</div>
				</div>

				<div class="item-slick1 bg-overlay1" style="background-image: url(MainPage/images/slide-07.jpg);" data-thumb="MainPage/images/thumb-03.jpg" data-caption="Men’s Wear">
					<div class="container h-full">
						<div class="flex-col-c-m h-full p-t-100 p-b-60 respon5">
							<div class="layer-slick1 animated visible-false" data-appear="rotateInDownLeft" data-delay="0">
								<span class="ltext-202 txt-center cl0 respon2">
									Men Collection 2018
								</span>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="rotateInUpRight" data-delay="800">
								<h2 class="ltext-104 txt-center cl0 p-t-22 p-b-40 respon1">
									NEW SEASON
								</h2>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="rotateIn" data-delay="1600">
								<a href="product.html" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn2 p-lr-15 trans-04">
									Shop Now
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="wrap-slick1-dots p-lr-10"></div>
		</div>
	</section>


	<!-- Banner -->
	<div class="sec-banner bg0 p-t-95 p-b-55">
		<div class="container">
			<div class="row">
				<div class="col-md-6 p-b-30 m-lr-auto">
					<!-- Block1 -->
					
					<div class="block1 wrap-pic-w">
						<img src="MainPage/images/banner-04.jpg" alt="IMG-BANNER">

						<a href="product.html" class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">
									Women
								</span>

								<span class="block1-info stext-102 trans-04">
									New Trend
								</span>
							</div>

							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">
									Shop Now
								</div>
							</div>
						</a>
					</div>
				</div>
				

				<div class="col-md-6 p-b-30 m-lr-auto">
					<!-- Block1 -->
					<div class="block1 wrap-pic-w">
						<img src="MainPage/images/banner-05.jpg" alt="IMG-BANNER">

						<a href="product.html" class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">
									Men
								</span>

								<span class="block1-info stext-102 trans-04">
									New Trend
								</span>
							</div>

							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">
									Shop Now
								</div>
							</div>
						</a>
					</div>
				</div>

				<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
					<!-- Block1 -->
					<div class="block1 wrap-pic-w">
						<img src="MainPage/images/banner-07.jpg" alt="IMG-BANNER">

						<a href="product.html" class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">
									Watches
								</span>

								<span class="block1-info stext-102 trans-04">
									Spring 2018
								</span>
							</div>

							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">
									Shop Now
								</div>
							</div>
						</a>
					</div>
				</div>

				<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
				
					<!-- Block1 -->
					<div class="block1 wrap-pic-w">
						<img src="MainPage/images/banner-08.jpg" alt="IMG-BANNER">

						<a href="product.html" class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">
									Bags
								</span>

								<span class="block1-info stext-102 trans-04">
									Spring 2018
								</span>
							</div>

							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">
									Shop Now
								</div>
							</div>
						</a>
					</div>
				</div>

				<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
					<!-- Block1 -->
					<div class="block1 wrap-pic-w">
						<img src="MainPage/images/banner-09.jpg" alt="IMG-BANNER">

						<a href="team_project3/ProductCategoryPro.pr?sell_category=잡화" class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
							<div class="block1-txt-child1 flex-col-l">
								<span class="block1-name ltext-102 trans-04 p-b-8">
									Accessories
								</span>

								<span class="block1-info stext-102 trans-04">
									Spring 2018
								</span>
							</div>

							<div class="block1-txt-child2 p-b-4 trans-05">
								<div class="block1-link stext-101 cl0 trans-09">
									Shop Now
								</div>
								
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	



	<!-- Product -->
	
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-c-m m-tb-10">
					<div
						class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
						<i
							class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
						<i
							class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
						Filter
					</div>

					<div
						class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
						<i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
						<i
							class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
						Search
					</div>
				</div>

				<!-- Search product -->
				<div class="dis-none panel-search w-full p-t-10 p-b-15">
					<form action="ProductSearchPro.pr">
						<table>
							<tr>
								<td><input type="text" placeholder="상품을 입력해주세요."
									name="ProductSearch"></td>
								<td><input type="submit" value="검색" placeholder="Search">
								</td>
							</tr>
						</table>
					</form>
				</div>

				<!-- Filter -->
				<div class="dis-none panel-filter w-full p-t-10">
					<div
						class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm">
						<div id="checkbox1" style="margin-bottom: 30px;" width="400">
							<table width=400px>
								<tr>
									<th><input type="button" style="font-size: 35px"
										class="filter-link stext-106 trans-04" name=filter
										value="brand" onclick="filter(this.value)"></th>
									<th><input type="button" style="font-size: 35px"
										class="filter-link stext-106 trans-04" name=filter
										value="category" onclick="filter(this.value)"></th>
									<th><input type=button style="font-size: 35px"
										class="filter-link stext-106 trans-04" name=filter
										value="price" onclick="filter(this.value)"></th>
								</tr>
								<tr>
								</tr>
							</table>
							<br>
						</div>

						<div id="checkbox2"
							style="vertical-align: middle; margin-bottom: 5px;"></div>
					</div>
				</div>
			</div>


			<div class="row isotope-grid">

				<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
					<table border="1" style="border: none; background: white;">
						<form>
							<c:forEach items="${articleList}" var="articleList">
								<div class="col-sm-6 col-md-3 col-lg-3 ">

									<div class="shop-item">
										<!-- 여기 -->
										<input class="sell_num" value="${articleList.sell_num}" /> <a
											href="ProductDetailPro.pr?sell_num=${articleList.sell_num}&sell_brand=${articleList.sell_brand}">

											<img
											src="./Upload/sell_img/${articleList.sell_img_real_name}"
											width="300px" height="400px" alt="Accessories Pack" />
										</a> <span>${articleList.sell_img_real_name}</span> <a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										<i class="fa fa-eye"></i><span style="text-align: right;">${articleList.sell_readcount }
										</span>| <span> <span> <span
												class="w3-border w3-center w3-padding"> <!-- 좋아요 기능을 위한 스크립트(Ajax)는 892행  -->
													<c:if test="${ sCode  eq null }">
														<i class="fa fa-heart" style="font-size: 16px; color: red"></i>    -->
								<span class="rec_count">세션이 만료되었습니다-로그인필요!</span>
													</c:if> <c:if test="${ sCode ne null }">
														<li class="w3-button w3-black w3-round" id="rec_update">
															<i class="fa fa-heart"
															style="font-size: 16px; color: red"></i> &nbsp;<span
															class="rec_count">${articleList.sell_likecount }</span>
														</li>
													</c:if>
											</span>
										</span>
										</span>
										<h5>${articleList.sell_title }</h5>
										<h5>${articleList.sell_brand }</h5>
										<!-- ------------------------------------------ -->

										<!-- ------------------------------------------ -->
									</div>
								</div>
							</c:forEach>
						</form>
					</table>
				</div>
			</div>

			<!-- Pagination -->

			<div class="flex-c-m flex-w w-full p-t-45">

				<section id="pageList">
					<!-- 
		현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
		=> 클릭 시 notice.jsp 로 이동하면서 
		   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
		-->
					<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
						<c:choose>
							<c:when test="${pageNum eq i}">${i }
								</c:when>
									<c:otherwise>
									<a href="Product.pr?page=${i }"
									class=" how-pagination1 trans-04 m-all-7">${i }</a>
									</c:otherwise>
						</c:choose>
					</c:forEach>
				</section>


			</div>
		</div>





		<!-- Footer영역과 상단 이동 버튼-->
	<jsp:include page="/MainPage/menu/footer.jsp"/>

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
									<div class="item-slick3" data-thumb="MainPage/images/product-detail-01.jpg">
										<div class="wrap-pic-w pos-relative">
											<img src="MainPage/images/product-detail-01.jpg" alt="IMG-PRODUCT">

											<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="MainPage/images/product-detail-01.jpg">
												<i class="fa fa-expand"></i>
											</a>
										</div>
									</div>

									<div class="item-slick3" data-thumb="MainPage/images/product-detail-02.jpg">
										<div class="wrap-pic-w pos-relative">
											<img src="MainPage/images/product-detail-02.jpg" alt="IMG-PRODUCT">

											<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="MainPage/images/product-detail-02.jpg">
												<i class="fa fa-expand"></i>
											</a>
										</div>
									</div>

									<div class="item-slick3" data-thumb="MainPage/images/product-detail-03.jpg">
										<div class="wrap-pic-w pos-relative">
											<img src="MainPage/images/product-detail-03.jpg" alt="IMG-PRODUCT">

											<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="MainPage/images/product-detail-03.jpg">
												<i class="fa fa-expand"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-6 col-lg-5 p-b-30">
						<div class="p-r-50 p-t-5 p-lr-0-lg">
							<h4 class="mtext-105 cl2 js-name-detail p-b-14">
								Lightweight Jacket
							</h4>

							<span class="mtext-106 cl2">
								$58.79
							</span>

							<p class="stext-102 cl3 p-t-23">
								Nulla eget sem vitae eros pharetra viverra. Nam vitae luctus ligula. Mauris consequat ornare feugiat.
							</p>
							
							<!--  -->
							<div class="p-t-33">
								<div class="flex-w flex-r-m p-b-10">
									<div class="size-203 flex-c-m respon6">
										Size
									</div>

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
									<div class="size-203 flex-c-m respon6">
										Color
									</div>

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
											<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-minus"></i>
											</div>

											<input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product" value="1">

											<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-plus"></i>
											</div>
										</div>

										<button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
											Add to cart
										</button>
									</div>
								</div>	
							</div>

							<!--  -->
							<div class="flex-w flex-m p-l-100 p-t-40 respon7">
								<div class="flex-m bor9 p-r-10 m-r-11">
									<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
										<i class="zmdi zmdi-favorite"></i>
									</a>
								</div>

								<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">
									<i class="fa fa-facebook"></i>
								</a>

								<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">
									<i class="fa fa-twitter"></i>
								</a>

								<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">
									<i class="fa fa-google-plus"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
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
		$(".js-select2").each(function(){
			$(this).select2({
				minimumResultsForSearch: 20,
				dropdownParent: $(this).next('.dropDownSelect2')
			});
		})
	</script>
<!--===============================================================================================-->
	<script src="MainPage/vendor/daterangepicker/moment.min.js"></script>
	<script src="MainPage/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="MainPage/vendor/slick/slick.min.js"></script>
	<script src="MainPage/js/slick-custom.js"></script>
<!--===============================================================================================-->
	<script src="MainPage/vendor/parallax100/parallax100.js"></script>
	<script>
        $('.parallax100').parallax100();
	</script>
<!--===============================================================================================-->
	<script src="MainPage/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
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
<!--===============================================================================================-->
	<script src="MainPage/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
	<script src="MainPage/vendor/sweetalert/sweetalert.min.js"></script>
	<script>
		$('.js-addwish-b2').on('click', function(e){
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

		/*---------------------------------------------*/

		$('.js-addcart-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
			$(this).on('click', function(){
				swal(nameProduct, "is added to cart !", "success");
			});
		});
	</script>
<!--===============================================================================================-->
	<script src="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
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
<!--===============================================================================================-->
	<script src="MainPage/js/main.js"></script>

</body>
</html>
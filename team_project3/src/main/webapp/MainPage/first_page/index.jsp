<%@page import="vo.SellerProductDTO"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.SellerDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!DOCTYPE html>
<html lang="en">
<head>
<title>COZA STORE 공식 온라인 스토어</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="MainPage/js/jquery-3.6.0.js"></script>
<style type="text/css">
.selectbox {
	text-align: right;
	position: relative;
	width: 200px; /* 너비설정 */
	border: 1px solid #999; /* 테두리 설정 */
	z-index: 1;
}
</style>
<script type="text/javascript">
var sCode = '${sCode}' //좋아요 기능을 위하여 sCode 값을 script 위에 뿌려줌
var sell_num


function recCount(sell_num,thisRow) { //  좋아요 갯수
	
// 	alert("<좋아요갯수세기>--판매번호"+sell_num);
	$.ajax({
		url: "ProductRecCount.pr",
        type: "POST",
        data: {
     		sell_num : sell_num
        },
        success: function (data) {   //성공시 값을 반환못함 -> 
       		console.log(data);
//         	$("thisplace").innerHTML(data);
				debugger;
        }
	})

};	
	
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
	<link rel="stylesheet" type="text/css" href="MainPage/css/community.css">
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
									Women Collection 2022
								</span>
							</div>
								
							<div class="layer-slick1 animated visible-false" data-appear="fadeInUp" data-delay="800">
								<h2 class="ltext-104 txt-center cl0 p-t-22 p-b-40 respon1">
									New arrivals
								</h2>
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
						</div>
					</div>
				</div>
			</div>

			<div class="wrap-slick1-dots p-lr-10"></div>
		</div>
	</section>

	<!-- Product -->
	<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<table border="1" style="border: none; background:white;">
		
<!--     </table>  구매물품 뿌려주는 곳    -->
     	<div class="container">
     <hr>
     	<h2>Favorite Brand</h2>
     	<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <div class="row multi-columns-row">
      			
     		 	<form>
            <c:forEach items="${likearticleList}" var="likearticleList">
              <div class="col-sm-6 col-md-3 col-lg-3 ">
              
                <div class="shop-item">   <!-- 여기 -->
               <input class="sell_num" value="${likearticleList.sell_num}" readonly="readonly" style="border: none;">
                <a href="ProductDetailPro.pr?sell_num=${likearticleList.sell_num}&sell_brand=${likearticleList.sell_brand}">
                 <img src="./Upload/sell_img/${likearticleList.sell_img_real_name}" width="300px" height="300px" alt="Accessories Pack"/></a>
                
                   <a href="ProductDetailPro.pr?sell_num=${likearticleList.sell_num}&sell_brand=${likearticleList.sell_brand}"> 
                   </a>
                 <i class="fa fa-eye marginEye"></i><span style="text-align: right;">${likearticleList.sell_readcount } </span>|
                 <span>
               	     <span>
						<span class="w3-border w3-center w3-padding">      <!-- 좋아요 기능을 위한 스크립트(Ajax)는 892행  -->
							<c:if test="${ sCode  eq null }">
								<span class="rec_count">세션이 만료되었습니다-로그인필요!</span>	
							</c:if>
							<c:if test="${ sCode ne null }">
								<i class="w3-button w3-black w3-round" id="rec_update">
									<i class="fa fa-heart" style="font-size:16px;color:red"></i>
									<span class="rec_count">${likearticleList.sell_likecount }</span>
								</i> 
							</c:if>
						</span>
					</span>
				</span>
              		 <h5><a href="ProductDetailPro.pr?sell_num=${likearticleList.sell_num}&sell_brand=${likearticleList.sell_brand}" class="sell_listTile">   ${likearticleList.sell_title }</a></h5>
                 	 <h5><a href="ProductDetailPro.pr?sell_num=${likearticleList.sell_num}&sell_brand=${likearticleList.sell_brand}" class="sell_listBrand"> ${likearticleList.sell_brand }</a></h5>
								</div>
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
		</table>

		
	</div>
	
	<hr>
	
	

	<div class="container" >
		<div class="flex-w flex-sb-m p-b-52">
			<div class="flex-w flex-c-m m-tb-10">
			
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
								name="ProductSearch"  class="form-control form-control"></td>
							<td><input type="submit" value="검색" placeholder="Search" class="btn_sell_index">
							</td>
						</tr>
					</table>
				</form>
			</div>

		</div>
		
		<h2>New Release</h2>
     	 <div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<table border="1" style="border: none; background:white;">
<!--     </table>  구매물품 뿌려주는 곳    -->
		
     	<div class="container">
     	 
            <div class="row multi-columns-row">
          
      
     		 	<form>
            <c:forEach items="${mainarticleList}" var="mainarticleList">
              <div class="col-sm-6 col-md-3 col-lg-3 ">
              
                <div class="shop-item">   <!-- 여기 -->
               <input class="sell_num" value="${mainarticleList.sell_num}" readonly="readonly" style="border: none;">
                <a href="ProductDetailPro.pr?sell_num=${mainarticleList.sell_num}&sell_brand=${mainarticleList.sell_brand}">
                 <img src="./Upload/sell_img/${mainarticleList.sell_img_real_name}" width="300px" height="300px" alt="Accessories Pack"/></a>
                
                   <a href="ProductDetailPro.pr?sell_num=${mainarticleList.sell_num}&sell_brand=${mainarticleList.sell_brand}"> 
                   </a>
                 <i class="fa fa-eye marginEye"></i><span style="text-align: right;">${mainarticleList.sell_readcount } </span>|
                 <span>
               	     <span>
						<span class="w3-border w3-center w3-padding">      <!-- 좋아요 기능을 위한 스크립트(Ajax)는 892행  -->
							<c:if test="${ sCode  eq null }">
								<span class="rec_count">세션이 만료되었습니다-로그인필요!</span>	
							</c:if>
							<c:if test="${ sCode ne null }">
								<i class="w3-button w3-black w3-round" id="rec_update">
									<i class="fa fa-heart" style="font-size:16px;color:red"></i>
									<span class="rec_count">${mainarticleList.sell_likecount }</span>
								</i> 
							</c:if>
						</span>
					</span>
				</span>
              		 <h5><a href="ProductDetailPro.pr?sell_num=${mainarticleList.sell_num}&sell_brand=${mainarticleList.sell_brand}" class="sell_listTile">   ${mainarticleList.sell_title }</a></h5>
                 	 <h5><a href="ProductDetailPro.pr?sell_num=${mainarticleList.sell_num}&sell_brand=${mainarticleList.sell_brand}" class="sell_listBrand"> ${mainarticleList.sell_brand }</a></h5>
								</div>
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
		</table>

		
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
	
	<script type="text/javascript">// 추천버튼 클릭시(추천 추가 또는 추천 제거)

	$(function(){
		$(".fa-heart").click(function(a){
		var thisRow = $(this).closest('div');  //하트표시 줄의 부모(div)를 찾아  thisRow값에 저장
		var thisplace = thisRow.find('span:eq(4)')[0];
		
		var sell_num =  thisRow.find('input').val(); //thisRow의 하위 요소인 find(input)=>input이라는 value를 찾아 sell_num에 저장 == ${sell_num}
		
			$.ajax({
				url: "ProductRecUpdate.pr",
	            type: "POST",
	            data: {
	         		sell_num : sell_num,    
	                id: sCode					 
	            },
	            success: function (data) {
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
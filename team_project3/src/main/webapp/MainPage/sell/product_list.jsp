<%@page import="vo.SellerProductDTO"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.SellerDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html >
<head>
<title>Shop</title>
<script src="MainPage/assets/js/jquery-3.6.0.js"></script>
<script type="text/javascript" >
//좋아요 기능을 위하여 sCode 값을 script 위에 뿌려줌
var sCode = '${sCode}'
var sell_num

function filter(value){
	
			if(value =="brand") {
				var tagSpan = document.getElementById("checkbox2");	
				tagSpan.innerHTML =  
									"<a href='ProductBrandPro.pr?sell_brand=에르메스' style='font-size: 1.5em' > Hermes </a> &nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=샤넬' style='font-size: 1.5em' > Chanel </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=베테가베네타' style='font-size: 1.5em' > Bottega Veneta </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=프라다' style='font-size: 1.5em' > Prada </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=롤렉스' style='font-size: 1.5em'> Rolex </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=디올' style='font-size: 1.5em'> Dior </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=셀린느' style='font-size: 1.5em'> Celine </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=발렌시아가' style='font-size: 1.5em'> Balenciaga </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=까르띠에' style='font-size: 1.5em'> Cartier </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=펜디' style='font-size: 1.5em'> Fendi </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=고야드' style='font-size: 1.5em'> Goyard </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=미우미우' style='font-size: 1.5em'> MiuMiu </a>"
									+"<a href='ProductBrandPro.pr?sell_brand=르메르' style='font-size: 1.5em'> Lemaire </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=로에베' style='font-size: 1.5em'> Loewe </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=마르니' style='font-size: 1.5em'> Marni </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=오프화이트' style='font-size: 1.5em'> Off-White </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=슈프림' style='font-size: 1.5em'> Supreme </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=톰브라운' style='font-size: 1.5em'> Jordan </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=루이비통' style='font-size: 1.5em'> Louis Vuitton </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=조던' style='font-size: 1.5em'> jordan </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=몽클레어' style='font-size: 1.5em'> Moncler </a>&nbsp;&nbsp;"
									+"<a href='ProductBrandPro.pr?sell_brand=질센더' style='font-size: 1.5em'> Jil Sander </a>";
			} 
			else if(value =="category") {
				var tagSpan = document.getElementById("checkbox2");
				tagSpan.innerHTML = "<a></a>&nbsp;&nbsp;&nbsp;&nbsp;"
								   +"<a href='ProductCategoryPro.pr?sell_category=상의' style='font-size: 1.5em'> Top </a>&nbsp;&nbsp;&nbsp;"
								   +"<a href='ProductCategoryPro.pr?sell_category=하의' style='font-size: 1.5em'> Bottom </a>&nbsp;&nbsp;&nbsp;"
								   +"<a href='ProductCategoryPro.pr?sell_category=신발' style='font-size: 1.5em'> Shoes </a>&nbsp;&nbsp;&nbsp;"
								   +"<a href='ProductCategoryPro.pr?sell_category=잡화' style='font-size: 1.5em'> Accessories </a>";	   
			}
			else if(value =="price") {
				var tagSpan = document.getElementById("checkbox2");
				tagSpan.innerHTML = "<a></a>&nbsp;"
					+ "<a href='ProductPricePro.pr?sell_price=100000' style='font-size: 1.0em'> 100,000원 이하 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductPricePro.pr?sell_price=500000' style='font-size: 1.0em'> 100,000원 ~ 500,000원 </a>&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductPricePro.pr?sell_price=1000000' style='font-size: 1.0em'> 500,000원 ~ 1,000,000원 </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductPricePro.pr?sell_price=5000000' style='font-size: 1.0em'> 1,000,000원 ~ 5,000,000원 </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductPricePro.pr?sell_price=10000000' style='font-size: 1.0em'> 5,000,000원 ~ 10,000,000원 </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<a href='ProductPricePro.pr?sell_price=20000000' style='font-size: 1.0em'> 10,000,000원 이상 </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		
}

</script>  
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="icon" type="image/png" href="MainPage/images/icons/favicon.png"/>
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/bootstrap/css/bootstrap.min.css">
   <link rel="stylesheet" type="text/css" href="MainPage/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
   <link rel="stylesheet" type="text/css" href="MainPage/fonts/iconic/css/material-design-iconic-font.min.css">
   <link rel="stylesheet" type="text/css" href="MainPage/fonts/linearicons-v1.0.0/icon-font.min.css">
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/animate/animate.css">
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/css-hamburgers/hamburgers.min.css">
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/animsition/css/animsition.min.css">    <!-- 여기인거같기도 -->
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/select2/select2.min.css">
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/daterangepicker/daterangepicker.css">
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/slick/slick.css">
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/MagnificPopup/magnific-popup.css">
   <link rel="stylesheet" type="text/css" href="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.css">
   <link rel="stylesheet" type="text/css" href="MainPage/css/util.css">
   <link rel="stylesheet" type="text/css" href="MainPage/css/main.css">
   <link rel="stylesheet" type="text/css" href="MainPage/css/community.css">
   
<style type="text/css">
	a {
	    color: #333;
	}
</style>
</head>
<body >

	<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
	<c:set var="startPage" value="${pageInfo.getStartPage() }" />
	<c:set var="endPage" value="${pageInfo.getEndPage() }" />
	<c:set var="listCount" value="${pageInfo.getListCount() }" />
   
   <!-- Header -->
   <header class="header-v4">
      <!-- Header desktop -->
      <div class="container-menu-desktop">
         <!-- Topbar -->

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
	<!-- 여기까지 헤더 -->
	<!-- Cart -->
	<jsp:include page="/MainPage/menu/pc_shopping cart.jsp" />
<!--     우리가 필터링으로 구분해줘야될 곳 -->
   <!-- Product -->

	<div class="container">
		<div class="flex-w flex-sb-m p-b-52">
			<div class="flex-w flex-l-m filter-tope-group m-tb-10"></div>

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
							<td><input type="text" placeholder="상품을 입력해주세요." name="ProductSearch"  class="form-control form-control"></td>
							<td><input type="submit" value="검색" placeholder="Search" class="btn_sell_index">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<!-- Filter -->
			<div class="dis-none panel-filter w-full p-t-10">
				<div class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm" style="justify-content: center;">
					<div id="checkbox1">
						<table>
							<tr>
								<th><input type="button" style="font-size: 35px; padding:1px;"
									class="filter-link stext-106 trans-04" name=filter
									value="brand" onclick="filter(this.value)"></th>
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<th><input type="button" style="font-size: 35px; padding:1px; "
									class="filter-link stext-106 trans-04" name=filter
									value="category" onclick="filter(this.value)"></th>
									<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								<th><input type=button style="font-size: 35px; padding:1px; "
									class="filter-link stext-106 trans-04" name=filter
									value="price" onclick="filter(this.value)"></th>
							</tr>
						</table>
						<br>
					</div>
					<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<div id="checkbox2" class="filter-link stext-106 trans-04"
						style="vertical-align: bottom; display: inline-block; margin-bottom: 2px;"></div>
				</div>
			</div>
		</div>

		<!-- 여기서부터 사진 상세보기로 서블릿만들기 -->
		 <table border="1" style="border: none; background:white;">
<!--     </table>  구매물품 뿌려주는 곳    -->
     	<div class="container">
            <div class="row multi-columns-row">
      
     		 	<form>
            <c:forEach items="${articleList}" var="articleList">
              <div class="col-sm-6 col-md-3 col-lg-3 ">
              
                <div class="shop-item">   <!-- 여기 -->
               <input class="sell_num" value="${articleList.sell_num}" readonly="readonly" style="border: none;">
                <a href="ProductDetailPro.pr?sell_num=${articleList.sell_num}&sell_brand=${articleList.sell_brand}">
                 <img src="./Upload/sell_img/${articleList.sell_img_real_name}" width="300px" height="400px" alt="Accessories Pack"/></a>
                
                   <a href="ProductDetailPro.pr?sell_num=${articleList.sell_num}&sell_brand=${articleList.sell_brand}"> 
                   </a>
                 <i class="fa fa-eye marginEye"></i><span style="text-align: right;">${articleList.sell_readcount } </span>|
                 <span>
               	     <span>
						<span class="w3-border w3-center w3-padding">      <!-- 좋아요 기능을 위한 스크립트(Ajax)는 892행  -->
							<c:if test="${ sCode  eq null }">
								<span class="rec_count">세션이 만료되었습니다-로그인필요!</span>	
							</c:if>
							<c:if test="${ sCode ne null }">
								<i class="w3-button w3-black w3-round" id="rec_update">
									<i class="fa fa-heart" style="font-size:16px;color:red"></i>
									<span class="rec_count">${articleList.sell_likecount }</span>
								</i> 
							</c:if>
						</span>
					</span>
				</span>
              		 <h5><a href="ProductDetailPro.pr?sell_num=${articleList.sell_num}&sell_brand=${articleList.sell_brand}" class="sell_listTile">   ${articleList.sell_title }</a></h5>
                 	 <h5><a href="ProductDetailPro.pr?sell_num=${articleList.sell_num}&sell_brand=${articleList.sell_brand}" class="sell_listBrand"> ${articleList.sell_brand }</a></h5>
								</div>
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
		</table>

		<div class="flex-c-m flex-w w-full p-t-45">
			<section id="pageList">
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${pageNum eq i}">
					${i }
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
                           <div class="item-slick3" data-thumb="images/product-detail-01.jpg">
                              <div class="wrap-pic-w pos-relative">
                                 <img src="MainPage/images/product-detail-01.jpg" alt="IMG-PRODUCT">

                                 <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="MainPage/images/product-detail-01.jpg">
                                    <i class="fa fa-expand"></i>
                                 </a>
                              </div>
                           </div>

                           <div class="item-slick3" data-thumb="images/product-detail-02.jpg">
                              <div class="wrap-pic-w pos-relative">
                                 <img src="MainPage/images/product-detail-02.jpg" alt="IMG-PRODUCT">

                                 <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="MainPage/images/product-detail-02.jpg">
                                    <i class="fa fa-expand"></i>
                                 </a>
                              </div>
                           </div>

                           <div class="item-slick3" data-thumb="images/product-detail-03.jpg">
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
            swal(nameProduct, "is added to cart !", "success");
         });
      });
   
   </script>
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
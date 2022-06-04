<%@page import="vo.SellerProductDTO"%>
<%@page import="vo.MemberBean"%>
<%@page import="vo.SellerDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	

String member_nickname =(String)session.getAttribute("sNickname");
String sell_member_code =(String)session.getAttribute("sCode");


SellerProductDTO sellerDTO = (SellerProductDTO)request.getAttribute("sellerDTO");
MemberBean memberbean = (MemberBean)request.getAttribute("memberBean");
int charge = sellerDTO.getSell_price() /10; //검수비 판매가격 /10
int price = sellerDTO.getSell_price()+charge+3000;     //최종 판매가격+수수료+배송비
// int point1 = memberbean.getMember_info_detail_point();
%>
 
<!DOCTYPE html>
<html lang="en">
<head>
	<title>succeedProduct</title>

    
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<link rel="stylesheet" type="text/css" href="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.css">
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
<!-- 			<!-- Topbar --> 
<!-- 			<div class="top-bar"> -->
<!-- 				<div class="content-topbar flex-sb-m h-full container"> -->
<!-- 					<div class="left-top-bar"> -->
<!-- 						Free shipping for standard order over $100 -->
<!-- 					</div> -->

<!-- 					<div class="right-top-bar flex-w h-full"> -->
<!-- 						<a href="#" class="flex-c-m trans-04 p-lr-25"> -->
<!-- 							Help & FAQs -->
<!-- 						</a> -->

<!-- 						<a href="#" class="flex-c-m trans-04 p-lr-25"> -->
<!-- 							My Account -->
<!-- 						</a> -->

<!-- 						<a href="#" class="flex-c-m trans-04 p-lr-25"> -->
<!-- 							EN -->
<!-- 						</a> -->

<!-- 						<a href="#" class="flex-c-m trans-04 p-lr-25"> -->
<!-- 							USD -->
<!-- 						</a> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->

			<div class="wrap-menu-desktop how-shadow1">
				<nav class="limiter-menu-desktop container">
					
					<!-- Logo desktop -->		
					<a href="#" class="logo">
						<img src="MainPage/images/icons/logo-01.png" alt="IMG-LOGO">
					</a>

					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
							<li>
								<a href="MainPage.pr">Home</a>
<!-- 								<ul class="sub-menu"> -->
<!-- 									<li><a href="index.html">Homepage 1</a></li> -->
<!-- 									<li><a href="home-02.html">Homepage 2</a></li> -->
<!-- 									<li><a href="home-03.html">Homepage 3</a></li> -->
<!-- 								</ul> -->
							</li>

							<li>
								<a href="Product.pr">Shop</a>
							</li>

<!-- 							<li class="label1" data-label1="hot"> -->
<!-- 								<a href="shoping-cart.html">Features</a> -->
<!-- 							</li> -->

							<li>
								<a href="SellForm.pr">Sell</a>
							</li>

							<li>
								<a href="CommunityNotice.ma">Community</a>
							</li>
			
							<li>
								<a href="Contact.pr">Contact</a>
							</li>
						</ul>
					</div>	

					<!-- Icon header -->
					<div class="wrap-icon-header flex-w flex-r-m">
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
							<i class="zmdi zmdi-search"></i>
						</div>

						<div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="2">
							<i class="zmdi zmdi-shopping-cart"></i>
						</div>

						<a href="#" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti" data-notify="0">
							<i class="zmdi zmdi-favorite-outline"></i>
						</a>
					</div>
				</nav>
			</div>	
		</div>

		<!-- Header Mobile -->
		<div class="wrap-header-mobile">
			<!-- Logo moblie -->		
			<div class="logo-mobile">
				<a href="MainPage.pr"><img src="MainPage/images/icons/logo-01.png" alt="IMG-LOGO"></a>
			</div>

			<!-- Icon header -->
			<div class="wrap-icon-header flex-w flex-r-m m-r-15">
				<div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 js-show-modal-search">
					<i class="zmdi zmdi-search"></i>
				</div>

				<div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart" data-notify="2">
					<i class="zmdi zmdi-shopping-cart"></i>
				</div>

				<a href="#" class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti" data-notify="0">
					<i class="zmdi zmdi-favorite-outline"></i>
				</a>
			</div>
	
			<!-- Button show menu -->
			<div class="btn-show-menu-mobile hamburger hamburger--squeeze">
				<span class="hamburger-box">
					<span class="hamburger-inner"></span>
				</span>
			</div>
		</div>


		<!-- Menu Mobile -->
		<div class="menu-mobile">
			<ul class="topbar-mobile">
				<li>
					<div class="left-top-bar">
						Free shipping for standard order over $100
					</div>
				</li>

				<li>
					<div class="right-top-bar flex-w h-full">
						<a href="#" class="flex-c-m p-lr-10 trans-04">
							Help & FAQs
						</a>

						<a href="#" class="flex-c-m p-lr-10 trans-04">
							My Account
						</a>

						<a href="#" class="flex-c-m p-lr-10 trans-04">
							EN
						</a>

						<a href="#" class="flex-c-m p-lr-10 trans-04">
							USD
						</a>
					</div>
				</li>
			</ul>

			
				<ul class="main-menu">
					<li>
						<a href="MainPage.pr">Home</a>
<!-- 						<ul class="sub-menu"> -->
<!-- 							<li><a href="index.html">Homepage 1</a></li> -->
<!-- 							<li><a href="home-02.html">Homepage 2</a></li> -->
<!-- 							<li><a href="home-03.html">Homepage 3</a></li> -->
<!-- 						</ul> -->
					</li>

					<li>
						<a href="Product.pr">Shop</a>
					</li>

					
					<li>
						<a href="SellForm.pr">Sell</a>
					</li>
					<li>
						<a href="CommunityNotice.ma">Community</a>
					</li>

					<li>
						<a href="Contact.pr">Contact</a>
					</li>
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
					<input class="plh3" type="text" name="search" placeholder="Search...">
				</form>
			</div>
		</div>
	</header>

	<!-- Cart -->
	<div class="wrap-header-cart js-panel-cart">
		<div class="s-full js-hide-cart"></div>

		<div class="header-cart flex-col-l p-l-65 p-r-25">
			<div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2">
					Your Cart
				</span>

				<div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
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
								White Shirt Pleat
							</a>

							<span class="header-cart-item-info">
								1 x $19.00
							</span>
						</div>
					</li>

					<li class="header-cart-item flex-w flex-t m-b-12">
						<div class="header-cart-item-img">
							<img src="MainPage/images/item-cart-02.jpg" alt="IMG">
						</div>

						<div class="header-cart-item-txt p-t-8">
							<a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
								Converse All Star
							</a>

							<span class="header-cart-item-info">
								1 x $39.00
							</span>
						</div>
					</li>

					<li class="header-cart-item flex-w flex-t m-b-12">
						<div class="header-cart-item-img">
							<img src="MainPage/images/item-cart-03.jpg" alt="IMG">
						</div>

						<div class="header-cart-item-txt p-t-8">
							<a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
								Nixon Porter Leather
							</a>

							<span class="header-cart-item-info">
								1 x $17.00
							</span>
						</div>
					</li>
				</ul>
				
				<div class="w-full">
					<div class="header-cart-total w-full p-tb-40">
						Total: $75.00
					</div>

					<div class="header-cart-buttons flex-w w-full">
						<a href="shoping-cart.html" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
							View Cart
						</a>

						<a href="shoping-cart.html" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
							Check Out
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

		
	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="index.html" class="stext-109 cl8 hov-cl1 trans-04">
				Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<span class="stext-109 cl4">
				Shoping Cart
			</span>
		</div>
	</div>
	
	<center>
		<h2>결제 완료! </h2>
	</center>
<!-- 	<form action="SucceedProduct.pr" class="bg0 p-t-75 p-b-85"> -->
		<div class="container" >
			<div class="row" >
				
				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
					<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
						<h5 class="mtext-109 cl2 p-b-30">
							제품정보
						</h5>
						<div class="flex-w flex-t bor12 p-b-13">
							<div class="size-208">
								<span class="stext-110 cl2">
									<img src="./Upload/sell_img/<%=sellerDTO.getSell_img_name() %>" width="150" height="150">	
						
								</span>
							</div>
							<div class="size-209">
								
								title:<%=sellerDTO.getSell_title() %><br>
								brand:<%=sellerDTO.getSell_brand() %><br>
								size:<%=sellerDTO.getSell_size() %><br>
								price:<%=sellerDTO.getSell_price() %><br>
							
							</div>
							
						</div>
						<div class="flex-w flex-t bor12 p-t-15 p-b-30">
						<h5 class="mtext-109 cl2 p-b-30">
							배송정보
						</h5>
							<div class="size-208 w-full-ssm">
								<span class="stext-110 cl2">
								</span><br>
								<span class="stext-110 cl2">
									address:<%=memberbean.getMember_info_address() %>
								</span><br>
								<span class="stext-110 cl2">
									address:<%=memberbean.getMember_info_address_detail() %>	
								</span><br>
								<span class="stext-110 cl2">
									post:<%=memberbean.getMember_info_post_code() %>	
								</span><br> 
								<span class="stext-190 cl2">
 									name:<%=memberbean.getMember_info_name() %>
								</span><br>	
								<span class="stext-190 cl2">
 									phone:<%=memberbean.getMember_info_phone() %>
								</span><br>	
  					   		</div>
							<div class="flex-w flex-t bor12 p-t-15 p-b-30">
						<h5 class="mtext-109 cl2 p-b-30">
							구매정보
						</h5>
							<div class="size-208 w-full-ssm">
								<span class="stext-110 cl2">
								</span><br>
								<span class="stext-110 cl2">
									point:<%=memberbean.getMember_info_detail_point() %>
								</span><br>
								<span class="stext-110 cl2">
									acc_money:<%=memberbean.getMember_info_detail_acc_money()%>	
								</span><br>
								<span class="stext-110 cl2">
									회원등급 :<%=memberbean.getMember_info_post_code() %>	
								</span><br> 
								<span class="stext-190 cl2">
 									최종금액:<%=price%>
								</span><br>	
								
<!--  									<P>우리 COZA_STORE에서는 택배비+검수 수수료 합산하여 결제 됨을 알려드립니다.</P> -->
									
  					   		</div>
							
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm">
								<p class="stext-111 cl6 p-t-2"> </p>
								
							
								<div class="p-t-15">
									<span class="stext-112 cl8">
										
									</span>

									
								</div>
							</div>
						</div>

						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2">
									
								</span>
							</div>

							<div class="size-209 p-t-1">
								<span class="mtext-110 cl2">
									
								</span>
							</div>
						</div>
					  <input type="button" value="홈으로" onclick="location.href='MainPage.pr'"  class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer"> 
					 
					  <input type="button" value="마이페이지" onclick="location.href='MainPage.pr'"  class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer"> 
					</div>
				</div>
			</div>
		</div>
<!-- 	</form> -->

	
	
		

	<!-- Footer -->
	<footer class="bg3 p-t-75 p-b-32">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">
						Categories
					</h4>

					<ul>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Women
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Men
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Shoes
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Watches
							</a>
						</li>
					</ul>
				</div>

				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">
						Help
					</h4>

					<ul>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Track Order
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Returns 
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								Shipping
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								FAQs
							</a>
						</li>
					</ul>
				</div>

				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">
						GET IN TOUCH
					</h4>

					<p class="stext-107 cl7 size-201">
						Any questions? Let us know in store at 8th floor, 379 Hudson St, New York, NY 10018 or call us on (+1) 96 716 6879
					</p>

					<div class="p-t-27">
						<a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
							<i class="fa fa-facebook"></i>
						</a>

						<a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
							<i class="fa fa-instagram"></i>
						</a>

						<a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
							<i class="fa fa-pinterest-p"></i>
						</a>
					</div>
				</div>

				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">
						Newsletter
					</h4>

					<form>
						<div class="wrap-input1 w-full p-b-4">
							<input class="input1 bg-none plh1 stext-107 cl7" type="text" name="email" placeholder="email@example.com">
							<div class="focus-input1 trans-04"></div>
						</div>

						<div class="p-t-18">
							<button class="flex-c-m stext-101 cl0 size-103 bg1 bor1 hov-btn2 p-lr-15 trans-04">
								Subscribe
							</button>
						</div>
					</form>
				</div>
			</div>

			<div class="p-t-40">
				<div class="flex-c-m flex-w p-b-18">
					<a href="#" class="m-all-1">
						<img src="MainPage/images/icons/icon-pay-01.png" alt="ICON-PAY">
					</a>

					<a href="#" class="m-all-1">
						<img src="MainPage/images/icons/icon-pay-02.png" alt="ICON-PAY">
					</a>

					<a href="#" class="m-all-1">
						<img src="MainPage/images/icons/icon-pay-03.png" alt="ICON-PAY">
					</a>

					<a href="#" class="m-all-1">
						<img src="MainPage/images/icons/icon-pay-04.png" alt="ICON-PAY">
					</a>

					<a href="#" class="m-all-1">
						<img src="MainPage/images/icons/icon-pay-05.png" alt="ICON-PAY">
					</a>
				</div>

				<p class="stext-107 cl6 txt-center">
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | Made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a> &amp; distributed by <a href="https://themewagon.com" target="_blank">ThemeWagon</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->

				</p>
			</div>
		</div>
	</footer>


	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
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
		function sub() {
			var prePoint  = 6400;
			var subPoint  = document.getElementById("MemberPoint").value;//사용 할 포인트
			document.getElementById("realPoint").value =	prePoint - subPoint ;//차감후 남은 포인트
			document.getElementById("point").value = subPoint;//최종 결제창에 띄울 포인트(사용할 포인트)
			
			$("#realPoint").append("남은포인트:"+document.getElementById("realPoint").value);
		}
		
	
	</script>
	<script>
		$(".js-select2").each(function(){
			$(this).select2({
				minimumResultsForSearch: 20,
				dropdownParent: $(this).next('.dropDownSelect2')
			});
		})
	</script>
<!--===============================================================================================-->
	<script src="MainPage/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
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
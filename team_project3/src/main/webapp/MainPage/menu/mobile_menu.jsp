<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String code = (String)session.getAttribute("sCode");
	String nickname = (String)session.getAttribute("sNickname");
%>    
	<div class="header-cart flex-col-l p-l-65 p-r-25">
		<ul class="sidebar-link w-full">
				<li class="p-b-13">
					<a href="index.html" class="stext-102 cl2 hov-cl1 trans-04">
						Home
					</a>
				</li>

				<li class="p-b-13">
					<a href="Mypage.ma?member_code=<%=code %>" class="stext-102 cl2 hov-cl1 trans-04">
						MyPage
					</a>
				</li>

				<li class="p-b-13">
					<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
						My Account
					</a>
				</li>

				<li class="p-b-13">
					<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
						Track Oder
					</a>
				</li>

				<li class="p-b-13">
					<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
						Refunds
					</a>
				</li>

				<li class="p-b-13">
					<a href="javascript:void(0)" onclick="confirmLogout()" class="stext-102 cl2 hov-cl1 trans-04">
						LogOut
					</a>
				</li>
			</ul>
		
		<div class="header-cart-content flex-w js-pscroll">
			<ul class="header-cart-wrapitem w-full">
				<li class="header-cart-item flex-w flex-t m-b-12">
				</li>

				<li class="header-cart-item flex-w flex-t m-b-12">
			</ul>
			<div class="sidebar-gallery w-full p-tb-30">
<!-- 					<span class="mtext-101 cl5"> -->
<!-- 						@ CozaStore -->
<!-- 					</span> -->
				<div class="sidebar-gallery w-full">
				<span class="mtext-101 cl5">
					About Us
				</span>

				<p class="stext-108 cl6 p-t-27">
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur maximus vulputate hendrerit. Praesent faucibus erat vitae rutrum gravida. Vestibulum tempus mi enim, in molestie sem fermentum quis. 
				</p>
			

<!-- 						<div class="flex-w flex-sb p-t-36 gallery-lb"> -->
<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-01.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-01.jpg');"></a> -->
<!-- 							</div> -->

<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-02.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-02.jpg');"></a> -->
<!-- 							</div> -->

<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-03.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-03.jpg');"></a> -->
<!-- 							</div> -->

<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-04.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-04.jpg');"></a> -->
<!-- 							</div> -->

<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-05.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-05.jpg');"></a> -->
<!-- 							</div> -->

<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-06.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-06.jpg');"></a> -->
<!-- 							</div> -->

<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-07.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-07.jpg');"></a> -->
<!-- 							</div> -->

<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-08.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-08.jpg');"></a> -->
<!-- 							</div> -->

<!-- 							item gallery sidebar -->
<!-- 							<div class="wrap-item-gallery m-b-10"> -->
<!-- 								<a class="item-gallery bg-img1" href="MainPage/images/gallery-09.jpg" data-lightbox="gallery"  -->
<!-- 								style="background-image: url('images/gallery-09.jpg');"></a> -->
<!-- 							</div> -->
<!-- 						</div> -->
				</div>
			</div>
		</div>
	</div>
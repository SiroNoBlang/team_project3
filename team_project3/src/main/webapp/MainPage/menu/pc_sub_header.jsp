<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
					
<!-- Logo desktop -->		
<a href="MainPage.ma" class="logo">
	<img src="MainPage//images/icons/logo-01.png" alt="IMG-LOGO">
</a>

<!-- Menu desktop -->
<div class="menu-desktop">
	<ul class="main-menu">
		<li>
			<a href="MainPage.ma">Home</a>
		</li>

		<li>
			<a href="Product.pr">Shop</a>
		</li>

<!-- 		<li class="label1" data-label1="hot">
			<a href="shoping-cart.html">Features</a>
		</li> 
 -->
		<li>
			<a href="SellForm.pr">Sell</a>
		</li>


		<li class="menu">
			<a href="CommunityNotice.ma">Community</a>
			<ul class="sub-menu">
				<li><a href="CommunityNotice.ma">공지사항</a></li>
				<li><a href="CommunityEvent.ma">이벤트</a></li>
				<li><a href="CommunityQna.ma">Q&#38;A</a></li>
			</ul>
		</li>
		
		<li>
			<a href="Contact.pr?member_code=${sCode }">Contact</a>
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

	<div class="flex-c-m h-full p-r-25 bor6">
		<div class="icon-header-item c12 hov-cl1 trans-04 p-lr-11 js-show-sidebar">
			<i class="zmdi zmdi-account zmd-fw"></i>
		</div>
	</div>
</div>
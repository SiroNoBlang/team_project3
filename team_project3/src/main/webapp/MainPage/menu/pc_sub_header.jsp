<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
					
<!-- Logo desktop -->		
<a href="MainPage.pr" class="logo">
	<img src="MainPage//images/icons/logo-01.png" alt="IMG-LOGO">
</a>

<!-- Menu desktop -->
<div class="menu-desktop">
	<ul class="main-menu">
		<li>
			<a href="MainPage.pr">Home</a>
		</li>

		<li>
			<a href="Product.pr">Shop</a>
		</li>

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
	<div class="flex-c-m h-full p-r-25 bor6">
		<div class="icon-header-item c12 hov-cl1 trans-04 p-lr-11 js-show-sidebar">
			<i class="zmdi zmdi-account zmd-fw"></i>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="MainPage/js/jquery-3.6.0.js"></script>
<div class="wrap-header-mobile">
	<!-- Logo moblie -->		
<div class="logo-mobile">
	<a href="index.html"><img src="MainPage/images/icons/logo-01.png" alt="IMG-LOGO"></a>
</div>

<!-- Icon header -->
<div class="wrap-icon-header flex-w flex-r-m h-full m-r-15">
	<div class="flex-c-m h-full p-r-5">
		<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 icon-header-noti js-show-cart" data-notify="2">
			<i class="zmdi zmdi-account zmd-fw"></i>
		</div>
	</div>
</div>

<!-- Button show menu -->
	<div class="btn-show-menu-mobile hamburger hamburger--squeeze" id="chat">
		<span class="hamburger-box">
			<span class="hamburger-inner"></span>
		</span>
	</div>
</div>


<!-- Menu Mobile -->
<div class="menu-mobile">
	<div class="main-menu-m">
		<%@ include file="/MainPage/menu/chatBot.jsp" %>
	</div>
</div>





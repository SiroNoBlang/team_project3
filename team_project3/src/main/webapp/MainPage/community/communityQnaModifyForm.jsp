<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>community -  QnA 글쓰기 </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="MainPage/images/icons/favicon.png"/>
<link rel="stylesheet" type="text/css" href="MainPage/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="MainPage/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="MainPage/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css" href="MainPage/fonts/linearicons-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css" href="MainPage/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="MainPage/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="MainPage/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="MainPage/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="MainPage/vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="MainPage/css/util.css">
<link rel="stylesheet" type="text/css" href="MainPage/css/main.css">
<link rel="stylesheet" type="text/css" href="MainPage/css/community.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="MainPage/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(document).ready( function() {
	$("#qnaTypeId").change(function(){
		let title = $('input[name=qna_title]').val();
		title = title.substring(title.lastIndexOf("]")+1);
		
		$("#qnaTitleId").val("["+$(this).val()+"] " + title ).focus();
	});
});

</script>
</head>
<body class="animsition">
	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<!-- Topbar -->
					<!-- Topbar -->
<!-- 			<div class="top-bar">
				<div class="content-topbar flex-sb-m h-full container">
					<div class="left-top-bar">
						Free shipping for standard order over $100
					</div>

					<div class="right-top-bar flex-w h-full">
						<a href="#" class="flex-c-m trans-04 p-lr-25">
							Help & FAQs
						</a>

						<a href="#" class="flex-c-m trans-04 p-lr-25">
							My Account
						</a>

						<a href="#" class="flex-c-m trans-04 p-lr-25">
							EN
						</a>

						<a href="#" class="flex-c-m trans-04 p-lr-25">
							USD
						</a>
					</div>
				</div>
			</div>
 -->
		<!-- pc_sub_header -->
			<div class="wrap-menu-desktop how-shadow1" style="top:0px;">
				<nav class="limiter-menu-desktop container">
					<jsp:include page="/MainPage/menu/pc_sub_header.jsp"/>
				</nav>
			</div>	
		</div>

		<!-- PC_menu_Sidebar -->
		<jsp:include page="/MainPage/menu/pc_menu.jsp"/>

		<!-- Header Mobile -->
		<div class="wrap-header-mobile">
			<!-- Logo moblie -->		
			<div class="logo-mobile">
				<a href="index.html"><img src="MainPage/images/icons/logo-01.png" alt="IMG-LOGO"></a>
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

			<ul class="main-menu-m">
				<li>
					<a href="index.html">Home</a>
					<ul class="sub-menu-m">
						<li><a href="index.html">Homepage 1</a></li>
						<li><a href="home-02.html">Homepage 2</a></li>
						<li><a href="home-03.html">Homepage 3</a></li>
					</ul>
					<span class="arrow-main-menu-m">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</span>
				</li>

				<li>
					<a href="product.html">Shop</a>
				</li>

				<li>
					<a href="shoping-cart.html" class="label1 rs1" data-label1="hot">Features</a>
				</li>

				<li>
					<a href="blog.html">Blog</a>
				</li>

				<li>
					<a href="about.html">About</a>
				</li>

				<li>
					<a href="contact.html">Contact</a>
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
	<jsp:include page="/MainPage/menu/pc_shopping cart.jsp"/>


	<!-- Title page -->
	<section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('MainPage/images/bg-01.jpg');">
		<h2 class="ltext-105 cl0 txt-center">
			Community
		</h2>
	</section>	


	<!-- Community page -->
		<div id="com_container">
			<div class=" container d-flex justify-content-center" >     
		        <ul class="pagination shadow-lg">
		            <li class="page-item "><a class="page-link" href="CommunityNotice.ma"><small>공지사항</small></a></li>
		            <li class="page-item "><a class="page-link" href="CommunityEvent.ma"><small>이벤트</small></a></li>                        
		            <li class="page-item active"><a class="page-link " href="CommunityQna.ma"><small>Q&#38;A</small></a></li>  
		        </ul> 
		    </div>
	  </div>  
	    
	    
	<!-- 리스트 뷰 -->
	    		<div class="container">
	    		<form action="CommunityQnaModifyPro.ma" name="boardForm" method="post">
						<input type="hidden" name="qna_num" value="${param.qna_num}">
						<input type="hidden" name="page" value="${param.page}">
						<table class="table write" >
							<tr>
								<th><label for="qna_type">문의유형</label></th>
								<td style="text-align:left;">
									<select id="qnaTypeId" name="qna_type">
									  <option value="상품문의">상품문의</option>
									  <option value="배송문의">배송문의</option>
									  <option value="교환및반품문의">교환및반품문의</option>
									  <option value="주문변경및취소문의">주문변경및취소문의</option>
									  <option value="입금및결제문의">입금및결제문의</option>
									  <option value="기타문의">기타문의</option>
									</select>
								</td>
							</tr>
							<tr>
								<th><label for="qna_title">제목</label></th>
								<td><input type="text" name="qna_title" id="qnaTitleId"  required="required" value="${qnaArticle.getQna_title() }">
								</td>
							</tr>
							<tr>
								<th><label for="qna_nickname">작성자</label></th>
								<td><input type="text" name="qna_nickname" value="${sNickname }" readonly="readonly"></td>
							</tr>
							<tr>
								<th><label for="qna_date">작성일</label></th>
								<td><input type="text" name="qna_date" readonly="readonly" value="${qnaArticle.getQna_write_date() }">
								</td>
							</tr>
							<tr>
								<th><label for="qna_content">내용</label></th>
								<td>
									<textarea id="board_content" name="qna_content"  rows="20" cols="100">${qnaArticle.getQna_content() } </textarea>
								</td>
							</tr>
						</table>
						<br>
						<section class="pagination justify-content-center" >	
							<input class="page-item page-link listMargin"  type="submit" value="수정">
							<input class="page-item page-link listMargin"  type="reset" value="다시쓰기">
							<input class="page-item page-link listMargin"  type="button" value="취소" onclick="history.back()">
						</section>
					</form>
  				</div>
	
	<!-- Footer영역과 상단 이동 버튼-->
	<jsp:include page="/MainPage/menu/footer.jsp"/>

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
	<script src="MainPage/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
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
	<script src="MainPage/js/community.js"></script>
	
</body>
</html>
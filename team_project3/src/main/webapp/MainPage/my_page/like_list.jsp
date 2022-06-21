<%@page import="vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String code = (String) session.getAttribute("sCode");
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좋아요 리스트</title>
<style type="text/css">
	img {
		width: 100px;
	}

</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>       <!-- 결제기능 -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>    <!-- 결제기능 -->
<script type="text/javascript">
	function selectAll(selectAll)  {
	  const checkboxes 
	       = document.getElementsByName('items');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked;
	  })
	}		
</script>
  
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
</head>
<body class="animsition">
	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">

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
			찜목록
		</h2>
	</section>	
	
	<div id="com_container">
			<div class=" container d-flex justify-content-center" >     
		        <ul class="pagination shadow-lg">
		            <li class="page-item "><a class="page-link" href="Mypage.ma?member_code=${sCode }"><small>마이페이지</small></a></li>
		            <li class="page-item active "><a class="page-link" href="LikeList.ma?member_code=${sCode }"><small>찜목록</small></a></li>                        
		            <li class="page-item "><a class="page-link " href="BuyList.ma?member_code=${sCode }"><small>구매내역</small></a></li> 
		            <li class="page-item  "><a class="page-link " href="SellList.ma?member_code=${sCode }"><small>판매내역</small></a></li> 
		        </ul> 
		    </div>
	  </div>  
<body>
	<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
	<c:set var="startPage" value="${pageInfo.getStartPage() }" />
	<c:set var="endPage" value="${pageInfo.getEndPage() }" />
	<c:set var="listCount" value="${pageInfo.getListCount() }" />
	
	<div class="container">
	<form action="">
	<table class="table table-hover">
		<tr>
			<th scope="col" class="th-date"><input type="checkbox" onclick='selectAll(this)'>번호</th>
			<th scope="col" class="th-date">제목</th>
			<th scope="col" class="th-date">제품사진</th>
		</tr>
		
<%-- 		<c:if test="${not empty articleList and pageInfo.getListCount() > 0}"> --%>
			<c:forEach var="like_list" items="${articleList }">
				<c:set var="i" value="${i+1 }"/>
				<tr>
					<td><input type="checkbox" name="items">${i }</td>
					<td id="subject">
						<a href="ProductDetailPro.pr?sell_num=${like_list.sell_num}">
							${like_list.getSell_title() }
						</a>
					</td>
					<td>
					<img src="./Upload/sell_img/${like_list.getSell_img_real_name() }"> 
					</td>
				</tr>
			</c:forEach>
<%-- 		</c:if> --%>
			
	</table>
	<input type="button" value="구매하기" id="buyCard" >
	
	</form>	
		<!-- 페이징 처리 -->
		<section id="pageList"> 
		<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${pageNum > 1}">
				<li class="page-item"><a class="page-link" href="LikeList.ma?member_code=${sCode }&page=${pageNum - 1 }">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" >이전</a></li>
			</c:otherwise>
		</c:choose>
			
		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${pageNum eq i}">
					<li class="page-item"><a class="page-link">${i }</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="LikeList.ma?member_code=${sCode }&page=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${pageNum < maxPage}">
				<li class="page-item"><a class="page-link" href="LikeList.ma?member_code=${sCode }&page=${pageNum + 1 }">다음</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link">다음</a></li>
			</c:otherwise>
		</c:choose>
		</ul>
	</section>
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
   <script>
   
   
   $(document).ready(function () {
      
       $("#buyCard").click(function () { 
          debugger;         
//             var address1 = document.getElementById('address1').value;
//             var address2 = document.getElementById('address2').value;
//             var postcode = document.getElementById('postcode').value;
//             var name = document.getElementById('name').value;
//             var phone = document.getElementById('phone').value;
//             var price = document.getElementById('realPrice').value;  
//             var point = document.getElementById('MemberPoint').value;
//             var member_info_detail_acc_money = document.getElementById('realPrice').value; 
            
        var IMP = window.IMP; // 생략가능
        IMP.init('iamport'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
//             name : 'COZA STORE',
            amount : 100 ,     //price 100원으로 고치면 100원만나감
//             buyer_email : '${memberBean.member_email}',
            buyer_name : '1',//name,
            buyer_tel : '1',//phone,
            buyer_addr : '1',//address1,
            buyer_addr_detail: '1',//address2,
            buyer_postcode : '1',//postcode,
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
            if ( rsp.success ) {
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                }).done(function(data) {
                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid  //
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;  // merchant_uid : 'merchant_' + new Date().getTime(),
                        msg += '\결제 금액 : ' + rsp.paid_amount;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        
                        alert(msg);
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    }
                });
        /*
       member_code  ㅇ 무조건 있어야됨
      sell_num   ㅇ
      sell_price  ㅇ
      member_info_detail_acc_money  o
      ----------------------
      address1     x   var address1 = document.getElementById('address1').value;
      address2   x var address2 = document.getElementById('address2').value;
      postcode  x var postcode = document.getElementById('postcode').value;
      name     x var name = document.getElementById('name').value;
      phone    x var phone = document.getElementById('phone').value;
      point.val()}      x
      
      <input type="hidden" value="member_nickname" name="member_nickname">
      <input type="hidden" value="${sellerDTO.sell_num}" name="sell_num">
      <input type="hidden"value="${sellerDTO.sell_price}"name="member_info_detail_acc_money">
      <input type="hidden" value="${memberBean.discount_rate }">
      <input type="hidden"value="${sCode}" name="member_code"> 
      <input type="hidden"value="${sellerDTO.sell_price}" name="sell_price">
      ------------------------
      1 판매에 관한 내용은 무조건 있어야됨. 하지만 2 구매자 정보는 없을 수도있음 포인트 -> 3 내보유 포인트 값은 보여주지만, 보유포인트를 얼마쓸 지는 모름 
      */
       
                //성공시 이동할 페이지
                 location.href='SucceedProductAction.pr?member_code=${sCode}&sell_num=${sellerDTO.sell_num}&sell_price=${sellerDTO.sell_price}'
                          +'&address1='+address1+'&address2='+address2+'&postcode='+postcode+'&name='+name+'&phone='+phone+'&point='+point+'&member_info_detail_acc_money='+member_info_detail_acc_money;
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                
                alert(msg);
            }
        });
        
    });
});
    </script>
	<script src="MainPage/js/main.js"></script>
	<script src="MainPage/js/community.js"></script>
	
</body>
</html>
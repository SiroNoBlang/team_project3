<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<script type="text/javascript">
	function confirmLogout() {
	if(confirm("로그아웃 하시겠습니까?")) {
		location.href = "./Logout.ma";
		}
	} 
</script>
<aside class="wrap-sidebar js-sidebar">
<div class="s-full js-hide-sidebar"></div>

<div class="sidebar flex-col-l p-t-22 p-b-25">
	<div class="flex-r w-full p-b-30 p-r-27">
		<div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-sidebar">
			<i class="zmdi zmdi-close"></i>
		</div>
	</div>

	<div class="sidebar-content flex-w w-full p-lr-65 js-pscroll">
		<ul class="sidebar-link w-full">
			<li class="p-b-13">
				<a href="MainPage.pr" class="stext-102 cl2 hov-cl1 trans-04">
					Home
				</a>
			</li>
				
			<li class="p-b-13">
				<a href="Mypage.ma?member_code=${sCode }" class="stext-102 cl2 hov-cl1 trans-04">
					My Page
				</a>
			</li>

			<li class="p-b-13">
				<a href="SellList.ma?member_code=${sCode }" class="stext-102 cl2 hov-cl1 trans-04">
					SellList
				</a>
			</li>
			
			<li class="p-b-13">
				<a href="BuyList.ma?member_code=${sCode }" class="stext-102 cl2 hov-cl1 trans-04">
					BuyList
				</a>
			</li>

			<li class="p-b-13">
				<a href="#" class="stext-102 cl2 hov-cl1 trans-04">
					Refunds
				</a>
			</li>

			 <c:if test="${sGradeName eq 'Admin'}">
				<li class="p-b-13">
					<a href="MemberManagement.co" class="stext-102 cl2 hov-cl1 trans-04">
						Admin
					</a>
				</li>
			</c:if>
			
			<li class="p-b-13">
				<a href="javascript:void(0)" onclick="confirmLogout()" class="stext-102 cl2 hov-cl1 trans-04">
					LogOut
				</a>
			</li>
			
		</ul>
		<span class="mtext-101 cl5">
			@ CozaStore
		</span>
		<p class="stext-108 cl6">
			Many of the products are like new. We offer our customers a quality product at a reasonable price.
		</p>
		</div>
	</div>
</aside>

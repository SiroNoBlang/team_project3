<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	function confirmLogout() {
	if(confirm("로그아웃 하시겠습니까?")) {
		location.href = "./Logout.ma";
		}
	} 
</script>	
	<div class="header-cart flex-col-l p-l-65 p-r-25">
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
				<div class="sidebar-gallery w-full">
				<span class="mtext-101 cl5">
					About Us
				</span>

				<p class="stext-108 cl6 p-t-27">
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur maximus vulputate hendrerit. Praesent faucibus erat vitae rutrum gravida. Vestibulum tempus mi enim, in molestie sem fermentum quis. 
				</p>
				</div>
			</div>
		</div>
	</div>

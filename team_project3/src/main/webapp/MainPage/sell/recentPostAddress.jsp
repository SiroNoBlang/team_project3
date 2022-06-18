<%@page import="java.util.ArrayList"%>
<%@page import="vo.SellerAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
ArrayList<SellerAddress> arrAddressDTO = (ArrayList<SellerAddress>) request.getAttribute("arrAddressDTO");
String member_id = (String) session.getAttribute("sId"); //아이디 값 세션
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
crossorigin="anonymous"></script>
<script type="text/javascript">

	function useId(id) {
		// 자식창(나)에서 부모창(나를 연 창)을 제어하려면 window.opener.xxx 형태로 제어
		// => xxx 은 원래 해당 요소에 접근하는 문법 그대로 사용
		// 1. 사용 가능한 아이디를 부모창의 아이디 입력란에 표시
		window.opener.document.fr.id.value = id;
		// 2. 부모창의 전역변수 checkIdResult 값을 true 로 변경
		window.opener.checkIdResult = true;
		// 3. 자식창 닫기
		window.close();
	}
	$(document).ready(
		function() {
			$(".checkBtn").on(
					"click",
					function() {

						//현재 row의 정보 가져오기 
						var thisRow = $(this).closest('tr');

						//주소 input 값 가져오기
						var address_code = thisRow.find('td:eq(0)').find(
								'input').val();
						var address_detail = thisRow.find('td:eq(1)').find(
								'input').val();
						//섦졍 input 값 가져오기
						var post_code = thisRow.find('td:eq(2)').find(
								'input').val();
						var address_name = thisRow.find('td:eq(3)').find(
								'input').val();
						//섦졍 input 값 가져오기
						var address_phone = thisRow.find('td:eq(4)').find(
								'input').val();
						//섦졍 input 값 가져오기						
						
						// 확인 버튼 클릭 시 동작
						if (confirm("선택하시겠습니까?")) {
				                
				                alert("선택되었습니다.");
				            	
								window.opener.document.getElementById("address1").value = address_code;
								window.opener.document.getElementById("address2").value = address_detail;
								window.opener.document.getElementById("postcode").value = post_code;
								window.opener.document.getElementById("name").value = address_name;
								window.opener.document.getElementById("phone").value = address_phone;
				        		// 3. 자식창 닫기
				        		window.close();
				        		
				            } else {
				                // 취소 버튼 클릭 시 동작
				                alert("취소되었습니다.");
				            }
					
					
					});
		});
</script>
</head>
<body>


	<div class="row">
		<table id="example-table-2"
			class="table table-bordered table-hover text-center">
			<tr>
				<th colspan="6"><h1>최근 배송지</h1></th>
			</tr>

			<tr>
				<td width="350" style="text-align: center;">주소</td>
				<td width="200" style="text-align: center;">상세 주소</td>
				<td width="100" style="text-align: center;">우편번호</td>
				<td width="70" style="text-align: center;">이름</td>
				<td width="90">전화번호</td>
				<td style="text-align: center;">버튼</td>
			</tr>

			<c:forEach items="${arrAddressDTO}" var="arrAddressDTO">
			<tr>
				<td style="text-align: center;">
					<input value="${arrAddressDTO.address_code }"
					style=""/>
				</td>
				<td style="text-align: center;">
					<input value="${arrAddressDTO.address_detail}"
					/>
				</td>
				<td style="text-align: center;">
					<input value="${arrAddressDTO.post_code}"
					/>
				</td>
				<td style="text-align: center;">
					<input value="${arrAddressDTO.address_name}"
					/>
				</td>
				<td style="text-align: center;">
					<input value="${arrAddressDTO.address_phone}"
					/>
				</td>
				<td><input type="button" value="선택" class="checkBtn"
					style="margin: 10px; border: none; padding: 15px 30px; text-align: center;">
				</td>
				
				<!-- onclick="useAddress('')"  -->
			</tr>
			</c:forEach>
		</table>
		<div class="col-lg-12" id="ex2_Result1"></div>
		<div class="col-lg-12" id="ex2_Result2"></div>
	</div>
	<!-- 배송 작업 -->

</body>
</html>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String code = (String)session.getAttribute("sCode");
String nickname = session.getAttribute("sNickname").toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function findAddr(){ //주소 찾기
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);
        	
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('member_info_post_code').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("member_info_address").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("member_info_address_detail").value = jibunAddr;
            }
        }
    }).open();
}

function findAddr1(){ //배송지 주소 찾기
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);
        	
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('member_info_ship_post_code').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("member_info_ship_address").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("member_info_ship_address_detail").value = jibunAddr;
            }
        }
    }).open();
}

</script>
</head>
<body>
	<h1>dd</h1>
	<h1>mypage 수정함</h1>
	<form action="Modify_Member.ma" method="post">
<%-- 		<input type="hidden" name="member_code" id="member_code" value="<%=memberBean.getMember_code() %>"> --%>
<%-- 		<input type="hidden" name="member_info_code" id="member_info_code" value="<%=memberBean.getMember_info_code() %>"> --%>
<%-- 		<input type="hidden" name="member_info_detail_code" id="member_info_detail_code" value="<%=memberBean.getMember_info_detail_code() %>"> --%>
<%-- 		<input type="hidden" name="member_service_log_code" id="member_service_log_code" value="<%=memberBean.getMember_service_log_code() %>"> --%>
		<table border="1">
			<tr> 
				<th>멤버코드(사라질예정)</th>
				<td><input type="text" name="member_code" id="member_code" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>멤버인포코드(사라질예정)</th>
				<td><input type="text" name="member_info_code" id="member_info_code" value="<%=code %>" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>멤버인포디테일코드(사라질예정)</th>
				<td><input type="text" name="member_info_detail_code" id="member_info_detail_code" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>멤버서비스로그코드(사라질예정)</th>
				<td><input type="text" name="member_service_log_code" id="member_service_log_code" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>닉네임</th>
				<td><input type="text" name="member_nickname" id="member_nickname" value="<%=nickname %>" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>아이디</th>
				<td><input type="text" name="member_id" id="member_id" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>패스워드</th>
				<td><input type="password" name="member_passwd" id="member_passwd">
					<input type="button" value="패스워드 수정" onclick="">
				</td>
			</tr>
			<tr> 
				<th>이메일1</th>
				<td><input type="text" name="member_email1" id="member_email1" value=""></td>
			</tr>
			<tr> 
				<th>이메일2</th>
				<td><select id="member_email2" name="member_email2">
						<option value="@gmail.com">@gmail.com</option>
						<option value="@naver.com">@naver.com</option>
						<option value="@nate.com">@nate.com</option>
						<option value="@hanmail.net">@hanmail.net</option>
						<option value="@daum.net">@daum.net</option>
						<option value="@yahoo.com">@yahoo.com</option>
<%-- 						<option value="@gmail.com" <%if(memberBean.getMember_email().equals("@gmail.com")) {%>selected="selected"<%} %>> --%>
<!-- 						gmail.com -->
<!-- 						</option> -->
<%-- 						<option value="@naver.com" <%if(memberBean.getMember_email().equals("@naver.com")) {%>selected="selected"<%} %>> --%>
<!-- 						naver.com -->
<!-- 						</option> -->
<%-- 						<option value="@nate.com" <%if(memberBean.getMember_email().equals("@nate.com")) {%>selected="selected"<%} %>> --%>
<!-- 						nate.com -->
<!-- 						</option> -->
<%-- 						<option value="@hanmail.net" <%if(memberBean.getMember_email().equals("@hanmail.net")) {%>selected="selected"<%} %>> --%>
<!-- 						hanmail.net -->
<!-- 						</option> -->
<%-- 						<option value="@daum.net" <%if(memberBean.getMember_email().equals("@daum.net")) {%>selected="selected"<%} %>> --%>
<!-- 						daum.net -->
<!-- 						</option> -->
<%-- 						<option value="@yahoo.com" <%if(memberBean.getMember_email().equals("@yahoo.com")) {%>selected="selected"<%} %>> --%>
<!-- 						yahoo.com -->
<!-- 						</option> -->
				</select></td>
			</tr>
			<tr> 
				<th>이름</th>
				<td><input type="text" name="member_info_name" id="member_info_name" value="" ></td>
			</tr>
			<tr> 
				<th>성별</th>
				<td><select id="member_info_gender " name="member_info_gender ">
						<option value="male">남자</option>
						<option value="female">여자</option>
<%-- 						<option value="male" <%if(memberBean.getMember_info_gender().equals("male")) {%>selected="selected"<%} %>> --%>
<!-- 						남자 -->
<!-- 						</option> -->
<%-- 						<option value="female" <%if(memberBean.getMember_info_gender().equals("female")) {%>selected="selected"<%} %>> --%>
<!-- 						여자 -->
<!-- 						</option> -->
				</select></td>
			</tr>
			<tr> 
				<th>전화번호</th>
				<td><input type="text" name="member_info_phone" id="member_info_phone" value="" ></td>
			</tr>
			<tr> 
				<th>나이대</th>
				<td><select id="member_info_age" name="member_info_age">
						<option value="19세이하">19세이하</option>
						<option value="20~29">20~29</option>
						<option value="30~39">30~39</option>
						<option value="40~49">40~49</option>
						<option value="50~59">50~59</option>
						<option value="60~69">60~69</option>
						<option value="70대이상">70세이상</option>
						
<%-- 						<option value="19세이하" <%if(memberBean.getMember_info_age().equals("19세이하")) {%>selected="selected"<%} %>> --%>
<!-- 						19세이하 -->
<!-- 						</option> -->
<%-- 						<option value="20~29" <%if(memberBean.getMember_info_age().equals("20~29")) {%>selected="selected"<%} %>> --%>
<!-- 						20~29 -->
<!-- 						</option> -->
<%-- 						<option value="30~39" <%if(memberBean.getMember_info_age().equals("30~39")) {%>selected="selected"<%} %>> --%>
<!-- 						30~39 -->
<!-- 						</option> -->
<%-- 						<option value="40~49" <%if(memberBean.getMember_info_age().equals("40~49")) {%>selected="selected"<%} %>> --%>
<!-- 						40~49 -->
<!-- 						</option> -->
<%-- 						<option value="50~59" <%if(memberBean.getMember_info_age().equals("50~59")) {%>selected="selected"<%} %>> --%>
<!-- 						50~59 -->
<!-- 						</option> -->
<%-- 						<option value="60~69" <%if(memberBean.getMember_info_age().equals("60~69")) {%>selected="selected"<%} %>> --%>
<!-- 						60~69 -->
<!-- 						</option> -->
<%-- 						<option value="70대이상" <%if(memberBean.getMember_info_age().equals("70대이상")) {%>selected="selected"<%} %>> --%>
<!-- 						70대이상 -->
<!-- 						</option> -->
						
				</select></td>
			</tr>
			<tr> 
				<th>가입날짜</th>
				<td><input type="text" name="member_service_log_join_date" id="member_service_log_join_date" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>패스워드 수정날짜</th>
				<td><input type="text" name="member_service_log_passwd_change_date" id="member_service_log_passwd_change_date" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>우편번호</th>
				<td><input type="text" name="member_info_post_code" id="member_info_post_code" value="" readonly="readonly">
					<input type="button" onclick="findAddr()" value="우편번호 찾기">
				</td>
			</tr>
			<tr> 
				<th>주소</th>
				<td><input type="text" name="member_info_address" id="member_info_address" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>상세주소</th>
				<td><input type="text" name="member_info_address_detail" id="member_info_address_detail" value="" ></td>
			</tr>
			<tr> 
				<th>배송지우편번호</th>
				<td><input type="text" name="member_info_ship_post_code" id="member_info_ship_post_code" value="" readonly="readonly">
					<input type="button" onclick="findAddr1()" value="우편번호 찾기">
				</td>
			</tr>
			<tr> 
				<th>배송지주소</th>
				<td><input type="text" name="member_info_ship_address" id="member_info_ship_address" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>배송지상세주소</th>
				<td><input type="text" name="member_info_ship_address_detail" id="member_info_ship_address_detail" value="" ></td>
			</tr>
			<tr> 
				<th>등급</th>
				<td><input type="text" name="grade_name " id="grade_name " value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>누적금액</th>
				<td><input type="text" name="member_info_detail_acc_money" id="member_info_detail_acc_money" value="" readonly="readonly"></td>
			</tr>
			<tr> 
				<th>스타일</th>
				<td><select id="member_info_detail_like_style" name="member_info_detail_like_style">
						<option value="귀여움">귀여움</option>
						<option value="섹시함">섹시함</option>
<%-- 						<option value="귀여움" <%if(memberBean.getMember_info_detail_like_style().equals("귀여움")) {%>selected="selected"<%} %>> --%>
<!-- 						귀여움 -->
<!-- 						</option> -->
<%-- 						<option value="섹시함" <%if(memberBean.getMember_info_detail_like_style().equals("섹시함")) {%>selected="selected"<%} %>> --%>
<!-- 						섹시함 -->
<!-- 						</option> -->
				</select></td>
			</tr>
			<tr> 
				<th>브랜드</th>
				<td><select id="member_info_detail_like_brand" name="member_info_detail_like_brand">
						<option value="나이키">나이키</option>
						<option value="에르메스">에르메스</option>
<%-- 						<option value="나이키" <%if(memberBean.getMember_info_detail_like_brand().equals("나이키")) {%>selected="selected"<%} %>> --%>
<!-- 						나이키 -->
<!-- 						</option> -->
<%-- 						<option value="에르메스" <%if(memberBean.getMember_info_detail_like_brand().equals("에르메스")) {%>selected="selected"<%} %>> --%>
<!-- 						에르메스 -->
<!-- 						</option> -->
				</select></td>
			</tr>
			<tr> 
				<th>관심품목</th>
				<td><select id="member_info_detail_like_category" name="member_info_detail_like_category">
						<option value="상의">상의</option>
						<option value="하의">하의</option>
<%-- 						<option value="상의" <%if(memberBean.getMember_info_detail_like_category().equals("상의")) {%>selected="selected"<%} %>> --%>
<!-- 						상의 -->
<!-- 						</option> -->
<%-- 						<option value="하의" <%if(memberBean.getMember_info_detail_like_category().equals("하의")) {%>selected="selected"<%} %>> --%>
<!-- 						하의 -->
<!-- 						</option> -->
				</select></td>
			</tr>
<!-- 			<tr>  -->
<!-- 				<th>프로필사진</th> -->
<!-- 				<td><input type="file" id="member_info_mypage_img_name" name="member_info_mypage_img_name" required="required" > </td> -->
<!-- 			</tr> -->
			<tr> 
				<th>누적 포인트</th>
				<td><input type="text" name="member_info_detail_point"  id="member_info_detail_point" value=""  readonly="readonly"></td>
			</tr>
			<tr> 
				<th>회원 상태(정상,정지,탈퇴)</th>
				<td><input type="text" name="member_service_log_status"  id="member_service_log_status" value=""  readonly="readonly"></td>
			</tr>
			<tr> 
				<th>회원가입 날짜</th>
				<td><input type="text" name="member_service_log_join_date"  id="member_service_log_join_date" value=""  readonly="readonly"></td>
			</tr>
			<tr> 
				<th>비밀번호변경 날짜</th>
				<td><input type="text" name="member_service_log_passwd_change_date"  id="member_service_log_passwd_change_date" value=""  readonly="readonly"></td>
			</tr>
			<tr> 
				<th>등급변화 날짜</th>
				<td><input type="text" name="member_service_log_grade_change_date"  id="member_service_log_grade_change_date" value=""  readonly="readonly"></td>
			</tr>
			<tr> 
				<th>등급코드??</th>
				<td><input type="text" name="member_info_grade_code"  id="member_info_grade_code" value=""  readonly="readonly"></td>
			</tr>
			<tr> 
				<th>로그인 날짜</th>
				<td><input type="text" name="member_service_log_login_date"  id="member_service_log_login_date" value=""  readonly="readonly"></td>
			</tr>
			<tr> 
				<th>구매횟수</th>
				<td><input type="text" name="member_service_log_order_count"  id="member_service_log_order_count" value=""  readonly="readonly"></td>
			</tr>
			<tr> 
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="회원탈퇴" onclick="">
				 </td> 
			</tr>
			
		</table>
	
	</form>
</body>
</html>
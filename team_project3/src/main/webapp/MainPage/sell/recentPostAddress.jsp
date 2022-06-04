<%@page import="java.util.ArrayList"%>
<%@page import="vo.SellerAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
ArrayList<SellerAddress> arrAddressDTO = (ArrayList<SellerAddress>) request.getAttribute("arrAddressDTO");
String member_id = (String) session.getAttribute("sId"); //아이디 값 세션
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
  integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
  crossorigin="anonymous"></script>
<script type="text/javascript">
	// function checkOnlyOne(element) {  //체크박스 하나만 클릭가능 <!-- 		onclick='checkOnlyOne(this)' -->

	// 	  const checkboxes 
	// 	      = document.getElementsByName("findAddress");

	// 	  checkboxes.forEach((cb) => {
	// 	    cb.checked = false;
	// 	  })

	// 	  element.checked = true;
	// 	}

	// function setCheckUserList(){

	// 	console.log("lengh:"+$("input:checkbox[name=userList]:checked").length );

	// 	var idList ="";
	// 	var nameList = "";

	// 	var checkbox =$("input:checkbox[name = userList]:checked");

	// 	checkbox.each(function(i)){

	// 	var tr = checkbox.parent().parent().eq(i);
	// 	var td =tr.children();

	// 	nameList += name +"/";
	// 	idList   += userid +"/";

	// 	});
	// 	nameList = nameList.substring(0,nameList.lentgth-1);
	// 	idList = idList.substring(0,idList.length -1);
	// 	$("#seluserNmList").val(nameList);
	// 	$("#userIdList").val(idList);
	// 	 console.log(nameList); 
	// 	 console.log(idLists);
	// }	
//---------------------------------------------------------------------
// 	$(".checkBtn").click(function() { // 버튼 클릭시 Row 값 가져오기

// 				var str = ""
// 				var tdArr = new Array(); // 배열 선언
// 				var checkBtn = $(this);

// 				// checkBtn.parent() : checkBtn의 부모는 <td>이다.
// 				// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
// 				var tr = checkBtn.parent().parent();
// 				var td = tr.children();

// // 				console.log("클릭한 Row의 모든 데이터 : " + tr.text());

// 				var address_code = td.eq(0).text();
// 				var address_detail = td.eq(1).text();
// 				var post_code = td.eq(2).text();
// 				var post_name = td.eq(3).text();
// 				var post_phone = td.eq(4).text();
// 				// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
				
// 				td.each(function(i) {
// 					tdArr.push(td.eq(i).text());
// 				});

// 				console.log("배열에 담긴 값 : " + tdArr);

// 					 str += "address_code : <font color='red'>" + address_code + "</font>" 
// 						 + ", address_detail : <font color='red'>" + address_detail + "</font>"
// 						 + ",post_code : <font color='red'>" + post_code + "</font>" 
// 						 + ",  post_name : <font color='red'>" +  post_name + "</font>"
// 						 + ",  post_phone : <font color='red'>" +  post_phone + "</font>" ;
						

// 				$("#ex2_Result1").html(" * 클릭한 Row의 모든 데이터 = " + tr.text());
// 				$("#ex2_Result2").html(str);
// 			});
//-------------------------------------------------------------------
$('.checkBtn').on('click', function() { 
  
  //현재 row의 정보 가져오기 
  var thisRow = $(this).closest('tr'); 
  
  //주소 input 값 가져오기
  var address_code = thisRow.find('td:eq(0)').find('input').val();
  var address_detail = thisRow.find('td:eq(1)').find('input').val();
  //섦졍 input 값 가져오기
  var post_code = thisRow.find('td:eq(2)').find('input').val();
  var address_name = thisRow.find('td:eq(3)').find('input').val();
  //섦졍 input 값 가져오기
  var address_phone = thisRow.find('td:eq(4)').find('input').val(); 
  //섦졍 input 값 가져오기
  alert(address_code + ", " + address_detail +
		  post_code + ", " + address_name +
		  address_phone 
		  + "을(를) 클릭하였습니다.");
  
})

	
// 	function useAddress(findAddress) {
// 		// 자식창(나)에서 부모창(나를 연 창)을 제어하려면 window.opener.xxx 형태로 제어
// 		// => xxx 은 원래 해당 요소에 접근하는 문법 그대로 사용
// 		// 1. 사용 가능한 아이디를 부모창의 아이디 입력란에 표시
// 		window.opener.document.id.value = findAddress;
// 		// 2. 부모창의 전역변수 checkIdResult 값을 true 로 변경
// 		window.opener.checkIdResult = true;
// 		// 3. 자식창 닫기
// 		window.close();
// 	}
	
        // 버튼 클릭시 Row 값 가져오기
//         $(".checkBtn").click(function(){ 
            
//             var str = ""
//             var tdArr = new Array();    // 배열 선언
//             var checkBtn = $(this);
            
//             // checkBtn.parent() : checkBtn의 부모는 <td>이다.
//             // checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
//             var tr = checkBtn.parent().parent();
//             var td = tr.children();
            
//             console.log("클릭한 Row의 모든 데이터 : "+tr.text());
            
//             var no = td.eq(0).text();
//             var userid = td.eq(1).text();
//             var name = td.eq(2).text();
//             var email = td.eq(3).text();
            
            
//             // 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
//             td.each(function(i){    
//                 tdArr.push(td.eq(i).text());
//             });
            
//             console.log("배열에 담긴 값 : "+tdArr);
            
 
//             str +=    " * 클릭된 Row의 td값 = No. : <font color='red'>" + no + "</font>" +
//                     ", 아이디 : <font color='red'>" + userid + "</font>" +
//                     ", 이름 : <font color='red'>" + name + "</font>" +
//                     ", 이메일 : <font color='red'>" + email + "</font>";        
            
//             $("#ex2_Result1").html(" * 클릭한 Row의 모든 데이터 = " + tr.text());        
//             $("#ex2_Result2").html(str);    
//         });
    
</script>
</head>
<body>
	
	<div class="row">
		<table id="example-table-2"  class="table table-bordered table-hover text-center">
		<tr>
			<th colspan="6"><h1>최근 배송지</h1></th>
		</tr>

		<tr >
			<td width="350" style="text-align: center;">주소</td>
			<td width="200" style="text-align: center;">상세 주소</td>
			<td width="100" style="text-align: center;">우편번호</td>
			<td width="70" style="text-align: center;">이름</td>
			<td width="90">전화번호</td>
			<td style="text-align: center;">버튼</td>
		</tr>
		
<%for (SellerAddress arrAddressdto : arrAddressDTO) {%> 
		<tr >
			<td  style="text-align: center;"><%=arrAddressdto.getAddress_code()%></td>
			<td  style="text-align: center;"><%=arrAddressdto.getAddress_detail()%></td>
			<td  style="text-align: center;"><%=arrAddressdto.getPost_code()%></td>
			<td  style="text-align: center;"><%=arrAddressdto.getAddress_name()%></td>
			<td  style="text-align: center;"><%=arrAddressdto.getAddress_phone()%></td>
			<td ><input type="button" value="선택" class="checkBtn"
				style=" margin: 10px; border: none; padding: 15px 30px; text-align: center;">
			</td>

<!-- onclick="useAddress('')"  -->
		</tr> 
		<%}%>
		
	</table>
			<div class="col-lg-12" id="ex2_Result1" ></div> 
			<div class="col-lg-12" id="ex2_Result2" ></div> 
		</div>
	<!-- 배송 작업 -->
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="HomePage/js/jquery-3.6.0.js"></script>
<script type="module">
</script>
<script type="text/javascript">


$(function(){
	//버튼 숨기기
	$('#btnHome').hide();
	$("#btnOk").on("click",function(){
		//폼을 지정하여 serialize()함수 호출시 해당 폼의 데이터 직렬화(모두 내보내기)
		let sendData = $("form").serialize();
	
		$.ajax({
			type:"GET",
			url:"Reason.ma?reason=${sReasonContent}",
			data: sendData, // 함수 사용시 가져온 데이터 전달
			dataType:"text",
			success:function(msg){
				$("#reason_content").html(msg);
			}
		});
		//버튼 보이기
		$('#btnHome').show();
	});
});


</script>
</head>
<body>
	<h2>본 회원은 정지되어 있는 상태입니다.</h2>
	<h4>정지 일자 : ${sLoginDate}</h4>
	<h5>정지 사유가 궁금하시면 <input type="button" value="여기" id="btnOk">를 눌러주세요.</h5>
	<span id="reason_content"></span><br><br>
	<input type="button" value="홈페이지로 돌아가기" id="btnHome" onclick="location.href='./'">
</body>
</html>
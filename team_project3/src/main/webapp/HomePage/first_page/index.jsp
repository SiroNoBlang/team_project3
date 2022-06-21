<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>COZA STORE 공식 온라인 스토어</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="HomePage/assets/css/main.css" />
<link rel="stylesheet" href="HomePage/assets/css/noscript.css" />
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="HomePage/assets/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
//==================================================================================제약조건  
var isCheckConfirmPasswd = false, passSafe = false, isCheckEmail = false, checkNickname = false, checkId = false;
//==================================================================================제약조건

	//패스워드 제약 조건
	function checkPass(pass) {
		var spanElem = document.getElementById("passSafe");
		var lengthRegex = /^[A-Za-z0-9-!@#$%]{8,16}$/;
		var engUpperRegex = /[A-Z]/;
		var engLowerRegex = /[a-z]/;
		var numRegex = /[0-9]/;
		var specRegex = /[!@#$%]/;

		var count = 0;

		if (lengthRegex.exec(pass)) {
			if (engUpperRegex.exec(pass))
				count++;
			if (engLowerRegex.exec(pass))
				count++;
			if (numRegex.exec(pass))
				count++;
			if (specRegex.exec(pass))
				count++;

			switch (count) {
			case 4:
				spanElem.innerHTML = "사용 가능한 패스워드(안전)";
				spanElem.style.color = "GREEN";
				passSafe = true;
				break;
			case 3:
				spanElem.innerHTML = "사용 가능한 패스워드(보통)";
				spanElem.style.color = "GREEN";
				passSafe = true;
				break;
			case 2:
				spanElem.innerHTML = "사용 가능한 패스워드(위험)";
				spanElem.style.color = "ORANGE";
				passSafe = true;
				break;
			default:
				spanElem.innerHTML = "영문자, 숫자, 특수문자 중 2가지 이상 조합 필수!";
				spanElem.style.color = "RED";
				passSafe = false;
			}
		} else {
			spanElem.innerHTML = "영문자,숫자,특수문자 조합 8~16자리 필수";
			spanElem.style.color = "RED";
			passSafe = false;
		}
	}
	
	//패스워드와 패스워드 일치여부
	function checkPassResult(pass2){
		var spanElem = document.getElementById("passCheck");
		var pass = fr.join_member_passwd.value;
		
		if(pass==pass2){
			spanElem.innerHTML = "패스워드 일치";
			spanElem.style.color = "GREEN";
			isCheckConfirmPasswd = true;
		}else{
			spanElem.innerHTML = "패스워드 불일치";
			spanElem.style.color = "RED";
			isCheckConfirmPasswd = false; 
		}
	}
	
	//join버튼 클릭시 제출하기 버튼 숨기기
	function submitHide(){
		$('#checkSubmit').hide();
	}
	
	//이용약관 체크박스 첫번째만 체크할 경우 제출하기 버튼 숨기기 & 두개 다 체크 될 경우 버튼 나타내기
	var result ,result2;
	function is_checked1() {
		  
		  const checkbox = document.getElementById('Check1');

		  const is_checked = checkbox.checked;
		  
			 result = is_checked;
			 
			 if(result==true && result2==true){
				 $('#checkSubmit').show();
			 }else{
				 $('#checkSubmit').hide();
			 }
		  
		}
	
	//이용약관 체크박스 두번째만 체크할 경우 제출하기 버튼 숨기기 & 두개 다 체크 될 경우 버튼 나타내기
	function is_checked2() {
		  
		  const checkbox = document.getElementById('Check2');

		  const is_checked = checkbox.checked;

		  result2 = is_checked;
		  
		  if(result==true && result2==true){
				 $('#checkSubmit').show();
			 }else{
				 $('#checkSubmit').hide();
			 }
		  
		}
	
	//이용약관 체크박스 전체동의 체크시 모든 버튼 체크되면서 제출하기 버튼 나타내기
	function is_checked3() {
		  
		  const checkbox = document.getElementById('CheckAll');

		  const is_checked = checkbox.checked;

			  document.getElementById('Check1').checked = is_checked;
			  document.getElementById('Check2').checked = is_checked;
		
		 if(is_checked){
			 $('#checkSubmit').show();
		 }else{
			 $('#checkSubmit').hide();
		 }
		 
		}

	//닉네임 입력시 제약조건 
	$(function(){
		$("#member_nickname").on("keyup",function(){
			let sendData = $("#fr").serialize();
			
			$.ajax({
				type:"GET",
				url:"HomePage/first_page/check_nickname.jsp",
				data: sendData,
				dataType:"text",
				async : false,
				success:function(msg){
					$("#nicknameResultArea").html(msg);
					
				},
				error: function(xhr, textStatus, errorThrown){
					$("#nicknameResultArea").empty();
					$("#nicknameResultArea").html(
							"xhr = " + xhr + "<br>"
							+ "textStatus = " + textStatus + "<br>"
							+ "errorThrown = " + errorThrown);
				}
			});
		});
	});		

	//아이디 입력시 제약조건
	$(function(){
		$("#join_member_id").on("keyup",function(){
			let sendData = $("#fr").serialize();
			
			$.ajax({
				type:"GET",
				url:"HomePage/first_page/check_id.jsp",
				data: sendData,
				dataType:"text",
				async : false,
				success:function(msg){
					$("#idResultArea").html(msg);
				},
				error: function(xhr, textStatus, errorThrown){
					$("#idResultArea").html(
							"xhr = " + xhr + "<br>"
							+ "textStatus = " + textStatus + "<br>"
							+ "errorThrown = " + errorThrown);
				}
			});
		});
	});	
	
	//이메일 입력시 제약조건
	function checkEmail(email){
		var spanElem = document.getElementById("emailCheck");
		var specRegex = /@/;
		
		if (specRegex.exec(email)){
			spanElem.innerHTML="'@' 사용불가!";
			isCheckEmail  = false;
			
		} else{
			spanElem.innerHTML="";
			isCheckEmail = true;
		}
	}
	
	// 제약조건
	function checkForm(){
		if(!checkNickname){ // 닉네임 제약조건
			alert("닉네임 확인 필수!");
			document.fr.member_nickname.focus();
			return false;
		} 
		if(!checkId){ //아이디 제약조건
			alert("아이디 확인 필수!");
			document.fr.join_member_id.focus();
			return false;
		}
		if(!passSafe){ //패스워드 제약조건
			alert("패스워드 조건 확인 필수!");
			document.fr.join_member_passwd.focus();
			return false;
		}
		if(!isCheckConfirmPasswd){ //패스워드 확인 제약조건
			alert("패스워드 확인 필수!");
			document.fr.join_member_passwd_check.focus();
			return false;
		}
		if (!isCheckEmail){ //이메일 제약조건
			alert("이메일 확인 필수!");
			document.fr.member_email1.focus();
			return false;
		}
	}	
	
	//아이디찾기
	$(function(){
		$('#idFind_login').hide();
		$("#idFind").on("click",function(){
			let sendData = $("#fi").serialize();
			$.ajax({
				type:"GET",
				url:"HomePage/first_page/find_id.jsp",
				data: sendData,
				dataType:"text",
				success:function(msg){
					$("#findIdResultArea").html(msg);
					$('#idFind').hide(); //idFind_login
					$('#idFind_login').show();
				},
				error: function(xhr, textStatus, errorThrown){
					$("#findIdResultArea").html(
							"xhr = " + xhr + "<br>"
							+ "textStatus = " + textStatus + "<br>"
							+ "errorThrown = " + errorThrown);
				}
			});
		});
	});	
	
	//아이디찾기 버튼 클릭시 찾은 결과값 출력 
	$(function(){
		$("#idFind_login").on("click", function(){
			location.href="#contact";
			$('#findIdResultArea').empty();
			$('#idFind_login').hide();
			$('#idFind').show();
		});
	});
	
</script>
</head>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<div class="logo">
				<span class="icon fa-gem"></span>
			</div>
			<div class="content">
				<div class="inner">
					<h1>COZA STORE</h1>
					<p>Many of the products are like new.<br>
					We offer our customers a quality product at a reasonable price.</p>
				</div>
			</div>
			<nav>
				<ul>
					<li><a href="#contact">Login</a></li>
					<li><a href="#join" onclick="submitHide()">join</a></li>
				</ul>
			</nav>
		</header>
		<!-- Main -->
		<div id="main">
			<!-- Contact -->
			<article id="contact">
				<h2 class="major">Login</h2>
				<form method="post" action="LoginPro.ma">
					<div class="fields">
						<div class="field half">
							<label for="member_id">ID</label>
							<input type="text" name="login_member_id" id="login_member_id" />
						</div>
						<div class="field half">
							<label for="member_passwd">Password</label>
							<input type="password" name="login_member_passwd" id="login_member_passwd" />
						</div>
					</div>
					<ul class="actions">
						<li><input type="submit" value="Login" class="primary" /></li>
						<li><input  type="button" value="Lost ID & PASSWORD"  onclick="location.href='#find'"></li>
					</ul>
				</form>
			</article>
			
			<article id="find">
				<h2 class="major">FIND ID </h2>
				<form method="post" action="" id="fi" name="fi">
					<div class="fields">
						<div class="field half">
							<label for="find_id_member_nickname">NICKNAME</label>
							<input type="text" name="find_id_member_nickname" id="find_id_member_nickname" />
						</div>
						<div class="field half">
							<label for="find_id_member_email">EMAIL</label>
							<input type="email" name="find_id_member_email" id="find_id_member_email"  placeholder="xxx@xxx.xxx형식으로 입력하세요"/>
						</div>
					</div>
					<ul class="actions">
						<li><input type="button" value="Find" class="primary" id="idFind"/> <div id="findIdResultArea" ></div></li>
						<li><input type="reset" value="Login" class="primary" id="idFind_login" /> </li>
					</ul>
				</form>
				<h2 class="major">FIND PASSWORD</h2>
				<form method="post" action="FindPasswd.ma">
					<div class="fields">
						<div class="field half">
							<label for="find_passwd_member_id">ID</label>
							<input type="text" name="find_passwd_member_id" id="find_passwd_member_id" />
						</div>
						<div class="field half">
							<label for="find_passwd_member_email">EMAIL</label>
							<input type="email" name="find_passwd_member_email" id="find_passwd_member_email" placeholder="xxx@xxx.xxx형식으로 입력하세요"/>
						</div>
					</div>
					<ul class="actions">
						<li><input type="submit" value="Find" class="primary" /></li>
					</ul>
				</form>
			</article>
			
			<!-- Contact -->
			<article id="join">
				<h2 class="major">약관</h2>
				<form method="post" action="#joinPro">
					<fieldset>
						<legend>이용약관(필수)</legend>
						<textarea rows="5" cols="5">
제1조(목적) 이 약관은 OO 회사(전자상거래 사업자)가 운영하는 COZA STORE 공식 온라인 스토어 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.

※「PC통신, 무선 등을 이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다.」


제2조(정의)

① “몰”이란 COZA STORE 공식 온라인 스토어 회사가 재화 또는 용역(이하 “재화 등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다.

② “이용자”란 “몰”에 접속하여 이 약관에 따라 “몰”이 제공하는 서비스를 받는 회원 을 말합니다.

③ ‘회원’이라 함은 “몰”에 회원등록을 한 자로서, 계속적으로 “몰”이 제공하는 서비스를 이용할 수 있는 자를 말합니다.


제3조 (약관 등의 명시와 설명 및 개정)

① “몰”은 이 약관의 내용과 상호 및 대표자 성명, 영업소 소재지 주소(소비자의 불만을 처리할 수 있는 곳의 주소를 포함), 전화번호․모사전송번호․전자우편주소, 사업자등록번호, 통신판매업 신고번호, 개인정보관리책임자등을 이용자가 쉽게 알 수 있도록 00 사이버몰의 초기 서비스화면(전면)에 게시합니다. 다만, 약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다.

② “몰은 이용자가 약관에 동의하기에 앞서 약관에 정하여져 있는 내용 중 청약철회․배송책임․환불조건 등과 같은 중요한 내용을 이용자가 이해할 수 있도록 별도의 연결화면 또는 팝업화면 등을 제공하여 이용자의 확인을 구하여야 합니다.

③ “몰”은 「전자상거래 등에서의 소비자보호에 관한 법률」, 「약관의 규제에 관한 법률」, 「전자문서 및 전자거래기본법」, 「전자금융거래법」, 「전자서명법」, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」, 「방문판매 등에 관한 법률」, 「소비자기본법」 등 관련 법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.

④ “몰”이 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 몰의 초기화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. 다만, 이용자에게 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다.  이 경우 "몰“은 개정 전 내용과 개정 후 내용을 명확하게 비교하여 이용자가 알기 쉽도록 표시합니다. 

⑤ “몰”이 약관을 개정할 경우에는 그 개정약관은 그 적용일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의 약관조항이 그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정약관 조항의 적용을 받기를 원하는 뜻을 제3항에 의한 개정약관의 공지기간 내에 “몰”에 송신하여 “몰”의 동의를 받은 경우에는 개정약관 조항이 적용됩니다.

⑥ 이 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 전자상거래 등에서의 소비자보호에 관한 법률, 약관의 규제 등에 관한 법률, 공정거래위원회가 정하는 전자상거래 등에서의 소비자 보호지침 및 관계법령 또는 상관례에 따릅니다.


제4조(서비스의 제공 및 변경)

① “몰”은 다음과 같은 업무를 수행합니다.

1. 재화 또는 용역에 대한 정보 제공 및 구매계약의 체결

2. 구매계약이 체결된 재화 또는 용역의 배송

3. 기타 “몰”이 정하는 업무


② “몰”은 재화 또는 용역의 품절 또는 기술적 사양의 변경 등의 경우에는 장차 체결되는 계약에 의해 제공할 재화 또는 용역의 내용을 변경할 수 있습니다. 이 경우에는 변경된 재화 또는 용역의 내용 및 제공일자를 명시하여 현재의 재화 또는 용역의 내용을 게시한 곳에 즉시 공지합니다.

③ “몰”이 제공하기로 이용자와 계약을 체결한 서비스의 내용을 재화등의 품절 또는 기술적 사양의 변경 등의 사유로 변경할 경우에는 그 사유를 이용자에게 통지 가능한 주소로 즉시 통지합니다.

④ 전항의 경우 “몰”은 이로 인하여 이용자가 입은 손해를 배상합니다. 다만, “몰”이 고의 또는 과실이 없음을 입증하는 경우에는 그러하지 아니합니다.


제5조(서비스의 중단) 

① “몰”은 컴퓨터 등 정보통신설비의 보수점검․교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다.

② “몰”은 제1항의 사유로 서비스의 제공이 일시적으로 중단됨으로 인하여 이용자 또는 제3자가 입은 손해에 대하여 배상합니다. 단, “몰”이 고의 또는 과실이 없음을 입증하는 경우에는 그러하지 아니합니다.

③ 사업종목의 전환, 사업의 포기, 업체 간의 통합 등의 이유로 서비스를 제공할 수 없게 되는 경우에는 “몰”은 제8조에 정한 방법으로 이용자에게 통지하고 당초 “몰”에서 제시한 조건에 따라 소비자에게 보상합니다. 다만, “몰”이 보상기준 등을 고지하지 아니한 경우에는 이용자들의 마일리지 또는 적립금 등을 “몰”에서 통용되는 통화가치에 상응하는 현물 또는 현금으로 이용자에게 지급합니다.


제6조(회원가입) 

① 이용자는 “몰”이 정한 가입 양식에 따라 회원정보를 기입한 후 이 약관에 동의한다는 의사표시를 함으로서 회원가입을 신청합니다.

② “몰”은 제1항과 같이 회원으로 가입할 것을 신청한 이용자 중 다음 각 호에 해당하지 않는 한 회원으로 등록합니다.

1. 가입신청자가 이 약관 제7조제3항에 의하여 이전에 회원자격을 상실한 적이 있는 경우, 다만 제7조제3항에 의한 회원자격 상실 후 3년이 경과한 자로서 “몰”의 회원재가입 승낙을 얻은 경우에는 예외로 한다.

2. 등록 내용에 허위, 기재누락, 오기가 있는 경우

3. 기타 회원으로 등록하는 것이 “몰”의 기술상 현저히 지장이 있다고 판단되는 경우


③ 회원가입계약의 성립 시기는 “몰”의 승낙이 회원에게 도달한 시점으로 합니다.

④ 회원은 회원가입 시 등록한 사항에 변경이 있는 경우, 상당한 기간 이내에 “몰”에 대하여 회원정보 수정 등의 방법으로 그 변경사항을 알려야 합니다.



제7조(회원 탈퇴 및 자격 상실 등)

① 회원은 “몰”에 언제든지 탈퇴를 요청할 수 있으며 “몰”은 확인 후 회원탈퇴를 처리합니다.

② 회원이 다음 각 호의 사유에 해당하는 경우, “몰”은 회원자격을 제한 및 정지시킬 수 있습니다.

1. 가입 신청 시에 허위 내용을 등록한 경우

2. “몰”을 이용하여 구입한 재화 등의 대금, 기타 “몰”이용에 관련하여 회원이 부담하는 채무를 기일에 지급하지 않는 경우

3. 다른 사람의 “몰” 이용을 방해하거나 그 정보를 도용하는 등 전자상거래 질서를 위협하는 경우

4. “몰”을 이용하여 법령 또는 이 약관이 금지하거나 공서양속에 반하는 행위를 하는 경우


③ “몰”이 회원 자격을 제한․정지 시킨 후, 동일한 행위가 2회 이상 반복되거나 30일 이내에 그 사유가 시정되지 아니하는 경우 “몰”은 회원자격을 상실시킬 수 있습니다.

④ “몰”이 회원자격을 상실시키는 경우에는 회원등록을 말소합니다. 이 경우 회원에게 이를 통지하고, 회원등록 말소 전에 최소한 30일 이상의 기간을 정하여 소명할 기회를 부여합니다.


제8조(회원에 대한 통지)

① “몰”이 회원에 대한 통지를 하는 경우, 회원이 “몰”과 미리 약정하여 지정한 전자우편 주소로 할 수 있습니다.

② “몰”은 불특정다수 회원에 대한 통지의 경우 1주일이상 “몰” 게시판에 게시함으로서 개별 통지에 갈음할 수 있습니다. 다만, 회원 본인의 거래와 관련하여 중대한 영향을 미치는 사항에 대하여는 개별통지를 합니다.


제9조(구매신청 및 개인정보 제공 동의 등)

① “몰”이용자는 “몰”상에서 다음 또는 이와 유사한 방법에 의하여 구매를 신청하며, “몰”은 이용자가 구매신청을 함에 있어서 다음의 각 내용을 알기 쉽게 제공하여야 합니다. 

1. 재화 등의 검색 및 선택

2. 받는 사람의 성명, 주소, 전화번호, 전자우편주소(또는 이동전화번호) 등의 입력

3. 약관내용, 청약철회권이 제한되는 서비스, 배송료․설치비 등의 비용부담과 관련한 내용에 대한 확인

4. 이 약관에 동의하고 위 3.호의 사항을 확인하거나 거부하는 표시
	(예, 마우스 클릭)

5. 재화등의 구매신청 및 이에 관한 확인 또는 “몰”의 확인에 대한 동의

6. 결제방법의 선택


② “몰”이 제3자에게 구매자 개인정보를 제공할 필요가 있는 경우 1) 개인정보를 제공받는 자, 2)개인정보를 제공받는 자의 개인정보 이용목적, 3) 제공하는 개인정보의 항목, 4) 개인정보를 제공받는 자의 개인정보 보유 및 이용기간을 구매자에게 알리고 동의를 받아야 합니다. (동의를 받은 사항이 변경되는 경우에도 같습니다.)

③ “몰”이 제3자에게 구매자의 개인정보를 취급할 수 있도록 업무를 위탁하는 경우에는 1) 개인정보 취급위탁을 받는 자, 2) 개인정보 취급위탁을 하는 업무의 내용을 구매자에게 알리고 동의를 받아야 합니다. (동의를 받은 사항이 변경되는 경우에도 같습니다.) 다만, 서비스제공에 관한 계약이행을 위해 필요하고 구매자의 편의증진과 관련된 경우에는 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」에서 정하고 있는 방법으로 개인정보 취급방침을 통해 알림으로써 고지절차와 동의절차를 거치지 않아도 됩니다.


제10조 (계약의 성립)

①  “몰”은 제9조와 같은 구매신청에 대하여 다음 각 호에 해당하면 승낙하지 않을 수 있습니다. 다만, 미성년자와 계약을 체결하는 경우에는 법정대리인의 동의를 얻지 못하면 미성년자 본인 또는 법정대리인이 계약을 취소할 수 있다는 내용을 고지하여야 합니다.

1. 신청 내용에 허위, 기재누락, 오기가 있는 경우

2. 미성년자가 담배, 주류 등 청소년보호법에서 금지하는 재화 및 용역을 구매하는 경우

3. 기타 구매신청에 승낙하는 것이 “몰” 기술상 현저히 지장이 있다고 판단하는 경우


② “몰”의 승낙이 제12조제1항의 수신확인통지형태로 이용자에게 도달한 시점에 계약이 성립한 것으로 봅니다.

③ “몰”의 승낙의 의사표시에는 이용자의 구매 신청에 대한 확인 및 판매가능 여부, 구매신청의 정정 취소 등에 관한 정보 등을 포함하여야 합니다.

제11조(지급방법) “몰”에서 구매한 재화 또는 용역에 대한 대금지급방법은 다음 각 호의 방법중 가용한 방법으로 할 수 있습니다. 단, “몰”은 이용자의 지급방법에 대하여 재화 등의 대금에 어떠한 명목의 수수료도  추가하여 징수할 수 없습니다.

1. 폰뱅킹, 인터넷뱅킹, 메일 뱅킹 등의 각종 계좌이체 

2. 선불카드, 직불카드, 신용카드 등의 각종 카드 결제

3. 전자화폐에 의한 결제

4. 수령 시 대금지급

5. 마일리지 등 “몰”이 지급한 포인트에 의한 결제

6. “몰”과 계약을 맺었거나 “몰”이 인정한 상품권에 의한 결제  

7. 기타 전자적 지급 방법에 의한 대금 지급 등


제12조(수신확인통지․구매신청 변경 및 취소)

① “몰”은 이용자의 구매신청이 있는 경우 이용자에게 수신확인통지를 합니다.

② 수신확인통지를 받은 이용자는 의사표시의 불일치 등이 있는 경우에는 수신확인통지를 받은 후 즉시 구매신청 변경 및 취소를 요청할 수 있고 “몰”은 배송 전에 이용자의 요청이 있는 경우에는 지체 없이 그 요청에 따라 처리하여야 합니다. 다만 이미 대금을 지불한 경우에는 제15조의 청약철회 등에 관한 규정에 따릅니다.


제13조(재화 등의 공급)

① “몰”은 이용자와 재화 등의 공급시기에 관하여 별도의 약정이 없는 이상, 이용자가 청약을 한 날부터 7일 이내에 재화 등을 배송할 수 있도록 주문제작, 포장 등 기타의 필요한 조치를 취합니다. 다만, “몰”이 이미 재화 등의 대금의 전부 또는 일부를 받은 경우에는 대금의 전부 또는 일부를 받은 날부터 3영업일 이내에 조치를 취합니다.  이때 “몰”은 이용자가 재화 등의 공급 절차 및 진행 사항을 확인할 수 있도록 적절한 조치를 합니다.

② “몰”은 이용자가 구매한 재화에 대해 배송수단, 수단별 배송비용 부담자, 수단별 배송기간 등을 명시합니다. 만약 “몰”이 약정 배송기간을 초과한 경우에는 그로 인한 이용자의 손해를 배상하여야 합니다. 다만 “몰”이 고의․과실이 없음을 입증한 경우에는 그러하지 아니합니다.


제14조(환급) “몰”은 이용자가 구매신청한 재화 등이 품절 등의 사유로 인도 또는 제공을 할 수 없을 때에는 지체 없이 그 사유를 이용자에게 통지하고 사전에 재화 등의 대금을 받은 경우에는 대금을 받은 날부터 3영업일 이내에 환급하거나 환급에 필요한 조치를 취합니다.


제15조(청약철회 등)

① “몰”과 재화등의 구매에 관한 계약을 체결한 이용자는 「전자상거래 등에서의 소비자보호에 관한 법률」 제13조 제2항에 따른 계약내용에 관한 서면을 받은 날(그 서면을 받은 때보다 재화 등의 공급이 늦게 이루어진 경우에는 재화 등을 공급받거나 재화 등의 공급이 시작된 날을 말합니다)부터 7일 이내에는 청약의 철회를 할 수 있습니다. 다만, 청약철회에 관하여 「전자상거래 등에서의 소비자보호에 관한 법률」에 달리 정함이 있는 경우에는 동 법 규정에 따릅니다. 

② 이용자는 재화 등을 배송 받은 경우 다음 각 호의 1에 해당하는 경우에는 반품 및 교환을 할 수 없습니다.

1. 이용자에게 책임 있는 사유로 재화 등이 멸실 또는 훼손된 경우(다만, 재화 등의 내용을 확인하기 위하여 포장 등을 훼손한 경우에는 청약철회를 할 수 있습니다)

2. 이용자의 사용 또는 일부 소비에 의하여 재화 등의 가치가 현저히 감소한 경우

3. 시간의 경과에 의하여 재판매가 곤란할 정도로 재화등의 가치가 현저히 감소한 경우

4. 같은 성능을 지닌 재화 등으로 복제가 가능한 경우 그 원본인 재화 등의 포장을 훼손한 경우


③ 제2항제2호 내지 제4호의 경우에 “몰”이 사전에 청약철회 등이 제한되는 사실을 소비자가 쉽게 알 수 있는 곳에 명기하거나 시용상품을 제공하는 등의 조치를 하지 않았다면 이용자의 청약철회 등이 제한되지 않습니다.

④ 이용자는 제1항 및 제2항의 규정에 불구하고 재화 등의 내용이 표시·광고 내용과 다르거나 계약내용과 다르게 이행된 때에는 당해 재화 등을 공급받은 날부터 3월 이내, 그 사실을 안 날 또는 알 수 있었던 날부터 30일 이내에 청약철회 등을 할 수 있습니다.


제16조(청약철회 등의 효과)

① “몰”은 이용자로부터 재화 등을 반환받은 경우 3영업일 이내에 이미 지급받은 재화 등의 대금을 환급합니다. 이 경우 “몰”이 이용자에게 재화등의 환급을 지연한때에는 그 지연기간에 대하여 「전자상거래 등에서의 소비자보호에 관한 법률 시행령」제21조의2에서 정하는 지연이자율을 곱하여 산정한 지연이자를 지급합니다.

② “몰”은 위 대금을 환급함에 있어서 이용자가 신용카드 또는 전자화폐 등의 결제수단으로 재화 등의 대금을 지급한 때에는 지체 없이 당해 결제수단을 제공한 사업자로 하여금 재화 등의 대금의 청구를 정지 또는 취소하도록 요청합니다.

③ 청약철회 등의 경우 공급받은 재화 등의 반환에 필요한 비용은 이용자가 부담합니다. “몰”은 이용자에게 청약철회 등을 이유로 위약금 또는 손해배상을 청구하지 않습니다. 다만 재화 등의 내용이 표시·광고 내용과 다르거나 계약내용과 다르게 이행되어 청약철회 등을 하는 경우 재화 등의 반환에 필요한 비용은 “몰”이 부담합니다.

④ 이용자가 재화 등을 제공받을 때 발송비를 부담한 경우에 “몰”은 청약철회 시 그 비용을  누가 부담하는지를 이용자가 알기 쉽도록 명확하게 표시합니다.


제17조(개인정보보호)

① “몰”은 이용자의 개인정보 수집시 서비스제공을 위하여 필요한 범위에서 최소한의 개인정보를 수집합니다. 

② “몰”은 회원가입시 구매계약이행에 필요한 정보를 미리 수집하지 않습니다. 다만, 관련 법령상 의무이행을 위하여 구매계약 이전에 본인확인이 필요한 경우로서 최소한의 특정 개인정보를 수집하는 경우에는 그러하지 아니합니다.

③ “몰”은 이용자의 개인정보를 수집·이용하는 때에는 당해 이용자에게 그 목적을 고지하고 동의를 받습니다. 

④ “몰”은 수집된 개인정보를 목적외의 용도로 이용할 수 없으며, 새로운 이용목적이 발생한 경우 또는 제3자에게 제공하는 경우에는 이용·제공단계에서 당해 이용자에게 그 목적을 고지하고 동의를 받습니다. 다만, 관련 법령에 달리 정함이 있는 경우에는 예외로 합니다.

⑤ “몰”이 제2항과 제3항에 의해 이용자의 동의를 받아야 하는 경우에는 개인정보관리 책임자의 신원(소속, 성명 및 전화번호, 기타 연락처), 정보의 수집목적 및 이용목적, 제3자에 대한 정보제공 관련사항(제공받은자, 제공목적 및 제공할 정보의 내용) 등 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」 제22조제2항이 규정한 사항을 미리 명시하거나 고지해야 하며 이용자는 언제든지 이 동의를 철회할 수 있습니다.

⑥ 이용자는 언제든지 “몰”이 가지고 있는 자신의 개인정보에 대해 열람 및 오류정정을 요구할 수 있으며 “몰”은 이에 대해 지체 없이 필요한 조치를 취할 의무를 집니다. 이용자가 오류의 정정을 요구한 경우에는 “몰”은 그 오류를 정정할 때까지 당해 개인정보를 이용하지 않습니다.

⑦ “몰”은 개인정보 보호를 위하여 이용자의 개인정보를 취급하는 자를  최소한으로 제한하여야 하며 신용카드, 은행계좌 등을 포함한 이용자의 개인정보의 분실, 도난, 유출, 동의 없는 제3자 제공, 변조 등으로 인한 이용자의 손해에 대하여 모든 책임을 집니다.

⑧ “몰” 또는 그로부터 개인정보를 제공받은 제3자는 개인정보의 수집목적 또는 제공받은 목적을 달성한 때에는 당해 개인정보를 지체 없이 파기합니다.

⑨ “몰”은 개인정보의 수집·이용·제공에 관한 동의 란을 미리 선택한 것으로 설정해두지 않습니다. 또한 개인정보의 수집·이용·제공에 관한 이용자의 동의거절시 제한되는 서비스를 구체적으로 명시하고, 필수수집항목이 아닌 개인정보의 수집·이용·제공에 관한 이용자의 동의 거절을 이유로 회원가입 등 서비스 제공을 제한하거나 거절하지 않습니다.


제18조(“몰“의 의무)

① “몰”은 법령과 이 약관이 금지하거나 공서양속에 반하는 행위를 하지 않으며 이 약관이 정하는 바에 따라 지속적이고, 안정적으로 재화․용역을 제공하는데 최선을 다하여야 합니다.

② “몰”은 이용자가 안전하게 인터넷 서비스를 이용할 수 있도록 이용자의 개인정보(신용정보 포함)보호를 위한 보안 시스템을 갖추어야 합니다.

③ “몰”이 상품이나 용역에 대하여 「표시․광고의 공정화에 관한 법률」 제3조 소정의 부당한 표시․광고행위를 함으로써 이용자가 손해를 입은 때에는 이를 배상할 책임을 집니다.

④ “몰”은 이용자가 원하지 않는 영리목적의 광고성 전자우편을 발송하지 않습니다.


제19조(회원의 ID 및 비밀번호에 대한 의무)

① 제17조의 경우를 제외한 ID와 비밀번호에 관한 관리책임은 회원에게 있습니다.

② 회원은 자신의 ID 및 비밀번호를 제3자에게 이용하게 해서는 안됩니다.

③ 회원이 자신의 ID 및 비밀번호를 도난당하거나 제3자가 사용하고 있음을 인지한 경우에는 바로 “몰”에 통보하고 “몰”의 안내가 있는 경우에는 그에 따라야 합니다.


제20조(이용자의 의무) 이용자는 다음 행위를 하여서는 안 됩니다.

1. 신청 또는 변경시 허위 내용의 등록

2. 타인의 정보 도용

3. “몰”에 게시된 정보의 변경

4. “몰”이 정한 정보 이외의 정보(컴퓨터 프로그램 등) 등의 송신 또는 게시

5. “몰” 기타 제3자의 저작권 등 지적재산권에 대한 침해

6. “몰” 기타 제3자의 명예를 손상시키거나 업무를 방해하는 행위

7. 외설 또는 폭력적인 메시지, 화상, 음성, 기타 공서양속에 반하는 정보를 몰에 공개 또는 게시하는 행위


제21조(연결“몰”과 피연결“몰” 간의 관계)

① 상위 “몰”과 하위 “몰”이 하이퍼링크(예: 하이퍼링크의 대상에는 문자, 그림 및 동화상 등이 포함됨)방식 등으로 연결된 경우, 전자를 연결 “몰”(웹 사이트)이라고 하고 후자를 피연결 “몰”(웹사이트)이라고 합니다.

② 연결“몰”은 피연결“몰”이 독자적으로 제공하는 재화 등에 의하여 이용자와 행하는 거래에 대해서 보증 책임을 지지 않는다는 뜻을 연결“몰”의 초기화면 또는 연결되는 시점의 팝업화면으로 명시한 경우에는 그 거래에 대한 보증 책임을 지지 않습니다.


제22조(저작권의 귀속 및 이용제한)

① “몰“이 작성한 저작물에 대한 저작권 기타 지적재산권은 ”몰“에 귀속합니다.

② 이용자는 “몰”을 이용함으로써 얻은 정보 중 “몰”에게 지적재산권이 귀속된 정보를 “몰”의 사전 승낙 없이 복제, 송신, 출판, 배포, 방송 기타 방법에 의하여 영리목적으로 이용하거나 제3자에게 이용하게 하여서는 안됩니다.

③ “몰”은 약정에 따라 이용자에게 귀속된 저작권을 사용하는 경우 당해 이용자에게 통보하여야 합니다.


제23조(분쟁해결)

① “몰”은 이용자가 제기하는 정당한 의견이나 불만을 반영하고 그 피해를 보상처리하기 위하여 피해보상처리기구를 설치․운영합니다.

② “몰”은 이용자로부터 제출되는 불만사항 및 의견은 우선적으로 그 사항을 처리합니다. 다만, 신속한 처리가 곤란한 경우에는 이용자에게 그 사유와 처리일정을 즉시 통보해 드립니다.

③ “몰”과 이용자 간에 발생한 전자상거래 분쟁과 관련하여 이용자의 피해구제신청이 있는 경우에는 공정거래위원회 또는 시·도지사가 의뢰하는 분쟁조정기관의 조정에 따를 수 있습니다.


제24조(재판권 및 준거법)

① “몰”과 이용자 간에 발생한 전자상거래 분쟁에 관한 소송은 제소 당시의 이용자의 주소에 의하고, 주소가 없는 경우에는 거소를 관할하는 지방법원의 전속관할로 합니다. 다만, 제소 당시 이용자의 주소 또는 거소가 분명하지 않거나 외국 거주자의 경우에는 민사소송법상의 관할법원에 제기합니다.

② “몰”과 이용자 간에 제기된 전자상거래 소송에는 한국법을 적용합니다.</textarea>
						<input type="checkbox" name="agree" value="cb1" id="Check1" onclick="is_checked1()">
						<label for="Check1">동의</label>
					</fieldset>
					<br>
					<fieldset>
						<legend>개인정보 수집/이용 동의(필수)</legend>
						<textarea rows="5" cols="5">
1. 개인정보의 수집항목 및 수집방법 
“몰”에서는 기본적인 회원 서비스 제공을 위한 필수정보로 실명인증정보와 가입정보로 구분하여 다음의 정보를 수집하고 있습니다. 필수정보를 입력해주셔야 회원 서비스 이용이 가능합니다.

가. 수집하는 개인정보의 항목 
	* 수집하는 필수항목
		- 실명인증정보 : 이름, 휴대전화번호, 본인 인증 또는 I-PIN(개인식별번호), GPKI
		- 가입정보 : 아이디, 비밀번호, 성명, 이메일, 전화번호, 휴대전화번호, 기관명
	* 선택항목
		- 주소, 기관의 부서명

[컴퓨터에 의해 자동으로 수집되는 정보]
인터넷 서비스 이용과정에서 아래 개인정보 항목이 자동으로 생성되어 수집될 수 있습니다. 
	- IP주소, 서비스 이용기록, 방문기록 등

나. 개인정보 수집방법
홈페이지 회원가입을 통한 수집 

2. 개인정보의 수집/이용 목적 및 보유/이용 기간
“몰”에서는 정보주체의 회원 가입일로부터 서비스를 제공하는 기간 동안에 한하여 “몰” 서비스를 이용하기 위한 최소한의 개인정보를 보유 및 이용 하게 됩니다. 회원가입 등을 통해 개인정보의 수집·이용, 제공 등에 대해 동의하신 내용은 언제든지 철회하실 수 있습니다. 회원 탈퇴를 요청하거나 수집/이용목적을 달성하거나 보유/이용기간이 종료한 경우, 사업 폐지 등의 사유발생시 개인 정보를 지체 없이 파기합니다.

	* 실명인증정보
		- 개인정보 수집항목 : 이름, 휴대폰 본인 인증 또는 I-PIN(개인식별번호), GPKI
		- 개인정보의 수집·이용목적   : 홈페이지 이용에 따른 본인 식별/인증절차에 이용
		- 개인정보의 보유 및 이용기간 : I-PIN / GPKI는 별도로 저장하지 않으며 실명인증용으로만 이용

	* 가입정보
		- 개인정보 수집항목 : 아이디, 비밀번호, 성명, 이메일, 전화번호, 휴대전환번호, 기관명
		- 개인정보의 수집·이용목적 : 홈페이지 서비스 이용 및 회원관리, 불량회원의 부정 이용방지, 민원신청 및 처리 등
		- 개인정보의 보유 및 이용기간 : 2년 또는 회원탈퇴시

정보주체는 개인정보의 수집·이용목적에 대한 동의를 거부할 수 있으며, 동의 거부시 “몰”에 회원가입이 되지 않으며, “몰”에서 제공하는 서비스를 이용할 수 없습니다.

3. 수집한 개인정보 제3자 제공
“몰”에서는 정보주체의 동의, 법률의 특별한 규정 등 개인정보 보호법 제17조 및 제18조에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.

4. 개인정보 처리업무 안내
“몰”에서는 개인정보의 취급위탁은 하지 않고 있으며, 원활한 서비스 제공을 위해 아래의 기관을 통한 실명인증 및 공공 I-PIN, GPKI 인증을 하고 있습니다. 

	* 수탁업체
		- SCI서울신용평가정보(주)
		  · 위탁업무 내용 : 실명확인
		  · 개인정보 보유 및 이용 기간 : 신용평가 기관에서는 이미 보유하고 있는 개인정보이기 때문에 별도로 저장하지 않음
		- 행정자치부
		  · 위탁업무 내용 : 공공 I-PIN, GPKI 인증
		  · 개인정보 보유 및 이용 기간 : 행정자치부에서는 이미 보유하고 있는 개인정보이기 때문에 별도로 저장하지 않음</textarea>
						<input type="checkbox" name="agree" value="cb2" id="Check2" onclick="is_checked2()">
						<label for="Check2">동의</label>
					</fieldset>
					<br>
					<pre id='result1'></pre>
					<pre id='result2'></pre>
					<pre id='result3'></pre>
					<input type="checkbox" name="agree" value="cb2" id="CheckAll" onclick="is_checked3()">
					<label for="CheckAll">전체동의</label>					
					<span id="span"></span>
					<button type="submit" id="checkSubmit" >제출하기</button>
				</form>
			</article>
			
			<!-- join  -->
			
			<article id="joinPro">
				<h2 class="major">Join</h2>
				<form method="post" action="SendEmail.ma" id="fr" name="fr" onsubmit="return checkForm()">
					<div class="fields">
						<div class="field half">
							<label for="member_nickname">Nickname</label> 
							<input type="text" name="member_nickname" id="member_nickname" />
							<div id="nicknameResultArea"></div>
						</div>
						<div class="field half">
							<label for="member_id">ID</label>
							<input type="text" name="join_member_id" id="join_member_id" />
							<div id="idResultArea"></div>
						</div>
						<div class="field half">
							<label for="member_passwd">Password</label>
							<input type="password" name="join_member_passwd" id="join_member_passwd" onkeyup="checkPass(this.value)"/>
							<span id="passSafe"></span>
						</div>
						<div class="field half">
							<label for="member_passwd_check">Password Check</label> 
							<input type="password"name="join_member_passwd_check" id="join_member_passwd_check" onkeyup="checkPassResult(this.value)"/> 
								<span id="passCheck"></span>
						</div>
						<div class="field half">
							<label for="member_email1">Email</label> 
							<input type="text" name="member_email1" id="member_email1" onkeyup="checkEmail(this.value)" />
							<span id="emailCheck"></span>
						</div>
						<div class="field half">
							<label>도메인</label> 
							<select id="member_email2" name="member_email2" >
								<option value="">선택하세요</option>
								<option value="@gmail.com">gmail.com</option>
								<option value="@naver.com">naver.com</option>
								<option value="@nate.net">nate.net</option>
								<option value="@hanmail.net">hanmail.net</option>
								<option value="@daum.net">daum.net</option>
								<option value="@yahoo.com">yahoo.com</option>
							</select> 
						</div>
						<div class="field half">
							<label for="member_info_gender">성별</label> 
							<select id="member_info_gender" name="member_info_gender">
								<option value="male">남자</option>
								<option value="female">여자</option>
							</select>
						</div>
						<div class="field half">
							<label for="member_info_age">나이</label> 
							<select id="member_info_age" name="member_info_age">
								<option value="19세이하">19세이하</option>
								<option value="20~29">20~29</option>
								<option value="30~39">30~39</option>
								<option value="40~49">40~49</option>
								<option value="50~59">50~59</option>
								<option value="60~69">60~69</option>
								<option value="70대이상">70세이상</option>
							</select>
						</div>
						<div class="field half">
							<label for="member_info_detail_like_style">Style</label> 
							<input type="checkbox" name="style" value="러블리" id="Check3">
							<label for="Check3">러블리</label>
							<input type="checkbox" name="style" value="모던시크" id="Check4">
							<label for="Check4">모던시크</label>
							<input type="checkbox" name="style" value="캐주얼" id="Check5">
							<label for="Check5">캐주얼</label>
							<input type="checkbox" name="style" value="심플베이직" id="Check6">
							<label for="Check6">심플베이직</label>
							<input type="checkbox" name="style" value="유니크" id="Check7">
							<label for="Check7">유니크</label>
							<input type="checkbox" name="style" value="빈티지" id="Check8">
							<label for="Check8">빈티지</label>
							<input type="checkbox" name="style" value="럭셔리" id="Check9">
							<label for="Check9">럭셔리</label>
							<input type="checkbox" name="style" value="아메카지" id="Check10">
							<label for="Check10">아메카지</label>
							<input type="checkbox" name="style" value="클래식" id="Check11">
							<label for="Check11">클래식</label>
							<input type="checkbox" name="style" value="유니섹스" id="Check12">
							<label for="Check12">유니섹스</label>
						</div>
						<div class="field half">
							<label for="member_info_detail_like_brand">Brand</label> 
							<input type="checkbox" name="brand" value="에르메스" id="Check13">
							<label for="Check13">에르메스</label>
							<input type="checkbox" name="brand" value="샤넬" id="Check14">
							<label for="Check14">샤넬</label>
							<input type="checkbox" name="brand" value="루이비통" id="Check15">
							<label for="Check15">루이비통</label>
							<input type="checkbox" name="brand" value="롤렉스" id="Check16">
							<label for="Check16">롤렉스</label>
							<input type="checkbox" name="brand" value="까르띠에" id="Check17">
							<label for="Check17">까르띠에</label>
							<input type="checkbox" name="brand" value="프라다" id="Check18">
							<label for="Check18">프라다</label>
							<input type="checkbox" name="brand" value="미우미우" id="Check19">
							<label for="Check19">미우미우</label>
							<input type="checkbox" name="brand" value="셀린느" id="Check20">
							<label for="Check20">셀린느</label>
							<input type="checkbox" name="brand" value="톰브라운" id="Check21">
							<label for="Check21">톰브라운</label>
							<input type="checkbox" name="brand" value="발렌시아가" id="Check22">
							<label for="Check22">발렌시아가</label>
						</div>
						<div class="field half">
							<label for="member_info_detail_like_category">Like Item</label> 
							<input type="checkbox" name="category" value="상의" id="Check23">
							<label for="Check23">상의</label>
							<input type="checkbox" name="category" value="하의" id="Check24">
							<label for="Check24">하의</label>
							<input type="checkbox" name="category" value="신발" id="Check25">
							<label for="Check25">신발</label>
							<input type="checkbox" name="category" value="잡화" id="Check26">
							<label for="Check26">잡화</label>
						</div>
					</div>
					<ul class="actions">
						<li><input type="submit" value="Join" class="primary" /></li>
					</ul>
				</form>
			</article>
		</div>
		<!-- Footer -->
		<footer id="footer">
			<p class="copyright">
				&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.
			</p>
		</footer>
	</div>
	<!-- BG -->
	<div id="bg"></div>

	<!-- Scripts -->
	<script src="HomePage/assets/js/jquery-3.6.0.js"></script>
	<script src="HomePage/assets/js/browser.min.js"></script>
	<script src="HomePage/assets/js/breakpoints.min.js"></script>
	<script src="HomePage/assets/js/util.js"></script>
	<script src="HomePage/assets/js/main.js"></script>

</body>
</html>



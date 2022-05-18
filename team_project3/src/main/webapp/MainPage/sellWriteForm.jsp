<%@page import="vo.SellerDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.MemberBean"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
// MemberBean bean =new MemberBean();
// String member_code =request.getParameter("id");
String member_id =(String)session.getAttribute("sId");

//BoardListAction 클래스에서 request 객체에 저장한 객체 꺼내기(getAttribute())
//=> "pageInfo" 와 "articleList" 라는 속성명을 사용하여 꺼내기
//=> 단, 리턴타입이 Object 타입이므로 각 데이터타입으로 강제 형변환 필요

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script type="text/javascript">
	function optionChange() {            //대분류 소분류 <select/>
		var ko=['반팔','긴팔','롱슬리브'];
		var ja=['반바지','긴바지','와이드','슬랙스'];
		var sh=['스니커즈','워커','슬립온','슬리퍼'];
		var ka=['쥬얼리','모자','피규어'];
		
		var v=$('#s0').val();
		var o;
		if(v=='상의'){
			o=ko;
		}else if(v=='하의'){
			o=ja;
		}else if(v=='신발'){
			o=sh;
		}
		else{
			o=ka;
		}
		$('#s1').empty();
		$('#s1').append('<option>상세 카테고리(소분류)</option>');
		for(var i=0;i<o.length;i++){
			$('#s1').append('<option>'+o[i]+'</option>');
		}
	}
	 function readURL(obj) {   //사진 5장 뿌리기 
		
         let reader = new FileReader();
         if(!obj.files.length) {
             return;
         }
         for(let i=0;i<obj.files.length;i++){
         reader.readAsDataURL(obj.files[i]);
         reader.onload = function (e) {
             let img = $('<img  width=300,height=350 />');
             $(img).attr('src', e.target.result);
             $('#previewDiv').append(img);
         }
		}
         
     }
// 	   $(document).ready(function(){
//            // 옵션추가 버튼 클릭시
//            $("#addItemBtn").click(function(){
//                //파일 선택란을 보여준다.
//                $("tr#item1").show();
//                // tr태그의 마지막 번째를 구해 id="item"의 형태로 만들어 lastItemNo에 대입
//                var lastItemNo = $("#example tr:last").attr("id").replace("item", "");
//                //새로 추가 할 경우 두번째 tr 값을 복사하여 newitem변수에 대입
//                var newitem = $("#example tr:eq(1)").clone();
//                //아이템 추가시 id="item" 값에 넘버를 추가해 준다.               
//                newitem.attr("id","item"+(parseInt(lastItemNo)+1));
               
//                if(lastItemNo==5){
//                //그리고 해당 아이템은 5개 이상 생성할수 없도록 제한
//                    alert("5개 이상 파일 추가 하실 수 없습니다.");
//                }else{
//                $("#example").append(newitem);
//                }
//            });
//        });       
</script>
</head>
<body>

 <h1><%=member_id %>님의 판매글 작성</h1>
<form action="sellWritePro.pr" name="sellForm" method="post" enctype="multipart/form-data">
		
		
  	<ul>		
  	  
  			  <li><label for="imgInput" ></label>
   			 <input type="file" id="imgInput" name="sell_img_name"  onchange="readURL(this)">
  			  
<!--   			  <li><label for="imgInput" ></label> -->
<!--    			 <input type="file" id="imgInput" name="sell_sub_image_name"  onchange="readURL(this)"> -->
   			
<!--    			 <li><label for="imgInput" ></label> -->
<!--    			 <input type="file" id="imgInput" name="sell_just_image_name"  onchange="readURL(this)"> -->
   			 
   			 <div id="previewDiv" ></div>
 	 	
		
	
<!-- 		<li> -->
<!-- 			<table id="example"> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 						<button id="addItemBtn">파일업로드</button> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!--       	    	<tr id="item1" style="display: none"> -->
<!--             		<td><input type="file" name="sell_main_image_name" /></td> -->
<!--         		</tr> -->
<!-- 			</table> -->
			
<!-- 		</li>  -->
		<li><label>제목</label>
			<input type="text" name="sell_title" id=""  required="required" placeholder="제목"></li>
		<li><label>브랜드</label>
			<input type="text" name="sell_brand" id=""  required="required" placeholder="제목"></li>
		<li><label>색상</label>
			<input type="text" name="sell_color" id=""></li>
		<li><label>사이즈</label>
			<input type="text" name="sell_size" id=""  required="required"></li>
		<li><label>판매자</label>
			<input type="text"  value="<%=member_id %>" name=member_code id=""  required="required" readonly="readonly"></li>
		<li><label>희망금액</label>
			<input type="text" name="sell_price" id="" placeholder="희망하는 금액을 입력하세요." ></li>
			

			
		<li><label>상세 카테고리</label>
			<select id="s0" name="sell_category" onchange="optionChange();">
  				 <option selected="selected" >상세선택</option>
  	   	 	 	 <option value="상의">상의</option>
			   	 <option value="하의">하의</option>
			   	 <option value="신발">신발</option>
			  	 <option value="잡화">잡화</option>
			</select>
>
			<select id="s1" name="sell_category_detail">
		    	<option>상세 카테고리(소분류)</option>
			</select>
			</li>

		</ul>	
			<textarea rows="25" cols="50" name="sell_content" placeholder="상세설명"></textarea>
			
			<section id="commandCell">
				<input type="submit" value="판매등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>			
</form>
</body>
</html>
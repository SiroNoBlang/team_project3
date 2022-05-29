<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검수현황</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css'>
<link rel='stylesheet' href='https://raw.githubusercontent.com/forsigner/magic-check/master/css/magic-check.css'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400'>
<link rel='stylesheet' href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
<link rel="stylesheet" href="AdminPage/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css'><link rel="stylesheet" href="./style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
</head>
<body>
<!-- 페이징 처리 -->		
	<c:set var="pageNum" value="${pageInfo.getPageNum() }" /> <!-- 현재 페이지 번호 --> 
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" /><!-- 최대 페이지 수 --> 
	<c:set var="startPage" value="${pageInfo.getStartPage() }" /><!-- 시작 페이지 번호 --> 
	<c:set var="endPage" value="${pageInfo.getEndPage() }" /><!-- 끝 페이지 번호 --> 
	<c:set var="listCount" value="${pageInfo.getListCount() }" /><!-- 총 게시물 수 --> 
	<c:set var="listLimit" value="${pageInfo.getListLimit() }" /><!-- 표시할 페이지 수 --> 

	<div id="logo">
		<span class="big-logo">Admin</span> <span class="small-logo">&nbsp; A</span>
	</div>
	<div id="left-menu">
		<jsp:include page="/AdminPage/menu/menu.jsp"/>
	</div>
	<div id="main-content">
		<div id="header">
			<div class="header-left float-left">
				<i id="toggle-left-menu" class="ion-android-menu"></i>
			</div>
			<div class="header-right float-right">
				<a href="javascript:void(0)" onclick="confirmLogout()"><i class="icon ion-log-out" ></i></a>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">검수현황</div>
				    <section class="row text-center placeholders">
				        <div class="col-6 col-sm-3" id="completion" onclick="confirmType(this.id)">
				          <div class="panel panel-info">
				            <div class="panel-heading">검수완료</div>
				            <div class="panel-body">
				              <h4>${CountType.getCompletion() } </h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3" id="progress" onclick="confirmType(this.id)">
				          <div class="panel panel-success">
				            <div class="panel-heading">검수중</div>
				            <div class="panel-body">
				              <h4>${CountType.getProgress() }</h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3" id="cancel" onclick="confirmType(this.id)">
				          <div class="panel panel-warning">
				            <div class="panel-heading">검수반려</div>
				            <div class="panel-body">
				              <h4>${CountType.getCancel() }</h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3" id ="sale" onclick="confirmType(this.id)">
				          <div class="panel panel-danger">
				            <div class="panel-heading">판매완료</div>
				            <div class="panel-body">
				              <h4>${CountType.getSale() }</h4>
				            </div>
				          </div>
				        </div>
				      </section>
				</div>
				
				
				
				<div class="card">
					<div class="title">검수 목록</div>
					
					<div id="board-search">
					        <div class="container">
					            <div class="search-window">
					                <form action="ProductSearch.co" class="formCss">
										<select name="searchType" id="product">
										    <option value="sell_title" >상품명</option>
										    <option value="sell_brand" <c:if test="${param.searchType eq 'sell_brand'}"> selected="selected"</c:if>>브랜드</option>
										    <option value="sell_category" <c:if test="${param.searchType eq 'sell_category'}"> selected="selected"</c:if>>카테고리</option>
										</select>
					                        <label for="search" class="blind"></label>
					                        <input id="search" type="search" name="search" value="${param.search}">
					                        <button type="submit" class="btn btn-dark">검색</button>
					                </form>
					            </div>
					        </div>
					    </div>
						 <div id="board-list">
					        <div class="container">
					        
					        
					            <table class="board-table">
					                <thead>
						                <tr>
						  <!--                   <th scope="col" class="th-num"></th> -->
						                    <th scope="col" class="th-num">번호</th>
						                    <th scope="col" class="th-date">카테고리</th>
						                    <th scope="col" class="th-date">브랜드</th>
						                    <th scope="col" class="th-title">판매품제목 </th>
						                    <th scope="col" class="th-date">판매등록일</th>
						                    <th scope="col" class="th-date">반려/승인날짜</th>
						                    <th scope="col" class="th-date">현재상태</th>
						                </tr>
						                </thead>
						                <tbody>
						                <c:if test="${not empty productConfirmList and pageInfo.getListCount() > 0}">
										<c:forEach var="confirm" items="${productConfirmList }" varStatus="status">
							                <tr>
			<!-- 				                    <td> <input type="checkbox"> </td> -->
							                    <td>${listCount -(listCount -((pageNum-1)* listLimit + status.index)-1)} </td> 
							                    <td>${confirm.getSell_category() } </td>
							                    <td>${confirm.getSell_brand() } </td>
							                     <td>
													<a href="ProductConfirmDetail.co?sell_num=${confirm.getSell_num() }&page=${pageNum}">
								                    	${confirm.getSell_title() } </a>
								                    </td>
							                    <td>${confirm.getSell_write_date() } </td>
							                    <td>
							                    <c:choose>  
													<c:when test="${empty confirm.getSell_list_approve_date() }"> 
														미승인
													</c:when> 
													<c:otherwise>
															${confirm.getSell_list_approve_date() } 
													</c:otherwise> 
												</c:choose> 
							                    </td>
							                    <td>${confirm.getSell_list_item_status() } </td>
							                </tr>
							               </c:forEach>
										</c:if> 
					                </tbody>
					            </table>
				           	 <button type="button" class="btn btn-primary" onclick="location.href='ProductConfirm.co'">총 검수목록 보기</button>
					     <!--        <button type="button" class="btn btn-success">검수취소</button>
					            <button type="button" class="btn btn-danger">여긴뭐하지</button>  -->
					        </div>
    					</div>
    					 <section id="pageList">
								<c:choose>
									<c:when test="${pageNum > 1}">
										<input type="button" value="이전" onclick="location.href='ProductConfirm.co?page=${pageNum - 1}'">
									</c:when>
									<c:otherwise>
										<input type="button" value="이전">
									</c:otherwise>
								</c:choose>
									
								<c:forEach var="i" begin="${startPage }" end="${endPage }">
									<c:choose>
										<c:when test="${pageNum eq i}">
											${i }
										</c:when>
										<c:otherwise>
											<a href="ProductConfirm.co?page=${i }">${i }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						
								<c:choose>
									<c:when test="${pageNum < maxPage}">
										<input type="button" value="다음" onclick="location.href='ProductConfirm.co?page=${pageNum + 1}'">
									</c:when>
									<c:otherwise>
										<input type="button" value="다음">
									</c:otherwise>
								</c:choose>
							</section>
					</div>
				</div>
			</div>
	<span id="show-lable">Hello</span>
	<!-- partial -->
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js'></script>
	<script src="AdminPage/js/script.js"></script>
	<script src="AdminPage/js/jquery-3.6.0.js"></script>
</body>
</html>

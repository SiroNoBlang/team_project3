<%@page import="vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>test</h1>
	<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
	<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
	<c:set var="startPage" value="${pageInfo.getStartPage() }" />
	<c:set var="endPage" value="${pageInfo.getEndPage() }" />
	<c:set var="listCount" value="${pageInfo.getListCount() }" />
	
	<h2>좋아요 글 목록</h2>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>이미지제목</td>
		</tr>
<%-- 		<c:if test="${not empty articleList and pageInfo.getListCount() > 0}"> --%>
			<c:forEach var="like_list" items="${articleList }">
				<tr>
					<td>${like_list.getLike_list_count() }</td>
					<td id="subject">
						<a href="">
							${like_list.getLike_list_title() }
						</a>
					</td>
					<td>${like_list.getLike_list_img_name() } </td>
				</tr>
			</c:forEach>
<%-- 		</c:if> --%>
	</table>
		
		<!-- 페이징 처리 -->
		<section id="pageList"> 
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전" onclick="">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
			
		<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${pageNum < maxPage}">
				<input type="button" value="다음" onclick="">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>
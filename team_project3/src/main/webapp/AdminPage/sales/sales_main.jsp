<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css'>
<!-- <link rel='stylesheet' href='https://raw.githubusercontent.com/forsigner/magic-check/master/css/magic-check.css'> -->
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400'>
<link rel='stylesheet' href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
<link rel="stylesheet" href="AdminPage/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawVisualization);
		function drawVisualization() {
			var data = google.visualization.arrayToDataTable([
				['Month', '판매된 총액', '판매 수수료', '구매한 총액', '구매 수수료', '판매관리비', '순수익'],
				['2004/05', 165, 1235, 522, 998, 450, 614.6],
				['2004/06', 135, 1120, 599, 1268, 288, 682],
				['2004/07', 157, 1167, 807, 807, 397, 623],
				['2004/08', 139, 1110, 615, 968, 215, 609.4],
				['2004/09', 136, 691, 629, 1026, 366, 569.6]
			]);
			var options = {
				title: '매출 관련 현황',
				vAxis: {title: '천'},
				hAxis: {title: '월별'},
				seriesType: 'bars',
				series: {5: {type:'line'}}
			};
			var chart = new google.visualization.ComboChart(document.getElementById('salesChart'));
			chart.draw(data, options);
		};
</script>
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
		<%@ include file="/AdminPage/menu/menu.jsp" %>
	</div>
	<div id="main-content">
		<div id="header">
			<div class="header-left float-left">
				<i id="toggle-left-menu" class="ion-android-menu"></i>
			</div>
			<div class="header-right float-right">
				<i class="icon ion-log-out"></i>
			</div>
		</div>
		<div id="page-container">
			<div class="card">
				<div class="title">매출현황</div>
				    <section class="row text-center placeholders">
				        <div class="col-6 col-sm-3" onclick="location.href='#">
				          <div class="panel panel-success">
				            <div class="panel-heading">매출</div>
				            <div class="panel-body">
				              <h4>123</h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3" onclick="location.href='#'">
				          <div class="panel panel-warning">
				            <div class="panel-heading">매출 총이익</div>
				            <div class="panel-body">
				              <h4>123</h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3" onclick="location.href='#'">
				          <div class="panel panel-info">
				            <div class="panel-heading">순이익</div>
				            <div class="panel-body">
				              <h4>123</h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3" onclick="location.href='#">
				          <div class="panel panel-danger">
				            <div class="panel-heading">포괄손익</div>
				            <div class="panel-body">
				              <h4>123</h4>
				            </div>
				          </div>
				        </div>
				      </section>
				</div>				
				
				
				<div class="card">
					<div class="title">매출 그래프</div>
						<div class="container">
							<div id="salesChart" style="width: 900px; height: 500px;"></div>
						</div>
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

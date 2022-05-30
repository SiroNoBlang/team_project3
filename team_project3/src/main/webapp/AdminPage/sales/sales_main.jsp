<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매출관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css'>
<!-- <link rel='stylesheet' href='https://raw.githubusercontent.com/forsigner/magic-check/master/css/magic-check.css'> -->
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400'>
<link rel='stylesheet' href='http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'>
<link rel="stylesheet" href="AdminPage/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css'>
</head>
<body>
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
				        <div class="col-6 col-sm-3">
				          <div class="panel panel-success">
				            <div class="panel-heading">매출</div>
				            <div class="panel-body">
				              <h4>123</h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3">
				          <div class="panel panel-warning">
				            <div class="panel-heading">매출 총이익</div>
				            <div class="panel-body">
				              <h4>123</h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3">
				          <div class="panel panel-info">
				            <div class="panel-heading">순이익</div>
				            <div class="panel-body">
				              <h4>123</h4>
				            </div>
				          </div>
				        </div>
				        <div class="col-6 col-sm-3">
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
							<%@ include file="/AdminPage/sales/NewFile.jsp" %>
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

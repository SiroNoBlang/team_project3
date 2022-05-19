<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
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
	<!-- partial:index.partial.html -->
	<div id="logo">
		<span class="big-logo">Admin</span> <span class="small-logo">&nbsp; A</span>
	</div>
	<div id="left-menu">
		<ul>
			<!-- <li class="active"><a href="#"> <i
					class="ion-ios-person-outline"></i> <span>Dashboard</span>
			</a></li>-->
			<li class="#"><a href="#"> <i class="ion-ios-person-outline"></i> <span>멤버관리</span></a></li>
			<li class="has-sub"><a href="#"> <i class="icon ion-clipboard"></i> <span>검수현황</span></a>
<!-- 				<ul>
					<li><a href="#">검수진행상황</a></li>
				</ul> -->
			</li>
			<li class="has-sub"><a href="#"> <i class="ion-ios-chatboxes-outline"></i> <span>커뮤니티</span>
			</a>
				<ul>
					<li><a href="#">글쓰기</a></li>
					<li><a href="#">목록</a></li>
<!-- 					<li><a href="#">Report Item 3</a></li>
					<li><a href="#">Report Item 3</a></li>
					<li><a href="#">Report Item 3</a></li>
					<li><a href="#">Report Item 3</a></li>
					<li><a href="#">Report Item 3</a></li> -->
				</ul>
			</li>
		</ul>
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
				<div class="title">회원프로필</div>
				    	<span id="show-lable">Hello</span>
				    	<div class="container">
		<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12 edit_information">
			<form action=""  method="POST">	
				<h3 class="text-center">Edit Personal Information</h3>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<div class="form-group">
							<label class="profile_details_text">First Name:</label>
							<input type="text" name="first_name" class="form-control" value="" required >
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<div class="form-group">
							<label class="profile_details_text">Last Name: </label>
							<input type="text" name="last_name" class="form-control" value="" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="profile_details_text">Email Address:</label>
							<input type="email" name="email" class="form-control" value="" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="profile_details_text">Mobile Number:</label>
							<input type="tel" name="phone" class="form-control" value="" required pattern=[0-9]{10}>
							
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="profile_details_text">Date Of Birth:</label>
							<input type="date" name="birthday" class="form-control" value="" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="profile_details_text">Gender:</label>
							<select name="gender" class="form-control" value="" required>
								<option value="Male">Male</option>
								<option value="Female">Female</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="profile_details_text">Nationality:</label>
							<input type="text" name="nationality" class="form-control" value="" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
							<label class="profile_details_text">Monthly Income:</label>
							<input type="text" name="monthly_income" class="form-control" value="" required>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 submit">
						<div class="form-group">
							<input type="submit" class="btn btn-success" value="Submit">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
				    	
				    
				    	
	</div>
	</div>
	</div>
				  
	<!-- partial -->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js'></script>
	<script src="AdminPage/script.js"></script>

</body>
</html>

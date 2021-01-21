<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%--     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Font Awesome -->
<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
<link rel="stylesheet"
	href="./resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- <!-- icheck bootstrap -->
<!-- <link rel="stylesheet" -->
<!-- 	href="./resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css"> -->
<!-- Theme style -->
<link rel="stylesheet"
	href="./resources/bootstrap/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<!-- jQuery -->
<script src="./resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script
	src="./resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="./resources/bootstrap/dist/js/adminlte.min.js"></script>

<style>
body.login-page {
	background-image: url('/resources/images/intro.jpg');
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
}
</style>
<script>
$(function(){
	
	if(Cookies.get("id") != undefined){
		$("#id").val(Cookies.get("id"));
		$("#rememberMe").prop("checked", true);
	}
	
	
	$('#signin').on('click', function(){
	
		if($("#rememberMe").is(":checked") == true){
			Cookies.set("id", $("#id").val());
			Cookies.set("rememberme", "Y");
		}else{
			Cookies.remove("id");
			Cookies.remove("rememberme");
		}
		
		$("#frm1").submit();
			
	})

})
</script>

</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>관리자 로그인</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>

				<!-- form start -->
				<form id="frm1" action="${cp }/login" method="post">
				<!-- pageSize, page (hidden) -->
				<input type="hidden" name="page" value="1"/>
				<input type="hidden" name="pageSize" value="5"/>
				
					<div class="form-group has-feedback">
						<input type="text" class="form-control" id="id" name="id" value="brown"
							placeholder="아이디를 입력하세요." required autofocus> <span
							class="glyphicon glyphicon-envelope form-control-feedback"
							></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" id="pwd" name="pwd" value="brownPass"
							placeholder="패스워드를 입력하세요." required> <span
							class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="checkbox icheck">
								<label><input type="checkbox" id="rememberMe" name="rememberMe"> Remember Me</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-sm-4">
							<button type="button" id="signin" class="btn btn-primary btn-block btn-flat">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

			</div>
			<!-- /.login-box-body -->
		</div>
	</div>
	<!-- /.login-box -->

</body>
</html>









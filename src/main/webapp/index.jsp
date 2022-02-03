<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/member/login.css">
</head>
<body>

<div class="wrapper">
	
	<div class="wrap">
		<div class="logo_wrap">
			<span>가입 부탁드립니다!</span>
		</div>
		<div class="login_wrap"> 
			<div class="id_wrap">
					<div class="id_input_box">
					<input class="id_input">
				</div>
			</div>
			<div class="pw_wrap">
				<div class="pw_input_box">
					<input class="pw_iput">
				</div>
			</div>
			<div class="login_button_wrap">
				<input type="button" class="login_button" value="로그인">
			</div>			
			<div class="login_button_wrap">
				<a href="<%=request.getContextPath() %>/member/join"><input type="button" class="login_button" value="회원가입"></a>
			</div>			
		</div>
		
		
	</div>

</div>

</body>
</html>
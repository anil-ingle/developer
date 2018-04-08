<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet"
   type = "text/css"
   href = "css/myStyle.css" />
<title>Login</title>
</head>
<body style="background: url('park.jpg')">
<form action="login"  method="post">
		<div class="main-container">
			<div class="user-logo">
				<img style="width: 100px; border-radius: 50px;" src="images/user-logo.jpeg">
			</div>
			<h2>${msg}</h2>
			<div class="input-user">
				<input type="text" name="email" placeholder="email">
				<input type="password" name="password" placeholder="password">	
			</div>
			<div class="bottom-login">
				<input type="submit" value="Login" name="submit">
				<input type="submit" value="forgot password" name="forgot">
				<!-- <div class="hiperlink">
					<a href="#">Forgot Password</a>
				</div>
				<div class="hiperlink">
					<a href="#">New User</a>
				</div> -->
			</div>
		</div>
	</form>
</body>
</html>
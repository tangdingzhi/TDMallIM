<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
	<header>
	<div class="content">
		<p class="text">登录</p>
		<div class="inputBox">
			<input type="text" placeholder="用户名" class="phone">
		</div>
		<!--<div class="errMsg">
				<img src="./img/login/gth.png">
			</div> -->
		<div class="inputBox">
			<input type="password" placeholder="密码（6-16个字符）" class="password">
		</div>
		<div class="radioIpt">
			<div>
				<input type="radio" checked="checked" name="Radio" value="2" /> <span>聊天系统</span>
			</div>
			<div>
				<input type="radio" name="Radio" value="1" /> <span>后台系统</span>
			</div>
		</div>
		<!-- <div class="remenber">
				<p><a href="#">新用户注册</a></p>
				<p><a href="#">忘记密码？</a></p>
			</div> -->
		<button class="loginBtn">登录</button>
	</div>
	</header>
	<footer>
	<p>Copyright 2017-2018 云栖 All Rights Reserved.</p>
	</footer>
	<canvas id="background"></canvas>
	<script src="/js/jquery-3.2.1.min.js"></script>
	<script src="/js/login.js"></script>
	<script async type="text/javascript" src="/js/background.js"></script>
	<div
		style="text-align: center; margin: 50px 0; font: normal 14px/24px 'MicroSoft YaHei';">
	</div>
</body>
</html>
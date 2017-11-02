<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>通兑商城客服系统</title>
	<link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
	<header>
		<div class="content">
			<p class="text">登录</p>
			<div class="inputBox">
				<input type="text" placeholder="用户名" class="phone">
			</div>
			<div class="inputBox">
				<input type="password" placeholder="密码（6-16个字符）" class="password">
			</div>
			 <div class="radioIpt">
		        <div>
		          <input type="radio" checked="checked" name="Radio" value="2"/>
		          <span>聊天系统</span>
		        </div>
		        <div>
		          <input type="radio" name="Radio" value="1" />
		          <span>后台系统</span>
		        </div>
		      </div>
			<button class="loginBtn"><p>登录</p></button>
		</div>
	</header>
	<footer>
		<p>Copyright © 2017 通兑商城客服系统 All Rights Reserved.</p>
	</footer>
<canvas id="background"></canvas>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.js"></script>  
<script src="/js/common.js"></script>
<script src="/js/login.js"></script>
<script async type="text/javascript" src="/js/background.js"></script>
</body>
</html>
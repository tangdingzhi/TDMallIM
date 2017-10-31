<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>客服管理</title>
	<link rel="stylesheet" type="text/css" href="css/system.css">
</head>
<body>
	<header>
		<p>客服后台管理系统</p>
	</header>
	<div class="content">
		<div id="input-button">
			<input type="text" placeholder="请输入用户账号">
			<button>查询</button>
		</div>
		<table border="0">
		  <tr>
		    <th>用户ID</th>
		    <th>用户账号</th>
		    <th>用户姓名</th>
		    <th>所属商铺ID</th>
		    <th>所属商铺名称</th>
		    <th>创建时间</th>
		    <th>操作</th>
		  </tr>
		  <tr>
		    <td>January</td>
		    <td>$100</td>
		    <td>$100</td>
		    <td>$100</td>
		    <td>$100</td>
		    <td>$100</td>
		    <td><button>修改</button><button>删除</button></td>
		  </tr>
		</table>
	</div>
	
	<footer>
		<p>Copyright © 2017 通兑商城 All Rights Reserved.</p>
	</footer>
	<script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/common.js"></script>
	<script src="js/system.js"></script>

</body>
</html>
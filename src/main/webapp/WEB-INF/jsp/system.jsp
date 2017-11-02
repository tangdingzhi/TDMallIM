<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>客服管理</title>
	<link rel="stylesheet" type="text/css" href="/css/system.css">
	<link rel="stylesheet" type="text/css" href="/css/paging.css">
</head>
<body>
	<header>
		<p>客服后台管理系统</p>
	</header>
	<div class="content">
		<div id="input-button">
			<input type="text" placeholder="请输入用户账号" id="name">
			<button class="clickQuery">查询</button>
			<button class="Refresh">刷新</button>
			<button class="added">新增</button>
		</div>
		<table border="0">
		 <thead>
		    <th>用户账号</th>
		    <th>用户姓名</th>
		    <th>所属商铺ID</th>
		    <th>所属商铺名称</th>
		    <th>创建时间</th>
		    <th>操作</th>
		 </thead>
		 <tbody class="massge">
		 </tbody>
		</table>
		<div class="addBox">
			<div>
				<p>用户账号：</p>
				<input type="text" class="account">
			</div>
			<div>
				<p>用户名字：</p>
				<input type="text" class="name">
			</div>
			<div>
				<p>备注：</p>
				<input type="text" class="remarks">
			</div>
			<div class="shopBox">
				<p>商铺名称：</p>
				<div>
					<input type="text" name="makeupCo" id="makeupCo" class="makeinp shop" onfocus="setfocus(this)" oninput="setinput(this);"/>  
				 	<select name="makeupCoSe" id="typenum" onchange="changeF(this)" size="6" style="display:none;">  
					 </select>  
				</div>
			</div>
			<div>
				<button class="submit">提交</button>
				<button class="cancel">取消</button>
			</div>
		</div>
	</div>

 	<div class="box" id="box"></div>


	<footer>
		<p>Copyright © 2017 通兑商城 All Rights Reserved.</p>
	</footer>
	<script src="/js/jquery-3.2.1.min.js"></script>
	<script src="/js/paging.js"></script>
    <script src="/js/common.js"></script>
	<script src="/js/system.js"></script>
</body>
</html>
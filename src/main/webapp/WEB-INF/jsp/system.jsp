<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>客服管理</title>
	<link rel="stylesheet" type="text/css" href="/css/system.css">
	<link rel="stylesheet" type="text/css" href="/css/paging.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<header>
		<p>客服后台管理系统</p>
	</header>
	<div class="content">
		<div id="input-button">
			<input type="text" placeholder="请输入用户账号" id="name">
			<button class="clickQuery btn btn-default">查询</button>
			<button class="Refresh btn btn-default">刷新</button>
			<button class="added btn btn-default">新增</button>
		</div>
		<table border="0" class="table table-striped table-bordered">
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
				<button class="submit btn btn-success">提交</button>
				<button class="cancel btn btn-warning">取消</button>
			</div>
		</div>
		<div class="delBox">
			<p>你确定要删除吗？</p>
			<button class="del btn btn-success">确定</button>
			<button class="undel btn btn-warning">取消</button>
		</div>
 		<div class="box" id="box"></div>
	</div>



	<footer>
		<p>Copyright © 2017 通兑商城客服后台管理系统 All Rights Reserved.</p>
	</footer>
	<canvas id="background"></canvas>
	<script src="/js/jquery-3.2.1.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/js/paging.js"></script>
    <script src="/js/common.js"></script>
	<script src="/js/system.js"></script>
	<script src="/js/background.js"></script>
</body>
</html>
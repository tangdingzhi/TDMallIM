<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="/css/chat.css">
</head>
<body>
	<div class="chat-container">
	    <div class="chat-board">
          	<div class="user-info">
               <p class="username">${server.name}</p>
	        </div>
	        <div class="utils">
	            <p>聊天人数：<span class="userNumber"></span></p>
	            <button>注销</button>
	        </div>
            <ul class="user-name" id="usersName">
              	
            </ul>
        </div>
	    <div class="message">
	    	<div class="message-board-head">
	    		<p class="onlyName"></p>
	    	</div>
	        <ul class="message-board"  style=" overflow-y:scroll; width:585px; height:400px;">
	            
	        </ul>
		    <div class="message-input">
		    	<div>
			    	<div contenteditable="true" class="test_box">
			    	</div> 
		    	</div>
		        <div class="input-bottom">
		        	<form id="input_box" enctype="multipart/form-data">
					    <label for="file" id="pic">图片</label>
						<input type="file" id="file" />
					</form>
		          	<button onclick="sendMessageToServer(1)" id="submitBtn">发送</button>
		        </div>
		    </div>
     	</div>
    </div>
    <input type="hidden" id="server" value="${server.id}"/>
    <footer>
      <p>Copyright © 2017 通兑商城 All Rights Reserved.</p>
    </footer>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/chat.js"></script>
</body>
</html>
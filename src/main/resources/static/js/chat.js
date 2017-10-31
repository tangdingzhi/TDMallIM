var ws = null;
var shopMsg;
var data = {
    user:[],
    index:0,
};
var fromId = "",toId = "",fname="",tname="";
var shopname = "";
var shopID = "";
var obj;
var className;
var sendImgUrl;
var pattern = /^(http(s)?:\/\/)?(www\.)?[\w-]+\.\w{2,4}(\/)?$/;

WebSocketTest();



function WebSocketTest() {
	shopname = $(".username").text();
	shopID = $("#server").val();
	if (ws == null && "WebSocket" in window) {
     	ws = new WebSocket("ws://localhost:12345/im");
     	ws.onopen = function() {
	        // Web Socket 已连接上，使用 send() 方法发送数据
	        ws.send('{"toID":"","type":2,"fromID":"' + shopID + '","fromName":"' + shopname + '"}');
	        //聊天界面加载完成后链接服务器
	        //链接服务器成功后发送一条type=1；fromId=用户id，fromname=用户名字；toID等于商铺id
	        //用户链接成功，服务器发生一条fromid=客服id，fromname=客服名字；content=聊天记录；
	        //用户链接失败；服务器会发送一条status=500；content=错误提示；
	        //正常聊天type=0 时间必须传当前时间的毫秒数；
	        //如果收到type=10；断开链接；
	        };
        ws.onmessage = function(evt) {
            var received_msg = evt.data;
            //回调过来的信息
            shopMsg = JSON.parse(received_msg);
	   		//将发送者和接收者id替换
			if(shopMsg.type !== 10){
				tname = shopMsg.fromName;//客服发送消息时的名字
				toId = shopMsg.fromID;//客服发送消息时的ID
			}
			console.log(shopMsg)
			//添加数据
			switch (shopMsg.type) {
				case 0:
					//时间转换
					let unixTimestamp = new Date(shopMsg.sendTime) ;
					var nowTime = mend(unixTimestamp)
					//正常通讯时，直接添加
					if(shopMsg.contentType == 2){
						$(".message-board").append(`<li class="${shopMsg.fromID}"><p>${shopMsg.fromName} : ${nowTime}</p><p><img src="${shopMsg.content}"/></p></li>`);
					}else if(shopMsg.contentType == 1){
						$(".message-board").append(`<li class="${shopMsg.fromID}"><p>${shopMsg.fromName} : ${nowTime}</p><p><a href="${shopMsg.content}">${shopMsg.content}</a></p></li>`);
					}else {
						$(".message-board").append(`<li class="${shopMsg.fromID}"><p>${shopMsg.fromName} : ${nowTime}</p><p>${shopMsg.content}</p></li>`);
					}
					//提示消息并显示
					dataPrompt(toId)
					if(className == toId){
						showdiv(toId)
					}
					break;
				case 1:
					//初次链接，创建信息
					messageRecord= JSON.parse(shopMsg.content);
					for (var i = messageRecord.length-1; i >= 0  ; i--) {
						if (messageRecord[i].fromID == shopID) {
							let unixTimestamp = new Date(messageRecord[i].sendTime) ;
							var nowTime = mend(unixTimestamp)
							if(messageRecord[i].contentType == 2){
								$(".message-board").append(`<li class='left ${messageRecord[i].toID}'><p>${messageRecord[i].fromName} : ${nowTime}</p><img src="${messageRecord[i].content}"/></li>`);
							}else if(messageRecord[i].contentType == 1){
								$(".message-board").append(`<li class='left ${messageRecord[i].toID}'><p>${messageRecord[i].fromName} : ${nowTime}</p><a href="${messageRecord[i].content}">${messageRecord[i].content}</a></li>`);
							}else {
								$(".message-board").append(`<li class='left ${messageRecord[i].toID}'><p>${messageRecord[i].fromName} : ${nowTime}</p><p>${messageRecord[i].content}</p></li>`);
							}
						}else {
							let unixTimestamp = new Date(messageRecord[i].sendTime) ;
							var nowTime = mend(unixTimestamp)
							if(messageRecord[i].contentType == 2){
								$(".message-board").append(`<li class='${messageRecord[i].fromID}'><p>${messageRecord[i].fromName} : ${nowTime}</p><img src="${messageRecord[i].content}"/></li>`);
							}else if(messageRecord[i].contentType == 1){
								$(".message-board").append(`<li class='${messageRecord[i].fromID}'><p>${messageRecord[i].fromName} : ${nowTime}</p><a href="${messageRecord[i].content}">${messageRecord[i].content}</a></li>`);
							}else{
								$(".message-board").append(`<li class='${messageRecord[i].fromID}'><p>${messageRecord[i].fromName} : ${nowTime}</p><p>${messageRecord[i].content}</p></li>`);
							}
						}
					}
					//将获取的用户加入到data这个对象的todoList这个数组中
					if(shopMsg.fromID !== shopID){
						data.user.unshift({
			                userID:shopMsg.fromID,
			                userName:shopMsg.fromName,
			                completed:false,
			                clickThis:false,
			                _id:data.index++
			            });
			          
			            render()
					}
					break;
				case 2:
					// statements_1
					break;
				case 6:
					// statements_1
					break;
				case 7:
					// statements_1
					break;
				case 8:
					// statements_1
					break;
				case 9:
					alert("您的账户在其他地方上线！")
					break;
				case 10:
					//找到这个用户并干掉他
					data.user.forEach((item,index)=>{
		                if(item.userID == shopMsg.fromID){
		                    data.user.splice(index,1);
		                    removeClass(shopMsg.fromID)
		                }
		            })
		            render()
					break;
				default:
					// statements_def
					break;
			}
   			$('.message-board').scrollTop( $('.message-board')[0].scrollHeight );
        };
		ws.onclose = function() {
			// 关闭 websocket
			ws = null;
			alert("连接已关闭...");
		};
	} else {
		// 浏览器不支持 WebSocket
		alert("您的浏览器不支持 WebSocket或已连接!");
	}
}


handle()
// 通过Socket发送一条消息到服务器
function sendMessageToServer(message) {
	obj = shopMsg;
	obj.fromID = shopID;
	obj.fromName = shopname;
	obj.toName = tname;
	obj.toID = toId;
	obj.type = 0;
	obj.sendTime = new Date().getTime();
	let str = $("div .test_box").text();
	console.log(pattern.test(str));

	//1.如果是图片；2.如果是链接；3.如果是文本
	if($("div .test_box").find("img").length == 1){
		obj.content = sendImgUrl
		obj.contentType = 2;
	}else if(pattern.test(str)){
		obj.content = str;
		obj.contentType = 1;
	}
	else{
		obj.content =$("div .test_box").text();
		obj.contentType = 0;
	}
	
	let textMsg = JSON.stringify(obj);
	console.log(textMsg)
	if (textMsg !== null && obj.toID !== shopID) {
		ws.send(textMsg);
		let unixTimestamp = new Date(obj.sendTime) ;
		var nowTime = mend(unixTimestamp)
		console.log(nowTime)
		if ($("div .test_box").find("img").length == 1) {
			$(".message-board").append(`<li class='left ${obj.toID}'><p>${shopname} : ${nowTime}</p><img src="${obj.content}"/></li>`);
			$("div.test_box").text("")
		}else if(pattern.test(str)){
			$(".message-board").append(`<li class='left ${obj.toID}'><p>${shopname} : ${nowTime}</p><a href="${obj.content}">${obj.content}</li>`);
			$("div.test_box").text("")
		}else {
			$(".message-board").append(`<li class='left ${obj.toID}'><p>${shopname} : ${nowTime}</p><p>${obj.content}</p></li>`);
			$("div.test_box").text("")
		}
		showdiv(obj.toID)
	}
	$('.message-board').scrollTop( $('.message-board')[0].scrollHeight );
}


//过滤content是否为json字符串或者是string
function isJSON(str) {
	if (typeof str == 'string') {
	    try {
	        var obj=JSON.parse(str);
	        if(str.indexOf('{')>-1){
	            return true;
	        }else{
	            return false;
	        }

	    } catch(e) {
	        console.log(e);
	        return false;
	    }
	}
	return false;
}

//添加到页面中
function render(){
    var list = data.user.map((item)=>{
        return`<li id=${item.userID} _id="${item._id}" class="${item.completed ? "":"completed"} ${item.clickThis ? "clickThis":""}">${item.userName}</li>`;
    }).join("");//将输入进来的东西循环转换成字符串
    usersName.innerHTML = list;//将字符串放到ul中
    $(".userNumber").text(data.user.length)
}

//提示消息
function dataPrompt(fromID){
    data.user.forEach((item,index)=>{
        if(item.userID == fromID){
            item.completed = false;
        }
    })
   render()
}


function handle(){
	usersName.addEventListener("click",(e)=>{
        var ele = e.target;
        if(ele.nodeName === "LI"){
            var id = ele.getAttribute("_id");
             // 取消提示颜色
            data.user.forEach((item)=>{
                if(item._id == id){
                    item.completed = true;
                    item.clickThis = true;
                    $(".onlyName").text(item.userName)
                }else{
                	item.clickThis = false;
                }
            })
		className = ele.getAttribute("id");
		toId = className
		showdiv(className);
        render();
        }
	$('.message-board').scrollTop( $('.message-board')[0].scrollHeight );
    },false)
}


//展开点击的对话框
function showdiv(className){
	$(".message-board li").css("display","none");
	$(`.${className}`).css("display","block");
}
//展开点击的对话框
function removeClass(className){
	$(`.${className}`).remove();
}

//转成自己要的时间
function mend(unixTimestamp){
	let yy = unixTimestamp.getYear();   
        if(yy<1900) yy = yy+1900;   
    let MM = unixTimestamp.getMonth()+1;   
        if(MM<10) MM = '0' + MM;   
    let dd = unixTimestamp.getDate();   
        if(dd<10) dd = '0' + dd;   
    let hh = unixTimestamp.getHours();   
        if(hh<10) hh = '0' + hh;   
    let mm = unixTimestamp.getMinutes();   
        if(mm<10) mm = '0' + mm;   
    let ss = unixTimestamp.getSeconds();   
        if(ss<10) ss = '0' + ss;   
    return yy + "/" + MM + "/" + dd + " " + hh + ":" + mm + ":" + ss;
}

//上传图片
$("#file").change(function(e) {
	console.log(e.target.files)
	var file = e.target.files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		var base64 = this.result;
		$(".test_box").append( `<img src="${base64}"/>`);
		var param = new FormData();
        param.append('file', file);
        $.ajax({
            url: `${commonUrl}/manage/upimg`,
            type: 'POST',
            cache: false,
            contentType: false,
            processData: false,
            data: param,
            success: function (response) {
            	sendImgUrl = response.data
            	console.log(sendImgUrl)
            },
            error: function (response) {
            	console.log('in')
               
            }
        });

	}
});
//快捷键enter
$(document).keyup(function(event){ 
	console.log('in')
    if(event.keyCode ==13){ 
      $("#submitBtn").trigger("click"); 
    } 
});



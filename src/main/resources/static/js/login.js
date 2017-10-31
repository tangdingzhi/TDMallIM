$(".loginBtn").on("click",function () {
	var user = {
        acc:$(".phone").val(),
        password:$(".password").val(),
        type:$('input:radio:checked').val()
    };
	console.log(user)
	$.ajax({
        url: "http://192.168.1.15:8081/login",
        type: 'POST',
        data: user,
        success: function (response) {
        	console.log(response)
        	window.location.href="http://192.168.1.15:8081/server"; 
        },
        error: function (response) {
        	console.log('in')
           
        }
    });
})	

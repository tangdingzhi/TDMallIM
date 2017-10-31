$(".loginBtn").on("click",function () {
    var passwd = $(".password").val();
	var user = {
        acc:$(".phone").val(),
        password: md5(passwd),
        type:$('input:radio:checked').val()
    };
	$.ajax({
        url: `${commonUrl}/login`,
        type: 'POST',
        data: user,
        success: function (data) {
            if(data.type == 2){
                window.location.href = `${commonUrl}/manage/server/web`;
            }else if(data.type == 1) {
                window.location.href = `${commonUrl}/manage/master/web`;
            }
        },
        error: function (data) {

        }
    });
})	

$(document).keyup(function(event){ 
    if(event.keyCode ==13){ 
      $(".loginBtn").trigger("click"); 
    } 
});

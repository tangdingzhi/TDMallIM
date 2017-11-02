var currentPage = {
    rows: 10,
    page: 1,
    name:"",
};
var oldshop;
var oldAccID;
var totalPage = 0,pageCount = 0;
var oldAcc;
  
query()
//初次渲染
function query() {
    currentPage = {
        rows: 10,
        page: 1,
        name:"",
    };
    $.ajax({
        url: `${commonUrl}/manage/server/selServer`,
        type: 'POST',
        data: currentPage,
        success: function (data) {
            totalPage = data.data.total
            pageCount = Math.ceil(data.data.total/10)
            showMsg(data.data.rows)
            $('#box').paging({
                initPageNo: 1, // 初始页码
                totalPages: pageCount, //总页数
                slideSpeed: 600, // 缓动速度。单位毫秒
                callback: function(page) { // 回调函数
                    NextQueryUser(page)
                }
            })
        },
        error: function (data) {
        }
    });
}
//翻页
function NextQueryUser(eachpage) {
    currentPage = {
        rows: 10,
        page: eachpage,
        name:'',
    };
    $.ajax({
        url: `${commonUrl}/manage/server/selServer`,
        type: 'POST',
        data: currentPage,
        success: function (data) {
           showMsg(data.data.rows)
        },
        error: function (data) {
        }
    });
}
//查询
function queryUser(eachpage) {
    currentPage = {
        rows: 10,
        page: 1,
        name:$("#name").val(),
    };
    $.ajax({
        url: `${commonUrl}/manage/server/selServer`,
        type: 'POST',
        data: currentPage,
        success: function (data) {
            totalPage = data.data.total
            pageCount = Math.ceil(data.data.total/10)
            showMsg(data.data.rows)
            $('#box').paging({
                initPageNo: 1, // 初始页码
                totalPages: pageCount, //总页数
                slideSpeed: 600, // 缓动速度。单位毫秒
                callback: function(page) { // 回调函数
                }
            })
        },
        error: function (data) {
        }
    });
}
//分页
$(".clickQuery").on("click",function(){
    queryUser()
    $("#name").val("")
}) 

$(".Refresh").on("click",function(){
    query()
}) 

function showMsg(data){
    $(".massge").empty()
    var str = "";
    for(let i = 0 ; i < data.length; i++){
        let unixTimestamp = new Date( data[i].timeStamp.time)
        let nowTime = mend(unixTimestamp)
        str += `<tr>
            <td>${data[i].acc}</td>
            <td>${data[i].name}</td>
            <td class="dnone">${data[i].note}</td>
            <td>${data[i].shopID}</td>
            <td>${data[i].shopName}</td>
            <td>${nowTime}</td>
            <td class="dnone">${data[i].id}</td>
            <td><button onclick="modify(this)">修改</button><button onclick="showzhi(this)">删除</button></td>
        </tr>`
    }
    $(".massge").append(str)
}
//删除
function  showzhi(obj){  
    let x = $(obj).parent().parent().find("td");  
    let y = x.eq(6).text()  
    let userID={
        id:y
    } 
    $.ajax({
        url: `${commonUrl}/manage/server/delServer`,
        type: 'POST',
        data: userID,
        success: function (data) {
          if(data.status == 100){
             query()
          }
        },
        error: function (data) {
        }
    })
};  

//渲染时间
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
    return yy + "/" + MM + "/" + dd + " " + hh + ":" + mm;
}

//新增用户
$(".added").on("click",function(){
    $(".addBox").css("display","inline-block")
    $(".account").val("").attr("readOnly",false);
    $(".name").val("")
    $(".remarks").val("")
    $("#typenum").val("");
    $(".shop").val("")
})
//取消
$(".cancel").on("click",function(){
    $(".addBox").css("display","none")
})
//提交时
$(".submit").on("click",function(){
    let account = $(".account").val();
    let name = $(".name").val();
    let remarks = $(".remarks").val();
    let shopid = $("#typenum").val();
    let submitUser = {};
    if(account == oldAcc){
         submitUser={
            id:oldAccID,
            name:name,
            note:remarks,
            shopid:oldshop,
        }
    }else{
         submitUser={
            acc:account,
            name:name,
            note:remarks,
            shopID:shopid,
        }
    }
    $.ajax({
        url: `${commonUrl}/manage/server/save`,
        type: 'POST',
        data: submitUser,
        success: function (data) {
          if(data.status == 100){
             query()
          }
        },
        error: function (data) {
        }
    })
    $(".addBox").css("display","none")
})

//修改用户
function  modify(obj){ 
    $("#typenum").val("");
    let x = $(obj).parent().parent().find("td");  
    oldAcc = x.eq(0).text() 
    let oldName = x.eq(1).text() 
    let oldRemarks = x.eq(2).text()
    oldshop = x.eq(3).text()
    let oldshopName = x.eq(4).text()
    oldAccID = x.eq(6).text()
    $(".account").val(oldAcc).attr("readOnly","true");
    $(".name").val(oldName)
    $(".remarks").val(oldRemarks)
    $(".shop").val(oldshopName)
    $(".addBox").css("display","inline-block")
}; 

//监听input输入时模糊查询
$('.shop').bind('input propertychange', function() {  
    queryShop()
}); 

function queryShop(){
    let shopPage = {
        rows: 10,
        page: 1,
        name:$(".shop").val(),
    };
    $.ajax({
        url: `${commonUrl}/manage/shop/selShop`,
        type: 'POST',
        data: shopPage,
        success: function (data) {
            let str = "";
            for(let i = 0 ; i < data.data.rows.length; i++){
                str += `<option value="${data.data.rows[i].id}">${data.data.rows[i].name}</option> `
            }
            $("#typenum").append(str)
        },
        error: function (data) {
        }
    });
}
//模糊查询
var TempArr=[];//存储option  
  
$(function(){  
    /*先将数据存入数组*/  
    $("#typenum option").each(function(index, el) {  
        TempArr[index] = $(this).text();  
    });  
    $(document).bind('click', function(e) {    
        var e = e || window.event; //浏览器兼容性     
        var elem = e.target || e.srcElement;    
        while (elem) { //循环判断至跟节点，防止点击的是div子元素     
            if (elem.id && (elem.id == 'typenum' || elem.id == "makeupCo")) {    
                return;    
            }    
            elem = elem.parentNode;    
        }    
        $('#typenum').css('display', 'none'); //点击的不是div或其子元素     
    });    
})  
  
function changeF(this_) {  
    $(this_).prev("input").val($(this_).find("option:selected").text());  
    $("#typenum").css({"display":"none"});  
}  
function setfocus(this_){  
    $("#typenum").css({"display":""});  
    var select = $("#typenum");  
    for(i=0;i<TempArr.length;i++){  
        var option = $("<option></option>").text(TempArr[i]);  
        select.append(option);  
    }   
}  
  
function setinput(this_){  
    var select = $("#typenum");  
    select.html("");  
    for(i=0;i<TempArr.length;i++){  
        //若找到以txt的内容开头的，添option  
        if(TempArr[i].substring(0,this_.value.length).indexOf(this_.value)==0){  
            var option = $("<option></option>").text(TempArr[i]);  
            select.append(option);  
        }  
    }  
}  


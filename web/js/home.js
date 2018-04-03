var isLogin = false;
var loginName = ""

function getCookie(c_name)
{
    if (document.cookie.length>0)
    {
        c_start=document.cookie.indexOf(c_name + "=")
        if (c_start!=-1)
        {
            c_start=c_start + c_name.length+1
            c_end=document.cookie.indexOf(";",c_start)
            if (c_end==-1) c_end=document.cookie.length
            return unescape(document.cookie.substring(c_start,c_end))
        }
    }
    return ""
}

function checkLogin() {
    loginName=getCookie("LoginName");
    if(getCookie("isLogin")=="true")
        isLogin=true;
}

function addRentingCar(id,carName,brand,day_price,displacement,type,ownner,date) {
    var dis=brand+"-"+displacement+"L-"+type;
    var content="<div class=\"list_item\">\n" +
        "                            <div class=\"list_item_time\">"+date+"</div>\n" +
        "<div id='this_id' style='display: none'>"+id+"</div>\n"+
        "<div id='this_name' style='display: none'>"+carName+"</div>\n"+
        "                            <div class=\"list_item_main\">\n" +
        "                                <div class=\"car_photo\"><img></div>\n" +
        "                                <div class=\"car_discription\">"+dis+"</div>\n" +
        "                                <div class=\"car_ownner\">"+ownner+"</div>\n" +
        "                                <div class=\"car_enddate\">"+date+"</div>\n" +
        "                                <div class=\"car_money\">"+day_price+"元/天"+"</div>\n" +
        "                                <div class=\"car_order\">\n" +
        "                                    <div class=\"button_grey\" onclick='returncar(this)'>还车</div>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>";
    $("#isgoing").append(content);

}

function waitRented(id,carName,brand,day_price,displacement,type,ownner,date) {
    var dis=brand+"-"+displacement+"L-"+type;
    var content="<div class=\"list_item\">\n" +
        "                                <div class=\"list_item_time\">2017-12-15</div>\n" +
        "<div id='this_id' style='display: none'>"+id+"</div>"+
        "                                <div class=\"list_item_main\">\n" +
        "                                    <div class=\"car_photo\"><img></div>\n" +
        "                                    <div class=\"car_discription\">"+dis+"</div>\n" +
        "                                    <div class=\"car_ownner\">"+ownner+"</div>\n" +
        "                                    <div class=\"car_enddate car_days\">N/A</div>\n" +
        "                                    <div class=\"car_money\">N/A</div>\n" +
        "                                    <div class=\"car_order car_cancel\">\n" +
        "                                        <div class=\"button_grey\" onclick='deletecar(this)'>取消订单</div>\n" +
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>";

    $("#waitrented").append(content);
}

function addRentedCar(id,carName,start_date,end_date,cost){
    var content=" <div class=\"list_item\">\n" +
        "                            <div class=\"list_item_time\">"+end_date+"</div>\n" +
        "                            <div class=\"list_item_main\">\n" +
        "                                <div class=\"car_photo\"><img></div>\n" +
        "                                <div class=\"car_discription\">"+carName+"</div>\n" +
        "                                <div class=\"car_ownner\">"+start_date+"</div>\n" +
        "                                <div class=\"car_enddate\">"+end_date+"</div>\n" +
        "                                <div class=\"car_money\">"+cost+"</div>\n" +
        "                                <div class=\"car_order\">\n" +
        "                                    <div class=\"button_grey\">再来一单</div>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>";
    $("#complete").append(content);
}

function isRenting(id,carName,brand,day_price,displacement,type,ownner,date,renter) {
    var dis=brand+"-"+displacement+"L-"+type;
    var content="<div class=\"list_item\">\n" +
        "                            <div class=\"list_item_time\">2017-12-15</div>\n" +
        "                            <div class=\"list_item_main\">\n" +
        "                                <div class=\"car_photo\"><img></div>\n" +
        "                                <div class=\"car_discription\">"+dis+"</div>\n" +
        "                                <div class=\"car_ownner\">"+renter+"</div>\n" +
        "                                <div class=\"car_enddate car_days\">7天</div>\n" +
        "                                <div class=\"car_money\">"+day_price+"/天"+"</div>\n" +
        "                                <div class=\"car_order car_cancel\">\n" +
        "                                    <div class=\"button_grey\">查看订单</div>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>";

    $("#isrenting").append(content);
}

$(document).ready(function () {
    $("#head").load("/head.html", function () {
        checkLogin();
        if (isLogin) {
            $("#userspan").html(loginName + "<i id='angle_down_2' class='fa fa-angle-down fa-fw rotate_logo'></i>");
            $("#loginname").html(loginName);
            $(".top_nav_middle").mouseenter(function () {
                $(this).css("background-color", "#ffffff");
                $(".user_info").css("display", "block");
                $("#angle_down_2").css({
                    "-webkit-transform": "rotateZ(180deg)",
                    "-moz-transform": "rotateZ(180deg)",
                    "-o-transform": "rotateZ(180deg)",
                    "-ms-transform": "rotateZ(180deg)",
                    "transform": "rotateZ(180deg)",
                });
            });

            $(".top_nav_middle").mouseleave(function () {
                $(this).css("background-color", "");
                $(".user_info").css("display", "none");
                $("#angle_down_2").css({
                    "-webkit-transform": "",
                    "-moz-transform": "",
                    "-o-transform": "",
                    "-ms-transform": "",
                    "transform": "",
                });
            });
            $(".top_nav_middle").click(function () {
            });

            $("#login_tip").css("display", "none");

            $.ajax({type:"post",url:"/Search",data:{"userName":loginName,"renter":loginName,"StatusCode":"2"},dataType:"json",success:function (data) {
                    if(data.valid!=null&&data.valid=="false"){
                        alert("非法操作！");
                    }else{
                        for(var i in data){
                            var obj=data[i];
                            addRentingCar(obj.id,obj.carName,obj.brand,obj.day_price,obj.displacement,obj.type,obj.ownerName,obj.rentCarDate);
                        }
                    }
                }});

            $.ajax({type:"post",url:"/ReturnRented",data:{"username":loginName},dataType:"json",success:function (data) {
                    if(data.valid!=null&&data.valid=="false"){
                        alert("非法操作");
                    }else{
                        for(var i in data){
                            var obj=data[i];
                            addRentedCar(obj.id,obj.carname,obj.startdate,obj.enddate,obj.cost);
                        }
                    }
                }});

            $.ajax({type:"post",url:"/Search",data:{"userName":loginName,"owner":loginName,"StatusCode":"2"},dataType:"json",success:function (data) {
                    if(data.valid!=null&&data.valid=="false"){
                        alert("非法操作！");
                    }else{
                        for(var i in data){
                            var obj=data[i];
                            isRenting(obj.id,obj.carName,obj.brand,obj.day_price,obj.displacement,obj.type,obj.ownerName,obj.rentCarDate,obj.rentCarUserName);
                        }
                    }
                }});

            $.ajax({type:"post",url:"/Search",data:{"userName":loginName,"owner":loginName,"StatusCode":"1"},dataType:"json",success:function (data) {
                    if(data.valid!=null&&data.valid=="false"){
                        alert("非法操作！");
                    }else{
                        for(var i in data){
                            var obj=data[i];
                            waitRented(obj.id,obj.carName,obj.brand,obj.day_price,obj.displacement,obj.type,obj.ownerName,obj.rentCarDate);
                        }
                    }
                }});
        }

        $(".top_nav_left").mouseenter(function () {
            $(this).css("background-color", "white");
            $(".member_info").css("display", "block");
            $("#angle_down_1").css({
                "-webkit-transform": "rotateZ(180deg)",
                "-moz-transform": "rotateZ(180deg)",
                "-o-transform": "rotateZ(180deg)",
                "-ms-transform": "rotateZ(180deg)",
                "transform": "rotateZ(180deg)",
            });
        });
        $(".top_nav_left").mouseleave(function () {
            $(this).css("background-color", "");
            $(".member_info").css("display", "none");

            $("#angle_down_1").css({
                "-webkit-transform": "",
                "-moz-transform": "",
                "-o-transform": "",
                "-ms-transform": "",
                "transform": "",
            });
        });

        $(".head_list_item").mouseenter(function () {
            $(this).css("border-bottom", "3px solid #22ac38");
        });
        $(".head_list_item").mouseleave(function () {
            $(this).css("border-bottom", "");
        });

        var year = 1927;
        for (var i = 1; i <= 91; i++) {
            var str = "<option>" + year + "</option>"
            $("#year").append(str);
            year++;
        }

        var month = 2;
        for (var i = 0; i < 11; i++) {
            var str = "<option>" + month + "</option>"
            $("#month").append(str);
            month++;
        }
        var day = 2;
        for (var i = 0; i < 30; i++) {
            var str = "<option>" + day + "</option>"
            $("#day").append(str);
            day++;
        }
        var height = $(window).height();
        $("#info_main").css("margin-top", (height - 400) / 2);
        $(window).resize(function () {
            var height = $(window).height();
            $("#info_main").css("margin-top", (height - 400) / 2);
        });
        $("#info_main_close").click(function () {
            $("#info_main_bg").css("display", "none");
        });

    });
});

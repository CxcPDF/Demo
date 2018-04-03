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
    if(getCookie("isLogin")=="true"&&loginName=="admin")
        isLogin=true;
}

function addcheckcar(id,brand,displacement,type,ownner,startdate) {
    var dis=brand+"-"+displacement+"L-"+type;
    var content="<div class=\"list_item\">\n" +
        "                                <div class=\"list_item_time\">"+startdate+"</div>\n" +
        "<div id='this_id' style='display: none'>"+id+"</div>"+
        "                                <div class=\"list_item_main\">\n" +
        "                                    <div class=\"car_photo\"><img></div>\n" +
        "                                    <div class=\"car_discription\">"+dis+"</div>\n" +
        "                                    <div class=\"car_ownner\" style='font-size: 12px'>"+ownner+"</div>\n" +
        "                                    <div class=\"car_enddate car_days\">"+startdate+"</div>\n" +
        "                                    <div class=\"car_money\" style='width: 100px;padding-right: 40px;'><input type='number' class='each_price' style='width: 30px;display: inline-block'><span>&nbsp;元</span></div>\n" +
        "                                    <div class=\"car_order car_cancel\">\n" +
        "                                        <div class=\"button_grey\" onclick='confirm_new(this)' style='height:27px!important;top:10px!important;'>批准</div>" +
        "                                        <div class=\"button_grey\" onclick='delete_new(this)' style='height:27px!important;top:50px;!important;'>取消</div>" +
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>";
    $("#check_new").append(content);
}

function addgoingcar(id,brand,displacement,type,renter,date,month_price) {
    var dis=brand+"-"+displacement+"L-"+type;
    var content="<div class=\"list_item\">\n" +
        "                                <div class=\"list_item_time\">"+date+"</div>\n" +
        "<div id='this_id2' style='display: none'>"+id+"</div>"+
        "                                <div class=\"list_item_main\">\n" +
        "                                    <div class=\"car_photo\"><img></div>\n" +
        "                                    <div class=\"car_discription\">"+dis+"</div>\n" +
        "                                    <div class=\"car_enddate car_days\">"+date+"</div>\n" +
        "                                    <div class=\"car_ownner\" style='font-size: 12px'>"+renter+"</div>\n" +
        "                                    <div class=\"car_money\" style='width: 100px;padding-right: 40px;'>"+month_price+"<span></span>元/天</span></div>\n" +
        "                                    <div class=\"car_order car_cancel\">\n" +
        "                                       <span>无</span>"+
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>";
    $("#isgoing").append(content);
}

function  addreturncar(id,brand,displacement,type,renter,date,month_price) {
    var dis=brand+"-"+displacement+"L-"+type;
    var content="<div class=\"list_item\">\n" +
        "                                <div class=\"list_item_time\">"+date+"</div>\n" +
        "<div id='this_id3' style='display: none'>"+id+"</div>"+
        "                                <div class=\"list_item_main\">\n" +
        "                                    <div class=\"car_photo\"><img></div>\n" +
        "                                    <div class=\"car_discription\">"+dis+"</div>\n" +
        "                                    <div class=\"car_enddate car_days\">"+date+"</div>\n" +
        "                                    <div class=\"car_ownner\" style='font-size: 12px'>"+renter+"</div>\n" +
        "                                    <div class=\"car_money\" style='width: 100px;padding-right: 40px;'>"+month_price+"<span></span>元/天</span></div>\n" +
        "                                    <div class=\"car_order car_cancel\">\n" +
        "                                       <div class=\"button_grey\" onclick='confirm_return(this)'>确认</div>"+
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>";
    $("#check_com").append(content);
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

            $.ajax({
                type:"post",
                url:"/Search",
                dataType:"json",
                data:{"userName":"admin","StatusCode":"0"},
                success:function (data) {
                    if(data.valid!=null&&data.valid=="false"){
                        alert("认证错误");
                    }else{
                        for(var i in data) {
                            var obj=data[i];
                            addcheckcar(obj.id,obj.brand, obj.displacement, obj.type, obj.ownerName, obj.rentCarDate);
                        }
                    }
                }
            });

            $.ajax({
                type:"post",
                url:"/Search",
                dataType:"json",
                data:{"userName":"admin","StatusCode":"2"},
                success:function (data) {
                    if(data.valid!=null&&data.valid=="false"){
                        alert("认证错误");
                    }else{
                        for(var i in data) {
                            var obj=data[i];
                            addgoingcar(obj.id,obj.brand, obj.displacement, obj.type, obj.rentCarUserName, obj.rentCarDate, obj.month_price);
                        }
                    }
                }
            })

            $.ajax({
                type:"post",
                url:"/Search",
                dataType:"json",
                data:{"userName":"admin","StatusCode":"3"},
                success:function (data) {
                    if(data.valid!=null&&data.valid=="false"){
                        alert("认证错误");
                    }else{
                        for(var i in data) {
                            var obj=data[i];
                            addreturncar(obj.id,obj.brand, obj.displacement, obj.type, obj.rentCarUserName, obj.rentCarDate, obj.month_price);
                        }
                    }
                }
            })
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


    });
});
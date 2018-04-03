var isLogin = false;
var loginName = "";
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

function additem(parkname,location,price,sum,use,zone,id) {
    var content=" <div class=\"park_item\">\n" +
        "<div class='this_id' style='display: none'>"+id+"</div>"+
        "                <div class=\"park_item_img item\" ><img height=\"200px\" width=\"200px\"></div>\n" +
        "                <div class=\"park_item_dis item\"><span>"+parkname+"</span></div>\n" +
        "                <div class=\"park_item_addr item\"><span><i class=\"fa fa-location-arrow fa-fw\"></i>"+location+"</span></div>\n" +
        "                <div class=\"park_item_pri item\"><span><i class=\"fa fa-rmb fa-fw\"></i>"+price+"元/小时</span></div>\n" +
        "                <div class=\"park_item_status item\"><span><i class=\"fa fa-car fa-fw\"></i>"+"<div  class='use_num' style='display: inline-block'>"+use+"</div>"+"/"+sum+"</span></div>\n" +
        "                <div class=\"park_item_act item\">\n" +
        "                    <button type='button' onclick='gosub(this)'>预定</button>\n" +
        "                </div>\n" +
        "            </div>";

    $("#container").append(content);
}

$(document).ready(function () {
    $("#head").load("/head.html", function () {
        checkLogin();
        if (isLogin) {
            $("#userspan").html("<a href='/home.html 'style='text-decoration: none;color: white;'>"+loginName + "<i id='angle_down_2' class='fa fa-angle-down fa-fw rotate_logo'></i>"+"</a>");
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

        $.ajax({
            type:"post",
            url:"/ListPark",
            dataType:"json",
            success:function (data) {
                var count=0;
                for(var i in data){
                    var obj=data[i];
                    additem(obj.parkname,obj.park_location,obj.hour_price,obj.sum,obj.use,obj.zone,obj.parkId);
                    count++;
                }
                $("#park_num").html(count);
            }
        })
    });
});

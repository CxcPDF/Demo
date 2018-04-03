var isLogin = true;
var loginName = "one_how@163.com";
var myDate=new Date();
var nowdate=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();

$(document).ready(function () {
    $("#head").load("/Demo/head.html", function () {
        if (isLogin) {
            $("#userspan").html("<a href='/home.html 'style='text-decoration: none;color: white;'>"+loginName + "<i id='angle_down_2' class='fa fa-angle-down fa-fw rotate_logo'></i>"+"</a>");

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

        $(".date_tip").each(function () {
            $(this).attr("placeholder", nowdate);
        });

        $("#rent_car").click(function () {
            $(this).css({
                "background-color": "#ffffff",
                "border-left": "solid 0px #ffffff",
                "border-bottom": "solid 0px #ffffff"
            });
            $("#share_car").css({"background-color": "#f0f1f3"});
            $("#park_car").css({"background-color": "#f0f1f3"});
            $("#share_car_page").css("display", "none");
            $("#rent_car_page").css("display", "block");
            $("#park_car_page").css("display", "none");
        });

        $("#share_car").click(function () {
            $(this).css({"background-color": "#ffffff", "border-bottom": "solid 0px #ffffff"});
            $("#rent_car").css({"background-color": "#f0f1f3"});
            $("#park_car").css({"background-color": "#f0f1f3"});
            $("#share_car_page").css("display", "block");
            $("#rent_car_page").css("display", "none");
            $("#park_car_page").css("display", "none");
        });

        $("#park_car").click(function () {
            $(this).css({"background-color": "#ffffff", "border-bottom": "solid 0px #ffffff"});
            $("#share_car").css({"background-color": "#f0f1f3"});
            $("#rent_car").css({"background-color": "#f0f1f3"});
            $("#share_car_page").css("display", "none");
            $("#rent_car_page").css("display", "none");
            $("#park_car_page").css("display", "block");
        });

        $("#rent_car_button").click(function () {
            if (!isLogin) {
                window.open("/login.html", "_blank");
            }

        });

        $("#share_car_button").click(function () {

        });

        $("#park_car_button").click(function () {

        });

    });
});

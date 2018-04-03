var isLogin=false;
var loginName = "";

function error_info(item, info) {
    item.css("box-shadow", " 0 0 3px rgba(90,151,221,0.5)");
    item.css("border-color", "#F40256");
    $(".login_error_display").html(info);
}

function error_remove(item) {
    item.css("border-color", "");
    item.css("box-shadow", "");
    $(".login_error_display").html("");
}

function usernameTest(username) {
    if (username.length != 0) {
        if(username=="admin")
            return true;
        var pattern = /\d{11}/;
        if (pattern.test(username) == false) {
            var pattern2 = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            if (!pattern2.exec(username)) {
                return false;
            } else {
                return true;
            }
        } else {
            if (username.length != 11) {
                return false;
            } else {
                return true;
            }
        }
    }
}

function passwordTest(password) {
    if (password.length != 0) {
        if (password.length < 6) {
            return false;
        } else {
            return true;
        }
    }
}

//如果登陆的话修改上方面板登陆样式
function loginAction()  {
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


$(document).ready(function () {
    $("#head").load("/head.html", function () {
        if(isLogin)
            loginAction();

        $(".top_nav_left").mouseenter(function () {
            $(this).css("background-color", "white");
            $(".member_info").css("display", "block");
            $(".rotate_logo").css({
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
            $(".rotate_logo").css({
                "-webkit-transform": "",
                "-moz-transform": "",
                "-o-transform": "",
                "-ms-transform": "",
                "transform": "",
            });
        });
        $(".head_list_item").mouseenter(function () {
            $(this).css("border-bottom", "3px solid #22ac38");
        })
        $(".head_list_item").mouseleave(function () {
            $(this).css("border-bottom", "");
        })

        $(".login_form_input").focus(function () {
            $(this).css("border-color", "#5A97DD");
            $(this).css("box-shadow", " 0 0 3px rgba(90,151,221,0.5)");
        });
        $(".login_form_input").blur(function () {
            $(this).css("border-color", "");
            $(this).css("box-shadow", "");
        });

        $("#username").blur(function () {
                var username = $("#username").val();
                if (usernameTest(username)) {
                    error_remove($(this));
                } else {
                    error_info($(this), "输入的格式有误,请重新输入");
                }});

        $(".password").blur(function () {
            password = $("#password").val();
            if (passwordTest(password)) {
                error_remove($(this));
            } else {
                error_info($(this), "输入的格式有误,请重新输入");
            }
        });

        $("#login_button").click(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            var nameok = usernameTest(username);
            var passok = passwordTest(password);

            var isSuccess = nameok && passok;
            if (isSuccess) {
                $.ajax({
                    type: "post",
                    url: "/UserLogin",
                    dataType: "json",
                    data: {"username": username, "password": password},
                    success: function (data) {
                        var jsonRes = data;
                        if (jsonRes.result == "true") {
                            isLogin=true;
                            loginName=username;
                            loginAction();
                            document.cookie="LoginName="+username;
                            document.cookie="isLogin="+"true";
                            $(location).href("/index.html");

                        } else if (jsonRes.result == "false") {
                            $(".login_error_display").html("你输入的账户名或密码有错误,请重新输入！");
                        }
                    }
                });
            }
        });
    });
});

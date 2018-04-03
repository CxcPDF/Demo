function error_info(item, info) {
    item.css("box-shadow", " 0 0 3px rgba(90,151,221,0.5)");
    item.css("border-color", "#F40256");
    $(".register_error_display").html(info);
}

function error_remove(item) {
    item.css("border-color", "");
    item.css("box-shadow", "");
    $(".register_error_display").html("");
}

function send_message() {
    $(this).css("background-color", "#ffffff");
    $(this).unbind("click");
    var count = 5;
    var s = setInterval(function () {
        $("#send_message_tip").html("&nbsp&nbsp&nbsp&nbsp" + count + "s");
        count--;
        if (count == 0) {
            $("#register_send_message").on("click", send_message);
            $("#register_send_message").css("background-color", "#f9f9f9");
            $("#send_message_tip").text("点击发送");
            clearInterval(s);
        }
    }, 1000);
}

function  phone_test(phone_num) {
    if (phone_num.length!= 0) {
        var pattern = /\d{11}/;
        if (pattern.test(phone_num) == false) {
            return false;
        } else {
            return true;
        }
    }
}

function  mail_test(mail_address) {
    if (mail_address.length!= 0) {
        var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
        if (!pattern.test(mail_address)) {
            return false;
        } else {
            return true;
        }
    }
}

function passwordTest(password) {
    if (password.length!= 0) {
        if (password.length < 6) {
            return false;
        } else {
            return true;
        }
    }
}

$(document).ready(function () {

    $("#head").load("/head.html", function () {
        //一些头部的动画效果等
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

        $("#register_mail").click(function () {
            $(".register_highlight").css("left", "50%");
            $(".register_mail_content").css("display", "block");
            $(".register_phone_content").css("display", "none");
            $(".register_error_display").html("");
        });
        $("#register_phone").click(function () {
            $(".register_highlight").css("left", "0");
            $(".register_phone_content").css("display", "block");
            $(".register_mail_content").css("display", "none");
            $(".register_error_display").html("");
        });
        $(".register_form_input").focus(function () {
            $(this).css("border-color", "#5A97DD");
            $(this).css("box-shadow", " 0 0 3px rgba(90,151,221,0.5)");
        });
        $(".register_form_input").blur(function () {
            $(this).css("border-color", "");
            $(this).css("box-shadow", "");
        });
        $("#register_send_message").on("click", send_message);

        //在用户填写别的项时检查是否格式合法并提示

        var picverify = true, messageverify = true; //期中验证码和短信验证码暂时不写

        $("#phone_number").blur(function () {
                var phone_num = $("#phone_number").val();
                if(!phone_test(phone_num)){
                    error_info($("#phone_number"), "电话号码格式有误，请重新输入");
                }else{
                    error_remove($("#phone_number"));
                }
            }
        );

        $("#mail_address").blur(function () {
            var mail_adress = $("#mail_address").val();
            if(!mail_test(mail_adress)){
                error_info($("#mail_address"), "邮箱地址有误！");
            }else{
                error_remove($("#mail_address"));
            }
        });

        $(".password").blur(function () {
            var password = $(this).val();
            if (password.length != 0) {
                if (password.length < 6) {
                    error_info($(this), "密码长度太短！");
                } else {
                    error_remove($(".password"));
                }
            }
        });

        //动态调整弹出框高度
        var height = $(window).height();
        $(".tip_main").css("margin-top", (height - 400) / 2);
        $(window).resize(function () {
            height = $(window).height();
            $(".tip_main").css("margin-top", (height - 400) / 2);
        });

        //电话号码注册，先检查格式正确性，再AJAX
        $("#phone_register").click(function () {
            var username = $("#phone_number").val();
            var password = $("#phone_password").val();
            var success = messageverify && picverify;
            if(phone_test(username)&&passwordTest(password)){
                error_remove($("#phone_number"));
                if (success) {
                $.ajax({
                    type: "post",
                    url: "/UserAdd",
                    dataType: "json",
                    data: {"username": username, "password": password,"sex":"男","birthday":"2000-00-00","money":"0"},
                    success: function (data) {
                        var result = data.result;
                        if (result == "true") {
                            $("#get_phone_num").text("您注册的手机是" + $("#phone_number").val());
                            $(".register_box_wrapper").css("display", "none");
                            $(".register_phone_success").css("display", "block");
                            $(".register_mail_success").css("display", "none");
                        } else if (result == "repeat") {
                            error_info($("#phone_number"), "用户名重复,请换一个");
                        } else {
                            error_info($("#phone_number"), "服务器错误");
                        }
                    }
                });
            }}else if(!phone_test(username)){
                error_info($("#phone_number"), "电话号码格式有误，请重新输入");
            }else if(!passwordTest(password)){
                error_info($("#phone_number"), "密码格式有误，请重新输入");
            }
        });

        //邮箱注册按钮，先检查格式是否合法，再AJAX
        $("#mail_register").click(function () {
            var username = $("#mail_address").val();
            var password = $("#mail_password").val();
            var success = picverify;
            if(mail_test(username)&&passwordTest(password)) {
                error_remove($("#mail_address"));
                if (success) {
                    $.ajax({
                        type: "post",
                        url: "/UserAdd",
                        dataType: "json",
                        data: {
                            "username": username,
                            "password": password,
                            "sex": "男",
                            "birthday": "2000-00-00",
                            "money": "0"
                        },
                        success: function (data) {
                            var result = data.result;
                            if (result == "true") {
                                $("#get_mail_address").text("您的邮箱是 " + $("#mail_address").val());
                                $(".register_box_wrapper").hide();
                                $(".register_phone_success").hide();
                                $(".register_mail_success").css("display", "block");
                            } else if (result == "repeat") {
                                error_info($("#phone_number"), "用户名重复,请换一个");
                            } else {
                                error_info($("#phone_number"), "服务器错误");
                            }
                        }
                    });
                }
            }else if(!mail_test(username)){
                error_info($("#mail_address"),"您的邮箱格式有误，请检查！");
            }else if(!passwordTest(password)){
                error_info($("#mail_address"),"您的密码格式有误，请检查！");
            }
        });

        //注册后跳转到制定邮箱的按钮功能实现
        $("#go_verify_mail").click(function () {
            var address = $("#mail_address").val().split("@");
            window.open("http://mail." + address[1], "_blank");
        });
    });
});

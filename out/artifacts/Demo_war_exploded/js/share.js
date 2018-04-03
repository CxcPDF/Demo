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

        $("#myFile").fileinput({
            language: 'zh',
            uploadUrl: "/FileUpload",
            autoReplace: true,
            maxFileCount: 3,
            allowedFileExtensions: ["jpg", "png", "gif"],
            browseClass: "btn btn-primary", //按钮样式
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            uploadExtraData:{"id": 2},
        });

        var height = $(window).height();
        $(".tip_main").css("margin-top", (height - 400) / 2);
        $(window).resize(function () {
            height = $(window).height();
            $(".tip_main").css("margin-top", (height - 400) / 2);
        });

        $("#confirm_buttton").click(function () {
            var brand=$("#car_brand").val();
            var displacement=$("#car_displacement").val();
            var mileage=$("#car_displacement").val();
            var age=$("#car_age").val();
            var type=$("#car_type").val();
            var location=$("#location").val();
            if(brand==null||displacement==null||mileage==null||age==null||type==null||location==null){
                alert("请填入完整信息！");
            }else if(!$("input[type='checkbox']").is(':checked')){
                alert("请同意协议");
            }else{
                $.ajax({
                    type: "post",
                    url: "/CarAdd",
                    dataType: "json",
                    data: {
                        "username": loginName,
                        "carName": brand,
                        "day_price": "100",
                        "month_price": "100",
                        "age": age,
                        "brand": brand,
                        "displacement": displacement,
                        "mileage": mileage,
                        "type": type,
                        "getCarLocation": location,
                        "returnCarLocation": location
                    },
                    success: function (data) {
                        if (data.valid != null && data.valid == "false") {
                            alert("请登录");
                        } else if (data.result == "false") {
                            alert("失败");
                        } else {
                            alert("成功添加，等待审核");
                        }
                    }
                });
            }
        });
    });
});

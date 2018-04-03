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

function  addItem(id,name,brand,price,displacement,type,getCarLocation) {
        var dis=name+"-"+displacement+"L-"+type;
        var content=" <div class=\"main_item\" onclick=\"gosub(this)\">\n" +
            "<div id='this_id' style='display: none'>"+id+"</div>\n"+
            "<div id='this_name' style='display: none'>"+name+"</div>\n"+
            "<div id='getCarLocation' style='display: none'>"+getCarLocation+"</div>\n"+
        "                <div class=\"main_item_inner\">\n" +
        "                    <div class=\"item_img\"></div>\n" +
        "                    <div class=\"item_dis\">\n" +
        dis +
        "                    </div>\n" +
        "                    <div class=\"item_act\">\n" +
        "                        <div class=\"item_price\" style=\"display: inline-block ;font-size: 20px;color:#f95523; \"><span class=\"car_price\">"+price+"</span>元<span style=\"font-size: 14px;color: #333333\">/天</span></div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>";

        $(".main_item_wrapper").append(content);
}

var startdateval,enddateval='2000-12-12';
function  update() {
    var date1=new Date(startdateval+" "+"00:00:00");
    var date2=new Date(enddateval+" "+"00:00:00");
    var day=date2.getTime()-date1.getTime();
    day=day/ (24*60*60*1000);
    day++;
    if(day<0){
        day=0; }
    $("#day").html(day+'<span style="font-size: 14px;color: #333333">&nbsp;天</span>');
    $("#day").css('color','#f95523');
    var priceeach=$('.each_price').text();
    priceeach=priceeach*day;
    $("#price").html(priceeach);
    $("#price").css('color','#f95523');
}

$(document).ready(function () {
    $("#head").load("/head.html",function () {
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

        $("body").bind("click", function () {
            $(".item_extend").slideUp();
            $("#arrow").css({
                "-webkit-transform": "",
                "-moz-transform": "",
                "-o-transform": "",
                "-ms-transform": "",
                "transform": "",
            });
        });

        $("#brand_more").bind("click", function (event) {
            $(".item_extend").slideDown();
            event.stopPropagation();
            $("#arrow").css({
                "-webkit-transform": "rotateZ(-180deg)",
                "-moz-transform": "rotateZ(-180deg)",
                "-o-transform": "rotateZ(-180deg)",
                "-ms-transform": "rotateZ(-180deg)",
                "transform": "rotateZ(-180deg)",
            });
        });

        $(".item_type").mouseenter(function () {
            $(this).css({
                'color': '#22ac38',
                'border-top': '1px solid #22ac38',
                'border-left': '1px solid #22ac38',
                'border-right': '1px solid #22ac38',
                'border-bottom': '0px solid #e6e6e6'
            });
            $(".item_type_more").css('display', 'block');
        });

        $(".item_type").mouseleave(function () {
            $(this).css({'color': '', 'border-top': '', 'border-left': '', 'border-right': '', 'border-bottom': ''});
            $(".item_type_more").css('display', 'none');
        });

        $(".item_age").mouseenter(function () {
            $(this).css({
                'color': '#22ac38',
                'border-top': '1px solid #22ac38',
                'border-left': '1px solid #22ac38',
                'border-right': '1px solid #22ac38',
                'border-bottom': '0px solid #e6e6e6'
            });
            $(".item_age_more").css('display', 'block');
        });

        $(".item_age").mouseleave(function () {
            $(this).css({'color': '', 'border-top': '', 'border-left': '', 'border-right': '', 'border-bottom': ''});
            $(".item_age_more").css('display', 'none');

        });
        var height = $(window).height();
        $("#info_main").css("margin-top", (height - 400) / 2);
        $(window).resize(function () {
            height = $(window).height();
            $("#info_main").css("margin-top", (height - 400) / 2);
        });

        $("#start_year").datepicker("option","minDate",new Date());
        $("#start_year").datepicker( {dateFormat:"yy-mm-dd",
            showOtherMonths: true,
            selectOtherMonths: true,
            language: "zh-CN",
            onSelect:function (dateText,inst) {
                startdateval=$("#start_year").val();
                $("#end_year").datepicker("option","minDate",dateText);
                update();
            }
        });

        $("#end_year").datepicker( {dateFormat:"yy-mm-dd",
            showOtherMonths: true,
            selectOtherMonths: true,
            language: "zh-CN",
            onSelect:function (dateText,inst) {
                enddateval=$("#end_year").val();
                $("#start_year").datepicker("option","maxDate",dateText);
                update();
            }
        });


        $.ajax({type:"post",url:"/Search",data:{"userName":loginName,"StatusCode":"1"},dataType:"json",success:function (data) {
                    if(data.valid!=null&&data.valid=="false"){
                        alert("非法操作！");
                    }else{
                        for(var i in data){
                            var obj=data[i];
                            addItem(obj.id,obj.carName,obj.brand,obj.day_price,obj.displacement,obj.type,obj.getCarLocation);
                        }
                    }
            }});
    });
});

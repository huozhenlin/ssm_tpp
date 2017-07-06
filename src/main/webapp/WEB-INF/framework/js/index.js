/**
 * Created by hzl on 2017/4/4.
 */

//进入主页，会进行初始化和cookie检测
$(document).ready(function () {

    init();
    checkCookie();
    var txt = $(".login_name").val();
    //初始化打开页面的时候调用
    changImg(txt);

});

//检查登录验证码是否正确
$('#valid').blur(function () {
    var value = $('#valid').val();
    console.log("获取到的验证码是" + value);
    htmlobj = $.ajax({url: "/api/checkCode?code=" + value, async: false});
    if (htmlobj.responseText == "fail") {
        $("#err").text("验证码错误");
    } else {
        $("#err").text("");
    }
});

//检查注册验证码是否正确
$('#index_code').blur(function () {
    var value = $('#index_code').val();
    console.log("获取到的验证码是" + value);
    htmlobj = $.ajax({url: "/api/checkCode?code=" + value, async: false});
    if (htmlobj.responseText == "fail") {
        alert("验证码错误")
    }
});


//注册用户名输入框失去焦点是触发ajax异步请求，检测用户名是否存在
$('#txtUserName').blur(function () {
    var value = $('#txtUserName').val();
    if (value != null) {
        showUser(value)
    }
});

//填完所有信息点击注册按钮是会对cooke进行注入
$('#subLogin').click(function () {
    var username = $('#txtUserName').val();//获取输入的用户名
    var password = $('#password').val();//获取输入的密码
    setCookie('username', username, 360);
    setCookie('password', password, 360);
});


/**
 *
 * @param cname cookie的名
 * @param cvalue cookie对应的值
 * @param exdays  有效期限
 */
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}


//这是改变验证码的方法
function changeImg() {
    var imgSrc = $("#imgObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
}

//时间戳
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    url = url.substring(0, 17);
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}

//    获取屏幕宽高
height = $(window).height();
width = $(window).width();

//    获取盒子宽高
div_width = $('#div_register').width();
div_height = $('#div_register').height();

$('#close').click(function () {
    $('#div_register').hide();
});

//    注册按钮被点击触发事件
$('#register').click(function () {
    r_height = (height - div_height) / 2 + "px";
    r_width = (width - div_width) / 2 + "px";
    $('#div_register').css({'position': 'absolute'});
    $('#div_register').css({'left': r_width, 'top': r_height});
    $('#div_register').show();
});

//判断两次密码是否一样
$('#com_pass').blur(function () {
    var text = $('#com_pass').val();
    CheckPass(text)
});

//检测两次密码是否一致
function CheckPass(pass) {
    if ($('#password').val() != pass) {
        $('.pass_tip').text('两次密码不一致');
        return false;
    } else {
        $('.pass_tip').text('通过');
        return true;
    }
}

//邮箱输入框失去焦点是，会先对格式进行检测，成功则去数据库检测邮箱是否已经被注册
$('#email').blur(function () {
    var text = $('#email').val();
    CheckMail(text);
    if (CheckMail(text)) {
        searchEmail(text);
    }

});

//验证邮箱
function CheckMail(mail) {
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (filter.test(mail)) return true;
    else {
        $('#email_tip').text('格式不正确');
        return false;
    }
}

//完成注册按钮被点击
$('#subLogin').click(function () {
    var b = 1;
    if ($('#password').val() == '') {
        $('.pass_tip').text('请输入密码');
        b = 0;
    } else if ($('#password').val() != $('#com_pass').val()) {
        $('.pass_tip').text('两次密码不一致');
        b = 0;
    } else {

        $('.pass_tip').text('通过');
    }

    if (b == 1) {
        $('#re_form').submit();
    }
});


//    忘记密码按钮被点击触发事件
$('#forget').click(function () {

    f_height = $('#findPass').height();
    f_width = $('#findPass').width();

    a_height = (height - f_height) / 2 + "px";
    a_width = (width - f_width) / 2 + "px";

    $('#findPass').css({
        // 'background': '#3fb9ff',
        'position': 'absolute',
        'box-shadow': '0 0 0 999px rgba(0,0,0, 0.5)'


    });
    $('#findPass').css({'left': a_width, 'top': a_height});
    $('#findPass').show();
});

//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}

//检查cookie
function checkCookie() {
    var user = getCookie("username");
    var pass = getCookie("password");
    if (user != "") {
        $('.name').val(user)
        $('.pwd').val(pass)
    }
}

//找回密码确认按钮
$('#findSubmit').click(function () {
    var text = $('#input_mail').val();
    alert(text)
    if (text != "")
        findPass(text);
});

//找回密码关闭按钮
$('#exit').click(function () {
    $('#findPass').hide()
});

function init() {

    $(".name,.pwd,#valid").keyup(function (event) {
        if (event.keyCode == 13) {
            $('#login').click();
        }

    });

    //检查用户名或密码
    $("#login").click(function (event) {
        var account = $(".name").val();
        var pwd = $(".pwd").val();
        if (!account || !pwd) {
            $("#err").css("display", "inline-block");
            $("#err").text("请输入用户名或密码");
            return false;
        } else {
            //localhost:8080/checkUserAndPwd?username=huozhenlin&pwd=12344
            var htmlobj = $.ajax({url: "/api/checkUserAndPwd?username=" + account + "&pwd=" + pwd, async: false});
            if (htmlobj.responseText == "fail") {
                $("#err").text("用户名或密码错误");
                return false;
            } else {
                $("#err").text("");
            }

        }

        //检查验证码
        var code = $("#valid").val().toLocaleLowerCase();
        code = $.trim(code);
        if (code.length < 4) {
            $("#err").css("display", "inline-block");
            $("#err").text("验证码位数不够");
            return false;
        }
    });


    $('#changeimg').click(function () {
        $('#verifyCodePic')[0].src = '/code?' + Math.random();
        return false;
    });

}
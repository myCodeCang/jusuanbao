$(function () {
    var topHtml = '<div class="header-top">\n' +
        '\t\t\t<ul>\n' +
        '\t\t\t\t<li class="fl" style="color:#666;">服务热线：400-809-3628</li>\n' +
        '\t\t\t\t<li class="fr"><a href="javascript:void(0);" class="loginStatus" onclick="doSomething()"></a>&nbsp;&nbsp;/&nbsp;&nbsp;<a href="javascript:void(0);" onclick="toRegister()">注册</a></li>\n' +
        '\t\t\t\t<li class="fr"><a href="javascript:void(0);" id="loginName"></a></li>\n' +
        '\t\t\t</ul>\n' +
        '\t\t</div>';
    $("body").prepend(topHtml);
    var loginhtml = '<div class="cal-cover profit-pop disnone" id="login">\n' +
        '\t\t\t<div class="cal-pop clearfix bakfff profit-pop" >\n' +
        '\t\t\t\t<img src="../image/cal-4.jpg" width="400" height="563" class="fl">\n' +
        '\t\t\t\t<div class="cal-pfr fr" style="position: relative;"><div>\n' +
        '\t\t\t\t\t<img src="../image/guanbi.png" onclick="closeLogin();"  style="position: absolute;top:10px;right: 10px;width:20px;z-index: 999;cursor: pointer" ></div>\n' +
        '\t\t\t\t\t<div class="cal-pfrmn relative" style="padding: 100px 40px 100px 40px;">\n' +
        '\t\t\t\t\t\t<i class="cal-close absolute"></i>\n' +
        '\n' +
        '\t\t\t\t\t\t<p class="cal-pp1 mb20"><img src="../image/logo.png" alt=""><span class="f20 col555 ml25">登录</span></p>\n' +
        '\t\t\t\t\t\t<form>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="text" id="loginMob"  placeholder="请输手机号码"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="password" id="loginPass"  placeholder="请输入密码"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-sub clearfix tac mt50">\n' +
        '\t\t\t\t\t\t\t\t<a href="javascript:void(0)" class="fl f18 colfff place"onclick="login()" style="width: 155px;">登录</a>\n' +
        '\t\t\t\t\t\t\t\t<a href="javascript:void(0)" class="f18 fr profit-return" onclick="toRegister()" style="width: 155px;border:1px solid #ff7e00;color:#ff7e00;border-radius:4px;">注册</a>\n' +
        '\n' +
        '\t\t\t\t\t\t\t</div><p onclick="openAlterPass()" style="margin-top:15px;padding-right:5px;font-size:15px;"><span class="fr" style="cursor: pointer;">忘记密码?</span></p>\n' +
        '\t\t\t\t\t\t</form>\n' +
        '\t\t\t\t\t</div>\n' +
        '\t\t\t\t</div>\n' +
        '\t\t\t</div>\n' +
        '\t\t</div>';
    $("body").append(loginhtml);
    var registerHtml = '<div class="cal-cover profit-pop disnone" style="position:absoulte;z-index:5555;" id="register">\n' +
        '\t\t\t<div class="cal-pop clearfix bakfff profit-pop ">\n' +
        '\t\t\t\t<img src="../image/cal-4.jpg" width="400" height="563" class="fl">\n' +
        '\t\t\t\t<div class="cal-pfr fr" style="position: relative;cursor: pointer;"><div onclick="closeRegister()">\n' +
        '\t\t\t\t\t<img src="../image/guanbi.png" style="position: absolute;top:10px;right: 10px;width:20px;z-index: 999;"></div>\n' +
        '\t\t\t\t\t<div class="cal-pfrmn relative" style="padding: 35px 40px 20px 40px;">\n' +
        '\t\t\t\t\t\t<i class="cal-close absolute"></i>\n' +
        '\n' +
        '\t\t\t\t\t\t<p class="cal-pp1 mb20"><img src="../image/logo.png" height="60" alt=""><span class="f20 col555 ml25">注册信息</span></p>\n' +
        '\t\t\t\t\t\t<form>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="text" id="trueName" name="name" placeholder="请输入真实姓名"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="text" id="mobile" name="phone" placeholder="请输手机号码"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox relative"><input type="text" id="code" name="smsCode" placeholder="请输入手机验证码">\n' +
        '\t\t\t\t\t\t\t\t<a href="#" class="absolute cal-send alink"  style="width: 115px;text-align: right;"  id="getVerifyCode">获取验证码</a>\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="password" id="pwdOne"  placeholder="请输入密码"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="password" id="pwdTwo"  placeholder="请再输入密码"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-sub clearfix tac mt50">\n' +
        '\t\t\t\t\t\t\t\t<!--<a href="javascript:void(0)" class="f18 colbf fl ml70 profit-return">返回</a>-->\n' +
        '\t\t\t\t\t\t\t\t<a href="javascript:void(0)" class="fr f18 colfff place" onclick="register()" style="width:100%;">注册</a>\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t</form>\n' +
        '\t\t\t\t\t</div>\n' +
        '\t\t\t\t</div>\n' +
        '\t\t\t</div>\n' +
        '\t\t</div>';
    $("body").append(registerHtml);

    var forgetPass = '<div class="cal-cover profit-pop disnone" style="position:absoulte;z-index:5555;" id="alterPass">\n' +
        '\t\t\t<div class="cal-pop clearfix bakfff profit-pop ">\n' +
        '\t\t\t\t<img src="../image/cal-4.jpg" width="400" height="563" class="fl">\n' +
        '\t\t\t\t<div class="cal-pfr fr" style="position: relative;cursor: pointer;"><div onclick="closeAlterPass()">\n' +
        '\t\t\t\t\t<img src="../image/guanbi.png" style="position: absolute;top:10px;right: 10px;width:20px;z-index: 999;"></div>\n' +
        '\t\t\t\t\t<div class="cal-pfrmn relative" style="padding: 35px 40px 20px 40px;">\n' +
        '\t\t\t\t\t\t<i class="cal-close absolute"></i>\n' +
        '\n' +
        '\t\t\t\t\t\t<p class="cal-pp1 mb20"><img src="../image/logo.png" height="60" alt=""><span class="f20 col555 ml25">密码找回</span></p>\n' +
        '\t\t\t\t\t\t<form>\n' +
        '\t\t\t\t\t\t\t\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="text" id="alterMobile" name="phone" placeholder="请输手机号码"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox relative"><input type="text" id="alterCode" name="smsCode" placeholder="请输入手机验证码">\n' +
        '\t\t\t\t\t\t\t\t<a href="#" class="absolute cal-send alink"  style="width: 115px;text-align: right;" id="alterGetVerifyCode">获取验证码</a>\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="password" id="alterPassOne"  placeholder="请输入密码"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-inbox"><input type="password" id="alterPassTwo"  placeholder="请再输入密码"></div>\n' +
        '\t\t\t\t\t\t\t<div class="cal-sub clearfix tac mt50">\n' +
        '\t\t\t\t\t\t\t\t<!--<a href="javascript:void(0)" class="f18 colbf fl ml70 profit-return">返回</a>-->\n' +
        '\t\t\t\t\t\t\t\t<a href="javascript:void(0)" class="fr f18 colfff place" onclick="toAlterPass()" style="width:100%;">确认修改</a>\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t</form>\n' +
        '\t\t\t\t\t</div>\n' +
        '\t\t\t\t</div>\n' +
        '\t\t\t</div>\n' +
        '\t\t</div>';
    $("body").append(forgetPass);
});

function closeLogin() {
    $("#login").addClass("disnone");
}

function closeRegister() {
    $("#register").addClass("disnone");
}

function closeAlterPass() {
    $("#alterPass").addClass("disnone");
}

function register() {

    var trueName = $("#trueName").val();
    var parentName = "pt10001";
    var mobile = $("#mobile").val();
    var code = $("#code").val();
    var pwdOne = $("#pwdOne").val();
    var pwdTwo = $("#pwdTwo").val();
    if (!trueName) {
        layui.qyWin.toast("请输入真实姓名!");
        return;
    }
    if (!mobile) {
        layui.qyWin.toast("请输入手机号!");
        return;
    }
    if (!(/^1[3456789]\d{9}$/.test(mobile))) {
        layui.qyWin.toast("手机号码有误，请重填");
        return;
    }
//				if(!code){
//                    layui.qyWin.toast("请输入验证码!");
//                    return ;
//                }
    if (!pwdOne) {
        layui.qyWin.toast("请输入密码!");
        return;
    }
    if (pwdOne.length < 8) {
        layui.qyWin.toast("密码长度必读大于八位!");
        return;
    }
    if (pwdOne != pwdTwo) {
        layui.qyWin.toast("请两次密码保持一致!");
        return;
    }
    var data = {
        trueName: trueName,
        parentName: parentName,
        mobile: mobile,
        validCode: code,
        walterName: "pt10001",
        password: pwdTwo
    }
    layui.qyForm.qyajax("/f/register", data, function (ret) {
        layui.qyWin.toast(ret.info);
        setTimeout(function () {
            $("#register").addClass("disnone");
            $("#login").removeClass("disnone");
        }, 1500);

    });
}

//登录
function login() {

    var username = $("#loginMob").val();
    var password = $("#loginPass").val();

    if (!username) {
        layui.qyWin.toast("请输入手机号!");
        return;
    }
    if (!password) {
        layui.qyWin.toast("请输入密码!");
        return;
    }
    var data = {
        username: username,
        password: password,
        mobileLogin: "true",
        userType: "website",
        login: "true"

    }
    layui.qyForm.qyajax("/f/login", data, function () {
        layui.qyWin.toast("登录成功!");
        setTimeout(function () {
            window.location.reload();
        }, 1500)
        setTimeout(function () {
            $("#login").addClass("disnone");
        }, 1500);
    });

}

var nums = 60;

function doLoop() {
    nums--;
    if (nums > 0) {
        $("#getVerifyCode").attr("disabled", true);
        $("#getVerifyCode").text(nums + '秒后可重新获取');
    } else {
        clearInterval(clock);
        //清除js定时器
        $("#getVerifyCode").removeAttr("disabled");
        $("#getVerifyCode").text('获取验证码');
        nums = 60;
        //重置时间

        $('#getVerifyCode').on('click', function () {
            getVerify();
        });
    }
}

function alterDoLoop() {
    nums--;
    if (nums > 0) {
        $("#alterGetVerifyCode").attr("disabled", true);
        $("#alterGetVerifyCode").text(nums + '秒后可重新获取');
    } else {
        clearInterval(clock);
        //清除js定时器
        $("#alterGetVerifyCode").removeAttr("disabled");
        $("#alterGetVerifyCode").text('获取验证码');
        nums = 60;
        //重置时间

        $('#alterGetVerifyCode').on('click', function () {
            alterGetVerify();
        });
    }
}


function getVerify() {

    if ($("#mobile").val() == '' || $("#mobile").val() == 'undefined') {
        layui.qyWin.toast("请填写手机号码!");
        return;
    }

    var param = {
        mobile: $("#mobile").val(),
        isMobile: 1
    };

    $('#getVerifyCode').off('click');

    layui.qyForm.qyajax("/msm/lkMsm/sendVerifyCode", param, function (data) {
        $("#getVerifyCode").attr("disabled", true);
        $("#getVerifyCode").text(nums + '秒后可重新获取');

        //一秒执行一次
        clock = setInterval(doLoop, 1000);

    });

}

function alterGetVerify() {

    if ($("#alterMobile").val() == '' || $("#alterMobile").val() == 'undefined') {
        layui.qyWin.toast("请填写手机号码!");
        return;
    }

    var param = {
        mobile: $("#alterMobile").val(),
        isMobile: 1
    };

    $('#alterGetVerifyCode').off('click');

    layui.qyForm.qyajax("/msm/lkMsm/sendVerifyCode", param, function (data) {
        $("#alterGetVerifyCode").attr("disabled", true);
        $("#alterGetVerifyCode").text(nums + '秒后可重新获取');

        //一秒执行一次
        clock = setInterval(alterDoLoop, 1000);

    });

}

function toAlterPass() {
    var newPwd = $("#alterPassOne").val();
    var newPwds = $("#alterPassTwo").val();
    var mobile = $("#alterMobile").val();
    var code = $("#alterCode").val();
    if (!newPwd) {
        layui.qyWin.toast("请输新密码!");
        return;
    }
    if (newPwd != newPwds) {
        layui.qyWin.toast("请两次新密码保持一致!");
        return;
    }
    var data = {
        newPwd: newPwd,
        newPwds: newPwds,
        mobile: mobile,
        code: code

    }
    layui.qyForm.qyajax("/f/forgetPassword", data, function (res) {
        layui.qyWin.toast(res.info);
        setTimeout(function () {
            $("#alterPass").addClass("disnone");
        }, 1000);
    });
}

function openAlterPass() {
    $("#alterPass").removeClass("disnone");
    $("#login").addClass("disnone");
}


function toRegister() {
    $("#register").removeClass("disnone");
    $("#login").addClass("disnone");
}

function huiche() {
    $("#login").keyup(function (event) {
        if (event.keyCode == 13) {
            login();
        }
    });
}

//登陆就注销，如果注销则登陆
function doSomething() {
    layui.qyForm.qyajax("/f/isLogin", {}, function (data) {
        if (data.isLogin) {
            layui.layer.open({
                content: '您确定要注销吗？'
                , btn: ['确认', '取消']
                , yes: function (index) {
                    layui.qyForm.qyajax("/f/logOut", {}, function (data) {
                        layui.qyWin.toast(data.info);
                        setTimeout(function () {
                            window.location.reload();
                        }, 1500)
                    });
                    layui.layer.close(index);
                }
            });

        } else {
            $("#login").removeClass("disnone");
            huiche();
        }
    });
}

function loginGo(url) {
    layui.qyForm.qyajax("/f/isLogin", {}, function (data) {
        if (data.isLogin) {
            layui.qyWin.win("", url + ".html", "");
        } else {
            $("#login").removeClass("disnone");
            huiche();
        }
    });
}

function verfLogin() {
    $('#getVerifyCode').on('click', function () {
        getVerify();
    });
    $('#getBuyVerifyCode').on('click', function () {
        getBuyVerify();
    });
    $('#alterGetVerifyCode').on('click', function () {
        alterGetVerify();
    });
    layui.qyForm.qyajax("/f/isLogin", {}, function (data) {
        if (data.isLogin) {
            $("#loginName").html(data.loginName);
            $(".loginStatus").html("注销");
        } else {
            $(".loginStatus").html("登陆");
            $("#loginName").html("游客");
        }
    });
}







/**
 * Created by hzl on 2016/11/29.
 */

var xmlHttp;

/**
 * 发送请求
 * @param str
 */

$
function changImg(str) {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null) {
        alert("Browser does not support HTTP Request");
        return
    }
    var url = "/api/exist";
    url = url + "?username=" + str;
    xmlHttp.onreadystatechange = changImage;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null)
}

function changImage() {
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        var status = xmlHttp.responseText;
        if (status.split(':')[1] == 'true') {
            var url = status.split(':')[2];
            $('#user_image').attr('src', "http://localhost:8080/file/" + url);
        } else {
            $('#user_image').attr('src', '1.jpeg');
        }

    }

}


function showUser(str) {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null) {
        alert("Browser does not support HTTP Request");
        return
    }
    var url = "/api/exist";
    url = url + "?username=" + str;
    xmlHttp.onreadystatechange = stateChanged;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null)
}


function searchEmail(email) {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null) {
        alert("Browser does not support HTTP Request");
        return
    }
    var url = "/api/checkmail";
    url = url + "?email=" + email;
    xmlHttp.onreadystatechange = emailChanged;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null)
}

function findPass(email) {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null) {
        alert("Browser does not support HTTP Request");
        return
    }
    var url = "/api/find";
    url = url + "?email=" + email;
    xmlHttp.onreadystatechange = readyPass;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null)
}
/**
 * 密码发回处理
 */
function readyPass() {
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        var status = xmlHttp.responseText;
        if (status.split(':')[1] == 'ok') {
            $('#status').text('密码已经发回，注意查收');
        } else {
            $('#status').text('邮件发送失败，请重新操作');
        }
    }
}


/**
 * 邮箱检测逻辑处理
 *
 */
function emailChanged() {
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        var status = xmlHttp.responseText;
        if (status.split(':')[1] == 'ok') {
            $('#email_tip').text("邮箱已被注册");
        } else {
            $('#email_tip').text("通过");
        }
    }
}


/**
 * 用户名检测逻辑处理
 */
function stateChanged() {
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        //解析json
        var status = xmlHttp.responseText;
        if (status.split(':')[1] == 'true') {
            $('.name_tip').text("用户已存在");
            $('#subLogin').attr("disabled", "disable");

        } else {
            // alert('不存在')
            $('#subLogin').removeAttr("disabled");
            $('.name_tip').text("通过");

        }

    }
}
/**
 * 根据不同浏览器，获得getxmlhttpobject对象
 * @returns {*}
 * @constructor
 */
function GetXmlHttpObject() {
    var xmlHttp = null;
    try {
        // Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    }
    catch (e) {
        //Internet Explorer
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlHttp;
}

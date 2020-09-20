var msg="";

//正则表达式
/*是否带有小数*/
function isDecimal(str )  {
    var  reg= /^\d+\.\d+$/;
    return  reg.test(str);
}
/*校验是否由2-4个中文字符组成 */
function ischina(str) {
    var reg=/^[\u4E00-\u9FA5]{2,4}$/;   /*定义验证表达式*/
    return reg.test(str);     /*进行验证*/
}
/*校验是否全由6-12位数字组成 */
function isNo(str) {
    var reg=/^[0-9]{6,12}$/;   /*定义验证表达式*/
    return reg.test(str);     /*进行验证*/
}
/*校验是否全由6-12位数字或字母组成 */
function isPw(str) {
    var reg=/^[[a-zA-Z0-9]{6,12}$/;   /*定义验证表达式*/
    return reg.test(str);     /*进行验证*/
}
/*校验电话码格式 */
function isTelCode(str) {
    var reg= /^1[0-9]{10}$/;
    return reg.test(str);
}
/*校验邮件地址是否合法 */
function isEmail(str) {
    var reg=/^[a-zA-Z0-9]{1,15}@[a-zA-Z0-9]{2,8}.(\w){2,5}$/;
    return reg.test(str);
}



//ajax登陆验证
function form1() {

    //获取输入的用户名和密码
    var username = $("#txtUserName1").val();
    var password = $("#txtpassword1").val();
    //正则表达式过滤
    if(!isNo(username)){
        alert("账号不合法");
        return false;
    };
    if(!isPw(password)){
        alert("密码不合法");
        return false;
    };

    var mark=false;
    $.ajax({
        url: "/testLogin",
        type: "post",
        //同步执行
        async:false,
        //发送的数据
        data: JSON.stringify({user_number: username, user_password: password}),
        //定义发送请求的数据格式为JSON字符串
        contentType: "application/json;charset=UTF-8",
        //定义回调响应的数据格式为JSON字符串,该属性可以省略
        //dataType : "json",
        //成功响应的结果
        success: function (data) {
            if (data == "ok") {
                mark=true;
            } else {
                alert("账号或密码错误!");
            }
        },
        error: function () {
            alert("ajax请求异常!");
        }
    });
    return mark;
};

//获取输入的用户名和邮箱
var username="";
var email="";
//用户输入的密码检测
var ps1="";
var ps2="";

//ajax注册测验证
function form2() {
    //用户输入的验证码
    var textMsg=$("#test").val();
    username=$("#txtUserName2").val();
    ps1=$("#txtpassword2").val();
    ps2=$("#txtpassword3").val();

    //正则表达式过滤
    if(!isNo(username)){
        alert("账号应为6-12位数字");
        return false;
    };
    if(ps1!=ps2){
        alert("两个密码不一致");
        return false;
    };
    if(!isPw(ps1)){
        alert("密码应为6-12位数字或字母组合");
        return false;
    };
    if(msg!=textMsg||msg==""||textMsg==""){
        alert("验证码有误");
        return false;
    };

    var mark=false;
    $.ajax({
        url : "/testRegister",
        type : "post",
        //同步执行
        async:false,
        //发送的数据
        data : JSON.stringify({user_number:username,user_email:email}),
        //定义发送请求的数据格式为JSON字符串
        contentType : "application/json;charset=UTF-8",
        //定义回调响应的数据格式为JSON字符串,该属性可以省略
        //dataType : "json",
        //成功响应的结果
        success : function(data) {
            if(data=="ok") {
                alert("注册成功!")
                mark=true;
            }else if(data=="number"){
                alert("账号被占用");
            }else {
                alert("邮箱被占用");
            }
        },
        error:function () {
            alert("ajax请求异常!");

        }
    });
    return mark;
};


$().ready(function (){
    //隐藏注册表单
    $("#form2").hide(1);

    //清除广告
    $("#draggable").draggable();
    $("#droppable").droppable({
        drop:function(){
            $("#draggable").css({"display":"none"});
        }
    });
    $("#draggable").mouseenter(function () {
        $("#droppable").text("拖动到此处消除").css({"background-color":"#aaaaaa","width":"120px","height":"120px"})
        $("#droppable").css({"display": "block"});
    });
    $("#draggable").mouseleave(function () {
        $("#droppable").css({"display": "none"});
    });



    var testEmail=$("#testEmail");
    //ajax发送验证码
    self();
    function self(){

        testEmail.one("click",function () {

            email=$("#txtEmail").val();

            if(!isEmail(email)){
                alert("邮箱格式错误");
                self();
                return;

            };

            send_email();
            var int = setInterval(showalert, 1000);
            var wait = 60;

            function showalert() {
                if (wait == 0) {
                    msg = "";
                    self();
                    testEmail.text("重新获取验证码");
                    clearInterval(int);
                } else {
                    testEmail.text(wait-1);
                    wait--;
                }
            }
        });
        function send_email(){
            $.ajax({
                url : "/send_email",
                type : "post",
                //发送的数据
                data : JSON.stringify({user_email:email}),
                //定义发送请求的数据格式为JSON字符串
                contentType : "application/json;charset=UTF-8",
                //定义回调响应的数据格式为JSON字符串,该属性可以省略
                //dataType : "json",
                //成功响应的结果
                success : function(data) {
                    msg=data;
                },
                error:function () {
                    alert("ajax请求异常!");
                }
            });
        }
    }


    //切换到注册表单
    $("#register1").click(function () {
        $("#title").text("用户注册")
        $("#form1").hide(1);
        $("#form2").show(1);


    })
    //切换到登陆表单
    $("#register2").click(function () {
        $("#title").text("用户登录")
        $("#form2").hide(1);
        $("#form1").show(1);

    })




})



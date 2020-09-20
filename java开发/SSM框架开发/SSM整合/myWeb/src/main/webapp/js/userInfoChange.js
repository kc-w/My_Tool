//正则表达式
/*校验是否由6-12个中文,数字,字母字符组成 */
function ischina(str) {
    var reg=/^[a-zA-Z0-9\u4E00-\u9FA5]{6,12}$/;   /*定义验证表达式*/
    return reg.test(str);     /*进行验证*/
}
/*校验是否全由6-12位数字或字母组成 */
function isPw(str) {
    var reg=/^[[a-zA-Z0-9]{6,12}$/;   /*定义验证表达式*/
    return reg.test(str);     /*进行验证*/
}
/*校验邮件地址是否合法 */
function isEmail(str) {
    var reg=/^[a-zA-Z0-9]{1,15}@[a-zA-Z0-9]{2,8}.(\w){2,5}$/;
    return reg.test(str);
}


function userInfoChange(){
    var name=$("#name").val();
    var password=$("#psssword").val();
    var email=$("#email").val();

    if(name!=""){
        if(!ischina(name)){
            alert("昵称由6-12个中文,数字,字母字符组成");
            return false;
        };
    }
    if(password!=""){
        if(!isPw(password)){
            alert("密码应为6-12位数字或字母组合");
            return false;
        };
    }
    if(email!=""){
        if(!isEmail(email)){
            alert("邮箱格式错误");
            return false;

        };
    }

    var mark=false;
    $.ajax({
        url : "/userInfoChange",
        type : "post",
        //同步执行
        async:false,
        //发送的数据
        data : JSON.stringify({user_name:name,user_email:email}),
        //定义发送请求的数据格式为JSON字符串
        contentType : "application/json;charset=UTF-8",
        //定义回调响应的数据格式为JSON字符串,该属性可以省略
        //dataType : "json",
        //成功响应的结果
        success : function(data) {
            if(data=="ok") {
                alert("修改成功!")
                mark=true;
            }else if(data=="name"){
                alert("昵称被占用");
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

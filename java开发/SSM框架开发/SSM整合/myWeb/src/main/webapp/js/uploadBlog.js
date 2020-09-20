$().ready(function () {
    $("#submit").click(function () {
        var title=$("#title").val();
        var body=$("#body").val();

        $.ajax({
            url : "/upLoadBlog",
            type : "post",
            //发送的数据
            data : JSON.stringify({blog_title:title,blog_body:body}),
            //定义发送请求的数据格式为JSON字符串
            contentType : "application/json;charset=UTF-8",
            //定义回调响应的数据格式为JSON字符串,该属性可以省略
            //dataType : "json",
            //成功响应的结果
            success : function(data) {
                if(data=="ok"){
                    alert("发表成功");
                }
            },
            error:function () {
                alert("ajax请求异常!");
            }
        });
    })

})
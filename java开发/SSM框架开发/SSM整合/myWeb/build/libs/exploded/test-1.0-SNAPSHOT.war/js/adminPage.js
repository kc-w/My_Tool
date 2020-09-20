$().ready(function () {

    var pageNumber=Number($("#pageNumber").text());//当前页面数
    var totalPage=Number($("#totalPage").text());//总页数

    //是否正整数
    function isInteger(number){
        return number > 0 && String(number).split('.')[1] == undefined
    }

    //是否是数字
    function isNumber(number){
        return typeof number === 'number'
    }

    //当页数到底时删除掉"下一页"
    if(pageNumber==totalPage){
        $("#nextPage").remove();
    }
    if(pageNumber==1){
        $("#previousPage").remove();
    }
    //检测输入值是否合法,然后进行跳转
    $("#go").click(function(){

        var goPage=Number($("#goPage").val());//输入的页数

        if(isInteger(goPage)&&isNumber(goPage)){
            if(goPage>totalPage){
                $("#goPage").val("");
                alert("超出最大页数!");
            }else {
                $("#go").attr({"href":"/selectAllUser?nowPage="+(goPage*10-10)+"&pageNumber="+goPage});
            }
        }else {
            $("#goPage").val("");
            alert("请输入正整数!");
        }
    });




})
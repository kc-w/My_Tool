//抛出异常
var a=parseInt("111aaa");
var b=parseFloat("11.1aaa");
var c=Number(b);
var d=String(a);
document.write(a+"</br>");
document.write(b+"</br>");
document.write(c+"</br>");
document.write(d+"<br>");
function myfun(x,y){
	var z;
	try{
		if(y==0){
			throw new Error("除数不能为0");
		}
		z=x/y;
	}catch(e){
		z=e.message;
	}finally{
		return z;
	}
}
document.write(myfun(1,0));

//遍历器
function iterator(array){
	var nextIndex=0;
	return{
		next:function(){
			return nextIndex<array.length?{value:array[nextIndex++],done:false}:{value:"数组超出",done:true};
		}
	}
}
//调用函数,传入数组,返回遍历器对象赋值给it,并指向当前数据结构开始位置,然后使用next()进行逐个遍历
//每次调用next()都会返回一个包含value和done的属性对象,value表示当前值,done表示遍历是否结束
var it=iterator(["a","b"]);
document.write(it.next().value+"<br>");
document.write(it.next().value+"<br>");
document.write(it.next().value+"<br>");
document.write(it.next().value+"<br>");
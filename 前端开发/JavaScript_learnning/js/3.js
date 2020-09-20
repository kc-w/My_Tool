//Generator函数,是ES6提供的一种异步编程解决方案
function*try1(){
	yield '第一个';
	yield '第二个';
	yield '第三个';
	return '第si个';
}
var tg=try1();
document.write(tg.next().done);
document.write(tg.next());
document.write(tg.next().value);
document.write(tg.next().done);
//for(var n of try1()){//其中"第三个"不会被输出,因为在遍历到它时会返回一个true终止循环
//	document.write(n.next().value);
//}
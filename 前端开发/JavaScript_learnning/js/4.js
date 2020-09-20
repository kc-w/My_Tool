//打开一个新窗口
var w=window.open();
with(w.document){
	open();
	write("hello"+"<button onclick='self.close()'>关闭窗口</button>");
	close();
}
from socket import *

#定义一个tcp/ip网络服务
tcpServer=socket(AF_INET,SOCK_STREAM)
#给服务器绑定地址
tcpServer.bind( ('127.0.0.1',1111) )
#开启服务器监听(最多同时操作5个客户端)
tcpServer.listen(5)

#等待客户端请求
while True:
    # 接受等待客户端请求
    (tcpClient,tcpAddr)=tcpServer.accept()
    print("客户端地址:" + tcpAddr[0] + "   " + str(tcpAddr[1]))

    #接受客户端传输的数据
    while True:
        #等待客户端传参数
        data=tcpClient.recv(1024)
        if data==b'':
            break
        #将字符串转为字典
        info=eval(data.decode("utf-8"))
        print("用户名:%s,密码:%s" % (info["name"],info["pwd"]))
        reaults="验证失败!"
        if "111" ==info["name"] and "111"==info["pwd"]:
            reaults="验证通过!"
            #将字符串转换二进制数据发给客户端
            tcpClient.send(reaults.encode("utf-8"))
tcpServer.close()
from socket import *

#定义服务器的访问地址和访问端口号
host='127.0.0.1'
port=1111
addr=(host,port)
#一次性最大传输字节
size=1024

#连接服务器
tcpClient=socket(AF_INET,SOCK_STREAM)
tcpClient.connect(addr)
while True:
    info={"name":"111","pwd":"111"}
    #客户端将字典转换未字符串传输
    tcpClient.send(str(info).encode("utf-8"))
    #传输完成后接收服务端响应结果
    data=tcpClient.recv(size)
    #将二进制的响应数据转换为字符串
    print(str(data,encoding="utf-8"))
tcpClient.close()
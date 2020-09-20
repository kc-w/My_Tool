import socket
s=socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
#绑定端口:
s.bind(('127.0.0.1',9999))
print('Bind UDP on 9999...')
while True:
#接收数据:
    data, addr=s.recvfrom(1024)
    print(addr)
    print(data)
    s.sendto(str(data).encode('utf-8'), addr)
import smtplib
from email.header import Header
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.utils import formataddr

# 发件人邮箱
form_email = '17607169189@163.com'
# 收件人列表
list = ['2312297207@qq.com', '17607169189@163.com']
# 授权码
password = '999999999wy'
# 邮箱服务器
smtp_server = 'smtp.163.com'

for to_email in list:
    mail_msg = """
    <div style="width:610px;height:610px;margin:0 auto;">
    <p>
    <img src="cid:image1" style="width:200px;height:200px">
    <img src="cid:image1" style="width:200px;height:200px">
    <img src="cid:image1" style="width:200px;height:200px">
    <img src="cid:image1" style="width:200px;height:200px">
    <img src="cid:image2" style="width:200px;height:200px">
    <img src="cid:image1" style="width:200px;height:200px">
    <img src="cid:image1" style="width:200px;height:200px">
    <img src="cid:image1" style="width:200px;height:200px">
    <img src="cid:image1" style="width:200px;height:200px">
    </p>
    </div>
    """
    msg = MIMEMultipart()
    # 发件人标题
    msg["From"] = formataddr(["Kc", form_email])
    # 收件人标题
    msg["To"] = formataddr(["收件人", to_email])
    # 邮件标题
    msg["Subject"] = Header("空间", "utf-8").encode()

    msgAlternative = MIMEMultipart('alternative')
    msg.attach(msgAlternative)

    # 指定图片为当前目录
    fp1 = open('8.gif', 'rb')
    msgImage1 = MIMEImage(fp1.read())
    fp2 = open('9.gif', 'rb')
    msgImage2 = MIMEImage(fp2.read())
    fp1.close()
    fp2.close()
    # 定义图片 ID，在 HTML 文本中引用
    msgImage1.add_header('Content-ID', '<image1>')
    msg.attach(msgImage1)
    msgImage2.add_header('Content-ID', '<image2>')
    msg.attach(msgImage2)

    # 构造附件1，传送当前目录下的文件
    att1 = MIMEText(open('test.py', 'rb').read(), 'base64', 'utf-8')
    att1["Content-Type"] = 'application/octet-stream'
    # 这里的filename可以任意写，写什么名字，邮件中显示什么名字
    att1["Content-Disposition"] = 'attachment; filename="test.py"'
    msg.attach(att1)
    # 构造附件2，传送当前目录下的文件
    att2 = MIMEText(open('__init__.py', 'rb').read(), 'base64', 'utf-8')
    att2["Content-Type"] = 'application/octet-stream'
    att2["Content-Disposition"] = 'attachment; filename="__init__.py"'
    msg.attach(att2)

    # 第一个参数为邮件正文,第二个参数为邮件格式(html:网页,plain:文本),第三个参数为发送邮件的格式编码
    msgAlternative.attach(MIMEText(mail_msg, 'html', 'utf-8'))

    # smtp服务器对象的创建
    # smtp_ssl方式发送,设置服务器路径和端口号
    server = smtplib.SMTP_SSL(smtp_server, 465)
    # 登录服务器,第一个参数为邮箱账号,第二个参数为授权码
    server.login(form_email, password)
    # 发送邮件,第一个参数发件人,第二个参数收件人,第三个参数邮件内容
    server.sendmail(form_email, to_email, msg.as_string())
    # 关闭
    server.quit()
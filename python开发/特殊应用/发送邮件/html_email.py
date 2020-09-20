import smtplib
from email.header import Header
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.utils import formataddr

#发件人邮箱
form_email='17607169189@163.com'
#授权码
password='999999999wy'
#收件人邮箱
to_email='2312297207@qq.com'
#邮箱服务器
smtp_server='smtp.163.com'

mail_msg = """
<div><img src="cid:image"></div>
"""
msg = MIMEMultipart()
#发件人标题
msg["From"]=formataddr(["Kc",  form_email])
#收件人标题
msg["To"]=formataddr(["收件人", to_email])
#邮件标题
msg["Subject"]=Header("kc空间", "utf-8").encode()

msgAlternative = MIMEMultipart()
msg.attach(msgAlternative)

# 指定图片为当前目录
fp = open('kc.png', 'rb')
msgImage = MIMEImage(fp.read())
fp.close()
# 定义图片 ID，在 HTML 文本中引用
msgImage.add_header('Content-ID', '<image>')
msg.attach(msgImage)

#第一个参数为邮件正文,第二个参数为邮件格式(html:网页,plain:文本),第三个参数为发送邮件的格式编码
msgAlternative.attach(MIMEText(mail_msg, 'html', 'utf-8'))

#smtp服务器对象的创建
#smtp_ssl方式发送,设置服务器路径和端口号
server=smtplib.SMTP_SSL(smtp_server,465)
#登录服务器,第一个参数为邮箱账号,第二个参数为授权码
server.login(form_email,password)
#发送邮件,第一个参数发件人,第二个参数收件人,第三个参数邮件内容
server.sendmail(form_email,to_email,msg.as_string())
#关闭
server.quit()
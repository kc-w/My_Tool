systemctl start network
systemctl enable network
tar -zxvf /home/jdk-*-linux-x64.tar.gz  -C /usr/local/
echo "export JAVA_HOME=/usr/local/jdk1.8.0_181/" >>/etc/profile
echo 'export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar' >>/etc/profile
echo "export HADOOP_HOME=/opt/program/hadoop-2.6.0/" >>/etc/profile
echo "export HIVE_HOME=/opt/program/hive-1.1.0/" >>/etc/profile
echo 'export PATH=$PATH:$JAVA_HOME/bin:$HIVE_HOME/bin:$HADOOP_HOME/bin' >>/etc/profile
source /etc/profile
yum -y install libffi-devel zlib-devel bzip2-devel openssl-devel ncurses-devel sqlite-devel readline-devel tk-devel gcc make
tar -zxvf /home/Python-3.7.0.tgz -C /usr/local/
cd /usr/local/Python-3.7.0
./configure --prefix=/usr/local/python3
make && make install
echo 'export PATH="$PATH:/usr/local/python3/bin"' >/etc/profile.d/python3.sh
cd /etc/profile.d/
source ../profile
yum install unzip
cd /home
unzip /home/Packages.zip
cd /home/Packages
rpm -ivh apr-1.4.8-3.el7_4.1.x86_64.rpm
rpm -ivh apr-util-1.5.2-6.el7.x86_64.rpm
rpm -ivh httpd-tools-2.4.6-80.el7.centos.x86_64.rpm
rpm -ivh mailcap-2.1.41-2.el7.noarch.rpm
rpm -ivh httpd-2.4.6-80.el7.centos.x86_64.rpm
systemctl   start    httpd
systemctl   enable   httpd
rpm -ivh deltarpm-3.6-3.el7.x86_64.rpm
rpm -ivh python-deltarpm-3.6-3.el7.x86_64.rpm
rpm -ivh libxml2-python-2.9.1-6.el7_2.3.x86_64.rpm
rpm -ivh libxml2-2.9.1-6.el7_2.3.x86_64.rpm
rpm -ivh createrepo-0.9.9-28.el7.noarch.rpm
mkdir  /var/www/html/CentOS7.5/
ln -s  /home/Packages   /var/www/html/CentOS7.5/Packages
chmod 755 -R /var/www/html/CentOS7.5/Packages 
createrepo  /var/www/html/CentOS7.5/Packages
setenforce 0
systemctl stop firewalld
systemctl disable firewalld.service
cd /etc/yum.repos.d/
mkdir  bak
mv CentOS-*.repo  bak/
echo '[base]' > base.repo
echo 'name=CentOS-Packages' >> base.repo
echo 'baseurl=http://192.168.1.131/CentOS7.5/Packages/' >> base.repo
echo 'gpgkey=' >> base.repo
echo 'path=/' >> base.repo
echo 'enabled=1' >> base.repo
echo 'gpgcheck=0' >> base.repo
yum clean all
yum makecache
yum list|grep vim
yum install psmisc
hostnamectl --static set-hostname hadoop1
echo '127.0.0.1   localhost' > /etc/hosts
echo '192.168.1.131 hadoop1' >> /etc/hosts
echo '192.168.1.132 hadoop2' >> /etc/hosts
echo '192.168.1.133 hadoop3' >> /etc/hosts
setenforce 0
sed -i 's/^SELINUX=.*/SELINUX=disabled/g'   /etc/selinux/config
sed -i 's/^SELINUX=.*/SELINUX=disabled/g'   /etc/sysconfig/selinux
echo never > /sys/kernel/mm/transparent_hugepage/enabled
echo never > /sys/kernel/mm/transparent_hugepage/defrag
echo "vm.swappiness=0" >> /etc/sysctl.conf
sysctl -p 
cat /proc/sys/vm/swappiness
timedatectl set-timezone "Asia/Shanghai"
yum -y install ntp
cp /etc/ntp.conf /etc/ntp.conf.bak
unzip /opt/program.zip
yum install mariadb-server mariadb
systemctl start mariadb.service
systemctl enable mariadb.service
systemctl stop mariadb.service
cp -r /var/lib/mysql/ /data01/
mv /var/lib/mysql/ /var/lib/mysql.bak/
chown -R mysql:mysql /data01/mysql
ln -s /data01/mysql/ /var/lib/mysql
systemctl restart mariadb 
yum -y install mysql-connector-java
yum -y install psmisc
yum -y install perl
yum -y install  nfs-utils  portmap
systemctl start rpcbind
systemctl enable rpcbind
shutdown -h 3
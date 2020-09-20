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
timedatectl set-timezone "Asia/Shanghai"
yum install ntp
cp /etc/ntp.conf /etc/ntp.conf.bak
hostnamectl --static set-hostname hadoop2
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
unzip /opt/program.zip
yum -y install mysql-connector-java
yum -y install psmisc
yum -y install perl
yum -y install  nfs-utils  portmap
systemctl start rpcbind
systemctl enable rpcbind
shutdown -h 3

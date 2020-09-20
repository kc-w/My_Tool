cd /opt/program/zookeeper-3.4.5
bin/zkServer.sh start
cd /opt/program/hadoop-2.6.0
rm -rf data/tmp/*
sbin/hadoop-daemon.sh start journalnode
sbin/hadoop-daemon.sh start datanode

cd /opt/program/zookeeper-3.4.5
bin/zkServer.sh start
cd /opt/program/hadoop-2.6.0
rm -rf data/tmp/*
sbin/hadoop-daemon.sh start journalnode
bin/hdfs namenode -bootstrapStandby
sbin/hadoop-daemon.sh start namenode
sbin/hadoop-daemon.sh start datanode
sbin/start-yarn.sh
sbin/mr-jobhistory-daemon.sh  start  historyserver
sbin/hadoop-daemon.sh start zkfc

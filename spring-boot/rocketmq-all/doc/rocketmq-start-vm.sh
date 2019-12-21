#!/bin/bash

# rocketmq
# ======= namespace =========
ssh root@192.168.0.116  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin;
nohup sh mqnamesrv  > /root/logs/rocketmqlogs/namesrv.log 2>&1 &";

ssh root@192.168.0.117  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin;
nohup sh mqnamesrv  > /root/logs/rocketmqlogs/namesrv.log 2>&1 &";

# ======= brokers =========
#master-1
ssh root@192.168.0.116  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin;
nohup sh mqbroker -c /usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/2m-2s-sync/broker-a.properties > /root/logs/rocketmqlogs/broker-a.log 2>&1 &";
#slave-1
ssh root@192.168.0.119  " cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin;
nohup sh mqbroker -c /usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/2m-2s-sync/broker-b-s.properties > /root/logs/rocketmqlogs/broker-b-s.log 2>&1 &";
#master-2
ssh root@192.168.0.117  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin;
nohup sh mqbroker -c /usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/2m-2s-sync/broker-b.properties  > /root/logs/rocketmqlogs/broker-b.log 2>&1 &";
#slave-2
 ssh root@192.168.0.120  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin;
nohup sh mqbroker -c /usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/2m-2s-sync/broker-a-s.properties > /root/logs/rocketmqlogs/broker-a-s.log 2>&1 &";

# 开启监控
sleep 1;
ssh root@192.168.0.117  "nohup java -jar rocketmq-console-ng-1.0.1.jar >temp.txt &";
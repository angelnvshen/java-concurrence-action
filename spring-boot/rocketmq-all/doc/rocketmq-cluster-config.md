##### 集群配置



#####集群启动

1：启动NameServer 集群

```shell
cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin
nohup sh mqnamesrv  > /root/logs/rocketmqlogs/namesrv.log 2>&1 &
```

2：启动broker集群

在192.168.0.116上启动master1和slave1

Master1:

```shell
cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin
nohup sh mqbroker -c /usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/2m-2s-sync/broker-a.properties > /root/logs/rocketmqlogs/broker-a.log 2>&1 &
```

Slave1:

```shell
cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin
nohup sh mqbroker -c /usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/2m-2s-sync/broker-b-s.properties > /root/logs/rocketmqlogs/broker-b-s.log 2>&1 &
```

在192.168.0.117上启动master2和slave2

Master2:

```shell
cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin
nohup sh mqbroker -c /usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/2m-2s-sync/broker-b.properties  > /root/logs/rocketmqlogs/broker-b.log 2>&1 &
```

Slave2:

```shell
cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin
nohup sh mqbroker -c /usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/2m-2s-sync/broker-a-s.properties > /root/logs/rocketmqlogs/broker-a-s.log 2>&1 &
```



mvn clean package -Dmaven.test.skip=true

lsof -i:9876

 scp -r 2m-2s-sync/ root@192.168.0.117:/usr/local/soft/rocketmq-all-4.5.1-bin-release/conf/

rm -rf store

mkdir store

mkdir store/commitlog

mkdir store/consumequeue



rm -rf store-slave

mkdir store-slave

mkdir store-slave/commitlog

mkdir store-slave/consumequeue



===================

rm -rf store/

mkdir store

mkdir store/broker-a

mkdir store/broker-a/commitlog

mkdir store/broker-a/consumequeue



mkdir store/broker-a-slave

mkdir store/broker-a-slave/commitlog

mkdir store/broker-a-slave/consumequeue

mkdir store/broker-a-slave/checkpoint



mkdir store/broker-b

mkdir store/broker-b/commitlog

mkdir store/broker-a/consumequeue



mkdir store/broker-b-slave

mkdir store/broker-b-slave/commitlog

mkdir store/broker-b-slave/consumequeue



```shell
sh mqshutdown broker
sh mqshutdown namesrv
```

######进入命令行模式：systemctl set-default multi-user.target 

1. nohup java -jar shareniu.jar >temp.txt &



wget https://archive.apache.org/dist/rocketmq/4.4.0/rocketmq-all-4.4.0-bin-release.zip




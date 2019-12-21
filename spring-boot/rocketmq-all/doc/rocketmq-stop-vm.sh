#!/bin/bash

# rocketmq

# ======= namespace =========
ssh root@192.168.0.116  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin; sh mqshutdown namesrv";

ssh root@192.168.0.117  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin; sh mqshutdown namesrv";

# ======= brokers =========
#master-1
ssh root@192.168.0.116  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin; sh mqshutdown broker";
#slave-1
ssh root@192.168.0.119  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin; sh mqshutdown broker";
#master-2
ssh root@192.168.0.117  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin; sh mqshutdown broker";
#slave-2
 ssh root@192.168.0.120  "cd /usr/local/soft/rocketmq-all-4.5.1-bin-release/bin; sh mqshutdown broker";
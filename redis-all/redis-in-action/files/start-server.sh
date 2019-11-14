#!/bin/bash

# --network host  主机模式

PWD=/Users/my/soft/docker-data/redis
for port in `seq 1 3`;
do
docker run --name redis-0${port} -p 6${port}79:6${port}79 -v $PWD/server-0${port}/data:/data -v $PWD/server-0${port}/config/redis.conf:/redis/config/redis.conf -d redis redis-server /redis/config/redis.conf --port 6${port}79
done
#docker run --name redis-02 --network host -p 6279:6279 -v $PWD/server-02/data:/data -v $PWD/server-02/config/redis.conf:/redis/config/redis.conf -d redis redis-server /redis/config/redis.conf --port 6279

#docker run --name redis-03 --network host -p 6379:6379 -v $PWD/server-03/data:/data -v $PWD/server-03/config/redis.conf:/redis/config/redis.conf -d redis redis-server /redis/config/redis.conf --port 6379
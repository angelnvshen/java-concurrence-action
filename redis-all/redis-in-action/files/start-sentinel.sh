#!/bin/bash

PWD=/Users/my/soft/docker-data/redis/sentinel

for port in `seq 1 3`;
do
 docker run -d --name redis-sentinel-0${port} -p 26${port}79:26${port}79 -v $PWD/0${port}/data:/var/redis/data -v $PWD/0${port}/config:/config redis /config/sentinel.conf --sentinel
done

 #docker run -d --name redis-sentinel-02 --network host -p 26279:26279 -v $PWD/02/data:/var/redis/data -v $PWD/02/config:/config redis /config/sentinel.conf --sentinel

 #docker run -d --name redis-sentinel-03 --network host -p 26379:26379 -v $PWD/03/data:/var/redis/data -v $PWD/03/config:/config redis /config/sentinel.conf --sentinel



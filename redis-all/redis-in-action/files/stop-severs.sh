#!/bin/bash
for port in `seq 1 3`;
do 
docker stop redis-0${port}
docker rm redis-0${port}
done
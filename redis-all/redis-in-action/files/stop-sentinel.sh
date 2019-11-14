#!/bin/bash
docker stop redis-sentinel-01
docker stop redis-sentinel-02
docker stop redis-sentinel-03

docker rm redis-sentinel-01
docker rm redis-sentinel-02
docker rm redis-sentinel-03
#!/bin/bash

docker start amazing_almeida

sleep 3.25m

systemctl -f stop docker
service docker start
docker start amazing_almeida

sleep 2.5m

systemctl -f stop docker
service docker start
docker start amazing_almeida

sleep 2.5m

systemctl -f stop docker
service docker start
docker start amazing_almeida

sleep 2.5m

systemctl -f stop docker
service docker start
docker start amazing_almeida

sleep 3.25m

docker stop amazing_almeida
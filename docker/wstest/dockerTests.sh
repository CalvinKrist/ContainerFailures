#!/bin/bash
set -ex

container_name=$(sudo docker run -d -p 8080:8080 wsserver)
cd client_operator

./docker_baseline.sh $container_name 1
./docker_container_force_stop.sh $container_name 2
./docker_container_pause.sh $container_name 3
./docker_container_restart.sh $container_name 4
./docker_container_stop.sh $container_name 5
./docker_service_force_shutdown.sh $container_name 6
./docker_service_restart.sh $container_name 7
./docker_service_shutdown.sh $container_name 8
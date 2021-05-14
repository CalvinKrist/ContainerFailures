#!/bin/bash
set -ex

serverName=wsserver

container_name=$(sudo docker run -d -p 8080:8080 $serverName)
cd client_operator

if [ "$1" = "1" ] ; then
	./docker_baseline.sh $container_name 1
fi
if [ "$1" = "2" ] ; then
	./docker_container_force_stop.sh $container_name 2
fi
if [ "$1" = "3" ] ; then
	./docker_container_pause.sh $container_name 3
fi
if [ "$1" = "4" ] ; then
	./docker_container_restart.sh $container_name 4
fi
if [ "$1" = "5" ] ; then
	./docker_container_stop.sh $container_name 5
fi


docker stop "$container_name"


###################################################################

serverName=wsservercomp
container_name=$(sudo docker run -d -p 8080:8080 $serverName)

if [ "$1" = 11 ] ; then
	./docker_container_pause.sh $container_name 11 --compute
fi

if [ "$1" = 14 ] ; then
./docker_service_force_shutdown.sh $container_name 14 --compute
fi

if [ "$1" = 15 ] ; then
./docker_service_restart.sh $container_name 15 --compute
fi

if [ "$1" = 16 ] ; then
./docker_service_shutdown.sh $container_name 16 --compute
fi

docker stop "$container_name"
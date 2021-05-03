#!/bin/bash
set -ex

useNewContainer=false
if [ $# -eq 1 ]
  then
  	if [ $1 = "--useNewContainer" ]
	  	then 
	    	useNewContainer=true
	fi
fi

serverName=wsserver

container_name=$(sudo docker run -d -p 8080:8080 $serverName)
cd client_operator

./docker_baseline.sh $container_name 1

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_container_force_stop.sh $container_name 2

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_container_pause.sh $container_name 3

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_container_restart.sh $container_name 4

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_container_stop.sh $container_name 5

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_service_force_shutdown.sh $container_name 6

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_service_restart.sh $container_name 7

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_service_shutdown.sh $container_name 8

docker stop "$container_name"


###################################################################

serverName=wsservercomp
container_name=$(sudo docker run -d -p 8080:8080 $serverName)

./docker_baseline.sh $container_name 9

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_container_force_stop.sh $container_name 10

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_container_pause.sh $container_name 11

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_container_restart.sh $container_name 12

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_container_stop.sh $container_name 13

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_service_force_shutdown.sh $container_name 14

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_service_restart.sh $container_name 15

if [ "$useNewContainer" = true ] ; then
	docker stop "$container_name"
	container_name=$(sudo docker run -d -p 8080:8080 $serverName)
fi

./docker_service_shutdown.sh $container_name 16

docker stop "$container_name"
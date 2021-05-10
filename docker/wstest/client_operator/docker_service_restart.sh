#!/bin/bash
set -ex

id="$2"

jarName="Client.jar"
if [ $# -eq 3 ]
  then
    if [ $3 = "--compute" ]
      then
        jarName="ClientCompute.jar"
    fi
fi

run_client() {
	cd ../client_emulator
	java -jar $jarName localhost 8080 "$id" 14
}

docker start $1
run_client &

sleep 3.25m

service docker restart
docker start $1

sleep 2.5m

service docker restart
docker start $1

sleep 2.5m

service docker restart
docker start $1

sleep 2.5m

service docker restart
docker start $1

#sleep 3.25m
sleep 3.5m

docker stop $1

until [ -f "../client_emulator/Results_Data_Test_$2.csv" ]
do
     sleep 5
done
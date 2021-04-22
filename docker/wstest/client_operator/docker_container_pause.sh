#!/bin/bash
set -ex

id="$2"

run_client() {
	cd ../client_emulator
	java -jar Client.jar localhost 8080 "$id" 14
}

docker start $1

run_client &

sleep 3.25m

docker pause $1
docker unpause $1

sleep 2.5m

docker pause $1
docker unpause $1

sleep 2.5m

docker pause $1
docker unpause $1

sleep 2.5m

docker pause $1
docker unpause $1

sleep 3.25m

docker stop $1

until [ -f "../client_emulator/Results_Data_Test_$2.csv" ]
do
     sleep 5
done

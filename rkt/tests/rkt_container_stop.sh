#!/bin/bash
set -ex

id="$1"

run_client() {
	cd ../results
	java -jar ../tests/Client.jar localhost 8080 "$id" 14
}

cd ../rkt
vagrant ssh -c 'nohup sudo rkt run --insecure-options=image --net=host --uuid-file-save="/tmp/uuid" wsserver.aci & sleep 30s'

run_client &

sleep 195

vagrant ssh -c "sudo rkt stop --uuid-file=/tmp/uuid"
vagrant ssh -c 'nohup sudo rkt run --insecure-options=image --net=host --uuid-file-save="/tmp/uuid" wsserver.aci & sleep 30s'

sleep 150

vagrant ssh -c "sudo rkt stop --uuid-file=/tmp/uuid"
vagrant ssh -c 'nohup sudo rkt run --insecure-options=image --net=host --uuid-file-save="/tmp/uuid" wsserver.aci & sleep 30s'

sleep 150

vagrant ssh -c "sudo rkt stop --uuid-file=/tmp/uuid"
vagrant ssh -c 'nohup sudo rkt run --insecure-options=image --net=host --uuid-file-save="/tmp/uuid" wsserver.aci & sleep 30s'

sleep 150

vagrant ssh -c "sudo rkt stop --uuid-file=/tmp/uuid"
vagrant ssh -c 'nohup sudo rkt run --insecure-options=image --net=host --uuid-file-save="/tmp/uuid" wsserver.aci & sleep 30s'

#sleep 3.25m
sleep 210

vagrant ssh -c "sudo rkt stop --uuid-file=/tmp/uuid"

until [ -f "../results/Results_Data_Test_$id.csv" ]
do
     sleep 5
done
#!/bin/bash

id="$1"

run_client() {
	cd ../results
	java -jar ../tests/Client.jar localhost 8080 "$id" 4
}

cd ../rkt
vagrant ssh -c 'nohup sudo rkt run --insecure-options=image --net=host --uuid-file-save="/tmp/uuid" wsserver.aci & sleep 30s'

run_client &

sleep 3.25m

pid=$(vagrant ssh -c "pgrep -f /usr/lib/jvm/java/bin/java")
vagrant ssh -c "kill -19 ${pid}"
vagrant ssh -c "kill -18 ${pid}"

sleep 2.5m

vagrant ssh -c "kill -19 ${pid}"
vagrant ssh -c "kill -18 ${pid}"

sleep 2.5m

vagrant ssh -c "kill -19 ${pid}"
vagrant ssh -c "kill -18 ${pid}"

sleep 2.5m

vagrant ssh -c "kill -19 ${pid}"
vagrant ssh -c "kill -18 ${pid}"

sleep 3.25m

vagrant ssh -c "sudo rkt stop --uuid-file=/tmp/uuid"

until [ -f "../results/Results_Data_Test_$id.csv" ]
do
     sleep 5
done
#!/bin/bash

systemd-run --unit=rkt --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 3.25m

pid=`pgrep -f /usr/lib/jvm/java/bin/java`
kill -19 $pid
kill -18 $pid

sleep 2.5m

kill -19 $pid
kill -18 $pid

sleep 2.5m

kill -19 $pid
kill -18 $pid

sleep 2.5m

kill -19 $pid
kill -18 $pid

sleep 3.25m

rkt stop --uuid-file=/tmp/uuid
#!/bin/bash

systemd-run --unit=rkt --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 3.25m

service rkt restart

sleep 2.5m

service rkt restart

sleep 2.5m

service rkt restart

sleep 2.5m

service rkt restart

sleep 3.25m

service rkt stop
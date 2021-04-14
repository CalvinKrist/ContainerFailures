#!/bin/bash

systemd-run --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 3.25m

rkt stop --force --uuid-file=/tmp/uuid
systemd-run --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 2.5m

rkt stop --force --uuid-file=/tmp/uuid
systemd-run --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 2.5m

rkt stop --force --uuid-file=/tmp/uuid
systemd-run --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 2.5m

rkt stop --force --uuid-file=/tmp/uuid
systemd-run --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 3.25m

rkt stop --uuid-file=/tmp/uuid
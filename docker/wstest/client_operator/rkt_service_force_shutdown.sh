#!/bin/bash

systemd-run --unit=rkt --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 3.25m

systemctl -f stop rkt
systemd-run --unit=rkt --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 2.5m

systemctl -f stop rkt
systemd-run --unit=rkt --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 2.5m

systemctl -f stop rkt
systemd-run --unit=rkt --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 2.5m

systemctl -f stop rkt
systemd-run --unit=rkt --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 3.25m

service rkt stop
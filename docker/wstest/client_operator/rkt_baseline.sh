#!/bin/bash

systemd-run --unit=rkt --slice=machine rkt run --uuid-file-save=/tmp/uuid --net=host sha512-a552c61073f6 &

sleep 14m

systemctl -f stop rkt
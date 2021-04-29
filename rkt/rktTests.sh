#!/bin/bash
set -ex

cd rkt
vagrant up --provision

cd ../tests

./rkt_baseline.sh 1 # on for 14 minutes, then shutdown. No interruptions

./rkt_container_force_stop.sh 2 # request through rkt force stop every ~2.75 minutes

./rkt_container_pause.sh  # sigpause java subprocess every ~2.75 minutes

./rkt_container_stop.sh  # request through rkt stop every ~2.75 minutes

./rkt_service_force_shutdown.sh  # container process kill every ~2.75 minutes

# no service non-force stop (unsupported)
# no container restart (unsupported)

cd ../rkt
vagrant destroy -f
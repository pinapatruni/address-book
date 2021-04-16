#!/bin/sh

set -x
set -e

java -XX:+UseSerialGC \
    -XX:MinHeapFreeRatio=20 \
    -XX:MaxHeapFreeRatio=40 \
    -jar /reece/service/service.jar


     

#!/bin/bash

docker build -t tcpatter/sentimenty ./
wd=$(pwd)
docker run -v "${wd}/app":/home/sentimenty -p 8080:8080 --name sentimenty -d tcpatter/sentimenty

#!/usr/bin/env bash

sudo su

apt-get update

apt-get install -y nginx
apt-get install -y vim

apt-get install python-software-properties
add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install oracle-java8-installer

service nginx start

exit

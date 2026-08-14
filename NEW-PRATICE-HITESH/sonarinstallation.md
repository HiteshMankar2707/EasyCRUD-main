# Sonarqube Installation
## Install and configure Database
```shell
sudo apt update
apt install openjdk-17-jdk -y. 
apt install postgresql -y
systemctl start postgresql
sudo -u postgres psql
>> CREATE USER linux PASSWORD 'redhat';
>> CREATE DATABASE sonarqube;
>> GRANT ALL PRIVILEGES ON DATABASE sonarqube TO linux;
>> \c sonarqube;
>> GRANT ALL PRIVILEGES ON SCHEMA public TO linux;
>> \q
```
## Required java. 21
```shell
sudo apt update
sudo apt install fontconfig openjdk-21-jre
java -version
```

## Configure Linux Machine
```shell
sysctl -w vm.max_map_count=524288
sysctl -w fs.file-max=131072
ulimit -n 131072
ulimit -u 8192
```

## Install and Configure Sonarqube
```shell
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-25.5.0.107428.zip
apt install unzip -y
unzip sonarqube-25.5.0.107428.zip
mv sonarqube-25.5.0.107428 /opt/sonar
cd /opt/sonar
vim conf/sonar.properties
>> sonar.jdbc.username=linux
>> sonar.jdbc.password=redhat
>> sonar.jdbc.url=jdbc:postgresql://localhost/sonarqube
useradd sonar -m
chown sonar:sonar -R /opt/sonar
su sonar
cd /opt/sonar/bin/linux-x86-64
./sonar.sh start
./sonar.sh status 
```
## SonarQube
sqp_83d0147c52b3e869387a7c968cbc3e34474f6d8c

## Jnekins 
b501a2ba303b46e983d091adeb1555a5

# Install plugin  rght way add the credential in sonarqube instead of pipeline not recommanedded security point of view. 
sonarqube scanner 
GO -> manage jenkins -> system -> SonarQube installations

# Go -> credetails all credential are stored there.

# How to add the sonarqube pipeline syntax in pipeline make sure SonarPlugin is installed 

go to -> jenkins pipeline -> pipeline syntax -> check -> withSonarQubeEnvPrepare SonarQube Scanner environment

# SonarQube added the stage quality gate 

jenkins -> pipeline syntax -> timeout: enfore time out -> 10 minute -> wait till time it important because wait 10 minute otherwise fail without timeout it will wait we dont how much time

# we need to creat the webhook in sonarqube 
step1 -> go to the -> sonarqube -> administrator -> webook configure -> add jenkins URL -> PRIVATE IP:8080/sonarqube-webhook
-> in short sonarqube send payload (message) to the jenkins code passes or not 

# we have to stored the artifact in s3 buckets 
delivery -> studentapp.jar -> artifact s3 bucket -> versioning enable.

# how to webook configure in Github 
-> repo -> setting -> webhook -> add -> http://3.111.57.68:8080/github-webhook/ -> push event -> update. 
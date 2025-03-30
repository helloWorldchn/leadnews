# leadnews
## 1.简介
### 尚硅谷微服务项目：谷粒学院（后端）

## 2.依赖中间件
### 启动项目需要：
### 开启elasticsearch,kibana,mysql,mongodb,redis,kafka,zookeeper,nacos,minio,xxl-job-admin
### 本项目中elasticsearch,kibana,mysql,mongodb,redis,kafka,zookeeper,nacos,minio,xxl-job-admin部署在Linux中，
### 位于 `docker`容器

## 3.中间件启动流程：
### start the docker on linux
```shell
systemctl start docker.service
```
### start the container (elasticsearch,kibana,mysql,mongodb,redis,kafka,zookeeper,nacos,minio,xxl-job-admin)
```
kibana:7.4.0
xxl-job-admin:2.3.0
mysql:5.7
mongo
elasticsearch:7.4.0
kafka:2.12-2.3.1
zookeeper:3.4.14
redis 
minio 
nacos:1.2.0
```
### start the nginx on Windows
http://localhost:8801

http://localhost:8802

http://localhost:8803

### the web of nacos
http://192.168.153.82:8848/nacos/index.html



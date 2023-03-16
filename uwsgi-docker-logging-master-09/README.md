# uwsgi-docker-logging
Logging configuration of a flask container with uWSGI and Nginx using Supervisor, Syslog-ng and FluentD.
The deployment collects both the Nginx and uWSGI (application) logs and separates between them by introducting `process` tag with either `uwsgi` or `nginx` values.
Nginx logs are parsed using FluentD Nginx parsing support. Python (uWSGI) exceptions (Traceback) multi-line logs are concatenated into a single log message.

# Configuration

## Output to Elasticsearch
Point FluentD container to Elasticsearch by setting the environment variables `ES_HOST` and `ES_PORT`

例子在：C:\fluent\uwsgi-docker-logging\container
https://github.com/tzs919/uwsgi-docker-logging.git

构建fluentd镜像并启动
docker-compose up --build fluentd
tutorial3的程序路径在：C:\python\tutorial3
运行前先docker-compose down

生成的镜像名：目录名（项目名称）_服务名称:latest
uwsgi-docker-logging_fluentd:latest
uwsgi-docker-logging_flask:latest

生成的容器名：项目名称_服务名称_序号

====================启动=========================
需要按以下顺序启动
docker-compose up -d elasticsearch kibana
稍等约1分钟，等完全可用
docker-compose up -d fluentd
稍等约半分钟，等完全可用
docker-compose up -d flask
====================使用=========================
日志搜索
kibana：http://localhost:5601

web程序访问（flask程序）
http://localhost:8090/
http://localhost:8090/echo_request
=============================================



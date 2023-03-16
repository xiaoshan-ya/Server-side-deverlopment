FROM library/python:3.7-buster

RUN apt-get update && apt-get install -y nginx

RUN apt-get install -y syslog-ng
RUN pip install supervisor

COPY requirements.txt /home/docker/code/app/
RUN pip install -r /home/docker/code/app/requirements.txt

RUN echo "daemon off;" >> /etc/nginx/nginx.conf
COPY nginx-app.conf /etc/nginx/sites-available/default
COPY supervisor-app.conf /usr/local/etc/supervisord.conf
COPY syslog-ng.conf /etc/syslog-ng/conf.d/syslog-ng-extra.conf

RUN mkdir -p /opt/app
RUN mkdir -p /run/uwsgi && chown www-data /run/uwsgi
COPY app /opt/app
COPY uwsgi.ini /opt/app/uwsgi.ini
COPY uwsgi_params /opt/app/uwsgi_params

CMD ["supervisord", "-n"]
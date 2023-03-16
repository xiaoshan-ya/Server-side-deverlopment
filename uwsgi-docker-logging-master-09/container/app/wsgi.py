from flask import Flask, request, jsonify
import logging.config

app = Flask(__name__)

logger_conf = {
    'version': 1,
    'formatters': {'default': {
        'format': '[%(asctime)s] %(levelname)s in %(module)s: %(message)s',
    }},
    'handlers': {'wsgi': {
        'class': 'logging.StreamHandler',
        'stream': 'ext://flask.logging.wsgi_errors_stream',
        'formatter': 'default'
    }},
    'root': {
        'level': 'DEBUG',
        'handlers': ['wsgi']
    }
}

logging.config.dictConfig(logger_conf)


@app.route("/")
def hello():
    app.logger.info('Asking for home')
    return "<h1 style='color:blue'>Hello There!</h1>"


@app.route('/echo_request')
def echo_request():
    app.logger.info('Asking for echo_request')
    return jsonify(dict(request.headers))


@app.route('/fail')
def fail_request():
    raise Exception("Very bad")


if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0')

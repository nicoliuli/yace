#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import datetime
import base64
import hmac
import json
from hashlib import sha256 as sha256
from urllib.request import Request, urlopen


pid = '90800002'
secret_key = 'GmpxVGG6pRdKc6frQ+WQA1RtpUQz3680SrYKtBKH79I='
endpoint_host = 'asr.ilivedata.com'
endpoint_path = '/api/v1/speech/recognize/submit'
endpoint_url = 'https://asr.ilivedata.com/api/v1/speech/recognize/submit'


def recognize(audio_url, language_code, user_id, speaker_diarization):
    now_date = datetime.datetime.utcnow().strftime('%Y-%m-%dT%H:%M:%SZ')

    params = {
        'languageCode': language_code,
        'config': {
            'codec': "PCM",
            'sampleRateHertz': 16000
        },
        'diarizationConfig': {
            'enableSpeakerDiarization': speaker_diarization
        },
        'uri': audio_url,
        'userId': user_id
    }
    query_body = json.dumps(params)
    print(query_body)
    parameter = "POST\n"
    parameter += endpoint_host + "\n"
    parameter += endpoint_path + '\n'
    parameter += sha256(query_body.encode('utf-8')).hexdigest() + "\n"
    parameter += "X-AppId:" + pid + "\n"
    parameter += "X-TimeStamp:" + now_date

    print(parameter)
    signature = base64.b64encode(
        hmac.new(secret_key, parameter.encode('utf-8'), digestmod=sha256).digest())
    print(signature)
    return send(query_body, signature, now_date)


def send(querystring, signature, time_stamp):
    headers = {
        "X-AppId": pid,
        "X-TimeStamp": time_stamp,
        "Content-type": "application/json",
        "Authorization": signature,
        "Host": endpoint_host,
        "Connection": "keep-alive"
    }

    # querystring = parse.urlencode(params)
    req = Request(endpoint_url, querystring.encode(
        'utf-8'), headers=headers, method='POST')
    return urlopen(req).read().decode()


if __name__ == '__main__':

    audio_uri = 'https://asr-test.s3.cn-northwest-1.amazonaws.com.cn/rcs/90800009/audio/2022/05/09/test_24774b98-5f31-4df6-9124-e1c5ab7dbc54_1652065831997/b99df6ac943840cb8c392fc8355067dd'
    response = recognize(audio_uri, 'zh-CN', '12345678', True)
    print(response)

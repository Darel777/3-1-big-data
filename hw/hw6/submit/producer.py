#!/usr/bin/python3

from kafka import KafkaProducer

conf = {
    'bootstrap_servers': ["192.168.0.125:9092"],
    'topic_name': '77',
}

print('start producer')
producer = KafkaProducer(bootstrap_servers=conf['bootstrap_servers'])

data = bytes("201250125 liuchengjie")
producer.send(conf['topic_name'], data)
producer.close()
print('end producer')

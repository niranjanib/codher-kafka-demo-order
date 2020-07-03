package com.tw.codher.order.publisher;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderPlacedEventPublisher {

    @Value("${order.created.topic}")
    String topicName;

    @Value("${broker.url}")
    String brokerUrl;

    public void publish(int count) {

        Map<String, Object> producerProperties = new HashMap();

        producerProperties.put("bootstrap.servers", brokerUrl);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Producer<Integer, String> producer = new KafkaProducer<>(producerProperties);

        for (int i = 1; i <= count; i++) {
            producer.send(new ProducerRecord(topicName, i, "Order Created - Id : " + 1));
        }

        producer.flush();
        producer.close();
    }
}

package com.huyong.spring.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author huyong
 */
public class ConsumerTest  {


    private static Properties createConsumerConfig(String group) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "49.234.13.129:9092");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", 1000);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("group.id", group);
        return props;
    }

    private static ConsumerConfig createProviderConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "49.234.13.129:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        return new ConsumerConfig(props);
    }

    public static void test() throws InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "49.234.13.129:9092");
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singletonList("quickstart-events"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, value = %s \n", record.offset(), record.value());
            }
        }
    }

    public static void testProvider() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "49.234.13.129:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        while (true) {
            producer.send(new ProducerRecord<String, String>("quickstart-events222","hello","world"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread consumer = new Thread(() -> {
            try {
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread provider = new Thread(() -> {
            testProvider();
        });
       // consumer.start();
        provider.start();
        //consumer.join();
        provider.join();
    }
}

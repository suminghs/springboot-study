package com.example.fuzz;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/5/20 16:46
 */
public class Consumer2 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "39.106.77.135:9092");
        props.put("enable.auto.commit", "true");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "first");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("first"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value  = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}

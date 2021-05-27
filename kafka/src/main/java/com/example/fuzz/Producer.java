package com.example.fuzz;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/5/20 16:16
 */
public class Producer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "39.106.77.135:9092");
        props.put("acks", "all");
        //重试次数
        props.put("retries", 1);
        //批次大小
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, Arrays.asList("com.example.fuzz.interceptor.TimeInterceptor"));

        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 50; i++) {
//            producer.send(new ProducerRecord<String, String>("first", String.valueOf(i), "api === " + String.valueOf(i)),
//                    ((RecordMetadata metadata, Exception exception) -> {
//                        if (exception == null) {
//                            System.out.println("success->" +
//                                    metadata.offset());
//                        } else {
//                            exception.printStackTrace();
//                        }
//                    }));
            RecordMetadata metadata = producer.send(new ProducerRecord<String, String>("first", String.valueOf(i), "api === " + String.valueOf(i))).get();
            System.out.println("success->" + metadata.offset());
        }
        producer.close();
    }

}

package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author lee
 */
public class IConsumer {

    public static void main(String[] args){
        Properties defaultProperties = new Properties();
        //指定了kafka集群的地址，多个地址使用逗号隔开
        defaultProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "81.71.64.120:9092");
        //指定了对key进行解序列化所使用的序列化器，这里的解序列化器需要与producer端使用的序列化器一一对应
        defaultProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //指定了对value进行解序列化所使用的序列化器，这里的解序列化器需要与producer端使用的序列化器一一对应
        defaultProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //指定了当前消费者所在的组，如果有多个消费者，无论是否在同一个服务中，只要其group.id是一样的，那么就认为其同属于一个组；
        defaultProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        defaultProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        defaultProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        defaultProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(defaultProperties);
        // 提交当前consumer所订阅的消息，如果有多个，则将其添加到一个list中进行提交
        consumer.subscribe(Collections.singletonList("test-topic"));
        try {
            // 在无限循环中执行的原因在于，consumer是需要不断的轮询kafka以获取所订阅的topic的消息数据的
            while (true) {
                // 这里poll()方法就是拉取消息相关的数据的方法，而参数指定的拉取间隔为1s，也即每秒进行一次拉取动作
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    // 进行消息的相关处理，这里是将其进行了打印
                    System.out.printf("offset = %d, key = %s, value = %s%n",
                            record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }

    }



}

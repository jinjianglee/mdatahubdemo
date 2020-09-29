package com.kafka;

import com.google.common.collect.ImmutableList;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author Lee
 */
public class KafkaUtil {
    private static final Logger logger = Logger.getLogger(KafkaUtil.class);

    public static AdminClient getAdminClient() throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        //此处属性为配置在zookeeper上面的Kafka节点位置，如果连接失败检查Kafka配置是否为监听本地，修改监听本地配置为监听服务器host
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"81.71.64.120:9092");
        AdminClient client = KafkaAdminClient.create(properties);
        //此步骤为通过Java在Kafka节点上面创建一个topic
        //createTopic("test-topic", 1, (short) 1, client);
        Set topics = client.listTopics().names().get();
        return client;
    }

    public static boolean createTopic(String name, int numPartitions, short replicationFactor,AdminClient client) throws ExecutionException, InterruptedException {
        Map<String,String> topicConfig=new HashMap<>();
        /** 旧日志段的保留策略，删除或压缩，此时选择删除 */
        topicConfig.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE);
        /** 过期数据的压缩方式，如果上面选项为压缩的话才有效 */
        //topicConfig.put(TopicConfig.COMPRESSION_TYPE_CONFIG,"snappy");
        /**
         * The amount of time to retain delete tombstone markers for log compacted topics.
         * This setting also gives a bound on the time in which a consumer must complete a
         * read if they begin from offset 0 to ensure that they get a valid snapshot of the
         * final stage (otherwise delete tombstones may be collected before they complete their scan).
         * 默认1天
         * */
        topicConfig.put(TopicConfig.DELETE_RETENTION_MS_CONFIG,"86400000");
        /** 文件在文件系统上被删除前的保留时间，默认为60秒 */
        topicConfig.put(TopicConfig.FILE_DELETE_DELAY_MS_CONFIG,"60000");
        /** 将数据强制刷入日志的条数间隔 */
        //topicConfig.put(TopicConfig.FLUSH_MESSAGES_INTERVAL_CONFIG,"9223372036854775807");
        /** 将数据强制刷入日志的时间间隔 */
        //topicConfig.put(TopicConfig.FLUSH_MS_CONFIG,"9223372036854775807");
        /** offset设置 */
        //topicConfig.put(TopicConfig.INDEX_INTERVAL_BYTES_CONFIG,"4096");
        /** 每个批量消息最大字节数 */
        //topicConfig.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"1000012");
        /** 记录标记时间与kafka本机时间允许的最大间隔，超过此值的将被拒绝 */
        //topicConfig.put(TopicConfig.MESSAGE_TIMESTAMP_DIFFERENCE_MAX_MS_CONFIG,"9223372036854775807");
        /** 标记时间类型，是创建时间还是日志时间 CreateTime/LogAppendTime */
        //topicConfig.put(TopicConfig.MESSAGE_TIMESTAMP_TYPE_CONFIG,"CreateTime");
        /** 如果日志压缩设置为可用的话，设置日志压缩器清理日志的频率。默认情况下，压缩比率超过50%时会避免清理日志。
         此比率限制重复日志浪费的最大空间，设置为50%，意味着最多50%的日志是重复的。更高的比率设置意味着更少、更高效
         的清理，但会浪费更多的磁盘空间。*/
        //topicConfig.put(TopicConfig.MIN_CLEANABLE_DIRTY_RATIO_CONFIG,"0.5");
        /** 消息在日志中保持未压缩状态的最短时间，只对已压缩的日志有效 */
        //topicConfig.put(TopicConfig.MIN_COMPACTION_LAG_MS_CONFIG,"0");
        /** 当一个producer的ack设置为all（或者-1）时，此项设置的意思是认为新记录写入成功时需要的最少副本写入成功数量。
         如果此最小数量没有达到，则producer抛出一个异常（NotEnoughReplicas 或者NotEnoughReplicasAfterAppend）。
         你可以同时使用min.insync.replicas 和ack来加强数据持久话的保障。一个典型的情况是把一个topic的副本数量设置为3,
         min.insync.replicas的数量设置为2,producer的ack模式设置为all，这样当没有足够的副本没有写入数据时，producer会抛出一个异常。*/
        topicConfig.put(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG,"1");
        /** 如果设置为true，会在新日志段创建时预分配磁盘空间 */
        topicConfig.put(TopicConfig.PREALLOCATE_CONFIG,"true");
        /** 当保留策略为删除（delete）时，此设置控制在删除就日志段来清理磁盘空间前，保存日志段的partition能增长到的最大尺寸。
         * 默认情况下没有尺寸大小限制，只有时间限制。。由于此项指定的是partition层次的限制，它的数量乘以分区数才是topic层面保留的数量。 */
        // topicConfig.put(TopicConfig.RETENTION_BYTES_CONFIG,"-1");
        /**
         * 当保留策略为删除（delete）时，此设置用于控制删除旧日志段以清理磁盘空间前，日志保留的最长时间。默认为7天。
         * 这是consumer在多久内必须读取数据的一个服务等级协议（SLA）。
         * */
        topicConfig.put(TopicConfig.RETENTION_MS_CONFIG,"604800000");
        /**
         * 此项用于控制日志段的大小，日志的清理和持久话总是同时发生，所以大的日志段代表更少的文件数量和更小的操作粒度。
         * */
        topicConfig.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824");
        /**
         * 此项用于控制映射数据记录offsets到文件位置的索引的大小。我们会给索引文件预先分配空间，然后在日志滚动时收缩它。
         * 一般情况下你不需要改动这个设置。
         * */
        //topicConfig.put(TopicConfig.SEGMENT_INDEX_BYTES_CONFIG,"10485760");

        /**  从预订的段滚动时间中减去最大的随机抖动，避免段滚动时的惊群（thundering herds）  */
        //topicConfig.put(TopicConfig.SEGMENT_JITTER_MS_CONFIG,"0");

        /** 此项用户控制kafka强制日志滚动时间，在此时间后，即使段文件没有满，也会强制滚动，以保证持久化操作能删除或压缩就数据。默认7天 */
        topicConfig.put(TopicConfig.SEGMENT_MS_CONFIG,"604800000");
        /**
         * 是否把一个不在isr中的副本被选举为leader作为最后手段，即使这样做会带来数据损失
         * */
        topicConfig.put(TopicConfig.UNCLEAN_LEADER_ELECTION_ENABLE_CONFIG,"false");

        NewTopic newTopic=new NewTopic(name,numPartitions,replicationFactor);
        newTopic.configs(topicConfig);
        CreateTopicsOptions options=new CreateTopicsOptions();
        options.timeoutMs(6000);
        CreateTopicsResult result=client.createTopics(ImmutableList.of(newTopic),options);
        for(Map.Entry<String, KafkaFuture<Void>> e : result.values().entrySet()){
            KafkaFuture<Void> future= e.getValue();
            future.get();
            boolean success=!future.isCompletedExceptionally();
            if(logger.isInfoEnabled()&&success){
                logger.info("已成功创建Kafka topic "+name+" ,分区 "+numPartitions+" ,副本 "+replicationFactor);
            }
            return success;
        }

        return false;
    }


    public static Producer<String,String> getProducer(Properties properties) {
        Properties defaultProperties = new Properties();
        boolean flag = null == properties || properties.isEmpty();
        if (flag){
            defaultProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"81.71.64.120:9092");
            defaultProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            defaultProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
            defaultProperties.put(ProducerConfig.ACKS_CONFIG, "all");
            defaultProperties.put(ProducerConfig.RETRIES_CONFIG, 3);
            defaultProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 323840);
            defaultProperties.put(ProducerConfig.LINGER_MS_CONFIG, 10);
            defaultProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
            defaultProperties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 3000);
        }
        // 创建一个ProducerRecord，该对象封装的是一个消息，然后通过producer进行发送，最后得到一个Future
        // 对象，并通过该对象获取发送之后的元数据信息
        /*Future<RecordMetadata> future = producer
                .send(new ProducerRecord<>(topic, key, value));*/
        return new KafkaProducer<String, String>(flag ? defaultProperties : properties);
     /*   RecordMetadata metadata = future.get();
        System.out.println(
                "topic: " + metadata.topic() + ", partition: " + metadata.partition() + ", offset: "
                        + metadata.offset());*/
    }

}

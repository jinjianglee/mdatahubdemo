package com.dh.common.util;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.model.*;
import com.dh.datahub.entity.SosOrder;
import com.dh.datahub.service.ISosOrderService;
import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import com.kafka.KafkaUtil;
import org.apache.kafka.clients.producer.*;
import org.jboss.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.Future;

/**
 * @author KaiFa
 */
@Component
public class PushDataUtil {
    private static String DATAHUB_ENDPOINT="http://dh-cn-hangzhou.aliyuncs.com";
    private static String ACCESS_ID="LTAI4G6TkVfPGVPsMTRr5kNm";
    private static String ACCESS_KEY="Ass9BNVchd2ZxveDqBsOkYjZW29bax";
    public ISosOrderService sosOrderService;
    private final static Logger logger= Logger.getLogger(PushDataUtil.class);
    @Scheduled(cron = "*/30 * * * * ?")
    public void pushSosOrderData(){
        this.sosOrderService = (ISosOrderService)ContextUtil.getBean(ISosOrderService.class);
        System.out.println("QQQQQQQQQQQQ");
        DatahubClient client = DatahubClientBuilder.newBuilder().setDatahubConfig(new DatahubConfig(DATAHUB_ENDPOINT,new AliyunAccount(ACCESS_ID,ACCESS_KEY),true)).build();
        List<SosOrder> sosOrders = sosOrderService.getSosOrdersByDate("2020-09-08","4854ab8e-c9d5-4ef1-aee5-4f8b50bcbec5");
        sosOrders.forEach(sosOrder -> {
            putRecordsByShard(client,sosOrder);
        });
    }

    private void putRecordsByShard(DatahubClient client, SosOrder sosOrder){
        /**
         * or get record schema from server
         * RecordSchema schema = client.getTopic("projectName", "topicName").getRecordSchema();
         */
        RecordSchema schema = client.getTopic("order_project", "order_data").getRecordSchema();
        Gson gson = new Gson();
        Map map = gson.fromJson(gson.toJson(sosOrder),Map.class);
        List<RecordEntry> recordEntries = new ArrayList<>();
        RecordEntry entry = new RecordEntry();
        entry.addAttribute("key1", "value1");
        entry.addAttribute("key2", "value2");
        TupleRecordData data = new TupleRecordData(schema);
        data.getRecordSchema().getFields().forEach(field -> {
            String s = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getName());
            data.setField(field.getName(),map.get(s)==null?"XXX":map.get(s).toString());
        });
        entry.setRecordData(data);
        recordEntries.add(entry);
        // put tuple records by shard
        client.putRecordsByShard("order_project", "order_data", "0", recordEntries);
    }

    public static void main(String[] args) {
        try{
            Producer<String,String> producer = KafkaUtil.getProducer(null);
            for(int i=0;i<10000;i++){
                Thread.sleep(10);
                Future future = producer.send(new ProducerRecord<>("test-topic","testKey"+i,"testValue"+i));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }




}

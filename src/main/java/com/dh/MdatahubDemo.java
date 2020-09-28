package com.dh;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.model.*;
import com.dh.datahub.entity.SosOrder;
import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MdatahubDemo {
    private static String DATAHUB_ENDPOINT="http://dh-cn-hangzhou.aliyuncs.com";
    private static String ACCESS_ID="LTAI4G6TkVfPGVPsMTRr5kNm";
    private static String ACCESS_KEY="Ass9BNVchd2ZxveDqBsOkYjZW29bax";

    static ConfigurableApplicationContext APPLICATION;
    static {
        System.out.println("容器启动！！！");
        Thread.currentThread().setContextClassLoader(MdataHubApplication.class.getClassLoader());
        APPLICATION = SpringApplication.run(MdataHubApplication.class);
    }
    public static void main(String[] args) {
      /*  DatahubClient client = DatahubClientBuilder.newBuilder().setDatahubConfig(new DatahubConfig(DATAHUB_ENDPOINT,new AliyunAccount(ACCESS_ID,ACCESS_KEY),true)).build();
        RecordSchema schema = new RecordSchema();
        schema.addField(new Field("commodity", FieldType.STRING));
        schema.addField(new Field("gmtdate", FieldType.STRING));
        putRecordsByShard(client);*/
    }

}

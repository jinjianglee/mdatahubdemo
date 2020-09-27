package com.dh;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author KaiFa
 */
@MapperScan("com.dh.**.mapper")
@EnableScheduling
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class} )
public class MdataHubApplication {
    public static ConfigurableApplicationContext APPLICATION;

    public static void main(String[] args){
        APPLICATION = SpringApplication.run(MdataHubApplication.class, args);
    }


}

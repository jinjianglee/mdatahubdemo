package com.dh.common.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * 数据源管理
 *
 * @author Nevernow
 * @date 15:06 2020/3/10
 */
@Component
public class ManageDataSource {
    /**
     * log Object
     */
    private static Logger log = LoggerFactory.getLogger(ManageDataSource.class);
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void init() {
        log.info("数据库连接加载:{}",((DynamicRoutingDataSource) dataSource).getCurrentDataSources().keySet());
    }
}

package com.hjcc.ydItem.dataSport.config;/**
 Created by 胡杰 on 2019/3/30. */

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * FileName: com.hjcc.ydItem.dataSport.config.DataSourceConfig.java
 * Author: Jeremy_Hu
 * Date: 2019/3/30 下午5:02
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.user")
public class DataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);


    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private String Type;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private String connectionProperties;

    private String passwordCallbackClassName;


    @Bean
    @Qualifier("userDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource primaryUserDate(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setConnectionProperties(connectionProperties);
       // datasource.setDbType(Type);
        try {
            datasource.setPasswordCallbackClassName(passwordCallbackClassName);
        } catch (Exception e) {
            logger.error("druid configuration initialization passwordCallbackClassName", e);
        }
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("========druid configuration initialization filter========", e);
        }
        return datasource;
    }

}


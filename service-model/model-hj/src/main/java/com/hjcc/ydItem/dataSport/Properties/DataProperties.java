package com.hjcc.ydItem.dataSport.Properties;/**
 Created by 胡杰 on 2019/3/30. */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FileName: com.hjcc.ydItem.dataSport.Properties.DataProperties.java
 * Author: Jeremy_Hu
 * Date: 2019/3/30 下午6:29
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.user")
public class DataProperties {

    private String dbUrl;

    private String username;

    private String password;

    private String driverClassName;

    private String dbType;

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
}

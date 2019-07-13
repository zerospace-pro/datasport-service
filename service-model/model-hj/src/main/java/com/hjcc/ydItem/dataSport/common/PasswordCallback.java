package com.hjcc.ydItem.dataSport.common;/**
 Created by 胡杰 on 2019/3/30. */

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * FileName: com.hjcc.ydItem.dataSport.common.PasswordCallback.java
 * Author: Jeremy_Hu
 * Date: 2019/3/30 下午6:32
 */
public class PasswordCallback extends DruidPasswordCallback {

    private static final Logger log= LoggerFactory.getLogger(PasswordCallback.class);

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String password = (String) properties.get("password");
        String publickey = (String) properties.get("publickey");
        try {
            String dbpassword = ConfigTools.decrypt(publickey, password);
            setPassword(dbpassword.toCharArray());
        } catch (Exception e) {
            log.error("Druid ConfigTools.decrypt", e);
        }
    }
}

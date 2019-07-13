package com.hjcc.ydItem.dataSport.config;/**
 Created by 胡杰 on 2019/3/30. */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * FileName: com.hjcc.ydItem.dataSport.config.UserDataSourceConfig.java
 * Author: Jeremy_Hu
 * Date: 2019/3/30 下午5:12
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory",
transactionManagerRef = "userTransactionManager",
basePackages = {"com.hjcc.ydItem.dataSport.domain.repository"})
public class UserDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;


    @Autowired
    @Qualifier("userDataSource")
    private DataSource userDataSource;


    @Bean(name = "userEntityManagerFactoryBean")
    //@Primary
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(userDataSource)
                .properties(getVendorProperties(userDataSource))
                .packages("com.hjcc.ydItem.dataSport.domain.entity") //设置实体类所在位置
                .persistenceUnit("userPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }



    @Bean(name = "userEntityManagerFactory")
    @Primary
    public EntityManagerFactory userEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.userEntityManagerFactoryBean(builder).getObject();
    }




    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "userTransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(userEntityManagerFactory(builder));
    }

}

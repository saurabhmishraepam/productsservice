package com.epam.productsreview.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Created by saurabh on 2/3/19.
 */

@Profile("test")
@Configuration
public class TestDataSourceConfiguration {



    @Value("${test.spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${test.spring.datasource.url}")
    private String url;
    @Value("${test.spring.datasource.username}")
    private String username;
    @Value("${test.spring.datasource.password}")
    private String password;

    @Bean
    public DataSource createDataSource() {
        DataSourceBuilder dsBuilder = DataSourceBuilder
                .create().driverClassName(driverClass).url(url).username(username).password(password);
        return dsBuilder.build();
    }

}

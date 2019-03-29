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

@Configuration
@Profile("dev")
public class DataSourceConfiguration {

    @Value("${spring.datasource.driverClassName}")
    private String driverClass;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    @Bean
    public DataSource createDataSource() {
        DataSourceBuilder dsBuilder = DataSourceBuilder

                .create().driverClassName(driverClass).url(url).username(username).password(password);
        return dsBuilder.build();
    }



}

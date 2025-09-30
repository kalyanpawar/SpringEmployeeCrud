package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${app.use-in-memory-db}")
    private boolean useInMemoryDb;

    @Bean
    public DataSource dataSource() {
        if (useInMemoryDb) {
            return DataSourceBuilder.create()
                    .driverClassName("org.h2.Driver")
                    .url("jdbc:h2:mem:employee_db")
                    .username("sa")
                    .password("")
                    .build();
        } else {
            return DataSourceBuilder.create()
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .url("jdbc:mysql://localhost:3306/jdbc_db")
                    .username("root")
                    .password("root")
                    .build();
        }
    }
}
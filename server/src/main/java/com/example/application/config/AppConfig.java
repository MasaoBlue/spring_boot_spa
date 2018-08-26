package com.example.application.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.infrastructure.TaskRepository;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class AppConfig {
    @Autowired
    private Environment env;
    
    @Bean
    public TaskRepository TaskRepository() {
        return new TaskRepository();
    }
    
    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Bean
    public JdbcTemplate jdbcTemplateTest(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver"));
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        return dataSource;
    }
}

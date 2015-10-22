package br.com.gsw.example.liquibase.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application using Spring Boot
 * To run application run method main or mvn spring-boot:run
 */
@ComponentScan("br.com.gsw.example.liquibase")
@EntityScan("br.com.gsw.example.liquibase")
@EnableJpaRepositories("br.com.gsw.example.liquibase")
@EnableTransactionManagement
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    public static void main(final String[] args) {
        SpringApplication.run(WebApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }
}
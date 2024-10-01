package com.anderfolg.daorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories(basePackages = "com.anderfolg.daorest.repo")
@EntityScan(basePackages = "com.anderfolg.daorest.entities")
@PropertySource("classpath:application.properties")
public class DaoRestApplication {

    public static void main( String[] args ) {
        SpringApplication.run(DaoRestApplication.class, args);
    }

}

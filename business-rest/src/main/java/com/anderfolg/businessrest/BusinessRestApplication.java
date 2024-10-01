package com.anderfolg.businessrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-business.yml")
public class BusinessRestApplication {

    public static void main( String[] args ) {
        SpringApplication.run(BusinessRestApplication.class, args);
    }

}

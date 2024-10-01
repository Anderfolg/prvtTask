package com.anderfolg.regpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RegPaymentApplication {

    public static void main( String[] args ) {
        SpringApplication.run(RegPaymentApplication.class, args);
    }

}

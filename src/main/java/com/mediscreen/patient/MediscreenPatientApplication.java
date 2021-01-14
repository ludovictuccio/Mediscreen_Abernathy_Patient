package com.mediscreen.patient;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class MediscreenPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediscreenPatientApplication.class, args);
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}

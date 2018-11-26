package com.softech.sbdata;

import com.softech.sbdata.entities.Customer;
import com.softech.sbdata.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SbDataJpaCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbDataJpaCrudApplication.class, args);
    }


}

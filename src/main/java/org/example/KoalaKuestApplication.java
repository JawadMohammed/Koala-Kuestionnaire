package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages = "org.example")
//@EnableJpaRepositories(basePackages = "org.example.models")
//@EntityScan(basePackages = "org.example.models")

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.models")
@EntityScan("org.example.models")

public class KoalaKuestApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoalaKuestApplication.class, args);
    }
}
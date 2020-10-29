package br.ufrn.imd.groupapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class GroupappApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupappApplication.class, args);
    }

}

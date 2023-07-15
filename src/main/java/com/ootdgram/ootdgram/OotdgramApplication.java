package com.ootdgram.ootdgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OotdgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(OotdgramApplication.class, args);
    }

}

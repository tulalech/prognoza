package pl.lech.prognoza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PrognozaApplication {

    public static void main(String[] args) {

        SpringApplication.run(PrognozaApplication.class, args);
    }

}

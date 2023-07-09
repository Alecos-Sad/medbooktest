package by.sadovnick.slave2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class Slave2Application {
    public static void main(String[] args) {
        SpringApplication.run(Slave2Application.class, args);
    }
}

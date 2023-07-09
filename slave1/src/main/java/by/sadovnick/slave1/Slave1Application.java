package by.sadovnick.slave1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class Slave1Application {
    public static void main(String[] args) {
        SpringApplication.run(Slave1Application.class, args);
    }
}

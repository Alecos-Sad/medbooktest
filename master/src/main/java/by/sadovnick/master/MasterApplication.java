package by.sadovnick.master;

import by.sadovnick.master.service.MasterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class MasterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MasterApplication.class);
        MasterService masterService = context.getBean(MasterService.class);
        masterService.processStrings(new String[]{"Sadovnick", "Alex", "Demo"});
    }
}

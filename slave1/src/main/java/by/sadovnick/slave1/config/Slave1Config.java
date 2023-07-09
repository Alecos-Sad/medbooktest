package by.sadovnick.slave1.config;

import by.sadovnick.slave1.service.Slave1Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

@Configuration
public class Slave1Config {
    @Bean
    public MessageChannel slave1InputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow slave1Flow(Slave1Service slave1Service) {
        return IntegrationFlows.from(slave1InputChannel())
                .handle(slave1Service, "process")
                .channel(outputChannel())
                .get();
    }
}

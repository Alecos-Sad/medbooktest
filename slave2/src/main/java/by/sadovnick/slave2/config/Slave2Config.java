package by.sadovnick.slave2.config;

import by.sadovnick.slave2.service.Slave2Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

@Configuration
public class Slave2Config {
    @Bean
    public MessageChannel slave2InputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow slave2Flow(Slave2Service slave2Service) {
        return IntegrationFlows.from(slave2InputChannel())
                .handle(slave2Service, "process")
                .channel(outputChannel())
                .get();
    }
}

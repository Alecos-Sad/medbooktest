package by.sadovnick.slave1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {
    @Bean
    public MessageChannel slave1InputChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel slave1OutputChannel(){
        return new DirectChannel();
    }
}

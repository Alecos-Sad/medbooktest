package by.sadovnick.slave2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel slave2InputChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel slave2OutputChannel(){
        return new DirectChannel();
    }
}
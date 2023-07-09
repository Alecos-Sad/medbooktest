package by.sadovnick.master.config;

import by.sadovnick.slave1.processor.Slave1Processor;
import by.sadovnick.slave2.processor.Slave2Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfiguration {

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow integrationFlow(Slave1Processor slave1Processor, Slave2Processor slave2Processor) {
        return IntegrationFlows.from(inputChannel())
                .split()
                .<String, String>route(payload -> payload.length() % 2 == 0 ? "slave2" : "slave1",
                        mapping -> mapping.subFlowMapping("slave1", sf -> sf.handle(slave1Processor))
                                .subFlowMapping("slave2", sf -> sf.handle(slave2Processor)))
                .aggregate()
                .get();
    }
}

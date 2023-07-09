package by.sadovnick.master.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
public class MasterConfig {

    @Bean
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel slave1InputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel slave2InputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow masterFlow() {
        return IntegrationFlows.from(outputChannel())
                .<Message<String>, String>route(m -> {
                    String slave = m.getHeaders().get("slave", String.class);
                    return slave;
                }, mapping -> mapping
                        .subFlowMapping("slave1", sf -> sf
                                .handle("process"))
                        .subFlowMapping("slave2", sf -> sf
                                .handle("process")))
                .channel("outputChannel")
                .get();
    }
}

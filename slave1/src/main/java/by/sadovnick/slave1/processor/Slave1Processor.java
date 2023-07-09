package by.sadovnick.slave1.processor;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class Slave1Processor {
    @ServiceActivator(inputChannel = "slave1InputChannel", outputChannel = "slave1OutputChannel")
    public String process(String input) {
        return input.toUpperCase();
    }

}


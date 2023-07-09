package by.sadovnick.slave2.processor;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class Slave2Processor {

    @ServiceActivator(inputChannel = "slave2InputChannel", outputChannel = "slave2OutputChannel")
    public String process(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}

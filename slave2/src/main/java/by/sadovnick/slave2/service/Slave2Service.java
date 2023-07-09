package by.sadovnick.slave2.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class Slave2Service {

    @ServiceActivator(inputChannel = "slave2InputChannel", outputChannel = "outputChannel")
    public String processString(String input) {
        String processedString = new StringBuilder(input).reverse().toString();
        System.out.println("Slave2: " + processedString);
        return processedString;
    }
}

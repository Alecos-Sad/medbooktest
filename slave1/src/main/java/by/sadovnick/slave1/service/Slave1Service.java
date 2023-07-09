package by.sadovnick.slave1.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class Slave1Service {


    @ServiceActivator(inputChannel = "slave1InputChannel", outputChannel = "outputChannel")
    public String processString(String input) {
        String processedString = input.toUpperCase();
        System.out.println("Slave1: " + processedString);
        return processedString;
    }
}


package by.sadovnick.master.service;

import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MasterService {

    private final MessageChannel outputChannel;
    private final List<String> processedStrings;

    public MasterService() {
        this.outputChannel = new DirectChannel();
        this.processedStrings = new ArrayList<>();
    }

    public void processStrings(String[] input) {
        for (int i = 0; i < input.length; i++) {
            String message = input[i];
            Message<String> stringMessage = MessageBuilder.withPayload(message).build();
            if (i % 2 == 0) {
                outputChannel.send(MessageBuilder.fromMessage(stringMessage).setHeader("slave", "slave1").build());
            } else {
                outputChannel.send(MessageBuilder.fromMessage(stringMessage).setHeader("slave", "slave2").build());
            }
        }
        synchronized (processedStrings) {
            try {
                processedStrings.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        processedStrings.toArray(new String[0]);
    }

    public void processResult(String result) {
        synchronized (processedStrings) {
            processedStrings.add(result);
            if (processedStrings.size() == 2) {
                processedStrings.notify();
            }
        }
    }
}
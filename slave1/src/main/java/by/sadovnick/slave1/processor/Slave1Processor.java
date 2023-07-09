package by.sadovnick.slave1.processor;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class Slave1Processor implements GenericHandler<Message<String>> {

    @Override
    public Object handle(Message<String> message, MessageHeaders headers) {
        return reverseString(message.getPayload());
    }

    private String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}


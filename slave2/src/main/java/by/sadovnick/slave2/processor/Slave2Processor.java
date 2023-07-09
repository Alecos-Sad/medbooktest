package by.sadovnick.slave2.processor;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class Slave2Processor implements GenericHandler<Message<String>> {

    @Override
    public Object handle(Message<String> message, MessageHeaders headers) {
        String input = message.getPayload();
        String output = input.toUpperCase();
        return output;
    }
}

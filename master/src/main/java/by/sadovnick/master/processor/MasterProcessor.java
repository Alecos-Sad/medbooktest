package by.sadovnick.master.processor;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MasterProcessor {
    String process(String[] input);
}

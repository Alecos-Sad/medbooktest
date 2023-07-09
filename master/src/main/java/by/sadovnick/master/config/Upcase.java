package by.sadovnick.master.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Collection;

@MessagingGateway
public interface Upcase {

    @Gateway(requestChannel = "upcaseCh")
    Collection<String> upcase(Collection<String> strings);
}

package by.sadovnick.master.processor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;

@AllArgsConstructor
@Getter
@Setter
public class MasterProcessor extends AbstractMessageSplitter {

    @Override
    protected Object splitMessage(Message<?> message) {
        String[] inputArray = (String[]) message.getPayload();
        String[] outputArray = new String[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            String input = inputArray[i];
            int index = i + 1;
            if (index % 2 == 0){
                outputArray[i] = (String) sendMessageToSlave2(input);
            } else {
                outputArray[i] = (String) sendMessageToSlave1(input);
            }
        }
        return outputArray;
    }

    private Object sendMessageToSlave1(String input){

        return null;
    }

    private Object sendMessageToSlave2(String output){

        return null;
    }
}

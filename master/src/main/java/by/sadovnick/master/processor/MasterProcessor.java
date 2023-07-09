package by.sadovnick.master.processor;

import by.sadovnick.slave1.processor.Slave1Processor;
import by.sadovnick.slave2.processor.Slave2Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;

@MessageEndpoint
public class MasterProcessor {

    private final Slave1Processor slave1Processor;
    private final Slave2Processor slave2Processor;

    @Autowired
    public MasterProcessor(Slave1Processor slave1Processor, Slave2Processor slave2Processor) {
        this.slave1Processor = slave1Processor;
        this.slave2Processor = slave2Processor;
    }

    @Splitter(inputChannel = "inputChannel", outputChannel = "splitterChannel")
    public List<String> splitInput(String input) {
        List<String> items = new ArrayList<>();
        for (String item : input.split(",")) {
            items.add(item.trim());
        }
        return items;
    }

    @Aggregator(inputChannel = "slave1OutputChannel", outputChannel = "outputChannel",
            correlationStrategy = "correlationStrategy", releaseStrategy = "releaseStrategy")
    public List<String> aggregateOutput(List<String> results) {
        return results;
    }

    @Splitter(inputChannel = "splitterChannel", outputChannel = "slave1Channel")
    public boolean routeToSlave1(String item) {
        return isOddIndex(item);
    }

    @Splitter(inputChannel = "splitterChannel", outputChannel = "slave2Channel")
    public boolean routeToSlave2(String item) {
        return !isOddIndex(item);
    }

    @MessageEndpoint
    static class Slave1Processor {

        @Autowired
        public Slave1Processor() {
        }

        @Splitter(inputChannel = "slave1Channel", outputChannel = "slave1OutputChannel")
        public String invertCase(String item) {
            return new StringBuilder(item).reverse().toString();
        }
    }

    @MessageEndpoint
    static class Slave2Processor {

        @Autowired
        public Slave2Processor() {
        }

        @Splitter(inputChannel = "slave2Channel", outputChannel = "slave2OutputChannel")
        public String toUpperCase(String item) {
            return item.toUpperCase();
        }
    }

    private boolean isOddIndex(String item) {
        int index = Integer.parseInt(item.substring(4));
        return index % 2 != 0;
    }

    private Object correlationStrategy(Message<?> message) {
        return "aggregation";
    }

    private boolean releaseStrategy(List<String> results) {
        return results.size() == 2;
    }
}

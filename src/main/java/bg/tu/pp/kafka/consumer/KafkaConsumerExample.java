package bg.tu.pp.kafka.consumer;

import bg.tu.pp.kafka.common.CommonSettings;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

public class KafkaConsumerExample implements CommonSettings {
    private static final String READ_CMD = "read";
    private static final String STOP_CMD = "stop";
    private static final int CUT_OFF_OFFSET = READ_CMD.length();

    private static List<KafkaConsumer<String,String>> consumers = new ArrayList<>(5);
    private static List<ConsumerThread> threads = new ArrayList<>(5);

    public static void main(String[] args) {
        initKafkaConsumer("first-app", true);//0
        initKafkaConsumer("first-app", true);//1

        Scanner scanner = new Scanner(System.in);
        String command = getCommand(scanner);

        while (!EXIT_CMD.equals(command)) {
            if (command.startsWith(READ_CMD)) {
                read(0);
            } else if (STOP_CMD.equals(command)) {
                threads.forEach((t) -> {
                    t.stopReading();
                });
            } else {
                System.out.println("unknown command: "+command+" - Please, enter a valid command!");
            }
            command = getCommand(scanner);
        }
        System.out.println("Exiting...");
        consumers.forEach((c) -> {
            c.close();
        });
    }

    private static String getCommand(Scanner scanner) {
        System.out.println("Please, enter a command: ");
        return scanner.nextLine();
    }

    private static void initKafkaConsumer(String groupId, boolean startFromBeginning) {
        Properties properties = new Properties();

        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        properties.put("enable.auto.commit","true");
        properties.put("auto.commit.interval.ms", 1000);

        properties.put("group.id", groupId);

        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        if (startFromBeginning) {
            ConsumerRebalanceListener listener = new ConsumerRebalanceListenerExample(consumer);
            consumer.subscribe(Arrays.asList(TOPIC_NAME), listener);
        } else {
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
        }

        consumers.add(consumer);

        consumer.poll(Duration.ofSeconds(0l));
    }

    private static void read(int consumerIndex) {
        System.out.println("Reading...");
        int i=0;

        for (KafkaConsumer<String,String> consumer : consumers) {
            ConsumerThread ct = new ConsumerThread(i++, consumer);

            System.out.println(ct.getConsumerId());

            ct.start();
            threads.add(ct);
        }
    }

}

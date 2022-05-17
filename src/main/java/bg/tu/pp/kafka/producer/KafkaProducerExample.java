package bg.tu.pp.kafka.producer;

import bg.tu.pp.kafka.common.CommonSettings;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.*;
import java.util.concurrent.Future;

public class KafkaProducerExample implements CommonSettings {

    private static KafkaProducer<String,String> producer;

    public static void main(String[] args) {
        initKafkaProducer();

        Scanner scanner = new Scanner(System.in);
        String command = getCommand(scanner);

        while (!EXIT_CMD.equals(command)) {
            if (command.startsWith(MESSAGE_CMD)) {
                String message = command.substring(CUT_OFF_OFFSET);
                sendKafkaMessage(message);
            } else {
                System.out.println("unknown command: "+command+" - Please, try again:");
            }

            command = getCommand(scanner);
        }
        System.out.println("Exiting...");
        producer.close();
    }

    private static String getCommand(Scanner scanner) {
        System.out.println("Please, enter a command: ");
        return scanner.nextLine();
    }

    private static void initKafkaProducer() {
        Properties properties = new Properties();

        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("request.timeout.ms", 2000);

        System.out.println("Initializing kafka connection");
        producer = new KafkaProducer<>(properties);
    }

    private static void sendKafkaMessage(String inputMessage) {
        int l = inputMessage.length();
        int i = l%6;
        String key = null;

        switch (i) {
            case 0: key = "aa";break;
            case 1: key = "b";break;
            case 2: key = "";break;
            case 3: key = "c";break;
        }

        ProducerRecord<String,String> message = new ProducerRecord<>(TOPIC_NAME, key, inputMessage);

        System.out.println("Sending message with key "+key+"...");
        Future<RecordMetadata> f = producer.send(message);

        try {
            RecordMetadata rm = f.get();
            System.out.println("Message sent: "+rm);
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }

}

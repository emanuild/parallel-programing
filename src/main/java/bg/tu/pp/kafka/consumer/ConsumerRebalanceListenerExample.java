package bg.tu.pp.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

public class ConsumerRebalanceListenerExample implements ConsumerRebalanceListener {

    private KafkaConsumer<String,String> consumer;

    public ConsumerRebalanceListenerExample(KafkaConsumer<String,String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        // nothing to do
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        consumer.seekToBeginning(partitions);
    }
}

package DataCollector;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.Future;

public class SynchronousProvider {
    private static KafkaProducer<String, String> producer;


    private static void initializeProducer() {
        Properties props = new Properties();
        //consumer group
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public static void Send(HashMap<String, String> data) {
        if (producer == null)
            initializeProducer();
        for (String key : data.keySet()) {
            Future<RecordMetadata> metadata = producer.send(new ProducerRecord<String, String>("quickstart-events", key, data.get(key)));
            System.out.println(metadata.isDone());
        }
        producer.close();
    }
}

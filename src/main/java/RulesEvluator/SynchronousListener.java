package RulesEvluator;

import DataCollector.CandleSerializer;
import Model.Candle;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class SynchronousListener {

    public static void main(String[] args) {
        initializeConsumer();
    }

    public static void initializeConsumer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", CandleDeserializer.class.getName());
        KafkaConsumer<String, Candle> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("quickstart-events"));
        while (true) {
            ConsumerRecords<String, Candle> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, Candle> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value().getOpen());
        }
    }
}

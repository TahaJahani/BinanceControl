import REST.DatabaseConnection;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class SynchronousListener {

    private static SynchronousListener instance;
    private KafkaConsumer<String, Candle> consumer;
    private CandleController controller = CandleController.getInstance();

    public static void main(String[] args) {
        getInstance().listenForCandles();
    }

    public static SynchronousListener getInstance() {
        if (instance == null)
            instance = new SynchronousListener();
        return instance;
    }

    public void initializeConsumer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", CandleDeserializer.class.getName());
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("quickstart-events"));
    }

    public void listenForCandles() {
        if (consumer == null)
            initializeConsumer();
        while (true) {
            ConsumerRecords<String, Candle> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, Candle> record : records) {
                Candle currentCandle = record.value();
                controller.addNewCandle(currentCandle);
            }
        }
    }
}

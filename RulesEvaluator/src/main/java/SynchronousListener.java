import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class SynchronousListener {

    private static SynchronousListener instance;
    private KafkaConsumer<String, String> consumer;
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
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("quickstart-events"));
    }

    public void listenForCandles() {
        if (consumer == null)
            initializeConsumer();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000 * 60));
            for (ConsumerRecord<String, String> record : records) {
                Candle currentCandle = new Gson().fromJson(record.value(), Candle.class);
                System.out.println("Offset: " + record.offset() + ", value: " +currentCandle);
                controller.addNewCandle(currentCandle);
                consumer.commitSync(); //For more consistency
            }
        }
    }
}

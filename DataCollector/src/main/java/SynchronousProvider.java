import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronousProvider {

    private static KafkaProducer<String, String> producer;
    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);


    private static void initializeProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public static void Send(Candle candle) {
        if (producer == null)
            initializeProducer();
        Future<RecordMetadata> metadata = producer.send(new ProducerRecord<String, String>("quickstart-events", "candle", new Gson().toJson(candle)));
        producer.flush();
    }

    public void run() {
        Runnable task = () -> {
            try {
                Candle candle = APIClient.getInstance().getLatestCandle(Candle.Symbol.BTCUSD);
                System.out.println("Candle received");
                Send(candle);
                System.out.println("Candle Data Sent: " + candle);
            }catch (Exception e){
                e.printStackTrace();
                producer.close();
            }
        };
        executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        new SynchronousProvider().run();
    }
}

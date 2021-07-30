import Model.Candle;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class SynchronousProvider {
    private static KafkaProducer<String, Candle> producer;


    private static void initializeProducer() {
        Properties props = new Properties();
        //consumer group
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", CandleSerializer.class.getName());
        producer = new KafkaProducer<>(props);
    }

    public static void Send(Candle candle) {
        if (producer == null)
            initializeProducer();
        Future<RecordMetadata> metadata = producer.send(new ProducerRecord<String, Candle>("quickstart-events", "candle", candle));
        producer.flush();
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Getting candle");
                Candle candle = APIClient.getInstance().getLatestCandle(Candle.Symbol.BTCUSD);
                System.out.println("Candle received");
                Send(candle);
                System.out.println("Data Sent");
                Thread.sleep(10 * 1000);
                //TODO: use executor service
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SynchronousProvider().run();
    }
}

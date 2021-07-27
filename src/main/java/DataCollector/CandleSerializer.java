package DataCollector;

import Model.Candle;
import org.apache.kafka.common.serialization.Serializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class CandleSerializer implements Serializer<Candle> {

    ByteArrayOutputStream byteStream;
    ObjectOutputStream objectStream;
    public CandleSerializer() {
        try {
            this.byteStream = new ByteArrayOutputStream();
            this.objectStream = new ObjectOutputStream(byteStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] serialize(String topic, Candle data) {
        try {
            objectStream.writeObject(data);
            objectStream.flush();
            return byteStream.toByteArray();
        }
        catch (IOException e) {
            throw new IllegalStateException("Can't serialize object: " + data, e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {
        try {
            byteStream.close();
            objectStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

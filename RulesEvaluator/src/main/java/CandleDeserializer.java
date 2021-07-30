import Model.Candle;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.*;
import java.util.Map;

public class CandleDeserializer implements Deserializer<Candle> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Candle deserialize(String s, byte[] bytes) {
        //TODO: write better deserializer
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            Candle candle = (Candle) objectStream.readObject();
            byteStream.close();
            return candle;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Can't deserialize data");
        }
    }
}

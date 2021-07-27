package DataCollector;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIClient {

    private static APIClient instance = new APIClient();
    private final String BASE_URL = "https://api.bybit.com/v2/public/kline/list?interval=1&limit=1";

    public static APIClient getInstance() {
        return instance;
    }

    private String sendGetRequest() {
        StringBuilder loadedData = new StringBuilder();
        try {
            URL address = new URL(BASE_URL + "&from=" + (System.currentTimeMillis() / 1000 - 60); //+ "&symbol=" + symbol.name());
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = reader.readLine()) != null)
                    loadedData.append(inputLine);
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedData.toString();
    }



    public static void main(String[] args) {
        getInstance().sendGetRequest();
    }
}

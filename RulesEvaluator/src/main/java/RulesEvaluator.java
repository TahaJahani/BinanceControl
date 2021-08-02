import HibernateEntities.Notification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RulesEvaluator {

    private static final String FILE = "RulesEvaluator/src/main/java/Rules.json";
    private static ArrayList<Rule> readRules;

    public static void readRules() {
        try {
            File rulesFile = openFile();
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(rulesFile));
            String rulesJson = new String(inputStream.readAllBytes());
            readRules = new Gson().fromJson(rulesJson, new TypeToken<ArrayList<Rule>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkRules() {
        if (readRules == null)
            readRules();
        for (Rule rule : readRules) {
            System.out.println("Checking rule " + rule.getName() + "...");
            double SMA = CandleController.getInstance().calculateSMA(rule.getInterval(), rule.getItem());
            double price = CandleController.getInstance().getLastCandle().getItem(rule.getItem());
            try {
                if (rule.evaluate(SMA)) {
                    Notification notification = new Notification.Builder()
                            .ruleName(rule.getName())
                            .marketName("BiByte")
                            .price(price)
                            .build();
                    DatabaseHandler.saveNotification(notification);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static File openFile() throws IOException {
        File file = new File(FILE);
        if (!file.exists())
            file.createNewFile();
        return file;
    }
}

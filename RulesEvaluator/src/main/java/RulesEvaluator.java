import Model.Rule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jboss.jandex.Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RulesEvaluator {

    private static final String FILE = "src/main/java/Rules.json";

    public static ArrayList<Rule> readRules() {
        ArrayList<Rule> rules = null;
        try {
            File rulesFile = openFile();
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(rulesFile));
            String rulesJson = new String(inputStream.readAllBytes());
            rules = new Gson().fromJson(rulesJson, new TypeToken<ArrayList<Rule>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rules;
    }

    public static void checkRules() {
        ArrayList<Rule> rules = readRules();
        for (Rule rule : rules) {
            double SMA = CandleController.getInstance().calculateSMA(rule.getInterval(), rule.getItem());
            if (rule.evaluate(SMA)) {
                ;//TODO: insert database row
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

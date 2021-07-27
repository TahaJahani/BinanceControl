import DataCollector.SynchronousProvider;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to continue...");
        scanner.nextLine();
        HashMap<String, String> data = new HashMap<>();
        data.put("2021-07-26 12:40", "26$");
        data.put("2021-07-26 12:41", "27$");
        data.put("2021-07-26 12:42", "26.5$");
        SynchronousProvider.Send(data);
    }
}

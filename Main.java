import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {
    public static Map<String, String> map = new HashMap<String, String>();
    public static ArrayList<String> list = new ArrayList<>();
    public static String record;

    public static void main(String[] args) throws IOException {

        String[] data = new String[]{"C:\\Users\\cactus\\Downloads\\123.txt", "user_id=89210077293"};

        for (int i = 1; i < data.length; i++) {
            String[] pair = data[i].split("=");
            map.put(pair[0], pair[1]);
        }

        List<String> lines = Files.readAllLines(Paths.get(data[0]), StandardCharsets.UTF_8);

        for (String line : lines) {
            record = line;
            Record record1 = new Record();
            record1.convert(record);
            compareMap(record1.getData());
        }

        if (!list.isEmpty()) {
            list.forEach(record -> System.out.println(record));
        } else {
            System.out.println("Совпадений не найдено");
        }

    }

    public static void compareMap(Map<String, String> records) {
        int countCompare = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            for (Map.Entry<String, String> pair : records.entrySet()) {
                if (entry.getKey().equals(pair.getKey()) && entry.getValue().equals(pair.getValue())) {
                    countCompare++;
                }
            }
        }
        if (countCompare == map.size()) {
            list.add(record);
        }
    }
}

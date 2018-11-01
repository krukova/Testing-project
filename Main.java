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

    public static void main(String[] args) throws IOException {

        String[] data = new String[]{"C:\\Users\\cactus\\Downloads\\123.txt", "DownlinkPortID=4"}; //тестовые данные
        boolean flag = false;
        ArrayList<String> list = new ArrayList<>(); //массив для записи строк, подходящих по значению
        BufferedReader bufferedReader = new BufferedReader(new FileReader(data[0]));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.S");
        //сохрание всех аргументов в словарь
        for (int i = 1; i < data.length; i++) {
            String[] name = data[i].split("=");
            map.put(name[0], name[1]);
        }
        //запись всех строк из файла
        //List<String> lines = Files.readAllLines(Paths.get(data[0]), StandardCharsets.UTF_8);

        while (bufferedReader.ready())
        {
            String record = bufferedReader.readLine();
            String[] parametrs = record.split(";", 7);
            Record record1 = new Record();
            try {
                record1.setDate_time(dateFormat.parse(parametrs[0]));
                record1.setUser_id(Integer.parseInt(parametrs[1]));
                record1.setService(Integer.parseInt(parametrs[2]));
                record1.setProtocol(Integer.parseInt(parametrs[3]));
                record1.setUpload(Integer.parseInt(parametrs[4]));
                record1.setDownload(Integer.parseInt(parametrs[5]));
                record1.setMetadata(record1.convertString(parametrs[6]));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            for (Map.Entry<String, String> entry : map.entrySet()) {
                
            }



        }



    }
}

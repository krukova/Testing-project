import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {
    public static Map<String, String> map = new HashMap<String, String>();

    public static void main(String[] args) throws IOException {

        String[] data = new String[]{"C:\\Users\\cactus\\Downloads\\123.txt", "user_id=89210077293", "DownlinkPortID=4"}; //тестовые данные
        boolean flag = false;
        ArrayList<String> list = new ArrayList<>(); //массив для записи строк, подходящих по значению

        //сохрание всех аргументов в словарь
        for (int i = 1; i < data.length; i++) {
            String[] name = data[i].split("=");
            map.put(name[0], name[1]);
        }
        //запись всех строк из файла
        List<String> lines = Files.readAllLines(Paths.get(data[0]), StandardCharsets.UTF_8);

        //обработка данных
        for (String line : lines) {
            point:
            {
                int count=0;
                String[] strings = line.split(";", 7); //разделение каждого параметра
                for (Map.Entry<String, String> entry : map.entrySet()) { //проверка основных аргументов и подсчет
                    if (entry.getKey().equals("date_time") || entry.getKey().equals("user_id") || entry.getKey().equals("service") || entry.getKey().equals("protocol") || entry.getKey().equals("upload") || entry.getKey().equals("download")) {
                        switch (entry.getKey()) {
                            case "date_time": {
                                if (!strings[0].equals(entry.getValue())) //если не совпадают значения
                                    break point; //переход в начало цикла
                                else count++; //если совпадает параметр засчитывается пройденным
                                break;
                            }
                            case "user_id": {
                                if (!strings[1].equals(entry.getValue()))
                                    break point;
                                else count++;
                                break;
                            }
                            case "service": {
                                if (!strings[2].equals(entry.getValue()))
                                    break point;
                                else count++;
                                break;
                            }
                            case "protocol": {
                                if (!strings[3].equals(entry.getValue()))
                                    break point;
                                else count++;
                                break;
                            }
                            case "upload": {
                                if (!strings[4].equals(entry.getValue()))
                                    break point;
                                else count++;
                                break;
                            }
                            case "download": {
                                if (!strings[5].equals(entry.getValue()))
                                    break point;
                                else count++;
                                break;
                            }
                        }
                    }
                    boolean check = false;
                    if(count!=map.size()) //если остались не  пройденные параметры
                    {
                        strings[6] = strings[6].replaceAll("\\{|\\}|\"", ""); //разделение строчного представления объекта json
                        String[] str = strings[6].split(",");
                        for (String s : str) {
                            String[] pair = s.split(":");
                            if (pair[0].equals(entry.getKey())) //проверка не совпадения пары
                            {
                                if (!pair[0].equals(entry.getValue()))
                                    break;
                            } else
                                check = true;
                        }
                    }

                    if(!check) {
                        list.add(line); //добавление совпадающией строки
                        flag = true;
                    }
                }
            }
        }

        if (flag)
            list.forEach(number -> System.out.println(number)); //вывод строк
        else
            System.out.println("Совпадений не найдено");
    }
}

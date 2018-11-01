import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Record {

    private Map<String, String> data = new HashMap<String, String>();

    public Map<String, String> getData() {
        return data;
    }

    public void convert(String metadata) {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] parametrs = metadata.split(";", 7);
        data.put("date_time", parametrs[0]);
        data.put("user_id", parametrs[1]);
        data.put("service", parametrs[2]);
        data.put("protocol", parametrs[3]);
        data.put("download", parametrs[4]);
        if (!parametrs[6].equals(""))
            convertString(parametrs[6]);
    }

    public void convertString(String metadata) {
        String[] str = metadata.replaceAll("\\{|\\}|\"", "").split(",");
        for (String s : str) {
            String[] pair = s.split(":");
            data.put(pair[0], pair[1]);
        }
    }
}

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Record {
    private Date date_time;
    private int user_id;
    private int service;
    private int protocol;
    private int upload;
    private int download;
    private Map<String, String> metadata = new HashMap<String, String>();

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getUpload() {
        return upload;
    }

    public void setUpload(int upload) {
        this.upload = upload;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public HashMap<String, String> convertString(String metadata)
    {
        HashMap<String, String> map = new HashMap<String, String>();

        String[] str = metadata.replaceAll("\\{|\\}|\"", "").split(",");
        for (String s : str) {
            String[] pair = s.split(":");
            map.put(pair[0], pair[1]);
        }
        return map;
    }
}

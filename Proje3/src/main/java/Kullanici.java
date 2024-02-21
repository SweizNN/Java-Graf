
import com.fasterxml.jackson.databind.JsonNode;

public class Kullanici {
    public static HashTablosu hashTablosu =new HashTablosu();

    public HashTablosu getTable() {
        return hashTablosu;
    }
    public Kullanici(JsonNode jsonNode){
        hashTablosu.put("username", jsonNode.get("username"));
        hashTablosu.put("name", jsonNode.get("name"));
        hashTablosu.put("followers_count", jsonNode.get("followers_count"));
        hashTablosu.put("following_count", jsonNode.get("following_count"));
        hashTablosu.put("language", jsonNode.get("language"));
        hashTablosu.put("region", jsonNode.get("region"));
        hashTablosu.put("tweets", jsonNode.get("tweets"));
        hashTablosu.put("following", jsonNode.get("following"));
        hashTablosu.put("followers", jsonNode.get("followers"));
    }
}


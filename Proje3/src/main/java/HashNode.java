import com.fasterxml.jackson.databind.JsonNode;

public class HashNode {
    String data;
    JsonNode value;
   HashNode next;


    public HashNode(String data, JsonNode value) {
        this.data = data;
        this.value=value;
        this.next = null;
    }

}

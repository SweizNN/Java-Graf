import com.fasterxml.jackson.databind.JsonNode;

// class tipinde node oluşturmak için <T> kullandık
public class GrafNode<T> {
    JsonNode key;
    Kullanici k;
    GrafNode<Kullanici> next;

    public GrafNode(Kullanici k, JsonNode key){
        this.key=key;
        this.next=null;
        this.k=k;
    }

}

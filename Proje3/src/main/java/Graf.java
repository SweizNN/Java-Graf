import com.fasterxml.jackson.databind.JsonNode;

public class Graf {
    int dugumSayisi;//graphın boyutu
    GrafNode<Kullanici> dugum;
    public static int dugumBaglamaSayisi = 0;

    public Graf(int dugumSayisi) {
        this.dugumSayisi = dugumSayisi;
    }

    // teker teker bütün düğümleri birbirine bağlar
    public void addEdge(Kullanici k, JsonNode edge) {
        GrafNode<Kullanici> newNode;
        for (JsonNode j : edge) {
             newNode= new GrafNode<>(k, j);
            if(dugum==null){
                dugum=newNode;
            }
            else {
                GrafNode<Kullanici> dugumBagla = dugum;
                while(dugumBagla.next != null){
                    dugumBagla = dugumBagla.next;
                }
                dugumBagla.next = newNode;
                dugum=dugumBagla;
            }
            System.out.println(k.getTable().get("username") + "-->" + j);
            dugumBaglamaSayisi++;
        }
    }
}










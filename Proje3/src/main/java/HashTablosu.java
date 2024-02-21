import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.Objects;

// Hash tablosu sınıfı
class HashTablosu {
    private final HashNode[] HashTablo;
    private final int size = 128;



    private int indexBul(String data) {
        int adres = data.hashCode();
        return Math.abs(adres) % size;
    }


    public HashTablosu() {
        HashTablo = new HashNode[size];
    }

    // Hash tablosuna eleman ekleme
    public void put(String data, JsonNode value) {
        int index = indexBul(data);
        HashNode hashNode = new HashNode(data, value);
        // İlgili indeksteki bağlantılı listeye ekleme
            HashTablo[index] = hashNode;
            HashNode nodeBagla = HashTablo[index];
            while (nodeBagla.next != null) {
                nodeBagla = nodeBagla.next;
            }
            nodeBagla.next = hashNode;
    }



    // Hash tablosundan elemanı getirme
    public JsonNode get(String data) {
        int index = indexBul(data);
        HashNode node = HashTablo[index];
        // İlgili indeksteki bağlantılı listeyi kontrol etme
        while (node != null) {
            if (Objects.equals(node.data, data)) {
                return node.value;
            }
            node = node.next;
        }
        return null; // Eleman bulunamadı
    }


}
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper  objectMapper = new ObjectMapper();
            // JSON dosyasını oku ve JsonNode'a dönüştür
            JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\musta\\data50k.json"));
            Kullanici kullanici;
            Graf graf;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jsonNode.size(); i++) {
               kullanici = new Kullanici(jsonNode.get(i));
                    graf = new Graf(jsonNode.size()-i);
                    graf.addEdge(kullanici, Kullanici.hashTablosu.get("following"));
                    graf.addEdge(kullanici, Kullanici.hashTablosu.get("followers"));
                    arrayList.add(kullanici);
            }
            System.out.print("Düğümler kenarlar ile birbirine bağlandı, toplam düğüm bağlama sayısı : " + Graf.dugumBaglamaSayisi);

            String enFazlaGecenKelime1 = "";
            String enFazlaGecenKelime2 = "";
            int enFazlaGecenFrekans1;
            int enFazlaGecenFrekans2;
            String tempKelime ="";
            int tempSayi = 0;
            boolean isUseless;
            Scanner scanner = new Scanner(System.in);
            String[] stopWords={"ve","olarak","ile","de","da","ancak","ama","Ayrıca","profesyonel","ilk","aynı","fakat",
                    "bunlar","onlar","bunun","bu","daha","şu","önce","yine","bundan","onların","ilk","aynen","ın","tüm",
                    "ayrıca","sonra","önce","","bir","çok","tarafından","yer","en","olduğu","ilgili","birkaç","devam",
                    "önemli","sahiptir","sahiptir.","pek","vardır","bulunmaktadır","vardır.","bulunmaktadır.","özellikle",
                    "beraber","içinde","fazla","büyük","kuvvetleri","için","alır.","aldı.","oldu.","birçok","eleme","ise","onun","adlı","arasında","üyesi","almaktadır.","mezunudur.","iki","olan","an","görev","gibi","adı","başladı.","baskı","kez","değildir.",
                    "yaptı.","altı","iyi","kadar","ona","diğer","hem","her","beş","yeni","iyi","şey","üç","iki","süre","ona","son", "yaptı", "ikinci", "yapmıştır.", "bunu", "günümüzde", "nedeniyle", "arasındaki", "yüzden", "bugün", "ince", "çeşitli", "zamanda", "veya", "tamamlamıştır."};
            System.out.println("\n\n---------------------------------\n\n");

            String[][] ilgiAlan = new String[50000][3];

                for(int i = 0; i< jsonNode.size(); i++) {
                    String[] tweetler = jsonNode.get(i).get("tweets").toString().split(",");
                    enFazlaGecenFrekans1 = 0;
                    enFazlaGecenFrekans2 = 0;
                    for (String tweet : tweetler) {
                        tweet = tweet.substring(1, tweet.length() - 1);
                        String[] kelimeler = tweet.split(" ");
                        for (String kelime : kelimeler) {
                            // kelime gereksiz kelimeler arasında mı diye bakar öyle ise atlar kelimeyi
                            isUseless = false;
                            for (String uselesWords : stopWords)
                                if (Objects.equals(kelime.toLowerCase(Locale.ROOT), uselesWords)) {
                                    isUseless = true;
                                    break;
                                }
                            if (isUseless) {
                                continue;
                            }

                            tempKelime = kelime;
                            tempSayi = 0;
                            for (int l = 0; l < tweetler.length; l++) {
                                String tempTweet = tweetler[l].substring(1, tweetler[l].length() - 1);
                                String[] tempKelimeler = tempTweet.split(" ");
                                for (String kelimeSayac : tempKelimeler) {
                                    if (Objects.equals(kelime, kelimeSayac)) {
                                        tempSayi++;
                                    }
                                }
                            }
                            if (tempSayi > enFazlaGecenFrekans1) {
                                enFazlaGecenKelime1 = tempKelime;
                                enFazlaGecenFrekans1 = tempSayi;
                            } else if (tempSayi > enFazlaGecenFrekans2 && !Objects.equals(tempKelime, enFazlaGecenKelime1)) {
                                enFazlaGecenKelime2 = tempKelime;
                                enFazlaGecenFrekans2 = tempSayi;
                            }
                        }
                    }
                    // Sonuçları yazdır
                    System.out.println("Kullanıcı adı : " + jsonNode.get(i).get("username"));
                    System.out.println("Birinci ilgi alanı : '" + enFazlaGecenKelime1 + "', Frekans: " + enFazlaGecenFrekans1);
                    System.out.println("İkinci ilgi alanı : '" + enFazlaGecenKelime2 + "', Frekans: " + enFazlaGecenFrekans2 + "\n");

                    // kullanıcıların ilgi alanlarını teker teker diziye atıyoruz
                    ilgiAlan[i][0] = String.valueOf(jsonNode.get(i).get("username"));
                    ilgiAlan[i][1] = enFazlaGecenKelime1;
                    ilgiAlan[i][2] = enFazlaGecenKelime2;
                }

                // ilgi alanları benzer olan kullanıcıları ve ortak olan ilgi alanlarını getiriyor
            for(int k = 0; k < jsonNode.size(); k++){
                for(int m = k + 1; m < jsonNode.size(); m++){
                    if(ilgiAlan[k][1].equals(ilgiAlan[m][1]) || ilgiAlan[k][2].equals(ilgiAlan[m][2])){
                        System.out.println("Bu ikisinin ilgi alanı benzerdir : " + ilgiAlan[k][0] + " - " + ilgiAlan[m][0]);
                        if(ilgiAlan[k][1].equals(ilgiAlan[m][1])){
                            System.out.println("Benzer ilgi alanları : '" + ilgiAlan[k][1] +"'\n");
                        }else if(ilgiAlan[k][2].equals(ilgiAlan[m][2])){
                            System.out.println("Benzer ilgi alanları : '" + ilgiAlan[k][2] + "'\n");
                        }else if(ilgiAlan[k][1].equals(ilgiAlan[m][1]) || ilgiAlan[k][2].equals(ilgiAlan[m][2])){
                            System.out.println("Benzer ilgi alanlar : '" + ilgiAlan[k][1] +"', " + ilgiAlan[k][2] + "'\n");
                        }
                    }
                }
            }
            int sec = 1;
            while(sec != -1){
                System.out.print("\nLutfen aramak istediğiniz kullanıcı sırasını giriniz - (çıkış için ise '-1' giriniz) : ");
                sec = scanner.nextInt();
                if(sec == -1){
                    break;
                }
                    Kullanici kisi;
                    kisi = new Kullanici(jsonNode.get(sec));
                    JsonNode wow = Kullanici.hashTablosu.get("following");
                    JsonNode wow2 = Kullanici.hashTablosu.get("followers");

                System.out.println("\nGraf : ");
                    for(int i = 0, j = 0; i< wow.size() && j<wow2.size(); i++, j++){
                        System.out.println(wow.get(i) + " --> " +kisi.getTable().get("username") + " --> " + wow2.get(j));
                    }

                System.out.println("\nKullanıcı adı : " + kisi.getTable().get("username"));
                System.out.println("Adı : " + kisi.getTable().get("name"));
                System.out.println("Dil Kodu : " + kisi.getTable().get("language"));
                System.out.println("Bölge Kodu : " + kisi.getTable().get("region"));

                System.out.println("\n1. ilgi alanı : " + ilgiAlan[sec][1]);
                System.out.println("2. ilgi alanı : " + ilgiAlan[sec][2] + "\n");
                for(int i=0; i< jsonNode.size(); i++){
                    if(i == sec){
                        continue;
                    }
                    if(ilgiAlan[i][1].equals(ilgiAlan[sec][1]) || ilgiAlan[i][2].equals(ilgiAlan[sec][2])){
                        System.out.println("Bu kullanıcı ile benzer ilgi alanını sahip kullanıcı : " + ilgiAlan[i][0]);
                        System.out.println(ilgiAlan[sec][0] + "-->" + ilgiAlan[i][0]);
                        if(ilgiAlan[i][1].equals(ilgiAlan[sec][1])){
                            System.out.println("Benzer olan ilgi alanı : " + ilgiAlan[i][1]);
                        }else if(ilgiAlan[i][2].equals(ilgiAlan[sec][2])){
                            System.out.println("Benzer olan ilgi alanı : " + ilgiAlan[i][2]);
                        }
                        System.out.println("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
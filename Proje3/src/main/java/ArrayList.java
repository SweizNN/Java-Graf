import java.util.Arrays;

public class ArrayList {


    private Kullanici[] nesneler;
    private final int kapasite = 10;
        private int size;

        public ArrayList() {
            size = 0;
            nesneler = new Kullanici[kapasite];
        }


        public Kullanici get(int index) {
            if (index < 0 || index >= size) {
                System.out.println("Gecersiz index: "+index);
                System.exit(1);
            }
            return nesneler[index];
        }



    public void add(Kullanici nesne) {
        if (size == nesneler.length) {
            kapasiteDuzen();
        }
        nesneler[size++] = nesne;
    }


        private void kapasiteDuzen() {
            int yeniKapasite = (int)(nesneler.length * 1.5);
            nesneler = Arrays.copyOf(nesneler, yeniKapasite);
        }



    public Kullanici[] getNesneler() {
        return nesneler;
    }

    public void setNesneler(Kullanici[] nesneler) {
        this.nesneler = nesneler;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    }

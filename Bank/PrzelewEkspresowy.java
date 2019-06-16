package Bank;

public class PrzelewEkspresowy {
    private int id;
    private String tytul;
    private double kwota;
    private String numerKonta;


    public PrzelewEkspresowy(String tytul, String numerKonta, double kwota, int id) {
        this.id = id;
        this.tytul = tytul;
        this.kwota = kwota;
        this.numerKonta = numerKonta;
    }

    public boolean equals(PrzelewEkspresowy przelewEkspresowy) {
        return przelewEkspresowy.getID() == id;
    }

    public int getID() {
        return id;
    }

}
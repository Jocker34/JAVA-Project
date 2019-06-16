package Bank;



public class PrzelewNormalny extends PrzelewEkspresowy {
    private Data data;

    public PrzelewNormalny(String tytul, String numerKonta, double kwota, int id, Data data) {
        super(tytul, numerKonta, kwota, id);
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
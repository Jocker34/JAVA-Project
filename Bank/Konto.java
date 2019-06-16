package Bank;

public class Konto {

    private String login;
    private String haslo;
    private String telefon;

    private String numerKonta;
    private double stan;

    private Uprawnienia uprawnienia;

    public Konto(String login, String haslo, String telefon, String numerKonta, double stan, boolean przelewEkspresowy, boolean przelewNormalny, boolean wycofaniePrzelewu, boolean zmianaUprawnien) {
        this.login = login;
        this.haslo = haslo;
        this.telefon = telefon;
        this.numerKonta = numerKonta;
        this.stan = stan;
        this.uprawnienia = new Uprawnienia(przelewEkspresowy, przelewNormalny, wycofaniePrzelewu, zmianaUprawnien);
    }

    public boolean equals(Konto konto) {
        return this.login == konto.getLogin();
    }

    public String getLogin() {
        return login;
    }



    public double getStan() {
        return stan;
    }

    public Uprawnienia getUprawnienia() { return uprawnienia; }
}
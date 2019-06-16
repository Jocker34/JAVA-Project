package Bank;

import java.util.ArrayList;
import java.util.HashMap;


public class Aplikacja {


    private ArrayList<PrzelewEkspresowy> zleconePrzelewy = new ArrayList<>();
    private ArrayList<PrzelewNormalny> odwleczonePrzelewy = new ArrayList<>();
    private ArrayList<Konto> konta = new ArrayList<>();

    public ArrayList<Konto> getKonta() {
        return this.konta;
    }


    // ===========
    // WERYFIKACJA
    // ===========
    public void weryfikujPrzelew(double stanKonta, String tytul, String numerKonta, double kwota) throws AplikacjaException {

        if (tytul.length() > 30) {
            throw new AplikacjaException("Tytul zbyt dlugi.");
        }

        weryfikujNrKonta(numerKonta);

        if (kwota <= 0 || stanKonta < kwota) {
            throw new AplikacjaException("Kwota musi być większa od 0 i nie przekraczać stanu konta zleceniodawcy");
        }

    }


    public void weryfikujNrKonta(String numerKonta) throws AplikacjaException {
        if (numerKonta.length() != 26) {
            throw new AplikacjaException("Numer konta powinien mieć 26 znaków");
        }

        for (int i = 0; i < numerKonta.length(); i++) {
            if (!Character.isDigit(numerKonta.charAt(i))) {
                throw new AplikacjaException("Numer konta musi składać sie z samych cyfr");
            }
        }
    }


    // ==================
    // ZLECENIE PRZELEWÓW
    // ==================
    public PrzelewEkspresowy zlecPrzelewEkspresowy(Konto konto, String tytul, String numerKonta, double kwota, int id) throws AplikacjaException {

        weryfikujPrzelew(konto.getStan(), tytul, numerKonta, kwota);

        PrzelewEkspresowy nowyPrzelew = new PrzelewEkspresowy(tytul, numerKonta, kwota, id);

        this.zleconePrzelewy.add(nowyPrzelew);
        return nowyPrzelew;
    }

    public PrzelewNormalny zlecPrzelewNormalny(Konto konto, String tytul, String numerKonta, double kwota, int id, Data dataPrzelewu) throws AplikacjaException {

        weryfikujPrzelew(konto.getStan(), tytul, numerKonta, kwota);

        PrzelewNormalny nowyPrzelew = new PrzelewNormalny(tytul, numerKonta, kwota, id, dataPrzelewu);


        this.odwleczonePrzelewy.add(nowyPrzelew);
        return nowyPrzelew;
    }


    // ==================
    // WYCOFANIE PRZELEWU
    // ==================

    public void wycofajPrzelew(Konto konto, PrzelewNormalny przelewNormalny) throws AplikacjaException {
        if (!konto.getUprawnienia().isWycofaniePrzelewu()) {
            throw new AplikacjaException("Brak uprawnien do wycofania przelewu");
        }

        for (PrzelewNormalny przelew : odwleczonePrzelewy) {
            if (przelew.equals(przelewNormalny)) {
                odwleczonePrzelewy.remove(przelew);
                return;
            }
        }

        throw new AplikacjaException("Przelew nie istnieje lub został zaksięgowany");
    }



    public void zmienDatePrzelewu(PrzelewNormalny przelewNormalny, Data nowaData) {
        przelewNormalny.setData(nowaData);
    }




    // ===============
    // TWORZENIE KONTA
    // ===============
    public Konto stworzKonto(String login, String haslo, String telefon, String numerKonta, double stan, boolean przelewEkspresowy, boolean przelewNormalny, boolean wycofaniePrzelewu, boolean zmianaUprawnien) throws AplikacjaException {
        for (Konto konto : konta) {
            if (konto.getLogin().equals(login)) {
                throw new AplikacjaException("Bank.Konto o podanym loginie juz istnieje");
            }
        }

        weryfikujNrKonta(numerKonta);

        Konto konto = new Konto(login, haslo, telefon, numerKonta, stan, przelewEkspresowy, przelewNormalny, wycofaniePrzelewu, zmianaUprawnien);
        konta.add(konto);
        return konto;
    }






    public ArrayList<PrzelewEkspresowy> getZleconePrzelewy() {
        return zleconePrzelewy;
    }

    public ArrayList<PrzelewNormalny> getOdwleczonePrzelewy() {
        return odwleczonePrzelewy;
    }
}
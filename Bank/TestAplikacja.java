package Bank;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;


public class TestAplikacja {

    private Aplikacja app = new Aplikacja();

    @Test
    public void testStworzKontoZlyNumer() {

        Throwable e = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "2008039180292638915786726x";

        double stan = 559.64;

        try {
            app.stworzKonto(login, haslo, telefon, numerKonta, stan, false, false, false, false);
        } catch (AplikacjaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e instanceof AplikacjaException);

    }

    @Test
    public void testStworzKonto() {
        Throwable e = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "20080391802926389157867262";

        double stan = 559.64;


        try {
            Konto stworzoneKonto = app.stworzKonto(login, haslo, telefon, numerKonta, stan, false, false, false ,false);
            ArrayList<Konto> konta = app.getKonta();

            Assertions.assertTrue(konta.get(0).equals(stworzoneKonto));
        } catch (AplikacjaException ex) {
            e = ex;
        }

        Assertions.assertFalse(e instanceof AplikacjaException);
    }

    @Test
    public void testWeryfikujKonto() {
        Throwable e = null;
        String numerKonta = "20080391802926389157867262";

        try {
            app.weryfikujNrKonta(numerKonta);
        } catch (AplikacjaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e == null);
    }

    @Test
    public void testWeryfikujZleKonto() {
        Throwable e = null;
        String numerKonta = "200803918029263891578672as2";

        try {
            app.weryfikujNrKonta(numerKonta);
        } catch (AplikacjaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e instanceof AplikacjaException);
    }

    @Test
    public void testWeryfikujZlyPrzelew() {
        Throwable e = null;

        double stanKonta = 0;
        String tytul = "Przelew za czynsz";
        String numerKonta = "20080391802926389157867262";
        double kwota = 50;

        try {
            app.weryfikujPrzelew(stanKonta, tytul, numerKonta, kwota);
        } catch (AplikacjaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e instanceof AplikacjaException);
    }


    @Test
    public void testZlecPrzelewEkspresowy() {
        Throwable e = null;
        Object przelew = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "20080391802926389157867262";
        double stan = 559.64;
        int id = 157;

        String tytul = "Przelew za czynsz";
        String numerKontaOdbiorcy = "11876809869057641613549372";
        double kwota = 50.55;


        try {
            Konto konto = app.stworzKonto(login, haslo, telefon, numerKonta, stan, true, false, false, false);
            przelew = app.zlecPrzelewEkspresowy(konto, tytul, numerKontaOdbiorcy, kwota, id);
        } catch (AplikacjaException ex) {
            e = ex;
        }

        ArrayList<PrzelewEkspresowy> zleconePrzelewy = app.getZleconePrzelewy();

        Assertions.assertTrue(przelew instanceof PrzelewEkspresowy);
        Assertions.assertFalse(e instanceof AplikacjaException);
        Assertions.assertTrue(zleconePrzelewy.get(0).equals(przelew));


    }


    @Test
    public void testZlecPrzelewNormalny() {
        Throwable e = null;
        Object przelew = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "20080391802926389157867262";
        double stan = 559.64;
        int id = 254;

        String tytul = "Przelew za czynsz";
        String numerKontaOdbiorcy = "11876809869057641613549372";
        double kwota = 50.55;

        try {
            Konto konto = app.stworzKonto(login, haslo, telefon, numerKonta, stan, false, true, false, false);
            Data data = new Data(15, 5, 2019);
            przelew = app.zlecPrzelewNormalny(konto, tytul, numerKontaOdbiorcy, kwota, id, data);
        } catch (AplikacjaException ex) {
            e = ex;
        } catch (DataException ex) {
            e = ex;
        } catch(ParseException ex) {
            e = ex;
        }

        ArrayList<PrzelewNormalny> odwleczonePrzelewy = app.getOdwleczonePrzelewy();

        Assertions.assertTrue(przelew instanceof PrzelewNormalny);
        Assertions.assertFalse(e instanceof AplikacjaException);
        Assertions.assertFalse(e instanceof DataException);
        Assertions.assertTrue(odwleczonePrzelewy.get(0).equals(przelew));


    }

    @Test
    public void testZlecPrzelewNormalnyZlaData() {
        Throwable e = null;
        Object przelew = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "20080391802926389157867262";
        double stan = 559.64;
        int id = 254;

        String tytul = "Przelew za czynsz";
        String numerKontaOdbiorcy = "11876809869057641613549372";
        double kwota = 50.55;

        try {
            Konto konto = app.stworzKonto(login, haslo, telefon, numerKonta, stan, false, true, false, false);
            Data data = new Data(32, 5, 2019);

            przelew = app.zlecPrzelewNormalny(konto, tytul, numerKontaOdbiorcy, kwota, id, data);
        } catch (AplikacjaException ex) {
            e = ex;
        } catch (DataException ex) {
            e = ex;
        } catch(ParseException ex) {
            e = ex;
        }

        ArrayList<PrzelewNormalny> odwleczonePrzelewy = app.getOdwleczonePrzelewy();

        Assertions.assertFalse(przelew instanceof PrzelewNormalny);
        Assertions.assertFalse(e instanceof AplikacjaException);
        Assertions.assertTrue(e instanceof DataException);
        Assertions.assertTrue(odwleczonePrzelewy.isEmpty());

    }


    @Test
    public void testWycofajPrzelewNormalny() {
        Throwable e = null;
        PrzelewNormalny przelew = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "20080391802926389157867262";
        double stan = 559.64;
        int id = 254;

        String tytul = "Przelew za czynsz";
        String numerKontaOdbiorcy = "11876809869057641613549372";
        double kwota = 50.55;

        try {
            Konto konto = app.stworzKonto(login, haslo, telefon, numerKonta, stan, false, true, true, false);
            Data data = new Data(15, 5, 2019);
            przelew = app.zlecPrzelewNormalny(konto, tytul, numerKontaOdbiorcy, kwota, id, data);


            ArrayList<PrzelewNormalny> odwleczonePrzelewy = app.getOdwleczonePrzelewy();

            Assertions.assertTrue(odwleczonePrzelewy.get(0).equals(przelew));
            app.wycofajPrzelew(konto, przelew);
            Assertions.assertTrue(odwleczonePrzelewy.isEmpty());

        } catch (AplikacjaException ex) {
            e = ex;
        } catch (DataException ex) {
            e = ex;
        } catch(ParseException ex) {
            e = ex;
        }


        Assertions.assertTrue(e == null);

    }


    @Test
    public void testWycofajPrzelewBezUprawnien() {
        Throwable e = null;
        PrzelewNormalny przelew = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "20080391802926389157867262";
        double stan = 559.64;
        int id = 254;

        String tytul = "Przelew za czynsz";
        String numerKontaOdbiorcy = "11876809869057641613549372";
        double kwota = 50.55;

        try {
            Konto konto = app.stworzKonto(login, haslo, telefon, numerKonta, stan, false, true, false, false);
            Data data = new Data(15, 5, 2019);
            przelew = app.zlecPrzelewNormalny(konto, tytul, numerKontaOdbiorcy, kwota, id, data);

            app.wycofajPrzelew(konto, przelew);

        } catch (AplikacjaException ex) {
            e = ex;
        } catch (DataException ex) {
            e = ex;
        } catch(ParseException ex) {
            e = ex;
        }

        ArrayList<PrzelewNormalny> odwleczonePrzelewy = app.getOdwleczonePrzelewy();
        Assertions.assertTrue(odwleczonePrzelewy.get(0).equals(przelew));

        Assertions.assertTrue(e instanceof AplikacjaException);

        Assertions.assertFalse(odwleczonePrzelewy.isEmpty());
    }

    @Test
    public void testZmienDatePrzelewu() {
        Throwable e = null;
        PrzelewNormalny przelew = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "20080391802926389157867262";
        double stan = 559.64;
        int id = 254;

        String tytul = "Przelew za czynsz";
        String numerKontaOdbiorcy = "11876809869057641613549372";
        double kwota = 50.55;

        try {
            Konto konto = app.stworzKonto(login, haslo, telefon, numerKonta, stan, false, true, false, false);
            Data data = new Data(15, 5, 2019);
            przelew = app.zlecPrzelewNormalny(konto, tytul, numerKontaOdbiorcy, kwota, id, data);

            Assertions.assertTrue(przelew.getData().equals(data));

            Data nowaData = new Data(13, 9, 2020);
            app.zmienDatePrzelewu(przelew, nowaData);

            Assertions.assertTrue(przelew.getData().equals(nowaData));

        } catch (AplikacjaException ex) {
            e = ex;
        } catch (DataException ex) {
                e = ex;
        } catch (ParseException ex) {
            e = ex;
        }

        Assertions.assertTrue(e == null);
    }

    @Test
    public void testZmienDatePrzelewuNaZla() {
        Throwable e = null;
        PrzelewNormalny przelew = null;

        String login = "qwerty";
        String haslo = "1234";

        String telefon = "123 456 789";
        String numerKonta = "20080391802926389157867262";
        double stan = 559.64;
        int id = 254;

        String tytul = "Przelew za czynsz";
        String numerKontaOdbiorcy = "11876809869057641613549372";
        double kwota = 50.55;

        try {
            Konto konto = app.stworzKonto(login, haslo, telefon, numerKonta, stan, false, true, false, false);
            Data data = new Data(15, 5, 2019);
            przelew = app.zlecPrzelewNormalny(konto, tytul, numerKontaOdbiorcy, kwota, id, data);

            Assertions.assertTrue(przelew.getData().equals(data));

            Data nowaData = new Data(13, 9, 2001);
            app.zmienDatePrzelewu(przelew, nowaData);

        } catch (AplikacjaException ex) {
            e = ex;
        } catch (DataException ex) {
            e = ex;
        } catch (ParseException ex) {
            e = ex;
        }

        Assertions.assertTrue(e instanceof DataException);
    }


}
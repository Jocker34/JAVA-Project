package Bank;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;


public class TestData {

    @Test
    public void testData() {
        Throwable e = null;
        try {
            Data data = new Data(30, 5, 2019);
        } catch (DataException ex) {
            e = ex;
        }  catch(ParseException ex) {
            e = ex;
        }
        Assertions.assertFalse(e instanceof ParseException);
        Assertions.assertFalse(e instanceof DataException);
    }


    @Test
    public void testZlaData() {
        Throwable e = null;
        try {
            Data data = new Data(20, 5, 2016);
        } catch (DataException ex) {
            e = ex;
        } catch(ParseException ex) {
            e = ex;
        }
        Assertions.assertFalse(e instanceof ParseException);
        Assertions.assertTrue(e instanceof  DataException);
    }

    @Test
    public void setDzien() {
        Throwable e = null;
        Data data = null;
        int initDzien = 25;
        int dzien = 30;
        try {
            data = new Data(initDzien, 8, 2019);
            data.setDzien(dzien);
        } catch (DataException ex) {
            e = ex;
        }  catch(ParseException ex) {
            e = ex;
        } finally {
            Assertions.assertTrue(data.getDzien() == dzien);
        }


        Assertions.assertTrue(e == null);
    }

    @Test
    public void setZlyDzien() {
        Throwable e = null;
        Data data = null;
        int initDzien = 25;
        int dzien = 32;
        try {
            data = new Data(initDzien, 8, 2019);
            data.setDzien(dzien);
        } catch (DataException ex) {
            e = ex;
        }  catch(ParseException ex) {
            e = ex;
        } finally {
            Assertions.assertTrue(data.getDzien() == initDzien);
        }

        Assertions.assertFalse(e instanceof ParseException);
        Assertions.assertTrue(e instanceof DataException);
    }


    @Test
    public void setMiesiac() {
        Throwable e = null;
        Data data = null;
        int initMiesiac = 11;
        int miesiac = 12;
        try {
            data = new Data(25, initMiesiac, 2019);
            data.setMiesiac(miesiac);
        } catch (DataException ex) {
            e = ex;
        } catch(ParseException ex) {
            e = ex;
        } finally {
            Assertions.assertTrue(data.getMiesiac() == miesiac);
        }


        Assertions.assertTrue(e == null);
    }

    @Test
    public void setZlyMiesiac() {
        Throwable e = null;
        Data data = null;
        int initMiesiac = 11;
        int miesiac = 13;
        try {
            data = new Data(25, initMiesiac, 2019);
            data.setMiesiac(miesiac);
        } catch (DataException ex) {
            e = ex;
        }  catch(ParseException ex) {
            e = ex;
        } finally {
            Assertions.assertTrue(data.getMiesiac() == initMiesiac);
        }

        Assertions.assertFalse(e instanceof ParseException);
        Assertions.assertTrue(e instanceof DataException);
    }


    @Test
    public void setRok() {
        Throwable e = null;
        Data data = null;
        int initRok = 2019;
        int rok = 2020;
        try {
            data = new Data(15, 11, initRok);
            data.setRok(rok);
        } catch (DataException ex) {
            e = ex;
        } catch(ParseException ex) {
            e = ex;
        } finally {
            Assertions.assertTrue(data.getRok() == rok);
        }


        Assertions.assertTrue(e == null);
    }


    @Test
    public void setZlyRok() {
        Throwable e = null;
        Data data = null;
        int initRok = 2019;
        int rok = 2001;
        try {
            data = new Data(15, 11, initRok);
            data.setMiesiac(rok);
        } catch (DataException ex) {
            e = ex;
        }  catch(ParseException ex) {
            e = ex;
        } finally {
            Assertions.assertTrue(data.getRok() == initRok);
        }

        Assertions.assertFalse(e instanceof ParseException);
        Assertions.assertTrue(e instanceof DataException);
    }


    @Test
    public void equalsTaSamaData() {
        Throwable e = null;
        Data data1 = null;
        Data data2 = null;

        int poczDzien = 15;
        int poczMiesiac = 12;
        int poczRok = 2019;

        try {
            data1 = new Data(poczDzien, poczMiesiac, poczRok);
            data2 = new Data(poczDzien, poczMiesiac, poczRok);


        } catch (DataException ex) {
            e = ex;
        } catch (ParseException ex) {
            e = ex;
        }

        Assertions.assertTrue(data1.equals(data2));
    }

    @Test
    public void equalsInnaData() {
        Throwable e = null;
        Data data1 = null;
        Data data2 = null;

        int poczDzien = 15;
        int poczMiesiac = 12;
        int poczRok = 2019;

        int koncDzien = 14;
        int koncMiesiac = 12;
        int koncRok = 2020;

        try {
            data1 = new Data(poczDzien, poczMiesiac, poczRok);
            data2 = new Data(koncDzien, koncMiesiac, koncRok);


        } catch (DataException ex) {
            e = ex;
        } catch (ParseException ex) {
            e = ex;
        }

        Assertions.assertFalse(data1.equals(data2));
    }
}

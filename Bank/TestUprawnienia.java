package Bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUprawnienia {

    @Test
    public void testSetPrzelewEkspresowy() {
        Throwable e = null;
        Uprawnienia upr = new Uprawnienia(false, false, false, true);

        try {
            upr.setPrzelewEkspresowy(true);
        } catch (UprawnieniaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e == null);
        Assertions.assertTrue(upr.isPrzelewEkspresowy());
    }

    @Test
    public void testSetPrzelewEkspresowyBezUprawnien() {
        Throwable e = null;
        Uprawnienia upr = new Uprawnienia(false, false, false, false);

        try {
            upr.setPrzelewEkspresowy(true);
        } catch (UprawnieniaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e instanceof UprawnieniaException);
        Assertions.assertFalse(upr.isPrzelewEkspresowy());
    }


    @Test
    public void testSetPrzelewNormalny() {
        Throwable e = null;
        Uprawnienia upr = new Uprawnienia(false, false, false, true);

        try {
            upr.setPrzelewNormalny(true);
        } catch (UprawnieniaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e == null);
        Assertions.assertTrue(upr.isPrzelewNormalny());
    }


    @Test
    public void testSetPrzelewNormalnyBezUprawnien() {
        Throwable e = null;
        Uprawnienia upr = new Uprawnienia(false, false, false, false);

        try {
            upr.setPrzelewNormalny(true);
        } catch (UprawnieniaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e instanceof UprawnieniaException);
        Assertions.assertFalse(upr.isPrzelewNormalny());
    }


    @Test
    public void testSetWycofaniePrzelewu() {
        Throwable e = null;
        Uprawnienia upr = new Uprawnienia(false, false, false, true);

        try {
            upr.setWycofaniePrzelewu(true);
        } catch (UprawnieniaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e == null);
        Assertions.assertTrue(upr.isWycofaniePrzelewu());
    }


    @Test
    public void testSetWycofaniePrzelewuBezUprawnien() {
        Throwable e = null;
        Uprawnienia upr = new Uprawnienia(false, false, false, false);

        try {
            upr.setWycofaniePrzelewu(true);
        } catch (UprawnieniaException ex) {
            e = ex;
        }

        Assertions.assertTrue(e instanceof UprawnieniaException);
        Assertions.assertFalse(upr.isWycofaniePrzelewu());
    }

}

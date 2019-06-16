package Bank;

public class Uprawnienia {
    private boolean przelewEkspresowy;
    private boolean przelewNormalny;
    private boolean wycofaniePrzelewu;
    private boolean zmianaUprawnien;

    public Uprawnienia(boolean przelewEkspresowy, boolean przelewNormalny, boolean wycofaniePrzelewu, boolean zmianaUprawnien) {
        this.przelewEkspresowy = przelewEkspresowy;
        this.przelewNormalny = przelewNormalny;
        this.wycofaniePrzelewu = wycofaniePrzelewu;
        this.zmianaUprawnien = zmianaUprawnien;
    }


    public boolean isPrzelewEkspresowy() {
        return przelewEkspresowy;
    }

    public void setPrzelewEkspresowy(boolean przelewEkspresowy) throws UprawnieniaException {
        if(isZmianaUprawnien()) {
            this.przelewEkspresowy = przelewEkspresowy;
        } else {
            throw new UprawnieniaException("Brak praw do zmiany uprawnienia: przelew ekspresowy.");
        }
    }

    public boolean isPrzelewNormalny() {
        return przelewNormalny;
    }

    public void setPrzelewNormalny(boolean przelewNormalny) throws UprawnieniaException {
        if (isZmianaUprawnien()) {
            this.przelewNormalny = przelewNormalny;
        } else {
            throw new UprawnieniaException("Brak praw do zmiany uprawnienia: przelew normalny.");
        }
    }

    public boolean isWycofaniePrzelewu() {
        return wycofaniePrzelewu;
    }

    public void setWycofaniePrzelewu(boolean wycofaniePrzelewu) throws UprawnieniaException {
        if (isZmianaUprawnien()) {
            this.wycofaniePrzelewu = wycofaniePrzelewu;
        } else {
            throw new UprawnieniaException("Brak praw do zmiany uprawnienia: wycofanie przelewu.");
        }
    }

    public boolean isZmianaUprawnien() {
        return zmianaUprawnien;
    }


}

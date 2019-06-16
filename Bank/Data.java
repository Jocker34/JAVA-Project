package Bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Data{


    private int aktualny_rok = Calendar.getInstance().get(Calendar.YEAR);
    private int aktualny_dzien = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    private int aktualny_miesiac = Calendar.getInstance().get(Calendar.MONTH);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Date aktualna_data;
    private Date data;


    public Data(int dzien, int miesiac, int rok) throws DataException, ParseException {

        if (dzien < 1 || dzien > 31) {
            throw new DataException("Niepoprawny dzien. Prawidlowa wartosc to 1-31.");
        }

        if (miesiac < 1 || miesiac > 12) {
            throw new DataException("Niepoprawny miesiac. Prawidlowa wartosc to 1-12.");
        }

        if (rok < aktualny_rok || rok > aktualny_rok + 5) {
            throw new DataException("Niepoprawny rok. Prawidlowy przedial to: " + aktualny_rok + " - " + (aktualny_rok + 5));
        }

        aktualna_data = sdf.parse(aktualny_rok + "-" + aktualny_miesiac + "-" + aktualny_dzien);
        data = sdf.parse(rok + "-" + miesiac + "-" + dzien);

        if (data.before(aktualna_data)) {
            throw new DataException("Niepoprawna data");
        }


    }

    public int getDzien() {
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();
    }

    public void setDzien(int dzien) throws DataException, ParseException {
        if (dzien < 1 || dzien > 31 ) {
            throw new DataException("Niepoprawny miesiac. Prawidlowa wartosc: 1 - 31.");
        }

        Date nowa_data = sdf.parse(getRok() + "-" + getMiesiac() + "-" + dzien);

        if (nowa_data.before(aktualna_data)) {
            throw new DataException("Niepoprawny dzień.");
        } else {
            this.data = nowa_data;
        }

    }

    public int getMiesiac() {
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
    }

    public void setMiesiac(int miesiac) throws DataException, ParseException {
        if (miesiac < 1 || miesiac > 12) {
            throw new DataException("Niepoprawny miesiac. Prawidlowa wartosc: 1 - 12.");
        }

        Date nowa_data = sdf.parse(getRok() + "-" + miesiac + "-" + getDzien());

        if (nowa_data.before(aktualna_data)) {
            throw new DataException("Nie można ustawić starszego terminu");
        } else {
            this.data = nowa_data;
        }
    }

    public int getRok() {
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
    }

    public void setRok(int rok) throws DataException, ParseException {
        if (rok < aktualny_rok || rok > aktualny_rok + 5) {
            throw new DataException("Niepoprawny rok. Poprawny przedział to: " + aktualny_rok + " - " + (aktualny_rok + 5));
        }

        Date nowa_data = sdf.parse(rok + "-" + getMiesiac() + "-" + getDzien());

        if (nowa_data.before(aktualna_data)) {
            throw new DataException("Nie można ustawić starszego terminu");
        } else {
            this.data = nowa_data;
        }
    }


    public boolean equals(Data data) {
        if (this.data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth() == data.getDzien()
            && this.data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == data.getMiesiac()
            && this.data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == data.getRok()) {
            return true;
        } else {
            return false;
        }
    }





}

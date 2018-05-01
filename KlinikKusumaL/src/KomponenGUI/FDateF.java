package KomponenGUI;

import Eror.LogEror;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Locale.ENGLISH;

public class FDateF {

    public static String datetostr(Date tanggal, String SFromatTanggal) {
        String Tanggal = null;
        if (tanggal != null) {
            try {
                SimpleDateFormat formattanggal = new SimpleDateFormat(SFromatTanggal);
                Tanggal = formattanggal.format(tanggal);
            } catch (Exception e) {
                LogEror.SaveEror(e);
            }
            return Tanggal;
        } else {
            return null;
        }
    }

    public static Date strtodate(String tgl, String FormatTanggal) {
        Date date = null;
        if (tgl != null) {
            try {
                DateFormat format = new SimpleDateFormat(FormatTanggal, ENGLISH);
                date = format.parse(tgl);
            } catch (ParseException e) {
                LogEror.SaveEror(e);
            }
            return date;
        }
        return null;
    }
}

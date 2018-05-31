package LSubProces;

import java.io.InputStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import static net.sf.jasperreports.engine.util.JRLoader.loadObject;
import static net.sf.jasperreports.view.JasperViewer.viewReport;

public class FLaporan {

    HashMap hashs = new HashMap();
    String Filename;
    String Erorm;

    public void sethashmap(HashMap h) {
        hashs = h;
    }

    public void setfilename(String F) {
        Filename = F;
    }

    public void setErorm(String E) {
        Erorm = E;
    }

    public void excute() {
        Koneksi koneksi = new Koneksi();
        Connection con = koneksi.getConnection();
        try {
            InputStream files = getClass().getResourceAsStream("/Resource/" + Filename + ".jasper");
            JasperReport jasperReport;
            jasperReport = (JasperReport) loadObject(files);
            JasperPrint jasperPrint = fillReport(jasperReport, hashs, con);
            viewReport(jasperPrint, false);
        } catch (JRException ex) {
            out.println("E5" + ex);
            ex.printStackTrace();
        } finally {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                out.println("conclose Laporan Gudang" + e);
//            }
        }
    }
}

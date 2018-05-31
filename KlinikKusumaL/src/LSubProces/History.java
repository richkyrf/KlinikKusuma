package LSubProces;

import Eror.LogEror;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class History {

    /**
     *
     * @param hysNamaUser
     * @param hysActivity
     * @param HysTanggal
     * @return
     */
    public static String simpanhistory(String hysNamaUser, String hysActivity) {

        Koneksi koneksi = new Koneksi();
        Connection con = koneksi.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO `tbhistory`(`Username`, `Activity`, `DateAndTime`) VALUES ('" + hysNamaUser + "','" + hysActivity + "',NOW())");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LogEror.SaveString(pstmt.toString());
            Eror.LogEror.SaveEror(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
//                    con.close();
                }
            } catch (SQLException ex) {
                Eror.LogEror.SaveEror(ex);
                //System.out.println("Eror Close Con/Prep");
            }
        }
        return hysNamaUser;
    }
}

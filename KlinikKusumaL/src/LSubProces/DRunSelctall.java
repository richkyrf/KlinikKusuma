package LSubProces;

import Eror.LogEror;
import FunctionGUI.JOptionPaneF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DRunSelctall {

    String SQL;
    Connection con;
    String Erorm;
    boolean iskosong = false;

    public void setQuery(String Query) {
        SQL = Query;
    }

    public void setQuerynolimit(String Query) {
        SQL = Query;
    }

    public void seterorm(String ErorM) {
        Erorm = ErorM;
    }

    public ArrayList excute() {
        ArrayList<String> list = new ArrayList<>();
        Koneksi koneksi = new Koneksi();
        con = koneksi.getConnection();
        int rowsUpdated = 0;
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            String changeLastName = SQL;
            pstmt = con.prepareStatement(changeLastName);
            //System.out.println(pstmt);
            rs = pstmt.executeQuery();
            int totalcol = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= totalcol; i++) {
                    list.add(rs.getString(i));
                }
            }
            if (!rs.isBeforeFirst()) {
                for (int i = 1; i <= totalcol; i++) {
                    list.add(null);
                }
            }
        } catch (SQLException sqle) {
            LogEror.SaveString(pstmt.toString());
            LogEror.SaveEror(sqle);
            if (Erorm != null) {
                JOptionPaneF.showMessageDialog(null, Erorm);
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                LogEror.SaveEror(ex);
            }
        }
        return list;
    }
}

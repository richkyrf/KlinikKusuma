package LSubProces;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RunSelct {

    String SQL;
    Connection con;
    boolean iskosong = false;
    ResultSet rs;
    PreparedStatement pstmt;

    public void setQuery(String Query) {
        SQL = Query;
    }

    public ResultSet excute() throws SQLException {
        Koneksi koneksi = new Koneksi();
        con = koneksi.getConnection();
        rs = null;
        int rowsUpdated = 0;
        pstmt = con.prepareStatement(SQL);
        ////System.out.println(pstmt);
        rs = pstmt.executeQuery();
        if (!rs.isBeforeFirst()) {
            iskosong = true;
        }
        return rs;
    }

    public void closecon() {
        try {
//            con.close();
        } catch (Exception ex) {
            out.println("E37" + ex);
        }
    }

    public boolean iskosong() {
        return iskosong;
    }
}

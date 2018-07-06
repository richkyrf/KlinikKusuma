package LSubProces;

import Eror.LogEror;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MultiInsert {

    public int returnkey = 0;
    public boolean setautocommit = true;
    boolean BerhasilSimpan = false;
    Connection con = null;
    PreparedStatement pstmt = null;

    public boolean setautocomit(boolean autocomit) {
        try {
            con.setAutoCommit(autocomit);
            return true;
        } catch (Exception e) {
            Eror.LogEror.SaveEror(e);
            //System.out.println("EX:commit " + e);
            return false;
        }
    }

    public int getreturnkey() {
        return returnkey;
    }

    public boolean OpenConnection() {
        try {
            int autono = 0;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getConnection();
            return true;
        } catch (Exception e) {
            Eror.LogEror.SaveEror(e);
            //System.out.println("EX:opencon " + e);
            return false;
        }
    }

    public boolean Excute(String Query, Component Parent) {
        try {
            pstmt = con.prepareStatement(Query.replace("'null'", "null"));
            int no = 0;
            //System.out.println("QUESRY INS = " + Query);
            no = pstmt.executeUpdate();
            if (no > 0) {
                LSubProces.History.simpanhistory(GlobalVar.VarL.username, " Multi Insert");
                return true;
            } else {
                //System.out.println("No   " + no);
                return false;
            }
        } catch (SQLException e) {
            Eror.LogEror.SaveEror(e);
            //System.out.println("EX:ClosePstmt  " + e);
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                LogEror.SaveString(pstmt.toString());
                Eror.LogEror.SaveEror(e);
                //System.out.println("EX:ClosePstmt  " + e);
            }
        }
    }

    public boolean ExcuteWithGenerateKey(String Query, Component Parent) {
        try {
            //System.out.println(Query.replace("'null'", "null"));
            pstmt = con.prepareStatement(Query.replace("'null'", "null"), PreparedStatement.RETURN_GENERATED_KEYS);
            int no = 0;
            no = pstmt.executeUpdate();
            if (no > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    returnkey = rs.getInt(1);
                }
                LSubProces.History.simpanhistory(GlobalVar.VarL.username, " Multi Insert");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            Eror.LogEror.SaveEror(e);
            //System.out.println("EX:ExcuteWithGenKey " + e);
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                LogEror.SaveString(pstmt.toString());
                Eror.LogEror.SaveEror(e);
                //System.out.println("EX:ExcuteWithGenKey " + e);
            }
        }
    }

    public boolean Commit() {
        try {
            con.commit();
            return true;
        } catch (Exception e) {
            Eror.LogEror.SaveEror(e);
            //System.out.println("EX:Commit " + e);
            return false;
        }
    }

    public boolean rollback() {
        try {
            con.rollback();
            return true;
        } catch (Exception e) {
            Eror.LogEror.SaveEror(e);
            //System.out.println("EX:Commit " + e);
            return false;
        }
    }

    public void closecon() {
        setautocomit(true);
        try {
            if (con != null) {
//                con.close();
            }
        } catch (Exception e) {
            Eror.LogEror.SaveEror(e);
            //System.out.println("EX:CloseCon " + e);
        }
    }
}

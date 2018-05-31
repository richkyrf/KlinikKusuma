package LSubProces;

import Eror.LogEror;
import FunctionGUI.JOptionPaneF;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class Delete {

    public boolean Hapus(Object ID, String Query, String JenisData, Component Parent) {
        Connection con;
        Koneksi koneksi = new Koneksi();
        con = koneksi.getConnection();
        boolean berhasilhapus = false;
        int reply = JOptionPaneF.showConfirmDialog(Parent, "Apakah Anda Yakin Akan Menghapus Data " + JenisData + " Dengan " + ID + " ?", "Konfirmasi", YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            PreparedStatement pstmt = null;
            try {
                pstmt = con.prepareStatement(Query);
                int no = 0;
                no = pstmt.executeUpdate();
                if (no > 0) {
                    LSubProces.History.simpanhistory(GlobalVar.VarL.username, "Berhasil Menghapus Data " + JenisData);
                    JOptionPaneF.showMessageDialog(Parent, "Berhasil Menghapus Data " + JenisData);
                    berhasilhapus = true;
                } else {
                    JOptionPaneF.showMessageDialog(Parent, "Gagal Menghapus Data " + JenisData);
                }
            } catch (SQLException e) {
                LogEror.SaveString(pstmt.toString());
                Eror.LogEror.SaveEror(e);
                JOptionPaneF.showMessageDialog(Parent, LSubProces.Parsestringeror.GetErorString(e));
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (con != null) {
//                        con.close();
                    }
                } catch (SQLException ex) {
                    Eror.LogEror.SaveEror(ex);
                    //System.out.println("Eror Close Con/Prep");
                }
            }
        } else {
            //JOptionPaneF.showMessageDialog(null, "Batal Hapus Data " + JenisData);
        }
        return berhasilhapus;
    }
}

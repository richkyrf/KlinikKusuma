package KomponenGUI;

import Eror.LogEror;
import FunctionGUI.JOptionPaneF;
import LSubProces.DRunSelctOne;
import LSubProces.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class JcomboboxF extends JComboBox {

    String Query;

    public JcomboboxF() {
        setFont(new java.awt.Font("Tahoma", 0, 18));
        setModel(new javax.swing.DefaultComboBoxModel(new String[]{"_______________"}));
    }

    String GetQuery() {
        return Query;
    }

    public void load(String query) {
        Query = query;
        DefaultComboBoxModel modelbaru = new DefaultComboBoxModel();
        setModel(modelbaru);
        ArrayList<String> groupNames = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            int autono = 0;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getConnection();
            pstmt = con.prepareStatement(Query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    groupNames.add(rs.getString(1));
                }
            }
            if (!rs.isBeforeFirst()) {
                //groupNames.add("-");
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(groupNames.toArray());
            setModel(model);
        } catch (SQLException e) {
            LogEror.SaveEror(e);
            JOptionPaneF.showMessageDialog(null, LSubProces.Parsestringeror.GetErorString(e));
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
    }

    public void loadall(String query) {
        Query = query;
        DefaultComboBoxModel modelbaru = new DefaultComboBoxModel();
        setModel(modelbaru);
        ArrayList<String> groupNames = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            int autono = 0;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getConnection();
            pstmt = con.prepareStatement(Query);
            ResultSet rs = pstmt.executeQuery();
            groupNames.add("ALL");
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    groupNames.add(rs.getString(1));
                }
            }
            if (!rs.isBeforeFirst()) {
                //groupNames.add("ALL");
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(groupNames.toArray());
            setModel(model);
        } catch (SQLException e) {
            LogEror.SaveEror(e);
            JOptionPaneF.showMessageDialog(null, LSubProces.Parsestringeror.GetErorString(e));
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
    }

    public void Cari(String QueryWhere) {
        String Querycari;
        if (Query.toLowerCase().matches("(.*)" + "where" + "(.*)")) {
            Querycari = Query + QueryWhere;
        } else {
            Querycari = Query + QueryWhere;
        }
        DefaultComboBoxModel modelbaru = new DefaultComboBoxModel();
        setModel(modelbaru);
        ArrayList<String> groupNames = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            int autono = 0;
            Koneksi koneksi = new Koneksi();
            con = koneksi.getConnection();
            pstmt = con.prepareStatement(Querycari);
            ResultSet rs = pstmt.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    groupNames.add(rs.getString(1));
                }
            }
            if (!rs.isBeforeFirst()) {
                //groupNames.add("-");
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(groupNames.toArray());
            setModel(model);
        } catch (SQLException e) {
            LogEror.SaveEror(e);
            JOptionPaneF.showMessageDialog(null, LSubProces.Parsestringeror.GetErorString(e));
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
    }

    public int GetIndexDb(String GetIndexQuery) {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.setQuery(GetIndexQuery + " '" + this.getSelectedItem() + "' ");
        ArrayList<String> hasil = new ArrayList();
        hasil = dRunSelctOne.excute();
        return Integer.parseInt(hasil.get(0));
    }

    public int GetIndexDbFromString(String GetIndexQuery, String Item) {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.setQuery(GetIndexQuery + " '" + Item + "' ");
        ArrayList<String> hasil = new ArrayList();
        hasil = dRunSelctOne.excute();
        return Integer.parseInt(hasil.get(0));
    }

    public void setSelectedI(Object c) {
        if (c == null) {
            this.setSelectedIndex(0);
        } else {
            this.setSelectedItem(c);
        }
    }
}

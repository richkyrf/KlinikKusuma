package KomponenGUI;

import FunctionGUI.JOptionPaneF;
import LSubProces.Koneksi;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JcomCari extends javax.swing.JPanel {

    Component parent = null;
    public String Query = "", Order = "";
    public ArrayList<String> DbColomName = new ArrayList<>();
    Object SelectedValue = null;
    int selectedIndex = 1;
    int[] X;
    String[] Format;
    
    public void setRender(int[] x, String[] Type){
        X = x;
        Format = Type;
    }

    public Object GetIDTable() {
        return jtablef.getValueAt(jtablef.getSelectedRow(), 0);
    }

    public int getSelectedRows() {
        int row = 0;
        for (int i = 0; i < jtablef.getRowCount(); i++) {
            if (jtablef.getValueAt(i, jtablef.getColumnCount() - 1).toString().equals("true")) {
                row++;
            }
        }
        return row;
    }

    public Object[] getSelectedIDs() {
        Object[] a = new Object[getSelectedRows()];
        int row = 0;
        for (int i = 0; i < jtablef.getRowCount(); i++) {
            if (jtablef.getValueAt(i, jtablef.getColumnCount() - 1).toString().equals("true")) {
                a[row] = jtablef.getValueAt(i, 0);
                row++;
            }
        }
        return a;
    }

    public Object GetIDTable1() {
        return jtablef.getValueAt(jtablef.getSelectedRow(), 1);
    }

    public Object GetIDTable3() {
        return jtablef.getValueAt(jtablef.getSelectedRow(), 3);
    }

    public int getSelectedRow() {
        return jtablef.getSelectedRow();
    }

    public int getselectedcolumn() {
        return jtablef.getSelectedColumn();
    }

    public Object getSelectedValue() {
        return jtablef.getValueAt(jtablef.getSelectedRow(), jtablef.getSelectedColumn());
    }

    public String getselected11() {
        return jtablef.getValueAt(jtablef.getSelectedRow(), 1).toString();
    }

//    public void refresh() {
//        jtextF1.setText("");
//        jcomboboxF1.setSelectedIndex(0);
//        if (jcomboboxF1.getItemCount() > 1) {
//        jcomboboxF1.setSelectedIndex(selectedIndex);
//        }
//        jcomboboxF2.setSelectedIndex(3);
//    }

    public JcomCari() {
        initComponents();
//        jtextF1.setText("");
    }

    public void setQuery(String query) {
        Query = query;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public void setSelectedIndex(int index) {
        selectedIndex = index;
    }

    @SuppressWarnings("unchecked")
    public void tampilkan() {
//        if (JcomCari.jcomboboxF1.getSelectedItem().toString().equals("-")) {
//            getjcombobox();
//        }
        this.jtablef.setQuery(this.Query + " " + this.Order);
        this.jtablef.tampilkan();
        jtablef.setrender(X, Format);
//        jtextF1.requestFocus();
    }

//    @SuppressWarnings("unchecked")
//    public void getjcombobox() {
//        DefaultComboBoxModel modelbaru = new DefaultComboBoxModel();
//        jcomboboxF1.setModel(modelbaru);
//        ArrayList<String> groupNames = new ArrayList<>();
//        DbColomName = new ArrayList<>();
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        try {
//            int autono = 0;
//            Koneksi koneksi = new Koneksi();
//            con = koneksi.getConnection();
//            //System.out.println("KomponenGUI.JcomCari.getjcombobox()"+Query);
//            pstmt = con.prepareStatement(Query);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.isBeforeFirst()) {
//                int columnsNumber = rs.getMetaData().getColumnCount();
//                for (int i = 1; i < columnsNumber + 1; i++) {
//                    {
//                        groupNames.add(rs.getMetaData().getColumnLabel(i));
//                        DbColomName.add(rs.getMetaData().getColumnName(i));
//                    }
//                }
//            }
//            if (!rs.isBeforeFirst()) {
//                groupNames.add("-");
//                DbColomName.add("");
//            }
//            DefaultComboBoxModel model = new DefaultComboBoxModel(groupNames.toArray());
//            jcomboboxF1.setModel(model);
//        } catch (SQLException e) {
//            JOptionPaneF.showMessageDialog(parent, LSubProces.Parsestringeror.GetErorString(e));
//        } finally {
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException ex) {
//                //System.out.println("Eror Close Con/Prep");
//            }
//        }
//        jcomboboxF1.setSelectedIndex(selectedIndex);
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtablef = new KomponenGUI.JtableF();

        new JScrollPane(jtablef, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jtablef.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtablef.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtablef);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
//    public void Cari() {
//        try {
//            this.jtablef.setQuery(this.Query + " HAVING " + this.DbColomName.get(JcomCari.jcomboboxF1.getSelectedIndex()) + getjcomtypecari() + this.Order);
//            this.jtablef.tampilkan();
//        jtablef.setrender(X, Format);
//        } catch (Exception localException) {
//        }
//    }

//    String getjcomtypecari() {
//        switch (jcomboboxF2.getSelectedIndex()) {
//            case 0:
//                return " = '" + jtextF1.getText() + "' ";
//            case 1:
//                return " Like '" + jtextF1.getText() + "%' ";
//            case 2:
//                return " Like '%" + jtextF1.getText() + "' ";
//            case 3:
//                if (jtextF1.getText().equals("")) {
//                    return " Like '%" + jtextF1.getText() + "%' ";
//                } else {
//                    return " REGEXP '" + jtextF1.getText().replace(" ", ".+") + "' ";
//                }
//            default:
//                return "EROR";
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public KomponenGUI.JtableF jtablef;
    // End of variables declaration//GEN-END:variables
}

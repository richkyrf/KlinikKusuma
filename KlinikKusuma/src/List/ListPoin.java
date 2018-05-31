/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import LSubProces.Delete;
import Master.*;
import FunctionGUI.JOptionPaneF;
import static GlobalVar.Var.*;
import KomponenGUI.FDateF;
import LSubProces.DRunSelctOne;
import LSubProces.Insert;
import Proses.*;
import java.awt.Color;
import java.awt.Component;
import static java.awt.Frame.NORMAL;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.RowFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author richky
 */
public class ListPoin extends javax.swing.JFrame {

    /**
     * Creates new form ListKaryawan
     */
    String ID;

    public ListPoin(Object id) {
        ID = id.toString();
        initComponents();
        setVisible(true);
        setTitle("Cek Poin");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        refreshAll();
        jtextF1.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBRefresh = new KomponenGUI.JbuttonF();
        jbuttonF4 = new KomponenGUI.JbuttonF();
        jcomCari1 = new KomponenGUI.JcomCari();
        jlableF1 = new KomponenGUI.JlableF();
        jtextF1 = new KomponenGUI.JtextF();
        JLPoin = new KomponenGUI.JlableF();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        JBRefresh.setText("Refresh");
        JBRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRefreshActionPerformed(evt);
            }
        });

        jbuttonF4.setText("Kembali");
        jbuttonF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF4ActionPerformed(evt);
            }
        });

        jlableF1.setText("Pencarian :");

        jtextF1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtextF1KeyReleased(evt);
            }
        });

        JLPoin.setText("Poin Sekarang :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlableF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtextF1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 389, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbuttonF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JBRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLPoin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcomCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLPoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcomCari1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlableF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtextF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF4ActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonF4ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cekPoin = null;
    }//GEN-LAST:event_formWindowClosed

    private void JBRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRefreshActionPerformed
        refreshAll();
    }//GEN-LAST:event_JBRefreshActionPerformed

    private void jtextF1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextF1KeyReleased
        String text = jtextF1.getText();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jcomCari1.jtablef.getModel());
        jcomCari1.jtablef.setRowSorter(rowSorter);
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_jtextF1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListPoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListPoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListPoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListPoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListPoin("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private KomponenGUI.JbuttonF JBRefresh;
    private KomponenGUI.JlableF JLPoin;
    private KomponenGUI.JbuttonF jbuttonF4;
    public static KomponenGUI.JcomCari jcomCari1;
    private KomponenGUI.JlableF jlableF1;
    private KomponenGUI.JtextF jtextF1;
    // End of variables declaration//GEN-END:variables

    void refreshAll() {
        jtextF1.setText("");
        load();
    }

    public void load() {
        //jcomCari1.setQuery("SELECT `Tanggal`, `NamaPasien`, `Ket`, `Total Belanja`, IFNULL(`Poin`,0) as 'Poin' FROM (SELECT d.`IdPasien`, DATE_FORMAT(a.`Tanggal`,'%d-%m-%Y') as 'Tanggal', `NamaPasien`, 'Belanja' as 'Ket', (SUM(e.`Jumlah`*e.`Harga`)+SUM(f.`Jumlah`*f.`Harga`) - (`Poin` * 5000)) as 'Total Belanja', FLOOR((SUM(e.`Jumlah`*e.`Harga`)+SUM(f.`Jumlah`*f.`Harga`) - (`Poin` * 5000)) / 50000) as 'Poin' FROM `tbbilling`a JOIN `tbperawatan`b ON a.`NoInvoice`=b.`NoInvoice` JOIN `tbantrian`c ON b.`NoAntrian`=c.`NoAntrian` AND b.`Tanggal`=c.`Tanggal` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 AND d.`IdPasien` = '" + ID + "' GROUP BY a.`NoBilling` UNION ALL SELECT d.`IdPasien`, DATE_FORMAT(a.`Tanggal`,'%d-%m-%Y') as 'Tanggal', `NamaPasien`, 'Pakai Poin' as 'Ket', a.`Poin`*5000 as 'Total Belanja', `Poin`*-1 FROM `tbbilling`a JOIN `tbperawatan`b ON a.`NoInvoice`=b.`NoInvoice` JOIN `tbantrian`c ON b.`NoAntrian`=c.`NoAntrian` AND b.`Tanggal`=c.`Tanggal` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 AND a.`StatusPoin` = 1 AND d.`IdPasien` = '" + ID + "' GROUP BY a.`NoBilling`) t1 WHERE 1 ");
        jcomCari1.setQuery("SELECT `IdPasien` as 'ID', `Tanggal`, `NamaPasien` as 'Pasien', `Total Belanja`, `Pakai Poin`, `Setelah Potong`, `Dapat Poin` FROM ( SELECT a.`NoBIlling` as 'No Trans', d.`IdPasien`, DATE_FORMAT(a.`Tanggal`,'%d-%m-%Y') as 'Tanggal', `NamaPasien`, SUM(IFNULL(e.`Jumlah`,0)*IFNULL(e.`Harga`,0))+SUM(IFNULL(f.`Jumlah`,0)*IFNULL(f.`Harga`,0)) as 'Total Belanja', `Poin` as 'Pakai Poin', (SUM(IFNULL(e.`Jumlah`,0)*IFNULL(e.`Harga`,0))+SUM(IFNULL(f.`Jumlah`,0)*IFNULL(f.`Harga`,0)) - (`Poin` * 5000)) as 'Setelah Potong', FLOOR((SUM(IFNULL(e.`Jumlah`,0)*IFNULL(e.`Harga`,0))+SUM(IFNULL(f.`Jumlah`,0)*IFNULL(f.`Harga`,0)) - (`Poin` * 5000)) / 50000) as 'Dapat Poin' FROM `tbbilling`a LEFT JOIN `tbperawatan`b ON a.`NoTransaksi`=b.`NoTransaksi` JOIN `tbantrian`c ON IF(b.`IdAntrian` IS NULL, a.`IdAntrian`, b.`IdAntrian`)=c.`IdAntrian` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` LEFT JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` LEFT JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 AND d.`IdPasien` = '" + ID + "' GROUP BY a.`NoBilling` ) t1 ");
        jcomCari1.setOrder(" ORDER BY `Tanggal` DESC, `No Trans` DESC");
        jcomCari1.tampilkan();
        jcomCari1.jtablef.setrender(new int[]{3, 4, 5, 6}, new String[]{"Number", "CenteredNumber", "Number", "CenteredNumber"});
        setPoin();
    }

    void setPoin() {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Gagal setPoin()");
        dRunSelctOne.setQuery("SELECT `IdPasien`, IFNULL(SUM(`Poin`),0) as 'Poin' FROM (    SELECT `IdPasien`, 0 as 'Jumlah Belanja', 0 as 'Poin' FROM `tbmpasien` WHERE 1 AND `IdPasien` = '" + ID + "'     UNION ALL     SELECT d.`IdPasien`, (SUM(IFNULL(e.`Jumlah`,0)*IFNULL(e.`Harga`,0))+SUM(IFNULL(f.`Jumlah`,0)*IFNULL(f.`Harga`,0)) - (`Poin` * 5000)) as 'Total Belanja', FLOOR((SUM(IFNULL(e.`Jumlah`,0)*IFNULL(e.`Harga`,0))+SUM(IFNULL(f.`Jumlah`,0)*IFNULL(f.`Harga`,0)) - (`Poin` * 5000)) / 50000) as 'Poin' FROM `tbbilling`a LEFT JOIN `tbperawatan`b ON a.`NoTransaksi`=b.`NoTransaksi` JOIN `tbantrian`c ON IF(b.`IdAntrian` IS NULL, a.`IdAntrian`, b.`IdAntrian`)=c.`IdAntrian` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` LEFT JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` LEFT JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 AND d.`IdPasien` = '" + ID + "' GROUP BY d.`IdPasien`, a.`NoBilling`     UNION ALL     SELECT d.`IdPasien`, a.`Poin`*5000*-1 as 'Total Belanja', `Poin`*-1 FROM `tbbilling`a LEFT JOIN `tbperawatan`b ON a.`NoTransaksi`=b.`NoTransaksi` JOIN `tbantrian`c ON IF(b.`IdAntrian` IS NULL, a.`IdAntrian`, b.`IdAntrian`)=c.`IdAntrian` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` LEFT JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` LEFT JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 AND a.`StatusPoin` = 1 AND d.`IdPasien` = '" + ID + "' GROUP BY d.`IdPasien`, a.`NoBilling`) t1 WHERE 1 GROUP BY `IdPasien`");
        ArrayList<String> list = dRunSelctOne.excute();
        JLPoin.setText("Poin Sekarang : " + Integer.parseInt(list.get(1)));
    }
}

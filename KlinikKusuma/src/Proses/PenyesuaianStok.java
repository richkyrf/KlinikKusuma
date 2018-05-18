/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;

import KomponenGUI.FDateF;
import LSubProces.DRunSelctOne;
import LSubProces.Insert;
import LSubProces.RunSelct;
import LSubProces.Update;
import java.awt.event.KeyEvent;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import FunctionGUI.JOptionPaneF;
import static GlobalVar.Var.*;
import static java.awt.Frame.NORMAL;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author richky
 */
public class PenyesuaianStok extends javax.swing.JFrame {

    public String IdPenyesuaian;

    /**
     * Creates new form PenyesuaianStok
     */
    public PenyesuaianStok(String type) {
        setTitle(type);
        initComponents();
        JTNoPenyesuaian.setText(getNoPenyesuaianStok());
        JTNamaBarang.requestFocus();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    boolean checkInput() {
        if (JTNamaBarang.getText().replace(" ", "").isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Pilih Nama Barang Terlebih Dahulu.");
            JTNamaBarang.requestFocus();
            return false;
        } else if ((Integer.valueOf(JTStokBaru.getText().replace(".", "")) - Integer.valueOf(JTStokLama.getText().replace(".", "").replace(",", "."))) == 0) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Stok Baru Tidak Boleh Sama Dengan Stok Lama.");
            return false;
        } else if (JDTanggalPenyesuaian.getDate() == null) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Tanggal Tidak Boleh Kosong.");
            return false;
        } else {
            return true;
        }
    }

    void cariBarang(String keywordCari) {
        if (jcari == null) {
            jcari = new Jcari("SELECT `JenisBarang`, `NamaBarang` FROM `tbmbarang`a JOIN `tbsmjenisbarang`b ON a.`IdJenisBarang`=b.`IdJenisBarang` WHERE `JenisBarang` ", "SELECT `JenisBarang`, `NamaBarang` FROM `tbmbarang`a JOIN `tbsmjenisbarang`b ON a.`IdJenisBarang`=b.`IdJenisBarang` WHERE `NamaBarang` ", "Jenis Barang", "Nama Barang", "Cari Barang", 1, JTNamaBarang, JTStokBaru, keywordCari);
        } else {
            jcari.setState(NORMAL);
            jcari.toFront();
        }
    }

    boolean isAlphanumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlableF1 = new KomponenGUI.JlableF();
        jlableF2 = new KomponenGUI.JlableF();
        jlableF3 = new KomponenGUI.JlableF();
        jlableF4 = new KomponenGUI.JlableF();
        jlableF5 = new KomponenGUI.JlableF();
        jlableF6 = new KomponenGUI.JlableF();
        jlableF9 = new KomponenGUI.JlableF();
        jlableF10 = new KomponenGUI.JlableF();
        jlableF13 = new KomponenGUI.JlableF();
        jlableF14 = new KomponenGUI.JlableF();
        JTNoPenyesuaian = new KomponenGUI.JtextF();
        jlableF17 = new KomponenGUI.JlableF();
        jlableF18 = new KomponenGUI.JlableF();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAKeterangan = new KomponenGUI.JTextAreaF();
        JDTanggalPenyesuaian = new KomponenGUI.JdateCF();
        JTStokLama = new KomponenGUI.JtextF();
        JTStokBaru = new KomponenGUI.JRibuanTextField();
        jbuttonF1 = new KomponenGUI.JbuttonF();
        jbuttonF2 = new KomponenGUI.JbuttonF();
        JTNamaBarang = new KomponenGUI.JtextF();
        JBSearchNamaBarang = new KomponenGUI.JbuttonF();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jlableF1.setText("No. Penyesuaian");

        jlableF2.setText(":");

        jlableF3.setText("Tanggal");

        jlableF4.setText(":");

        jlableF5.setText("Nama Barang");

        jlableF6.setText(":");

        jlableF9.setText("Stok Lama");

        jlableF10.setText(":");

        jlableF13.setText("Stok Baru");

        jlableF14.setText(":");

        JTNoPenyesuaian.setEnabled(false);

        jlableF17.setText("Keterangan");

        jlableF18.setText(":");

        JTAKeterangan.setColumns(20);
        JTAKeterangan.setRows(5);
        JTAKeterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTAKeteranganKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(JTAKeterangan);

        JDTanggalPenyesuaian.setDate(new Date());
        JDTanggalPenyesuaian.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JDTanggalPenyesuaianPropertyChange(evt);
            }
        });

        JTStokLama.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTStokLama.setText("0");
        JTStokLama.setEnabled(false);

        JTStokBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTStokBaruKeyPressed(evt);
            }
        });

        jbuttonF1.setText("Sesuaikan");
        jbuttonF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF1ActionPerformed(evt);
            }
        });

        jbuttonF2.setText("Kembali");
        jbuttonF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF2ActionPerformed(evt);
            }
        });

        JTNamaBarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTNamaBarangFocusLost(evt);
            }
        });
        JTNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTNamaBarangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTNamaBarangKeyReleased(evt);
            }
        });

        JBSearchNamaBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Search.png"))); // NOI18N
        JBSearchNamaBarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JBSearchNamaBarangFocusLost(evt);
            }
        });
        JBSearchNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSearchNamaBarangActionPerformed(evt);
            }
        });
        JBSearchNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JBSearchNamaBarangKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbuttonF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlableF3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlableF5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlableF1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlableF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlableF4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JTNoPenyesuaian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JDTanggalPenyesuaian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlableF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBSearchNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlableF17, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlableF18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlableF9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlableF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTStokLama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JTStokBaru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlableF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTNoPenyesuaian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JDTanggalPenyesuaian, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlableF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JBSearchNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlableF9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTStokLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTStokBaru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlableF17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (getTitle().equals("Penyesuaian Stok")) {
            tambahPenyesuaianStok = null;
        } else {
            tambahPenyesuaianStokGudangBesar = null;
        }
    }//GEN-LAST:event_formWindowClosed

    private void jbuttonF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF2ActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonF2ActionPerformed

    private void JTAKeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTAKeteranganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();
            sesuaikan();
        }
    }//GEN-LAST:event_JTAKeteranganKeyPressed

    private void jbuttonF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF1ActionPerformed
        sesuaikan();
    }//GEN-LAST:event_jbuttonF1ActionPerformed

    private void JTStokBaruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTStokBaruKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTAKeterangan.requestFocus();
        }
    }//GEN-LAST:event_JTStokBaruKeyPressed

    private void JDTanggalPenyesuaianPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JDTanggalPenyesuaianPropertyChange

    }//GEN-LAST:event_JDTanggalPenyesuaianPropertyChange

    private void JTNamaBarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTNamaBarangFocusLost
        setStok();
    }//GEN-LAST:event_JTNamaBarangFocusLost

    private void JTNamaBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNamaBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTStokBaru.requestFocus();
        }
    }//GEN-LAST:event_JTNamaBarangKeyPressed

    private void JTNamaBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNamaBarangKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            cariBarang(null);
        } else if (isAlphanumeric(String.valueOf(evt.getKeyChar()))) {
            cariBarang(JTNamaBarang.getText());
        }
    }//GEN-LAST:event_JTNamaBarangKeyReleased

    private void JBSearchNamaBarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JBSearchNamaBarangFocusLost
        setStok();
    }//GEN-LAST:event_JBSearchNamaBarangFocusLost

    private void JBSearchNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSearchNamaBarangActionPerformed
        cariBarang(null);
    }//GEN-LAST:event_JBSearchNamaBarangActionPerformed

    private void JBSearchNamaBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JBSearchNamaBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTStokBaru.requestFocus();
        }
    }//GEN-LAST:event_JBSearchNamaBarangKeyPressed

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
            java.util.logging.Logger.getLogger(PenyesuaianStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenyesuaianStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenyesuaianStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenyesuaianStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenyesuaianStok("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private KomponenGUI.JbuttonF JBSearchNamaBarang;
    private KomponenGUI.JdateCF JDTanggalPenyesuaian;
    private KomponenGUI.JTextAreaF JTAKeterangan;
    private KomponenGUI.JtextF JTNamaBarang;
    private KomponenGUI.JtextF JTNoPenyesuaian;
    private KomponenGUI.JRibuanTextField JTStokBaru;
    private KomponenGUI.JtextF JTStokLama;
    private javax.swing.JScrollPane jScrollPane1;
    private KomponenGUI.JbuttonF jbuttonF1;
    private KomponenGUI.JbuttonF jbuttonF2;
    private KomponenGUI.JlableF jlableF1;
    private KomponenGUI.JlableF jlableF10;
    private KomponenGUI.JlableF jlableF13;
    private KomponenGUI.JlableF jlableF14;
    private KomponenGUI.JlableF jlableF17;
    private KomponenGUI.JlableF jlableF18;
    private KomponenGUI.JlableF jlableF2;
    private KomponenGUI.JlableF jlableF3;
    private KomponenGUI.JlableF jlableF4;
    private KomponenGUI.JlableF jlableF5;
    private KomponenGUI.JlableF jlableF6;
    private KomponenGUI.JlableF jlableF9;
    // End of variables declaration//GEN-END:variables

    String getNoPenyesuaianStok() {
        NumberFormat nf = new DecimalFormat("000000");
        String NoPenyesuaian = null;
        RunSelct runSelct = new RunSelct();
        if (getTitle().equals("Penyesuaian Stok")) {
            runSelct.setQuery("SELECT `NoPenyesuaian` FROM `tbpenyesuaianstok` ORDER BY `NoPenyesuaian` DESC LIMIT 1");
        } else {
            runSelct.setQuery("SELECT `NoPenyesuaian` FROM `tbpenyesuaianstokgudangbesar` ORDER BY `NoPenyesuaian` DESC LIMIT 1");
        }
        try {
            ResultSet rs = runSelct.excute();
            if (!rs.isBeforeFirst()) {
                if (getTitle().equals("Penyesuaian Stok")) {
                    NoPenyesuaian = "KB-" + "000001" + "-PS";
                } else {
                    NoPenyesuaian = "KB-" + "000001" + "-PG";
                }
            }
            while (rs.next()) {
                String nopenjualan = rs.getString("NoPenyesuaian");
                String number = nopenjualan.substring(3, 9);
                int p = 1 + parseInt(number);
                if (p != 999999) {
                    if (getTitle().equals("Penyesuaian Stok")) {
                        NoPenyesuaian = "KB-" + nf.format(p) + "-PS";
                    } else {
                        NoPenyesuaian = "KB-" + nf.format(p) + "-PG";
                    }
                } else if (p == 999999) {
                    p = 1;
                    if (getTitle().equals("Penyesuaian Stok")) {
                        NoPenyesuaian = "KB-" + nf.format(p) + "-PS";
                    } else {
                        NoPenyesuaian = "KB-" + nf.format(p) + "-PG";
                    }
                }
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Generate Nomor Penyesuaian");
        } finally {
            runSelct.closecon();
        }
        return NoPenyesuaian;
    }

    void setStok() {
        if (!JTNamaBarang.getText().replace(" ", "").equals("")) {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal Menampilkan Data Stok Barang");
            if (getTitle().equals("Penyesuaian Stok")) {
                dRunSelctOne.setQuery("SELECT `IdBarang`, IFNULL(SUM(`Jumlah`),0) as 'Stok' FROM( SELECT `IdBarang`, 0 as 'Jumlah' FROM `tbmbarang` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah` FROM `tbpermintaanstok` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah`*-1 FROM `tbpenjualandetail` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah` FROM `tbpenyesuaianstok` WHERE 1) t1 WHERE `IdBarang` = (SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTNamaBarang.getText() + "') GROUP BY `IdBarang`");
            } else {
                dRunSelctOne.setQuery("SELECT `IdBarang`, SUM(`Jumlah`) as 'Stok' FROM( SELECT `IdBarang`, 0 as 'Jumlah' FROM `tbmbarang` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah` FROM `tbbarangmasukdetail` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah`*-1 FROM `tbpermintaanstok` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah` FROM `tbpenyesuaianstokgudangbesar` WHERE 1) t1 WHERE `IdBarang` = (SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTNamaBarang.getText() + "') GROUP BY `IdBarang`");
            }
            ArrayList<String> list = dRunSelctOne.excute();
            String Stok = list.get(1);
            JTStokLama.setText(Stok);
        }
    }

    String Decformatdigit(double Number) {
        double value = 0;
        value = Number;
        String output;
        String pattern = "#,###,###.00";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        if (value < 0) {
            value = abs(value);
            output = "-" + myFormatter.format(value);
        } else if (value >= 0 && value < 1) {
            output = "0" + myFormatter.format(value);
        } else {
            output = myFormatter.format(value);
        }
        return output;
    }

    void sesuaikan() {
        if (checkInput()) {
            Boolean berhasil;
            Insert insert = new LSubProces.Insert();
            if (getTitle().equals("Penyesuaian Stok")) {
                berhasil = insert.simpan("INSERT INTO `tbpenyesuaianstok` (`NoPenyesuaian`, `Tanggal`, `IdBarang`, `Jumlah`, `Keterangan`) VALUES ('" + JTNoPenyesuaian.getText() + "', '" + FDateF.datetostr(JDTanggalPenyesuaian.getDate(), "yyyy-MM-dd") + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTNamaBarang.getText() + "'),'" + (Float.parseFloat(JTStokBaru.getText().replace(".", "")) - Float.parseFloat(JTStokLama.getText().replace(".", "").replace(",", "."))) + "','" + JTAKeterangan.getText() + "')", "Penyesuaian Stok", null);
            } else {
                berhasil = insert.simpan("INSERT INTO `tbpenyesuaianstokgudangbesar` (`NoPenyesuaian`, `Tanggal`, `IdBarang`, `Jumlah`, `Keterangan`) VALUES ('" + JTNoPenyesuaian.getText() + "', '" + FDateF.datetostr(JDTanggalPenyesuaian.getDate(), "yyyy-MM-dd") + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTNamaBarang.getText() + "'),'" + (Float.parseFloat(JTStokBaru.getText().replace(".", "")) - Float.parseFloat(JTStokLama.getText().replace(".", "").replace(",", "."))) + "','" + JTAKeterangan.getText() + "')", "Penyesuaian Stok Gudang Besar", null);
            }
            if (berhasil) {
                JTNoPenyesuaian.setText(getNoPenyesuaianStok());
                JTNamaBarang.setText("");
                JTStokLama.setText("0");
                JTStokBaru.setText("0");
                JTAKeterangan.setText("");
                JTNamaBarang.requestFocus();
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;

import File.Snapshot;
import java.awt.event.KeyEvent;
import java.util.Date;
import FunctionGUI.JOptionPaneF;
import javax.swing.table.DefaultTableModel;
import static GlobalVar.Var.*;
import KomponenGUI.FDateF;
import LSubProces.DRunSelctOne;
import LSubProces.MultiInsert;
import LSubProces.RunSelct;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_HEIGHT;
import static org.opencv.videoio.Videoio.CAP_PROP_FRAME_WIDTH;

/**
 *
 * @author richk
 */
public class Perawatan extends javax.swing.JFrame {

    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    String UrlGambarEdit;

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

                            BufferedImage buff = (BufferedImage) im;
                            Image newimg = buff.getScaledInstance(jlableF1.getWidth(), jlableF1.getHeight(), SCALE_SMOOTH);
                            ImageIcon newIcon = new ImageIcon(newimg);
                            jlableF1.setSize(jlableF1.getWidth(), jlableF1.getHeight());
                            jlableF1.setIconTextGap(0);
                            jlableF1.setBorder(null);
                            jlableF1.setText(null);
                            jlableF1.setIcon(newIcon);
//                            Graphics g = jPanel3.getGraphics();

//                            if (g.drawImage(buff, 0, 0, jPanel3.getWidth(), jPanel3.getHeight(), 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                            if (runnable == false) {
//                                    System.out.println("Going to wait()");
                                this.wait();
                            }
                        } catch (Exception ex) {
                            System.out.println("Error");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates new form Perawatan
     */
    String Dari, Parameter;

    public Perawatan(String dari, Object parameter) {
        Parameter = parameter.toString();
        Dari = dari;
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        JTNoTransaksi.setText(getNoTransaksi());
        if (dari.equals("Antrian")) {
            setTitle("Tambah Perawatan");
            loadPasien(parameter);
            JBUbah.setVisible(false);
            JBUbahPrint.setVisible(false);
        } else {
            setTitle("Ubah Perawatan");
            JBTambah.setVisible(false);
            JBTambahPrint.setVisible(false);
            loadData(parameter);
        }
        JCNamaDokter.requestFocus();
    }

    void loadData(Object idEdit) {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Eror gagal Menampilkan Data Perawatan");
        dRunSelctOne.setQuery("SELECT `IdPerawatan` as 'ID', CONCAT('(',`KodePasien`,') ',`NamaPasien`) as 'Nama Pasien', DATE_FORMAT(a.`Tanggal`,'%d-%m-%Y') as 'Tanggal', `NoAntrian` as 'No. Antrian', `NoTransaksi` as 'No. Transaksi', `NamaDokter` as 'Nama Dokter', `NamaBeautician` as 'Nama Beautician', `Keluhan`, `Diagnosa`, a.`Catatan`, `UrlGambar` FROM `tbperawatan`a JOIN `tbantrian`b ON a.`IdAntrian`=b.`IdAntrian` AND a.`Tanggal`=b.`Tanggal` JOIN `tbmpasien`c ON b.`IdPasien`=c.`IdPasien` JOIN `tbmdokter`d ON a.`IdDokter`=d.`IdDokter` LEFT JOIN `tbmbeautician`e ON a.`IdBeautician`=e.`IdBeautician` WHERE `IdPerawatan` = '" + Parameter + "'");
        ArrayList<String> list = dRunSelctOne.excute();
        JTNamaPasien.setText(list.get(1));
        JDTanggal.setDate(FDateF.strtodate(list.get(2), "dd-MM-yyyy"));
        JTNoAntrian.setText(list.get(3));
        JTNoTransaksi.setText(list.get(4));
        JCNamaDokter.setSelectedItem(list.get(5));
        JCNamaBeautician.setSelectedItem(list.get(6));
        JTKeluhanPasien.setText(list.get(7));
        JTDiagnosaPasien.setText(list.get(8));
        JTCatatanPasien.setText(list.get(9));
        if (list.get(10) != null) {
            File source = new File(list.get(10));
            if (source.exists()) {
                try {
                    UrlGambarEdit = list.get(10);
                    BufferedImage image = ImageIO.read(source);
                    Image newimg = image.getScaledInstance(jlableF1.getWidth(), jlableF1.getHeight(), SCALE_SMOOTH);
                    ImageIcon newIcon = new ImageIcon(newimg);
//                    Graphics g = jPanel3.getGraphics();
                    jlableF1.setSize(jlableF1.getWidth(), jlableF1.getHeight());
                    jlableF1.setIconTextGap(0);
                    jlableF1.setBorder(null);
                    jlableF1.setText(null);
                    jlableF1.setIcon(newIcon);
//                    g.drawImage(image, 0, 0, jPanel3.getWidth(), jPanel3.getHeight(), null);
                } catch (IOException ex) {
                    Logger.getLogger(Perawatan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        DefaultTableModel model = (DefaultTableModel) JTableTindakan.getModel();
        model.getDataVector().removeAllElements();
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `IdPerawatanDetail` as 'ID', `NoTransaksi` as 'No. Transaksi', `NamaTindakan` as 'Nama Tindakan', FORMAT(`Jumlah`,0) as 'Jumlah' FROM `tbperawatandetail`a JOIN `tbmtindakan`b ON a.`IdTindakan`=b.`IdTindakan` WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'");
        try {
            ResultSet rs = runSelct.excute();
            int row = 0;
            while (rs.next()) {
                model.addRow(new Object[]{"", "", "", "", ""});
                JTableTindakan.setValueAt(rs.getString(3), row, 0);
                JTableTindakan.setValueAt(rs.getString(4).replace(",", "."), row, 1);
                row++;
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Panggil Data Detail Tindakan");
        } finally {
//            runSelct.closecon();
        }
        DefaultTableModel model2 = (DefaultTableModel) JTableObat.getModel();
        model2.getDataVector().removeAllElements();
        RunSelct runSelct2 = new RunSelct();
        runSelct2.setQuery("SELECT `IdObatDetail` as 'ID', `NoTransaksi` as 'No. Transaksi', `NamaBarang` as 'Nama Obat', FORMAT(`Jumlah`,0) as 'Jumlah' FROM `tbobatdetail`a JOIN `tbmbarang`b ON a.`IdObat`=b.`IdBarang` WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'");
        try {
            ResultSet rs2 = runSelct2.excute();
            int row2 = 0;
            while (rs2.next()) {
                model2.addRow(new Object[]{"", "", "", "", ""});
                JTableObat.setValueAt(rs2.getString(3), row2, 0);
                JTableObat.setValueAt(rs2.getString(4).replace(",", "."), row2, 1);
                row2++;
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Panggil Data Detail Obat");
        } finally {
//            runSelct2.closecon();
        }
    }

    void loadPasien(Object idAntrian) {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Gagal loadPasien()");
        dRunSelctOne.setQuery("SELECT CONCAT('(',`KodePasien`,') ',`NamaPasien`), `NoAntrian` FROM `tbmpasien`a LEFT JOIN `tbantrian`b ON a.`IdPasien`=b.`IdPasien` WHERE 1 AND b.`Status` = 0 AND `IdAntrian` = '" + idAntrian + "'");
        ArrayList<String> list = dRunSelctOne.excute();
        JTNamaPasien.setText(list.get(0));
        JTNoAntrian.setText(list.get(1));
    }

    public static String getNoTransaksi() {
        NumberFormat nf = new DecimalFormat("00000");
        String NoTransaksi = null;
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `NoTransaksi` FROM `tbperawatan` ORDER BY `NoTransaksi` DESC LIMIT 1");
        try {
            ResultSet rs = runSelct.excute();
            if (!rs.isBeforeFirst()) {
                NoTransaksi = "PR-" + "00001" + "-" + FDateF.datetostr(new Date(), "YY");//PR-00001-18
            }
            while (rs.next()) {
                String noTransaksi = rs.getString("NoTransaksi");
                String number = noTransaksi.substring(3, 8);
                String year = noTransaksi.substring(9, 11);
                int p;
                if (year.equals(FDateF.datetostr(new Date(), "YY"))) {
                    p = 1 + parseInt(number);
                } else {
                    p = 1;
                }
                if (p != 99999) {
                    NoTransaksi = "PR-" + nf.format(p) + "-" + FDateF.datetostr(new Date(), "YY");
                } else if (p == 99999) {
                    p = 1;
                    NoTransaksi = "PR-" + nf.format(p) + "-" + FDateF.datetostr(new Date(), "YY");
                }
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Generate Nomor Transaksi");
        } finally {
//            runSelct.closecon();
        }
        return NoTransaksi;
    }

    boolean checkInput() {
        if (JDTanggal.getDate() == null) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Tanggal Tidak Boleh Kosong");
            return false;
        } else if (JTNoTransaksi.getText().isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. No. Transaksi Boleh Kosong");
            return false;
//        } else if (JCNamaDokter.getSelectedIndex() == 0) {
//            JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Pilih Nama Dokter Terlebih Dahulu.");
//            return false;
//        } else if (JTableTindakan.getRowCount() == 0) {
//            JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Isi Tindakan Terlebih Dahulu.");
//            return false;
//        } else if (JTableObat.getRowCount() == 0) {
//            JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Isi Obat Terlebih Dahulu.");
//            return false;
        } else {
            return true;
        }
    }

    boolean checkTableTindakan() {
        if (JCTindakan.getSelectedIndex() == 0) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Pilih Tindakan Terlebih Dahulu.");
            return false;
        } else if (JTJumlahTindakan.getNumberFormattedText().replace("0", "").isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Jumlah Tidak Boleh Kosong.");
            return false;
        } else if (cekDoubleTindakan(JCTindakan.getSelectedItem().toString())) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Tidak Bisa Input Tindakan Yang Sama.");
            JCTindakan.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    boolean cekDoubleTindakan(String item) {
        for (int i = 0; i < JTableTindakan.getRowCount(); i++) {
            if (item.equals(JTableTindakan.getValueAt(i, 0)) && i != JTableTindakan.getSelectedRow()) {
                return true;
            }
        }
        return false;
    }

    boolean checkTableObat() {
        if (JCObat.getSelectedIndex() == 0) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Pilih Obat Terlebih Dahulu.");
            return false;
        } else if (JTJumlahObat.getNumberFormattedText().replace("0", "").isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Jumlah Tidak Boleh Kosong.");
            return false;
        } else if (cekDoubleObat(JCObat.getSelectedItem().toString())) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Tidak Bisa Input Obat Yang Sama.");
            JCObat.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    boolean cekDoubleObat(String item) {
        for (int i = 0; i < JTableObat.getRowCount(); i++) {
            if (item.equals(JTableObat.getValueAt(i, 0)) && i != JTableObat.getSelectedRow()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlableF2 = new KomponenGUI.JlableF();
        jlableF3 = new KomponenGUI.JlableF();
        JTNamaPasien = new KomponenGUI.JtextF();
        jlableF4 = new KomponenGUI.JlableF();
        jlableF5 = new KomponenGUI.JlableF();
        JCNamaDokter = new KomponenGUI.JcomboboxF();
        jlableF6 = new KomponenGUI.JlableF();
        jlableF7 = new KomponenGUI.JlableF();
        jlableF8 = new KomponenGUI.JlableF();
        jlableF9 = new KomponenGUI.JlableF();
        JTKeluhanPasien = new KomponenGUI.JtextF();
        jlableF10 = new KomponenGUI.JlableF();
        jlableF11 = new KomponenGUI.JlableF();
        JTDiagnosaPasien = new KomponenGUI.JtextF();
        jlableF12 = new KomponenGUI.JlableF();
        jlableF13 = new KomponenGUI.JlableF();
        JTCatatanPasien = new KomponenGUI.JtextF();
        JCNamaBeautician = new KomponenGUI.JcomboboxF();
        jPanel1 = new javax.swing.JPanel();
        JBTambahTindakan = new KomponenGUI.JbuttonF();
        JTJumlahTindakan = new KomponenGUI.JPlaceHolder();
        JCTindakan = new KomponenGUI.JcomboboxF();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableTindakan = new KomponenGUI.JtableF();
        JBHapusTindakan = new KomponenGUI.JbuttonF();
        JBRefreshTindakan = new KomponenGUI.JbuttonF();
        jPanel2 = new javax.swing.JPanel();
        JBTambahObat = new KomponenGUI.JbuttonF();
        JTJumlahObat = new KomponenGUI.JPlaceHolder();
        JCObat = new KomponenGUI.JcomboboxF();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableObat = new KomponenGUI.JtableF();
        JBHapusObat = new KomponenGUI.JbuttonF();
        JBRefreshObat = new KomponenGUI.JbuttonF();
        JBTambah = new KomponenGUI.JbuttonF();
        JDTanggal = new KomponenGUI.JdateCF();
        jlableF14 = new KomponenGUI.JlableF();
        jlableF15 = new KomponenGUI.JlableF();
        jlableF16 = new KomponenGUI.JlableF();
        JTNoTransaksi = new KomponenGUI.JtextF();
        jlableF17 = new KomponenGUI.JlableF();
        JBKembali = new KomponenGUI.JbuttonF();
        JBUbah = new KomponenGUI.JbuttonF();
        jSeparator1 = new javax.swing.JSeparator();
        jlableF18 = new KomponenGUI.JlableF();
        jSeparator2 = new javax.swing.JSeparator();
        jlableF19 = new KomponenGUI.JlableF();
        jlableF20 = new KomponenGUI.JlableF();
        jlableF21 = new KomponenGUI.JlableF();
        JTNoAntrian = new KomponenGUI.JtextF();
        JBTambahPrint = new KomponenGUI.JbuttonF();
        JBUbahPrint = new KomponenGUI.JbuttonF();
        jbuttonF1 = new KomponenGUI.JbuttonF();
        jlableF1 = new KomponenGUI.JlableF();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jlableF2.setText("Nama Pasien");

        jlableF3.setText(":");

        JTNamaPasien.setEnabled(false);

        jlableF4.setText("Nama Dokter");

        jlableF5.setText(":");

        JCNamaDokter.load("SELECT '-- Pilih Nama Dokter --' as 'NamaDokter' UNION ALL SELECT `NamaDokter` FROM `tbmdokter` WHERE `Status` = 1 ");
        JCNamaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCNamaDokterKeyPressed(evt);
            }
        });

        jlableF6.setText("Nama Beautician");

        jlableF7.setText(":");

        jlableF8.setText("Keluhan Pasien");

        jlableF9.setText(":");

        JTKeluhanPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTKeluhanPasienKeyPressed(evt);
            }
        });

        jlableF10.setText("Diagnosa Pasien");

        jlableF11.setText(":");

        JTDiagnosaPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTDiagnosaPasienKeyPressed(evt);
            }
        });

        jlableF12.setText("Catatan Pasien");

        jlableF13.setText(":");

        JTCatatanPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTCatatanPasienKeyPressed(evt);
            }
        });

        JCNamaBeautician.load("SELECT '-- Pilih Nama Beautician --' as 'Nama Beautician' UNION ALL SELECT `NamaBeautician` FROM `tbmbeautician` WHERE `Status` = 1");
        JCNamaBeautician.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCNamaBeauticianKeyPressed(evt);
            }
        });

        JBTambahTindakan.setText("Tambah");
        JBTambahTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTambahTindakanActionPerformed(evt);
            }
        });

        JTJumlahTindakan.setPlaceholder("Jumlah");
        JTJumlahTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTJumlahTindakanKeyPressed(evt);
            }
        });

        JCTindakan.load("SELECT '-- Pilih Tindakan --' as 'NamaTindakan' UNION ALL (SELECT `NamaTindakan` FROM `tbmtindakan` WHERE `Status` = 1 ORDER BY `NamaTindakan`)");
        JCTindakan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCTindakanItemStateChanged(evt);
            }
        });
        JCTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCTindakanKeyPressed(evt);
            }
        });

        JTableTindakan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tindakan", "Jumlah"
            }
        ));
        JTableTindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableTindakanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableTindakan);
        if (JTableTindakan.getColumnModel().getColumnCount() > 0) {
            JTableTindakan.getColumnModel().getColumn(0).setMinWidth(668);
            JTableTindakan.getColumnModel().getColumn(0).setPreferredWidth(668);
            JTableTindakan.getColumnModel().getColumn(0).setMaxWidth(668);
        }
        JTableTindakan.setrender(new int[]{1,2}, new String[]{"Number","Number"});

        JBHapusTindakan.setText("Hapus");
        JBHapusTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBHapusTindakanActionPerformed(evt);
            }
        });

        JBRefreshTindakan.setText("Refresh");
        JBRefreshTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRefreshTindakanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JCTindakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTJumlahTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JBHapusTindakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTambahTindakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBRefreshTindakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambahTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTJumlahTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JBHapusTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBRefreshTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JBTambahObat.setText("Tambah");
        JBTambahObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTambahObatActionPerformed(evt);
            }
        });

        JTJumlahObat.setPlaceholder("Jumlah");
        JTJumlahObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTJumlahObatKeyPressed(evt);
            }
        });

        JCObat.load("SELECT '-- Pilih Obat --' as 'NamaBarang' UNION ALL (SELECT `NamaBarang` FROM `tbmbarang` WHERE `Status` = 1 ORDER BY `NamaBarang`)");
        JCObat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCObatItemStateChanged(evt);
            }
        });
        JCObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCObatKeyPressed(evt);
            }
        });

        JTableObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Obat", "Jumlah"
            }
        ));
        JTableObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableObatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTableObat);
        if (JTableObat.getColumnModel().getColumnCount() > 0) {
            JTableObat.getColumnModel().getColumn(0).setMinWidth(668);
            JTableObat.getColumnModel().getColumn(0).setPreferredWidth(668);
            JTableObat.getColumnModel().getColumn(0).setMaxWidth(668);
        }
        JTableObat.setrender(new int[]{1,2}, new String[]{"Number","Number"});

        JBHapusObat.setText("Hapus");
        JBHapusObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBHapusObatActionPerformed(evt);
            }
        });

        JBRefreshObat.setText("Refresh");
        JBRefreshObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRefreshObatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JCObat, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTJumlahObat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JBHapusObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTambahObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBRefreshObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambahObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTJumlahObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JBHapusObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBRefreshObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JBTambah.setText("Simpan");
        JBTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTambahActionPerformed(evt);
            }
        });

        JDTanggal.setDate(new Date());
        JDTanggal.setDateFormatString("dd-MM-yyyy");
        JDTanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JDTanggalPropertyChange(evt);
            }
        });
        JDTanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JDTanggalKeyPressed(evt);
            }
        });

        jlableF14.setText(":");

        jlableF15.setText("Tanggal");

        jlableF16.setText(":");

        JTNoTransaksi.setEnabled(false);

        jlableF17.setText("No. Transaksi");

        JBKembali.setText("Kembali");
        JBKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBKembaliActionPerformed(evt);
            }
        });

        JBUbah.setText("Ubah");
        JBUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBUbahActionPerformed(evt);
            }
        });

        jlableF18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlableF18.setText("-- Tindakan Pasien --");
        jlableF18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jlableF19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlableF19.setText("-- Obat & Injeksi Pasien --");
        jlableF19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jlableF20.setText("Antrian No.");

        jlableF21.setText(":");

        JTNoAntrian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTNoAntrian.setEnabled(false);

        JBTambahPrint.setText("Simpan & Print");
        JBTambahPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTambahPrintActionPerformed(evt);
            }
        });

        JBUbahPrint.setText("Ubah & Print");
        JBUbahPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBUbahPrintActionPerformed(evt);
            }
        });

        jbuttonF1.setText("Ambil Gambar");
        jbuttonF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF1ActionPerformed(evt);
            }
        });

        jlableF1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlableF1.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JBKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBUbahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTambahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlableF19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlableF18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jlableF15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlableF12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlableF10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlableF8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlableF2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlableF4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlableF6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlableF14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JDTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlableF17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlableF16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlableF13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTCatatanPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JCNamaBeautician, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JCNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JTKeluhanPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JTDiagnosaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jlableF20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jlableF21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(JTNoAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(JTNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(18, 18, 18)
                                        .addComponent(jlableF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlableF15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JDTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlableF20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTNoAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCNamaBeautician, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTKeluhanPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTDiagnosaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jlableF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlableF12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTCatatanPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlableF18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlableF19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBUbahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBTambahTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahTindakanActionPerformed
        if (JBTambahTindakan.getText().equals("Tambah")) {
            tambahTableTindakan();
        } else {
            ubahTableTindakan();
        }
    }//GEN-LAST:event_JBTambahTindakanActionPerformed

    private void JCTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTJumlahTindakan.requestFocus();
        }
    }//GEN-LAST:event_JCTindakanKeyPressed

    private void JTJumlahTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTJumlahTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JBTambahTindakan.getText().equals("Tambah")) {
                tambahTableTindakan();
            } else {
                ubahTableTindakan();
            }
        }
    }//GEN-LAST:event_JTJumlahTindakanKeyPressed

    private void JBTambahObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahObatActionPerformed
        if (JBTambahObat.getText().equals("Tambah")) {
            tambahTableObat();
        } else {
            ubahTableObat();
        }
    }//GEN-LAST:event_JBTambahObatActionPerformed

    private void JTJumlahObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTJumlahObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JBTambahObat.getText().equals("Tambah")) {
                tambahTableObat();
            } else {
                ubahTableObat();
            }
        }
    }//GEN-LAST:event_JTJumlahObatKeyPressed

    private void JCObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTJumlahTindakan.requestFocus();
        }
    }//GEN-LAST:event_JCObatKeyPressed

    private void JDTanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JDTanggalPropertyChange

    }//GEN-LAST:event_JDTanggalPropertyChange

    private void JDTanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JDTanggalKeyPressed

    }//GEN-LAST:event_JDTanggalKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (JBTambah.isVisible()) {
            tambahPerawatan = null;
        } else {
            ubahPerawatan = null;
        }
    }//GEN-LAST:event_formWindowClosed

    private void JBHapusTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBHapusTindakanActionPerformed
        hapusTableTindakan();
    }//GEN-LAST:event_JBHapusTindakanActionPerformed

    private void JBHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBHapusObatActionPerformed
        hapusTableObat();
    }//GEN-LAST:event_JBHapusObatActionPerformed

    private void JBTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahActionPerformed
        tambahData(false);
    }//GEN-LAST:event_JBTambahActionPerformed

    private void JBUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBUbahActionPerformed
        ubahData(false);
    }//GEN-LAST:event_JBUbahActionPerformed

    private void JCTindakanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCTindakanItemStateChanged

    }//GEN-LAST:event_JCTindakanItemStateChanged

    private void JCObatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCObatItemStateChanged

    }//GEN-LAST:event_JCObatItemStateChanged
    private void JBKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBKembaliActionPerformed
        dispose();
    }//GEN-LAST:event_JBKembaliActionPerformed

    private void JCNamaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCNamaDokterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JCNamaBeautician.requestFocus();
        }
    }//GEN-LAST:event_JCNamaDokterKeyPressed

    private void JTKeluhanPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTKeluhanPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTDiagnosaPasien.requestFocus();
        }
    }//GEN-LAST:event_JTKeluhanPasienKeyPressed

    private void JCNamaBeauticianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCNamaBeauticianKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTKeluhanPasien.requestFocus();
        }
    }//GEN-LAST:event_JCNamaBeauticianKeyPressed

    private void JTDiagnosaPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTDiagnosaPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTCatatanPasien.requestFocus();
        }
    }//GEN-LAST:event_JTDiagnosaPasienKeyPressed

    private void JTCatatanPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTCatatanPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JCTindakan.requestFocus();
        }
    }//GEN-LAST:event_JTCatatanPasienKeyPressed

    private void JTableTindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableTindakanMouseClicked
        if (JTableTindakan.getSelectedRow() != -1) {
            JCTindakan.setSelectedItem(JTableTindakan.getValueAt(JTableTindakan.getSelectedRow(), 0).toString());
            JTJumlahTindakan.setText(JTableTindakan.getValueAt(JTableTindakan.getSelectedRow(), 1).toString());
            JBTambahTindakan.setText("Ubah");
        } else {
            JBTambahTindakan.setText("Tambah");
        }
    }//GEN-LAST:event_JTableTindakanMouseClicked

    private void JTableObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableObatMouseClicked
        if (JTableTindakan.getSelectedRow() != -1) {
            JCObat.setSelectedItem(JTableObat.getValueAt(JTableObat.getSelectedRow(), 0).toString());
            JTJumlahObat.setText(JTableObat.getValueAt(JTableObat.getSelectedRow(), 1).toString());
            JBTambahObat.setText("Ubah");
        } else {
            JBTambahObat.setText("Tambah");
        }
    }//GEN-LAST:event_JTableObatMouseClicked

    private void JBRefreshTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRefreshTindakanActionPerformed
        refreshTindakan();
    }//GEN-LAST:event_JBRefreshTindakanActionPerformed

    private void JBRefreshObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRefreshObatActionPerformed
        refreshObat();
    }//GEN-LAST:event_JBRefreshObatActionPerformed

    private void JBTambahPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahPrintActionPerformed
        tambahData(true);
    }//GEN-LAST:event_JBTambahPrintActionPerformed

    private void JBUbahPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBUbahPrintActionPerformed
        ubahData(true);
    }//GEN-LAST:event_JBUbahPrintActionPerformed

    private void jbuttonF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF1ActionPerformed
        if (jbuttonF1.getText().equals("Ambil Gambar")) {
            jbuttonF1.setText("Save");
            webSource = new VideoCapture(0);
            webSource.set(CAP_PROP_FRAME_WIDTH, 640);
            webSource.set(CAP_PROP_FRAME_HEIGHT, 360);
            myThread = new DaemonThread();
            Thread t = new Thread(myThread);
            t.setDaemon(true);
            myThread.runnable = true;
            t.start();
        } else {
            jbuttonF1.setText("Ambil Gambar");
            myThread.runnable = false;
            webSource.release();
        }
    }//GEN-LAST:event_jbuttonF1ActionPerformed

//    public BufferedImage getImage(JPanel panel) {
//
//        int w = panel.getWidth();
//        int h = panel.getHeight();
//        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g = bi.createGraphics();
//        panel.paint(g);
//        g.dispose();
//        return bi;
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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
            java.util.logging.Logger.getLogger(Perawatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Perawatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Perawatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Perawatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Perawatan("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private KomponenGUI.JbuttonF JBHapusObat;
    private KomponenGUI.JbuttonF JBHapusTindakan;
    private KomponenGUI.JbuttonF JBKembali;
    private KomponenGUI.JbuttonF JBRefreshObat;
    private KomponenGUI.JbuttonF JBRefreshTindakan;
    private KomponenGUI.JbuttonF JBTambah;
    private KomponenGUI.JbuttonF JBTambahObat;
    private KomponenGUI.JbuttonF JBTambahPrint;
    private KomponenGUI.JbuttonF JBTambahTindakan;
    private KomponenGUI.JbuttonF JBUbah;
    private KomponenGUI.JbuttonF JBUbahPrint;
    private KomponenGUI.JcomboboxF JCNamaBeautician;
    private KomponenGUI.JcomboboxF JCNamaDokter;
    private KomponenGUI.JcomboboxF JCObat;
    private KomponenGUI.JcomboboxF JCTindakan;
    private static KomponenGUI.JdateCF JDTanggal;
    private KomponenGUI.JtextF JTCatatanPasien;
    private KomponenGUI.JtextF JTDiagnosaPasien;
    private KomponenGUI.JPlaceHolder JTJumlahObat;
    private KomponenGUI.JPlaceHolder JTJumlahTindakan;
    private KomponenGUI.JtextF JTKeluhanPasien;
    private KomponenGUI.JtextF JTNamaPasien;
    private KomponenGUI.JtextF JTNoAntrian;
    private KomponenGUI.JtextF JTNoTransaksi;
    private KomponenGUI.JtableF JTableObat;
    private KomponenGUI.JtableF JTableTindakan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private KomponenGUI.JbuttonF jbuttonF1;
    private KomponenGUI.JlableF jlableF1;
    private KomponenGUI.JlableF jlableF10;
    private KomponenGUI.JlableF jlableF11;
    private KomponenGUI.JlableF jlableF12;
    private KomponenGUI.JlableF jlableF13;
    private KomponenGUI.JlableF jlableF14;
    private KomponenGUI.JlableF jlableF15;
    private KomponenGUI.JlableF jlableF16;
    private KomponenGUI.JlableF jlableF17;
    private KomponenGUI.JlableF jlableF18;
    private KomponenGUI.JlableF jlableF19;
    private KomponenGUI.JlableF jlableF2;
    private KomponenGUI.JlableF jlableF20;
    private KomponenGUI.JlableF jlableF21;
    private KomponenGUI.JlableF jlableF3;
    private KomponenGUI.JlableF jlableF4;
    private KomponenGUI.JlableF jlableF5;
    private KomponenGUI.JlableF jlableF6;
    private KomponenGUI.JlableF jlableF7;
    private KomponenGUI.JlableF jlableF8;
    private KomponenGUI.JlableF jlableF9;
    // End of variables declaration//GEN-END:variables

    void tambahTableTindakan() {
        if (checkTableTindakan()) {
            DefaultTableModel model = (DefaultTableModel) JTableTindakan.getModel();
            model.addRow(new Object[]{JCTindakan.getSelectedItem(), JTJumlahTindakan.getText()});
            refreshTindakan();
        }
    }

    void tambahTableObat() {
        if (checkTableObat()) {
            DefaultTableModel model = (DefaultTableModel) JTableObat.getModel();
            model.addRow(new Object[]{JCObat.getSelectedItem(), JTJumlahObat.getText()});
            refreshObat();
        }
    }

    void hapusTableTindakan() {
        if (JTableTindakan.getSelectedRow() != -1) {
            ((DefaultTableModel) JTableTindakan.getModel()).removeRow(JTableTindakan.getSelectedRow());
            refreshTindakan();
        }
    }

    void hapusTableObat() {
        if (JTableObat.getSelectedRow() != -1) {
            ((DefaultTableModel) JTableObat.getModel()).removeRow(JTableObat.getSelectedRow());
            refreshObat();
        }
    }

    void ubahTableTindakan() {
        if (JTableTindakan.getSelectedRow() != -1) {
            if (checkTableTindakan()) {
                JTableTindakan.setValueAt(JCTindakan.getSelectedItem(), JTableTindakan.getSelectedRow(), 0);
                JTableTindakan.setValueAt(JTJumlahTindakan.getText(), JTableTindakan.getSelectedRow(), 1);
                refreshTindakan();
            }
        }
    }

    void ubahTableObat() {
        if (JTableObat.getSelectedRow() != -1) {
            if (checkTableObat()) {
                JTableObat.setValueAt(JCObat.getSelectedItem(), JTableObat.getSelectedRow(), 0);
                JTableObat.setValueAt(JTJumlahObat.getText(), JTableObat.getSelectedRow(), 1);
                refreshObat();
            }
        }
    }

    void refreshTindakan() {
        JCTindakan.setSelectedIndex(0);
        JTJumlahTindakan.setText("");
        JTableTindakan.clearSelection();
        JBTambahTindakan.setText("Tambah");
        JCTindakan.requestFocus();
    }

    void refreshObat() {
        JCObat.setSelectedIndex(0);
        JTJumlahObat.setText("");
        JTableObat.clearSelection();
        JBTambahObat.setText("Tambah");
        JCObat.requestFocus();
    }

    void tambahData(boolean print) {
        if (checkInput()) {
            boolean Berhasil;
            MultiInsert multiInsert = new MultiInsert();
            Berhasil = multiInsert.OpenConnection();
            if (Berhasil) {
                Berhasil = multiInsert.setautocomit(false);
                if (Berhasil) {
                    Berhasil = multiInsert.Excute("INSERT INTO `tbperawatan`(`Tanggal`, `NoTransaksi`, `IdAntrian`, `IdDokter`, `IdBeautician`, `Keluhan`, `Diagnosa`, `Catatan`, `UrlGambar`) VALUES ('" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "','" + JTNoTransaksi.getText() + "', '" + Parameter + "', (SELECT `IdDokter` FROM `tbmdokter` WHERE `NamaDokter` = '" + JCNamaDokter.getSelectedItem() + "'),(SELECT `IdBeautician` FROM `tbmbeautician` WHERE `NamaBeautician` = '" + JCNamaBeautician.getSelectedItem() + "'),'" + JTKeluhanPasien.getText() + "','" + JTDiagnosaPasien.getText() + "','" + JTCatatanPasien.getText() + "', '\\\\\\\\127.0.0.1\\\\ShareKusuma\\\\" + JTNamaPasien.getText() + " - " + JTNoTransaksi.getText() + ".jpg')", null);
                    if (Berhasil) {
                        for (int i = 0; i < JTableTindakan.getRowCount(); i++) {
                            Berhasil = multiInsert.Excute("INSERT INTO `tbperawatandetail`(`NoTransaksi`, `IdTindakan`, `Jumlah`) VALUES ('" + JTNoTransaksi.getText() + "',(SELECT `IdTindakan` FROM `tbmtindakan` WHERE `NamaTindakan` = '" + JTableTindakan.getValueAt(i, 0) + "'),'" + JTableTindakan.getValueAt(i, 1).toString().replace(".", "") + "')", null);
                        }
                        if (Berhasil) {
                            for (int j = 0; j < JTableObat.getRowCount(); j++) {
                                Berhasil = multiInsert.Excute("INSERT INTO `tbobatdetail`(`NoTransaksi`, `IdObat`, `Jumlah`) VALUES ('" + JTNoTransaksi.getText() + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTableObat.getValueAt(j, 0) + "'),'" + JTableObat.getValueAt(j, 1).toString().replace(".", "") + "')", null);
                            }
                            if (Berhasil) {
                                if (frame.width() != 0) {
                                    Berhasil = Imgcodecs.imwrite("\\\\127.0.0.1\\ShareKusuma\\" + JTNamaPasien.getText() + " - " + JTNoTransaksi.getText() + ".jpg", frame);
                                }
                                if (Berhasil) {
                                    Berhasil = multiInsert.Excute("UPDATE `tbantrian` SET `Status` = 1 WHERE `IdAntrian` = '" + Parameter + "'", null);
                                }
                            }

                        }
                    }
                }
                if (Berhasil == false) {
                    multiInsert.rollback();
//                    multiInsert.closecon();
                    JOptionPaneF.showMessageDialog(this, "Gagal Tambah Data Perawatan");
                }
                if (Berhasil == true) {
                    JOptionPaneF.showMessageDialog(this, "Berhasil Tambah Data Perawatan");
                    multiInsert.Commit();
//                    multiInsert.closecon();
                    if (print) {
                        printing();
                    }
                    if (listPerawatan != null) {
                        listPerawatan.load();
                    }
                    if (listAntrian != null) {
                        listAntrian.load();
                        if (listAntrian.jcomCari1.jtablef.getRowCount() == 0) {
                            listAntrian.dispose();
                        }
                    }
                    dispose();
                }
            }
        }
    }

    void ubahData(boolean print) {
        if (checkInput()) {
            boolean Berhasil;
            MultiInsert multiInsert = new MultiInsert();
            Berhasil = multiInsert.OpenConnection();
            if (Berhasil) {
                Berhasil = multiInsert.setautocomit(false);
                if (Berhasil) {
                    Berhasil = multiInsert.Excute("UPDATE `tbperawatan` SET `Tanggal`='" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "',`NoTransaksi`='" + JTNoTransaksi.getText() + "',`IdDokter`=(SELECT `IdDokter` FROM `tbmdokter` WHERE `NamaDokter` = '" + JCNamaDokter.getSelectedItem() + "'),`IdBeautician`=(SELECT `IdBeautician` FROM `tbmbeautician` WHERE `NamaBeautician` = '" + JCNamaBeautician.getSelectedItem() + "'),`Keluhan`='" + JTKeluhanPasien.getText() + "',`Diagnosa`='" + JTDiagnosaPasien.getText() + "',`Catatan`='" + JTCatatanPasien.getText() + "', `UrlGambar`='\\\\\\\\127.0.0.1\\\\ShareKusuma\\\\" + JTNamaPasien.getText() + " - " + JTNoTransaksi.getText() + ".jpg' WHERE `IdPerawatan` = '" + Parameter + "'", null);
                    if (Berhasil) {
                        Berhasil = multiInsert.Excute("DELETE FROM `tbperawatandetail` WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'", null);
                        if (Berhasil) {
                            Berhasil = multiInsert.Excute("DELETE FROM `tbobatdetail` WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'", null);
                            if (Berhasil) {
                                for (int i = 0; i < JTableTindakan.getRowCount(); i++) {
                                    Berhasil = multiInsert.Excute("INSERT INTO `tbperawatandetail`(`NoTransaksi`, `IdTindakan`, `Jumlah`) VALUES ('" + JTNoTransaksi.getText() + "',(SELECT `IdTindakan` FROM `tbmtindakan` WHERE `NamaTindakan` = '" + JTableTindakan.getValueAt(i, 0) + "'),'" + JTableTindakan.getValueAt(i, 1).toString().replace(".", "") + "')", null);
                                }
                                if (Berhasil) {
                                    for (int j = 0; j < JTableObat.getRowCount(); j++) {
                                        Berhasil = multiInsert.Excute("INSERT INTO `tbobatdetail`(`NoTransaksi`, `IdObat`, `Jumlah`) VALUES ('" + JTNoTransaksi.getText() + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTableObat.getValueAt(j, 0) + "'),'" + JTableObat.getValueAt(j, 1).toString().replace(".", "") + "')", null);
                                    }
                                    if (Berhasil) {
                                        if (frame.width() != 0) {
                                            Berhasil = Imgcodecs.imwrite("\\\\127.0.0.1\\ShareKusuma\\" + JTNamaPasien.getText() + " - " + JTNoTransaksi.getText() + ".jpg", frame);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (Berhasil == false) {
                    multiInsert.rollback();
//                    multiInsert.closecon();
                    JOptionPaneF.showMessageDialog(this, "Gagal Ubah Data Perawatan");
                }
                if (Berhasil == true) {
                    JOptionPaneF.showMessageDialog(this, "Berhasil Ubah Data Perawatan");
                    multiInsert.Commit();
//                    multiInsert.closecon();
                    if (print) {
                        printing();
                    }
                    dispose();
                    ubahPerawatan = null;
                    if (listPerawatan != null) {
                        listPerawatan.load();
                    }
                }
            }
        }
    }

    void printing() {
        PrintResep pr = new PrintResep();
        Object printitem[][] = getTableData(JTableTindakan, JTableObat);
        setItems(printitem);
        total_item_count = itemsTable.getRowCount();
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(pr, getPageFormat(pj));
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printer : printServices) {
            if (printer.getName().equals("Microsoft Print to PDF")) {
                try {
                    pj.setPrintService(printer);
                    pj.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    void setItems(Object[][] printitem) {
        Object data[][] = printitem;
        DefaultTableModel model = new DefaultTableModel();
        //assume jtable has 4 columns.
        model.addColumn(title[0]);
        model.addColumn(title[1]);

        int rowcount = printitem.length;

        addtomodel(model, data, rowcount);

        itemsTable = new JTable(model);
    }

    void addtomodel(DefaultTableModel model, Object[][] data, int rowcount) {
        int count = 0;
        while (count < rowcount) {
            model.addRow(data[count]);
            count++;
        }
        if (model.getRowCount() != rowcount) {
            addtomodel(model, data, rowcount);
        }

//        System.out.println("Check Passed.");
    }

    Object[][] getTableData(JTable table1, JTable table2) {
        int itemcount = table1.getRowCount() + table2.getRowCount() + 1;
//        System.out.println("Item Count:" + itemcount);

        DefaultTableModel dtm1 = (DefaultTableModel) table1.getModel();
        DefaultTableModel dtm2 = (DefaultTableModel) table2.getModel();
        int nRow1 = dtm1.getRowCount(), nCol1 = dtm1.getColumnCount();
        int nRow2 = dtm2.getRowCount(), nCol2 = dtm2.getColumnCount();
        Object[][] tableData = new Object[nRow1 + nRow2 + 1][nCol1 + nCol2];
        if (itemcount == nRow1 + nRow2 + 1) {
            int x = 0;
            for (int i = 0; i < nRow1; i++) {
                for (int j = 0; j < nCol1; j++) {
                    tableData[x][j] = j == 0 ? "R / " + dtm1.getValueAt(i, j) : dtm1.getValueAt(i, j);
                }
                x++;
            }
            tableData[x][0] = "";
            tableData[x][1] = "";
            x++;
            for (int i = 0; i < nRow2; i++) {
                for (int j = 0; j < nCol2; j++) {
                    tableData[x][j] = j == 0 ? "R / " + dtm2.getValueAt(i, j) : dtm2.getValueAt(i, j);
                }
                x++;
            }
//            System.out.println("Data check passed");
        }
        return tableData;
    }

    PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight = total_item_count * 13;
        double headerHeight = 4.58727;
        double footerHeight = 2.1;

        double width = convert_CM_To_PPI(7);
        double height = (convert_CM_To_PPI(headerHeight + footerHeight)) + middleHeight;
        paper.setSize(width, height);
        paper.setImageableArea(convert_CM_To_PPI(0.25), convert_CM_To_PPI(0.5), width - convert_CM_To_PPI(0.35), height - convert_CM_To_PPI(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    double toPPI(double inch) {
        return inch * 72d;
    }

    String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());

    }

    static JTable itemsTable;
    int total_item_count = 0;
    String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss a";
    String title[] = new String[]{"Nama Barang", "Jumlah"};
    String NoSIP, AlamatPasien;

    class PrintResep implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal Select Alamat Pasien");
            dRunSelctOne.setQuery("SELECT `Alamat` FROM `tbmpasien` WHERE `KodePasien` = '" + JTNamaPasien.getText().split("\\(")[1].split("\\)")[0] + "'");
            ArrayList<String> list = dRunSelctOne.excute();
            AlamatPasien = list.get(0);
            dRunSelctOne.seterorm("Gagal Select NoSIP Dokter");
            dRunSelctOne.setQuery("SELECT `NoSIP` FROM `tbmdokter` WHERE `NamaDokter` = '" + JCNamaDokter.getSelectedItem() + "'");
            list = dRunSelctOne.excute();
            NoSIP = list.get(0);
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
                try {
                    int x = 3;
                    int y = 40;
                    int w = 180;
                    int imagewidth = 120;
                    int imageheight = 40;
                    BufferedImage read = ImageIO.read(getClass().getResource("/Resource/logo.png"));
                    g2d.drawImage(read, x + ((w - imagewidth) / 2), 0, imagewidth, imageheight, null);
                    Font font = new Font("Times New Roman", Font.BOLD, 15);
                    g2d.setFont(font);
                    g2d.drawString(JCNamaDokter.getSelectedItem().toString(), x + ((w - g2d.getFontMetrics().stringWidth(JCNamaDokter.getSelectedItem().toString())) / 2), y += 10);

                    font = new Font("Times New Roman", Font.PLAIN, 10);
                    g2d.setFont(font);
                    g2d.drawString("SIP No. " + NoSIP, x + ((w - g2d.getFontMetrics().stringWidth("SIP No. " + NoSIP)) / 2), y += 15);

                    font = new Font("Times New Roman", Font.PLAIN, 7);
                    g2d.setFont(font);
                    g2d.drawString("Jl. Kompol Zainal Abidin No.05-06 (0741-7553068)", x + ((w - g2d.getFontMetrics().stringWidth("Jl. Kompol Zainal Abidin No.05-06 (0741-7553068)")) / 2), y += 10);
                    g2d.drawString("Tanjung Pinang - Jambi", x + ((w - g2d.getFontMetrics().stringWidth("Tanjung Pinang - Jambi")) / 2), y += 10);
                    g2d.drawLine(x, y += 10, 180, y);

                    font = new Font("Times New Roman", Font.PLAIN, 10);
                    g2d.setFont(font);
                    g2d.drawString("Tgl Resep : " + now(), x, y += 10);

                    int cH = y + 10;
                    TableModel mod = itemsTable.getModel();
//                    g2d.drawString("R / ", x, 90);

                    font = new Font("Times New Roman", Font.PLAIN, 12);
                    g2d.setFont(font);
                    for (int i = 0; i < mod.getRowCount(); i++) {
                        String NamaBarang = mod.getValueAt(i, 0).toString();
                        String Jumlah = mod.getValueAt(i, 1).toString();

                        cH += 13;

                        g2d.drawString(NamaBarang, x, cH);
                        g2d.drawString(Jumlah, x + 150 + (15 - g2d.getFontMetrics().stringWidth(Jumlah)), cH);

                    }

                    font = new Font("Times New Roman", Font.PLAIN, 10);
                    g2d.setFont(font);
                    g2d.drawString("Pasien  : " + JTNamaPasien.getText(), x, cH += 30);
                    g2d.drawString("Alamat : " + AlamatPasien, x, cH += 10);
                } catch (Exception r) {
                    r.printStackTrace();
                }
                result = PAGE_EXISTS;
            }
            return result;
        }
    }

}

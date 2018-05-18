/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;

import static GlobalVar.Var.*;
import KomponenGUI.FDateF;
import static KomponenGUI.FDateF.datetostr;
import Master.*;
import LSubProces.DRunSelctOne;
import LSubProces.Insert;
import LSubProces.MultiInsert;
import LSubProces.RunSelct;
import LSubProces.Update;
import java.awt.Color;
import static java.awt.Frame.NORMAL;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.String.format;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import static javax.print.DocFlavor.INPUT_STREAM.AUTOSENSE;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import static javax.print.PrintServiceLookup.lookupDefaultPrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import static javax.print.attribute.Size2DSyntax.INCH;
import javax.print.attribute.standard.Copies;
import static javax.print.attribute.standard.MediaSize.findMedia;
import static javax.print.attribute.standard.OrientationRequested.LANDSCAPE;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import FunctionGUI.JOptionPaneF;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author richky
 */
public class Penjualan extends javax.swing.JFrame {

    /**
     * Creates new form MasterPenjualan
     */
    String IdEdit;

    public Penjualan() {
        initComponents();
        setVisible(true);
        setTitle("Tambah Penjualan");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JBUbah.setVisible(false);
        JBUbahPrint.setVisible(false);
        JCPasien.requestFocus();
        JTNoTransaksi.setText(getNoPenjualan());
        setPoin(0);
    }

    public Penjualan(Object idEdit) {
        IdEdit = idEdit.toString();
        initComponents();
        setVisible(true);
        setTitle("Ubah Penjualan");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JBTambah.setVisible(false);
        JBTambahTutup.setVisible(false);
        JBTambahPrint.setVisible(false);
        loadeditdata();
        JCPasien.requestFocus();
    }

    void cariBarang(String keywordCari) {
        if (jcari == null) {
            jcari = new Jcari("SELECT `JenisBarang`, `NamaBarang` FROM `tbmbarang`a JOIN `tbsmjenisbarang`b ON a.`IdJenisBarang`=b.`IdJenisBarang` WHERE `JenisBarang` ", "SELECT `JenisBarang`, `NamaBarang` FROM `tbmbarang`a JOIN `tbsmjenisbarang`b ON a.`IdJenisBarang`=b.`IdJenisBarang` WHERE `NamaBarang` ", "Jenis Barang", "Nama Barang", "Cari Barang", 1, JTNamaBarang, JTJumlah, keywordCari);
        } else {
            jcari.setState(NORMAL);
            jcari.toFront();
        }
    }

    void loadeditdata() {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Eror Gagal Menampilkan Data Penjualan");
        dRunSelctOne.setQuery("SELECT `IdPenjualan` as 'ID', `NoTransaksi` as 'No. Transaksi', DATE_FORMAT(`Tanggal`,'%d-%m-%Y') as 'Tanggal', IFNULL(CONCAT('(', `KodePasien`,') ',`NamaPasien`),'-') as 'Nama Pasien', a.`Keterangan`, FORMAT(`Bayar`,0) as 'Jumlah Bayar', `Diskon`, IF(`StatusPoin`=1,'Poin','Cash'), `Poin` FROM `tbpenjualan`a LEFT JOIN `tbmpasien`b ON a.`IdPasien`=b.`IdPasien` WHERE `IdPenjualan` = '" + IdEdit + "'");
        ArrayList<String> list = dRunSelctOne.excute();
        JTNoTransaksi.setText(list.get(1));
        JDTanggal.setDate(FDateF.strtodate(list.get(2), "dd-MM-yyyy"));
        JCPasien.setSelectedItem(list.get(3));
        JTAKeterangan.setText(list.get(4));
        JTBayar.setInt(list.get(5).replace(",", ""));
        JCBPakaiPoin.setSelected(list.get(7).equals("Poin"));
        DefaultTableModel model = (DefaultTableModel) JTable.getModel();
        model.getDataVector().removeAllElements();
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `IdPenjualanDetail`, `NoTransaksi`, `NoKolom`, `NamaBarang`, FORMAT(`Jumlah`,0) as 'Jumlah', FORMAT(a.`Harga`,0) as 'Harga', FORMAT(`Jumlah`*a.`Harga`,0) as 'Sub Total' FROM `tbpenjualandetail`a JOIN `tbmbarang`b ON a.`IdBarang`=b.`IdBarang` WHERE `NoTransaksi` = '" + list.get(1) + "'");
        try {
            ResultSet rs = runSelct.excute();
            int row = 0;
            while (rs.next()) {
                model.addRow(new Object[]{"", "", "", "", ""});
                JTable.setValueAt(rs.getString(3), row, 0);
                JTable.setValueAt(rs.getString(4), row, 1);
                JTable.setValueAt(rs.getString(5).replace(",", "."), row, 2);
                JTable.setValueAt(rs.getString(6).replace(",", "."), row, 3);
                JTable.setValueAt(rs.getString(7).replace(",", "."), row, 4);
                row++;
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Panggil Data Detail Penjualan");
        } finally {
            runSelct.closecon();
        }
        JTGrandTotal.setText(String.valueOf(getGrandTotal()));
        if (JCBPakaiPoin.isSelected()) {
            JTSetelahPotong.setVisible(true);
            jlableF12.setVisible(true);
            jlableF24.setVisible(true);
            setPoin(Integer.valueOf(list.get(8)));
            JTSetelahPotong.setText(String.valueOf(JTGrandTotal.getInt() - Integer.valueOf(list.get(8)) * 5000));
        } else {
            JTSetelahPotong.setVisible(false);
            jlableF12.setVisible(false);
            jlableF24.setVisible(false);
            JLPoin.setText("Poin (0)");
            JCBPakaiPoin.setEnabled(false);
        }
    }

    Boolean checkInput() {
        if (JDTanggal.getDate() == null) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Tanggal Penjualan Tidak Boleh Kosong");
            return false;
        } else if (JTNoTransaksi.getText().isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. No. Transaksi Boleh Kosong");
            return false;
        } else if (JTable.getRowCount() == 0) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Detail Penjualan Tidak Boleh Kosong");
            return false;
        } else if (JTBayar.getInt() < JTGrandTotal.getInt() && !JCBPakaiPoin.isSelected()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Jumlah Bayar Tidak Mencukupi.");
            return false;
        } else if (JTBayar.getInt() < JTSetelahPotong.getInt() && JCBPakaiPoin.isSelected()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Jumlah Bayar Tidak Mencukupi.");
            return false;
        } else {
            return true;
        }
    }

    public static String getNoPenjualan() {
        NumberFormat nf = new DecimalFormat("000000");
        String NoTransaksi = null;
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `NoTransaksi` FROM `tbpenjualan` ORDER BY `NoTransaksi` DESC LIMIT 1");
        try {
            ResultSet rs = runSelct.excute();
            if (!rs.isBeforeFirst()) {
                NoTransaksi = "KB-" + "000001" + "-PJ";
            }
            while (rs.next()) {
                String nopenjualan = rs.getString("NoTransaksi");
                String number = nopenjualan.substring(3, 9);
                //String month = nopenjualan.substring(8, 10);
                int p = 1 + parseInt(number);
                /*if (month.equals(FDateF.datetostr(new Date(), "MM"))) {
                    p = 1 + parseInt(number);
                } else {
                    p = 1;
                }*/
                if (p != 999999) {
                    NoTransaksi = "KB-" + nf.format(p) + "-PJ";
                } else if (p == 999999) {
                    p = 1;
                    NoTransaksi = "KB-" + nf.format(p) + "-PJ";
                }
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Generate Nomor Penjualan");
        } finally {
            runSelct.closecon();
        }
        return NoTransaksi;
    }

    boolean checkTable() {
        if (JTNamaBarang.getText().replace(" ", "").isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Nama Barang Tidak Boleh Kosong");
            JTNamaBarang.requestFocus();
            return false;
        } else if (JTJumlah.getInt() == 0) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Jumlah Tidak Boleh Kosong");
            JTJumlah.requestFocus();
            return false;
        } else if (JTHargaSatuan.getInt() == 0) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Harga Satuan Tidak Boleh Kosong");
            JTHargaSatuan.requestFocus();
            return false;
        } else if (JTable.getRowCount() > 10) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Jenis Barang Yang Diinput Tidak Bisa Lebih Dari 10");
            return false;
        } else if (JTJumlah.getInt() > Float.parseFloat(JTStock.getText().replace(".", "").replace(",", "."))) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Jumlah Permintaan Tidak Bisa Melebihi Stok");
            JTJumlah.requestFocus();
            return false;
        } else if (cekdoubleitem(JTNamaBarang.getText()) && tambahtable.isEnabled()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Tidak Bisa Input Barang Yang Sama");
            JTNamaBarang.requestFocus();
            return false;
        } else {
            return true;
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

    boolean cekdoubleitem(String item) {
        for (int i = 0; i < JTable.getRowCount(); i++) {
            if (item.equals(JTable.getValueAt(i, 1))) {
                return true;
            }
        }
        return false;
    }

    void RefreshTbl() {
        JTable.clearSelection();
        JTNamaBarang.setText("");
        JTJumlah.setText("0");
        JTHargaSatuan.setText("0");
        JTSubTotal.setText("0");
        JTStock.setText("0");
    }

    void setHarga() {
        if (!JTNamaBarang.getText().replace(" ", "").equals("")) {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal Panggil Data Harga");
            dRunSelctOne.setQuery("SELECT `Harga` FROM `tbmbarang` WHERE `NamaBarang`= '" + JTNamaBarang.getText() + "'");
            ArrayList<String> list = dRunSelctOne.excute();
            JTHargaSatuan.setText(list.get(0));
        } else {
            JTHargaSatuan.setText("0");
        }
    }

    void setSubTotal() {
        int jumlah = JTJumlah.getInt();
        int harga = JTHargaSatuan.getInt();
        int subtotal = jumlah * harga;
        JTSubTotal.setText(String.valueOf(subtotal));
    }

    String getSetelahPotong() {
        if (JTGrandTotal.getInt() - (Integer.valueOf(JLPoin.getText().split("\\(")[1].split("\\)")[0]) * 5000) <= 0) {
            return "0";
        } else {
            return String.valueOf(JTGrandTotal.getInt() - ((Integer.valueOf(JLPoin.getText().split("\\(")[1].split("\\)")[0]) * 5000)));
        }
    }

    Integer getPoinTerpakai() {
        if (JCBPakaiPoin.isSelected()) {
            return (JTGrandTotal.getInt() - JTSetelahPotong.getInt()) / 5000;
        } else {
            return 0;
        }
    }

    void setKembalian() {
        if (!JTBayar.getText().isEmpty()) {
            if (JCBPakaiPoin.isSelected()) {
                JTKembali.setInt(JTBayar.getInt() - JTSetelahPotong.getInt());
            } else {
                JTKembali.setInt(JTBayar.getInt() - JTGrandTotal.getInt());
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        LBNoTransaksi = new KomponenGUI.JlableF();
        jlableF4 = new KomponenGUI.JlableF();
        jlableF10 = new KomponenGUI.JlableF();
        jlableF3 = new KomponenGUI.JlableF();
        JTNoTransaksi = new KomponenGUI.JtextF();
        JBTambah = new KomponenGUI.JbuttonF();
        JBTambahTutup = new KomponenGUI.JbuttonF();
        jbuttonF4 = new KomponenGUI.JbuttonF();
        jlableF6 = new KomponenGUI.JlableF();
        jlableF8 = new KomponenGUI.JlableF();
        JBUbah = new KomponenGUI.JbuttonF();
        jlableF21 = new KomponenGUI.JlableF();
        jlableF22 = new KomponenGUI.JlableF();
        JDTanggal = new KomponenGUI.JdateCF();
        jlableF23 = new KomponenGUI.JlableF();
        jlableF24 = new KomponenGUI.JlableF();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable = new KomponenGUI.JtableF();
        jlabelF7 = new KomponenGUI.JtextF();
        jlabelF8 = new KomponenGUI.JtextF();
        jlabelF9 = new KomponenGUI.JtextF();
        jlabelF10 = new KomponenGUI.JtextF();
        tambahtable = new KomponenGUI.JbuttonF();
        ubahtable = new KomponenGUI.JbuttonF();
        hapustable = new KomponenGUI.JbuttonF();
        hapustable1 = new KomponenGUI.JbuttonF();
        JTStock = new KomponenGUI.JtextF();
        jlabelF11 = new KomponenGUI.JtextF();
        JTHargaSatuan = new KomponenGUI.JRibuanTextField();
        JTSubTotal = new KomponenGUI.JRibuanTextField();
        JCPasien = new KomponenGUI.JcomboboxF();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAKeterangan = new KomponenGUI.JTextAreaF();
        JTGrandTotal = new KomponenGUI.JRibuanTextField();
        jbuttonF7 = new KomponenGUI.JbuttonF();
        JBTambahPrint = new KomponenGUI.JbuttonF();
        JBUbahPrint = new KomponenGUI.JbuttonF();
        jlableF29 = new KomponenGUI.JlableF();
        JTNamaBarang = new KomponenGUI.JtextF();
        jbuttonF11 = new KomponenGUI.JbuttonF();
        JBSearchNamaBarang = new KomponenGUI.JbuttonF();
        JTJumlah = new KomponenGUI.JRibuanTextField();
        JTKembali = new KomponenGUI.JRibuanTextField();
        jlableF11 = new KomponenGUI.JlableF();
        jlableF12 = new KomponenGUI.JlableF();
        JTBayar = new KomponenGUI.JPlaceHolder();
        JLPoin = new KomponenGUI.JlableF();
        jlableF13 = new KomponenGUI.JlableF();
        JCBPakaiPoin = new KomponenGUI.JCheckBoxF();
        jlableF14 = new KomponenGUI.JlableF();
        jlableF25 = new KomponenGUI.JlableF();
        JTSetelahPotong = new KomponenGUI.JRibuanTextField();
        jlableF26 = new KomponenGUI.JlableF();
        jlableF27 = new KomponenGUI.JlableF();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        LBNoTransaksi.setText("No. Transaksi");

        jlableF4.setText("Total");

        jlableF10.setText(":");

        jlableF3.setText("Keterangan");

        JTNoTransaksi.setEnabled(false);

        JBTambah.setText("Tambah");
        JBTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTambahActionPerformed(evt);
            }
        });

        JBTambahTutup.setText("Tambah & Tutup");
        JBTambahTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTambahTutupActionPerformed(evt);
            }
        });

        jbuttonF4.setText("Kembali");
        jbuttonF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF4ActionPerformed(evt);
            }
        });

        jlableF6.setText(":");

        jlableF8.setText(":");

        JBUbah.setText("Ubah");
        JBUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBUbahActionPerformed(evt);
            }
        });

        jlableF21.setText("Pasien");

        jlableF22.setText(":");

        JDTanggal.setDate(new Date());
        JDTanggal.setDateFormatString("dd-MM-yyyy");

        jlableF23.setText("Tanggal");

        jlableF24.setText(":");

        JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama Barang", "Jumlah", "Harga Satuan", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTable);
        if (JTable.getColumnModel().getColumnCount() > 0) {
            JTable.getColumnModel().getColumn(0).setMinWidth(50);
            JTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            JTable.getColumnModel().getColumn(0).setMaxWidth(50);
            JTable.getColumnModel().getColumn(1).setMinWidth(410);
            JTable.getColumnModel().getColumn(1).setPreferredWidth(410);
            JTable.getColumnModel().getColumn(1).setMaxWidth(410);
            JTable.getColumnModel().getColumn(2).setMinWidth(85);
            JTable.getColumnModel().getColumn(2).setPreferredWidth(85);
            JTable.getColumnModel().getColumn(2).setMaxWidth(85);
            JTable.getColumnModel().getColumn(3).setMinWidth(15);
            JTable.getColumnModel().getColumn(3).setPreferredWidth(115);
            JTable.getColumnModel().getColumn(3).setMaxWidth(115);
        }
        JTable.setrender(new int[]{2,3,4}, new String[]{"Number", "Number", "Number"});

        jlabelF7.setText("Nama Barang");
        jlabelF7.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF7.setEnabled(false);

        jlabelF8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jlabelF8.setText("Jumlah");
        jlabelF8.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF8.setEnabled(false);

        jlabelF9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jlabelF9.setText("Harga Satuan");
        jlabelF9.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF9.setEnabled(false);

        jlabelF10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jlabelF10.setText("Stock (GK)");
        jlabelF10.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF10.setEnabled(false);

        tambahtable.setText("TAMBAH");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement == null}"), tambahtable, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        tambahtable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahtableActionPerformed(evt);
            }
        });

        ubahtable.setText("UBAH");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), ubahtable, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        ubahtable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahtableActionPerformed(evt);
            }
        });

        hapustable.setText("HAPUS");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), hapustable, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        hapustable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapustableActionPerformed(evt);
            }
        });

        hapustable1.setText("REFRESH");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), hapustable1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        hapustable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapustable1ActionPerformed(evt);
            }
        });

        JTStock.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTStock.setText("0");
        JTStock.setEnabled(false);

        jlabelF11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jlabelF11.setText("Sub Total");
        jlabelF11.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF11.setEnabled(false);

        JTHargaSatuan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTHargaSatuanFocusLost(evt);
            }
        });
        JTHargaSatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTHargaSatuanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTHargaSatuanKeyReleased(evt);
            }
        });

        JTSubTotal.setEnabled(false);

        JCPasien.load("SELECT '-' as 'NamaPasien' UNION ALL (SELECT CONCAT('(',`KodePasien`,') ',`NamaPasien`) as 'NamaPasien' FROM `tbmpasien` GROUP BY `KodePasien` ORDER BY `NamaPasien`) ");
        JCPasien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCPasienItemStateChanged(evt);
            }
        });
        JCPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCPasienKeyPressed(evt);
            }
        });

        JTAKeterangan.setColumns(20);
        JTAKeterangan.setRows(5);
        jScrollPane1.setViewportView(JTAKeterangan);

        JTGrandTotal.setEnabled(false);

        jbuttonF7.setText("+");
        jbuttonF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF7ActionPerformed(evt);
            }
        });

        JBTambahPrint.setText("Tambah & Print");
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

        jlableF29.setText("PENJUALAN ");
        jlableF29.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

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

        jbuttonF11.setText("+");
        jbuttonF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF11ActionPerformed(evt);
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

        JTJumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTJumlahFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTJumlahFocusLost(evt);
            }
        });
        JTJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTJumlahKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTJumlahKeyReleased(evt);
            }
        });

        JTKembali.setEnabled(false);

        jlableF11.setText("Bayar");

        jlableF12.setText(":");

        JTBayar.setPlaceholder("Bayar");
        JTBayar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTBayarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTBayarFocusLost(evt);
            }
        });
        JTBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTBayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTBayarKeyReleased(evt);
            }
        });

        JLPoin.setText("Poin (000)");

        jlableF13.setText(":");

        JCBPakaiPoin.setSelected(false);
        JCBPakaiPoin.setText("Pakai Poin");
        JCBPakaiPoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBPakaiPoinActionPerformed(evt);
            }
        });

        jlableF14.setText("Setelah Potong");

        jlableF25.setText(":");

        JTSetelahPotong.setEnabled(false);

        jlableF26.setText("Kembali");

        jlableF27.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbuttonF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBUbahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTambahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTambahTutup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlableF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jlableF4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(JLPoin, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JTGrandTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(JCBPakaiPoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jlableF26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jlableF11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jlableF14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JTSetelahPotong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JTBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JTKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hapustable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ubahtable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tambahtable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hapustable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlableF21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlableF22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCPasien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbuttonF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlableF29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlabelF7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JTNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbuttonF11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JBSearchNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlabelF8, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                    .addComponent(JTJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(JTHargaSatuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlabelF9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlabelF11, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(JTSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JTStock, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                    .addComponent(jlabelF10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(LBNoTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlableF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(JTNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jlableF23, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlableF24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(JDTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlableF29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LBNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JDTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlableF24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlableF23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlabelF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabelF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabelF9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabelF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbuttonF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlabelF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JTHargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JBSearchNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(tambahtable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ubahtable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapustable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapustable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlableF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLPoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCBPakaiPoin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTSetelahPotong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbuttonF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambahTutup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBUbahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahActionPerformed
        tambahData(false, false);
    }//GEN-LAST:event_JBTambahActionPerformed

    private void JBTambahTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahTutupActionPerformed
        tambahData(true, false);
    }//GEN-LAST:event_JBTambahTutupActionPerformed

    private void JBUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBUbahActionPerformed
        ubahData(false);
    }//GEN-LAST:event_JBUbahActionPerformed

    private void jbuttonF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF4ActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonF4ActionPerformed

    private void tambahtableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahtableActionPerformed
        TambahTabel();
    }//GEN-LAST:event_tambahtableActionPerformed

    private void ubahtableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahtableActionPerformed
        ubahtable();
    }//GEN-LAST:event_ubahtableActionPerformed

    private void hapustableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapustableActionPerformed
        if (JTable.getSelectedRow() != -1) {
            ((DefaultTableModel) JTable.getModel()).removeRow(JTable.getSelectedRow());
            for (int a = 0; a < JTable.getRowCount(); a++) {
                JTable.setValueAt(a + 1, a, 0);
            }
            JOptionPaneF.showMessageDialog(this, "Berhasil Hapus Data");
            RefreshTbl();
        }
        JTNamaBarang.requestFocus();
        JTGrandTotal.setText(String.valueOf(getGrandTotal()));
    }//GEN-LAST:event_hapustableActionPerformed

    private void hapustable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapustable1ActionPerformed
        RefreshTbl();
    }//GEN-LAST:event_hapustable1ActionPerformed

    private void JTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableMouseClicked
        if (JTable.getSelectedRow() != -1) {
            JTNamaBarang.setText(JTable.getValueAt(JTable.getSelectedRow(), 1).toString());
            JTJumlah.setText(JTable.getValueAt(JTable.getSelectedRow(), 2).toString().replace(".", ""));
            JTHargaSatuan.setText(JTable.getValueAt(JTable.getSelectedRow(), 3).toString().replace(".", ""));
            JTSubTotal.setText(JTable.getValueAt(JTable.getSelectedRow(), 4).toString());
        }
    }//GEN-LAST:event_JTableMouseClicked

    private void JCPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTNamaBarang.requestFocus();
        }
    }//GEN-LAST:event_JCPasienKeyPressed

    private void JTHargaSatuanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTHargaSatuanFocusLost
        setSubTotal();
    }//GEN-LAST:event_JTHargaSatuanFocusLost

    private void jbuttonF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF7ActionPerformed
        if (tambahMasterPasien == null) {
            tambahMasterPasien = new MasterPasien();
        } else {
            tambahMasterPasien.setState(NORMAL);
            tambahMasterPasien.toFront();
        }
    }//GEN-LAST:event_jbuttonF7ActionPerformed

    private void JBTambahPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahPrintActionPerformed
        tambahData(false, true);
    }//GEN-LAST:event_JBTambahPrintActionPerformed

    private void JBUbahPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBUbahPrintActionPerformed
        ubahData(true);
    }//GEN-LAST:event_JBUbahPrintActionPerformed

    private void JTNamaBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNamaBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTJumlah.requestFocus();
        }
    }//GEN-LAST:event_JTNamaBarangKeyPressed

    private void JTNamaBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNamaBarangKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            cariBarang(null);
        } else if (isAlphanumeric(String.valueOf(evt.getKeyChar()))) {
            cariBarang(JTNamaBarang.getText());
        }
    }//GEN-LAST:event_JTNamaBarangKeyReleased

    private void jbuttonF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF11ActionPerformed
        if (tambahMasterBarang == null) {
            tambahMasterBarang = new MasterBarang();
        } else {
            tambahMasterBarang.setState(NORMAL);
            tambahMasterBarang.toFront();
        }
    }//GEN-LAST:event_jbuttonF11ActionPerformed

    private void JBSearchNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSearchNamaBarangActionPerformed
        cariBarang(null);
    }//GEN-LAST:event_JBSearchNamaBarangActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (JBTambah.isVisible()) {
            tambahPenjualan = null;
        } else {
            ubahPenjualan = null;
        }
    }//GEN-LAST:event_formWindowClosed

    private void JBSearchNamaBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JBSearchNamaBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTJumlah.requestFocus();
        }
    }//GEN-LAST:event_JBSearchNamaBarangKeyPressed

    private void JTJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTJumlahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTHargaSatuan.requestFocus();
        }
    }//GEN-LAST:event_JTJumlahKeyPressed

    private void JTHargaSatuanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTHargaSatuanKeyReleased
        setSubTotal();
    }//GEN-LAST:event_JTHargaSatuanKeyReleased

    private void JTHargaSatuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTHargaSatuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tambahtable.isEnabled()) {
                TambahTabel();
            } else {
                ubahtable();
            }
        }
    }//GEN-LAST:event_JTHargaSatuanKeyPressed

    private void JTNamaBarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTNamaBarangFocusLost

    }//GEN-LAST:event_JTNamaBarangFocusLost

    private void JBSearchNamaBarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JBSearchNamaBarangFocusLost
        setHarga();
        setStok();
    }//GEN-LAST:event_JBSearchNamaBarangFocusLost

    private void JTJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTJumlahKeyReleased
        setSubTotal();
    }//GEN-LAST:event_JTJumlahKeyReleased

    private void JTJumlahFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTJumlahFocusLost
        setSubTotal();
    }//GEN-LAST:event_JTJumlahFocusLost

    private void JTBayarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTBayarFocusGained
        setKembalian();
    }//GEN-LAST:event_JTBayarFocusGained

    private void JTBayarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTBayarFocusLost
        setKembalian();
    }//GEN-LAST:event_JTBayarFocusLost

    private void JTBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTBayarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JBTambah.isVisible()) {
                tambahData(false, false);
            } else {
                ubahData(false);
            }
        }
    }//GEN-LAST:event_JTBayarKeyPressed

    private void JTBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTBayarKeyReleased
        setKembalian();
    }//GEN-LAST:event_JTBayarKeyReleased

    private void JCBPakaiPoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBPakaiPoinActionPerformed
        if (JCBPakaiPoin.isSelected()) {
            JTSetelahPotong.setVisible(true);
            jlableF25.setVisible(true);
            jlableF14.setVisible(true);
            JTSetelahPotong.setText(getSetelahPotong());
        } else {
            JTSetelahPotong.setVisible(false);
            jlableF25.setVisible(false);
            jlableF14.setVisible(false);
        }
        JTBayar.requestFocus();
    }//GEN-LAST:event_JCBPakaiPoinActionPerformed

    private void JTJumlahFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTJumlahFocusGained
        setHarga();
        setStok();
    }//GEN-LAST:event_JTJumlahFocusGained

    private void JCPasienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCPasienItemStateChanged
        setPoin(0);
    }//GEN-LAST:event_JCPasienItemStateChanged

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
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private KomponenGUI.JbuttonF JBSearchNamaBarang;
    private KomponenGUI.JbuttonF JBTambah;
    private KomponenGUI.JbuttonF JBTambahPrint;
    private KomponenGUI.JbuttonF JBTambahTutup;
    private KomponenGUI.JbuttonF JBUbah;
    private KomponenGUI.JbuttonF JBUbahPrint;
    private KomponenGUI.JCheckBoxF JCBPakaiPoin;
    public static KomponenGUI.JcomboboxF JCPasien;
    private KomponenGUI.JdateCF JDTanggal;
    private KomponenGUI.JlableF JLPoin;
    private KomponenGUI.JTextAreaF JTAKeterangan;
    private KomponenGUI.JPlaceHolder JTBayar;
    private KomponenGUI.JRibuanTextField JTGrandTotal;
    private KomponenGUI.JRibuanTextField JTHargaSatuan;
    private KomponenGUI.JRibuanTextField JTJumlah;
    private KomponenGUI.JRibuanTextField JTKembali;
    private KomponenGUI.JtextF JTNamaBarang;
    private KomponenGUI.JtextF JTNoTransaksi;
    private KomponenGUI.JRibuanTextField JTSetelahPotong;
    private KomponenGUI.JtextF JTStock;
    private KomponenGUI.JRibuanTextField JTSubTotal;
    private KomponenGUI.JtableF JTable;
    private KomponenGUI.JlableF LBNoTransaksi;
    private KomponenGUI.JbuttonF hapustable;
    private KomponenGUI.JbuttonF hapustable1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private KomponenGUI.JbuttonF jbuttonF11;
    private KomponenGUI.JbuttonF jbuttonF4;
    private KomponenGUI.JbuttonF jbuttonF7;
    private KomponenGUI.JtextF jlabelF10;
    private KomponenGUI.JtextF jlabelF11;
    private KomponenGUI.JtextF jlabelF7;
    private KomponenGUI.JtextF jlabelF8;
    private KomponenGUI.JtextF jlabelF9;
    private KomponenGUI.JlableF jlableF10;
    private KomponenGUI.JlableF jlableF11;
    private KomponenGUI.JlableF jlableF12;
    private KomponenGUI.JlableF jlableF13;
    private KomponenGUI.JlableF jlableF14;
    private KomponenGUI.JlableF jlableF21;
    private KomponenGUI.JlableF jlableF22;
    private KomponenGUI.JlableF jlableF23;
    private KomponenGUI.JlableF jlableF24;
    private KomponenGUI.JlableF jlableF25;
    private KomponenGUI.JlableF jlableF26;
    private KomponenGUI.JlableF jlableF27;
    private KomponenGUI.JlableF jlableF29;
    private KomponenGUI.JlableF jlableF3;
    private KomponenGUI.JlableF jlableF4;
    private KomponenGUI.JlableF jlableF6;
    private KomponenGUI.JlableF jlableF8;
    private KomponenGUI.JbuttonF tambahtable;
    private KomponenGUI.JbuttonF ubahtable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    void TambahTabel() {
        if (checkTable()) {
            DefaultTableModel model = (DefaultTableModel) JTable.getModel();
            model.addRow(new Object[]{JTable.getRowCount() + 1, JTNamaBarang.getText(), JTJumlah.getText(), Intformatdigit(JTHargaSatuan.getInt()), JTSubTotal.getText()});
            JOptionPaneF.showMessageDialog(this, "Berhasil Tambah Detail Penjualan");
            JTNamaBarang.requestFocus();
            RefreshTbl();
            JTGrandTotal.setText(String.valueOf(getGrandTotal()));
        }
    }

    void ubahtable() {
        if (checkTable()) {
            JTable.setValueAt(JTNamaBarang.getText(), JTable.getSelectedRow(), 1);
            JTable.setValueAt(JTJumlah.getText(), JTable.getSelectedRow(), 2);
            JTable.setValueAt(Intformatdigit(JTHargaSatuan.getInt()), JTable.getSelectedRow(), 3);
            JTable.setValueAt(JTSubTotal.getText(), JTable.getSelectedRow(), 4);
            JOptionPaneF.showMessageDialog(this, "Berhasil Ubah Data");
            RefreshTbl();
            JTNamaBarang.requestFocus();
            JTGrandTotal.setText(String.valueOf(getGrandTotal()));
        }
    }

    void tambahData(Boolean tutup, Boolean print) {
        if (checkInput()) {
            boolean Berhasil;
            MultiInsert multiInsert = new MultiInsert();
            Berhasil = multiInsert.OpenConnection();
            if (Berhasil) {
                Berhasil = multiInsert.setautocomit(false);
                if (Berhasil) {
                    Berhasil = multiInsert.Excute("INSERT INTO `tbpenjualan`(`NoTransaksi`, `Tanggal`, `IdPasien`, `Keterangan`, `Bayar`, `Diskon`, `StatusPoin`, `Poin`, `Kasir`) VALUES ('" + JTNoTransaksi.getText() + "','" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "',(SELECT `IdPasien` FROM `tbmpasien` WHERE `KodePasien` = '" + JCPasien.getSelectedItem().toString().split("\\(")[1].split("\\)")[0] + "'),'" + JTAKeterangan.getText() + "', '" + JTBayar.getNumberFormattedText() + "', 0, " + JCBPakaiPoin.isSelected() + ", '" + getPoinTerpakai() + "', '" + GlobalVar.VarL.username + "')", null);
                    if (Berhasil) {
                        for (int i = 0; i < JTable.getRowCount(); i++) {
                            Berhasil = multiInsert.Excute("INSERT INTO `tbpenjualandetail`(`NoTransaksi`, `NoKolom`, `IdBarang`, `Jumlah`, `Harga`) VALUES ('" + JTNoTransaksi.getText() + "','" + JTable.getValueAt(i, 0) + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTable.getValueAt(i, 1) + "'),'" + JTable.getValueAt(i, 2).toString().replace(".", "") + "','" + JTable.getValueAt(i, 3).toString().replace(".", "") + "')", null);
                        }
                    }
                }
            }
            if (Berhasil == false) {
                multiInsert.rollback();
                multiInsert.closecon();
                JOptionPaneF.showMessageDialog(this, "Gagal Tambah Data Penjualan");
            }
            if (Berhasil == true) {
                JOptionPaneF.showMessageDialog(this, "Berhasil Tambah Data Penjualan");
                multiInsert.Commit();
                multiInsert.closecon();
                if (print) {
                    printing();
                }
                if (listPenjualan != null) {
                    listPenjualan.load();
                }
                if (tutup) {
                    dispose();
                } else {
                    JTAKeterangan.setText("");

                    JTable.setModel(new javax.swing.table.DefaultTableModel(
                            new Object[][]{},
                            new String[]{
                                "No", "Nama Barang", "Jumlah", "Harga Satuan", "Sub Total"
                            }
                    ));
                    RefreshTbl();
                    JTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                    JTable.getColumnModel().getColumn(1).setPreferredWidth(310);
                    JTable.getColumnModel().getColumn(2).setPreferredWidth(85);
                    JTable.getColumnModel().getColumn(3).setPreferredWidth(115);
                    JTNoTransaksi.setText(getNoPenjualan());
                    JTGrandTotal.setText("");
                    setPoin(0);
                    JTSetelahPotong.setText("0");
                    JTBayar.setText("0");
                    JTKembali.setText("0");
                }
            }
        }
    }

    void ubahData(Boolean print) {
        if (checkInput()) {
            boolean Berhasil;
            MultiInsert multiInsert = new MultiInsert();
            Berhasil = multiInsert.OpenConnection();
            if (Berhasil) {
                Berhasil = multiInsert.setautocomit(false);
                if (Berhasil) {
                    Berhasil = multiInsert.Excute("UPDATE `tbpenjualan` SET `NoTransaksi`='" + JTNoTransaksi.getText() + "',`Tanggal`='" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "',`IdPasien`=(SELECT `IdPasien` FROM `tbmpasien` WHERE `KodePasien` = '" + JCPasien.getSelectedItem().toString().split("\\(")[1].split("\\)")[0] + "'),`Keterangan`='" + JTAKeterangan.getText() + "', `Bayar`='" + JTBayar.getNumberFormattedText() + "', `Diskon`=0, `StatusPoin`=" + JCBPakaiPoin.isSelected() + ", `Poin`='" + getPoinTerpakai() + "', `Kasir`='" + GlobalVar.VarL.username + "' WHERE `IdPenjualan` = '" + IdEdit + "'", null);
                    if (Berhasil) {
                        Berhasil = multiInsert.Excute("DELETE FROM `tbpenjualandetail` WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'", null);
                        if (Berhasil) {
                            for (int i = 0; i < JTable.getRowCount(); i++) {
                                Berhasil = multiInsert.Excute("INSERT INTO `tbpenjualandetail`(`NoTransaksi`, `NoKolom`, `IdBarang`, `Jumlah`, `Harga`) VALUES ('" + JTNoTransaksi.getText() + "','" + JTable.getValueAt(i, 0) + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTable.getValueAt(i, 1) + "'),'" + JTable.getValueAt(i, 2).toString().replace(".", "") + "','" + JTable.getValueAt(i, 3).toString().replace(".", "") + "')", null);
                            }
                        }
                    }
                }
            }
            if (Berhasil == false) {
                multiInsert.rollback();
                multiInsert.closecon();
                JOptionPaneF.showMessageDialog(this, "Gagal Ubah Data Penjualan");
            }
            if (Berhasil == true) {
                JOptionPaneF.showMessageDialog(this, "Berhasil Ubah Data Penjualan");
                multiInsert.Commit();
                multiInsert.closecon();
                if (print) {
                    printing();
                }
                dispose();
                ubahPenjualan = null;
                if (listPenjualan != null) {
                    listPenjualan.load();
                }
            }
        }
    }

    /**
     *
     * @param Teks
     */
    String Intformatdigit(int Number) {
        int value = 0;
        value = Number;
        String output;
        String pattern = "#,###,###";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        if (value < 0) {
            value = abs(value);
            output = "(" + myFormatter.format(value) + ")";
        } else if (value > 0 && value < 1) {
            output = "0" + myFormatter.format(value);
        } else {
            output = myFormatter.format(value);
        }
        return output.replace(",", ".");
    }

    String Decformatdigit(double Number) {
        double value = 0;
        value = Number;
        String output;
        String pattern = "#,###,###.00";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        if (value <= -1) {
            value = abs(value);
            return "-" + myFormatter.format(value);
        } else if (value < 0 && value > -1) {
            value = abs(value);
            return "-0" + myFormatter.format(value);
        } else if (value < 1 && value >= 0) {
            return "0" + myFormatter.format(value);
        } else {
            return myFormatter.format(value);
        }
    }

    Integer getGrandTotal() {
        int GrandTotal = 0;
        for (int x = 0; x < JTable.getRowCount(); x++) {
            GrandTotal = GrandTotal + (Integer.valueOf(JTable.getValueAt(x, 2).toString().replace(".", "")) * Integer.valueOf(JTable.getValueAt(x, 3).toString().replace(".", "")));
        }
        return GrandTotal;
    }

    public static String angkaToTerbilang(Long angka) {
        String[] huruf = {"", "SATU", "DUA", "TIGA", "EMPAT", "LIMA", "ENAM", "TUJUH", "DELAPAN", "SEMBILAN", "SEPULUH", "SEBELAS"};
        if (angka < 12) {
            return huruf[angka.intValue()];
        }
        if (angka >= 12 && angka <= 19) {
            return huruf[angka.intValue() % 10] + " BELAS";
        }
        if (angka >= 20 && angka <= 99) {
            return angkaToTerbilang(angka / 10) + " PULUH " + huruf[angka.intValue() % 10];
        }
        if (angka >= 100 && angka <= 199) {
            return "SERATUS " + angkaToTerbilang(angka % 100);
        }
        if (angka >= 200 && angka <= 999) {
            return angkaToTerbilang(angka / 100) + " RATUS " + angkaToTerbilang(angka % 100);
        }
        if (angka >= 1000 && angka <= 1999) {
            return "SERIBU " + angkaToTerbilang(angka % 1000);
        }
        if (angka >= 2000 && angka <= 999999) {
            return angkaToTerbilang(angka / 1000) + " RIBU " + angkaToTerbilang(angka % 1000);
        }
        if (angka >= 1000000 && angka <= 999999999) {
            return angkaToTerbilang(angka / 1000000) + " JUTA " + angkaToTerbilang(angka % 1000000);
        }
        if (angka >= 1000000000 && angka <= 999999999999L) {
            return angkaToTerbilang(angka / 1000000000) + " MILYAR " + angkaToTerbilang(angka % 1000000000);
        }
        if (angka >= 1000000000000L && angka <= 999999999999999L) {
            return angkaToTerbilang(angka / 1000000000000L) + " TRILIUN " + angkaToTerbilang(angka % 1000000000000L);
        }
        if (angka >= 1000000000000000L && angka <= 999999999999999999L) {
            return angkaToTerbilang(angka / 1000000000000000L) + " QUADRILYUN " + angkaToTerbilang(angka % 1000000000000000L);
        }
        return "";
    }

    void setStok() {
        if (!JTNamaBarang.getText().replace(" ", "").equals("")) {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal Menampilkan Data Stok Barang");
            dRunSelctOne.setQuery("SELECT `IdBarang`, IFNULL(SUM(`Jumlah`),0) as 'Stok' FROM( SELECT `IdBarang`, 0 as 'Jumlah' FROM `tbmbarang` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah` FROM `tbpermintaanstok` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah`*-1 FROM `tbpenjualandetail` WHERE 1 UNION ALL SELECT `IdBarang`, `Jumlah` FROM `tbpenyesuaianstok` WHERE 1) t1 WHERE `IdBarang` = (SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTNamaBarang.getText() + "') GROUP BY `IdBarang`");
            ArrayList<String> list = dRunSelctOne.excute();
            String Stok = list.get(1);
            JTStock.setText(Stok);
        }
    }

    void printing() {
        PrintResep pr = new PrintResep();
        Object printitem[][] = getTableData(JTable);
        setItems(printitem);
        total_item_count = itemsTable.getRowCount();
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(pr, getPageFormat(pj));
        try {
            pj.print();

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    void setItems(Object[][] printitem) {
        Object data[][] = printitem;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(title[0]);
        model.addColumn(title[1]);
        model.addColumn(title[2]);
        model.addColumn(title[3]);

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

        System.out.println("Check Passed.");
    }

    Object[][] getTableData(JTable table1) {
        int itemcount = table1.getRowCount();
        System.out.println("Item Count:" + itemcount);

        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol - 1];
        if (itemcount == nRow) {
            for (int i = 0; i < nRow; i++) {
                for (int j = 0; j < nCol - 1; j++) {
                    tableData[i][j] = dtm.getValueAt(i, j + 1);
                }
            }
            if (tableData.length != itemcount) {
                getTableData(table1);
            }
            System.out.println("Data check passed");
        } else {
            getTableData(table1);
        }
        return tableData;
    }

    PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight = total_item_count * 13;
        double headerHeight = 5.4694448;
        double footerHeight = 2.575932;
        if (JCBPakaiPoin.isSelected()) {
            footerHeight = 3.705;
        }

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
    String title[] = new String[]{"Nama Barang", "Jml", "Harga", "Total"};
    String Poin, PoinTerpakai, SetelahPotong, Bayar;

    class PrintResep implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
                Font font;
                try {
                    int x = 5;
                    int y = 0;
                    int w = 180;
                    int imagewidth = 120;
                    int imageheight = 40;
                    BufferedImage read = ImageIO.read(getClass().getResource("/Resource/logo.png"));
                    g2d.drawImage(read, x + ((w - imagewidth) / 2), y, imagewidth, imageheight, null);
                    font = new Font("Times New Roman", Font.PLAIN, 7);
                    g2d.setFont(font);
                    g2d.drawString("Jl. Kompol Zainal Abidin No.05-06 (0741-7553068)", x + ((w - g2d.getFontMetrics().stringWidth("Jl. Kompol Zainal Abidin No.05-06 (0741-7553068)")) / 2), y + 45);
                    g2d.drawString("Tanjung Pinang - Jambi", x + ((w - g2d.getFontMetrics().stringWidth("Tanjung Pinang - Jambi")) / 2), y + 55);
                    g2d.drawLine(x, y + 65, 180, y + 65);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    int x = 5;
                    int y = 55;
                    int w = 188;
                    font = new Font("Times New Roman", Font.PLAIN, 10);
                    g2d.setFont(font);
                    g2d.drawString("Tanggal : " + now(), x, y + 20);
                    g2d.drawString("Cashier : " + GlobalVar.VarL.username, x, y + 33);

                    g2d.drawLine(x, y + 40, 180, y + 40);

                    font = new Font("Times New Roman", Font.PLAIN, 9);
                    g2d.setFont(font);
                    g2d.drawString(title[0], x, y + 50);
                    g2d.drawString(title[1], 85, y + 50);
                    g2d.drawString(title[2], 110, y + 50);
                    g2d.drawString(title[3], 155, y + 50);
                    g2d.drawLine(x, y + 55, 180, y + 55);

                    int cH = 0;
                    TableModel mod = itemsTable.getModel();
                    for (int i = 0; i < mod.getRowCount(); i++) {
                        String NamaBarang = mod.getValueAt(i, 0).toString();
                        String Jumlah = mod.getValueAt(i, 1).toString();
                        String Harga = mod.getValueAt(i, 2).toString();
                        String SubTotal = mod.getValueAt(i, 3).toString();

                        cH = (y + 65) + (13 * i);
                        g2d.drawString(NamaBarang, x, cH);
                        g2d.drawString(Jumlah, 82 + (13 - g2d.getFontMetrics().stringWidth(Jumlah)), cH);
                        g2d.drawString(Harga, 100 + (36 - g2d.getFontMetrics().stringWidth(Harga)), cH);
                        g2d.drawString(SubTotal, 134 + (46 - g2d.getFontMetrics().stringWidth(SubTotal)), cH);
                    }
                    g2d.drawLine(80, cH + 5, 180, cH + 5);
                    g2d.drawString("Total", 80, cH + 15);
                    g2d.drawString(":", 140, cH + 15);
                    g2d.drawString(JTGrandTotal.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTGrandTotal.getText())), cH + 15);
                    if (JCBPakaiPoin.isSelected()) {
                        g2d.drawString("Poin Anda", 80, cH + 25);
                        g2d.drawString(":", 140, cH + 25);
                        g2d.drawString(JLPoin.getText().split("\\(")[1].split("\\)")[0], 134 + (46 - g2d.getFontMetrics().stringWidth(JLPoin.getText().split("\\(")[1].split("\\)")[0])), cH + 25);
                        g2d.drawString("Poin Dipakai", 80, cH + 35);
                        g2d.drawString(":", 140, cH + 35);
                        g2d.drawString(String.valueOf(getPoinTerpakai()), 134 + (46 - g2d.getFontMetrics().stringWidth(String.valueOf(getPoinTerpakai()))), cH + 35);
                        g2d.drawString("Setelah Potong", 80, cH + 45);
                        g2d.drawString(":", 140, cH + 45);
                        g2d.drawString(JTSetelahPotong.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTSetelahPotong.getText())), cH + 45);
                        g2d.drawString("Bayar", 80, cH + 55);
                        g2d.drawString(":", 140, cH + 55);
                        g2d.drawString(JTBayar.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTBayar.getText())), cH + 55);
                        g2d.drawString("Kembali", 80, cH + 65);
                        g2d.drawString(":", 140, cH + 65);
                        g2d.drawString(JTKembali.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTKembali.getText())), cH + 65);
                        font = new Font("Arial", Font.BOLD, 10);
                        g2d.setFont(font);
                        g2d.drawString("Thank You Come Again", x + ((w - g2d.getFontMetrics().stringWidth("Thank You Come Again")) / 2), cH + 95);
                    } else {
                        g2d.drawString("Bayar", 80, cH + 25);
                        g2d.drawString(":", 140, cH + 25);
                        g2d.drawString(JTBayar.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTBayar.getText())), cH + 25);
                        g2d.drawString("Kembali", 80, cH + 35);
                        g2d.drawString(":", 140, cH + 35);
                        g2d.drawString(JTKembali.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTKembali.getText())), cH + 33);
                        font = new Font("Arial", Font.BOLD, 10);
                        g2d.setFont(font);
                        g2d.drawString("Thank You Come Again", x + ((w - g2d.getFontMetrics().stringWidth("Thank You Come Again")) / 2), cH + 63);
                    }

                } catch (Exception r) {
                    r.printStackTrace();
                }
                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    void setPoin(int poin) {
        if (JCPasien.getSelectedIndex() == 0) {
            JLPoin.setText("Poin (0)");
            JCBPakaiPoin.setEnabled(false);
        } else {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal setPoin()");
            dRunSelctOne.setQuery("SELECT `IdPasien`, IFNULL(SUM(`Poin`),0) as 'Poin' FROM (SELECT `IdPasien`, 0 as 'Jumlah Belanja', 0 as 'Poin' FROM `tbmpasien` WHERE 1 AND `KodePasien` = '" + JCPasien.getSelectedItem().toString().split("\\(")[1].split("\\)")[0] + "' UNION ALL SELECT d.`IdPasien`, (SUM(e.`Jumlah`*e.`Harga`)+SUM(f.`Jumlah`*f.`Harga`) - (`Poin` * 5000)) as 'Total Belanja', FLOOR((SUM(e.`Jumlah`*e.`Harga`)+SUM(f.`Jumlah`*f.`Harga`) - (`Poin` * 5000)) / 50000) as 'Poin' FROM `tbbilling`a JOIN `tbperawatan`b ON a.`NoInvoice`=b.`NoInvoice` JOIN `tbantrian`c ON b.`NoAntrian`=c.`NoAntrian` AND b.`Tanggal`=c.`Tanggal` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 AND d.`IdPasien` = (SELECT `IdPasien` FROM `tbmpasien` WHERE `KodePasien` = '" + JCPasien.getSelectedItem().toString().split("\\(")[1].split("\\)")[0] + "') GROUP BY d.`IdPasien`, a.`NoBilling` UNION ALL SELECT d.`IdPasien`, a.`Poin`*5000*-1 as 'Total Belanja', `Poin`*-1 FROM `tbbilling`a JOIN `tbperawatan`b ON a.`NoInvoice`=b.`NoInvoice` JOIN `tbantrian`c ON b.`NoAntrian`=c.`NoAntrian` AND b.`Tanggal`=c.`Tanggal` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 AND a.`StatusPoin` = 1 AND d.`IdPasien` = (SELECT `IdPasien` FROM `tbmpasien` WHERE `KodePasien` = '" + JCPasien.getSelectedItem().toString().split("\\(")[1].split("\\)")[0] + "') GROUP BY d.`IdPasien`, a.`NoBilling` UNION ALL SELECT b.`IdPasien`, (SUM(a.`Jumlah`*a.`Harga`) - (`Poin` * 5000)) as 'Total Belanja', FLOOR((SUM(a.`Jumlah`*a.`Harga`) - (`Poin` * 5000)) / 50000) as 'Poin' FROM `tbpenjualandetail`a JOIN `tbpenjualan`b ON a.`NoTransaksi`=b.`NoTransaksi` WHERE 1 AND b.`IdPasien` = (SELECT `IdPasien` FROM `tbmpasien` WHERE `KodePasien` = '" + JCPasien.getSelectedItem().toString().split("\\(")[1].split("\\)")[0] + "') GROUP BY b.`IdPasien`, a.`NoTransaksi` UNION ALL SELECT b.`IdPasien`, b.`Poin`*5000*-1 as 'Total Belanja', `Poin`*-1 FROM `tbpenjualandetail`a JOIN `tbpenjualan`b ON a.`NoTransaksi`=b.`NoTransaksi` WHERE 1 AND b.`StatusPoin` = 1 AND b.`IdPasien` = (SELECT `IdPasien` FROM `tbmpasien` WHERE `KodePasien` = '" + JCPasien.getSelectedItem().toString().split("\\(")[1].split("\\)")[0] + "') GROUP BY b.`IdPasien`, a.`NoTransaksi`) t1 WHERE 1 GROUP BY `IdPasien`");
            ArrayList<String> list = dRunSelctOne.excute();
            JLPoin.setText("Poin (" + (Integer.parseInt(list.get(1)) + poin) + ")");
            if (list.get(1).equals("0")) {
                JCBPakaiPoin.setEnabled(false);
            } else {
                JCBPakaiPoin.setEnabled(true);
            }
        }
    }

}

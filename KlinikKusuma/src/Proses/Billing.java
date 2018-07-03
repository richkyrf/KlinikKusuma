/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;

import java.awt.event.KeyEvent;
import java.util.Date;
import FunctionGUI.JOptionPaneF;
import javax.swing.table.DefaultTableModel;
import static GlobalVar.Var.*;
import KomponenGUI.FDateF;
import LSubProces.DRunSelctOne;
import LSubProces.MultiInsert;
import LSubProces.RunSelct;
import java.awt.Color;
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
import java.io.IOException;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

/**
 *
 * @author richk
 */
public class Billing extends javax.swing.JFrame {

    /**
     * Creates new form Perawatan
     */
    String Dari, Parameter;
    int tindakanCount, obatCount;

    public Billing(String dari, Object parameter) {
        Parameter = parameter.toString();
        Dari = dari;
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        UIManager.put("ComboBox.disabledForeground", Color.blue);
//        JCNamaBeautician.setEnabled(false);
        JTDiskonObat.setEnabled(false);
        if (dari.contains("Antrian")) {
            setTitle("Tambah Billing");
            JBUbah.setVisible(false);
            JBUbahPrint.setVisible(false);
            JLSetelahPotongTindakan.setVisible(false);
            JLSetelahPotongTindakan2.setVisible(false);
            JTSetelahPotongTindakan.setVisible(false);
            JLSetelahPotongObat.setVisible(false);
            JLSetelahPotongObat2.setVisible(false);
            JTSetelahPotongObat.setVisible(false);
            if (dari.contains("Billing")) {
                loadPerawatan(parameter);
            } else {
                loadPenjualan(parameter);
                JCNamaDokter.setEnabled(true);
                JCNamaBeautician.setEnabled(true);
                JTNoTransaksi.setText(getNoTransaksi());
            }
            setPoin(0);
            JTNoBilling.setText(getNoBilling());
        } else {
            setTitle("Ubah Billing");
            JBTambah.setVisible(false);
            JBTambahPrint.setVisible(false);
            loadEditData(parameter);
            if (dari.contains("Perawatan")) {
            } else {
                JCNamaBeautician.setEnabled(true);
            }
        }
        JTBayar.requestFocus();
    }

    void loadPenjualan(Object idAntrian) {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Gagal loadPasien()");
        dRunSelctOne.setQuery("SELECT CONCAT('(',`KodePasien`,') ',`NamaPasien`), `NoAntrian` FROM `tbmpasien`a LEFT JOIN `tbantrian`b ON a.`IdPasien`=b.`IdPasien` WHERE 1 AND `IdAntrian` IS NOT NULL AND `Tanggal` = CURDATE() AND b.`Status` = 0 AND `IdAntrian` = '" + idAntrian + "'");
        ArrayList<String> list = dRunSelctOne.excute();
        JTNamaPasien.setText(list.get(0));
        JTNoAntrian.setText(list.get(1));
        JCNamaDokter.setEnabled(false);
    }

    void loadPerawatan(Object idAntrian) {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Gagal loadPerawatan()");
        dRunSelctOne.setQuery("SELECT `IdPerawatan` as 'ID', CONCAT('(',`KodePasien`,') ',`NamaPasien`) as 'Nama Pasien', DATE_FORMAT(a.`Tanggal`,'%d-%m-%Y') as 'Tanggal', `NoAntrian` as 'No. Antrian', `NoTransaksi` as 'No. Transaksi', IFNULL(`NamaDokter`,'-- Pilih Nama Dokter --') as 'Nama Dokter', IFNULL(`NamaBeautician`,'-- Pilih Nama Beautician --') as 'Nama Beautician' FROM `tbperawatan`a JOIN `tbantrian`b ON a.`IdAntrian`=b.`IdAntrian` JOIN `tbmpasien`c ON b.`IdPasien`=c.`IdPasien` LEFT JOIN `tbmdokter`d ON a.`IdDokter`=d.`IdDokter` LEFT JOIN `tbmbeautician`e ON a.`IdBeautician`=e.`IdBeautician` WHERE a.`IdAntrian` = '" + idAntrian + "'");
        ArrayList<String> list = dRunSelctOne.excute();
        JTNamaPasien.setText(list.get(1));
        JDTanggal.setDate(FDateF.strtodate(list.get(2), "dd-MM-yyyy"));
        JTNoAntrian.setText(list.get(3));
        JTNoTransaksi.setText(list.get(4));
        JCNamaDokter.setSelectedItem(list.get(5));
        JCNamaBeautician.setSelectedItem(list.get(6));
        DefaultTableModel model = (DefaultTableModel) JTableTindakan.getModel();
        model.getDataVector().removeAllElements();
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `IdPerawatanDetail` as 'ID', `NoTransaksi` as 'No. Transaksi', `NamaTindakan` as 'Nama Tindakan', FORMAT(`Jumlah`,0) as 'Jumlah', FORMAT(`Harga`,0) as 'Harga', FORMAT(`Jumlah`*`Harga`,0) as 'Sub Total' FROM `tbperawatandetail`a JOIN `tbmtindakan`b ON a.`IdTindakan`=b.`IdTindakan` WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'");
        try {
            ResultSet rs = runSelct.excute();
            int row = 0;
            while (rs.next()) {
                model.addRow(new Object[]{"", "", "", "", ""});
                JTableTindakan.setValueAt(rs.getString(3), row, 0);
                JTableTindakan.setValueAt(rs.getString(4).replace(",", "."), row, 1);
                JTableTindakan.setValueAt(rs.getString(5).replace(",", "."), row, 2);
                JTableTindakan.setValueAt(rs.getString(6).replace(",", "."), row, 4);
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
        runSelct2.setQuery("SELECT `IdObatDetail` as 'ID', `NoTransaksi` as 'No. Transaksi', `NamaBarang` as 'Nama Obat', FORMAT(`Jumlah`,0) as 'Jumlah', FORMAT(`Harga`,0) as 'Harga', FORMAT(`Jumlah`*`Harga`,0) as 'Sub Total' FROM `tbobatdetail`a JOIN `tbmbarang`b ON a.`IdObat`=b.`IdBarang` WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'");
        try {
            ResultSet rs2 = runSelct2.excute();
            int row2 = 0;
            while (rs2.next()) {
                model2.addRow(new Object[]{"", "", "", "", ""});
                JTableObat.setValueAt(rs2.getString(3), row2, 0);
                JTableObat.setValueAt(rs2.getString(4).replace(",", "."), row2, 1);
                JTableObat.setValueAt(rs2.getString(5).replace(",", "."), row2, 2);
                JTableObat.setValueAt(rs2.getString(6).replace(",", "."), row2, 3);
                row2++;
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Panggil Data Detail Obat");
        } finally {
//            runSelct2.closecon();
        }
        setTotalTindakan();
        setTotalObat();
        setGrandTotal();
    }

    void loadEditData(Object idEdit) {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Eror Gagal Menampilkan Data Billing");
        dRunSelctOne.setQuery("SELECT `IdBilling` as 'ID', `NoBilling` as 'No. Billing', DATE_FORMAT(a.`Tanggal`,'%d-%m-%Y') as 'Tanggal', a.`NoTransaksi` as 'No. Transaksi', `NoAntrian` as 'No. Antrian', CONCAT('(',`KodePasien`,') ',`NamaPasien`) as 'Pasien', IFNULL(`NamaDokter`,'-- Pilih Nama Dokter --') as 'Dokter', IFNULL(`NamaBeautician`,'-- Pilih Nama Beautician --') as 'Beautician', FORMAT(`Bayar`,0) as 'Jumlah Bayar', FORMAT(`DiskonObat`,0) as 'Diskon Obat', FORMAT(`PoinTindakan`,0) as 'Poin Tindakan', FORMAT(`PoinObat`,0) as 'Poin Obat', `MetodePembayaran` FROM `tbbilling`a JOIN `tbantrian`b ON a.`IdAntrian`=b.`IdAntrian` JOIN `tbmpasien`c ON b.`IdPasien`=c.`IdPasien` LEFT JOIN `tbperawatan`d ON a.`NoTransaksi`=d.`NoTransaksi` LEFT JOIN `tbmdokter`e ON IF(a.`IdDokter` IS NULL, d.`IdDokter`, a.`IdDokter`)=e.`IdDokter` LEFT JOIN `tbmbeautician`f ON IF(a.`IdBeautician` IS NULL, d.`IdBeautician`, a.`IdBeautician`)=f.`IdBeautician` WHERE `IdBilling` = '" + Parameter + "'"); //0id, 1nobil, 2tgl, 3notrans, 4noantri, 5pasien, 6dokter, 7beautician, 8bayar, 9diskon, 10poinT, 11poinO, 12metode
        ArrayList<String> list = dRunSelctOne.excute();
        JTNoBilling.setText(list.get(1));
        JDTanggal.setDate(FDateF.strtodate(list.get(2), "dd-MM-yyyy"));
        JTNoTransaksi.setText(list.get(3));
        JTNoAntrian.setText(list.get(4));
        JTNamaPasien.setText(list.get(5));
        JCNamaDokter.setSelectedItem(list.get(6));
        JCNamaBeautician.setSelectedItem(list.get(7));
        JTBayar.setInt(list.get(8).replace(",", ""));
        JTDiskonObat.setInt(list.get(9).replace(",", ""));
        JTPoinTindakan.setInt(list.get(10).replace(",", ""));
        JTPoinObat.setInt(list.get(11).replace(",", ""));
        JCMetodePembayaran.setSelectedItem(list.get(12));
        DefaultTableModel model = (DefaultTableModel) JTableTindakan.getModel();
        model.getDataVector().removeAllElements();
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `IdBillingTindakan` as 'ID', `NoBilling` as 'No. Billing', `NamaTindakan` as 'Nama Tindakan', FORMAT(`Jumlah`,0) as 'Jumlah', FORMAT(a.`Harga`,0) as 'Harga', IF(`DiskonTindakan` > 100 || `DiskonTindakan` = 0, FORMAT(`DiskonTindakan`,0), CONCAT(`DiskonTindakan`,' %')) as 'Diskon Tindakan', IF(`DiskonTindakan` > 100, FORMAT((`Jumlah`*a.`Harga`)-`DiskonTindakan`,0), FORMAT((`Jumlah`*a.`Harga`)-((`Jumlah`*a.`Harga`)*`DiskonTindakan`/100),0)) as 'Sub Total' FROM `tbbillingtindakan`a JOIN `tbmtindakan`b ON a.`IdTindakan`=b.`IdTindakan` WHERE `NoBilling` = '" + JTNoBilling.getText() + "'");
        try {
            ResultSet rs = runSelct.excute();
            int row = 0;
            while (rs.next()) {
                model.addRow(new Object[]{"", "", "", "", ""});
                JTableTindakan.setValueAt(rs.getString(3), row, 0);
                JTableTindakan.setValueAt(rs.getString(4).replace(",", "."), row, 1);
                JTableTindakan.setValueAt(rs.getString(5).replace(",", "."), row, 2);
                JTableTindakan.setValueAt(rs.getString(6).replace(",", "."), row, 3);
                JTableTindakan.setValueAt(rs.getString(7).replace(",", "."), row, 4);
                row++;
            }
            tindakanCount = JTableTindakan.getRowCount();
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Panggil Data Billing Tindakan");
        } finally {
//            runSelct.closecon();
        }
        DefaultTableModel model2 = (DefaultTableModel) JTableObat.getModel();
        model2.getDataVector().removeAllElements();
        RunSelct runSelct2 = new RunSelct();
        runSelct2.setQuery("SELECT `IdBillingObat` as 'ID', `NoBilling` as 'No. Billing', `NamaBarang` as 'Nama Obat', FORMAT(`Jumlah`,0) as 'Jumlah', FORMAT(a.`Harga`,0) as 'Harga', FORMAT(`Jumlah`*a.`Harga`,0) as 'Sub Total' FROM `tbbillingobat`a JOIN `tbmbarang`b ON a.`IdObat`=b.`IdBarang` WHERE `NoBilling` = '" + JTNoBilling.getText() + "'");
        try {
            ResultSet rs2 = runSelct2.excute();
            int row2 = 0;
            while (rs2.next()) {
                model2.addRow(new Object[]{"", "", "", "", ""});
                JTableObat.setValueAt(rs2.getString(3), row2, 0);
                JTableObat.setValueAt(rs2.getString(4).replace(",", "."), row2, 1);
                JTableObat.setValueAt(rs2.getString(5).replace(",", "."), row2, 2);
                JTableObat.setValueAt(rs2.getString(6).replace(",", "."), row2, 3);
                row2++;
            }
            obatCount = JTableObat.getRowCount();
        } catch (SQLException e) {
            out.println("E6" + e);
            JOptionPaneF.showMessageDialog(null, "Gagal Panggil Data Billing Obat");
        } finally {
//            runSelct2.closecon();
        }
        setPoin(Integer.valueOf(list.get(10)) + Integer.valueOf(list.get(11)));
        setTotalTindakan();
        if (JTPoinTindakan.getInt() != 0) {
            JLSetelahPotongTindakan.setVisible(true);
            JLSetelahPotongTindakan2.setVisible(true);
            JTSetelahPotongTindakan.setVisible(true);
            JTSetelahPotongTindakan.setInt(getSetelahPotongPoinTindakan());
        } else {
            JLSetelahPotongTindakan.setVisible(false);
            JLSetelahPotongTindakan2.setVisible(false);
            JTSetelahPotongTindakan.setVisible(false);
        }
        setTotalObat();
        if (JTPoinObat.getInt() != 0 || JTDiskonObat.getInt() != 0) {
            JLSetelahPotongObat.setVisible(true);
            JLSetelahPotongObat2.setVisible(true);
            JTSetelahPotongObat.setVisible(true);
            setSetelahPotongObat();
        } else {
            JLSetelahPotongObat.setVisible(false);
            JLSetelahPotongObat2.setVisible(false);
            JTSetelahPotongObat.setVisible(false);
        }
        setGrandTotal();
//        if (JTPoinTindakan.getInt() != 0) {
//            JTSetelahPotongTindakan.setVisible(true);
//            JLSetelahPotongTindakan.setVisible(true);
//            JLSetelahPotongTindakan2.setVisible(true);
//            setPoin(Integer.valueOf(list.get(10)));
//            if (JTSetelahPotongTindakan.isVisible()) {
//                JTSetelahPotongTindakan.setText(String.valueOf(JTGrandTotal.getInt() - Integer.valueOf(list.get(8)) * 1000));
//            }
//        } else {
//            JTSetelahPotongTindakan.setVisible(false);
//            JLSetelahPotongTindakan.setVisible(false);
//            JLSetelahPotongTindakan2.setVisible(false);
//            JLPoinTindakan.setText("Poin (0)");
//            JCBPakaiPoinTindakan.setEnabled(false);
//        }
//        if (JCBPakaiPoinObat.isSelected()) {
//            JTSetelahPotongObat.setVisible(true);
//            JLSetelahPotongObat.setVisible(true);
//            JLSetelahPotongObat2.setVisible(true);
//            setPoin(Integer.valueOf(list.get(10)));
//            JTSetelahPotongObat.setText(String.valueOf(JTGrandTotal.getInt() - Integer.valueOf(list.get(8)) * 1000));
//        } else {
//            JTSetelahPotongObat.setVisible(false);
//            JLSetelahPotongObat.setVisible(false);
//            JLSetelahPotongObat2.setVisible(false);
//            JLPoinObat.setText("Poin (0)");
//            JCBPakaiPoinObat.setEnabled(false);
//        }
    }

//    void loadEditDataPerawatan(Object idEdit) {
//        DRunSelctOne dRunSelctOne = new DRunSelctOne();
//        dRunSelctOne.seterorm("Eror Gagal Menampilkan Data Billing");
//        dRunSelctOne.setQuery("SELECT `IdBilling` as 'ID', `NoBilling` as 'No. Billing', DATE_FORMAT(a.`Tanggal`,'%d-%m-%Y') as 'Tanggal', a.`NoTransaksi` as 'No. Transaksi', `NoAntrian` as 'No. Antrian', `NamaDokter` as 'Dokter', IFNULL(`NamaBeautician`,'-- Pilih Nama Beautician --') as 'Beautician', CONCAT('(',`KodePasien`,') ',`NamaPasien`) as 'Pasien', FORMAT(`Bayar`,0) as 'Jumlah Bayar', FORMAT(`DiskonObat`,0) as 'Diskon Obat', FORMAT(`PoinTindakan`,0) as 'Poin Tindakan', FORMAT(`PoinObat`,0) as 'Poin Obat', `MetodePembayaran` FROM `tbbilling`a JOIN `tbperawatan`b ON a.`NoTransaksi`=b.`NoTransaksi` JOIN `tbantrian`c ON b.`IdAntrian`=c.`IdAntrian` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` JOIN `tbmdokter`e ON b.`IdDokter`=e.`IdDokter` LEFT JOIN `tbmbeautician`f ON b.`IdBeautician`=f.`IdBeautician` WHERE `IdBilling` = '" + Parameter + "'");//0id, 1nobil, 2tgl, 3notrans, 4noantrian, 5dokter, 6beautician, 7pasien, 8bayar, 9diskonO, 10poinT, 11poinO, 12metode
//        ArrayList<String> list = dRunSelctOne.excute();
//        JTNoBilling.setText(list.get(1));
//        JDTanggal.setDate(FDateF.strtodate(list.get(2), "dd-MM-yyyy"));
//        JTNoTransaksi.setText(list.get(3));
//        JTNoAntrian.setText(list.get(4));
//        JCNamaDokter.setSelectedItem(list.get(5));
//        JCNamaBeautician.setSelectedItem(list.get(6));
//        JTNamaPasien.setText(list.get(7));
//        JTBayar.setInt(list.get(8).replace(",", ""));
//        JTDiskonObat.setInt(list.get(9).replace(",", ""));
//        JTPoinTindakan.setInt(list.get(10).replace(",", ""));
//        JTPoinObat.setInt(list.get(11).replace(",", ""));
//        JCMetodePembayaran.setSelectedItem(list.get(12));
//        DefaultTableModel model = (DefaultTableModel) JTableTindakan.getModel();
//        model.getDataVector().removeAllElements();
//        RunSelct runSelct = new RunSelct();
//        runSelct.setQuery("SELECT `IdBillingTindakan` as 'ID', `NoBilling` as 'No. Billing', `NamaTindakan` as 'Nama Tindakan', FORMAT(`Jumlah`,0) as 'Jumlah', FORMAT(a.`Harga`,0) as 'Harga', FORMAT(`DiskonTindakan`,0) as 'Diskon Tindakan', FORMAT(`Jumlah`*a.`Harga`,0) as 'Sub Total' FROM `tbbillingtindakan`a JOIN `tbmtindakan`b ON a.`IdTindakan`=b.`IdTindakan` WHERE `NoBilling` = '" + JTNoBilling.getText() + "'");
//        try {
//            ResultSet rs = runSelct.excute();
//            int row = 0;
//            while (rs.next()) {
//                model.addRow(new Object[]{"", "", "", "", ""});
//                JTableTindakan.setValueAt(rs.getString(3), row, 0);
//                JTableTindakan.setValueAt(rs.getString(4).replace(",", "."), row, 1);
//                JTableTindakan.setValueAt(rs.getString(5).replace(",", "."), row, 2);
//                JTableTindakan.setValueAt(rs.getString(6).replace(",", "."), row, 3);
//                row++;
//            }
//            tindakanCount = JTableTindakan.getRowCount();
//        } catch (SQLException e) {
//            out.println("E6" + e);
//            JOptionPaneF.showMessageDialog(null, "Gagal Panggil Data Billing Tindakan");
//        } finally {
////            runSelct.closecon();
//        }
//        DefaultTableModel model2 = (DefaultTableModel) JTableObat.getModel();
//        model2.getDataVector().removeAllElements();
//        RunSelct runSelct2 = new RunSelct();
//        runSelct2.setQuery("SELECT `IdBillingObat` as 'ID', `NoBilling` as 'No. Billing', `NamaBarang` as 'Nama Obat', FORMAT(`Jumlah`,0) as 'Jumlah', FORMAT(a.`Harga`,0) as 'Harga', FORMAT(`Jumlah`*a.`Harga`,0) as 'Sub Total' FROM `tbbillingobat`a JOIN `tbmbarang`b ON a.`IdObat`=b.`IdBarang` WHERE `NoBilling` = '" + JTNoBilling.getText() + "'");
//        try {
//            ResultSet rs2 = runSelct2.excute();
//            int row2 = 0;
//            while (rs2.next()) {
//                model2.addRow(new Object[]{"", "", "", "", ""});
//                JTableObat.setValueAt(rs2.getString(3), row2, 0);
//                JTableObat.setValueAt(rs2.getString(4).replace(",", "."), row2, 1);
//                JTableObat.setValueAt(rs2.getString(5).replace(",", "."), row2, 2);
//                JTableObat.setValueAt(rs2.getString(6).replace(",", "."), row2, 3);
//                row2++;
//            }
//            obatCount = JTableObat.getRowCount();
//        } catch (SQLException e) {
//            out.println("E6" + e);
//            JOptionPaneF.showMessageDialog(null, "Gagal Panggil Data Billing Obat");
//        } finally {
////            runSelct2.closecon();
//        }
//        setTotalTindakan();
//        setTotalObat();
//        setGrandTotal();
////        if (JCBPakaiPoinTindakan.isSelected()) {
////            JTSetelahPotongTindakan.setVisible(true);
////            JLSetelahPotongTindakan.setVisible(true);
////            JLSetelahPotongTindakan2.setVisible(true);
////            setPoin(Integer.valueOf(list.get(10)));
////            if (JTSetelahPotongTindakan.isVisible()) {
////                JTSetelahPotongTindakan.setText(String.valueOf(JTGrandTotal.getInt() - Integer.valueOf(list.get(10)) * 1000));
////            }
////        } else {
////            JTSetelahPotongTindakan.setVisible(false);
////            JLSetelahPotongTindakan.setVisible(false);
////            JLSetelahPotongTindakan2.setVisible(false);
////            JLPoinTindakan.setText("Poin (0)");
////            JCBPakaiPoinTindakan.setEnabled(false);
////        }
////        if (JCBPakaiPoinObat.isSelected()) {
////            JTSetelahPotongObat.setVisible(true);
////            JLSetelahPotongObat.setVisible(true);
////            JLSetelahPotongObat2.setVisible(true);
////            setPoin(Integer.valueOf(list.get(10)));
////            JTSetelahPotongObat.setText(String.valueOf(JTGrandTotal.getInt() - Integer.valueOf(list.get(10)) * 1000));
////        } else {
////            JTSetelahPotongObat.setVisible(false);
////            JLSetelahPotongObat.setVisible(false);
////            JLSetelahPotongObat2.setVisible(false);
////            JLPoinObat.setText("Poin (0)");
////            JCBPakaiPoinObat.setEnabled(false);
////        }
//    }
    public static String getNoTransaksi() {
        NumberFormat nf = new DecimalFormat("00000");
        String NoTransaksi = null;
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `NoTransaksi` FROM `tbbilling` WHERE `NoTransaksi` LIKE 'PJ-%' ORDER BY `NoTransaksi` DESC LIMIT 1");
        try {
            ResultSet rs = runSelct.excute();
            if (!rs.isBeforeFirst()) {
                NoTransaksi = "PJ-" + "00001" + "-" + FDateF.datetostr(new Date(), "YY");//PJ-00001-18
            }
            while (rs.next()) {
                String nopenjualan = rs.getString("NoTransaksi");
                String number = nopenjualan.substring(3, 8);
                String year = nopenjualan.substring(9, 11);
                int p;
                if (year.equals(FDateF.datetostr(new Date(), "YY"))) {
                    p = 1 + parseInt(number);
                } else {
                    p = 1;
                }
                if (p != 99999) {
                    NoTransaksi = "PJ-" + nf.format(p) + "-" + FDateF.datetostr(new Date(), "YY");
                } else if (p == 99999) {
                    p = 1;
                    NoTransaksi = "PJ-" + nf.format(p) + "-" + FDateF.datetostr(new Date(), "YY");
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

    public String getNoBilling() {
//        NumberFormat nf = new DecimalFormat("00000");
//        String NoBilling = null;
//        RunSelct runSelct = new RunSelct();
//        runSelct.setQuery("SELECT `NoBilling` FROM `tbbilling` ORDER BY `NoBilling` DESC LIMIT 1");
//        try {
//            ResultSet rs = runSelct.excute();
//            if (!rs.isBeforeFirst()) {
//                NoBilling = "BL-" + "00001" + "-" + FDateF.datetostr(new Date(), "YY");
//            }
//            while (rs.next()) {
//                String NoTransaksi = rs.getString("NoBilling");
//                String number = NoTransaksi.substring(3, 8);
//                String year = NoTransaksi.substring(9, 11);
//                int p;
//                if (year.equals(FDateF.datetostr(new Date(), "YY"))) {
//                    p = 1 + parseInt(number);
//                } else {
//                    p = 1;
//                }
//                if (p != 99999) {
//                    NoBilling = "BL-" + nf.format(p) + "-" + FDateF.datetostr(new Date(), "YY");
//                } else if (p == 99999) {
//                    p = 1;
//                    NoBilling = "BL-" + nf.format(p) + "-" + FDateF.datetostr(new Date(), "YY");
//                }
//            }
//        } catch (SQLException e) {
//            out.println("E6" + e);
//            JOptionPaneF.showMessageDialog(null, "Gagal Generate Nomor Billing");
//        } finally {
////            runSelct.closecon();
//        }
//        return NoBilling;
        NumberFormat nf = new DecimalFormat("000");
        return FDateF.datetostr(JDTanggal.getDate(), "YYMMdd") + nf.format(Integer.valueOf(JTNoAntrian.getText()));
    }

    boolean checkInput() {
        if (JDTanggal.getDate() == null) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Tanggal Tidak Boleh Kosong");
            return false;
        } else if (JTNoAntrian.getText().isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. No. Antrian Boleh Kosong");
            return false;
        } else if (JTNoTransaksi.getText().isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. No. Transaksi Boleh Kosong");
            return false;
        } else if (JTNoBilling.getText().isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. No. Nota Boleh Kosong");
            return false;
//        } else if (JTableTindakan.getRowCount() == 0) {
//            JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Isi Tindakan Terlebih Dahulu.");
//            return false;
//        } else if (JTableObat.getRowCount() == 0) {
//            JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Isi Obat Terlebih Dahulu.");
//            return false;
        } else if (JTBayar.getInt() < JTGrandTotal.getInt()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Jumlah Bayar Tidak Mencukupi.");
            JTBayar.requestFocus();
            return false;
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
        } else if (JTHargaTindakan.getNumberFormattedText().replace("0", "").isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Harga Tidak Boleh Kosong.");
            return false;
        } else if (JTHargaTindakan.getNumberFormattedText().replace("0", "").isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Sub Total Tidak Boleh Kosong.");
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
        } else if (JTHargaObat.getNumberFormattedText().replace("0", "").isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Harga Tidak Boleh Kosong.");
            return false;
        } else if (JTSubTotalObat.getNumberFormattedText().replace("0", "").isEmpty()) {
            JOptionPaneF.showMessageDialog(this, "Gagal. Sub Total Tidak Boleh Kosong.");
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
            if (item.equals(JTableObat.getValueAt(i, 0)) && i != JTableTindakan.getSelectedRow()) {
                return true;
            }
        }
        return false;
    }

    void setSubTotalTindakan() {
        if (JTDiskonTindakan.getInt() <= 100) {
            JTSubTotalTindakan.setInt(String.valueOf(JTJumlahTindakan.getInt() * JTHargaTindakan.getInt() - (JTJumlahTindakan.getInt() * JTHargaTindakan.getInt() * JTDiskonTindakan.getInt() / 100)));
        } else {
            JTSubTotalTindakan.setInt(String.valueOf((JTJumlahTindakan.getInt() * JTHargaTindakan.getInt()) - JTDiskonTindakan.getInt()));
        }
    }

    void setSubTotalObat() {
        JTSubTotalObat.setInt(String.valueOf(JTJumlahObat.getInt() * JTHargaObat.getInt()));
    }

    void setGrandTotal() {
        Integer total = 0;
        if (JTSetelahPotongTindakan.isVisible()) {
            total = total + JTSetelahPotongTindakan.getInt();
        } else {
            total = total + JTTotalTindakan.getInt();
        }
        if (JTSetelahPotongObat.isVisible()) {
            total = total + JTSetelahPotongObat.getInt();
        } else {
            total = total + JTTotalObat.getInt();
        }
        JTGrandTotal.setInt(String.valueOf(total));
        setKembalian();
    }

    void setTotalTindakan() {
        Integer total = 0;
        for (int i = 0; i < JTableTindakan.getRowCount(); i++) {
            total = total + Integer.valueOf(JTableTindakan.getValueAt(i, 4).toString().replace(".", ""));
        }
        JTTotalTindakan.setInt(String.valueOf(total));
        if (total != 0 && Integer.valueOf(JLPoinTindakan.getText().split("\\(")[1].split("\\)")[0]) != 0) {
            JTPoinTindakan.setEnabled(true);
        } else {
            JTPoinTindakan.setEnabled(false);
        }
    }

    void setTotalObat() {
        Integer total = 0;
        for (int i = 0; i < JTableObat.getRowCount(); i++) {
            total = total + Integer.valueOf(JTableObat.getValueAt(i, 3).toString().replace(".", ""));
        }
        JTTotalObat.setInt(String.valueOf(total));
        if (total != 0 && Integer.valueOf(JLPoinObat.getText().split("\\(")[1].split("\\)")[0]) != 0) {
            JTPoinObat.setEnabled(true);
        } else {
            JTPoinObat.setEnabled(false);
        }
        if (total != 0) {
            JTDiskonObat.setEnabled(true);
        } else {
            JTDiskonObat.setEnabled(false);
        }
    }

    void setPoin(int poin) {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Gagal setPoin()");
        dRunSelctOne.setQuery("SELECT `IdPasien`, IFNULL(SUM(`Poin`),0) as 'Poin' FROM (    SELECT `IdPasien`, 0 as 'Jumlah Belanja', 0 as 'Poin' FROM `tbmpasien` WHERE 1 AND `KodePasien` = '" + JTNamaPasien.getText().split("\\(")[1].split("\\)")[0] + "'   	UNION ALL     SELECT d.`IdPasien`, (SUM(IFNULL(e.`Jumlah`,0)*IFNULL(e.`Harga`,0))+SUM((IFNULL(f.`Jumlah`,0)*IFNULL(f.`Harga`,0)) - IFNULL(f.`DiskonTindakan`,0))) as 'Total Belanja', FLOOR((SUM(IFNULL(e.`Jumlah`,0)*IFNULL(e.`Harga`,0))+SUM(IFNULL(f.`Jumlah`,0)*IFNULL(f.`Harga`,0)) - IFNULL(f.`DiskonTindakan`,0)) / 50000) as 'Poin' FROM `tbbilling`a LEFT JOIN `tbperawatan`b ON a.`NoTransaksi`=b.`NoTransaksi` JOIN `tbantrian`c ON IF(b.`IdAntrian` IS NULL, a.`IdAntrian`, b.`IdAntrian`)=c.`IdAntrian` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` LEFT JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` LEFT JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 ANd `PoinTindakan` = 0 AND `PoinObat` = 0 AND `KodePasien` = '" + JTNamaPasien.getText().split("\\(")[1].split("\\)")[0] + "' GROUP BY d.`IdPasien`, a.`NoBilling`         UNION ALL         SELECT d.`IdPasien`, (a.`PoinTindakan`+a.`PoinObat`)*1000*-1 as 'Total Belanja', (`PoinTindakan`+`PoinObat`)*-1 FROM `tbbilling`a LEFT JOIN `tbperawatan`b ON a.`NoTransaksi`=b.`NoTransaksi` JOIN `tbantrian`c ON IF(b.`IdAntrian` IS NULL, a.`IdAntrian`, b.`IdAntrian`)=c.`IdAntrian` JOIN `tbmpasien`d ON c.`IdPasien`=d.`IdPasien` LEFT JOIN `tbbillingobat`e ON a.`NoBilling`=e.`NoBilling` LEFT JOIN `tbbillingtindakan`f ON a.`NoBilling`=f.`NoBilling` WHERE 1 AND (a.`PoinTindakan` > 0 OR a.`PoinObat` > 0) AND `KodePasien` = '" + JTNamaPasien.getText().split("\\(")[1].split("\\)")[0] + "' GROUP BY d.`IdPasien`, a.`NoBilling`	) t1 WHERE 1 GROUP BY `IdPasien`");
        ArrayList<String> list = dRunSelctOne.excute();
        JLPoinTindakan.setText("Poin (" + (Integer.parseInt(list.get(1)) + poin) + ")");
        JLPoinObat.setText("Poin (" + (Integer.parseInt(list.get(1)) + poin) + ")");
        if (!list.get(1).equals("0") && JTTotalTindakan.getInt() != 0) {
//            JCBPakaiPoinTindakan.setEnabled(false);
//            JCBPakaiPoinObat.setEnabled(false);
            JTPoinTindakan.setEnabled(true);
            JTPoinObat.setEnabled(true);
        } else {
//            JCBPakaiPoinTindakan.setEnabled(true);
//            JCBPakaiPoinObat.setEnabled(true);
            JTPoinTindakan.setEnabled(false);
            JTPoinObat.setEnabled(false);
        }
    }

    void setHargaTindakan() {
        if (JCTindakan.getSelectedIndex() != 0) {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal Panggil Data Harga Tindakan");
            dRunSelctOne.setQuery("SELECT `Harga` FROM `tbmtindakan` WHERE `NamaTindakan`= '" + JCTindakan.getSelectedItem() + "'");
            ArrayList<String> list = dRunSelctOne.excute();
            JTHargaTindakan.setInt(list.get(0));
        } else {
            JTHargaTindakan.setInt("0");
        }
    }

    void setHargaObat() {
        if (JCObat.getSelectedIndex() != 0) {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal Panggil Data Harga Obat");
            dRunSelctOne.setQuery("SELECT `Harga` FROM `tbmbarang` WHERE `NamaBarang`= '" + JCObat.getSelectedItem() + "'");
            ArrayList<String> list = dRunSelctOne.excute();
            JTHargaObat.setInt(list.get(0));
        } else {
            JTHargaObat.setInt("0");
        }
    }

    String getSetelahPotongPoinTindakan() {
        if (JTTotalTindakan.getInt() - (JTPoinTindakan.getInt() * 1000) <= 0) {
            return "0";
        } else {
            return String.valueOf(JTTotalTindakan.getInt() - (JTPoinTindakan.getInt() * 1000));
        }
    }

    Integer getPoinTerpakaiTindakan() {
//        if (JCBPakaiPoinTindakan.isSelected()) {
//            return (JTTotalTindakan.getInt() - JTSetelahPotongTindakan.getInt()) / 1000;
//        } else {
        return 0;
//        }
    }

    Integer getPoinTerpakaiObat() {
//        if (JCBPakaiPoinObat.isSelected()) {
//            return (JTTotalObat.getInt() - JTSetelahPotongObat.getInt()) / 1000;
//        } else {
        return 0;
//        }
    }

    void setKembalian() {
        if (!JTBayar.getText().isEmpty()) {
            JTKembali.setInt(JTBayar.getInt() - JTGrandTotal.getInt());
        }
    }

//    void setSetelahPotongTindakan() {
//        if (JTDiskonTindakan.getInt() != 0) {
//            JLSetelahPotongTindakan.setVisible(true);
//            JLSetelahPotongTindakan2.setVisible(true);
//            JTSetelahPotongTindakan.setVisible(true); 
//            if (JTDiskonTindakan.getInt() <= 100) {
//                JTSetelahPotongTindakan.setText(String.valueOf(JTSetelahPotongTindakan.getInt() - Math.round(JTSetelahPotongTindakan.getInt() * JTDiskonTindakan.getInt() / 100)));
//            } else {
//                JTSetelahPotongTindakan.setText(String.valueOf(JTSetelahPotongTindakan.getInt() - JTDiskonTindakan.getInt()));
//            }
//        }
//    }
    void setSetelahPotongObat() {
        if (JTDiskonObat.getInt() != 0 || JTPoinObat.getInt() != 0) {
            JLSetelahPotongObat.setVisible(true);
            JLSetelahPotongObat2.setVisible(true);
            JTSetelahPotongObat.setVisible(true);
            if (JTDiskonObat.getInt() <= 100) {
                if (JTPoinObat.getInt() != 0) {
                    JTSetelahPotongObat.setInt(String.valueOf(JTTotalObat.getInt() - Math.round(JTTotalObat.getInt() * JTDiskonObat.getInt() / 100) - (JTPoinObat.getInt() * 1000)));
                } else {
                    JTSetelahPotongObat.setInt(String.valueOf(JTTotalObat.getInt() - Math.round(JTTotalObat.getInt() * JTDiskonObat.getInt() / 100)));
                }
            } else {
                if (JTPoinObat.getInt() != 0) {
                    JTSetelahPotongObat.setInt(String.valueOf(JTTotalObat.getInt() - JTDiskonObat.getInt() - (JTPoinObat.getInt() * 1000)));
                } else {
                    JTSetelahPotongObat.setInt(String.valueOf(JTTotalObat.getInt() - JTDiskonObat.getInt()));
                }

            }
        } else {
            JLSetelahPotongObat.setVisible(false);
            JLSetelahPotongObat2.setVisible(false);
            JTSetelahPotongObat.setVisible(false);
            JTSetelahPotongObat.setText("");
        }
    }

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

    int getTotalDiskonTindakan() {
        int totalDiskon = 0;
        for (int i = 0; i < JTableTindakan.getRowCount(); i++) {
            if (Integer.valueOf(JTableTindakan.getValueAt(i, 3).toString().replace(" %", "").replace(".", "")) <= 100) {
                totalDiskon += Math.round(Integer.valueOf(JTableTindakan.getValueAt(i, 1).toString().replace(".", "")) * Integer.valueOf(JTableTindakan.getValueAt(i, 2).toString().replace(".", "")) * (JTableTindakan.getValueAt(i, 3).toString().replace(" %", "").replace(".", "").equals("") ? 0 : Integer.valueOf(JTableTindakan.getValueAt(i, 3).toString().replace(" %", "").replace(".", ""))) / 100);
            } else {
                totalDiskon += Integer.valueOf(JTableTindakan.getValueAt(i, 3).toString().replace(" %", "").replace(".", ""));
            }
        }
        return totalDiskon;
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

        JCMetodePembayaran = new KomponenGUI.JcomboboxF();
        jlableF2 = new KomponenGUI.JlableF();
        jlableF3 = new KomponenGUI.JlableF();
        JTNamaPasien = new KomponenGUI.JtextF();
        jlableF4 = new KomponenGUI.JlableF();
        jlableF5 = new KomponenGUI.JlableF();
        JCNamaDokter = new KomponenGUI.JcomboboxF();
        jlableF6 = new KomponenGUI.JlableF();
        jlableF7 = new KomponenGUI.JlableF();
        JCNamaBeautician = new KomponenGUI.JcomboboxF();
        jPanel1 = new javax.swing.JPanel();
        JBTambahTindakan = new KomponenGUI.JbuttonF();
        JTJumlahTindakan = new KomponenGUI.JPlaceHolder();
        JCTindakan = new KomponenGUI.JcomboboxF();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableTindakan = new KomponenGUI.JtableF();
        JBHapusTindakan = new KomponenGUI.JbuttonF();
        JTHargaTindakan = new KomponenGUI.JPlaceHolder();
        JTSubTotalTindakan = new KomponenGUI.JPlaceHolder();
        JBRefreshTindakan = new KomponenGUI.JbuttonF();
        jlableF27 = new KomponenGUI.JlableF();
        jlableF28 = new KomponenGUI.JlableF();
        JTTotalTindakan = new KomponenGUI.JPlaceHolder();
        JTDiskonTindakan = new KomponenGUI.JPlaceHolder();
        JLPoinTindakan = new KomponenGUI.JlableF();
        jlableF32 = new KomponenGUI.JlableF();
        JLSetelahPotongTindakan = new KomponenGUI.JlableF();
        JLSetelahPotongTindakan2 = new KomponenGUI.JlableF();
        jSeparator3 = new javax.swing.JSeparator();
        JTPoinTindakan = new KomponenGUI.JPlaceHolder();
        JTSetelahPotongTindakan = new KomponenGUI.JPlaceHolder();
        jPanel2 = new javax.swing.JPanel();
        JBTambahObat = new KomponenGUI.JbuttonF();
        JTJumlahObat = new KomponenGUI.JPlaceHolder();
        JCObat = new KomponenGUI.JcomboboxF();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableObat = new KomponenGUI.JtableF();
        JBHapusObat = new KomponenGUI.JbuttonF();
        JTHargaObat = new KomponenGUI.JPlaceHolder();
        JTSubTotalObat = new KomponenGUI.JPlaceHolder();
        JBRefreshObat = new KomponenGUI.JbuttonF();
        jlableF39 = new KomponenGUI.JlableF();
        jlableF40 = new KomponenGUI.JlableF();
        JTTotalObat = new KomponenGUI.JPlaceHolder();
        jSeparator4 = new javax.swing.JSeparator();
        jlableF41 = new KomponenGUI.JlableF();
        jlableF42 = new KomponenGUI.JlableF();
        JTDiskonObat = new KomponenGUI.JPlaceHolder();
        jlableF36 = new KomponenGUI.JlableF();
        JLPoinObat = new KomponenGUI.JlableF();
        JLSetelahPotongObat = new KomponenGUI.JlableF();
        JLSetelahPotongObat2 = new KomponenGUI.JlableF();
        JLPersenObat = new KomponenGUI.JlableF();
        JTPoinObat = new KomponenGUI.JPlaceHolder();
        JTSetelahPotongObat = new KomponenGUI.JPlaceHolder();
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
        jlableF22 = new KomponenGUI.JlableF();
        JTNoBilling = new KomponenGUI.JtextF();
        jlableF23 = new KomponenGUI.JlableF();
        JTGrandTotal = new KomponenGUI.JPlaceHolder();
        jlableF8 = new KomponenGUI.JlableF();
        jlableF9 = new KomponenGUI.JlableF();
        jlableF10 = new KomponenGUI.JlableF();
        jlableF11 = new KomponenGUI.JlableF();
        JTBayar = new KomponenGUI.JPlaceHolder();
        JBTambahPrint = new KomponenGUI.JbuttonF();
        JBUbahPrint = new KomponenGUI.JbuttonF();
        jlableF25 = new KomponenGUI.JlableF();
        jlableF26 = new KomponenGUI.JlableF();
        JTKembali = new KomponenGUI.JRibuanTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jlableF29 = new KomponenGUI.JlableF();
        jlableF30 = new KomponenGUI.JlableF();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        JCMetodePembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tunai", "Debit" }));

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

        JCNamaBeautician.load("SELECT '-- Pilih Nama Beautician --' as 'Nama Beautician' UNION ALL SELECT `NamaBeautician` FROM `tbmbeautician` WHERE `Status` = 1 ");
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
        JTJumlahTindakan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTJumlahTindakanFocusLost(evt);
            }
        });
        JTJumlahTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTJumlahTindakanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTJumlahTindakanKeyReleased(evt);
            }
        });

        JCTindakan.load("SELECT '-- Pilih Tindakan --' as 'NamaTindakan' UNION ALL (SELECT `NamaTindakan` FROM `tbmtindakan` WHERE `Status` = 1 ORDER BY `NamaTindakan`)");
        JCTindakan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCTindakanItemStateChanged(evt);
            }
        });
        JCTindakan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JCTindakanFocusLost(evt);
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
                "Tindakan", "Jumlah", "Harga", "Diskon", "Sub Total"
            }
        ));
        JTableTindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableTindakanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableTindakan);
        if (JTableTindakan.getColumnModel().getColumnCount() > 0) {
            JTableTindakan.getColumnModel().getColumn(0).setMinWidth(340);
            JTableTindakan.getColumnModel().getColumn(0).setPreferredWidth(340);
            JTableTindakan.getColumnModel().getColumn(0).setMaxWidth(340);
            JTableTindakan.getColumnModel().getColumn(1).setMinWidth(80);
            JTableTindakan.getColumnModel().getColumn(1).setPreferredWidth(80);
            JTableTindakan.getColumnModel().getColumn(1).setMaxWidth(80);
            JTableTindakan.getColumnModel().getColumn(2).setMinWidth(105);
            JTableTindakan.getColumnModel().getColumn(2).setPreferredWidth(105);
            JTableTindakan.getColumnModel().getColumn(2).setMaxWidth(105);
            JTableTindakan.getColumnModel().getColumn(3).setMinWidth(80);
            JTableTindakan.getColumnModel().getColumn(3).setPreferredWidth(80);
            JTableTindakan.getColumnModel().getColumn(3).setMaxWidth(80);
        }
        JTableTindakan.setrender(new int[]{1,2,3,4}, new String[]{"Number","Number","Right","Number"});

        JBHapusTindakan.setText("Hapus");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTableTindakan, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), JBHapusTindakan, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        JBHapusTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBHapusTindakanActionPerformed(evt);
            }
        });

        JTHargaTindakan.setPlaceholder("Harga");
        JTHargaTindakan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTHargaTindakanFocusLost(evt);
            }
        });
        JTHargaTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTHargaTindakanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTHargaTindakanKeyReleased(evt);
            }
        });

        JTSubTotalTindakan.setPlaceholder("Sub Total");
        JTSubTotalTindakan.setEnabled(false);
        JTSubTotalTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTSubTotalTindakanKeyPressed(evt);
            }
        });

        JBRefreshTindakan.setText("Refresh");
        JBRefreshTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRefreshTindakanActionPerformed(evt);
            }
        });

        jlableF27.setText("Total Tind.");

        jlableF28.setText(":");

        //JTTotalTindakan.setPlaceholder("Total Tindakan");
        JTTotalTindakan.setEnabled(false);
        JTTotalTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTTotalTindakanKeyPressed(evt);
            }
        });

        JTDiskonTindakan.setPlaceholder("Diskon");
        JTDiskonTindakan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTDiskonTindakanFocusLost(evt);
            }
        });
        JTDiskonTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTDiskonTindakanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTDiskonTindakanKeyReleased(evt);
            }
        });

        JLPoinTindakan.setText("Poin (000)");

        jlableF32.setText(":");

        JLSetelahPotongTindakan.setText("Setelah Potong");

        JLSetelahPotongTindakan2.setText(":");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        JTPoinTindakan.setPlaceholder("Pakai Poin");
        JTPoinTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTPoinTindakanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTPoinTindakanKeyReleased(evt);
            }
        });

        //JTSetelahPotongTindakan.setPlaceholder("Setelah Potong");
        JTSetelahPotongTindakan.setEnabled(false);
        JTSetelahPotongTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTSetelahPotongTindakanKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JCTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTJumlahTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTHargaTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTDiskonTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTSubTotalTindakan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBHapusTindakan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambahTindakan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBRefreshTindakan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jlableF27, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlableF28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTTotalTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(JLSetelahPotongTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JLSetelahPotongTindakan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTSetelahPotongTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JLPoinTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlableF32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTPoinTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTTotalTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTPoinTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLPoinTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTSetelahPotongTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLSetelahPotongTindakan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLSetelahPotongTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JCTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTJumlahTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTHargaTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTSubTotalTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTDiskonTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBTambahTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JBHapusTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBRefreshTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JBTambahObat.setText("Tambah");
        JBTambahObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTambahObatActionPerformed(evt);
            }
        });

        JTJumlahObat.setPlaceholder("Jumlah");
        JTJumlahObat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTJumlahObatFocusLost(evt);
            }
        });
        JTJumlahObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTJumlahObatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTJumlahObatKeyReleased(evt);
            }
        });

        JCObat.load("SELECT '-- Pilih Obat --' as 'NamaBarang' UNION ALL (SELECT `NamaBarang` FROM `tbmbarang` WHERE `Status` = 1 ORDER BY `NamaBarang`)");
        JCObat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCObatItemStateChanged(evt);
            }
        });
        JCObat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JCObatFocusLost(evt);
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
                "Obat", "Jumlah", "Harga", "Sub Total"
            }
        ));
        JTableObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableObatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTableObat);
        if (JTableObat.getColumnModel().getColumnCount() > 0) {
            JTableObat.getColumnModel().getColumn(0).setMinWidth(413);
            JTableObat.getColumnModel().getColumn(0).setPreferredWidth(413);
            JTableObat.getColumnModel().getColumn(0).setMaxWidth(413);
            JTableObat.getColumnModel().getColumn(1).setMinWidth(105);
            JTableObat.getColumnModel().getColumn(1).setPreferredWidth(105);
            JTableObat.getColumnModel().getColumn(1).setMaxWidth(105);
            JTableObat.getColumnModel().getColumn(2).setMinWidth(105);
            JTableObat.getColumnModel().getColumn(2).setPreferredWidth(105);
            JTableObat.getColumnModel().getColumn(2).setMaxWidth(105);
        }
        JTableObat.setrender(new int[]{1,2,3}, new String[]{"Number","Number","Number"});

        JBHapusObat.setText("Hapus");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTableObat, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), JBHapusObat, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        JBHapusObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBHapusObatActionPerformed(evt);
            }
        });

        JTHargaObat.setPlaceholder("Harga");
        JTHargaObat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTHargaObatFocusLost(evt);
            }
        });
        JTHargaObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTHargaObatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTHargaObatKeyReleased(evt);
            }
        });

        JTSubTotalObat.setPlaceholder("Sub Total");
        JTSubTotalObat.setEnabled(false);
        JTSubTotalObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTSubTotalObatKeyPressed(evt);
            }
        });

        JBRefreshObat.setText("Refresh");
        JBRefreshObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRefreshObatActionPerformed(evt);
            }
        });

        jlableF39.setText("Total Obat");

        jlableF40.setText(":");

        //JTTotalObat.setPlaceholder("Total Obat");
        JTTotalObat.setEnabled(false);
        JTTotalObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTTotalObatKeyPressed(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jlableF41.setText("Diskon");

        jlableF42.setText(":");

        JTDiskonObat.setPlaceholder("Diskon");
        JTDiskonObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTDiskonObatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTDiskonObatKeyReleased(evt);
            }
        });

        jlableF36.setText(":");

        JLPoinObat.setText("Poin (000)");

        JLSetelahPotongObat.setText("Setelah Potong");

        JLSetelahPotongObat2.setText(":");

        JLPersenObat.setText("(%)");

        JTPoinObat.setPlaceholder("Pakai Poin");
        JTPoinObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTPoinObatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTPoinObatKeyReleased(evt);
            }
        });

        //JTSetelahPotongObat.setPlaceholder("Setelah Potong");
        JTSetelahPotongObat.setEnabled(false);
        JTSetelahPotongObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTSetelahPotongObatKeyPressed(evt);
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JBHapusObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBRefreshObat, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JCObat, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTJumlahObat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTHargaObat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTSubTotalObat, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTambahObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLSetelahPotongObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLPoinObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlableF39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlableF41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JLPersenObat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlableF40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTTotalObat, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlableF42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTDiskonObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlableF36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLSetelahPotongObat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTSetelahPotongObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JTPoinObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTJumlahObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTHargaObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTSubTotalObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambahObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(JBHapusObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBRefreshObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTTotalObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTDiskonObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLPersenObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTPoinObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLPoinObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLSetelahPotongObat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLSetelahPotongObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTSetelahPotongObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator4))
                .addContainerGap())
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

        JTNoAntrian.setEnabled(false);

        jlableF22.setText(":");

        JTNoBilling.setEnabled(false);

        jlableF23.setText("No. Nota");

        JTGrandTotal.setPlaceholder("Grand Total");
        JTGrandTotal.setEnabled(false);
        JTGrandTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTGrandTotalKeyPressed(evt);
            }
        });

        jlableF8.setText(":");

        jlableF9.setText("Grand Total");

        jlableF10.setText("Bayar");

        jlableF11.setText(":");

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

        jlableF25.setText("Kembali");

        jlableF26.setText(":");

        JTKembali.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        JTKembali.setEnabled(false);

        jlableF29.setText("Metode Bayar");

        jlableF30.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlableF18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlableF2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlableF4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlableF6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlableF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JCNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlableF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JCNamaBeautician, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlableF15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlableF14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JDTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jlableF20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlableF21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTNoAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF17, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jlableF16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jlableF23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jlableF22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(JTNoBilling, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(JTNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(JBKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JBUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBUbahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBTambahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlableF19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator5)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlableF9, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jlableF10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlableF25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlableF29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlableF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JTGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlableF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlableF26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(JTKembali, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JTBayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlableF30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCMetodePembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlableF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JDTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlableF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTNoAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlableF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCNamaBeautician, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTNoBilling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jlableF18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jlableF19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlableF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlableF29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlableF30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCMetodePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTambahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBUbahPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bindingGroup.bind();

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
            JTHargaTindakan.requestFocus();
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
            JTHargaObat.requestFocus();
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
            tambahBilling = null;
        } else {
            ubahBilling = null;
        }
    }//GEN-LAST:event_formWindowClosed

    private void JBHapusTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBHapusTindakanActionPerformed
        hapusTableTindakan();
    }//GEN-LAST:event_JBHapusTindakanActionPerformed

    private void JBHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBHapusObatActionPerformed
        hapusTableObat();
    }//GEN-LAST:event_JBHapusObatActionPerformed

    private void JTHargaTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTHargaTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTDiskonTindakan.requestFocus();
        }
    }//GEN-LAST:event_JTHargaTindakanKeyPressed

    private void JTHargaObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTHargaObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JBTambahObat.getText().equals("Tambah")) {
                tambahTableObat();
            } else {
                ubahTableObat();
            }
        }
    }//GEN-LAST:event_JTHargaObatKeyPressed

    private void JBTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahActionPerformed
        tambahData(false);
    }//GEN-LAST:event_JBTambahActionPerformed

    private void JBUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBUbahActionPerformed
        ubahData(false);
    }//GEN-LAST:event_JBUbahActionPerformed

    private void JCTindakanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCTindakanItemStateChanged
        setHargaTindakan();
        setSubTotalTindakan();
    }//GEN-LAST:event_JCTindakanItemStateChanged

    private void JCObatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCObatItemStateChanged
        setHargaObat();
        setSubTotalObat();
    }//GEN-LAST:event_JCObatItemStateChanged
    private void JBKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBKembaliActionPerformed
        dispose();
    }//GEN-LAST:event_JBKembaliActionPerformed

    private void JCNamaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCNamaDokterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JCNamaBeautician.requestFocus();
        }
    }//GEN-LAST:event_JCNamaDokterKeyPressed

    private void JCNamaBeauticianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCNamaBeauticianKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JCTindakan.requestFocus();
        }
    }//GEN-LAST:event_JCNamaBeauticianKeyPressed

    private void JTSubTotalTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTSubTotalTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tambahTableTindakan();
        }
    }//GEN-LAST:event_JTSubTotalTindakanKeyPressed

    private void JTSubTotalObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTSubTotalObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tambahTableObat();
        }
    }//GEN-LAST:event_JTSubTotalObatKeyPressed

    private void JTJumlahTindakanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTJumlahTindakanKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            setSubTotalTindakan();
        }
    }//GEN-LAST:event_JTJumlahTindakanKeyReleased

    private void JTHargaTindakanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTHargaTindakanKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            setSubTotalTindakan();
        }
    }//GEN-LAST:event_JTHargaTindakanKeyReleased

    private void JTJumlahObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTJumlahObatKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            setSubTotalObat();
        }
    }//GEN-LAST:event_JTJumlahObatKeyReleased

    private void JTHargaObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTHargaObatKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            setSubTotalObat();
        }
    }//GEN-LAST:event_JTHargaObatKeyReleased

    private void JTGrandTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTGrandTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTGrandTotalKeyPressed

    private void JTBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTBayarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JBTambah.isVisible()) {
                tambahData(true);
            } else {
                ubahData(true);
            }
        }
    }//GEN-LAST:event_JTBayarKeyPressed

    private void JTableTindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableTindakanMouseClicked
        if (JTableTindakan.getSelectedRow() != -1) {
            JCTindakan.setSelectedItem(JTableTindakan.getValueAt(JTableTindakan.getSelectedRow(), 0).toString());
            JTJumlahTindakan.setText(JTableTindakan.getValueAt(JTableTindakan.getSelectedRow(), 1).toString());
            JTHargaTindakan.setText(JTableTindakan.getValueAt(JTableTindakan.getSelectedRow(), 2).toString());
            JTDiskonTindakan.setText(JTableTindakan.getValueAt(JTableTindakan.getSelectedRow(), 3).toString().replace(" %", ""));
            JTSubTotalTindakan.setText(JTableTindakan.getValueAt(JTableTindakan.getSelectedRow(), 4).toString());
            JBTambahTindakan.setText("Ubah");
        } else {
            JBTambahTindakan.setText("Tambah");
        }
    }//GEN-LAST:event_JTableTindakanMouseClicked

    private void JBRefreshTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRefreshTindakanActionPerformed
        refreshTindakan();
    }//GEN-LAST:event_JBRefreshTindakanActionPerformed

    private void JBRefreshObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRefreshObatActionPerformed
        refreshObat();
    }//GEN-LAST:event_JBRefreshObatActionPerformed

    private void JTableObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableObatMouseClicked
        if (JTableObat.getSelectedRow() != -1) {
            JCObat.setSelectedItem(JTableObat.getValueAt(JTableObat.getSelectedRow(), 0).toString());
            JTJumlahObat.setText(JTableObat.getValueAt(JTableObat.getSelectedRow(), 1).toString());
            JTHargaObat.setText(JTableObat.getValueAt(JTableObat.getSelectedRow(), 2).toString());
            JTSubTotalObat.setText(JTableObat.getValueAt(JTableObat.getSelectedRow(), 3).toString());
            JBTambahObat.setText("Ubah");
        } else {
            JBTambahObat.setText("Tambah");
        }
    }//GEN-LAST:event_JTableObatMouseClicked

    private void JTJumlahTindakanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTJumlahTindakanFocusLost
        setSubTotalTindakan();
    }//GEN-LAST:event_JTJumlahTindakanFocusLost

    private void JTHargaTindakanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTHargaTindakanFocusLost
        setSubTotalTindakan();
    }//GEN-LAST:event_JTHargaTindakanFocusLost

    private void JTJumlahObatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTJumlahObatFocusLost
        setSubTotalObat();
    }//GEN-LAST:event_JTJumlahObatFocusLost

    private void JTHargaObatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTHargaObatFocusLost
        setSubTotalObat();
    }//GEN-LAST:event_JTHargaObatFocusLost

    private void JCTindakanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JCTindakanFocusLost
        setSubTotalTindakan();
    }//GEN-LAST:event_JCTindakanFocusLost

    private void JCObatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JCObatFocusLost
        setSubTotalObat();
    }//GEN-LAST:event_JCObatFocusLost

    private void JBTambahPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTambahPrintActionPerformed
        tambahData(true);
    }//GEN-LAST:event_JBTambahPrintActionPerformed

    private void JBUbahPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBUbahPrintActionPerformed
        ubahData(true);
    }//GEN-LAST:event_JBUbahPrintActionPerformed

    private void JTBayarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTBayarFocusLost
        setKembalian();
    }//GEN-LAST:event_JTBayarFocusLost

    private void JTBayarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTBayarFocusGained
        setKembalian();
    }//GEN-LAST:event_JTBayarFocusGained

    private void JTBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTBayarKeyReleased
        setKembalian();
    }//GEN-LAST:event_JTBayarKeyReleased

    private void JTTotalTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTTotalTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTTotalTindakanKeyPressed

    private void JTDiskonTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTDiskonTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JBTambahTindakan.getText().equals("Tambah")) {
                tambahTableTindakan();
            } else {
                ubahTableTindakan();
            }
        }
    }//GEN-LAST:event_JTDiskonTindakanKeyPressed

    private void JTTotalObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTTotalObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTTotalObatKeyPressed

    private void JTDiskonObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTDiskonObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JTPoinObat.isEnabled()) {
                JTPoinObat.requestFocus();
            } else {
                JTBayar.requestFocus();
            }
        }
    }//GEN-LAST:event_JTDiskonObatKeyPressed

    private void JTDiskonTindakanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTDiskonTindakanKeyReleased
        setSubTotalTindakan();
    }//GEN-LAST:event_JTDiskonTindakanKeyReleased

    private void JTDiskonObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTDiskonObatKeyReleased
        JLPersenObat.setText(JTDiskonObat.getInt() <= 100 ? "(%)" : "");
        setSetelahPotongObat();
        setGrandTotal();
    }//GEN-LAST:event_JTDiskonObatKeyReleased

    private void JTDiskonTindakanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTDiskonTindakanFocusLost
        setSubTotalTindakan();
    }//GEN-LAST:event_JTDiskonTindakanFocusLost

    private void JTPoinTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPoinTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTPoinTindakanKeyPressed

    private void JTPoinTindakanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPoinTindakanKeyReleased
//        int poin = Integer.valueOf(JLPoinTindakan.getText().split("\\(")[1].split("\\)")[0]);
        if (JTPoinTindakan.getInt() != 0) {
            if (JTPoinTindakan.getInt() + JTPoinObat.getInt() > Integer.valueOf(JLPoinTindakan.getText().split("\\(")[1].split("\\)")[0])) {
                getToolkit().beep();
                JTPoinTindakan.setText(JTPoinTindakan.getText().replace(".", "").substring(0, JTPoinTindakan.getText().replace(".", "").length() - 1));
            } else {
                JLSetelahPotongTindakan.setVisible(true);
                JLSetelahPotongTindakan2.setVisible(true);
                JTSetelahPotongTindakan.setVisible(true);
                JTSetelahPotongTindakan.setInt(getSetelahPotongPoinTindakan());
//                JLPoinObat.setText("Poin (" + (poin - JTPoinTindakan.getInt()) + ")");
                setGrandTotal();
            }
        } else {
            JLSetelahPotongTindakan.setVisible(false);
            JLSetelahPotongTindakan2.setVisible(false);
            JTSetelahPotongTindakan.setVisible(false);
//            JLPoinObat.setText("Poin (" + poin + ")");
        }
        if (Integer.valueOf(JLPoinTindakan.getText().split("\\(")[1].split("\\)")[0]) - JTPoinTindakan.getInt() == 0) {
            JTPoinObat.setEnabled(false);
        } else {
            JTPoinObat.setEnabled(true);
        }
    }//GEN-LAST:event_JTPoinTindakanKeyReleased

    private void JTPoinObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPoinObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTBayar.requestFocus();
        }
    }//GEN-LAST:event_JTPoinObatKeyPressed

    private void JTPoinObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPoinObatKeyReleased
        if (JTPoinObat.getInt() + JTPoinTindakan.getInt() > Integer.valueOf(JLPoinObat.getText().split("\\(")[1].split("\\)")[0])) {
            getToolkit().beep();
            JTPoinObat.setText(JTPoinObat.getText().replace(".", "").substring(0, JTPoinObat.getText().replace(".", "").length() - 1));
        } else {
            setSetelahPotongObat();
            setGrandTotal();
        }
    }//GEN-LAST:event_JTPoinObatKeyReleased

    private void JTSetelahPotongTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTSetelahPotongTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTSetelahPotongTindakanKeyPressed

    private void JTSetelahPotongObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTSetelahPotongObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTSetelahPotongObatKeyPressed

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
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Billing("", "").setVisible(true);
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
    private KomponenGUI.JcomboboxF JCMetodePembayaran;
    private KomponenGUI.JcomboboxF JCNamaBeautician;
    private KomponenGUI.JcomboboxF JCNamaDokter;
    private KomponenGUI.JcomboboxF JCObat;
    private KomponenGUI.JcomboboxF JCTindakan;
    private static KomponenGUI.JdateCF JDTanggal;
    private KomponenGUI.JlableF JLPersenObat;
    private KomponenGUI.JlableF JLPoinObat;
    private KomponenGUI.JlableF JLPoinTindakan;
    private KomponenGUI.JlableF JLSetelahPotongObat;
    private KomponenGUI.JlableF JLSetelahPotongObat2;
    private KomponenGUI.JlableF JLSetelahPotongTindakan;
    private KomponenGUI.JlableF JLSetelahPotongTindakan2;
    private KomponenGUI.JPlaceHolder JTBayar;
    private KomponenGUI.JPlaceHolder JTDiskonObat;
    private KomponenGUI.JPlaceHolder JTDiskonTindakan;
    private KomponenGUI.JPlaceHolder JTGrandTotal;
    private KomponenGUI.JPlaceHolder JTHargaObat;
    private KomponenGUI.JPlaceHolder JTHargaTindakan;
    private KomponenGUI.JPlaceHolder JTJumlahObat;
    private KomponenGUI.JPlaceHolder JTJumlahTindakan;
    private KomponenGUI.JRibuanTextField JTKembali;
    private KomponenGUI.JtextF JTNamaPasien;
    private KomponenGUI.JtextF JTNoAntrian;
    private KomponenGUI.JtextF JTNoBilling;
    private KomponenGUI.JtextF JTNoTransaksi;
    private KomponenGUI.JPlaceHolder JTPoinObat;
    private KomponenGUI.JPlaceHolder JTPoinTindakan;
    private KomponenGUI.JPlaceHolder JTSetelahPotongObat;
    private KomponenGUI.JPlaceHolder JTSetelahPotongTindakan;
    private KomponenGUI.JPlaceHolder JTSubTotalObat;
    private KomponenGUI.JPlaceHolder JTSubTotalTindakan;
    private KomponenGUI.JPlaceHolder JTTotalObat;
    private KomponenGUI.JPlaceHolder JTTotalTindakan;
    private KomponenGUI.JtableF JTableObat;
    private KomponenGUI.JtableF JTableTindakan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private KomponenGUI.JlableF jlableF10;
    private KomponenGUI.JlableF jlableF11;
    private KomponenGUI.JlableF jlableF14;
    private KomponenGUI.JlableF jlableF15;
    private KomponenGUI.JlableF jlableF16;
    private KomponenGUI.JlableF jlableF17;
    private KomponenGUI.JlableF jlableF18;
    private KomponenGUI.JlableF jlableF19;
    private KomponenGUI.JlableF jlableF2;
    private KomponenGUI.JlableF jlableF20;
    private KomponenGUI.JlableF jlableF21;
    private KomponenGUI.JlableF jlableF22;
    private KomponenGUI.JlableF jlableF23;
    private KomponenGUI.JlableF jlableF25;
    private KomponenGUI.JlableF jlableF26;
    private KomponenGUI.JlableF jlableF27;
    private KomponenGUI.JlableF jlableF28;
    private KomponenGUI.JlableF jlableF29;
    private KomponenGUI.JlableF jlableF3;
    private KomponenGUI.JlableF jlableF30;
    private KomponenGUI.JlableF jlableF32;
    private KomponenGUI.JlableF jlableF36;
    private KomponenGUI.JlableF jlableF39;
    private KomponenGUI.JlableF jlableF4;
    private KomponenGUI.JlableF jlableF40;
    private KomponenGUI.JlableF jlableF41;
    private KomponenGUI.JlableF jlableF42;
    private KomponenGUI.JlableF jlableF5;
    private KomponenGUI.JlableF jlableF6;
    private KomponenGUI.JlableF jlableF7;
    private KomponenGUI.JlableF jlableF8;
    private KomponenGUI.JlableF jlableF9;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    void tambahTableTindakan() {
        if (checkTableTindakan()) {
            if (JCTindakan.getSelectedIndex() != 0) {
                DefaultTableModel model = (DefaultTableModel) JTableTindakan.getModel();
                model.addRow(new Object[]{JCTindakan.getSelectedItem(), JTJumlahTindakan.getText(), JTHargaTindakan.getText(), JTDiskonTindakan.getInt() <= 100 && JTDiskonTindakan.getInt() > 0 ? JTDiskonTindakan.getInt() + " %" : JTDiskonTindakan.getText(), JTSubTotalTindakan.getText()});
                setTotalTindakan();
                setGrandTotal();
                refreshTindakan();
            } else {
                JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Pilih Tindakan Terlebih Dahulu");
            }
        }
    }

    void tambahTableObat() {
        if (checkTableObat()) {
            if (JCObat.getSelectedIndex() != 0) {
                DefaultTableModel model = (DefaultTableModel) JTableObat.getModel();
                model.addRow(new Object[]{JCObat.getSelectedItem(), JTJumlahObat.getText(), JTHargaObat.getText(), JTSubTotalObat.getText()});
                setTotalObat();
                setGrandTotal();
                refreshObat();
            } else {
                JOptionPaneF.showMessageDialog(this, "Gagal. Silahkan Pilih Obat Terlebih Dahulu");
            }
        }
    }

    void hapusTableTindakan() {
        if (JTableTindakan.getSelectedRow() != -1) {
            ((DefaultTableModel) JTableTindakan.getModel()).removeRow(JTableTindakan.getSelectedRow());
            setTotalTindakan();
            setGrandTotal();
            refreshTindakan();
        }
    }

    void hapusTableObat() {
        if (JTableObat.getSelectedRow() != -1) {
            ((DefaultTableModel) JTableObat.getModel()).removeRow(JTableObat.getSelectedRow());
            setTotalObat();
            setGrandTotal();
            refreshObat();
        }
    }

    void ubahTableTindakan() {
        if (JTableTindakan.getSelectedRow() != -1) {
            if (checkTableTindakan()) {
                JTableTindakan.setValueAt(JCTindakan.getSelectedItem(), JTableTindakan.getSelectedRow(), 0);
                JTableTindakan.setValueAt(JTJumlahTindakan.getText(), JTableTindakan.getSelectedRow(), 1);
                JTableTindakan.setValueAt(JTHargaTindakan.getText(), JTableTindakan.getSelectedRow(), 2);
                JTableTindakan.setValueAt(JTDiskonTindakan.getInt() <= 100 ? JTDiskonTindakan.getInt() + " %" : JTDiskonTindakan.getText(), JTableTindakan.getSelectedRow(), 3);
                JTableTindakan.setValueAt(JTSubTotalTindakan.getText(), JTableTindakan.getSelectedRow(), 4);
                setTotalTindakan();
                setGrandTotal();
                refreshTindakan();
            }
        }
    }

    void ubahTableObat() {
        if (JTableObat.getSelectedRow() != -1) {
            if (checkTableObat()) {
                JTableObat.setValueAt(JCObat.getSelectedItem(), JTableObat.getSelectedRow(), 0);
                JTableObat.setValueAt(JTJumlahObat.getText(), JTableObat.getSelectedRow(), 1);
                JTableObat.setValueAt(JTHargaObat.getText(), JTableObat.getSelectedRow(), 2);
                JTableObat.setValueAt(JTSubTotalObat.getText(), JTableObat.getSelectedRow(), 3);
                setTotalObat();
                setGrandTotal();
                refreshObat();
            }
        }
    }

    void refreshTindakan() {
        JCTindakan.setSelectedIndex(0);
        JTJumlahTindakan.setText("");
        JTHargaTindakan.setText("");
        JTDiskonTindakan.setText("");
        JTSubTotalTindakan.setText("");
        JTableTindakan.clearSelection();
        JBTambahTindakan.setText("Tambah");
        JCTindakan.requestFocus();
    }

    void refreshObat() {
        JCObat.setSelectedIndex(0);
        JTJumlahObat.setText("");
        JTHargaObat.setText("");
        JTSubTotalObat.setText("");
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
                    String dokter = JTNoTransaksi.getText().contains("PJ") ? "(SELECT `IdDokter` FROM `tbmdokter` WHERE `NamaDokter` = '" + JCNamaDokter.getSelectedItem() + "')" : "null";
                    String beautician = JTNoTransaksi.getText().contains("PJ") ? "(SELECT `IdBeautician` FROM `tbmbeautician` WHERE `NamaBeautician` = '" + JCNamaBeautician.getSelectedItem() + "')" : "null";
                    Berhasil = multiInsert.Excute("INSERT INTO `tbbilling`(`IdAntrian`, `NoBilling`, `Tanggal`, `NoTransaksi`, `IdDokter`, `IdBeautician`, `Bayar`, `DiskonObat`, `PoinTindakan`, `PoinObat`, `MetodePembayaran`, `Kasir`) VALUES ('" + Parameter + "','" + JTNoBilling.getText() + "','" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "','" + JTNoTransaksi.getText() + "'," + dokter + "," + beautician + ",'" + JTBayar.getNumberFormattedText() + "', " + JTDiskonObat.getInt() + ", " + JTPoinTindakan.getInt() + ", '" + JTPoinObat.getInt() + "', '" + JCMetodePembayaran.getSelectedItem() + "','" + GlobalVar.VarL.username + "')", null);
                    if (Berhasil) {
                        for (int i = 0; i < JTableTindakan.getRowCount(); i++) {
                            Berhasil = multiInsert.Excute("INSERT INTO `tbbillingtindakan`(`NoBilling`, `IdTindakan`, `Jumlah`, `Harga`, `DiskonTindakan`) VALUES ('" + JTNoBilling.getText() + "',(SELECT `IdTindakan` FROM `tbmtindakan` WHERE `NamaTindakan` = '" + JTableTindakan.getValueAt(i, 0) + "'),'" + JTableTindakan.getValueAt(i, 1).toString().replace(".", "") + "','" + JTableTindakan.getValueAt(i, 2).toString().replace(".", "") + "','" + (JTableTindakan.getValueAt(i, 3).equals("") ? "0" : JTableTindakan.getValueAt(i, 3).toString().replace(".", "").replace(" %", "")) + "')", null);
                        }
                        if (Berhasil) {
                            for (int j = 0; j < JTableObat.getRowCount(); j++) {
                                Berhasil = multiInsert.Excute("INSERT INTO `tbbillingobat`(`NoBilling`, `IdObat`, `Jumlah`, `Harga`) VALUES ('" + JTNoBilling.getText() + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTableObat.getValueAt(j, 0) + "'),'" + JTableObat.getValueAt(j, 1).toString().replace(".", "") + "','" + JTableObat.getValueAt(j, 2).toString().replace(".", "") + "')", null);
                            }
                            if (Berhasil) {
                                Berhasil = multiInsert.Excute("UPDATE `tbantrian` SET `Status` = 1 WHERE `IdAntrian` = '" + Parameter + "'", null);
                            }
                        }
                    }
                }
                if (Berhasil == false) {
                    multiInsert.rollback();
//                    multiInsert.closecon();
                    JOptionPaneF.showMessageDialog(this, "Gagal Tambah Data Billing");
                }
                if (Berhasil == true) {
                    JOptionPaneF.showMessageDialog(this, "Berhasil Tambah Data Billing");
                    multiInsert.Commit();
//                    multiInsert.closecon();
                    if (print) {
                        printing();
                    }
                    if (listBilling != null) {
                        listBilling.load();
                    }
                    if (listAntrianBilling != null) {
                        listAntrianBilling.load();
                        if (listAntrianBilling.jcomCari1.jtablef.getRowCount() == 0) {
                            listAntrianBilling.dispose();
                        }
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
                    String dokter = JTNoTransaksi.getText().contains("PJ") ? "(SELECT `IdDokter` FROM `tbmdokter` WHERE `NamaDokter` = '" + JCNamaDokter.getSelectedItem() + "')" : "null";
                    String beautician = JTNoTransaksi.getText().contains("PJ") ? "(SELECT `IdBeautician` FROM `tbmbeautician` WHERE `NamaBeautician` = '" + JCNamaBeautician.getSelectedItem() + "')" : "null";
                    Berhasil = multiInsert.Excute("UPDATE `tbbilling` SET `NoBilling`='" + JTNoBilling.getText() + "',`Tanggal`='" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "',`NoTransaksi`='" + JTNoTransaksi.getText() + "', `IdDokter` = " + dokter + ",`IdBeautician` = " + beautician + ",`Bayar`='" + JTBayar.getNumberFormattedText() + "', `DiskonObat` = '" + JTDiskonObat.getInt() + "', `PoinTindakan` = '" + JTPoinTindakan.getInt() + "', `PoinObat` = '" + JTPoinObat.getInt() + "', `MetodePembayaran`='" + JCMetodePembayaran.getSelectedItem() + "' WHERE `IdBilling` = '" + Parameter + "'", null);
                    if (Berhasil) {
                        if (tindakanCount != 0) {
                            Berhasil = multiInsert.Excute("DELETE FROM `tbbillingtindakan` WHERE `NoBilling` = '" + JTNoBilling.getText() + "'", null);
                        }
                        if (Berhasil) {
                            if (obatCount != 0) {
                                Berhasil = multiInsert.Excute("DELETE FROM `tbbillingobat` WHERE `NoBilling` = '" + JTNoBilling.getText() + "'", null);
                            }
                            if (Berhasil) {
                                for (int i = 0; i < JTableTindakan.getRowCount(); i++) {
                                    Berhasil = multiInsert.Excute("INSERT INTO `tbbillingtindakan`(`NoBilling`, `IdTindakan`, `Jumlah`, `Harga`, `DiskonTindakan`) VALUES ('" + JTNoBilling.getText() + "',(SELECT `IdTindakan` FROM `tbmtindakan` WHERE `NamaTindakan` = '" + JTableTindakan.getValueAt(i, 0) + "'),'" + JTableTindakan.getValueAt(i, 1).toString().replace(".", "") + "','" + JTableTindakan.getValueAt(i, 2).toString().replace(".", "") + "','" + (JTableTindakan.getValueAt(i, 3).equals("") ? "0" : JTableTindakan.getValueAt(i, 3).toString().replace(".", "").replace(" %", "")) + "')", null);
                                }
                                if (Berhasil) {
                                    for (int j = 0; j < JTableObat.getRowCount(); j++) {
                                        Berhasil = multiInsert.Excute("INSERT INTO `tbbillingobat`(`NoBilling`, `IdObat`, `Jumlah`, `Harga`) VALUES ('" + JTNoBilling.getText() + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTableObat.getValueAt(j, 0) + "'),'" + JTableObat.getValueAt(j, 1).toString().replace(".", "") + "','" + JTableObat.getValueAt(j, 2).toString().replace(".", "") + "')", null);
                                    }
                                }
                            }
                        }
                    }
                }
                if (Berhasil == false) {
                    multiInsert.rollback();
//                    multiInsert.closecon();
                    JOptionPaneF.showMessageDialog(this, "Gagal Ubah Data Billing");
                }
                if (Berhasil == true) {
                    JOptionPaneF.showMessageDialog(this, "Berhasil Ubah Data Billing");
                    multiInsert.Commit();
//                    multiInsert.closecon();
                    if (print) {
                        printing();
                    }
                    dispose();
                    if (listBilling != null) {
                        listBilling.load();
                    }
                }
            }
        }
    }

    void printing() {
        PrintResep pr = new PrintResep();
        total_item_count = JTableTindakan.getRowCount() + JTableObat.getRowCount();
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

    PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight = total_item_count * 13;
//        double headerHeight = 5.4694448;
        double headerHeight = 10.4694448;
        double footerHeight = 2.575932;
//        if (JCBPakaiPoin.isSelected()) {
//            footerHeight = 3.705;
//        }

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

    static JTable itemsTableTindakan, itemsTableObat;
    int total_item_count = 0;
    String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss a";
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
                    g2d.drawString("Jl. Kompol Zainal Abidin No.05-06 (0741-7553068)", x + ((w - g2d.getFontMetrics().stringWidth("Jl. Kompol Zainal Abidin No.05-06 (0741-7553068)")) / 2), y += 45);
                    g2d.drawString("Tanjung Pinang - Jambi", x + ((w - g2d.getFontMetrics().stringWidth("Tanjung Pinang - Jambi")) / 2), y += 10);
                    g2d.drawLine(x, y += 10, 180, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    int x = 5;
                    int y = 65;
                    int w = 188;
                    font = new Font("Times New Roman", Font.PLAIN, 10);
                    g2d.setFont(font);

                    int x1 = g2d.getFontMetrics().stringWidth("Beautician") + 10;
                    int x2 = g2d.getFontMetrics().stringWidth("Beautician") + 20;

                    g2d.drawString("Pasien", x, y += 10);
                    g2d.drawString(" : ", x1, y);
                    g2d.drawString(JTNamaPasien.getText(), x2, y);

                    g2d.drawString("Tanggal", x, y += 10);
                    g2d.drawString(" : ", x1, y);
                    g2d.drawString(now(), x2, y);

                    g2d.drawString("No. Nota", x, y += 10);
                    g2d.drawString(" : ", x1, y);
                    NumberFormat nf = new DecimalFormat("000");
                    g2d.drawString(JTNoBilling.getText(), x2, y);

                    g2d.drawString("Cashier", x, y += 10);
                    g2d.drawString(" : ", x1, y);
                    g2d.drawString(GlobalVar.VarL.username, x2, y);

                    g2d.drawString("Dokter", x, y += 10);
                    g2d.drawString(" : ", x1, y);
                    g2d.drawString("" + (JCNamaDokter.getSelectedIndex() == 0 ? "-" : JCNamaDokter.getSelectedItem()), x2, y);

                    g2d.drawString("Beautician", x, y += 10);
                    g2d.drawString(" : ", x1, y);
                    g2d.drawString("" + (JCNamaBeautician.getSelectedIndex() == 0 ? "-" : JCNamaBeautician.getSelectedItem()), x2, y);

                    g2d.drawLine(x, y += 5, 180, y);
                    if (JTableTindakan.getRowCount() != 0) {
                        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 9));
                        g2d.drawString("Tindakan :", x, y += 15);
                        g2d.drawString("Jmlh", 85, y);
                        g2d.drawString("Harga", 110, y);
                        g2d.drawString("Total", 155, y);
//                        g2d.drawLine(x, y += 5, 180, y);
                        for (int i = 0; i < JTableTindakan.getRowCount(); i++) {
                            y += 10;
                            g2d.drawString(JTableTindakan.getValueAt(i, 0).toString(), x + 10, y);
                            g2d.drawString(JTableTindakan.getValueAt(i, 1).toString(), 82 + (13 - g2d.getFontMetrics().stringWidth(JTableTindakan.getValueAt(i, 1).toString())), y);
                            g2d.drawString(JTableTindakan.getValueAt(i, 2).toString(), 100 + (36 - g2d.getFontMetrics().stringWidth(JTableTindakan.getValueAt(i, 2).toString())), y);
                            Integer subTotalTindakan = Integer.valueOf(JTableTindakan.getValueAt(i, 1).toString().replace(".", "")) * Integer.valueOf(JTableTindakan.getValueAt(i, 2).toString().replace(".", ""));
                            g2d.drawString(Intformatdigit(subTotalTindakan), 134 + (46 - g2d.getFontMetrics().stringWidth(Intformatdigit(subTotalTindakan))), y);
                        }
                        g2d.drawLine(60, y += 5, 180, y);
                        g2d.drawString("Total", 60, y += 10);
                        g2d.drawString(": Rp.", 120, y);
                        g2d.drawString(JTTotalTindakan.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTTotalTindakan.getText())), y);
                        if (getTotalDiskonTindakan() != 0) {
                            g2d.drawString("Diskon", 60, y += 10);
                            g2d.drawString(": Rp.", 120, y);
                            g2d.drawString(Intformatdigit(getTotalDiskonTindakan()), 134 + (46 - g2d.getFontMetrics().stringWidth(Intformatdigit(getTotalDiskonTindakan()))), y);
                        }
                        if (JTPoinTindakan.getInt() != 0) {
                            g2d.drawString("Pakai Poin", 60, y += 10);
                            g2d.drawString(": Rp.", 120, y);
                            g2d.drawString(Intformatdigit(JTPoinTindakan.getInt() * 1000), 134 + (46 - g2d.getFontMetrics().stringWidth(Intformatdigit(JTPoinTindakan.getInt() * 1000))), y);
                        }
                        if (getTotalDiskonTindakan() != 0 || JTPoinTindakan.getInt() != 0) {
                            g2d.drawString("Setelah Potong", 60, y += 10);
                            g2d.drawString(": Rp.", 120, y);
                            g2d.drawString(JTSetelahPotongTindakan.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTSetelahPotongTindakan.getText())), y);
                        }
                    }
                    if (JTableObat.getRowCount() != 0) {
                        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 9));
                        g2d.drawString("Cream & Obat :", x, y += 25);
                        g2d.drawString("Jmlh", 85, y);
                        g2d.drawString("Harga", 110, y);
                        g2d.drawString("Total", 155, y);
                        for (int i = 0; i < JTableObat.getRowCount(); i++) {
                            y += 10;
                            g2d.drawString(JTableObat.getValueAt(i, 0).toString(), x + 10, y);
                            g2d.drawString(JTableObat.getValueAt(i, 1).toString(), 82 + (13 - g2d.getFontMetrics().stringWidth(JTableObat.getValueAt(i, 1).toString())), y);
                            g2d.drawString(JTableObat.getValueAt(i, 2).toString(), 100 + (36 - g2d.getFontMetrics().stringWidth(JTableObat.getValueAt(i, 2).toString())), y);
                            Integer subTotalObat = Integer.valueOf(JTableObat.getValueAt(i, 1).toString().replace(".", "")) * Integer.valueOf(JTableObat.getValueAt(i, 2).toString().replace(".", ""));
                            g2d.drawString(Intformatdigit(subTotalObat), 134 + (46 - g2d.getFontMetrics().stringWidth(JTableObat.getValueAt(i, 3).toString())), y);
                        }
                        g2d.drawLine(60, y += 5, 180, y);
                        g2d.drawString("Total", 60, y += 10);
                        g2d.drawString(": Rp.", 120, y);
                        g2d.drawString(JTTotalObat.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTTotalObat.getText())), y);
                        if (JTDiskonObat.getInt() != 0) {
                            g2d.drawString("Diskon", 60, y += 10);
                            g2d.drawString(": Rp.", 120, y);
                            if (JTDiskonObat.getInt() <= 100) {
                                g2d.drawString(JTDiskonObat.getInt() <= 100 ? Intformatdigit(Math.round(JTDiskonObat.getInt() * JTTotalObat.getInt() / 100)) : JTDiskonObat.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(Intformatdigit(Math.round(JTDiskonObat.getInt() * JTTotalObat.getInt() / 100)))), y);
                            } else {
                                g2d.drawString(JTDiskonObat.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTDiskonObat.getText())), y);
                            }
                        }
                        if (JTPoinObat.getInt() != 0) {
                            g2d.drawString("Pakai Poin", 60, y += 10);
                            g2d.drawString(": Rp.", 120, y);
                            g2d.drawString(Intformatdigit(JTPoinObat.getInt() * 1000), 134 + (46 - g2d.getFontMetrics().stringWidth(Intformatdigit(JTPoinObat.getInt() * 1000))), y);
                        }
                        if (JTDiskonObat.getInt() != 0 || JTPoinObat.getInt() != 0) {
                            g2d.drawString("Setelah Potong", 60, y += 10);
                            g2d.drawString(": Rp.", 120, y);
                            g2d.drawString(JTSetelahPotongObat.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTSetelahPotongObat.getText())), y);
                        }
                    }
                    g2d.drawLine(0, y += 15, 180, y);
                    g2d.drawString("Total", 60, y += 10);
                    g2d.drawString(": Rp.", 120, y);
//                    g2d.drawString(":", g2d.getFontMetrics().stringWidth("Total") + 5, y);
                    g2d.drawString(JTGrandTotal.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTGrandTotal.getText())), y);
//                    g2d.drawString(JTGrandTotal.getText(), g2d.getFontMetrics().stringWidth("Total") + 10, y);

                    g2d.drawString("Bayar", 60, y += 10);
                    g2d.drawString(": Rp.", 120, y);
                    g2d.drawString(JTBayar.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTBayar.getText())), y);

                    g2d.drawString("Kembali", 60, y += 10);
                    g2d.drawString(": Rp.", 120, y);
                    g2d.drawString(JTKembali.getText(), 134 + (46 - g2d.getFontMetrics().stringWidth(JTKembali.getText())), y);

                    g2d.drawString("Metode Bayar", 60, y += 10);
                    g2d.drawString(":", 120, y);
                    g2d.drawString(JCMetodePembayaran.getSelectedItem().toString(), 134 + (46 - g2d.getFontMetrics().stringWidth(JCMetodePembayaran.getSelectedItem().toString())), y);

                    font = new Font("Arial", Font.BOLD, 10);
                    g2d.setFont(font);
                    String text = "Terima Kasih Atas Kunjungan Anda";
                    g2d.drawString(text, (w - g2d.getFontMetrics().stringWidth(text)) / 2, y += 30);

                } catch (Exception r) {
                    r.printStackTrace();
                }
                result = PAGE_EXISTS;
            }
            return result;
        }
    }

}

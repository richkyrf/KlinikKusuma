/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GlobalVar;

import File.*;
import Laporan.*;
import Master.*;
import List.*;
import Proses.*;
import static java.awt.Frame.NORMAL;
import javax.swing.JFrame;

/**
 *
 * @author richky
 */
public class Var {

    public static Login login;
    public static TambahUser tambahUser;
    public static ResetPasswordUser resetPasswordUser;
    public static GantiPassword gantiPassword;
    public static MenuUtama menuUtama;

    public static MasterBarang tambahMasterBarang, ubahMasterBarang;
    public static MasterDokter tambahMasterDokter, ubahMasterDokter;
    public static MasterPasien tambahMasterPasien, ubahMasterPasien;
    public static MasterPemasok tambahMasterPemasok, ubahMasterPemasok;
    public static MasterBeautician tambahMasterBeautician, ubahMasterBeautician;
    public static MasterTindakan tambahMasterTindakan, ubahMasterTindakan;

    public static List listMasterBarang, listMasterDokter, listMasterPasien, listPenjualan, listMasterBeautician, listMasterTindakan, listAntrian, listMasterPemasok, listBarangMasuk, listPenyesuaianStok, listPerawatan, listAntrianBilling, listBilling, listPermintaanStok, listPenyesuaianStokGudangBesar;
    public static ListPoin cekPoin;
    public static ListRekap listRekap;

    public static Jcari jcari;
    public static BarangMasuk tambahBarangMasuk, ubahBarangMasuk;
    public static Perawatan tambahPerawatan, ubahPerawatan;
    public static PenyesuaianStok tambahPenyesuaianStok, tambahPenyesuaianStokGudangBesar;
    public static Billing tambahBilling, ubahBilling;
    public static PermintaanStok tambahPermintaanStok, ubahPermintaanStok;
    
    public static LaporanStok laporanStokGudangKecil, laporanStokGudangBesar;
    public static Laporan laporanTindakan, laporanFacial, laporanCream;
    public static LaporanGudang laporanGudangKecil, laporanGudangBesar;
}
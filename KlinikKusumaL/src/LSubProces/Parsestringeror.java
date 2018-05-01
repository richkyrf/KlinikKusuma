package LSubProces;

import java.sql.SQLException;

public class Parsestringeror {

    public static String GetErorString(SQLException e) {
        int eror1 = e.getErrorCode();
        e.printStackTrace();
        String erorm = e.getMessage();
        String errormessage;
        if (eror1 == 1062) {
            errormessage = " proses gagal karena data yang di inputkan telah ada ( tidak boleh sama )";
        } else if (eror1 == 1406) {
            errormessage = "Gagal Tambah data Karena data yang di input Terlalu Panjang !";
        } else if (eror1 == 1049) {
            errormessage = "Database Tidak Di temukan !";
        } else if (eror1 == 1045) {
            errormessage = "username pass database salah !";
        } else if (eror1 == 1451) {
            errormessage = "gagal hapus/ubah data ini karena telah terpakai";
        } else if (eror1 == 1452) {
            errormessage = "data yang di masukan salah atau belum ada di master";
        } else if (eror1 == 1064) {
            errormessage = "Eror Pada Program";
        } else if (eror1 == 1366) {
            errormessage = "Input Ada yang salah Coba Cek Lagi";
        } else if (eror1 == 1292) {
            errormessage = "Input Ada yang salah Coba Cek Lagi";
        } else if (eror1 == 1048) {
            errormessage = "Data Tidak Boleh Kosong";
        } else if (eror1 == 1049) {
            errormessage = "Database Tidak Ditemukan !!!";
        } else if (eror1 == 1045) {
            errormessage = "PROGRAM GAGAL LOGIN DATABASE!!!";
        } else if (eror1 == 0) {
            errormessage = "PROGRAM EROR " + erorm;
        } else {
            errormessage = "eror tidak diketahui !!" + erorm;
        }
        errormessage = "Gagal E :" + Integer.toString(eror1) + " " + errormessage;
        return errormessage;
    }

    private Parsestringeror() {
    }
}

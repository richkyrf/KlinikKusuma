package LSubProces;

import Eror.LogEror;
import FunctionGUI.JOptionPaneF;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {

    private Connection Con = null;
//    String url = "jdbc:mysql://118.97.150.178:33333/";
//    String user = "databasedo";
//    String pass = "Win32&serVer";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String pass = "";
    String db = "dbkusuma";

    public String GetUrl() {
        return url;
    }

    public String GetDb() {
        return db;
    }

    public String GetUser() {
        return user;
    }

    public String GetPass() {
        return pass;
    }

    public Connection getConnection() {
        if (GlobalVar.VarL.koneksi == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Con = DriverManager.getConnection(url + db + "?autoReconnect=true&useSSL=false", user, pass);
            } catch (ClassNotFoundException | SQLException ex) {
                LogEror.SaveEror(ex);
                JOptionPaneF.showMessageDialog(null, "Koneksi Ke Server Database Gagal !!!");
            }
            GlobalVar.VarL.koneksi = Con;
            return Con;
        } else {
            return GlobalVar.VarL.koneksi;
        }
    }
}

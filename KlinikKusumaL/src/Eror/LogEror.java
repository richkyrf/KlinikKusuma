package Eror;

import Fnc.DefaultVariable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class LogEror {

    public static void SaveEror(Exception e) {
        try {
            String x = new LogEror().geturljar();
            String LocationLogFile = x + DefaultVariable.ErorLogName;
            //System.out.println("LocationLogFile " + LocationLogFile);
            FileWriter fw = new FileWriter(LocationLogFile, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.print("\r\n");
            pw.print("==============================================================");
            pw.print("\r\n");
            e.printStackTrace(pw);
            pw.print("\r\n");
            pw.print("==============================================================");
            pw.print("\r\n");
            pw.close();
        } catch (IOException ee) {
            //System.out.println("Eror.LogEror.SaveEror()" + ee);
        }
    }

    public static void SaveString(String e) {
        try {
            //System.out.println("Eror.LogEror.SaveEror()" + e);
            String x = new LogEror().geturljar();
            String LocationLogFile = x + DefaultVariable.ErorLogName;
            //System.out.println("LocationLogFile " + LocationLogFile);
            FileWriter fw = new FileWriter(LocationLogFile, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.print("\r\n");
            pw.print("==============================================================");
            pw.print("\r\n");
            pw.print("STRING = " + e);
            pw.print("\r\n");
            pw.print("==============================================================");
            pw.print("\r\n");
            pw.close();
        } catch (IOException ee) {
            //System.out.println("Eror.LogEror.SaveEror()" + ee);
        }
    }

    public String geturljar() {
        String jarDir = null;
        try {
            CodeSource codeSource = LogEror.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
//            jarDir = jarFile.getParentFile().getPath() + "\\";
        } catch (URISyntaxException e) {
            //System.out.println("Eror.LogEror.geturljar()" + e);
        }
        return jarDir;
    }
}

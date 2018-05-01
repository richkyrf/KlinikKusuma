package KomponenGUI;

import com.toedter.calendar.JDateChooser;

public class JdateCF extends JDateChooser {

    public JdateCF() {
        this.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.setDateFormatString("dd/MM/yyyy");
        this.setSize(140, 22);
    }
}

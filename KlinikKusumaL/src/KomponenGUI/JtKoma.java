package KomponenGUI;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JtKoma extends JTextField {

    public JtKoma() {
        setHorizontalAlignment(4);
        setFont(new java.awt.Font("Tahoma", 0, 18));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                selectAll();
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != '\b') && (c != '') || (getText().length() >= 2 && !getText().equals("00"))) {
                    getToolkit().beep();
                    e.consume();
                }
                if(getText().isEmpty()){
                    setText("00");
                    selectAll();
                }
            }

        });

    }

    public void setint(int s) {
        if (s <= 9) {
            setText("0" + Integer.toString(s));
        } else {
            setText(Integer.toString(s));
        }
    }

    public int getint() {
        String s = getText();
        if (s.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }

    public String GetString() {
        String s = getText();
        if (s.isEmpty()) {
            return s = "0";
        } else if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }
}

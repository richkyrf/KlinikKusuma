package KomponenGUI;

import Eror.LogEror;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public final class JRibuanTextField extends JTextField {

    private static final long serialVersionUID = 1L;
    private char separator = '.';
    private int maxlimit = 2147483647;
    private int minlimit = -2147483647;

    public JRibuanTextField() {
        setFont(new java.awt.Font("Tahoma", 0, 18));
        setHorizontalAlignment(4);
        setDisabledTextColor(Color.BLUE);
        setText("0");
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                JRibuanTextField.this.setText(JRibuanTextField.this.getText());
                JRibuanTextField.this.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                JRibuanTextField.this.setText(JRibuanTextField.this.getText());
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != '\b') && (c != '')) {
                    JRibuanTextField.this.getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void setSeparator(char s) {
        this.separator = s;
    }

    public void setmaxnum(int max) {
        this.maxlimit = max;
    }

    public void setmin(int min) {
        this.minlimit = min;
    }

    @Override
    public void setText(String s) {
        super.setText(formatNumber(s));
    }

    public void setInt(int s) {
        super.setText(formatNumber(Integer.toString(s)));
    }

    @Override
    public String getText() {
        String s = super.getText();
        return s;
    }

    public Integer getInt() {
        String s = super.getText();
        if (s.isEmpty()) {
            s = "0";
        }
        return Integer.parseInt(s.replace(",", "").replace(".", ""));
    }

    public String getNumberFormattedText() {
        return super.getText();
    }

    public String formatNumber(String s) {
        try {
            int v = Integer.parseInt(s.replace(".", ""));
            if (v >= this.maxlimit) {
                v = this.maxlimit;
            }
            if (v <= this.minlimit) {
                v = this.minlimit;
            }
            return String.format("%,d", new Object[]{v}).replace(",", this.separator + "");
        } catch (NumberFormatException e) {
            LogEror.SaveEror(e);
        }
        return "0";
    }
}

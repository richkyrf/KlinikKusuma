package KomponenGUI;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JNumberOnly extends JTextField {

    int MaxText = 20;

    public JNumberOnly() {
        setText("0");
        setFont(new java.awt.Font("Tahoma", 0, 18));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                JNumberOnly.this.setText(JNumberOnly.this.getText());
                JNumberOnly.this.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                JNumberOnly.this.setText(JNumberOnly.this.getText());
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (JNumberOnly.this.getText().length() >= MaxText) {
                    JNumberOnly.this.getToolkit().beep();
                    e.consume();
                } else if ((c < '0') || (c > '9') || (c == '\b') || (c == '')) {
                    JNumberOnly.this.getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void setmaxtText(int J) {
        MaxText = J;
    }

    @Override
    public void setText(String s) {
        if ("".equals(getText())) {
            super.setText("0");
        } else {
            super.setText(s);
        }
    }

    public Integer getNum() {
        return Integer.parseInt(this.getText());
    }
}

package KomponenGUI;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JtextFnoSelect extends JTextField {

    static int MaxText = 250;

    public JtextFnoSelect() {
        setText("");
        setFont(new java.awt.Font("Tahoma", 0, 18));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                JtextFnoSelect.this.setText(JtextFnoSelect.this.getText());
            }

            @Override
            public void focusLost(FocusEvent e) {
                JtextFnoSelect.this.setText(JtextFnoSelect.this.getText());
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (JtextFnoSelect.this.getText().length() >= MaxText) {
                    JtextFnoSelect.this.getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public void setMaxText(int length) {
        MaxText = length;
    }
}

package KomponenGUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;

public class JTextAreaF extends JTextArea {

    public JTextAreaF() {
        setFont(new java.awt.Font("Tahoma", 0, 18));
        setLineWrap(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    evt.consume();
                }
            }
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KomponenGUI;

/**
 *
 * @author richk
 */
import Eror.LogEror;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class JPlaceHolder extends JTextField {

    private String placeholder;
    private char separator = '.';
    private int maxlimit = 2147483647;
    private int minlimit = -2147483647;

    public JPlaceHolder() {
        setFont(new java.awt.Font("Tahoma", 0, 18));
        setDisabledTextColor(Color.BLUE);
        setPlaceholder("");
        setText("");
        setHorizontalAlignment(4);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                JPlaceHolder.this.setInt(JPlaceHolder.this.getNumberFormattedText());
                JPlaceHolder.this.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                JPlaceHolder.this.setInt(JPlaceHolder.this.getNumberFormattedText());
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != '\b') && (c != '')) {
                    JPlaceHolder.this.getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                JPlaceHolder.this.setInt(JPlaceHolder.this.getNumberFormattedText());
            }
        });
    }

    public JPlaceHolder(
            final Document pDoc,
            final String pText,
            final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public JPlaceHolder(final int pColumns) {
        super(pColumns);
    }

    public JPlaceHolder(final String pText) {
        super(pText);
    }

    public JPlaceHolder(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.isEnabled()) {
            g.setColor(new Color(0, 200, 0));
        } else {
            g.setColor(Color.BLUE);
        }
        g.drawString(placeholder, getWidth() - pG.getFontMetrics().stringWidth(placeholder) - 3, pG.getFontMetrics().getMaxAscent() + 2);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

    public void setInt(String s) {
        super.setText(formatNumber(s));
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
        return super.getText().replace(".", "");
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
        return "";
    }

}

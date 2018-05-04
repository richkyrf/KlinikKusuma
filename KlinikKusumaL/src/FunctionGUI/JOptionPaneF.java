/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.UIManager;

/**
 *
 * @author richk
 */
public class JOptionPaneF {

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 18));
        if (message.toString().toLowerCase().contains("gagal")) {
            UIManager.put("Panel.background", new Color(255, 200, 200));
        } else {
            UIManager.put("Panel.background", new Color(200, 255, 200));
        }
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
        UIManager.put("Panel.background", new Color(240, 240, 240));
    }

    public static void showMessageDialog(Component parentComponent, Object message) {
        UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 18));
        if (message.toString().toLowerCase().contains("gagal")) {
            UIManager.put("Panel.background", new Color(255, 200, 200));
        } else {
            UIManager.put("Panel.background", new Color(200, 255, 200));
        }
        JOptionPane.showMessageDialog(parentComponent, message);
        UIManager.put("Panel.background", new Color(240, 240, 240));
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
        UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 18));
        UIManager.put("Panel.background", new Color(255, 225, 200));
        int a = showConfirmDialog(parentComponent, message, title, optionType);
        UIManager.put("Panel.background", new Color(240, 240, 240));
        return a;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import static File.EncMD5.getMD5;
import LSubProces.DRunSelctOne;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.BindException;
import static java.net.InetAddress.getLocalHost;
import java.net.ServerSocket;
import java.util.ArrayList;
import FunctionGUI.JOptionPaneF;
import static javax.swing.UIManager.setLookAndFeel;
import static GlobalVar.Var.*;
import KomponenGUI.FDateF;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Martono
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Silahkan Login");
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLUsername = new KomponenGUI.JlableF();
        JLUsername2 = new KomponenGUI.JlableF();
        JTUsername = new KomponenGUI.JtextF();
        JLPassword = new KomponenGUI.JlableF();
        JLPassword2 = new KomponenGUI.JlableF();
        JTPassword = new KomponenGUI.JpasswordT();
        JBLogin = new KomponenGUI.JbuttonF();
        JBExit = new KomponenGUI.JbuttonF();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        JLUsername.setText("Username");

        JLUsername2.setText(":");

        JTUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTUsernameKeyReleased(evt);
            }
        });
        JTUsername.setMaxText(25);

        JLPassword.setText("Password");

        JLPassword2.setText(":");

        JTPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTPasswordKeyReleased(evt);
            }
        });

        JBLogin.setText("LOGIN");
        JBLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBLoginActionPerformed(evt);
            }
        });

        JBExit.setText("EXIT");
        JBExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(JLUsername2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(JLPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JBLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBExit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 29, Short.MAX_VALUE))
                            .addComponent(JTPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLUsername2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JBExitActionPerformed

    private void JBLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBLoginActionPerformed
        login();
    }//GEN-LAST:event_JBLoginActionPerformed

    private void JTUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTUsernameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTPassword.requestFocus();
        }
    }//GEN-LAST:event_JTUsernameKeyReleased

    private void JTPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPasswordKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_JTPasswordKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        login = null;
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        try {
            ServerSocket serverSocket = new ServerSocket(65535, 1, getLocalHost());
            new Login();
        } catch (BindException ex) {
            JOptionPaneF.showMessageDialog(null, "Gagal. Aplikasi Sudah Terbuka !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            JOptionPaneF.showMessageDialog(null, "Gagal. Aplikasi Sudah Terbuka !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    void login() {
        DRunSelctOne dRunSelctOne = new LSubProces.DRunSelctOne();
        dRunSelctOne.seterorm("Koneksi Ke Server Database Gagal !!!");
        dRunSelctOne.setQuerynolimit("SELECT `Username`, `Password`, `Level` FROM `tbuser` WHERE `Username`='" + JTUsername.getText() + "' AND `Password`='" + getMD5(new String(JTPassword.getPassword())) + "'");
        ArrayList<String> list = dRunSelctOne.excute();
        try {
            GlobalVar.VarL.username = list.get(0);
            GlobalVar.VarL.password = list.get(1);
            GlobalVar.VarL.level = list.get(2);
            if (list.get(0) != null) {
                new MenuUtama();
                dispose();
            } else {
                JOptionPaneF.showMessageDialog(this, "Gagal Login. Silahkan Ulangi . . .", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Eror.LogEror.SaveEror(e);
            JOptionPaneF.showMessageDialog(this, "Gagal Login. Silahkan Ulangi . . .", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private KomponenGUI.JbuttonF JBExit;
    private KomponenGUI.JbuttonF JBLogin;
    private KomponenGUI.JlableF JLPassword;
    private KomponenGUI.JlableF JLPassword2;
    private KomponenGUI.JlableF JLUsername;
    private KomponenGUI.JlableF JLUsername2;
    private KomponenGUI.JpasswordT JTPassword;
    private KomponenGUI.JtextF JTUsername;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raisemeup.view;

import java.util.regex.Pattern;
import raisemeup.control.RaiseMeUp;

/**
 *
 * @author lekogabor
 */
public class UserSettings extends javax.swing.JFrame {

    /**
     * Creates new form UserSettings
     */
    public UserSettings() {
        initComponents();
        myInit();
    }
    
    private void myInit(){
        txtUsername.setText(RaiseMeUp.getCurrentUser().getUsername());
        pwfRegister.setText(RaiseMeUp.getCurrentUser().getPassword());
        pwfRegister2.setText(RaiseMeUp.getCurrentUser().getPassword());
        txtEmail.setText(RaiseMeUp.getCurrentUser().getEmail());
    }
    
    private static final Pattern rfc2822 = Pattern.compile(
        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
    );
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pwfRegister = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        pwfRegister2 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        butConfirm1 = new javax.swing.JButton();
        butBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel1.setText("Username:");

        txtUsername.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N
        txtUsername.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel2.setText("Password:");

        pwfRegister.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel3.setText("Password again:");

        pwfRegister2.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Hobo Std", 0, 18)); // NOI18N
        jLabel4.setText("E-mail:");

        txtEmail.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Hobo Std", 0, 24)); // NOI18N
        jLabel5.setText("User Settings");

        butConfirm1.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N
        butConfirm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ConfirmButton.png"))); // NOI18N
        butConfirm1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butConfirm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butConfirm1ActionPerformed(evt);
            }
        });

        butBack.setFont(new java.awt.Font("Hobo Std", 0, 14)); // NOI18N
        butBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackButton.png"))); // NOI18N
        butBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butBack, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butConfirm1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pwfRegister, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pwfRegister2)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pwfRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pwfRegister2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butBack, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butConfirm1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butConfirm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butConfirm1ActionPerformed
        if(!new String(pwfRegister.getPassword()).equals(new String(pwfRegister2.getPassword())))
        {
            RaiseMeUp.setErrorMessage(new ErrorMessage("The two passwords aren't matching!"));
            RaiseMeUp.getErrorMessage().setVisible(true);
        }
        else if("".equals(txtUsername.getText()) || "".equals(txtEmail.getText()) || "".equals(new String(pwfRegister.getPassword()))) {
            RaiseMeUp.setErrorMessage(new ErrorMessage("You didn't fill all the fields!"));
            RaiseMeUp.getErrorMessage().setVisible(true);
        }
        else if(!rfc2822.matcher(txtEmail.getText()).matches()) {
            RaiseMeUp.setErrorMessage(new ErrorMessage("This is not a valid e-mail adress!"));
            RaiseMeUp.getErrorMessage().setVisible(true);
        }
        else {
            RaiseMeUp.getCurrentUser().setEmail(txtEmail.getText());
            RaiseMeUp.getCurrentUser().setPassword(new String(pwfRegister.getPassword()));
            RaiseMeUp.updateUser(RaiseMeUp.getCurrentUser());
            myInit();
        }
    }//GEN-LAST:event_butConfirm1ActionPerformed

    private void butBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBackActionPerformed
        RaiseMeUp.setPetChooser(new PetChooser());
        this.setVisible(false);
        RaiseMeUp.getPetChooser().setVisible(true);
    }//GEN-LAST:event_butBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserSettings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butBack;
    private javax.swing.JButton butConfirm1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField pwfRegister;
    private javax.swing.JPasswordField pwfRegister2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

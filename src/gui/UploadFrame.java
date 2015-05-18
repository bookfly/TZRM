/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author tabasj
 */
public class UploadFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public UploadFrame() {
        initComponents();
        initiateScreen();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTxtFile = new javax.swing.JTextField();
        jBBrowse = new javax.swing.JButton();
        jBUpload = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("File");

        jTxtFile.setToolTipText("");
        jTxtFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFileActionPerformed(evt);
            }
        });

        jBBrowse.setText("Browse");
        jBBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBrowseActionPerformed(evt);
            }
        });

        jBUpload.setText("Upload");
        jBUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBUploadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTxtFile, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBUpload)
                .addGap(18, 18, 18)
                .addComponent(jBBrowse)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBBrowse)
                    .addComponent(jBUpload))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFileActionPerformed

    private void jBBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBrowseActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            jTxtFile.setText(selectedFile.getAbsolutePath());
            setVisibility(true);
        }
    }//GEN-LAST:event_jBBrowseActionPerformed

    private void jBUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBUploadActionPerformed

        if (!jTxtFile.getText().trim().equals("") && jTxtFile.getText().trim().endsWith(".txt")) {
            File selectedFile = new File(jTxtFile.getText().trim());
            EncriptFrame ef = new EncriptFrame();
            ef.setFile(selectedFile);
            ef.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Please choose file");
        }
    }//GEN-LAST:event_jBUploadActionPerformed

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
            java.util.logging.Logger.getLogger(UploadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBrowse;
    private javax.swing.JButton jBUpload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTxtFile;
    // End of variables declaration//GEN-END:variables

    private void initiateScreen() {
        setVisibility(false);
    }

    private void setVisibility(Boolean visible) {
        jBUpload.setVisible(visible);
    }
}

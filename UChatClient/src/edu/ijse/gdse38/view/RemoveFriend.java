/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse38.view;

/**
 *
 * @author SW96
 */
public class RemoveFriend extends javax.swing.JDialog {

    /**
     * Creates new form RemoveFriend
     */
    public RemoveFriend(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        deleteLabel = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        picLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deleteLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        deleteLabel.setForeground(new java.awt.Color(204, 0, 0));
        deleteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteLabel.setText("Press Del button to remove a friend");
        mainPanel.add(deleteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 316, 200, -1));

        sp.setOpaque(false);

        sp.setViewportView(jList1);

        mainPanel.add(sp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, 300));

        picLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ijse/gdse38/images/regWindow.png"))); // NOI18N
        mainPanel.add(picLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 350));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(RemoveFriend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemoveFriend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemoveFriend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoveFriend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RemoveFriend dialog = new RemoveFriend(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JList jList1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel picLabel;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}

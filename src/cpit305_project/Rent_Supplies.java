package cpit305_project;

import java.net.Socket;
import java.util.ArrayList;

public class Rent_Supplies extends javax.swing.JFrame {

    static private Socket incoming;
    static ArrayList<Supplies> table = new ArrayList<>();
    static ArrayList<Supplies> speaker = new ArrayList<>();
    static ArrayList<Supplies> chair = new ArrayList<>();
    static String name, price, quantity;

    /**
     * Creates new form Rent_Supplies
     */
    public Rent_Supplies() {
        this.setVisible(true);
        initComponents();
    }

    public Rent_Supplies(ArrayList<Supplies> table1, ArrayList<Supplies> chair1, ArrayList<Supplies> speaker1) {
        table = table1;
        speaker = speaker1;
        chair = chair1;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonTableF = new javax.swing.JButton();
        jButtonChairF = new javax.swing.JButton();
        jButtonSpeakerF = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(246, 228, 228));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setLabelFor(jLabel1);
        jLabel1.setText("Rent product");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 720, 140));

        jButtonTableF.setText("Tables");
        jButtonTableF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTableFActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonTableF, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 253, 139, 68));

        jButtonChairF.setText("Chairs");
        jButtonChairF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChairFActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonChairF, new org.netbeans.lib.awtextra.AbsoluteConstraints(711, 253, 145, 68));

        jButtonSpeakerF.setText("Speakers");
        jButtonSpeakerF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSpeakerFActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSpeakerF, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 253, 141, 68));

        jLabel2.setText("Choose a product");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 186, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTableFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTableFActionPerformed
        dispose();
        Rent_table table1 = new Rent_table(table);
        table1.show();
    }//GEN-LAST:event_jButtonTableFActionPerformed

    private void jButtonSpeakerFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSpeakerFActionPerformed
        dispose();
        Rent_Speaker speaker1 = new Rent_Speaker(speaker);
        speaker1.show();
    }//GEN-LAST:event_jButtonSpeakerFActionPerformed

    private void jButtonChairFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChairFActionPerformed
        dispose();
        Rent_Chair chair1 = new Rent_Chair(chair);
        chair1.show();
    }//GEN-LAST:event_jButtonChairFActionPerformed

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
            java.util.logging.Logger.getLogger(Rent_Supplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rent_Supplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rent_Supplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rent_Supplies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonChairF;
    private javax.swing.JButton jButtonSpeakerF;
    private javax.swing.JButton jButtonTableF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

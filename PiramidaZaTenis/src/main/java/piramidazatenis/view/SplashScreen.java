/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package piramidazatenis.view;

import javax.swing.JOptionPane;
import org.hibernate.Session;
import piramidazatenis.util.HibernateUtil;

/**
 *
 * @author stjep
 */
public class SplashScreen extends javax.swing.JFrame {

    /**
     * Creates new form SplashScreen
     */
    
     private int i = 0;
    private boolean hibernateGotov;
    
    public SplashScreen() {
        initComponents();
        postavke();
    }
    
     private void postavke(){
        i = 0;
        hibernateGotov = false;
        Ucitanje ucitanje = new Ucitanje();
        ucitanje.start();

        TijekUcitanja tijekUcitanja = new TijekUcitanja();
        tijekUcitanja.start();
    }

    private class TijekUcitanja extends Thread {

        @Override
        public void run() {
            if (hibernateGotov) {
                return;
            }
            try {
                pbUcitanje.setValue(++i);
                Thread.sleep(1000);
                run();
            } catch (InterruptedException ex) {
                
            }
        }

    }

   private class Ucitanje extends Thread {

        @Override
        public void run() {
            Session s = HibernateUtil.getSession();
            if (s.getMetamodel().getEntities().size() > 0) {
                hibernateGotov = true;
                for (int t = i; t < 100; t++) {
                    try {
                        pbUcitanje.setValue(++i);
                        Thread.sleep(3);
                    } catch (InterruptedException ex) {
                       
                    }
                }

                new Izbornik().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(getRootPane(), "Problem s povezivanje na bazu");
            }
        }

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
        pbUcitanje = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SplashScreen.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pbUcitanje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbUcitanje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar pbUcitanje;
    // End of variables declaration//GEN-END:variables
}

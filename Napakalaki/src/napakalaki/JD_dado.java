
package napakalaki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author fvelasco
 */
public class JD_dado extends javax.swing.JDialog {
  
  private Random r = new Random();
  private int valorDado;
  private Timer dadoTimer;
  private ActionListener dadoAction = new ActionListener() {
    public void actionPerformed (ActionEvent ev) {
        valorDado = r.nextInt(6)+1;
        jL_dado.setText(Integer.toString(valorDado));
        pack();
      }
    };
  

  public JD_dado(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    dadoTimer = new Timer (50,dadoAction);
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        System.exit(0);
      }
    });
  }
  
  public int getValor (String s1, String s2) {
    jB_OK.setVisible(false);
    jL_texto.setText(s1);
    jL_texto1.setText(s2);
    pack();
    dadoTimer.start();
    this.setVisible(true);
    return valorDado;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jL_dado = new javax.swing.JLabel();
        jL_texto = new javax.swing.JLabel();
        jB_OK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jL_texto1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dado");
        setMinimumSize(new java.awt.Dimension(400, 280));
        setPreferredSize(new java.awt.Dimension(400, 280));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jL_dado.setBackground(new java.awt.Color(255, 255, 255));
        jL_dado.setFont(new java.awt.Font("Trebuchet MS", 2, 48)); // NOI18N
        jL_dado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_dado.setText("1");
        jL_dado.setOpaque(true);
        jL_dado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_dadoMouseClicked(evt);
            }
        });
        getContentPane().add(jL_dado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 80, 80));

        jL_texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_texto.setText("jLabel1");
        getContentPane().add(jL_texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 30));

        jB_OK.setText("OK");
        jB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_OKActionPerformed(evt);
            }
        });
        getContentPane().add(jB_OK, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("pincha sobre el dado para detenerlo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jL_texto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_texto1.setText("jLabel1");
        getContentPane().add(jL_texto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 350, 30));

        setSize(new java.awt.Dimension(400, 284));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  private void jL_dadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_dadoMouseClicked
    dadoTimer.stop();
    jB_OK.setVisible(true);
    pack();
  }//GEN-LAST:event_jL_dadoMouseClicked

  private void jB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_OKActionPerformed
    this.dispose();
  }//GEN-LAST:event_jB_OKActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_OK;
    private javax.swing.JLabel jL_dado;
    private javax.swing.JLabel jL_texto;
    private javax.swing.JLabel jL_texto1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

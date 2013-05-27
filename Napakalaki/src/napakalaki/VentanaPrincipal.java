package napakalaki;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaPrincipal extends javax.swing.JFrame implements Vista{
    private Napakalaki juego;
    private JD_dado dado;
    private String[] nombresJugadores;
    private Monstruo monstruoEnJuego;
    private Jugador jugadorActivo;
    
    private class TesoroGrafico extends JPanel {
        protected Tesoro tesoro; // asociación con el tesoro que representa 
        private JLabel jL_nombre = new JLabel();

        TesoroGrafico (Tesoro unTesoro) {
            tesoro = unTesoro;
            this.setPreferredSize(new java.awt.Dimension(100, 140));
            this.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            jL_nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jL_nombre.setText(tesoro.getNombre());
            this.setBackground (new java.awt.Color(200,200,0));
            this.setOpaque(false);
            this.add(jL_nombre,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 10, 90, -1));
        }
    }
    
    private class TesoroGraficoVisible extends TesoroGrafico {
        TesoroGraficoVisible (Tesoro unTesoro) {
            super(unTesoro);
            addMouseListener(new java.awt.event.MouseAdapter() 
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                 // instrucciones para procesar la selección de un tesoro visible
                }
            });
         }
    }
    
    private class TesoroGraficoOculto extends TesoroGrafico {
        TesoroGraficoOculto (Tesoro unTesoro) {
            super(unTesoro);
            addMouseListener(new java.awt.event.MouseAdapter() 
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                 // instrucciones para procesar la selección de un tesoro visible
                }
            });
         }
    }


    
    
    
    
    public VentanaPrincipal(Napakalaki unJuego) {
        juego = unJuego;
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

        jP_monstruos = new javax.swing.JPanel();
        jL_malRollo = new javax.swing.JLabel();
        jL_nombreMonstruo = new javax.swing.JLabel();
        jL_nivelesGanados = new javax.swing.JLabel();
        jL_tesorosGanados = new javax.swing.JLabel();
        jL_nivel = new javax.swing.JLabel();
        jL_nivelContraSectarios = new javax.swing.JLabel();
        jL_nivelesPerdidos = new javax.swing.JLabel();
        jP_jugadores = new javax.swing.JPanel();
        jL_nombreJugador = new javax.swing.JLabel();
        jP_tesorosVisibles = new javax.swing.JPanel();
        jP_tesorosOcultos = new javax.swing.JPanel();
        jL_esSectario = new javax.swing.JLabel();
        jL_nivelCombate = new javax.swing.JLabel();
        jL_bonusSectario = new javax.swing.JLabel();
        jB_equiparse = new javax.swing.JButton();
        jB_comprarNivel = new javax.swing.JButton();
        jB_descartarseTesoros = new javax.swing.JButton();
        jB_combatir = new javax.swing.JButton();
        jB_siguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jP_monstruos.setBorder(javax.swing.BorderFactory.createTitledBorder("Territorio del Terrible Monstruo"));

        jL_malRollo.setText("Mal Rollo del monstruo");

        jL_nombreMonstruo.setText("Nombre Monstruo");

        jL_nivelesGanados.setText("Niveles Ganados");

        jL_tesorosGanados.setText("Tesoros Ganados");

        jL_nivel.setText("Nivel");

        jL_nivelContraSectarios.setText("Nivel contra Sectarios");

        jL_nivelesPerdidos.setText("Niveles Perdidos");

        javax.swing.GroupLayout jP_monstruosLayout = new javax.swing.GroupLayout(jP_monstruos);
        jP_monstruos.setLayout(jP_monstruosLayout);
        jP_monstruosLayout.setHorizontalGroup(
            jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_monstruosLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_monstruosLayout.createSequentialGroup()
                        .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jL_nombreMonstruo)
                            .addComponent(jL_nivelesGanados)
                            .addComponent(jL_tesorosGanados))
                        .addGap(145, 145, 145)
                        .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jL_nivelesPerdidos)
                            .addComponent(jL_nivel)
                            .addComponent(jL_nivelContraSectarios)))
                    .addGroup(jP_monstruosLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jL_malRollo, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jP_monstruosLayout.setVerticalGroup(
            jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_monstruosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_nombreMonstruo)
                    .addComponent(jL_nivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_nivelesGanados)
                    .addComponent(jL_nivelContraSectarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_tesorosGanados)
                    .addComponent(jL_nivelesPerdidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jL_malRollo)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jP_jugadores.setBorder(javax.swing.BorderFactory.createTitledBorder("Parcelita de los débiles jugadores"));

        jL_nombreJugador.setText("Nombre Jugador");

        jP_tesorosVisibles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jP_tesorosOcultos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jL_esSectario.setText("Humano");

        jL_nivelCombate.setText("Nivel de Combate");

        jL_bonusSectario.setText("Bonus Sectario");

        javax.swing.GroupLayout jP_jugadoresLayout = new javax.swing.GroupLayout(jP_jugadores);
        jP_jugadores.setLayout(jP_jugadoresLayout);
        jP_jugadoresLayout.setHorizontalGroup(
            jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_jugadoresLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jP_tesorosOcultos, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jP_jugadoresLayout.createSequentialGroup()
                            .addComponent(jL_nombreJugador)
                            .addGap(50, 50, 50)
                            .addComponent(jL_nivelCombate)
                            .addGap(98, 98, 98)
                            .addComponent(jL_esSectario))
                        .addComponent(jP_tesorosVisibles, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addComponent(jL_bonusSectario)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jP_jugadoresLayout.setVerticalGroup(
            jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_jugadoresLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_nombreJugador)
                    .addComponent(jL_esSectario)
                    .addComponent(jL_nivelCombate)
                    .addComponent(jL_bonusSectario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jP_tesorosVisibles, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jP_tesorosOcultos, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jB_equiparse.setText("Equiparse");

        jB_comprarNivel.setText("Comprar Nivel");

        jB_descartarseTesoros.setText("Descartarse Tesoros");

        jB_combatir.setText("¡COMBATIR!");

        jB_siguiente.setText("Siguiente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jP_monstruos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jP_jugadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jB_equiparse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jB_comprarNivel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jB_descartarseTesoros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jB_combatir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jB_siguiente)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jP_monstruos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jP_jugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jB_equiparse)
                    .addComponent(jB_comprarNivel)
                    .addComponent(jB_descartarseTesoros)
                    .addComponent(jB_combatir)
                    .addComponent(jB_siguiente))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VentanaPrincipal().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_combatir;
    private javax.swing.JButton jB_comprarNivel;
    private javax.swing.JButton jB_descartarseTesoros;
    private javax.swing.JButton jB_equiparse;
    private javax.swing.JButton jB_siguiente;
    private javax.swing.JLabel jL_bonusSectario;
    private javax.swing.JLabel jL_esSectario;
    private javax.swing.JLabel jL_malRollo;
    private javax.swing.JLabel jL_nivel;
    private javax.swing.JLabel jL_nivelCombate;
    private javax.swing.JLabel jL_nivelContraSectarios;
    private javax.swing.JLabel jL_nivelesGanados;
    private javax.swing.JLabel jL_nivelesPerdidos;
    private javax.swing.JLabel jL_nombreJugador;
    private javax.swing.JLabel jL_nombreMonstruo;
    private javax.swing.JLabel jL_tesorosGanados;
    private javax.swing.JPanel jP_jugadores;
    private javax.swing.JPanel jP_monstruos;
    private javax.swing.JPanel jP_tesorosOcultos;
    private javax.swing.JPanel jP_tesorosVisibles;
    // End of variables declaration//GEN-END:variables

    @Override
    public void  mostrar(String[] args) {
        JD_nombresJugadores dialogoNombres;
        dado = new JD_dado (this,true);
        dialogoNombres = new JD_nombresJugadores (this,true);
        nombresJugadores = dialogoNombres.getNombres();
        // Añadir aquí el código para comunicarse con la clase Napakalaki
        // y comenzar el juego
        juego.comenzarJuego(nombresJugadores);

        this.setVisible(true);
    }

    @Override
    public int getDado(String s1, String s2) {
        return dado.getValor(s1, s2);
    }
    
    private void actualizarMonstruo()
    {
        monstruoEnJuego = juego.obtenerMonstruoActivo();
        jL_nombreMonstruo.setText (monstruoEnJuego.getNombre());
        jL_nivelesGanados.setText("Niveles Ganados: " + monstruoEnJuego.cualEsTuBuenRollo().obtenerNivelesGanados());
        jL_tesorosGanados.setText("Tesoros Ganados: " + monstruoEnJuego.cualEsTuBuenRollo().obtenerTesorosGanados());
        jL_nivel.setText("Nivel: " + monstruoEnJuego.getValorBasico());
        jL_nivelContraSectarios.setText("Nivel contra Sectarios: " + monstruoEnJuego.getValorEspecial());
        jL_nivelesPerdidos.setText("Niveles perdidos: " + monstruoEnJuego.cualEsTuMalRollo().obtenerNivelesPerdidos());
        
        
        
        jL_malRollo.setText (monstruoEnJuego.cualEsTuMalRollo().obtenerTexto()); 
        // Obtener texto
        // etc.
        pack();

    }
    
    private void actualizarJugador()
    {        
    TesoroGrafico unTesoroGrafico; // variable auxiliar para varios usos
    jugadorActivo = juego.obtenerJugadorActivo(); // asociación con el modelo
    
    ////INCLUIR instrucciones para actualizar el nombre, el nivel, u otra
    // información del jugador activo distinta a los tesoros
    jugadorActivo = juego.obtenerJugadorActivo();
    jL_nombreJugador.setText(jugadorActivo.toString());
    jL_nivelCombate.setText("Nivel de combate: " + jugadorActivo.obtenerNivelCombate());
    
    
    if (jugadorActivo instanceof JugadorSectario)
    {
        jL_esSectario.setText("Sectario");
        jL_bonusSectario.setText("+" + ((JugadorSectario)jugadorActivo).getMiCartaSectario().getValorBasico() 
                                  + " por cada sectario en juego.");
    }
        
    else{
        jL_esSectario.setText("Humano");
        jL_bonusSectario.setText("");
    }
    
    
    // Antes de añadir los tesoros visibles de este jugador a su JPanel
    // de tesoros visibles, se eliminan los tesoros que ya tenía este
    // JPanel que pertenen al jugador anterior.
    // Esos tesoros se encuentran en tesorosVisiblesAlimpiar
    // (atributo de tipo List<TesoroGrafico> de la clase
    // VentanaPrincipal
    for (TesoroGrafico tg : tesorosVisiblesAlimpiar)
        jP_tesorosVisibles.remove (tg);
    // Se vacía tesorosAlimpiar para incluirle los del jugador activo
    tesorosVisiblesAlimpiar.clear();

    // Ahora se añaden los tesoros visibles del jugador actual
    for (Tesoro t : jugadorActivo.getTesorosVisibles()) {
        unTesoroGrafico = new TesoroGraficoVisible(t);
        // El tesoro gráfico se añade a su JPanel
        jP_tesorosVisibles.add (unTesoroGrafico);
        // También se incluye en tesorosVisiblesAlimpiar para la
        // actualización del próximo jugador
        tesorosVisiblesAlimpiar.add(unTesoroGrafico);
    }
    
    // El jugador que acaba de recibir el turno no debe tener ningun tesoro
    // visible seleccionado
    tesorosVisiblesSeleccionados.clear();
    
    // PROCEDER de forma similar con los tesoros ocultos
    // INCLUIR otras instrucciones que se estimen necesarias
    //
    // Se han estado añadiendo y quitando componentes del JPanel, no solo
    // modificando algún atributo de un componente existente.
    // No sólo se requiere pack(), sino también repaint()
    repaint();
    pack();



    }
}

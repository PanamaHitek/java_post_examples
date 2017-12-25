package Java.Executor;

import com.panamahitek.PanamaHitek_DataBuffer;
import com.panamahitek.liveinterfaces.PanamaHitek_TimeLineChart;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ejemplo sobre ejecución de instrucciones de manera secuencial
 *
 * @author Antony García González, de Proyecto Panama Hitek. Visita
 * http://panamahitek.com
 */
public class SecuentialExample3 extends javax.swing.JFrame {

    PanamaHitek_DataBuffer buffer;
    PanamaHitek_TimeLineChart chart;
    int red = 0;
    int blue = 0;
    int green = 0;

    public SecuentialExample3() {
        initComponents();
        buildChart(); //Se construye la grafica
        //Se ejecutan las tareas

        task1();
        task2();
        task3();
    }

    public void buildChart() {
        try {
            //Se inicializan los objetos para almacenar datos y graficar
            buffer = new PanamaHitek_DataBuffer();
            chart = new PanamaHitek_TimeLineChart();

            //Se agregan las tareas al buffer de datos
            buffer.addColumn(0, "Task 1", Integer.class);
            buffer.addColumn(1, "Task 2", Integer.class);
            buffer.addColumn(2, "Task 3", Integer.class);

            chart.setDataBuffer(buffer); //Se agrega el buffer a la grafica
            chart.setChartTitle("Valores generados en función del tiempo"); //Titulo de la grafica
            //Titulos de los ejes de la grafica
            chart.setAxisTitle("Tiempo", "Valores generados");
            //Color de fondo
            chart.setBackgroundColor(Color.WHITE);
            //Se eliminan las marcas en las lineas
            chart.setLinesMarksVisible(false);
            //Cantidad de elementos a mostrar en la grafica
            chart.setMaximumItemCount(3000);
            //Se inserta la grafica en el panel
            chart.insertToPanel(jPanel1);
        } catch (Exception ex) {
            Logger.getLogger(SecuentialExample3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void task1() {
        for (int i = 0; i < 1000; i++) {
            try {
                /**
                 * Se agrega un dato al buffer en los indices correspondientes a
                 * cada color
                 */
                buffer.addValue(0, red);
                buffer.addValue(1, blue);
                buffer.addValue(2, green);
                //Se imprime una fila en el buffer
                buffer.printRow();
                //Se detiene la ejecución durante un milisegundo
                Thread.sleep(1);
                //Se aumenta en una unidad el valor del color rojo
                red++;
            } catch (Exception ex) {
                Logger.getLogger(SecuentialExample3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void task2() {
        for (int i = 0; i < 1000; i++) {
            try {
                /**
                 * Se agrega un dato al buffer en los indices correspondientes a
                 * cada color
                 */
                buffer.addValue(0, red);
                buffer.addValue(1, blue);
                buffer.addValue(2, green);
                //Se imprime una fila en el buffer
                buffer.printRow();
                //Se detiene la ejecución durante un milisegundo
                Thread.sleep(1);
                //Se aumenta en una unidad el valor del color rojo
                blue++;
            } catch (Exception ex) {
                Logger.getLogger(SecuentialExample3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void task3() {
        for (int i = 0; i < 1000; i++) {
            try {
                /**
                 * Se agrega un dato al buffer en los indices correspondientes a
                 * cada color
                 */
                buffer.addValue(0, red);
                buffer.addValue(1, blue);
                buffer.addValue(2, green);
                //Se imprime una fila en el buffer
                buffer.printRow();
                //Se detiene la ejecución durante un milisegundo
                Thread.sleep(1);
                //Se aumenta en una unidad el valor del color rojo
                green++;
            } catch (Exception ex) {
                Logger.getLogger(SecuentialExample3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SecuentialExample3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecuentialExample3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecuentialExample3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecuentialExample3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecuentialExample3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

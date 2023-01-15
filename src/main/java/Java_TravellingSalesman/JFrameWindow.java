package Java_TravellingSalesman;

import static Java_TravellingSalesman.Permutations.getPermutations;
import com.google.common.math.BigIntegerMath;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JFrameWindow extends javax.swing.JFrame {

    private List<City> cities = new ArrayList<>();
    private Canvas bruteForceCanvas;
    private boolean progressFlag = false;
    private String processStage = "";
    private int totalPermutations = 0;
    private long startTime = 0;
    private long endTime = 0;
    private String bestRoute = "N/A";

    public JFrameWindow() {
        initComponents();

        /**
         * Here I set the cities, labels and coordinates
         */
        cities.add(new City(50, 300, "A"));
        cities.add(new City(250, 250, "B"));
        cities.add(new City(300, 100, "C"));
        cities.add(new City(600, 220, "D"));
        cities.add(new City(800, 500, "E"));
        cities.add(new City(500, 400, "F"));
        cities.add(new City(850, 100, "G"));
        cities.add(new City(75, 500, "H"));
        cities.add(new City(100, 50, "I"));
        cities.add(new City(300, 550, "J"));
        cities.add(new City(950, 150, "K"));

//Add the labels of the cities to the combo box of the GUI
        cities.forEach(a -> {
            ((DefaultTableModel) jTableCities.getModel()).addRow(new Object[]{a.getLabel(), a.getX(), a.getY()});
            jComboBox1.addItem(a.getLabel());
        });

        //Update GUI
        jLabel2.setText("Cantidad de destinos: " + cities.size() + "  |  Punto de partida: " + jComboBox1.getSelectedItem() + "  |  Posibles rutas: " + BigIntegerMath.factorial(cities.size() - 1) + " | Mejor ruta: N/A  |  Tiempo de procesamiento: N/A");

        /**
         * An object of class Canvas is created to render the diagram of cities
         * and routes
         */
        bruteForceCanvas = new Canvas();
        bruteForceCanvas.setVisible(true);
        bruteForceCanvas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bruteForceCanvas.setBounds(0, 0, jPanel3.getWidth(), jPanel3.getHeight());
        jPanel3.add(bruteForceCanvas);
        bruteForceCanvas.drawCities(cities);

        buildGUI();
        runProgressBar();
    }

    /**
     * This method handles the update of the GUI while the routes are being
     * calculated It uses a ExecutorService to create a parallel process
     */
    public void runProgressBar() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            //Loops forever
            while (true) {
                if (progressFlag) {
                    //GUI elements are updated here
                    if (jTableRoutes.getRowCount() > 0) {
                        int routes = jTableRoutes.getRowCount();
                        processStage = "Evaluating routes (" + routes + "/" + totalPermutations + ")";
                        double perc = (routes * Math.pow(totalPermutations, -1)) * 100;
                        jProgressBar1.setValue((int) perc);
                        if (jTableRoutes.getRowCount() == totalPermutations) {
                            processStage = "Done";
                            jProgressBar1.setValue(100);
                            progressFlag = false;
                            long totalTime = endTime - startTime;
                            jLabel2.setText("Cantidad de destinos: " + cities.size() + "  |  Punto de partida: " + jComboBox1.getSelectedItem() + "  |  Posibles rutas: " + BigIntegerMath.factorial(cities.size() - 1) + " | Mejor ruta: " + bestRoute + "  |  Tiempo de procesamiento: " + totalTime + " ms");
                            jButton1.setEnabled(true);
                        }
                    }
                }
                //Update right lower corner
                jLabel3.setText(processStage);
            }
        });
        executor.shutdown();
    }

    /**
     * Build GUI elements. Specifically tables
     */
    public void buildGUI() {
        DefaultTableModel model = ((DefaultTableModel) jTableRoutes.getModel());
        /**
         * This is for column sorting
         */
        TableRowSorter<TableModel> firstColumnSorter = new TableRowSorter<>(model);
        jTableRoutes.setRowSorter(firstColumnSorter);

        firstColumnSorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Implement the comparison logic here
                return s1.compareTo(s2);
            }
        });

        TableRowSorter<TableModel> secondColumnSorter = new TableRowSorter<>(model);
        jTableRoutes.setRowSorter(secondColumnSorter);
        secondColumnSorter.setComparator(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return Integer.valueOf(n1).compareTo(Integer.valueOf(n2));
            }
        });

        secondColumnSorter.sort();
        jTableRoutes.setRowHeight(20);
        jTableCities.setRowHeight(20);
        jTableRoutes.setDefaultRenderer(jTableRoutes.getColumnClass(1), new CenterAlignmentRenderer());

        jTableRoutes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTableRoutes.getSelectedRow() >= 0) {
                    bruteForceCanvas.setActualRoute(String.valueOf(jTableRoutes.getValueAt(jTableRoutes.getSelectedRow(), 0)));
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRoutes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCities = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1350, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));

        jTableRoutes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableRoutes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Route", "Distance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRoutes.setMaximumSize(new java.awt.Dimension(300, 80));
        jTableRoutes.setMinimumSize(new java.awt.Dimension(300, 80));
        jTableRoutes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableRoutes);
        if (jTableRoutes.getColumnModel().getColumnCount() > 0) {
            jTableRoutes.getColumnModel().getColumn(0).setResizable(false);
            jTableRoutes.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTableRoutes.getColumnModel().getColumn(1).setResizable(false);
            jTableRoutes.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        jTableCities.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableCities.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "City Label", "X coord.", "Y coord."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCities.setEnabled(false);
        jTableCities.setFocusable(false);
        jTableCities.setMaximumSize(new java.awt.Dimension(300, 80));
        jTableCities.setMinimumSize(new java.awt.Dimension(300, 80));
        jTableCities.setRequestFocusEnabled(false);
        jTableCities.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableCities);
        if (jTableCities.getColumnModel().getColumnCount() > 0) {
            jTableCities.getColumnModel().getColumn(0).setResizable(false);
            jTableCities.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTableCities.getColumnModel().getColumn(1).setResizable(false);
            jTableCities.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableCities.getColumnModel().getColumn(2).setResizable(false);
            jTableCities.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Find routes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Initial city: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        jTabbedPane1.addTab("Brute force", jPanel1);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Cantidad de destinos: 10  |  Punto de partida: E  |  Posibles rutas: 362000  | Mejor ruta: N/A  |  Tiempo de procesamiento: N/A");

        jProgressBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jProgressBar1.setStringPainted(true);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Procesando (1/362000)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(2, 2, 2))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //The button is disabled, so no new process can be started while computing a previous process
        jButton1.setEnabled(false);
        //Reset progress bar
        jProgressBar1.setValue(0);
        //Reset routes table
        DefaultTableModel model = ((DefaultTableModel) jTableRoutes.getModel());
        model.setRowCount(0);

        //Start updating GUI
        progressFlag = true;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {

            String initialCity = jComboBox1.getSelectedItem() + "";
            String possibleRoutes = "";
            //Measure start time
            startTime = System.currentTimeMillis();
            List<String> citiesToVisit = new ArrayList<>();
            cities.stream().filter(a -> !a.getLabel().equals(initialCity)).forEach(a -> {
                citiesToVisit.add(a.getLabel());
            });

            processStage = "Defining routes";
            for (int i = 0; i < citiesToVisit.size(); i++) {
                possibleRoutes += citiesToVisit.get(i);
            }
            processStage = "Calculating permutations";
            //Calculate all possible permutations
            List<String> permutations =  getPermutations(possibleRoutes);
            totalPermutations = permutations.size();
            for (int i = 0; i < permutations.size(); i++) {
                String str = permutations.get(i);
                str = initialCity + str;
                permutations.set(i, str);
            }
            processStage = "Evaluating routes (0/" + permutations.size() + ")";
            //Calculate routes and total distances based on permutations
            List<Route> routes = new RouteCalculator(permutations, cities).calculateAllRoutes(jTableRoutes);
            //Measure end time
            endTime = System.currentTimeMillis();
            processStage = "Done";
            bestRoute = Collections.min(routes, (a, b) -> Double.valueOf(a.getDistance()).compareTo(Double.valueOf(b.getDistance()))).getRoute();
        });
        executor.shutdown();
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCities;
    private javax.swing.JTable jTableRoutes;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    class CenterAlignmentRenderer extends DefaultTableCellRenderer {

        public CenterAlignmentRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }
}

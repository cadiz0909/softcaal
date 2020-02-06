/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import beans.Ingrediente;
import javax.swing.JOptionPane;

/**
 *
 * @author cadiaz
 */
public class IngredienteWindows extends javax.swing.JPanel {

    /**
     * Creates new form IngredienteWindows
     */
    public IngredienteWindows() {
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

        jLabelTitulo = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelUnidad = new javax.swing.JLabel();
        jLabelCantidad = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jComboUnidad = new javax.swing.JComboBox<>();
        jTextCantidad = new javax.swing.JTextField();
        crearIngrediente = new javax.swing.JButton();

        jLabelTitulo.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabelTitulo.setText("Ingrediente");

        jLabelNombre.setText("Nombre");

        jLabelUnidad.setText("Unidad");

        jLabelCantidad.setText("Cantidad");

        jTextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreActionPerformed(evt);
            }
        });

        jComboUnidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kg", "g", "Paquete", "ml", "unidad" }));
        jComboUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboUnidadActionPerformed(evt);
            }
        });

        crearIngrediente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        crearIngrediente.setText("Crear");
        crearIngrediente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearIngredienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabelTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCantidad)))
                            .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextNombre)
                            .addComponent(jComboUnidad, 0, 124, Short.MAX_VALUE)
                            .addComponent(jTextCantidad)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(crearIngrediente)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUnidad)
                    .addComponent(jComboUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCantidad)
                    .addComponent(jTextCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(crearIngrediente)
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreActionPerformed

    private void jComboUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboUnidadActionPerformed

    private void crearIngredienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearIngredienteMouseClicked
        // TODO add your handling code here:
        String nombre = jTextNombre.getText().trim();
        String unidad = jComboUnidad.getSelectedItem().toString().trim();
        String cantidad = jTextCantidad.getText().trim();
        
        int valor = 0;
        boolean exito = false;

        try {
            valor = Integer.parseInt(cantidad);
            exito = true;
            
        } catch (NumberFormatException e) {
            System.err.println("No se puede convertir a numero");
        }
        
        if(validate(nombre) && validate(unidad) && validate(cantidad) && exito){
            
            Ingrediente ingrediente = new Ingrediente(null, nombre, unidad, valor);
            
            if(!ingrediente.existeIngrediente(ingrediente.getNombre(),ingrediente.getUnidad(), ingrediente.getCantidad())){
                
                if(ingrediente.crearIngrediente()){
                    JOptionPane.showMessageDialog(this, "Ingrediente creado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al crear el ingrediente");
                }
            } else {
                JOptionPane.showMessageDialog(this, "El ingrediente ya existe");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Datos incorrectos");
        }
        
        
        
    }//GEN-LAST:event_crearIngredienteMouseClicked

    
       /**
     * Valida que el campo no sea nulo o vacio
     * @param param
     * @return 
     */
    private boolean validate(String param){
        
        return !(param == null || param.equals("") || param.equals(" "));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearIngrediente;
    private javax.swing.JComboBox<String> jComboUnidad;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelUnidad;
    private javax.swing.JTextField jTextCantidad;
    private javax.swing.JTextField jTextNombre;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caal;

import java.awt.BorderLayout;
import javax.swing.*;
import windows.ClienteWindows;
import windows.IngredienteWindows;
import windows.ProductoWindows;

/**
 *
 * @author carlos
 */
public class Caal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame f = new JFrame("Gestor Yoguis");
        f.setSize(500, 400);
        f.setLocation(150, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        //Panel con etiquetas
        JTabbedPane pVista = new JTabbedPane();
        f.add(pVista, BorderLayout.CENTER);//Agregamos al panel nuestro objeto JTabbedPane
        pVista.addTab("Crear cliente", new ClienteWindows());
        pVista.addTab("Crear ingrediente", new IngredienteWindows());
        pVista.addTab("Crear producto", new ProductoWindows());
    }

}

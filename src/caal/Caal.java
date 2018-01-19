/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caal;
import beans.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author carlos
 */
public class Caal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion bd= new Conexion();
        
        ResultSet m= bd.consultarBD("SELECT * FROM usu ");
        
        try {
            while(m.next()){
               System.out.println(m.getString("Id"));
               System.out.println(m.getString("Nombre"));
               System.out.println(m.getString("Genero"));
               System.out.println(m.getString("Telefono"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Caal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

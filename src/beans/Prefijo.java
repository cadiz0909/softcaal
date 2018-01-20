/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class Prefijo {
    private String Prefijo;
    private String Tabla;
    private String Consecutivo;

    public String getPrefijo() {
        return Prefijo;
    }

    public void setPrefijo(String Prefijo) {
        this.Prefijo = Prefijo;
    }

    public String getTabla() {
        return Tabla;
    }

    public void setTabla(String Tabla) {
        this.Tabla = Tabla;
    }

    public String getConsecutivo() {
        return Consecutivo;
    }

    public void setConsecutivo(String Consecutivo) {
        this.Consecutivo = Consecutivo;
    }

    public Prefijo() {
    }
    
    /**
     * Metodo para generar un nuevo identificador para un registro en la taba indicada
     * @param Tabla
     * @return id string con el nuevo identificador 
     **/
    public String CrearId(String Tabla){
        
        String prefijo = new String();
        int actual= 0;
        String id= new String();
        
        Conexion con= new Conexion();
        
        String sentencia= "SELECT Prefijo,Consecutivo FROM Prefijos WHERE Tabla='"+ Tabla +"'";
        
        ResultSet consulta= con.consultarBD(sentencia);
        
        
        try {
            if(consulta.next()){
                
                prefijo= consulta.getString("Prefijo").trim();
                actual= consulta.getInt("Consecutivo");
            }
            
            actual += 1;
            id= prefijo+actual;
            
            con.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Prefijo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      return id ;
    }
    /**Metodo para la actualización del consecutivo
     * @param Tabla
     * @return true/false segun el reutado de la actualización
     **/
    public boolean ActualizarConsecutivo(String Tabla){
        
        Conexion con = new Conexion();
        String sentencia = "SELECT Consecutivo FROM Prefijos WHERE Tabla='" +Tabla+ "'";
        int consec =0;
        ResultSet consulta= con.consultarBD(sentencia);
        
        try {
            if(consulta.next()){
                consec = consulta.getInt("Consecutivo");
            }else{
                return false;
            }
            consec = consec+1;
            sentencia= "UPDATE Prefijos SET Consecutivo ='"+ consec + "' WHERE Tabla='"+ Tabla +"'";
            
            return con.actualizarBD(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(Prefijo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            con.cerrarConexion();
        }
    }
}

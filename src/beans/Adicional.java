/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class Adicional {
    
    private String IdAdicional;
    private String IdIngrediente;
    private int Precio;

    public String getIdAdicional() {
        return IdAdicional;
    }

    public void setIdAdicional(String IdAdicional) {
        this.IdAdicional = IdAdicional;
    }

    public String getIdIngrediente() {
        return IdIngrediente;
    }

    public void setIdIngrediente(String IdIngrediente) {
        this.IdIngrediente = IdIngrediente;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public Adicional(String IdAdicional, String IdIngrediente, int Precio) {
        this.IdAdicional = IdAdicional;
        this.IdIngrediente = IdIngrediente;
        this.Precio = Precio;
    }

    public Adicional() {
    }
    /**Metodo para crear un adicional en la base de datos
     * @return true/false 
     **/
    public boolean CrearAdicional(){
        
        Conexion con = new Conexion();
        
        String setencia= "INSERT INTO Adicionales (IdAdicional,IdIngrediente,Precio) "
                + "VALUES('"+ this.IdAdicional +"','"+ this.IdIngrediente +"','"+ this.Precio +"')";
        boolean exito = con.insertarBD(setencia);
        con.cerrarConexion();
        
        return exito;
    }
    /**
     * Metodo para modificar el nombre o precio del adicional
     * @param IdAdicional Identificador del objeto
     * @param IdIngrediente Asociacion del ingrediente con el adicional
     * @param Precio Valor del nuevo adicional
     * @return 
     **/
    public boolean ModificarAdicional(String IdAdicional,String IdIngrediente, int Precio){
        
        Conexion con = new Conexion();
        
        String sentencia = "UPDATE Adicionales SET IdIngrediente='"+ IdIngrediente +"', Precio='"+ Precio +"' "
                + "WHERE IdAdicional='"+ IdAdicional +"'";
        boolean exito = con.actualizarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
        
    }
    /**Metodo para eliminar un adicional en particular
     * @param IdAdicional Identificador del adicional para eliminar
     * @return 
     **/
    public boolean EliminarAdicional(String IdAdicional){
        
        Conexion con = new Conexion();
        
        String sentencia = "DELETE  FROM Adicionales WHERE IdAdicional='"+ IdAdicional +"'";
        boolean exito = con.actualizarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
    }
    /**Metodo para consultar todos los adicionales
     * @return  Una lista con todos los adicionales en la base de datos
     **/
    public ArrayList<Adicional> getAdicionales(){
        
       ArrayList <Adicional> Adicionales= new ArrayList<>(); 
       Conexion con = new Conexion();
       
       String sentencia = "SELECT * FROM Adicionales";
       ResultSet consulta = con.consultarBD(sentencia);
       
        try {
            while(consulta.next()){
                Adicional Adi= new Adicional();
                Adi.setIdAdicional(consulta.getString("IdAdicional"));
                Adi.setIdIngrediente(consulta.getString("IdIngrediente"));
                Adi.setPrecio(consulta.getInt("Precio"));
                
                Adicionales.add(Adi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Adicional.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally{
            con.cerrarConexion();
        }
        
        return Adicionales;
    }
    /**Metodo para buscar adicionales por el nombre 
     * @param Nombre Parametro de busqueda del adicional
     * @return Una lista de adicionales que coincidan con alguna parte del parametro ingresado.
     **/
    public ArrayList<Adicional> BusquedaPorNombre(String Nombre){
        
        ArrayList <Adicional> Adicionales= new ArrayList<>(); 
       Conexion con = new Conexion();
       
       String sentencia = "SELECT adi.`IdAdicional`, adi.`IdIngrediente`, adi.`Precio`FROM Adicionales adi, Ingredientes ing"
               + " WHERE ing.`Nombre` LIKE '%"+ Nombre +"%' AND ing.`IdIngrediente`=adi.`IdIngrediente`";
       ResultSet consulta = con.consultarBD(sentencia);
       
        try {
            while(consulta.next()){
                Adicional Adi= new Adicional();
                Adi.setIdAdicional(consulta.getString("IdAdicional"));
                Adi.setIdIngrediente(consulta.getString("IdIngrediente"));
                Adi.setPrecio(consulta.getInt("Precio"));
                
                Adicionales.add(Adi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Adicional.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally{
            con.cerrarConexion();
        }
        
        return Adicionales;
    }
    /**Metodo para buscar el nombre de un adicional deacuerdo al nombre del ingrediente
     * @param IdIngrediente Parametro por el cual se busca el nombre del adicional en la lista de ingredientes
     * @return El nombre del adicional
     **/
    public String getNombre(String IdIngrediente){
        
        Conexion con = new Conexion();
        String sentencia= "SELECT Nombre FROM Ingredientes   WHERE IdIngrediente='"+ IdIngrediente+ "'";
        ResultSet consulta = con.consultarBD(sentencia);
        String respuesta = new String();
        try {
            if(consulta.next()){
                respuesta= consulta.getString("Nombre");
            }else{  
                respuesta= "No existe";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Adicional.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.cerrarConexion();
        }
        return respuesta;
    }
}

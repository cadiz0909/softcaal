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
public class Cliente {
    
    private String IdCliente;
    private String Cedula;
    private String Nombres;
    private String Direccion;
    private String Telefono;
    private String Celular;

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public Cliente() {
    }

    public Cliente(String IdCliente, String Cedula, String Nombres, String Direccion, String Telefono, String Celular) {
        this.IdCliente = IdCliente;
        this.Cedula = Cedula;
        this.Nombres = Nombres;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Celular = Celular;
    }
    /**Medoto para insertar un cliente en la base de datos
     * @return Devuelve true o false 
     **/
    public boolean CrearCliente(){
        
        Conexion con = new Conexion();
        
        Prefijo prefijo = new Prefijo();
        
        String id = prefijo.CrearId("clientes");
                
        String sentencia = "INSERT INTO Clientes (IdCliente,Cedula,Nombre,Direccion,Telefono,Celular) "
                + "VALUES ('"+ id +"','"+ this.Cedula +"','"+ this.Nombres +"','"+ this.Direccion + "','"+ this.Telefono +"','"+ this.Celular +"')";
        
        boolean exito= con.insertarBD(sentencia);
        
        if(exito){
            prefijo.ActualizarConsecutivo("clientes");
        }
        con.cerrarConexion();
        
        return exito;
    }
    /**Metodo Para modificar cualquier dato del cliente
     * @param IdCliente
     * @param Cedula
     * @param Nombre
     * @param Direccion
     * @param Telefono
     * @param Celular
     * @return Devuelve true o false 
     **/
    public boolean ModificarCliente(String IdCliente,String Cedula,String Nombre,String Direccion,String Telefono,String Celular){
        
        Conexion con = new Conexion();
        
        String sentencia= "UPDATE Clientes SET Cedula='"+ Cedula +"', Nombre='"+ Nombre +"', Direccion='"+ Direccion +"', Telefono='"+ Telefono +"', Celular='"+ Celular +"' "
                + "WHERE IdCliente='"+ IdCliente +"'";
        
        boolean exito= con.actualizarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
        
        
    }
   
    /**Metodo para eliminar un cliente de la base de datos
     * @param IdCliente
     * @return Devuelve true o false 
     **/
    public boolean EliminarCliente(String IdCliente){
        
        Conexion con= new Conexion();
        
        String sentencia= "DELETE FROM Clientes WHERE IdCliente='"+ IdCliente +"'";
        boolean exito= con.actualizarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
    }
    /**Metodo que devuelve en un listado todos los clientes de la base de datos
     * @return ArrayList de clientes
     **/
    public ArrayList<Cliente> getClientes(){
        
        ArrayList<Cliente> clientes= new ArrayList<>();
        Conexion con = new Conexion();
        
        String sentencia= "SELECT * FROM Clientes ";
        
        ResultSet busqueda= con.consultarBD(sentencia);
        
        try {
            while(busqueda.next()){
                Cliente clie = new Cliente();
                clie.setIdCliente(busqueda.getString("IdCliente"));
                clie.setCedula(busqueda.getString("Cedula"));
                clie.setNombres(busqueda.getString("Nombre"));
                clie.setDireccion(busqueda.getString("Direccion"));
                clie.setTelefono(busqueda.getString("Telefono"));
                clie.setCelular(busqueda.getString("Celular"));
                
                clientes.add(clie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        
        return clientes;
    }
    /**Metodo para buscar clientes por una parte del nombre o completo
     * @param Nombre
     * @return Devuelve un ArrayList con las coincidencas
     **/
    public ArrayList<Cliente> BusquedaPorNombre(String Nombre){
        
        ArrayList<Cliente> clientes= new ArrayList<>();
        Conexion con = new Conexion();
        
        String sentencia= "SELECT * FROM Clientes WHERE Nombre LIKE '%"+ Nombre +"%'";
        
        ResultSet busqueda= con.consultarBD(sentencia);
        
        try {
            while(busqueda.next()){
                Cliente clie = new Cliente();
                clie.setIdCliente(busqueda.getString("IdCliente"));
                clie.setCedula(busqueda.getString("Cedula"));
                clie.setNombres(busqueda.getString("Nombre"));
                clie.setDireccion(busqueda.getString("Direccion"));
                clie.setTelefono(busqueda.getString("Telefono"));
                clie.setCelular(busqueda.getString("Celular"));
                
                clientes.add(clie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        
        return clientes;
    }
    /**Metodo que busca clientes por cedula
     * @param Cedula
     * @return Devuelve un ArrayList con las coincidencas
     **/
    public ArrayList<Cliente> BusquedaPorCedula(String Cedula){
        
        ArrayList<Cliente> clientes= new ArrayList<>();
        Conexion con = new Conexion();
        
        String sentencia= "SELECT * FROM Clientes WHERE Cedula LIKE '"+ Cedula +"%'";
        
        ResultSet busqueda= con.consultarBD(sentencia);
        
        try {
            while(busqueda.next()){
                Cliente clie = new Cliente();
                clie.setIdCliente(busqueda.getString("IdCliente"));
                clie.setCedula(busqueda.getString("Cedula"));
                clie.setNombres(busqueda.getString("Nombre"));
                clie.setDireccion(busqueda.getString("Direccion"));
                clie.setTelefono(busqueda.getString("Telefono"));
                clie.setCelular(busqueda.getString("Celular"));
                
                clientes.add(clie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        
        return clientes;
    }
    
    public boolean existeUsuario(String cedula){
        
        Conexion con = new Conexion();
        
        String sentencia= "SELECT * FROM Clientes WHERE Cedula = '"+ Cedula.trim() +"%'";
        
        ResultSet busqueda= con.consultarBD(sentencia);
        
        try {
            if(busqueda.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        
        return false;
        
    }
    
}

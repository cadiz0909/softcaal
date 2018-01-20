/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import beans.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexy
 */


public class Producto {
    
    private String idProducto;
    private String nombre;
    private int precio;
    
    
    public Producto(){
        
    }
    
    public Producto(String id, String nombre, int precio){
        this.idProducto = id;
        this.nombre = nombre;
        this.precio = precio;
        
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean crearProducto(){
        Conexion bd= new Conexion();
        
        boolean insertar = bd.insertarBD("INSERT INTO Productos (IdProducto, Nombre, Precio) "
                + "VALUES('"+getIdProducto()+"', '"+getNombre()+"','"+getPrecio()+"')");
        bd.cerrarConexion();
        return insertar;
    }
    
    public boolean modificarProducto(String id, String nombre, int precio){
        Conexion bd = new Conexion();
        boolean resultado = false;
                
        if((!nombre.equals(null) || !nombre.equals("")) && ((precio == 0) )){
            resultado =  bd.actualizarBD("UPDATE Productos SET Nombre = '"+nombre+"' WHERE IdProducto = '"+id+"'");
            bd.cerrarConexion();
            return resultado;
        }
        
        if((nombre.equals(null) || nombre.equals("")) && ((precio > 0) )){
            resultado = bd.actualizarBD("UPDATE Productos SET precio = '"+precio+"' WHERE IdProducto = '"+id+"'");
            bd.cerrarConexion();
            return resultado;
        }
        
        if((!nombre.equals(null) || !nombre.equals("")) && ((precio > 0) )){
            resultado = bd.actualizarBD("UPDATE Productos SET Nombre = '"+nombre+"', Precio = '"+precio+"' WHERE IdProducto = '"+id+"'");
            bd.cerrarConexion();
            return resultado;
        }
        
        bd.cerrarConexion();
        return resultado;
    }
    
    public boolean eliminarProducto(String id){
        Conexion bd= new Conexion();
        
        boolean eliminar = bd.actualizarBD("DELETE from Productos WHERE IdProducto = '"+ id + "'");
        
        return eliminar;
    }
}

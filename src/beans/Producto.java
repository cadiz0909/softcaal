/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import beans.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public String toString(){
        return this.idProducto + " " + this.nombre + " " + this.precio;
    }

    /**
     * Este metodo crea un nuevo producto
     * @return boolean, true si se crea el producto, false en caso contrario 
     */
    public boolean crearProducto(){
        Conexion bd= new Conexion();
        
        boolean insertar = bd.insertarBD("INSERT INTO Productos (IdProducto, Nombre, Precio) "
                + "VALUES('"+getIdProducto()+"', '"+getNombre()+"','"+getPrecio()+"')");
        bd.cerrarConexion();
        return insertar;
    }
    /**
     * Permite modificar un producto ya creado.
     * @param id busca el producto que se desea modificar en base de datos
     * @param nombre contiene el valor a modificar
     * @param precio contiene el valor a modificar
     * @return true en caso de que se halla modificado el producto, false en caso contrario.
     */
    
    public boolean modificarProducto(String id, String nombre, int precio){
        Conexion bd = new Conexion();
        boolean resultado = false;
        
        resultado = bd.actualizarBD("UPDATE Productos SET Nombre = '"+nombre+"', Precio = '"+precio+"' WHERE IdProducto = '"+id+"'");
        bd.cerrarConexion();
        
        return resultado;
    }
    
    /**
     * Elimina un producto de la base de datos
     * @param id obtiene el producto que se desea eliminar
     * @return true si se elimino el producto
     */
    public boolean eliminarProducto(String id){
        Conexion bd= new Conexion();
        
        boolean eliminar = bd.actualizarBD("DELETE from Productos WHERE IdProducto = '"+ id + "'");
        
        return eliminar;
    }
    
    /**
     * Obtiene todos los productos 
     * @return lista de productos
     */
    public ArrayList<Producto> getProductos(){
        
        Conexion bd= new Conexion();
        ArrayList<Producto> listaObjeto = new ArrayList<Producto>();
        Producto objeto;
        String id;
        String nomb;
        int price;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Productos ");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdProducto");
                nomb = consulta.getString("Nombre");
                price = consulta.getInt("Precio");
                
                objeto = new Producto(id,nomb,price);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaObjeto;
    }
    
    
    /**
     * Busca todos lo productos que contienen en el nombre la frase que se lepasa como argumento.
     * @param nombre , contiene la palabra que se compara con el nombre de los productos en la base de datos
     * @return retorna un alista de productos que contienen en su nombre la frase pasada en el argumento.
     */
    public ArrayList<Producto> listarProductosNombre(String nombre){
        
        Conexion bd= new Conexion();
        ArrayList<Producto> listaObjeto = new ArrayList<Producto>();
        Producto objeto;
        String id;
        String nomb;
        int price;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Productos WHERE Nombre LIKE '%"+nombre+"%'");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdProducto");
                nomb = consulta.getString("Nombre");
                price = consulta.getInt("Precio");
                
                objeto = new Producto(id,nomb,price);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaObjeto;
    }
}

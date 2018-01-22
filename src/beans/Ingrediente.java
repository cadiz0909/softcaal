/*
 * La clase Ingrediente se encarga del manejo de la tabla de Ingredientes en base de datos. 
 * Realiza funciones como:
 * creación, modificación, eliminación, busqueda de ingredientes por nombre.
 */
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexy
 */
public class Ingrediente {
    
    private String idIngrediente;
    private String nombre;
    private int cantidad;

    public Ingrediente() {
    }

    public Ingrediente(String idIngrediente, String nombre, int cantidad) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(String idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
    public String toString(){
        return this.idIngrediente + " " + this.nombre + " " + this.cantidad;
    }
    
    /**
     * Este metodo crea un nuevo Ingrediente
     * @return true, si se creo el ingrediente
     */
    public boolean crearIngrediente(){
        Conexion bd= new Conexion();
        
        boolean insertar = bd.insertarBD("INSERT INTO Ingredientes (IdIngrediente, Nombre, Cantidad) "
                + "VALUES('"+getIdIngrediente()+"', '"+getNombre()+"','"+getCantidad()+"')");
        bd.cerrarConexion();
        return insertar;
    }
    /**
     * Permite modificar un ingrediente ya creado.
     * @param id
     * @param nombre 
     * @param cantidad 
     * @return true en caso de que se halla modificado el ingrediente, false en caso contrario.
     */
    
    public boolean modificarIngrediente(String id, String nombre, int cantidad){
        Conexion bd = new Conexion();
        boolean resultado;
        
        resultado = bd.actualizarBD("UPDATE Ingredientes SET Nombre = '"+nombre+"', Cantidad = '"+cantidad+"' WHERE IdIngrediente = '"+id+"'");
        bd.cerrarConexion();
        
        return resultado;
    }
    
    /**
     * Elimina un Ingrediente de la base de datos
     * @param id , Ingrediente a eliminar
     * @return true si se elimino el ingrediente
     */
    public boolean eliminarIngrediente(String id){
        Conexion bd= new Conexion();
        
        boolean eliminar = bd.actualizarBD("DELETE from Ingredientes WHERE IdIngrediente = '"+ id + "'");
        
        bd.cerrarConexion();
        
        return eliminar;
    }
    
    /**
     * Obtiene todos los ingredientes 
     * @return lista de ingredientes
     */
    public ArrayList<Ingrediente> getIngredientes(){
        
        Conexion bd= new Conexion();
        ArrayList<Ingrediente> listaObjeto = new ArrayList<Ingrediente>();
        Ingrediente objeto;
        String id;
        String nomb;
        int cant;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Ingredientes ");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdIngrediente");
                nomb = consulta.getString("Nombre");
                cant = consulta.getInt("Cantidad");
                
                objeto = new Ingrediente(id,nomb,cant);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
    
    
    /**
     * Busca todos lo ingredientes que contienen en el nombre la frase que se le pasa como argumento.
     * @param nombre , contiene la palabra que se compara con el nombre de los ingredientes en la base de datos
     * @return ArrayList<Ingrediente>, retorna una lista de ingredientes que contienen en su nombre la frase pasada en el argumento.
     */
    public ArrayList<Ingrediente> listarIngredienteNombre(String nombre){
        
        Conexion bd= new Conexion();
        ArrayList<Ingrediente> listaObjeto = new ArrayList<Ingrediente>();
        Ingrediente objeto;
        String id;
        String nomb;
        int cant;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Ingredientes WHERE Nombre LIKE '%"+nombre+"%'");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdIngrediente");
                nomb = consulta.getString("Nombre");
                cant = consulta.getInt("Cantidad");
                
                objeto = new Ingrediente(id,nomb,cant);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
    
    /**
     * Busca todos lo ingredientes por cantidad.
     * @param cantidad 
     * @return ArrayList<Ingrediente>
     */
    public ArrayList<Ingrediente> listarIngredienteCantidad(int cantidad){
        
        Conexion bd= new Conexion();
        ArrayList<Ingrediente> listaObjeto = new ArrayList<Ingrediente>();
        Ingrediente objeto;
        String id;
        String nomb;
        int cant;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Ingredientes WHERE Cantidad = '"+cantidad+"'");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdIngrediente");
                nomb = consulta.getString("Nombre");
                cant = consulta.getInt("Cantidad");
                
                objeto = new Ingrediente(id,nomb,cant);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
    
    
}

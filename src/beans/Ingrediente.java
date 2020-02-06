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
 * @modifi Carlos 
 * @date 30/01/2020
 * @version 2.0
 */
public class Ingrediente {
    
    private String idIngrediente;
    private String nombre;
    private String unidad;
    private int cantidad;

    /**
    Constructor sin parametros
    */
    public Ingrediente() {
        //Empty
    }

    /**
     * Constructor
     * @param idIngrediente
     * @param nombre
     * @param unidad
     * @param cantidad 
     */
    public Ingrediente(String idIngrediente, String nombre, String unidad, int cantidad) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
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

    @Override
    public String toString(){
        return  this.nombre + " " + this.cantidad + " " + this.unidad;
    }
    
    /**
     * Este metodo crea un nuevo Ingrediente
     * @return true, si se creo el ingrediente
     */
    public boolean crearIngrediente(){
        
        Conexion bd= new Conexion();

        Prefijo prefijo = new Prefijo();
        
        String id = prefijo.CrearId("ingredientes");
        
        boolean insertar = bd.insertarBD("INSERT INTO Ingredientes (IdIngrediente, Nombre, Unidad, Cantidad) "
                + "VALUES('"+ id +"', '" + getNombre() + "','" + getUnidad() + "','" + getCantidad() + "')");
        
        if(insertar){
            prefijo.ActualizarConsecutivo("ingredientes");
        }
        bd.cerrarConexion();
        return insertar;
    }
    /**
     * Permite modificar un ingrediente ya creado.
     * @param id
     * @param nombre 
     * @param unidad
     * @param cantidad 
     * @return true en caso de que se halla modificado el ingrediente, false en caso contrario.
     */
    
    public boolean modificarIngrediente(String id, String nombre, String unidad, int cantidad){
        Conexion bd = new Conexion();
        boolean resultado;
        
        resultado = bd.actualizarBD("UPDATE Ingredientes SET Nombre = '" + nombre + "', Cantidad = '" + cantidad + "', Unidad = '" + unidad + "' WHERE IdIngrediente = '" + id + "'");
        bd.cerrarConexion();
        
        return resultado;
    }
    
    /**
     * Elimina un Ingrediente de la base de datos, despues de haber eliminado 
     * su relacion con los respectivos prodcutos en ProductoIngrediente
     * @param idIngrediente , Ingrediente a eliminar
     * @return true si se elimino el ingrediente
     */
    public boolean eliminarIngrediente(String idIngrediente){
        Conexion bd= new Conexion();
        boolean eliminar = false;
        boolean exito = bd.actualizarBD("DELETE FROM ProductoIngrediente WHERE IdIngrediente = '"+ idIngrediente + "'");
        boolean exito2 = bd.actualizarBD("DELETE FROM Adicionales WHERE IdIngrediente = '"+ idIngrediente + "'");
                
        if(exito && exito2){
            eliminar = bd.actualizarBD("DELETE FROM Ingredientes WHERE IdIngrediente = '"+ idIngrediente + "'");
        }
        bd.cerrarConexion();
        
        return eliminar;
    }
    
    /**
     * Obtiene todos los ingredientes 
     * @return lista de ingredientes
     */
    public ArrayList<Ingrediente> getIngredientes(){
        
        Conexion bd= new Conexion();
        ArrayList<Ingrediente> listaObjeto = new ArrayList<>();
        Ingrediente objeto;
        String id;
        String nomb;
        String uni;
        int cant;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Ingredientes ");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdIngrediente");
                nomb = consulta.getString("Nombre");
                uni = consulta.getString("Unidad");
                cant = consulta.getInt("Cantidad");
                
                objeto = new Ingrediente(id, nomb, uni, cant);
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
     * @return ArrayList<>, retorna una lista de ingredientes que contienen en su nombre la frase pasada en el argumento.
     */
    public ArrayList<Ingrediente> listarIngredienteNombre(String nombre){
        
        Conexion bd= new Conexion();
        ArrayList<Ingrediente> listaObjeto = new ArrayList<>();
        Ingrediente objeto;
        String id;
        String nomb;
        String uni;
        int cant;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Ingredientes WHERE Nombre LIKE '%"+nombre+"%'");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdIngrediente");
                nomb = consulta.getString("Nombre");
                uni = consulta.getString("Unidad");
                cant = consulta.getInt("Cantidad");
                
                objeto = new Ingrediente(id, nomb, uni, cant);
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
     * @return ArrayList<>
     */
    public ArrayList<Ingrediente> listarIngredienteCantidad(int cantidad){
        
        Conexion bd= new Conexion();
        ArrayList<Ingrediente> listaObjeto = new ArrayList<>();
        Ingrediente objeto;
        String id;
        String nomb;
        String uni;
        int cant;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Ingredientes WHERE Cantidad = '"+cantidad+"'");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdIngrediente");
                nomb = consulta.getString("Nombre");
                uni = consulta.getString("Unidad");
                cant = consulta.getInt("Cantidad");
                
                objeto = new Ingrediente(id, nomb, uni, cant);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
    
    /**
     * Verifica la existencia de un ingrediente por nombre y unidad
     * @param nombre
     * @param unidad
     * @param cantidad
     * @return 
     */
    public boolean existeIngrediente(String nombre, String unidad, int cantidad){
        
        boolean existe = false;
        
        Conexion con = new Conexion();
        
        String sentencia= "SELECT * FROM Ingredientes WHERE Nombre = '" + nombre.trim() + "' and Unidad = '" + unidad.trim() + "' and Cantidad = '" + cantidad + "'";
        
        ResultSet busqueda= con.consultarBD(sentencia);
        
        try {
            if(busqueda.next()){
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        
        return existe;
    }
}

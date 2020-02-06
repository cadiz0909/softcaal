/*
 * La clase Producto se encarga del manejo de la tabla de Producto en base de datos. 
 * Realiza funciones como:
 * creación, modificación, eliminación, busqueda de Productos por nombre.
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


public class Producto {
    
    private String idProducto;
    private String nombre;
    private double precio;
    
    /**
     * Constructor sin parametros
     */
    public Producto(){
        //Empty
    }
    
    /**
     * Constructor
     * @param id
     * @param nombre
     * @param precio 
     */
    public Producto(String id, String nombre, double precio){
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString(){
        return this.idProducto + " " + this.nombre + " " + this.precio;
    }

    /**
     * Este metodo crea un nuevo producto
     * @return boolean, true si se crea el producto, false en caso contrario 
     */
    public boolean crearProducto(){
       
        Conexion bd= new Conexion();
        
        Prefijo prefijo = new Prefijo();
        
        String id = prefijo.CrearId("productos");
        
        boolean insertar = bd.insertarBD("INSERT INTO Productos (IdProducto, Nombre, Precio) "
                + "VALUES('"+ id +"', '"+getNombre()+"','"+getPrecio()+"')");
        
        if(insertar){
            prefijo.ActualizarConsecutivo("productos");
            setIdProducto(id);
        }
        
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
        boolean resultado;
        
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
        boolean eliminar = false;
        if(eliminarProductoIngrediente(id)){
        
            eliminar = bd.actualizarBD("DELETE from Productos WHERE IdProducto = '"+ id + "'");
        }
        bd.cerrarConexion();
        
        return eliminar;
    }
    
    /**
     * Obtiene todos los productos 
     * @return lista de productos
     */
    public ArrayList<Producto> getProductos(){
        
        Conexion bd= new Conexion();
        ArrayList<Producto> listaObjeto = new ArrayList<>();
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
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
    
    
    /**
     * Busca todos lo productos que contienen en el nombre la frase que se lepasa como argumento.
     * @param nombre , contiene la palabra que se compara con el nombre de los productos en la base de datos
     * @return retorna un alista de productos que contienen en su nombre la frase pasada en el argumento.
     */
    public ArrayList<Producto> listarProductosNombre(String nombre){
        
        Conexion bd= new Conexion();
        ArrayList<Producto> listaObjeto = new ArrayList<>();
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
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
    
    /**
     * Busca todos lo productos que contienen los precios similares.
     * @param precio , precio a comparar
     * @return lista de productos.
     */
    public ArrayList<Producto> listarProductosPrecio(int precio){
        
        Conexion bd= new Conexion();
        ArrayList<Producto> listaObjeto = new ArrayList<>();
        Producto objeto;
        String id;
        String nomb;
        double price;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Productos WHERE Precio = '"+precio+"'");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdProducto");
                nomb = consulta.getString("Nombre");
                price = consulta.getDouble("Precio");
                
                objeto = new Producto(id,nomb,price);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
    
    /**
     * Agregar la relacion de cada producto con sus respectivos ingredientes en la talba de ProductoIngrediente
     * @param idProducto
     * @param idIngrediente
     * @return boolean
     */
    
    public boolean agregarProductoIngrediente(String idProducto, String idIngrediente){
        
        Conexion bd= new Conexion();
        String sentencia = "INSERT INTO ProductoIngrediente (IdProducto, IdIngrediente) VALUES('"+ idProducto +"', '"+ idIngrediente+ "')";
        boolean resultado = bd.actualizarBD(sentencia);
        bd.cerrarConexion();
        
        return resultado;
    }
    
    /**
     * Elimina la relacion de un producto con cadaingrediente en la tabla de ProductoIngrediente
     * @param idProducto
     * @return boolean
     */
    public boolean eliminarProductoIngrediente(String idProducto){
        Conexion bd= new Conexion();
        String sentencia = "DELETE FROM ProductoIngrediente WHERE IdProducto = '"+idProducto+"'";
        boolean resultado = bd.actualizarBD(sentencia);
        bd.cerrarConexion();
        
        return resultado;
    }
    
    /**
     * Verifica la existencia de un producto por nombre y precio
     * @param nombre
     * @param precio
     * @return 
     */
    public boolean existeProducto(String nombre, double precio ){
        
        boolean existe = false;
        
        Conexion con = new Conexion();
        
        String sentencia= "SELECT * FROM Productos WHERE Nombre = '"+ nombre.trim() +"' and Precio = '" + precio + "'";
        
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

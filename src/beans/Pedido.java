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
public class Pedido {
    
    private String IdPedido;
    private String IdCliente;
    private String Mesa;
    private String Fecha;
    private String Estado;
    private String Observacion;


    public String getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(String IdPedido) {
        this.IdPedido = IdPedido;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getMesa() {
        return Mesa;
    }

    public void setMesa(String Mesa) {
        this.Mesa = Mesa;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
    public Pedido(String IdPedido, String IdCliente, String Mesa) {
        this.IdPedido = IdPedido;
        this.IdCliente = IdCliente;
        this.Mesa = Mesa;
        
    }

    public Pedido() {
    }
    
    /**Metodo para registar en BD un pedido
     * @return True/false si la operacion es exitosa o no
     **/
    public boolean CrearPedido(){
        
        Conexion con= new Conexion();
        String sentencia="INSERT INTO Pedidos (IdPedido,IdCliente,Mesa,Fecha,Estado) "
                + "VALUES ('"+ this.IdPedido +"','"+ this.IdCliente +"','"+ this.Mesa +"',DATE(NOW()),'Vigente') ";
        boolean exito = con.insertarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
    }
    
    /**Metodo para asociar a un pedido un producto en BD
     * @param idpedido Identificador del pedido
     * @param idproducto Identificador del producto
     * @param cantidad Numero de productos de un mismo tipo
     * @param observacion Parrafo que describe solicitudes para la elaboracion del producto
     * @return True/false si la operacion es exitosa o no
     **/
    public boolean AgregarProducto(String idpedido,String idproducto,String cantidad,String observacion){
        
        Conexion con= new Conexion();
        String sentencia= "INSERT INTO PedidoProducto (IdPedido,IdProducto,Cantidad,Observacion) "
                + "VALUES ( '"+ idpedido +"','"+ idproducto +"','"+ cantidad +"','"+ observacion +"')";
        boolean exito = con.insertarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
        
    }
    
    /**Metodo para asociar un adicional a un producto 
     * @param idpedido Identificador de pedido
     * @param idproducto Identificador de producto
     * @param idadicional Identificador de adicional
     * @return True/false si la operacion es exitosa o no
     **/
    public boolean AgregarAdicional(String idpedido,String idproducto,String idadicional){
        
        Conexion con= new Conexion();
        String sentencia= "INSERT PedidoProductoAdicional (IdPedido,IdProducto,IdAdicional) "
                + "VALUES ('"+ idpedido +"','"+ idproducto +"','"+ idadicional +"')";
        boolean exito = con.insertarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
    }
    
    /**Metodo que modifica el estado del pedido
     * @param id Identificador del pedido
     * @param estado Vigente o Finalizado segun sea el caso
     * @return 
     **/
    public boolean cambiarEstado(String id,String estado){
        
        Conexion con= new Conexion();
        String sentencia= "UPDATE Pedidos SET Estado='"+ estado +"' WHERE IdPedido='"+ id +"'";
        boolean exito = con.actualizarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
    }
    
    /**Metodo que consulta todos los pedidos activos en BD
     * @return Un array de pedidos activos
     **/
    public ArrayList<Pedido> consultarPedidosActivos(){
        
        Conexion con = new Conexion();
        String sentencia = "SELECT * FROM Pedidos WHERE  Estado='Vigente'";
        ResultSet consulta= con.consultarBD(sentencia);
        ArrayList<Pedido> pedidos= new ArrayList<>();
        try {
            while(consulta.next()){
                
                Pedido pd= new Pedido();
                pd.setIdPedido(consulta.getString("IdPedido"));
                pd.setIdCliente(consulta.getString("IdCliente"));
                pd.setMesa(consulta.getString("Mesa"));
                pd.setFecha(consulta.getString("Fecha"));
                pd.setEstado(consulta.getString("Estado"));
                
                pedidos.add(pd);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.cerrarConexion();
        }
        return pedidos;
    }
    
    /**Metodo que consulta los productos de un pedido
     * @param idpedido Identificador del pedido
     * @return Devuelve un resultset con todos los campos asociados a un producto
     **/
    public ResultSet consultarProductosPedido(String idpedido){
        
        Conexion con= new Conexion();
        String sentencia="SELECT pro.IdProducto,pro.Nombre,pro.Precio,pp.Cantidad,pp.Observacion FROM Productos pro, PedidoProducto pp "
                + "WHERE pp.IdPedido='"+ idpedido +"' AND pp.IdProducto=pro.IdProducto";
        ResultSet respuesta= con.consultarBD(sentencia);
        con.cerrarConexion();
        return respuesta;
        
    }
    
    /**Metodo para consultar los adicionales de un producto
     * @param idpedido Identficador de pedido
     * @param idproducto Identificador de producto
     * @return Devuelve en un resulset todos los campos asociados a un adicional
     **/
    public ResultSet consultarProductoAdicional(String idpedido,String idproducto){
        
        Conexion con= new Conexion();
        String sentencia="SELECT  adi.IdAdicional,ing.Nombre,adi.Precio FROM PedidoProductoAdicional ppa,Adicionales adi,Ingredientes ing "
                + "WHERE ppa.IdPedido='"+ idpedido +"' AND ppa.IdProducto='"+ idproducto +"' AND ppa.IdAdicional=adi.IdAdicional "
                + "AND adi.IdIngrediente=ing.IdIngrediente";
        ResultSet respuesta= con.consultarBD(sentencia);
        con.cerrarConexion();
        return respuesta;
    }
    
    /**Metodo que modifica los campos de caantidad y observacion de un producto
     * @param idpedido Identificador de pedido
     * @param idproducto Identificar de producto
     * @param cantidad Numero de productos
     * @param observacion Parrafo que describe solicitudes para la elaboracion del producto
     * @return True/false si la operacion es exitosa o no

     **/
    public boolean setProducto(String idpedido,String idproducto,int cantidad, String observacion ){
        
        Conexion con= new Conexion();
        String sentencia= "UPDATE PedidoProducto SET Cantidad='"+ cantidad +"',Observacion='"+ observacion +"' "
                + "WHERE IdPedido='"+ idpedido +"' AND IdProducto='"+ idproducto +"'";
        boolean exito= con.actualizarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
        
    }
    
    /**Metodo para eliminar un adicional de un producto 
     * @param idpedido Identificador de pedido
     * @param idproducto Identificador de producto
     * @param idadicional Identificador de adicional
     * @return True/false si la operacion es exitosa o no

     **/
    public boolean eliminarAdicional(String idpedido,String idproducto,String idadicional){
        
        Conexion con= new Conexion();
        String sentencia = "DELETE FROM PedidoProductoAdicional WHERE IdPedido='"+ idpedido +"' AND IdProducto='"+ idproducto +"' "
                + "AND IdAdicional='"+ idadicional +"'";
        boolean exito = con.actualizarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
        
    }
    
    /**Metodo para elimiar todos los adicionales de un pedido
     * @param idpedido Identificador de pedido
     * @param idproducto Identificador de producto
     * @return True/false si la operacion es exitosa o no
     **/
    public boolean eliminarAdicionales(String idpedido,String idproducto){
        
        Conexion con= new Conexion();
        String sentencia = "DELETE FROM PedidoProductoAdicional WHERE IdPedido='"+ idpedido +"' AND IdProducto='"+ idproducto +"' ";
               
        boolean exito = con.actualizarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
    }
    
    /**Elimina un producto de un pedido
     * @param idpedido
     * @param idproducto
     * @return 
     **/
    public boolean eliminarProducto(String idpedido,String idproducto){
        
        Conexion con= new Conexion();
        boolean exito = false;
        if(eliminarAdicionales(idpedido,idproducto)){
            String sentencia = "DELETE FROM PedidoProducto WHERE IdPedido='"+ idpedido +"' AND IdProducto='"+ idproducto +"' ";
            exito = con.actualizarBD(sentencia);
        }
        
        con.cerrarConexion();
        
        return exito;
    }
    /**Metodo para eliminar un pedido de la base de datos
     * @param idpedido Identificar de un pedido
     * @return 
     **/
    public boolean eliminarPedido(String idpedido){
        
        Conexion con= new Conexion();
        String sentencia="SELECT IdProducto FROM PedidoProducto WHERE IdPedido='"+ idpedido +"'";
        String sentencia2="DELETE FROM Pedidos WHERE IdPedido='"+ idpedido +"'";
        boolean exito= false;
        ResultSet productos= con.consultarBD(sentencia);
        
        try {
            while(productos.next()){
                
                eliminarProducto(idpedido,productos.getString("IdProducto"));
                
            }
            exito= con.actualizarBD(sentencia2);
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.cerrarConexion();
        }
        
        return exito;
    }
    
    
    
}

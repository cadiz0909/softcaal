/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.ResultSet;

/**
 *
 * @author alexy
 */
public class Factura {
    
    private String IdFactura;
    private String IdPedido;
    private String Fecha;
    private String Valor;

    public Factura() {
    }

    public Factura(String IdFactura, String IdPedido, String Valor) {
        this.IdFactura = IdFactura;
        this.IdPedido = IdPedido;
        this.Valor = Valor;
    }

    public String getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(String IdFactura) {
        this.IdFactura = IdFactura;
    }

    public String getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(String IdPedido) {
        this.IdPedido = IdPedido;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }
    
    /**
     * Crea una factura para un pedido realizado, devuelve true si se realizo la insercion correctamente.
     * @return boolean
     */
    public boolean crearFactura(){
        
        Conexion con= new Conexion();
        String sentencia = "INSERT INTO Factura (IdFactura, IdPedido,  Fecha, Valor) VALUES('" + this.IdFactura + "', '" + this.IdPedido + "','" + this.Fecha + "','" + this.Valor + "')";
        boolean exito = con.insertarBD(sentencia);
        con.cerrarConexion();
        
        return exito;
    }
    
    /**
     * Consulta todas las facturas por fecha. Devuelve todas las facturas de la fecha correspondiente.
     * @param fecha
     * @return ResultSet
     */
    public ResultSet consultarFacturaFecha(String fecha){
        
       Conexion con= new Conexion();
       String sentecia = "SELECT * FROM Factura WHERE Fecha = '" + fecha + "'";
       ResultSet consulta = con.consultarBD(sentecia);
       con.cerrarConexion();
       
       return consulta;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexy
 */
public class Gasto {
    
    private String idGasto;
    private String nombre;
    private String tipo;
    private Date fecha;
    private int valor;

    public Gasto() {
    }

    public Gasto(String idGasto, String nombre, String tipo, Date fecha, int valor) {
        this.idGasto = idGasto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.valor = valor;
    }

    public String getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(String idGasto) {
        this.idGasto = idGasto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    /**
     * Crea un nuevo gasto en BD
     * @return true, si se realizo 
     */
    public boolean crearGastro(){
        Conexion bd= new Conexion();
        
        boolean insertar = bd.insertarBD("INSERT INTO Gastos (IdGasto, Nombre, Tipo, Fecha, Valor) "
                + "VALUES('"+getIdGasto()+"', '"+getNombre()+"','"+getTipo()+"', '"+getFecha()+"', '"+getValor()+"')");
        bd.cerrarConexion();
        
        return false;
    }
    
    /**
     * Permite modificar el nombre y/o el valor de un gasto creado, realizando la busqueda del registro por medio 
     * del Id.
     * @param id
     * @param nombre
     * @param valor
     * @return true, si se realizo
     */
    public boolean modificarGasto(String id, String nombre, int valor){
        Conexion bd = new Conexion();
        boolean resultado;
        
        resultado = bd.actualizarBD("UPDATE Gastos SET Nombre = '"+nombre+"', Precio = '"+valor+"' WHERE IdProducto = '"+id+"'");
        bd.cerrarConexion();
        
        return resultado;
    }
    
    /**
     * Permite listar los gastos por tipo y fecha
     * @param tipo
     * @param fecha
     * @return ArrayList<Gato>
     */
    
    public ArrayList<Gasto> listarGastosTipo(String tipo, Date fecha){
        
        Conexion bd= new Conexion();
        ArrayList<Gasto> listaObjeto = new ArrayList<Gasto>();
        Gasto objeto;
        String id;
        String nomb;
        String tip;
        Date fech;
        int val;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Gastos WHERE Tipo = '"+tipo+"' AND Fecha = '"+fecha+"'");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdGasto");
                nomb = consulta.getString("Nombre");
                tip = consulta.getString("Tipo");
                fech = consulta.getDate("Fecha");
                val = consulta.getInt("Valor");
                
                objeto = new Gasto(id, nomb, tip, fech, val);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
    
    /**
     * Permite listar todos los gastos del día.
     * @param tipo
     * @param fecha
     * @return ArrayList<Gastos>
     */
    
    public ArrayList<Gasto> listarGastosFecha(String tipo, Date fecha){
        
        Conexion bd= new Conexion();
        ArrayList<Gasto> listaObjeto = new ArrayList<Gasto>();
        Gasto objeto;
        String id;
        String nomb;
        String tip;
        Date fech;
        int val;
        
        ResultSet consulta = bd.consultarBD("SELECT * FROM Gastos WHERE Fecha = '"+fecha+"'");
        
        try {
            while(consulta.next()){
                
                id = consulta.getString("IdGasto");
                nomb = consulta.getString("Nombre");
                tip = consulta.getString("Tipo");
                fech = consulta.getDate("Fecha");
                val = consulta.getInt("Valor");
                
                objeto = new Gasto(id, nomb, tip, fech, val);
                listaObjeto.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bd.cerrarConexion();
        
        return listaObjeto;
    }
}

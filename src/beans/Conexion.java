/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.*;

public class Conexion {

    // Configuracion de la conexion a la base de datos 
    private String DB_driver = "";
    private String url = "";
    private String username = "";
    private String password = "";
    public Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private String query = null;

    //Constructor sin parmetros		
    public Conexion() {

        DB_driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/YoguisBD";
        username = "root";
        password = "123456";
        //Asignacin del Driver
        try {
            Class.forName(DB_driver);
        } catch (ClassNotFoundException cnfx) {
            //System.out.println("No se pudo cargar el Driver Correctamente!");
        }
        // Realizar la conexin
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setTransactionIsolation(8);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //Retornar la conexin
    public Connection getConnection() {
        return con;
    }

    //Cerrar la conexin
    public void closeConnection(Connection con) {
         try {
            if (con != null) {
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * metodo para hacer consultas a la BD
     *
     * @param sentencia sentencia SQL
     * @return
     */
    public ResultSet consultarBD(String sentencia) {
       try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sentencia);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } catch (RuntimeException rex) {
            rex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rs;
    }

    // Mtodo que realiza un INSERT y devuelve TRUE si la operacin fue existosa
    public boolean insertarBD(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException sqlex) {
            //System.out.println("ERROR RUTINA: "+ sqlex);
            return false;
        } catch (RuntimeException rex) {
            //System.out.println("ERROR RUTINA: "+ rex);
            return false;
        } catch (Exception ex) {
            //System.out.println("EXCEPCION: "+ ex);
            return false;
        }
        return true;
    }

    // MÃ©todo que realiza una operacin como UPDATE, DELETE, CREATE TABLE, entre otras
    // y devuelve TRUE si la operacin fue existosa
    public boolean actualizarBD(String sentencia) {
         try {
            stmt = con.createStatement();
            stmt.executeUpdate(sentencia);
        } catch (SQLException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        } catch (RuntimeException rex) {
            System.out.println("ERROR RUTINA: " + rex);
            return false;
        } catch (Exception ex) {
            System.out.println("EXCEPCION: " + ex);
            return false;
        }
        return true;
    }

    public boolean setAutoCommitBD(boolean parametro) {
        try {
            con.setAutoCommit(parametro);
        } catch (SQLException sqlex) {
            System.out.println("Error al configurar el autoCommit " + sqlex.getMessage());
            return false;
        }
        return true;
    }

    public void cerrarConexion() {
        closeConnection(con);
    }

    public boolean commitBD() {
        try {
            con.commit();
            return true;
        } catch (SQLException sqlex) {
            //System.out.println("Error al hacer commit "+sqlex.getMessage());
            return false;
        }
    }

    public boolean rollbackBD() {
        try {
            con.rollback();
            return true;
        } catch (SQLException sqlex) {
            //System.out.println("Error al hacer rollback "+sqlex.getMessage());
            return false;
        }
    }

}


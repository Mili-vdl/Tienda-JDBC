/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author milip
 */
public abstract class DAO {

    protected Connection conexion; // para usar el getConnection que crea la conexion
    protected ResultSet resultado; // almacena el resultado de la query
    protected Statement sentencia; // objeto Statement para ejecutar una query o modificacion (update)
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    protected void conectarBase() throws Exception {
        try {
            Class.forName(DRIVER);
            String urlBasedeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";

            // 1. crear conexion
            conexion = DriverManager.getConnection(urlBasedeDatos, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    protected void desconectarBase() throws SQLException { // release resourses
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public void consultarBase(String query) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();

            resultado = sentencia.executeQuery(query);

        } catch (Exception e) {
            throw e;
        }
    }

    public void mostrarQuery(int cantColum) throws SQLException {
        while (this.resultado.next()) {
            for (int i = 1; i <= cantColum; i++) {
                System.out.print(resultado.getString(i) + "\t |");
            }
            System.out.println("");
        }
        System.out.println("");
        desconectarBase();
    }

    protected void ime(String sql) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql); // ejecuta lo que sea insertar, modificar, eliminar
        } catch (SQLException ex) {
            throw ex;
        } finally {
            desconectarBase();
        }
    }

}

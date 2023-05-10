/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author user
 */
public class ConexionBD {
    public static String Url="jdbc:mysql://127.0.0.1/login_bd";
public static String usuarios ="root";
public static String contraseña = "haloxbox306";


public static Connection conectar(){
    Connection conexion = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion= DriverManager.getConnection(Url, usuarios, contraseña);
        System.out.println("Conexion establecida mensaje en consola..");   
    } catch (HeadlessException | ClassNotFoundException | SQLException e){
        System.out.println("Error: "+ e);
}
    return conexion;
}
public static void desconectar (){
    Connection conexion = null;
    if (conexion ==null){
        System.out.println("La variable de conexion an sido puesta en null");
    }
}
}

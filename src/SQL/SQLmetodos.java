/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import frmloginejemplo.frmMenuAdministradores;
import frmloginejemplo.frmMenuEmpleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class SQLmetodos {
    Connection conexion;
    PreparedStatement sentencia_preparada;
    ResultSet resultado;
    String sql;
    frmMenuAdministradores formularioMenu = new frmMenuAdministradores();
    frmMenuEmpleado formularioEmpleado = new frmMenuEmpleado();
    public String contraseña;
    public void buscarUsuarioRegistrado(String usuarios, String contraseña){
        String tipo_permiso_usuario;
        try{
        conexion= ConexionBD.conectar();
                //sql = "SELECT  usuarios,contraseña FROM usuarios_registrados WHERE usuarios = '"+usuarios+"' && contraseña ='"+contraseña+"'";

        sql = "SELECT  * FROM usuarios_registrados WHERE usuario = '"+usuarios+"' && contraseña ='"+contraseña+"'";
        sentencia_preparada = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia_preparada.executeQuery();
        if(resultado.next()){
        usuarios = resultado.getString("usuario"); 
        contraseña = resultado.getString("contraseña");
        
        if(usuarios != null && contraseña !=null){
        tipo_permiso_usuario= resultado.getString("permisos");
        switch(tipo_permiso_usuario){
            case   "Administrador":
            formularioMenu.setVisible(true);
            break;
            case   "Empleado":
            formularioEmpleado.setVisible(true);
            break;
        }
        }
        //formularioMenu.setVisible(true);
        
        }else{
        JOptionPane.showMessageDialog(null,"Error en el usuario o contraseña");
        }
        conexion.close();
        ConexionBD.desconectar();
        }catch (Exception e){
            System.out.println("Error:"+e);
    }
}
}

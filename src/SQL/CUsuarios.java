/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

/**
 *
 * @author user
 */


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class CUsuarios {

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
  int codigo;
    String usuario;
    String contraseña;
       String permisos;

     public void InsertarUsuario(JTextField paramUsuario, JTextField paramContraseña,JTextField paramPermisos){
         
        setUsuario(paramUsuario.getText());
         setContraseña(paramContraseña.getText());
         setPermisos(paramPermisos.getText());
         ConexionBD objetoConexion= new ConexionBD();
         String consulta="insert into usuarios_registrados (usuario,contraseña,permisos) values (?,?,?);";
         try {
         CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
         
         cs.setString(1,getUsuario());
         cs.setString(2, getContraseña());
            cs.setString(3, getPermisos());
         
         
         cs.execute();
         
         
         JOptionPane.showMessageDialog(null,"Se inserto correctamente el usuario");
         }catch (SQLException e){
               JOptionPane.showMessageDialog(null,"No se inserto correctamente el usuario, error: "+ e.toString());
         }
     }
     
     public void MostrarUsuario(JTable paramTablaVentas){
     ConexionBD objetcConexion = new ConexionBD();
     DefaultTableModel modelo = new DefaultTableModel();
     
     TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
   paramTablaVentas.setRowSorter(OrdenarTabla);
   String sql= "";
   modelo.addColumn("id_usuario");
   modelo.addColumn("usuario");
   modelo.addColumn("contraseña");
    modelo.addColumn("permisos");
   paramTablaVentas.setModel(modelo);
   sql= "Select * from usuarios_registrados;";
   
   String[] datos = new String[4];
   Statement st;
   try{
       st = objetcConexion.conectar().createStatement();
       ResultSet rs = st.executeQuery(sql);
       while (rs.next()){
           datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
             datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
             modelo.addRow(datos);
   }
       paramTablaVentas.setModel(modelo);
    
   }catch (SQLException e){
          JOptionPane.showMessageDialog(null, "No se pudo mostrar Usuarios");
   }
     }
     public void SeleccionarUsuario(JTable paramTablaVentas,JTextField paramId, JTextField paramUsuario, JTextField paramContraseña,JTextField paramPermisos){
         try{
             int fila = paramTablaVentas.getSelectedRow();
             if(fila>=0){
                              paramId.setText((paramTablaVentas.getValueAt(fila,0).toString()));
             paramUsuario.setText((paramTablaVentas.getValueAt(fila,1).toString()));
              paramContraseña.setText((paramTablaVentas.getValueAt(fila,2).toString()));
               paramPermisos.setText((paramTablaVentas.getValueAt(fila,3).toString()));
             }else{
             JOptionPane.showMessageDialog(null, "Fila no seleccionada");
             }
         }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
         }
     }
     public void ModificarUsuario(JTextField paramCodigo,  JTextField paramUsuario, JTextField paramContraseña,JTextField paramPermisos){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         setUsuario(paramUsuario.getText());
            setContraseña(paramContraseña.getText());
                        setPermisos(paramPermisos.getText());

            
            ConexionBD objetoConexion = new ConexionBD();
            String sql= "UPDATE usuarios_registrados SET usuarios_registrados.usuario = ? , usuarios_registrados.contraseña = ?,usuarios_registrados.permisos = ? WHERE usuarios_registrados.id_usuario = ?;";
           try{
                 CallableStatement cs = objetoConexion.conectar().prepareCall(sql);
                 cs.setString(1,getUsuario());
                 cs.setString(2,getContraseña());
                     cs.setString(3,getPermisos());
                 cs.setInt(4,getCodigo());
                 cs.execute();
                 JOptionPane.showMessageDialog(null, "Modificación Exitosa");
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "Error de modificación, error: "+e.toString());
           }
     }
     public void EliminarUsuario(JTextField paramCodigo){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         ConexionBD objetoConexion= new ConexionBD();
          String sql= "DELETE FROM usuarios_registrados WHERE usuarios_registrados.id_usuario=?;";
          try{
          CallableStatement cs = objetoConexion.conectar().prepareCall(sql); 
          cs.setInt(1,getCodigo());
          cs.execute();
          JOptionPane.showMessageDialog(null, "Eliminación Exitosa");
          }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Error de eliminación, error: "+e.toString());
          }
     }
}

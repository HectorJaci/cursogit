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
public class CproductosEmpleados {
     int codigo;
    String producto;
    String costo;
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

     
     public void MostrarProductos(JTable paramTablaProductos){
     ConexionBD objetcConexion = new ConexionBD();
     DefaultTableModel modelo = new DefaultTableModel();
     
     TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
   paramTablaProductos.setRowSorter(OrdenarTabla);
   String sql= "";
   modelo.addColumn("id");
   modelo.addColumn("producto");
   modelo.addColumn("costo");
   paramTablaProductos.setModel(modelo);
   sql= "Select * from Productos;";
   
   String[] datos = new String[3];
   Statement st;
   try{
       st = objetcConexion.conectar().createStatement();
       ResultSet rs = st.executeQuery(sql);
       while (rs.next()){
           datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
             datos[2] = rs.getString(3);
             modelo.addRow(datos);
   }
       paramTablaProductos.setModel(modelo);
    
   }catch (SQLException e){
          JOptionPane.showMessageDialog(null, "No se pudo mostrar Productos");
   }
     }
}

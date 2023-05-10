/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

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
public class CProductos {
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


     public void InsertarProducto(JTextField paramProducto, JTextField paramCosto){
         
        setProducto(paramProducto.getText());
         setCosto(paramCosto.getText());
         
         ConexionBD objetoConexion= new ConexionBD();
         String consulta="insert into Productos (producto,costo) values (?,?);";
         try {
         CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
         
         cs.setString(1,getProducto());
         cs.setString(2, getCosto());
         
         
         
         cs.execute();
         
         
         JOptionPane.showMessageDialog(null,"Se inserto correctamente el producto");
         }catch (SQLException e){
               JOptionPane.showMessageDialog(null,"No se inserto correctamente el producto, error: "+ e.toString());
         }
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
     public void SeleccionarProducto(JTable paramTablaProducto, JTextField paramId, JTextField paramProducto, JTextField paramCosto){
         try{
             int fila = paramTablaProducto.getSelectedRow();
             if(fila>=0){
             paramId.setText((paramTablaProducto.getValueAt(fila,0).toString()));
              paramProducto.setText((paramTablaProducto.getValueAt(fila,1).toString()));
               paramCosto.setText((paramTablaProducto.getValueAt(fila,2).toString()));
             }else{
             JOptionPane.showMessageDialog(null, "Fila no seleccionada");
             }
         }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
         }
     }
     public void ModificarProductos(JTextField paramCodigo, JTextField paramProducto, JTextField paramCosto){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         setProducto(paramProducto.getText());
            setCosto(paramCosto.getText());
            ConexionBD objetoConexion = new ConexionBD();
            String sql= "UPDATE Productos SET productos.producto = ?, productos.costo = ? WHERE productos.id = ?;";
           try{
                 CallableStatement cs = objetoConexion.conectar().prepareCall(sql);
                 cs.setString(1,getProducto());
                 cs.setString(2,getCosto());
                 cs.setInt(3,getCodigo());
                 cs.execute();
                 JOptionPane.showMessageDialog(null, "Modificación Exitosa");
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "Error de modificación, error: "+e.toString());
           }
     }
     public void EliminarProductos(JTextField paramCodigo){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         ConexionBD objetoConexion= new ConexionBD();
          String sql= "DELETE FROM Productos WHERE Productos.id=?;";
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

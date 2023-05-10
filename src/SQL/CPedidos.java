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
public class CPedidos {

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

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

   

  


  int codigo;
    String producto;
    String costo;
  String cel;
  String apellidos;
  String id;
  String nombre;
  String cantidad;

  

     public void InsertarPedido(JTextField paramProducto,JTextField paramCosto, JTextField paramCel, JTextField paramApellidos, JTextField paramId, JTextField paramNombre, JTextField paraCantidad){
         
        setProducto(paramProducto.getText());
         setCosto(paramCosto.getText());
          setCel(paramCel.getText());
           setApellidos(paramApellidos.getText());
            setId(paramId.getText());
             setNombre(paramNombre.getText());
             setCantidad(paraCantidad.getText());
             
         
         ConexionBD objetoConexion= new ConexionBD();
         String consulta="insert into pedidos (producto,nombre,apellidos,cantidad,cel,costo,id) values (?,?,?,?,?,?,?);";
         
         try {
         CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
         
         cs.setString(1,getProducto());
         cs.setString(2, getCosto());
           cs.setString(3, getNombre());
          cs.setString(4, getApellidos());
          cs.setString(5, getCantidad());
           cs.setString(6, getCel());
            cs.setString(7, getId());
            
         cs.execute();
         
         
         JOptionPane.showMessageDialog(null,"Se inserto correctamente el pedido");
         }catch (SQLException e){
               JOptionPane.showMessageDialog(null,"No se inserto correctamente el pedido, error: "+ e.toString());
         }
     }
     
     public void MostrarPedidos(JTable paramTablaPedidos){
     ConexionBD objetcConexion = new ConexionBD();
     DefaultTableModel modelo = new DefaultTableModel();
     
     TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
   paramTablaPedidos.setRowSorter(OrdenarTabla);
   String sql= "";
         modelo.addColumn("id_cliente");

      modelo.addColumn("producto");
         modelo.addColumn("nombre");
            modelo.addColumn("apellidos");
               modelo.addColumn("cantidad");
       
   modelo.addColumn("cel");
   modelo.addColumn("costo");
   modelo.addColumn("id");
   paramTablaPedidos.setModel(modelo);
   sql= "Select * from pedidos;";
   
   String[] datos = new String[8];
   Statement st;
   try{
       st = objetcConexion.conectar().createStatement();
       ResultSet rs = st.executeQuery(sql);
       while (rs.next()){
           datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
             datos[2] = rs.getString(3);
              datos[3] = rs.getString(4);
            datos[4] = rs.getString(5);
             datos[5] = rs.getString(6);
              datos[6] = rs.getString(7);
                        datos[7] = rs.getString(8);

             modelo.addRow(datos);
   }
       paramTablaPedidos.setModel(modelo);
    
   }catch (SQLException e){
          JOptionPane.showMessageDialog(null, "No se pudo mostrar pedidos");
   }
     }
       public void SeleccionarPedidos(JTable paramTablaPedidos,JTextField paramIdCliente, JTextField paramProducto,JTextField paramCosto, JTextField paramCel, JTextField paramApellidos, JTextField paramId, JTextField paramNombre, JTextField paraCantidad){
         try{
             int fila = paramTablaPedidos.getSelectedRow();
             if(fila>=0){
             paramIdCliente.setText((paramTablaPedidos.getValueAt(fila,0).toString()));
              paramProducto.setText((paramTablaPedidos.getValueAt(fila,1).toString()));
               paramCosto.setText((paramTablaPedidos.getValueAt(fila,2).toString()));
                      paramCel.setText((paramTablaPedidos.getValueAt(fila,3).toString()));
                             paramApellidos.setText((paramTablaPedidos.getValueAt(fila,4).toString()));
                                    paramId.setText((paramTablaPedidos.getValueAt(fila,5).toString()));
                                           paramNombre.setText((paramTablaPedidos.getValueAt(fila,6).toString()));
                             paraCantidad.setText((paramTablaPedidos.getValueAt(fila,7).toString()));                     
                                           
             }else{
             JOptionPane.showMessageDialog(null, "Fila no seleccionada");
             }
         }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
         }
     }
     public void ModificarPedidos(JTextField paramCodigo, JTextField paramProducto,JTextField paramCosto, JTextField paramCel, JTextField paramApellidos, JTextField paramId, JTextField paramNombre, JTextField paraCantidad){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         setProducto(paramProducto.getText());
            setCosto(paramCosto.getText());
            setCel(paramCel.getText());
            setApellidos(paramApellidos.getText());
            setId(paramId.getText());
            setNombre(paramNombre.getText());
            setCantidad(paraCantidad.getText());
            
            ConexionBD objetoConexion = new ConexionBD();
            String sql= "UPDATE pedidos SET pedidos.producto = ? , pedidos.nombre = ?,pedidos.apellidos = ?,pedidos.cantidad = ?,pedidos.cel = ?,pedidos.costo = ? ,pedidos.id = ? WHERE pedidos.id_cliente = ?;";
           try{
                 CallableStatement cs = objetoConexion.conectar().prepareCall(sql);
                 cs.setString(1,getProducto());
                 cs.setString(2,getCosto());
                  cs.setString(3,getCel());
                   cs.setString(4,getApellidos());
                    cs.setString(5,getId());
                     cs.setString(6,getNombre());
                      cs.setString(7,getCantidad());
                      
                 cs.setInt(8,getCodigo());
                 cs.execute();
                 JOptionPane.showMessageDialog(null, "Modificación Exitosa");
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "Error de modificación, error: "+e.toString());
           }
     }
     public void EliminarPedidos(JTextField paramCodigo){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         ConexionBD objetoConexion= new ConexionBD();
          String sql= "DELETE FROM pedidos WHERE pedidos.id_cliente=?;";
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

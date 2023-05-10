
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
public class CVentas {

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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   

  


  int codigo;
    String producto;
    String cantidad;
  String precio;
  String id;
     public void InsertarVenta(JTextField paramProducto,JTextField paraCantidad,JTextField paramPrecio, JTextField paramId){
         
        setProducto(paramProducto.getText());
         setCantidad(paraCantidad.getText());
          setPrecio(paramPrecio.getText());
               setId(paramId.getText());

              
         
         ConexionBD objetoConexion= new ConexionBD();
         String consulta="insert into Ventas (producto,cantidad,precio,id) values (?,?,?,?);";
         
         try {
         CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
         
         cs.setString(1,getProducto());
         
          cs.setString(2, getCantidad());
           cs.setString(3, getPrecio());
            cs.setString(4, getId());
            
         cs.execute();
         
         
         JOptionPane.showMessageDialog(null,"Se inserto correctamente la venta");
         }catch (SQLException e){
               JOptionPane.showMessageDialog(null,"No se inserto correctamente la venta, error: "+ e.toString());
         }
     }
     
     public void MostrarVentas(JTable paramTablaVentas){
     ConexionBD objetcConexion = new ConexionBD();
     DefaultTableModel modelo = new DefaultTableModel();
     
     TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
   paramTablaVentas.setRowSorter(OrdenarTabla);
   String sql= "";
         modelo.addColumn("id_venta");

      modelo.addColumn("producto");
               modelo.addColumn("cantidad");
                 modelo.addColumn("precio");
   modelo.addColumn("id");
   paramTablaVentas.setModel(modelo);
   sql= "Select * from ventas;";
   
   String[] datos = new String[5];
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

             modelo.addRow(datos);
   }
       paramTablaVentas.setModel(modelo);
    
   }catch (SQLException e){
          JOptionPane.showMessageDialog(null, "No se pudo mostrar ventas");
   }
     }
       public void SeleccionarVentas(JTable paramTablaVentas,JTextField paramIdVentas,JTextField paramProducto,JTextField paraCantidad,JTextField paramPrecio, JTextField paramId){
         try{
             int fila = paramTablaVentas.getSelectedRow();
             if(fila>=0){
             paramIdVentas.setText((paramTablaVentas.getValueAt(fila,0).toString()));
              paramProducto.setText((paramTablaVentas.getValueAt(fila,1).toString()));
               paraCantidad.setText((paramTablaVentas.getValueAt(fila,2).toString()));
                      paramPrecio.setText((paramTablaVentas.getValueAt(fila,3).toString()));
                             paramId.setText((paramTablaVentas.getValueAt(fila,4).toString()));          
                                           
             }else{
             JOptionPane.showMessageDialog(null, "Fila no seleccionada");
             }
         }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
         }
     }
     public void ModificarVentas(JTextField paramCodigo,JTextField paramProducto,JTextField paraCantidad,JTextField paramPrecio, JTextField paramId){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         setProducto(paramProducto.getText());
            setId(paramId.getText());
              setPrecio(paramPrecio.getText());
            setCantidad(paraCantidad.getText());
            
            ConexionBD objetoConexion = new ConexionBD();
            String sql= "UPDATE ventas SET ventas.producto = ? , ventas.cantidad = ?,ventas.precio = ?,ventas.id = ?  WHERE ventas.id_venta = ?;";
           try{
                 CallableStatement cs = objetoConexion.conectar().prepareCall(sql);
                 cs.setString(1,getProducto());
                    cs.setString(2,getId());
                      cs.setString(3,getPrecio());
                      cs.setString(4,getCantidad());
                      
                 cs.setInt(5,getCodigo());
                 cs.execute();
                 JOptionPane.showMessageDialog(null, "Modificación Exitosa");
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "Error de modificación, error: "+e.toString());
           }
     }
     public void EliminarVentas(JTextField paramCodigo){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         ConexionBD objetoConexion= new ConexionBD();
          String sql= "DELETE FROM ventas WHERE ventas.id_venta=?;";
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

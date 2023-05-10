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
public class CFactura {
int codigo;
    String RFC;
    String fecha;
    String id;
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
  
   


     public void InsertarFacturas(JTextField paramRFC, JTextField paramFecha,JTextField paramId ){
         
        setRFC(paramRFC.getText());
         setFecha(paramFecha.getText());
             setId(paramId.getText());
         ConexionBD objetoConexion= new ConexionBD();
         String consulta="insert into factura (RFC,id_ventas,fecha) values (?,?,?);";
         try {
         CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
         
         cs.setString(1,getRFC());
         cs.setString(2, getFecha());
                  cs.setString(3, getId());
         
         cs.execute();
         
         
         JOptionPane.showMessageDialog(null,"Se inserto correctamente la factura");
         }catch (SQLException e){
               JOptionPane.showMessageDialog(null,"No se inserto correctamente la factura, error: "+ e.toString());
         }
     }
     
     public void MostrarFacturas(JTable paramTablaFacturas){
     ConexionBD objetcConexion = new ConexionBD();
     DefaultTableModel modelo = new DefaultTableModel();
     
     TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
   paramTablaFacturas.setRowSorter(OrdenarTabla);
   String sql= "";
   modelo.addColumn("id_factura");
   modelo.addColumn("RFC");
   modelo.addColumn("id_ventas");
    modelo.addColumn("fecha");
   paramTablaFacturas.setModel(modelo);
   sql= "Select * from factura;";
   
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
       paramTablaFacturas.setModel(modelo);
    
   }catch (SQLException e){
          JOptionPane.showMessageDialog(null, "No se pudo mostrar Facturas");
   }
     }
     public void SeleccionarFacturas(JTable paramTablaFactura,JTextField paramIdFacturas,JTextField paramRFC,JTextField paraFecha, JTextField paramId){
         try{
             int fila = paramTablaFactura.getSelectedRow();
             if(fila>=0){
             paramIdFacturas.setText((paramTablaFactura.getValueAt(fila,0).toString()));
              paramRFC.setText((paramTablaFactura.getValueAt(fila,1).toString()));
               paraFecha.setText((paramTablaFactura.getValueAt(fila,2).toString()));
                      paramId.setText((paramTablaFactura.getValueAt(fila,3).toString()));
                                
                                           
             }else{
             JOptionPane.showMessageDialog(null, "Fila no seleccionada");
             }
         }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
         }
     }
     public void ModificarFacturas(JTextField paramCodigo,JTextField paramRFC, JTextField paramFecha,JTextField paramId){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         setRFC(paramRFC.getText());
            setFecha(paramFecha.getText());
                      setId(paramId.getText());
            ConexionBD objetoConexion = new ConexionBD();
            String sql= "UPDATE factura SET factura.RFC = ? , factura.id_ventas = ?,factura.fecha = ?  WHERE factura.id_factura = ?;";
           try{
                 CallableStatement cs = objetoConexion.conectar().prepareCall(sql);
                 cs.setString(1,getRFC());
                 cs.setString(2,getFecha());
                 cs.setString(3,getId());
                 cs.setInt(4,getCodigo());
                 cs.execute();
                 JOptionPane.showMessageDialog(null, "Modificación Exitosa");
           }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "Error de modificación, error: "+e.toString());
           }
     }
     public void EliminarFacturas(JTextField paramCodigo){
         setCodigo(Integer.parseInt(paramCodigo.getText()));
         ConexionBD objetoConexion= new ConexionBD();
          String sql= "DELETE FROM factura WHERE factura.id_factura=?;";
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


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.persistencia;

import app.modelo.Cliente;
import app.modelo.DetallePedido;
import app.modelo.Pedido;
import app.modelo.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ploo
 */
public class TiendaDAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void abrirConexion() {
        try {
            /* Grave:   Loading class `com.mysql.jdbc.Driver'. This is deprecated.
            The new driver class is `com.mysql.cj.jdbc.Driver'. 
            The driver is automatically registered via the SPI and manual loading
            of the driver class is generally unnecessary.
            */
            Class.forName("com.mysql.jdbc.Driver");
            // Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Tienda?useSSL=false", "root", "carlossanjuanmartin");
        } catch (SQLException ex) {
            System.err.println("ERROR: abrir la conexion BBDD: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: cerrar la conexion BBDD: " + ex.getMessage());
        }
    }

////////////////////  P R O D U C T O S  /////////////////
    
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList();
        Statement st;
        ResultSet rs;
        String sql;

        try {
            abrirConexion();
            st = conexion.createStatement();
            sql = "Select * from Producto";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("DESCRIPCION"),
                        rs.getDouble("PRECIO"),
                        rs.getString("imagen")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("ERROR: listar todos los productos: " + ex.getMessage());
        } finally {
            cerrarConexion();
        }
        return lista;
    } // fin listarProductos()

    public Producto buscarProducto(int prodId) {
        Producto resultado = new Producto();
        PreparedStatement st;
        ResultSet rs;
        String sql;

        abrirConexion();
        try {
            sql = "Select * from Producto where id_producto =?";
            st = conexion.prepareStatement(sql);
            st.setInt(1, prodId);
            rs = st.executeQuery();
            if (rs.next()) {
                resultado = new Producto(rs.getInt("id_producto"),
                        rs.getString("DESCRIPCION"),
                        rs.getDouble("PRECIO"),
                        rs.getString("imagen"));
            }
        } catch (SQLException ex) {
            System.err.println("ERROR: buscar un producto: " + ex.getMessage());
        } finally {
            cerrarConexion();
        }
        return resultado;
    }  // fin buscarProducto(int prodId) 

////////////////////  C L I E N T E S   /////////////////   
    
    public int crearCliente(Cliente c) {
        PreparedStatement st;
        String sql;
        int resultado = 0;

        abrirConexion();
        try {
            sql = "Insert into Cliente values (?,?,?,?,?,?,?,?)";
            st = conexion.prepareStatement(sql);

            st.setString(1, c.getUsuario());
            st.setString(2, c.getPassword());
            st.setString(3, c.getNombre());
            st.setString(4, c.getDireccion());
            st.setString(5, c.getPoblacion());
            st.setString(6, c.getComunidad());
            st.setString(7, c.getCp());
            st.setString(8, c.getDni());

            resultado = st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al crear el Statement");
        } finally {
            cerrarConexion();
        }
        return resultado;
    } // fin crearCliente(Cliente c)

    public Cliente buscarCliente(String user) {
        Cliente resultado = new Cliente();
        PreparedStatement st;
        ResultSet rs;
        String sql;

        abrirConexion();
        try {
            sql = "Select * from Cliente where usuario =?";
            st = conexion.prepareStatement(sql);
            st.setString(1, user);
            rs = st.executeQuery();
            if (rs.next()) {
                resultado = new Cliente(
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("poblacion"),
                        rs.getString("comunidad"),
                        rs.getString("cp"),
                        rs.getString("dni")
                );
            }
        } catch (SQLException ex) {
            System.err.println("ERROR: buscar un producto: " + ex.getMessage());
        } finally {
            cerrarConexion();
        }
        return resultado;
    }  // fin buscarCliente(String user) {

////////////////////  P E D I D O S   /////////////////  
    
    public int crearPedido(Pedido p) {
        PreparedStatement st;
        String sql;
        int resultado = 0;
        
        abrirConexion();
        try {
            sql = "Insert into Pedido values (?,?,?,?)";
            st = getConexion().prepareStatement(sql);

            st.setInt(1, 0);
            st.setString(2, p.getUsuario());
            st.setString(3, p.getPago());
            st.setString(4, p.getEnvio());
            
            resultado = st.executeUpdate();
            ////// buscamos el nro de pedido mayor = el que acabamos de crear
            PreparedStatement pst1 = conexion.prepareStatement("select max(id_pedido) from Pedido");
            ResultSet rs = pst1.executeQuery();
            int numP = 0;
            if (rs.next()) {
                numP = rs.getInt(1);
            }
            ///////////
            resultado=numP;
        } catch (SQLException ex) {
            System.err.println("Error al crear el Statement de Pedidos: " + ex.getMessage());
        } finally {
            cerrarConexion();
        }
        return resultado;
    } // fin crearPedido(Pedido p) 
    
    public int crearDetalle(DetallePedido d) {
        PreparedStatement st;
        String sql;
        int resultado = 0;

        abrirConexion();
        try {
            sql = "Insert into DetallePedido values (?,?,?,?,?)";
            st = getConexion().prepareStatement(sql);

            st.setInt(1, d.getId_pedido());
            st.setInt(2, d.getId_item());
            st.setInt(3, d.getId_producto());
            st.setString(4, d.getDescripcion());
            st.setDouble(5, d.getPrecio());

            resultado = st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al crear el Statement");
        } finally {
            cerrarConexion();
        }
        return resultado;
    } // fin crearDetalle(DetallePedido p)
   
    public Pedido buscarPedido(int pedido) {
        Pedido resultado = new Pedido();
        PreparedStatement st;
        ResultSet rs;
        String sql;

        abrirConexion();
        try {
            sql = "Select * from Pedido where ID_pedido =?";
            st = conexion.prepareStatement(sql);
            st.setInt(1, pedido);
            rs = st.executeQuery();
            if (rs.next()) {
                resultado = new Pedido(
                        rs.getInt("ID_pedido"),
                        rs.getString("usuario"),
                        rs.getString("formaPago"),
                        rs.getString("envio")
                );
            }
        } catch (SQLException ex) {
            System.err.println("ERROR: buscar un pedido: " + ex.getMessage());
        } finally {
            cerrarConexion();
        }
        return resultado;
    }  // fin buscarCliente(String user) {
    
    public List<Producto> buscarDetalle(int pedido) {
        List<Producto> lista = new ArrayList();
        PreparedStatement st;
        ResultSet rs;
        String sql;
        
        abrirConexion();
        try {           
            sql = "Select * from DetallePedido where ID_pedido =?";
            st = conexion.prepareStatement(sql);
            st.setInt(1, pedido);
            rs = st.executeQuery();
            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio")
                        //rs.getString("imagen")
                ));
            }
// Producto(int id_producto, String descripcion, double precio, String imagen)         
// DetallePedido(int id_pedido, int id_item, int id_producto, String Descripcion, double precio)
        } catch (SQLException ex) {
            System.err.println("ERROR: buscar detalle pedidos: " + ex.getMessage());
        } finally {
            cerrarConexion();
        }
        return lista;
    } // fin buscarDetalle(int pedido)
    
} // fin class


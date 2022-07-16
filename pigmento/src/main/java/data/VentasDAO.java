package data;

import static data.Conexion.*;
import java.sql.*;
import java.util.*;
import model.*;

public class VentasDAO {
    private static final String SQL_CREATE="INSERT INTO productos(codigo, descripcion, tipo, cantidad, precio, ) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_READ="SELECT * FROM productos";
    private static final String SQL_READ_BY_ID= "SELECT * FROM productos WHERE idproductos= ?";
    private static final String SQL_UPDATE_PRECIO="UPDATE productos SET precio = ? WHERE idproductos = ?";
    private static final String SQL_UPDATE_CANTIDAD="UPDATE productos SET cantidad = ? WHERE idproductos = ?";
    private static final String SQL_UPDATE="UPDATE productos SET codigo = ?, descripcion = ?, tipo = ?, cantidad = ?,precio = ? WHERE idproductos = ?";
    private static final String SQL_DELETE="DELETE FROM productos WHERE idproductos = ?";
    
    
    public List<Ventas> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ventas producto ;
        List<Ventas> productos = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int idproductos = rs.getInt(1);
                int codigo = rs.getInt(2);
                String descripcion = rs.getString(3);
                String tipo = rs.getString(4);
                int cantidad = rs.getInt(5);                
                double precio = rs.getDouble(6);

                producto =  new Ventas(idproductos, codigo, descripcion,tipo,cantidad,precio);

                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return productos;
    }
    
    public Ventas findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ventas producto = null;
        
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                int idproductos = rs.getInt(1);
                int codigo = rs.getInt(2);
                String descripcion = rs.getString(3);
                String tipo = rs.getString(4);
                int cantidad = rs.getInt(5);
                double precio = rs.getDouble(6);

                producto = new Ventas(idproductos, codigo,descripcion,tipo,cantidad,precio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return producto;
    }
    
    
    
    public int insert(Ventas producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_CREATE);
            stmt.setInt(1, producto.getCodigo());
            stmt.setString(2, producto.getDescripcion());
            stmt.setString(3, producto.getTipo());
            stmt.setInt(4, producto.getCantidad());
            stmt.setDouble(5, producto.getPrecio());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int updatePrecio(Ventas producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_PRECIO);
            stmt.setDouble(1, producto.getPrecio());
            stmt.setInt(2, producto.getIdproductos());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int updateCantidad(Ventas producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_CANTIDAD);
            stmt.setInt(1, producto.getCantidad());
            stmt.setInt(2, producto.getIdproductos());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int update(Ventas producto){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, producto.getCodigo());
            stmt.setString(2, producto.getDescripcion());
            stmt.setString(3, producto.getTipo());
            stmt.setInt(4, producto.getCantidad());
            stmt.setDouble(5, producto.getPrecio());
            stmt.setInt(6, producto.getIdproductos());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int deleteProducto(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}

package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;

public class Conexion {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/ventas?useSSL=false&useTimeZone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USR ="root";
    private static final String JDBC_PASS ="Joha2208";
    
    public static Connection getConexion() throws SQLException{
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace(System.out);
//        }
        return DriverManager.getConnection(JDBC_URL,JDBC_USR,JDBC_PASS);
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement st) throws SQLException{
        st.close();
    }
    
    public static void close(Connection cn) throws SQLException{
        cn.close();
    }
}

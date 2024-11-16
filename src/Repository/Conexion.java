package Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    //mysql://root:zQXrcViTQSnLICcKNykQVJISiibfTBpV@junction.proxy.rlwy.net:39719/railway
    private static final String url = "jdbc:mysql://root:zQXrcViTQSnLICcKNykQVJISiibfTBpV@junction.proxy.rlwy.net:39719/railway";
    private static final String user = "root";
    private static final String password = "";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection cx;
    
    public Conexion(){
        
    }
    
    public Connection conectar(){
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url, user, password);
            System.out.println("Se conecto a base de datos");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se conecto");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }
    
    public void desconectar(){
        try {
            cx.close();
            System.out.println("Se desconecto la base de datos");
        } catch (SQLException ex) {
            System.out.println("No se pudo desconectar");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

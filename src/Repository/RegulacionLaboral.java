package Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegulacionLaboral {
    private static final String RUTA_ARCHIVO = "src/resources/regulacion_laboral.json";

    private int limite_dias_descanso;


    public int get_limite_dias_descanso() {
        return limite_dias_descanso;
    }

    public void set_limite_dias_descanso(int limite_dias_descanso) {
        this.limite_dias_descanso = limite_dias_descanso;
    }
    
    private Statement st;
    
    
    public RegulacionLaboral(Statement st){
        this.obtener_configuraciones();
        try {
            this.st = st.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean obtener_configuraciones(){  
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM regulacion";
            rs = st.executeQuery(query);
            
            if(rs.next()){
                System.out.println("Se encontro en la bd");
            } else {
                return false;
            }
            
            this.limite_dias_descanso = rs.getInt("valor");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean guardar_configuraciones(int limite_dias_descanso){
        
        try {
            
            String query = "UPDATE regulacion SET "
                + "valor = " + limite_dias_descanso + ", "
                + "WHERE limite_dias_descanso = 'limite_dias_descanso' "
                + ")";

            int rows_update = st.executeUpdate(query);
            
            
            return rows_update > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
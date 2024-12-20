package Repository;

import Models.Admin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.sql.Statement;
import java.sql.ResultSet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminRepository implements CRUD<Admin>{

    private static final String RUTA_ARCHIVO = "src/resources/admins.json";
    
    
    private static class AdminDTO {
        private String codigo;
        private String contrasena;
    }

    private static Admin convertirDto_Admin(AdminDTO adminDTO) {
        return new Admin(adminDTO.codigo, adminDTO.contrasena);
    }

    private static AdminDTO convertirAdmin_Dto(Admin admin) {
        AdminDTO adminDto = new AdminDTO();
        adminDto.codigo = admin.get_codigo();
        adminDto.contrasena = admin.get_contrasena();
        return adminDto;
    }
    
    private Statement st;
    
    public AdminRepository(Statement st){
        try {
            this.st = st.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean crear(Admin nuevo_admin) {
        
        try {
            String query = "INSERT INTO admins (codigo, contrasena) "
             + "SELECT '" + nuevo_admin.get_codigo() + "', '"
             + nuevo_admin.get_contrasena() + "' "
             + "WHERE NOT EXISTS ("
             + "    SELECT 1 FROM admins WHERE codigo = '" + nuevo_admin.get_codigo() + "'"
             + ")";

            
            int filas_afectadas = st.executeUpdate(query);
            
            return filas_afectadas > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Admin buscar(String codigo) {
        
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM admins WHERE codigo='"+ codigo +"'";
            rs = st.executeQuery(query);
            
            if(rs.next()){
                System.out.println("Se encontro en la bd");
            } else {
                return null;
            }
            
            AdminDTO admindto = new AdminDTO();
            admindto.codigo = rs.getString("codigo");
            admindto.contrasena = rs.getString("contrasena");
            
            return convertirDto_Admin(admindto);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    @Override
    public boolean actualizar(Admin admin_editar) {
        
        try {
            String query = "UPDATE admins SET contrasena ='"+ admin_editar.get_contrasena()+"' WHERE codigo = '"+ admin_editar.get_codigo() +"'";
            int rows_update = st.executeUpdate(query);
            
            
            return rows_update > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar(String codigo) {
        
        try {
            String query = "DELETE FROM admins WHERE codigo = '"+ codigo +"'";
            int rows_deleted = st.executeUpdate(query);
            
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Admin> listar() {
        
        try {
            String query = "SELECT * FROM admins";
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                return null;  
            } 
            
            List<Admin> admins;
            admins = new ArrayList<>();
            
            do{
                AdminDTO admindto = new AdminDTO();
                admindto.codigo = rs.getString("codigo");
                admindto.contrasena = rs.getString("contrasena");
                
                admins.add(convertirDto_Admin(admindto));
                
            } while(rs.next());
            
            return admins;
        } catch (SQLException ex) {
            Logger.getLogger(AdminRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

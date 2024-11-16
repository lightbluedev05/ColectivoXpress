package Repository;
import Models.Pasajero;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.ResultSet;

public class PasajeroRepository implements CRUD<Pasajero>{

    private static final String RUTA_ARCHIVO = "src/resources/pasajeros.json";

    private static class PasajeroDTO {
        private String nombre;
        private String correo;
        private String dni;
        private String fecha_nacimiento;
        private String contrasena;
        private String distrito;
        private String provincia;
        private String departamento;
    }

    private static Pasajero convertirDto_Pasajero(PasajeroDTO dto){
        LocalDate fecha = LocalDate.parse(dto.fecha_nacimiento);
        return new Pasajero(dto.nombre, dto.correo, dto.dni, fecha, dto.contrasena,
                dto.distrito, dto.provincia, dto.departamento);
    }
    private static PasajeroDTO convertirPasajero_Dto(Pasajero pasajero){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fecha = pasajero.get_fecha_nacimiento().format(formatter);
    
        PasajeroDTO pasajerodto = new PasajeroDTO();
        pasajerodto.nombre = pasajero.get_nombre();
        pasajerodto.correo = pasajero.get_correo();
        pasajerodto.dni = pasajero.get_dni();
        pasajerodto.fecha_nacimiento = fecha;
        pasajerodto.contrasena = pasajero.get_contrasena();
        pasajerodto.distrito = pasajero.get_distrito();
        pasajerodto.provincia = pasajero.get_provincia();
        pasajerodto.departamento = pasajero.get_departamento();
        return pasajerodto;
    }
    
    private Conexion cx = new Conexion();
    
    @Override
    public boolean crear(Pasajero nuevo_pasajero){
        try {
            
            PasajeroDTO pasajeroDto = convertirPasajero_Dto(nuevo_pasajero);
            
            String query = "INSERT INTO pasajeros (dni, nombre, correo, fecha_nacimiento, contrasena, "
             + "distrito, provincia, departamento) "
             + "SELECT '"
             + pasajeroDto.dni + "', '"
             + pasajeroDto.nombre + "', '"
             + pasajeroDto.correo + "', '"
             + pasajeroDto.fecha_nacimiento + "', '"
             + pasajeroDto.contrasena + "', '"
             + pasajeroDto.distrito + "', '"
             + pasajeroDto.provincia + "', '"
             + pasajeroDto.departamento + "' "
             + "WHERE NOT EXISTS ("
             + "    SELECT 1 FROM pasajeros "
             + "    WHERE dni = '" + pasajeroDto.dni + "' "
             + "    OR correo = '" + pasajeroDto.correo + "'"
             + ")";

            Statement st = cx.conectar().createStatement();
            int filas_afectadas = st.executeUpdate(query);
            
            cx.desconectar();
            return filas_afectadas > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }
    
    @Override
    public Pasajero buscar(String dni){
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM pasajeros WHERE dni='"+ dni +"'";
            Statement st = cx.conectar().createStatement();
            rs = st.executeQuery(query);
            
            if(rs.next()){
                System.out.println("Se encontro en la bd");
            } else {
                cx.desconectar();
                return null;
            }
            
            PasajeroDTO pasajeroDto = new PasajeroDTO();
            pasajeroDto.dni = rs.getString("dni");
            pasajeroDto.contrasena = rs.getString("contrasena");
            pasajeroDto.nombre = rs.getString("nombre");
            pasajeroDto.correo = rs.getString("correo");
            pasajeroDto.fecha_nacimiento = rs.getString("fecha_nacimiento");
            pasajeroDto.distrito = rs.getString("distrito");
            pasajeroDto.provincia = rs.getString("provincia");
            pasajeroDto.departamento = rs.getString("departamento");
            cx.desconectar();
            
            return convertirDto_Pasajero(pasajeroDto);
            
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return null;
        }
    }

    @Override
    public boolean actualizar(Pasajero pasajero_editar){
        try {
            
            PasajeroDTO pasajeroDto = convertirPasajero_Dto(pasajero_editar);

            String query = "UPDATE pasajeros SET "
                + "nombre = '" + pasajeroDto.nombre + "', "
                + "correo = '" + pasajeroDto.correo + "', "
                + "fecha_nacimiento = '" + pasajeroDto.fecha_nacimiento + "', "
                + "contrasena = '" + pasajeroDto.contrasena + "', "
                + "distrito = '" + pasajeroDto.distrito + "', "
                + "provincia = '" + pasajeroDto.provincia + "', "
                + "departamento = '" + pasajeroDto.departamento + "' "
                + "WHERE dni = '" + pasajeroDto.dni + "' "
                + "AND NOT EXISTS ("
                + "    SELECT 1 FROM pasajeros "
                + "    WHERE correo = '" + pasajeroDto.correo + "' "
                + "    AND dni != '" + pasajeroDto.dni + "'"
                + ")";

            Statement st = cx.conectar().createStatement();
            int rows_update = st.executeUpdate(query);
            
            cx.desconectar();
            
            return rows_update > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }

    @Override
    public boolean eliminar(String dni){
        try {
            String query = "DELETE FROM pasajeros WHERE dni = '"+ dni +"'";
            Statement st = cx.conectar().createStatement();
            int rows_deleted = st.executeUpdate(query);
            
            cx.desconectar();
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }

    @Override
    public List<Pasajero> listar(){
        try {
            String query = "SELECT * FROM pasajeros";
            Statement st = cx.conectar().createStatement();
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                cx.desconectar();
                return null;  
            } 
            
            List<Pasajero> pasajeros;
            pasajeros = new ArrayList<>();
            
            do{
                PasajeroDTO pasajeroDto = new PasajeroDTO();
                pasajeroDto.nombre = rs.getString("nombre");
                pasajeroDto.correo = rs.getString("correo");
                pasajeroDto.fecha_nacimiento = rs.getString("fecha_nacimiento");
                pasajeroDto.contrasena = rs.getString("contrasena");
                pasajeroDto.distrito = rs.getString("distrito");
                pasajeroDto.provincia = rs.getString("provincia");
                pasajeroDto.departamento = rs.getString("departamento");
                pasajeroDto.dni = rs.getString("dni");
                
                pasajeros.add(convertirDto_Pasajero(pasajeroDto));
                
            } while(rs.next());
            
            cx.desconectar();
            return pasajeros;
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return null;
        }
    }

}

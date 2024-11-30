package Repository;

import Models.Conductor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
//comentario
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

public class ConductorRepository implements CRUD<Conductor>{

    private static final String RUTA_ARCHIVO = "src/resources/conductores.json";

    private static class ConductorDTO {
        private String nombre;
        private String correo;
        private String dni;
        private String fecha_nacimiento;
        private String contrasena;
        private String distrito;
        private String provincia;
        private String departamento;
        private String dias_descanso;
        private int capacidad_vehiculo;
        private String placa_vehiculo;
        private String modelo_vehiculo;
        private String telefono;
    }

    private static Conductor convertirDto_Conductor(ConductorDTO dto){
        LocalDate fecha = LocalDate.parse(dto.fecha_nacimiento);
        Conductor conductor = new Conductor(dto.nombre, dto.correo, dto.dni, fecha, dto.contrasena,
                dto.distrito, dto.provincia, dto.departamento, dto.capacidad_vehiculo,
                dto.modelo_vehiculo, dto.placa_vehiculo, dto.telefono);
        conductor.set_dias_descanso(dto.dias_descanso);
        return conductor;
    }

    private static ConductorDTO convertirConductor_Dto(Conductor conductor){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fecha = conductor.get_fecha_nacimiento().format(formatter);
        ConductorDTO conductordto = new ConductorDTO();
        conductordto.nombre = conductor.get_nombre();
        conductordto.correo = conductor.get_correo();
        conductordto.dni = conductor.get_dni();
        conductordto.fecha_nacimiento = fecha;
        conductordto.contrasena = conductor.get_contrasena();
        conductordto.distrito = conductor.get_distrito();
        conductordto.provincia = conductor.get_provincia();
        conductordto.departamento = conductor.get_departamento();
        conductordto.dias_descanso = conductor.get_dias_descanso();
        conductordto.capacidad_vehiculo = conductor.get_capacidad_vehiculo();
        conductordto.placa_vehiculo = conductor.get_placa_vehiculo();
        conductordto.modelo_vehiculo = conductor.get_modelo_vehiculo();
        conductordto.telefono = conductor.get_telefono();
        return conductordto;
    }
    
    private Statement st;
    public ConductorRepository(Statement st){
        try {
            this.st = st.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean crear(Conductor nuevo_conductor) {
        
        try {
            
            ConductorDTO conductorDto = convertirConductor_Dto(nuevo_conductor);
            
            // Verificar si el dni o correo ya existen
            String checkQuery = "SELECT COUNT(*) FROM conductores "
                              + "WHERE dni = '" + conductorDto.dni + "' "
                              + "OR correo = '" + conductorDto.correo + "'";

            ResultSet rs = st.executeQuery(checkQuery);

            if (rs.next() && rs.getInt(1) == 0) { // Si no existe ningún conductor con el dni o correo
                // Si no existen duplicados, insertar el nuevo conductor
                String insertQuery = "INSERT INTO conductores (dni, nombre, correo, fecha_nacimiento, contrasena, "
                                   + "distrito, provincia, departamento, dias_descanso, capacidad_vehiculo, telefono, "
                                   + "placa_vehiculo, modelo_vehiculo ) "
                                   + "VALUES ('"
                                   + conductorDto.dni + "', '"
                                   + conductorDto.nombre + "', '"
                                   + conductorDto.correo + "', '"
                                   + conductorDto.fecha_nacimiento + "', '"
                                   + conductorDto.contrasena + "', '"
                                   + conductorDto.distrito + "', '"
                                   + conductorDto.provincia + "', '"
                                   + conductorDto.departamento + "', '"
                                   + conductorDto.dias_descanso + "', "
                                   + conductorDto.capacidad_vehiculo +", '"
                                   + conductorDto.telefono + "', '"
                                   + conductorDto.placa_vehiculo + "', '"
                                   + conductorDto.modelo_vehiculo + "' )";

                int filas_afectadas = st.executeUpdate(insertQuery);
                return filas_afectadas > 0;
            } else {
                return false; // Si ya existe un conductor con el dni o correo, no hacer la inserción
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConductorRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Conductor buscar(String dni){
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM conductores WHERE dni='"+ dni +"'";
            rs = st.executeQuery(query);
            
            if(rs.next()){
                System.out.println("Se encontro en la bd");
            } else {
                return null;
            }
            
            ConductorDTO conductorDto = new ConductorDTO();
            conductorDto.dni = rs.getString("dni");
            conductorDto.contrasena = rs.getString("contrasena");
            conductorDto.nombre = rs.getString("nombre");
            conductorDto.correo = rs.getString("correo");
            conductorDto.fecha_nacimiento = rs.getString("fecha_nacimiento");
            conductorDto.distrito = rs.getString("distrito");
            conductorDto.provincia = rs.getString("provincia");
            conductorDto.departamento = rs.getString("departamento");
            conductorDto.dias_descanso = rs.getString("dias_descanso");
            conductorDto.capacidad_vehiculo = rs.getInt("capacidad_vehiculo");
            conductorDto.telefono = rs.getString("telefono");
            conductorDto.placa_vehiculo = rs.getString("placa_vehiculo");
            conductorDto.modelo_vehiculo = rs.getString("modelo_vehiculo");
            
            return convertirDto_Conductor(conductorDto);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConductorRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Conductor> buscar_pro(String dni, String nombre, String distrito, String provincia, String departamento, String telefono,
            String placa_vehiculo, String modelo_vehiculo) {
        
        ResultSet rs;
        List<Conductor> conductores = new ArrayList<>();

        try {
            
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM conductores WHERE 1=1");

            
            if (dni != null && !dni.isEmpty()) {
                queryBuilder.append(" AND dni='").append(dni).append("'");
            }
            if (nombre != null && !nombre.isEmpty()) {
                queryBuilder.append(" AND nombre='").append(nombre).append("'");
            }
            if (distrito != null && !distrito.isEmpty()) {
                queryBuilder.append(" AND distrito='").append(distrito).append("'");
            }
            if (provincia != null && !provincia.isEmpty()) {
                queryBuilder.append(" AND provincia='").append(provincia).append("'");
            }
            if (departamento != null && !departamento.isEmpty()) {
                queryBuilder.append(" AND departamento='").append(departamento).append("'");
            }
            if (telefono != null && !telefono.isEmpty()) {
                queryBuilder.append(" AND telefono='").append(telefono).append("'");
            }
            if (placa_vehiculo != null && !placa_vehiculo.isEmpty()) {
                queryBuilder.append(" AND placa_vehiculo='").append(placa_vehiculo).append("'");
            }
            if (modelo_vehiculo != null && !modelo_vehiculo.isEmpty()) {
                queryBuilder.append(" AND modelo_vehiculo='").append(modelo_vehiculo).append("'");
            }

            
            String query = queryBuilder.toString();
            rs = st.executeQuery(query);

            
            while (rs.next()) {
                ConductorDTO conductorDto = new ConductorDTO();
                conductorDto.dni = rs.getString("dni");
                conductorDto.contrasena = rs.getString("contrasena");
                conductorDto.nombre = rs.getString("nombre");
                conductorDto.correo = rs.getString("correo");
                conductorDto.fecha_nacimiento = rs.getString("fecha_nacimiento");
                conductorDto.distrito = rs.getString("distrito");
                conductorDto.provincia = rs.getString("provincia");
                conductorDto.departamento = rs.getString("departamento");
                conductorDto.dias_descanso = rs.getString("dias_descanso");
                conductorDto.capacidad_vehiculo = rs.getInt("capacidad_vehiculo");
                conductorDto.telefono = rs.getString("telefono");
                conductorDto.placa_vehiculo = rs.getString("placa_vehiculo");
                conductorDto.modelo_vehiculo = rs.getString("modelo_vehiculo");

               
                conductores.add(convertirDto_Conductor(conductorDto));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConductorRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conductores; 
    }
    
    @Override
    public boolean actualizar(Conductor conductor_editar){
        try {
            
    ConductorDTO conductorDto = convertirConductor_Dto(conductor_editar);

    // Verificar si el correo ya está registrado con otro dni
    String checkQuery = "SELECT COUNT(*) FROM conductores "
                      + "WHERE correo = '" + conductorDto.correo + "' "
                      + "AND dni != '" + conductorDto.dni + "'";

    ResultSet rs = st.executeQuery(checkQuery);

    if (rs.next() && rs.getInt(1) == 0) { // Si no existe otro conductor con el mismo correo
        // Si el correo no está duplicado, realizar el UPDATE
        String updateQuery = "UPDATE conductores SET "
                           + "nombre = '" + conductorDto.nombre + "', "
                           + "correo = '" + conductorDto.correo + "', "
                           + "fecha_nacimiento = '" + conductorDto.fecha_nacimiento + "', "
                           + "contrasena = '" + conductorDto.contrasena + "', "
                           + "distrito = '" + conductorDto.distrito + "', "
                           + "provincia = '" + conductorDto.provincia + "', "
                           + "departamento = '" + conductorDto.departamento + "', "
                           + "dias_descanso = '" + conductorDto.dias_descanso + "', "
                           + "capacidad_vehiculo = " + conductorDto.capacidad_vehiculo + ", "
                           + "telefono = '" + conductorDto.telefono + "', "
                           + "placa_vehiculo = '" + conductorDto.placa_vehiculo + "', "
                           + "modelo_vehiculo = '" + conductorDto.modelo_vehiculo + "' "
                           + "WHERE dni = '" + conductorDto.dni + "'";

        int rows_update = st.executeUpdate(updateQuery);
            
        return rows_update > 0;
    } else {
        return false; // Si el correo ya está en uso, no realizar el UPDATE
    }
} catch (SQLException ex) {
    Logger.getLogger(ConductorRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
}
    }
    
    @Override
    public boolean eliminar(String dni){
        try {
            String query = "DELETE FROM conductores WHERE dni = '"+ dni +"'";
            int rows_deleted = st.executeUpdate(query);
            
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConductorRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public List<Conductor> listar(){

        try {
            String query = "SELECT * FROM conductores";
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                return null;  
            } 
            
            List<Conductor> conductores;
            conductores = new ArrayList<>();
            
            do{
                ConductorDTO conductorDto = new ConductorDTO();
                conductorDto.nombre = rs.getString("nombre");
                conductorDto.correo = rs.getString("correo");
                conductorDto.fecha_nacimiento = rs.getString("fecha_nacimiento");
                conductorDto.contrasena = rs.getString("contrasena");
                conductorDto.distrito = rs.getString("distrito");
                conductorDto.provincia = rs.getString("provincia");
                conductorDto.departamento = rs.getString("departamento");
                conductorDto.dias_descanso = rs.getString("dias_descanso");
                conductorDto.dni = rs.getString("dni");
                conductorDto.capacidad_vehiculo = rs.getInt("capacidad_vehiculo");
                conductorDto.telefono = rs.getString("telefono");
                conductorDto.placa_vehiculo = rs.getString("placa_vehiculo");
                conductorDto.modelo_vehiculo = rs.getString("modelo_vehiculo");
                
                conductores.add(convertirDto_Conductor(conductorDto));
                
            } while(rs.next());
            
            return conductores;
        } catch (SQLException ex) {
            Logger.getLogger(ConductorRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Conductor> listar_libres(){
        try {
            String query = "SELECT * FROM conductores c " +
                           "WHERE NOT EXISTS (" +
                           "    SELECT 1 FROM viajes v " +
                           "    WHERE v.dni_conductor = c.dni AND v.estado = TRUE" +
                           ")";

            ResultSet rs = st.executeQuery(query);

            if (!rs.next()) {
                return null;
            }

            List<Conductor> conductores = new ArrayList<>();

            do {
                ConductorDTO conductorDto = new ConductorDTO();
                conductorDto.nombre = rs.getString("nombre");
                conductorDto.correo = rs.getString("correo");
                conductorDto.fecha_nacimiento = rs.getString("fecha_nacimiento");
                conductorDto.contrasena = rs.getString("contrasena");
                conductorDto.distrito = rs.getString("distrito");
                conductorDto.provincia = rs.getString("provincia");
                conductorDto.departamento = rs.getString("departamento");
                conductorDto.dias_descanso = rs.getString("dias_descanso");
                conductorDto.dni = rs.getString("dni");
                conductorDto.capacidad_vehiculo = rs.getInt("capacidad_vehiculo");
                conductorDto.telefono = rs.getString("telefono");
                conductorDto.placa_vehiculo = rs.getString("placa_vehiculo");
                conductorDto.modelo_vehiculo = rs.getString("modelo_vehiculo");

                conductores.add(convertirDto_Conductor(conductorDto));

            } while (rs.next());

            return conductores;

        } catch (SQLException ex) {
            Logger.getLogger(ConductorRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}

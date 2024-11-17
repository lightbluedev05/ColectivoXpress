package Repository;
import Models.Conductor;
import Models.Ruta;
import Models.Viaje;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViajeRepository implements CRUD<Viaje>{

    private static final String RUTA_ARCHIVO = "src/resources/viajes.json";

    private static class ViajeDTO {
        private String id_viaje;
        private String fecha_salida;
        private String hora_salida;
        private String id_ruta;
        private String dni_conductor; 
        private boolean estado;
    }

    private static Viaje convertirDto_Viaje(ViajeDTO dto) {
        Ruta ruta = new RutaRepository().buscar(dto.id_ruta); 
        Conductor conductor = new ConductorRepository().buscar(dto.dni_conductor); 
        return new Viaje(
            dto.id_viaje, 
            LocalDate.parse(dto.fecha_salida), 
            ruta, 
            conductor, 
            LocalTime.parse(dto.hora_salida),
            dto.estado
        );
    }

    private static ViajeDTO convertirViaje_Dto(Viaje viaje) {
        ViajeDTO dto = new ViajeDTO();
        dto.id_viaje = viaje.get_id_viaje();
        dto.fecha_salida = viaje.get_fecha_salida().toString();
        dto.hora_salida = viaje.get_hora_salida().toString();
        dto.id_ruta = viaje.get_ruta().get_id_ruta();
        dto.dni_conductor = viaje.get_conductor().get_dni();
        dto.estado = viaje.get_estado();
        return dto;
    }
    
    private Conexion cx = new Conexion();

    @Override
    public boolean crear(Viaje nuevo_viaje) {
        
        try {
            
            ViajeDTO viajeDto = convertirViaje_Dto(nuevo_viaje);
            
            String query = "INSERT INTO viajes (id_viaje, fecha_salida, hora_salida, id_ruta, dni_conductor, estado) "
             + "SELECT '" + viajeDto.id_viaje + "', "
             + "'" + viajeDto.fecha_salida + "', "
             + "'" + viajeDto.hora_salida + "', "
             + "'" + viajeDto.id_ruta + "', "
             + "'" + viajeDto.dni_conductor + "', "
             + viajeDto.estado + " "
             + "WHERE NOT EXISTS ("
             + "    SELECT 1 FROM viajes "
             + "    WHERE id_viaje = '" + viajeDto.id_viaje + "')";
            
            Statement st = cx.conectar().createStatement();
            int filas_afectadas = st.executeUpdate(query);
            
            cx.desconectar();
            return filas_afectadas > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }

    @Override
    public Viaje buscar(String id_viaje) {
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM viajes WHERE id_viaje='"+ id_viaje +"'";
            Statement st = cx.conectar().createStatement();
            rs = st.executeQuery(query);
            
            if(rs.next()){
                System.out.println("Se encontro en la bd");
            } else {
                cx.desconectar();
                return null;
            }
            
            ViajeDTO viajeDto = new ViajeDTO();
            viajeDto.id_viaje = rs.getString("id_viaje");
            viajeDto.fecha_salida = rs.getString("fecha_salida");
            viajeDto.hora_salida = rs.getString("hora_salida");
            viajeDto.id_ruta = rs.getString("id_ruta");
            viajeDto.dni_conductor = rs.getString("dni_conductor");
            viajeDto.estado = rs.getBoolean("estado");

            cx.desconectar();
            
            return convertirDto_Viaje(viajeDto);
            
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return null;
        }
    }

    @Override
    public boolean actualizar(Viaje viaje_editar) {
        try {
            
            ViajeDTO viajeDto = convertirViaje_Dto(viaje_editar);
            
            String query = "UPDATE viajes SET "
             + "fecha_salida = '" + viajeDto.fecha_salida + "', "
             + "hora_salida = '" + viajeDto.hora_salida + "', "
             + "id_ruta = '" + viajeDto.id_ruta + "', "
             + "dni_conductor = '" + viajeDto.dni_conductor + "', "
             + "estado = " + viajeDto.estado + " "
             + "WHERE id_viaje = '" + viajeDto.id_viaje + "'";


            Statement st = cx.conectar().createStatement();
            int rows_update = st.executeUpdate(query);
            
            cx.desconectar();
            
            return rows_update > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id_viaje){
        
        try {
            String query = "DELETE FROM viajes WHERE id_viaje = '"+ id_viaje +"'";
            Statement st = cx.conectar().createStatement();
            int rows_deleted = st.executeUpdate(query);
            
            cx.desconectar();
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
        
    }

    @Override
    public List<Viaje> listar() {
        try {
            String query = "SELECT * FROM viajes";
            Statement st = cx.conectar().createStatement();
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                cx.desconectar();
                return null;  
            } 
            
            List<Viaje> viajes;
            viajes = new ArrayList<>();
            
            do {
                ViajeDTO viajeDto = new ViajeDTO();
                viajeDto.id_viaje = rs.getString("id_viaje");
                viajeDto.fecha_salida = rs.getString("fecha_salida");
                viajeDto.hora_salida = rs.getString("hora_salida");
                viajeDto.id_ruta = rs.getString("id_ruta");
                viajeDto.dni_conductor = rs.getString("dni_conductor");
                viajeDto.estado = rs.getBoolean("estado");
                System.out.println("Viaje: "+ viajeDto.id_viaje+" recuperado");
                viajes.add(convertirDto_Viaje(viajeDto));
            } while (rs.next());


            
            cx.desconectar();
            return viajes;
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return null;
        }
    }
    
    public List<Viaje> listar_activos(){
        try {
            String query = "SELECT * FROM viajes WHERE estado = TRUE";
            Statement st = cx.conectar().createStatement();
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                cx.desconectar();
                return null;  
            } 
            
            List<Viaje> viajes;
            viajes = new ArrayList<>();
            
            do {
                ViajeDTO viajeDto = new ViajeDTO();
                viajeDto.id_viaje = rs.getString("id_viaje");
                viajeDto.fecha_salida = rs.getString("fecha_salida");
                viajeDto.hora_salida = rs.getString("hora_salida");
                viajeDto.id_ruta = rs.getString("id_ruta");
                viajeDto.dni_conductor = rs.getString("dni_conductor");
                viajeDto.estado = rs.getBoolean("estado");

                viajes.add(convertirDto_Viaje(viajeDto));
            } while (rs.next());


            
            cx.desconectar();
            return viajes;
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return null;
        }
    }
}

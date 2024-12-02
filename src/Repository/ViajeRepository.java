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

    public static class ViajeDTO {
        public String id_viaje;
        public String fecha_salida;
        private String hora_salida;
        private String id_ruta;
        public String dni_conductor; 
        private boolean estado;
    }
    
    private static Viaje convertirDto_Viaje(ViajeDTO dto) {
        Ruta ruta = new RutaRepository(st).buscar(dto.id_ruta); 
        Conductor conductor = new ConductorRepository(st).buscar(dto.dni_conductor); 
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
    
    private static Statement st;
    public ViajeRepository(Statement st){
            try {
            this.st = st.getConnection().createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
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
            
            int filas_afectadas = st.executeUpdate(query);
            
            return filas_afectadas > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Viaje buscar(String id_viaje) {
        ResultSet rs;

        try {
            String query = "SELECT * FROM viajes WHERE id_viaje='"+ id_viaje +"'";
            rs = st.executeQuery(query);

            if(rs.next()){
                System.out.println("Se encontro en la bd");
            } else {
                return null;
            }

            ViajeDTO viajeDto = new ViajeDTO();
            viajeDto.id_viaje = rs.getString("id_viaje");
            viajeDto.fecha_salida = rs.getString("fecha_salida");
            viajeDto.hora_salida = rs.getString("hora_salida");
            viajeDto.id_ruta = rs.getString("id_ruta");
            viajeDto.dni_conductor = rs.getString("dni_conductor");
            viajeDto.estado = rs.getBoolean("estado");


            return convertirDto_Viaje(viajeDto);

        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
    public List<Viaje> buscar_pro(String id_viaje, String origen, String destino, String fecha, String dni_conductor, String estado) {
        ResultSet rs;
        List<Viaje> viajes = new ArrayList<>();

        try {
            StringBuilder queryBuilder = new StringBuilder(
                "SELECT v.*, r.origen, r.destino " +
                "FROM viajes v " +
                "JOIN rutas r ON v.id_ruta = r.id_ruta " +
                "WHERE 1=1"
            );

            if (id_viaje != null && !id_viaje.isEmpty()) {
                queryBuilder.append(" AND v.id_viaje='").append(id_viaje).append("'");
            }
            if (origen != null && !origen.isEmpty()) {
                queryBuilder.append(" AND r.origen='").append(origen).append("'");
            }
            if (destino != null && !destino.isEmpty()) {
                queryBuilder.append(" AND r.destino='").append(destino).append("'");
            }
            if (fecha != null && !fecha.isEmpty()) {
                queryBuilder.append(" AND v.fecha_salida='").append(fecha).append("'");
            }
            if (dni_conductor != null && !dni_conductor.isEmpty()) {
                queryBuilder.append(" AND v.dni_conductor='").append(dni_conductor).append("'");
            }
            if (estado != null && !estado.isEmpty() && !estado.equals("Cualquiera")) {
                if (estado.equals("Activo")) {
                    queryBuilder.append(" AND v.estado=").append(1);
                } else {
                    queryBuilder.append(" AND v.estado=").append(0);
                }
            }

            String query = queryBuilder.toString();
            rs = st.executeQuery(query);

            while (rs.next()) {
                ViajeDTO viajeDto = new ViajeDTO();
                viajeDto.id_viaje = rs.getString("id_viaje");
                viajeDto.fecha_salida = rs.getString("fecha_salida");
                viajeDto.hora_salida = rs.getString("hora_salida");
                viajeDto.id_ruta = rs.getString("id_ruta");
                viajeDto.dni_conductor = rs.getString("dni_conductor");
                viajeDto.estado = rs.getBoolean("estado");

                viajes.add(convertirDto_Viaje(viajeDto));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return viajes; 
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


            int rows_update = st.executeUpdate(query);
            
            
            return rows_update > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar(String id_viaje){
        
        try {
            String query = "DELETE FROM viajes WHERE id_viaje = '"+ id_viaje +"'";
            int rows_deleted = st.executeUpdate(query);
            
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public List<Viaje> listar() {
        try {
            String query = "SELECT * FROM viajes";
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
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


            
            return viajes;
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<ViajeDTO> listar_dto() {
        try {
            String query = "SELECT * FROM viajes";
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                return null;  
            } 
            
            List<ViajeDTO> viajes;
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
                viajes.add(viajeDto);
            } while (rs.next());


            
            return viajes;
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Viaje> listar_activos(){
        try {
            String query = "SELECT * FROM viajes WHERE estado = TRUE";
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
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


            
            return viajes;
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

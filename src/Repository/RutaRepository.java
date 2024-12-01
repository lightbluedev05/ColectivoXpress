package Repository;

import Models.Pasajero;
import Models.Ruta;
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
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RutaRepository implements CRUD<Ruta>{

    private static final String RUTA_ARCHIVO = "src/resources/rutas.json";

    private static class RutaDTO{
        private String id_ruta;
        private String origen;
        private String destino;
        private String tiempo_aproximado;
        private float precio;
    }

    private static Ruta convertirDto_Ruta(RutaDTO dto){
        
        String[] tiempo = dto.tiempo_aproximado.split(":");
        int horas = Integer.parseInt(tiempo[0]);
        int minutos = Integer.parseInt(tiempo[1]);
        
        return new Ruta(dto.id_ruta, dto.origen, dto.destino,
                Duration.ofHours(horas).plusMinutes(minutos), dto.precio);
    }

    private static RutaDTO convertirRuta_Dto(Ruta ruta){
        RutaDTO dto = new RutaDTO();
        dto.id_ruta = ruta.get_id_ruta();
        dto.origen = ruta.get_origen();
        dto.destino = ruta.get_destino();
        dto.precio = ruta.get_precio();
        
        long horas = ruta.get_tiempo_aproximado().toHours();
        long minutos = ruta.get_tiempo_aproximado().toMinutesPart();
        
        dto.tiempo_aproximado = String.format("%02d:%02d", horas, minutos);
        return dto;
    }
    
    private Statement st;
    
    public RutaRepository(Statement st){
        try {
            this.st = st.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean crear(Ruta nueva_ruta){
        
        try {
            
            RutaDTO rutaDto = convertirRuta_Dto(nueva_ruta);
            
            String query = "INSERT INTO rutas (id_ruta, origen, destino, tiempo_aproximado, precio) "
             + "SELECT '" + rutaDto.id_ruta + "', "
             + "'" + rutaDto.origen + "', "
             + "'" + rutaDto.destino + "', "
             + "'" + rutaDto.tiempo_aproximado + "', "
             + rutaDto.precio
             + " WHERE NOT EXISTS ("
             + "    SELECT 1 FROM rutas "
             + "    WHERE (origen = '" + rutaDto.origen + "' "
             + "           AND destino = '" + rutaDto.destino + "' "
             + "           OR id_ruta = '" + rutaDto.id_ruta + "'))";
            
            int filas_afectadas = st.executeUpdate(query);
            
            return filas_afectadas > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Ruta buscar(String id_ruta){
        
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM rutas WHERE id_ruta='"+ id_ruta +"'";
            rs = st.executeQuery(query);
            
            if(rs.next()){
                System.out.println("Se encontro en la bd");
            } else {
                return null;
            }
            
            RutaDTO rutaDto = new RutaDTO();
            rutaDto.id_ruta = rs.getString("id_ruta");
            rutaDto.origen = rs.getString("origen");
            rutaDto.destino = rs.getString("destino");
            rutaDto.tiempo_aproximado = rs.getString("tiempo_aproximado");
            rutaDto.precio = rs.getFloat("precio");

            
            return convertirDto_Ruta(rutaDto);
            
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Ruta> buscar_pro(String id_ruta, String origen, String destino) {

        ResultSet rs;
        List<Ruta> rutas = new ArrayList<>();

        try {

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM rutas WHERE 1=1");

            if (id_ruta != null && !id_ruta.isEmpty()) {
                queryBuilder.append(" AND id_ruta='").append(id_ruta).append("'");
            }
            if (origen != null && !origen.isEmpty()) {
                queryBuilder.append(" AND origen='").append(origen).append("'");
            }
            if (destino != null && !destino.isEmpty()) {
                queryBuilder.append(" AND destino='").append(destino).append("'");
            }

            String query = queryBuilder.toString();
            rs = st.executeQuery(query);

            while (rs.next()) {
                RutaDTO rutaDto = new RutaDTO();
                rutaDto.id_ruta = rs.getString("id_ruta");
                rutaDto.origen = rs.getString("origen");
                rutaDto.destino = rs.getString("destino");
                rutaDto.tiempo_aproximado = rs.getString("tiempo_aproximado");
                rutaDto.precio = rs.getFloat("precio");

                rutas.add(convertirDto_Ruta(rutaDto));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rutas;
    }


    @Override
    public boolean actualizar(Ruta ruta_editar){
        
        try {
            
            RutaDTO rutaDto = convertirRuta_Dto(ruta_editar);
            
            // Primero, verifica si existe una ruta con el mismo origen y destino
            String checkQuery = "SELECT COUNT(*) FROM rutas "
                              + "WHERE origen = '" + rutaDto.origen + "' "
                              + "AND destino = '" + rutaDto.destino + "' "
                              + "AND id_ruta != '" + rutaDto.id_ruta + "'";

            ResultSet rs = st.executeQuery(checkQuery);

            if (rs.next() && rs.getInt(1) == 0) { // Si no existe ninguna ruta duplicada
                // Si no hay duplicados, realizar la actualización
                String updateQuery = "UPDATE rutas SET "
                                   + "origen = '" + rutaDto.origen + "', "
                                   + "destino = '" + rutaDto.destino + "', "
                                   + "tiempo_aproximado = '" + rutaDto.tiempo_aproximado + "', "
                                   + "precio = " + rutaDto.precio + " "
                                   + "WHERE id_ruta = '" + rutaDto.id_ruta + "'";

                int rows_update = st.executeUpdate(updateQuery);

                return rows_update > 0;
            } else {
                return false; // Si existe una ruta duplicada, no hacer la actualización
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar(String id_ruta){
        
        try {
            String query = "DELETE FROM rutas WHERE id_ruta = '"+ id_ruta +"'";
            int rows_deleted = st.executeUpdate(query);
            
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public List<Ruta> listar(){
        try {
            String query = "SELECT * FROM rutas";
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                return null;  
            } 
            
            List<Ruta> rutas;
            rutas = new ArrayList<>();
            
            do {
                RutaRepository.RutaDTO rutaDto = new RutaRepository.RutaDTO();
                rutaDto.id_ruta = rs.getString("id_ruta");
                rutaDto.origen = rs.getString("origen");
                rutaDto.destino = rs.getString("destino");
                rutaDto.tiempo_aproximado = rs.getString("tiempo_aproximado");
                rutaDto.precio = rs.getFloat("precio");

                rutas.add(convertirDto_Ruta(rutaDto));
            } while (rs.next());

            
            return rutas;
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

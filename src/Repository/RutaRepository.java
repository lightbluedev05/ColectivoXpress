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
    
    private Conexion cx = new Conexion();
    
    @Override
    public boolean crear(Ruta nueva_ruta){
        
        try {
            
            RutaDTO rutaDto = convertirRuta_Dto(nueva_ruta);
            
            String query = "INSERT INTO rutas (id_ruta, origen, destino, tiempo_aproximado, precio) "
             + "VALUES ("
             + "'" + rutaDto.id_ruta + "', "
             + "'" + rutaDto.origen + "', "
             + "'" + rutaDto.destino + "', "
             + "'" + rutaDto.tiempo_aproximado + "', "
             + rutaDto.precio
             + ")";
            
            Statement st = cx.conectar().createStatement();
            int filas_afectadas = st.executeUpdate(query);
            
            cx.desconectar();
            return filas_afectadas > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }

    @Override
    public Ruta buscar(String id_ruta){
        
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM rutas WHERE id_ruta='"+ id_ruta +"'";
            Statement st = cx.conectar().createStatement();
            rs = st.executeQuery(query);
            
            if(rs.next()){
                System.out.println("Se encontro en la bd");
            } else {
                cx.desconectar();
                return null;
            }
            
            RutaDTO rutaDto = new RutaDTO();
            rutaDto.id_ruta = rs.getString("id_ruta");
            rutaDto.origen = rs.getString("origen");
            rutaDto.destino = rs.getString("destino");
            rutaDto.tiempo_aproximado = rs.getString("tiempo_aproximado");
            rutaDto.precio = rs.getFloat("precio");

            cx.desconectar();
            
            return convertirDto_Ruta(rutaDto);
            
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return null;
        }
    }

    @Override
    public boolean actualizar(Ruta ruta_editar){
        
        try {
            
            RutaDTO rutaDto = convertirRuta_Dto(ruta_editar);
            
            String query = "UPDATE rutas SET "
             + "origen = '" + rutaDto.origen + "', "
             + "destino = '" + rutaDto.destino + "', "
             + "tiempo_aproximado = '" + rutaDto.tiempo_aproximado + "', "
             + "precio = " + rutaDto.precio + " " // Sin comillas porque es float
             + "WHERE id_ruta = '" + rutaDto.id_ruta + "' "
             + "AND NOT EXISTS ("
             + "    SELECT 1 FROM rutas "
             + "    WHERE origen = '" + rutaDto.origen + "' "
             + "    AND destino = '" + rutaDto.destino + "' "
             + "    AND id_ruta != '" + rutaDto.id_ruta + "'"
             + ")";

            Statement st = cx.conectar().createStatement();
            int rows_update = st.executeUpdate(query);
            
            cx.desconectar();
            
            return rows_update > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }

    @Override
    public boolean eliminar(String id_ruta){
        
        try {
            String query = "DELETE FROM rutas WHERE id_ruta = '"+ id_ruta +"'";
            Statement st = cx.conectar().createStatement();
            int rows_deleted = st.executeUpdate(query);
            
            cx.desconectar();
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(RutaRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }
    
    @Override
    public List<Ruta> listar(){
        try {
            String query = "SELECT * FROM rutas";
            Statement st = cx.conectar().createStatement();
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                cx.desconectar();
                return null;  
            } 
            
            List<Ruta> rutas;
            rutas = new ArrayList<>();
            
            do{
                PasajeroRepository.PasajeroDTO pasajeroDto = new PasajeroRepository.PasajeroDTO();
                pasajeroDto.nombre = rs.getString("nombre");
                pasajeroDto.correo = rs.getString("correo");
                pasajeroDto.fecha_nacimiento = rs.getString("fecha_nacimiento");
                pasajeroDto.contrasena = rs.getString("contrasena");
                pasajeroDto.distrito = rs.getString("distrito");
                pasajeroDto.provincia = rs.getString("provincia");
                pasajeroDto.departamento = rs.getString("departamento");
                pasajeroDto.dni = rs.getString("dni");
                
                rutas.add(convertirDto_Pasajero(pasajeroDto));
                
            } while(rs.next());
            
            cx.desconectar();
            return rutas;
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return null;
        }
    }
}

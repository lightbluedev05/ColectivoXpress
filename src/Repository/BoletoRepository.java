package Repository;

import Models.Boleto;
import Models.Conductor;
import Models.Pasajero;
import Models.Viaje;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.PreparedStatement;
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

public class BoletoRepository implements CRUD<Boleto>{

    private static final String RUTA_ARCHIVO = "src/resources/boletos.json";

    private static class BoletoDTO {
        private String id_boleto;
        private String dni_pasajero;
        private String id_viaje;
        private float precio;
    }

    private static Boleto convertirDto_Boleto(BoletoDTO boletoDTO) {
        Pasajero pasajero = new PasajeroRepository(st).buscar(boletoDTO.dni_pasajero);
        Viaje viaje = new ViajeRepository(st).buscar(boletoDTO.id_viaje);
        return new Boleto(boletoDTO.id_boleto, pasajero, viaje, boletoDTO.precio);
    }

    private static BoletoDTO convertirBoleto_Dto(Boleto boleto) {
        BoletoDTO boletoDto = new BoletoDTO();
        boletoDto.id_boleto = boleto.get_id_boleto();
        boletoDto.dni_pasajero = boleto.get_pasajero().get_dni();
        boletoDto.id_viaje = boleto.get_viaje().get_id_viaje();
        boletoDto.precio = boleto.get_precio();
        return boletoDto;
    }
    
    private static Statement st;
    
    public BoletoRepository(Statement st){
        try {
            this.st = st.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean crear(Boleto nuevo_boleto) {
        
        try {
            BoletoDTO boletoDto = convertirBoleto_Dto(nuevo_boleto);
            
            String query = "INSERT INTO boletos (id_boleto, dni_pasajero, id_viaje, precio) "
             + "SELECT '" + boletoDto.id_boleto + "', '"
             + boletoDto.dni_pasajero + "', '"
             + boletoDto.id_viaje + "', "
             + boletoDto.precio + " "
             + "WHERE NOT EXISTS ("
             + "    SELECT 1 FROM boletos WHERE id_boleto = '" + boletoDto.id_boleto + "'"
             + ")";

            int filas_afectadas = st.executeUpdate(query);
            
            return filas_afectadas > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public Boleto buscar(String id_boleto) {
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM boletos WHERE id_boleto='"+ id_boleto +"'";
            rs = st.executeQuery(query);
            
            if(!rs.next()){
                return null;
            }
            
            BoletoDTO boletoDto = new BoletoDTO();
            boletoDto.id_boleto = rs.getString("id_boleto");
            boletoDto.dni_pasajero = rs.getString("dni_pasajero");
            boletoDto.id_viaje = rs.getString("id_viaje");
            boletoDto.precio = rs.getFloat("precio");
            
            return convertirDto_Boleto(boletoDto);
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean actualizar(Boleto boleto_editar) {
        try {
            
            BoletoDTO boletoDto = convertirBoleto_Dto(boleto_editar);
            
            String query = "UPDATE boletos SET "
             + "dni_pasajero = '" + boletoDto.dni_pasajero + "', "
             + "id_viaje = '" + boletoDto.id_viaje + "', "
             + "precio = " + boletoDto.precio + " "
             + "WHERE id_boleto = '" + boletoDto.id_boleto + "'";

            int rows_update = st.executeUpdate(query);
            
            
            return rows_update > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public boolean eliminar(String id_boleto) {
        try {
            String query = "DELETE FROM boletos WHERE id_boleto = '"+ id_boleto +"'";
            int rows_deleted = st.executeUpdate(query);
            
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public List<Boleto> listar() {
        try {
            String query = "SELECT * FROM boletos";
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                return null;  
            } 
            
            List<Boleto> boletos;
            boletos = new ArrayList<>();
            
            do{
                BoletoDTO boletoDto = new BoletoDTO();
                boletoDto.id_boleto = rs.getString("id_boleto");
                boletoDto.dni_pasajero = rs.getString("dni_pasajero");
                boletoDto.id_viaje = rs.getString("id_viaje");
                boletoDto.precio = rs.getFloat("precio");
                
                boletos.add(convertirDto_Boleto(boletoDto));
                
            } while(rs.next());
            
            return boletos;
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Boleto> obtenerBoletosParaViaje(String idViaje) {
    List<Boleto> boletos = new ArrayList<>();
    
    String query = "SELECT * FROM boletos WHERE id_viaje = ?";
    
    try (PreparedStatement pst = st.getConnection().prepareStatement(query)) {
        pst.setString(1, idViaje);
        
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                // Use the correct column names from the printed output
                String boletoId = rs.getString("id_boleto");
                String pasajeroId = rs.getString("dni_pasajero");
                String viajeId = rs.getString("id_viaje");
                float precio = rs.getFloat("precio");
                
                Pasajero pasajero = new PasajeroRepository(st).buscar(pasajeroId);
                Viaje viaje = new ViajeRepository(st).buscar(viajeId);
                
                Boleto boleto = new Boleto(boletoId, pasajero, viaje, precio);
                boletos.add(boleto);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return boletos;
}
}


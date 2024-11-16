package Repository;

import Models.Boleto;
import Models.Conductor;
import Models.Pasajero;
import Models.Viaje;
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

public class BoletoRepository implements CRUD<Boleto>{

    private static final String RUTA_ARCHIVO = "src/resources/boletos.json";

    private static class BoletoDTO {
        private String id_boleto;
        private String dni_pasajero;
        private String id_viaje;
        private float precio;
    }

    private static Boleto convertirDto_Boleto(BoletoDTO boletoDTO) {
        Pasajero pasajero = new PasajeroRepository().buscar(boletoDTO.dni_pasajero);
        Viaje viaje = new ViajeRepository().buscar(boletoDTO.id_viaje);
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
    
    private Conexion cx = new Conexion();
    
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

            Statement st = cx.conectar().createStatement();
            int filas_afectadas = st.executeUpdate(query);
            
            cx.desconectar();
            return filas_afectadas > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }
    
    @Override
    public Boleto buscar(String id_boleto) {
        ResultSet rs;
        
        try {
            String query = "SELECT * FROM boletos WHERE id_boleto='"+ id_boleto +"'";
            Statement st = cx.conectar().createStatement();
            rs = st.executeQuery(query);
            
            if(!rs.next()){
                cx.desconectar();
                return null;
            }
            
            BoletoDTO boletoDto = new BoletoDTO();
            boletoDto.id_boleto = rs.getString("id_boleto");
            boletoDto.dni_pasajero = rs.getString("dni_pasajero");
            boletoDto.id_viaje = rs.getString("id_viaje");
            boletoDto.precio = rs.getFloat("precio");
            cx.desconectar();
            
            return convertirDto_Boleto(boletoDto);
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
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

            Statement st = cx.conectar().createStatement();
            int rows_update = st.executeUpdate(query);
            
            cx.desconectar();
            
            return rows_update > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }
    
    @Override
    public boolean eliminar(String id_boleto) {
        try {
            String query = "DELETE FROM boletos WHERE id_boleto = '"+ id_boleto +"'";
            Statement st = cx.conectar().createStatement();
            int rows_deleted = st.executeUpdate(query);
            
            cx.desconectar();
            
            return rows_deleted > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return false;
        }
    }
    
    @Override
    public List<Boleto> listar() {
        try {
            String query = "SELECT * FROM boletos";
            Statement st = cx.conectar().createStatement();
            ResultSet rs = st.executeQuery(query);
            
            if(!rs.next()){
                cx.desconectar();
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
            
            cx.desconectar();
            return boletos;
        } catch (SQLException ex) {
            Logger.getLogger(BoletoRepository.class.getName()).log(Level.SEVERE, null, ex);
            cx.desconectar();
            return null;
        }
    }
}


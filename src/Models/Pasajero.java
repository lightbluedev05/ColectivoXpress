package Models;

import Repository.BoletoRepository;
import Repository.PasajeroRepository;
import Repository.ViajeRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.sql.Statement;


public class Pasajero extends Usuario {

    public Pasajero(String nombre, String correo, String dni, LocalDate fecha_nacimiento, String contrasena,
                    String distrito, String provincia, String departamento) {
        this.nombre = nombre;
        this.correo = correo;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.contrasena = contrasena;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
    }


    @Override
    public boolean editar_perfil(String nombre, String distrito, String provincia, String departamento, Statement st) {
        this.nombre = nombre;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        return new PasajeroRepository(st).actualizar(this);
    }

    @Override
    public boolean actualizar_contrasena(String nueva_contrasena, Statement st) {
        this.contrasena = nueva_contrasena;
        return new PasajeroRepository(st).actualizar(this);
    }
    
    public boolean actualizar_nombre(String nueva_nombre, Statement st) {
        this.nombre = nueva_nombre;
        return new PasajeroRepository(st).actualizar(this);
    }
    
    public boolean actualizar_distrito(String nueva_distrito, Statement st) {
        this.distrito = nueva_distrito;
        return new PasajeroRepository(st).actualizar(this);
    }
    
    public boolean actualizar_provincia(String nueva_provincia, Statement st) {
        this.provincia = nueva_provincia;
        return new PasajeroRepository(st).actualizar(this);
    }
    
    public boolean actualizar_departamento(String nueva_departamento, Statement st) {
        this.departamento = nueva_departamento;
        return new PasajeroRepository(st).actualizar(this);
    }
    
    public static boolean registro_pasajero(Pasajero nuevo_pasajero, Statement st){
        return new PasajeroRepository(st).crear(nuevo_pasajero);
    }

    public static boolean login(String dni, String contrasena, Statement st) {
        Pasajero pasajero = new PasajeroRepository(st).buscar(dni);
        if(pasajero==null){
            return false;
        }

        return pasajero.contrasena.equals(contrasena);
    }

     public boolean comprar_boleto(String id_viaje, Statement st) {
    // Buscar el viaje
    Viaje viaje = new ViajeRepository(st).buscar(id_viaje);
    if (viaje == null) {
        return false;
    }

    // Generar el boleto
    String idBoleto = Boleto.generarIdBoleto(st);
    Boleto nuevoBoleto = new Boleto(
        idBoleto, 
        this, 
        viaje, 
        (float)viaje.get_ruta().get_precio()
    );
    
    // Generar descripción de la ruta
    String descripcionRuta = "Viaje " + viaje.get_ruta().get_origen() + " -> " + viaje.get_ruta().get_destino();

    // Guardar el boleto
    boolean guardado = Boleto.guardar(nuevoBoleto,st);
    
    // Si se guardó, enviar correo
    if (guardado) {
        PagoMP.enviarCorreo(
            this.get_nombre(),                     // nombreCliente
            viaje.get_conductor().get_nombre(),    // nombreConductor
            descripcionRuta,                       // nombreRuta
            String.valueOf(viaje.get_ruta().get_precio()),  // precio
            viaje.get_fecha_salida().toString(),   // fechaSalida
            idBoleto,                              // codigoTicket
            this.get_correo()                      // emailTo
        );
        return true;
    }

    return false;
}
  
     public List<Viaje> ver_viajes(Statement st){
        return new ViajeRepository(st).listar();
    }
     
    public List<Viaje> ver_historial_viajes(Statement st) {
        List<Viaje> viajes_historico = new ArrayList<>();

        try {
            // Obtener la lista de boletos
            List<Boleto> boletos = new BoletoRepository(st).listar();

            // Si boletos es null, devolver lista vacía
            if (boletos == null) {
                return viajes_historico;
            }

            // Filtrar boletos por el DNI del pasajero
            for (Boleto boleto : boletos) {
                if (boleto != null && 
                    boleto.get_pasajero() != null && 
                    boleto.get_pasajero().get_dni().equals(this.get_dni())) {

                    // Verificar que el viaje no sea null antes de agregarlo
                    if (boleto.get_viaje() != null) {
                        viajes_historico.add(boleto.get_viaje());
                    }
                }
            }

            return viajes_historico;

        } catch (Exception e) {
            // Manejo de cualquier otra excepción
            System.err.println("Error al obtener historial de viajes: " + e.getMessage());
            return viajes_historico;
        }
    }

    public int calcular_edad(){
        return Period.between(this.fecha_nacimiento, LocalDate.now()).getYears();
    }
    
    public String get_nombre() {
    return nombre;
    }

    public String get_correo() {
        return correo;
    }

    public String get_dni() {
        return dni;
    }

    public LocalDate get_fecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String get_distrito() {
        return distrito;
    }

    public String get_provincia() {
        return provincia;
    }

    public String get_departamento() {
        return departamento;
}
    
}

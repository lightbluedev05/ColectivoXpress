package Models;

import Repository.BoletoRepository;
import Repository.ConductorRepository;
import Repository.PasajeroRepository;
import Repository.ViajeRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Pasajero extends Usuario {

    public static PasajeroRepository pr = new PasajeroRepository();

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
    public boolean editar_perfil(String nombre, String distrito, String provincia, String departamento) {
        this.nombre = nombre;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        return new PasajeroRepository().actualizar(this);
    }

    @Override
    public boolean actualizar_contrasena(String nueva_contrasena) {
        this.contrasena = nueva_contrasena;
        return new PasajeroRepository().actualizar(this);
    }
    
    public boolean actualizar_nombre(String nueva_nombre) {
        this.nombre = nueva_nombre;
        return new PasajeroRepository().actualizar(this);
    }
    
    public boolean actualizar_distrito(String nueva_distrito) {
        this.distrito = nueva_distrito;
        return new PasajeroRepository().actualizar(this);
    }
    
    public boolean actualizar_provincia(String nueva_provincia) {
        this.provincia = nueva_provincia;
        return new PasajeroRepository().actualizar(this);
    }
    
    public boolean actualizar_departamento(String nueva_departamento) {
        this.departamento = nueva_departamento;
        return new PasajeroRepository().actualizar(this);
    }
    
    public static boolean registro_pasajero(Pasajero nuevo_pasajero){
        return new PasajeroRepository().crear(nuevo_pasajero);
    }

    public static boolean login(String dni, String contrasena) {
        Pasajero pasajero = new PasajeroRepository().buscar(dni);
        if(pasajero==null){
            return false;
        }

        return pasajero.contrasena.equals(contrasena);
    }

     public boolean comprar_boleto(String id_viaje) {
    // Buscar el viaje
    Viaje viaje = new ViajeRepository().buscar(id_viaje);
    if (viaje == null) {
        return false;
    }

    // Verificar el pago
    String externalReference = "BOLETO-" + System.currentTimeMillis();
    if (!PagoMP.verificarEstadoPago(externalReference)) {
        return false;
    }

    // Generar el boleto
    String idBoleto = Boleto.generarIdBoleto();
    Boleto nuevoBoleto = new Boleto(
        idBoleto, 
        this, 
        viaje, 
        viaje.get_ruta().get_precio()
    );
    
    // Generar descripción de la ruta
    String descripcionRuta = "Viaje " + viaje.get_ruta().get_origen() + " -> " + viaje.get_ruta().get_destino();

    // Guardar el boleto
    boolean guardado = Boleto.guardar(nuevoBoleto);
    
    // Si se guardó, enviar correo
    if (guardado) {
        PagoMP.enviarCorreo(
            this.get_nombre(),                     // nombreCliente
            viaje.get_conductor().get_nombre(),    // nombreConductor
            descripcionRuta,         // nombreRuta
            String.valueOf(viaje.get_ruta().get_precio()),  // precio
            viaje.get_fecha_salida().toString(),   // fechaSalida
            idBoleto,                              // codigoTicket
            this.get_correo()                      // emailTo
        );
    }

    return guardado;
}
  
     public List<Viaje> ver_viajes(){
        return new ViajeRepository().listar();
    }
     
    public List<Viaje> ver_historial_viajes(){
        List<Boleto> boletos = new BoletoRepository().listar();
        List<Viaje> viajes = new ArrayList<>();

        for(Boleto boleto : boletos){
            if(boleto.get_pasajero().get_dni().equals(this.dni)){
                viajes.add(boleto.get_viaje());
            }
        }

        return viajes;
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

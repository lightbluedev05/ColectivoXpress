package Models;

import Repository.ConductorRepository;
import Repository.RegulacionLaboral;
import Repository.ViajeRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;


public class Conductor extends Usuario{
    private String dias_descanso;
    private int capacidad_vehiculo;


    public Conductor(String nombre, String correo, String dni, LocalDate fecha_nacimiento, String contrasena,
                     String distrito, String provincia, String departamento, int capacidad_vehiculo){
        this.nombre = nombre;
        this.correo = correo;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.contrasena = contrasena;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.dias_descanso = "";
        this.capacidad_vehiculo = capacidad_vehiculo;
    }
    public List<Viaje> ver_viaje_asignado(Statement st) {
        List<Viaje> todos_viajes = new ViajeRepository(st).listar();
        List<Viaje> viajes_asignados = new ArrayList<>();
        
        for (Viaje viaje : todos_viajes) {
            if (viaje.get_conductor() != null && 
                viaje.get_conductor().get_dni().equals(this.get_dni())) {
                viajes_asignados.add(viaje);
            }
        }
        return viajes_asignados;
    }

    @Override
    public boolean editar_perfil(String nombre, String distrito, String provincia, String departamento, Statement st) {
        this.nombre = nombre;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        return new ConductorRepository(st).actualizar(this);
    }

    @Override
    public boolean actualizar_contrasena(String nueva_contrasena, Statement st) {
        this.contrasena = nueva_contrasena;
        return new ConductorRepository(st).actualizar(this);
    }

    public static boolean login(String dni, String contrasena, Statement st) {
        Conductor conductor = new ConductorRepository(st).buscar(dni);
        if(conductor == null){
            return false;
        }

        return contrasena.equals(conductor.get_contrasena());
    }

    public boolean verificar_dias_descanso(Statement st){
        String[] dias = this.dias_descanso.split(",");
        
        return dias.length <= new RegulacionLaboral(st).get_limite_dias_descanso();
    }

    public int calcular_edad(){
        LocalDate hoy = LocalDate.now();
        return Period.between(this.fecha_nacimiento, hoy).getYears();
    }
    
    public boolean actualizar_departamento(String nueva_departamento, Statement st) {
        this.departamento = nueva_departamento;
        return new ConductorRepository(st).actualizar(this);
    }
    public boolean actualizar_distrito(String nueva_distrito, Statement st) {
        this.distrito = nueva_distrito;
        return new ConductorRepository(st).actualizar(this);
    }
    
    public boolean actualizar_provincia(String nueva_provincia, Statement st) {
        this.provincia = nueva_provincia;
        return new ConductorRepository(st).actualizar(this);
    }
    public boolean actualizar_nombre(String nueva_nombre, Statement st) {
        this.nombre = nueva_nombre;
        return new ConductorRepository(st).actualizar(this);
    }
    public String get_dias_descanso(){
        return dias_descanso;
    }
    public void set_dias_descanso(String dias_descanso){
        this.dias_descanso = dias_descanso;
    }
    
    public int get_capacidad_vehiculo(){
        return this.capacidad_vehiculo;
    }
    public void set_capacidad_vehiculo(int capacidad_vehiculo){
        this.capacidad_vehiculo = capacidad_vehiculo;
    }
    
    
    
    public boolean tieneViajeActivo(Statement st) {
    List<Viaje> viajesAsignados = ver_viaje_asignado(st);
    if (viajesAsignados.isEmpty()) {
        return false;
    }
    
    // Verificar si el viaje está activo (no ha pasado la fecha y hora de salida)
    Viaje viajeActivo = viajesAsignados.get(0); // Asumiendo que solo hay un viaje activo
    LocalDate fechaActual = LocalDate.now();
    LocalTime horaActual = LocalTime.now();
    
    return viajeActivo.get_fecha_salida().isEqual(fechaActual) && viajeActivo.get_hora_salida().isAfter(horaActual);
}
}
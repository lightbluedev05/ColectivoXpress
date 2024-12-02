package Models;

import Repository.ViajeRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Viaje {

    private String id_viaje;
    private LocalDate fecha_salida;
    private LocalTime hora_salida;
    private Ruta ruta;
    private Conductor conductor;
    private boolean estado;
    private List<Pasajero> pasajeros;

    public Viaje(String id_viaje, LocalDate fecha_salida, Ruta ruta, Conductor conductor, LocalTime hora_salida, boolean estado) {
        this.id_viaje = id_viaje;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.ruta = ruta;
        this.conductor = conductor;
        this.estado = estado;
    }

    public boolean validar_fecha(){
        return this.fecha_salida.isAfter(LocalDate.now());
    }

    public String get_id_viaje() {
        return id_viaje;
    }

    public void set_id_viaje(String id_viaje) {
        this.id_viaje = id_viaje;
    }

    public LocalDate get_fecha_salida() {
        return fecha_salida;
    }

    public void set_fecha_salida(LocalDate fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Ruta get_ruta() {
        return ruta;
    }

    public void set_ruta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Conductor get_conductor() {
        return conductor;
    }

    public void set_conductor(Conductor conductor) {
        this.conductor = conductor;
    }
    
    public LocalTime get_hora_salida(){
        return hora_salida;
    }
    
    public void set_hora_salida(LocalTime hora_salida){
        this.hora_salida = hora_salida;
    }
    
    public boolean get_estado(){
        return this.estado;
    }
    public void set_estado(boolean estado){
        this.estado = estado;
    }
} 
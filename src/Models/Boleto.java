package Models;

import Repository.BoletoRepository;

import java.util.Random;

public class Boleto {

    private String id_boleto;
    private Pasajero pasajero;
    private Viaje viaje;
    private float precio;

    public Boleto(String id_boleto, Pasajero pasajero, Viaje viaje, float precio) {
        this.id_boleto = id_boleto;
        this.pasajero = pasajero;
        this.viaje = viaje;
        this.precio = precio;
    }

    public static String generarIdBoleto() {
        Random random = new Random();
        String id;
        BoletoRepository repository = new BoletoRepository();
        
        do {
            int id_aux = 10000 + random.nextInt(90000);
            id = String.format("%05d", id_aux);
        } while (repository.buscar(id) != null);
        
        return id;
    }

    // Método para guardar el boleto
    public static boolean guardar(Boleto boleto) {
        return new BoletoRepository().crear(boleto);
    }



    public String get_id_boleto() {
        return id_boleto;
    }

    public void set_id_boleto(String id_boleto) {
        this.id_boleto = id_boleto;
    }

    public Pasajero get_pasajero() {
        return pasajero;
    }

    public void set_pasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Viaje get_viaje() {
        return viaje;
    }

    public void set_viaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public float get_precio() {
        return precio;
    }

    public void set_precio(float precio) {
        this.precio = precio;
    }
}


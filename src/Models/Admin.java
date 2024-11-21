package Models;

import Repository.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.sql.Statement;
//arturo vidal
public class Admin {
    private String codigo;
    private String contrasena;


    public Admin(String codigo, String contrasena){
        this.codigo = codigo;
        this.contrasena = contrasena;
    }
    

    public static boolean registrar_admin(String codigo, String contrasena, Statement st){
        Admin nuevo_admin = new Admin(codigo, contrasena);
        return new AdminRepository(st).crear(nuevo_admin);
    }

    public static boolean login_admin(String codigo, String contrasena, Statement st){
        Admin admin = new AdminRepository(st).buscar(codigo);
        if(admin == null){
            return false;
        }

        return admin.get_contrasena().equals(contrasena);
    }

    public boolean actualizar_codigo(String nuevo_codigo, Statement st){
        if(new AdminRepository(st).buscar(nuevo_codigo) != null){
            return false;
        }
        
        new AdminRepository(st).eliminar(this.get_codigo());

        this.set_codigo(nuevo_codigo);
        return new AdminRepository(st).crear(this);
    }

    public boolean actualizar_contrasena(String nueva_contrasena, Statement st){
        Admin admin = new AdminRepository(st).buscar(this.get_codigo());
        if(admin == null){
            return false;
        }
        admin.set_contrasena(nueva_contrasena);

        return new AdminRepository(st).actualizar(admin);
    }

    //------------------  ACCIONES SOBRE CONDUCTORES ---------------------

    public boolean crear_conductor(String nombre, String correo, String dni, LocalDate fecha_nacimiento, String contrasena,
                                   String distrito, String provincia, String departamento, int capacidad_vehiculo, Statement st){
        Conductor nuevo_conductor = new Conductor(nombre, correo, dni, fecha_nacimiento,
                contrasena, distrito, provincia, departamento, capacidad_vehiculo);

        return new ConductorRepository(st).crear(nuevo_conductor);
    }

    public boolean eliminar_conductor(String dni, Statement st){
        return new ConductorRepository(st).eliminar(dni);
    }

    public Conductor buscar_conductor(String dni, Statement st){
        return new ConductorRepository(st).buscar(dni);
    }

    public List<Conductor> ver_conductores( Statement st){
        return new ConductorRepository(st).listar();
    }
    
    public List<Conductor> ver_conductores_libres(Statement st){
        return new ConductorRepository(st).listar_libres();
    }

    //--------------------- ACCIONES SOBRE PASAJEROS -----------------------
    public boolean crear_pasajero(String nombre, String correo, String dni, LocalDate fecha_nacimiento,
                                  String contrasena, String distrito, String provincia, String departamento, Statement st){
        Pasajero nuevo_pasajero = new Pasajero(nombre, correo, dni, fecha_nacimiento,
                contrasena, distrito, provincia, departamento);

        return new PasajeroRepository(st).crear(nuevo_pasajero);
    }
    public boolean eliminar_pasajero(String dni, Statement st) {
        return new PasajeroRepository(st).eliminar(dni);
    }

    public Pasajero buscar_pasajero(String dni, Statement st) {
        return new PasajeroRepository(st).buscar(dni);
    }

    public List<Pasajero> ver_pasajeros(Statement st) {
        return new PasajeroRepository(st).listar();
    }

    //--------------------- ACCIONES SOBRE RUTAS -----------------------
    public boolean crear_ruta(String origen, String destino, Duration tiempo_aproximado, float precio, Statement st){
        String id_ruta;
        Random random = new Random();
        do{
            int codigo = 10000 + random.nextInt(90000);
            id_ruta = String.valueOf(codigo);
        } while(new RutaRepository(st).buscar(id_ruta)!=null);

        Ruta nueva_ruta = new Ruta(id_ruta, origen, destino, tiempo_aproximado, precio);
        return new RutaRepository(st).crear(nueva_ruta);
    }

    public boolean editar_ruta(String id_ruta, String origen, String destino, Duration tiempo_aproximado, float precio, Statement st){
        Ruta ruta_editar = new RutaRepository(st).buscar(id_ruta);
        if(ruta_editar == null){
            return false;
        }
        if(!origen.isEmpty()){
            ruta_editar.set_origen(origen);
        }
        if(!destino.isEmpty()){
            ruta_editar.set_destino(destino);
        }
        if(tiempo_aproximado != null){
            ruta_editar.set_tiempo_aproximado(tiempo_aproximado);
        }
        if(precio != 0){
            ruta_editar.set_precio(precio);
        }
        return new RutaRepository(st).actualizar(ruta_editar);
    }

    public boolean eliminar_ruta(String id_ruta, Statement st){
        return new RutaRepository(st).eliminar(id_ruta);
    }

    public Ruta buscar_ruta(String id_ruta, Statement st){
        return new RutaRepository(st).buscar(id_ruta);
    }

    public List<Ruta> ver_rutas(Statement st){
        return new RutaRepository(st).listar();
    }

    //--------------------- ACCIONES SOBRE VIAJES -----------------------
    public boolean crear_viaje(LocalDate fecha_salida, String id_ruta, String dni_conductor, LocalTime hora_salida, Statement st){
        Ruta ruta = new RutaRepository(st).buscar(id_ruta);
        if(ruta == null){
            return false;
        }
        Conductor conductor = new ConductorRepository(st).buscar(dni_conductor);
        if(conductor == null){
            return false;
        }

        String id_viaje;
        Random random = new Random();
        do{
            int codigo = 10000 + random.nextInt(90000);
            id_viaje = String.valueOf(codigo);
        } while(new ViajeRepository(st).buscar(id_viaje)!=null);

        Viaje nuevo_viaje = new Viaje(id_viaje, fecha_salida, ruta, conductor, hora_salida, true);
        return new ViajeRepository(st).crear(nuevo_viaje);
    }

    public boolean editar_viaje(String id_viaje, LocalDate fecha_salida, String id_ruta, String dni_conductor, LocalTime hora_salida, boolean estado, Statement st){
        Viaje viaje_editar = new ViajeRepository(st).buscar(id_viaje);
        if(viaje_editar == null){
            return false;
        }

        if(fecha_salida != null){
            viaje_editar.set_fecha_salida(fecha_salida);
        }
        if(!id_ruta.isEmpty()){
            viaje_editar.set_ruta(new RutaRepository(st).buscar(id_ruta));
        }
        if(!dni_conductor.isEmpty()){
            viaje_editar.set_conductor(new ConductorRepository(st).buscar(dni_conductor));
        }
        if(hora_salida != null){
            viaje_editar.set_hora_salida(hora_salida);
        }
        viaje_editar.set_estado(estado);

        return new ViajeRepository(st).actualizar(viaje_editar);
    }
    
    public List<Viaje> ver_viajes_activos(Statement st){
        return new ViajeRepository(st).listar_activos();
    }

    public boolean eliminar_viaje(String dni, Statement st){
        return new ViajeRepository(st).eliminar(dni);
    }

    public Viaje buscar_viaje(String id_viaje, Statement st){
        return new ViajeRepository(st).buscar(id_viaje);
    }

    public List<Viaje> ver_viajes(Statement st){
        return new ViajeRepository(st).listar();
    }

    public boolean editar_limite_dias_descanso(int limite_dias, Statement st){
        return new RegulacionLaboral(st).guardar_configuraciones(limite_dias);
    }


    public String get_codigo(){
        return this.codigo;
    }

    public String get_contrasena(){
        return this.contrasena;
    }

    public void set_codigo(String nuevo_codigo){
        this.codigo = nuevo_codigo;
    }

    public void set_contrasena(String nueva_contrasena){
        this.contrasena = nueva_contrasena;
    }
}

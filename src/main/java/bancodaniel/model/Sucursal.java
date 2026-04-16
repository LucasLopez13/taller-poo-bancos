package bancodaniel.model;

import bancodaniel.enums.Rol;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    private String nombre;
    private List<Persona> personas = new ArrayList<>();

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    public boolean agregarPersona(Persona persona) {
        if (buscarPersonaPorCorreo(persona.getCorreo()) != null) {
            return false;
        }
        this.personas.add(persona);
        return true;
    }
    public Persona buscarPersona(String nombre) {
        for (Persona persona : personas) {
            if (persona.getNombre().equalsIgnoreCase(nombre)) {
                return persona;
            }
        }
        return null;
    }

    public Persona buscarPersonaPorCorreo(String correo) {
        for (Persona persona : personas) {
            if (persona.getCorreo().equalsIgnoreCase(correo)) {
                return persona;
            }
        }
        return null;
    }
    public List<Persona> getPersonas() {
        return this.personas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getBalance() {
        int total = 0;

        for (Persona persona: this.personas){
            if (persona.getCuenta() != null){
                total += persona.getCuenta().getSaldo();
            }
        }
        return total;
    }

    public boolean tieneAdmin() {
        for (Persona persona : personas) {
            if (persona.getRol() == Rol.ADMIN) {
                return true;
            }
        }
        return false;
    }
}

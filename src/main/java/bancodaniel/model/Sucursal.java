package bancodaniel.model;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    private String nombre;
    private List<Persona> personas = new ArrayList<>();

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    public List<Persona> getPersonas() {
        return this.personas;
    }

    public String getNombre() {
        return this.nombre;
    }
}

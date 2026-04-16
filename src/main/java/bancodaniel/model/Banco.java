package bancodaniel.model;


import bancodaniel.enums.Rol;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nombre;
    private Persona ceo;
    private List<Sucursal> sucursales = new ArrayList<>();

    public void setCeo(Persona ceo) {
        if (ceo.getRol() == Rol.CEO) {
            this.ceo = ceo;
        } else {
            System.out.println("Error: La persona debe tener el rol de CEO");
        }
    }

    public Persona getCeo() {
        return this.ceo;
    }

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public boolean agregarSucursal(Sucursal sucursal) {
        if (buscarSucursal(sucursal.getNombre()) != null){
            return false;
        }

        this.sucursales.add(sucursal);
        return true;
    }

    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal sucursal : this.sucursales) {
            if (sucursal.getNombre().equalsIgnoreCase(nombre)) {
                return sucursal;
            }
        }
        return null;
    }

    public String getNombre(){
        return this.nombre;
    }
    public List<Sucursal> getSucursales() {
        return this.sucursales;
    }
}

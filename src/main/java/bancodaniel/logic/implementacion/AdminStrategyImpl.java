package bancodaniel.logic.implementacion;

import bancodaniel.logic.interfaces.IAdminStrategy;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;
import bancodaniel.model.Sucursal;

import java.util.List;

public class AdminStrategyImpl implements IAdminStrategy {
    private Banco banco;
    private List<Persona> personasRegistradas;

    public AdminStrategyImpl(Banco banco, List<Persona> personasRegistradas) {
        this.banco = banco;
        this.personasRegistradas = personasRegistradas;
    }

    @Override
    public void agregarPersonaASucursal(String nombreSucursal, String correo) {
        Sucursal existeSucursal = buscarSucursal(nombreSucursal);

        if (existeSucursal == null) {
            System.out.println("Error: Esa sucursal no existe");
            return;
        }

        Persona existePersona = buscarPersonaPorCorreo(correo);
        if (existePersona == null) {
            System.out.println("Error: Esa persona no esta registrada en el banco");
            return;
        }

        for (Sucursal sucursal : this.banco.getSucursales()) {
            if (sucursal.getPersonas().contains(existePersona)) {
                System.out.println("Error: " + existePersona.getNombre() + " ya esta asignado a la sucursal " + sucursal.getNombre());
                return;
            }
        }

        existeSucursal.getPersonas().add(existePersona);
        System.out.println(existePersona.getNombre() + " asignado a sucursal " + nombreSucursal + " exitosamente");
    }

    @Override
    public void verPersonasDeSucursal(String nombreSucursal) {
        Sucursal existeSucursal = buscarSucursal(nombreSucursal);

        if (existeSucursal == null){
            System.out.println("Error: Sucursal no encontrada");
            return;
        }

        System.out.println("Personas registradas en " + nombreSucursal + ": ");

        if (existeSucursal.getPersonas().isEmpty()) {
            System.out.println("Error: No hay usuarios asignados a esta sucursal");
        } else {
            for (Persona persona: existeSucursal.getPersonas()){
                System.out.println("    " + persona.getNombre());
            }
        }
    }

    private Sucursal buscarSucursal(String nombreSucursal) {
        for (Sucursal sucursal : this.banco.getSucursales()) {
            if (sucursal.getNombre().equalsIgnoreCase(nombreSucursal)) {
                return sucursal;
            }
        }
        return null;
    }

    private Persona buscarPersonaPorCorreo(String correo) {
        for (Persona persona : this.personasRegistradas) {
            if (persona.getCorreo().equalsIgnoreCase(correo)) {
                return persona;
            }
        }
        return null;
    }
}

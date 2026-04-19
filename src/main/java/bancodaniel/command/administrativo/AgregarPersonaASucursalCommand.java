package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;
import bancodaniel.model.Sucursal;

import java.util.List;

public class AgregarPersonaASucursalCommand implements Command {
    private Banco banco;
    private List<Persona> personasRegistradas;
    private String nombreSucursal;
    private String correo;

    public AgregarPersonaASucursalCommand(Banco banco, List<Persona> personasRegistradas, String nombreSucursal, String correo) {
        this.banco = banco;
        this.personasRegistradas = personasRegistradas;
        this.nombreSucursal = nombreSucursal;
        this.correo = correo;
    }

    @Override
    public void ejecutar() {
        Sucursal sucursal = this.banco.buscarSucursal(this.nombreSucursal);

        if (sucursal == null) {
            System.out.println("Sucursal no encontrada");
            return;
        }

        Persona existePersona = null;
        for (Persona persona: this.personasRegistradas) {
            if (persona.getCorreo().equalsIgnoreCase(this.correo)) {
                existePersona = persona;
                break;
            }
        }

        if (existePersona == null) {
            System.out.println("Persona no encontrada");
            return;
        }

        boolean agregado = sucursal.agregarPersona(existePersona);
        String mensaje = agregado ?
                "Persona agrega a la sucursal correctamente": "El usuario ya esta en la sucursal";
        System.out.println(mensaje);
    }
}

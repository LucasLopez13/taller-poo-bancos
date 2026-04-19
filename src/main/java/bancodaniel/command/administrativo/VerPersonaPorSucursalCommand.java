package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;
import bancodaniel.model.Sucursal;

public class VerPersonaPorSucursalCommand implements Command {
    private Banco banco;
    private String nombreSucursal;

    public VerPersonaPorSucursalCommand(Banco banco, String nombreSucursal) {
        this.banco = banco;
        this.nombreSucursal = nombreSucursal;
    }

    @Override
    public void ejecutar() {
        Sucursal sucursal = this.banco.buscarSucursal(nombreSucursal);

        if (sucursal == null){
            System.out.println("Sucursal no encontrada");
            return;
        }

        if (sucursal.getPersonas().isEmpty()) {
            System.out.println("La sucursal " + sucursal.getNombre() + " no tiene usuarios");
            return;
        }

        System.out.println("Personas registradas en " + sucursal.getNombre() + ": ");
        for (Persona persona: sucursal.getPersonas()){
            System.out.println("    " + persona.getNombre());
        }
    }
}

package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.IAdminStrategy;

public class AgregarPersonaASucursalCommand implements Command {
    private String nombreSucursal;
    private String correo;
    private IAdminStrategy strategy;

    public AgregarPersonaASucursalCommand(String nombreSucursal, String correo, IAdminStrategy strategy) {
        this.nombreSucursal = nombreSucursal;
        this.correo = correo;
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.agregarPersonaASucursal(nombreSucursal, correo);
    }
}

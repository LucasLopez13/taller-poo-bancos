package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.IAdminStrategy;

public class VerPersonaPorSucursalCommand implements Command {
    private String nombreSucursal;
    private IAdminStrategy strategy;

    public VerPersonaPorSucursalCommand(String nombreSucursal, IAdminStrategy strategy) {
        this.nombreSucursal = nombreSucursal;
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.verPersonasDeSucursal(nombreSucursal);
    }
}

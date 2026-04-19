package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.ICeoStrategy;
import bancodaniel.model.Banco;
import bancodaniel.model.Sucursal;

public class CrearSucursalCommand implements Command {
    private String nombreSucursal;
    private ICeoStrategy strategy;

    public CrearSucursalCommand(String nombreSucursal, ICeoStrategy strategy) {
        this.nombreSucursal = nombreSucursal;
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.crearSucursal(nombreSucursal);
    }
}

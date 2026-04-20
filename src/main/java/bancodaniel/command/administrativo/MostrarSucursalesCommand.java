package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.ICeoStrategy;

public class MostrarSucursalesCommand implements Command {
    private ICeoStrategy strategy;

    public MostrarSucursalesCommand(ICeoStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.mostrarSucursales();
    }
}

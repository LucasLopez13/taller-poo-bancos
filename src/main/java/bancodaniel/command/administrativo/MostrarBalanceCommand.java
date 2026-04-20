package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.ICeoStrategy;

public class MostrarBalanceCommand implements Command {
    private ICeoStrategy strategy;

    public MostrarBalanceCommand(ICeoStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.mostrarBalance();
    }
}

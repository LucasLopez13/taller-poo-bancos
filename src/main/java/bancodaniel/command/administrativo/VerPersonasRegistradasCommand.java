package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.ICeoStrategy;

public class VerPersonasRegistradasCommand implements Command {
    private ICeoStrategy strategy;

    public VerPersonasRegistradasCommand(ICeoStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.verPersonasRegistradas();
    }
}

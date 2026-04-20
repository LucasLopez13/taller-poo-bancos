package bancodaniel.command.usuario;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.IPersonaStrategy;
import bancodaniel.model.Persona;

public class VerSaldoCommand implements Command {
    private Persona persona;
    private IPersonaStrategy strategy;

    public VerSaldoCommand(Persona persona, IPersonaStrategy strategy) {
        this.persona = persona;
        this.strategy = strategy;
    }
    @Override
    public void ejecutar() {
        this.strategy.verSaldo(this.persona);
    }
}

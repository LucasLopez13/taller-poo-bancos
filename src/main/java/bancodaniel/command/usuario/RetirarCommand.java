package bancodaniel.command.usuario;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.IPersonaStrategy;
import bancodaniel.model.Persona;

public class RetirarCommand implements Command {
    private Persona persona;
    private int monto;
    private IPersonaStrategy strategy;

    public RetirarCommand(Persona persona, int monto, IPersonaStrategy strategy) {
        this.persona = persona;
        this.monto = monto;
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.retirar(this.persona.getCuenta(), this.monto);
    }
}

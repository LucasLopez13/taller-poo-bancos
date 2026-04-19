package bancodaniel.command.usuario;

import bancodaniel.command.Command;
import bancodaniel.model.Persona;

public class RetirarCommand implements Command {
    private Persona persona;
    private int monto;

    public RetirarCommand(Persona persona, int monto) {
        this.persona = persona;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        persona.getCuenta().retirar(monto);
    }
}

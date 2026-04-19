package bancodaniel.command.usuario;

import bancodaniel.command.Command;
import bancodaniel.model.Persona;

public class DepositarCommand implements Command {
    private Persona persona;
    private int monto;

    public DepositarCommand(Persona persona, int monto) {
        this.persona = persona;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        persona.getCuenta().depositar(monto);
    }
}

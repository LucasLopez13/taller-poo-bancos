package bancodaniel.command.usuario;

import bancodaniel.command.Command;
import bancodaniel.model.Persona;

public class VerSaldoCommand implements Command {
    private Persona persona;

    public VerSaldoCommand(Persona persona) {
        this.persona = persona;
    }

    @Override
    public void ejecutar() {
        System.out.println("Tu saldo actual es :" + this.persona.getCuenta().getSaldo());
    }
}

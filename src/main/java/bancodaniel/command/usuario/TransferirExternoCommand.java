package bancodaniel.command.usuario;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.IPersonaStrategy;
import bancodaniel.model.Persona;

public class TransferirExternoCommand implements Command {
    private IPersonaStrategy strategy;
    private Persona origen;
    private String codigoDestino;
    private String identificadorDestino;
    private int monto;

    public TransferirExternoCommand(Persona origen, String codigoDestino, String identificadorDestino, int monto,
            IPersonaStrategy strategy) {
        this.origen = origen;
        this.codigoDestino = codigoDestino;
        this.identificadorDestino = identificadorDestino;
        this.monto = monto;
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.transferirExterno(this.origen, this.codigoDestino, this.identificadorDestino, this.monto);
    }
}

package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.ICeoStrategy;

public class SolicitarBalanceExternoCommand implements Command {
    private ICeoStrategy strategy;
    private String codigoDestino;
    private String identificadorDestino;

    public SolicitarBalanceExternoCommand(String codigoDestino, String identificadorDestino, ICeoStrategy strategy) {
        this.codigoDestino = codigoDestino;
        this.identificadorDestino = identificadorDestino;
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.solicitarBalanceExterno(this.codigoDestino, this.identificadorDestino);
    }
}

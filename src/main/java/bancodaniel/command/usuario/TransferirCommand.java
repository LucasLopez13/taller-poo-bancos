package bancodaniel.command.usuario;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.IPersonaStrategy;
import bancodaniel.model.Persona;

public class TransferirCommand implements Command {
    private Persona origen;
    private String nombreSucursal;
    private String nombreDestinatario;
    private int monto;
    private IPersonaStrategy strategy;

    public TransferirCommand(Persona origen, String nombreSucursal, String nombreDestinatario, int monto, IPersonaStrategy strategy) {
        this.origen = origen;
        this.nombreSucursal = nombreSucursal;
        this.nombreDestinatario = nombreDestinatario;
        this.monto = monto;
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.transferir(this.origen, this.nombreSucursal, this.nombreDestinatario, this.monto);
    }
}

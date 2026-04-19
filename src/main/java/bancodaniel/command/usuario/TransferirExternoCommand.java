package bancodaniel.command.usuario;

import bancodaniel.command.Command;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;

public class TransferirExternoCommand implements Command {
    private Persona origen;
    private Banco banco;
    private String codigoDestino;
    private String identificadorDestino;
    private int monto;

    public TransferirExternoCommand(Persona origen, Banco banco, String codigoDestino, String identificadorDestino, int monto) {
        this.origen = origen;
        this.banco = banco;
        this.codigoDestino = codigoDestino;
        this.identificadorDestino = identificadorDestino;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        if(this.origen.getCuenta().getSaldo() < monto) {
            System.out.println("Saldo insuficiente");
            return;
        }

        boolean estado = this.banco.enviarTransferenciaExterna(this.codigoDestino, this.identificadorDestino, monto);

        if (estado) {
            this.origen.getCuenta().retirar(monto);
            System.out.println("Transferencia exitosa: " + monto );
        } else {
            System.out.println("No se pudo transferir");
        }
    }
}

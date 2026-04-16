package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.model.Banco;

public class SolicitarBalanceExternoCommand implements Command {
    private Banco banco;
    private String codigoDestino;
    private String idenficadorDestino;

    public SolicitarBalanceExternoCommand(Banco banco, String codigoDestino, String idenficadorDestino) {
        this.banco = banco;
        this.codigoDestino = codigoDestino;
        this.idenficadorDestino = idenficadorDestino;
    }

    @Override
    public void execute() {
        double saldo = this.banco.solicitarBalanceExterno(this.codigoDestino, this.idenficadorDestino);
        if (saldo >= 0) {
            System.out.println(this.idenficadorDestino + "su balance en el banco es de " + saldo);
        } else {
            System.out.println("No se pudo obtener el balance");
        }
    }
}

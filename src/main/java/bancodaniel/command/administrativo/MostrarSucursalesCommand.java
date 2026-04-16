package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.model.Banco;
import bancodaniel.model.Sucursal;

public class MostrarSucursalesCommand implements Command {
    private Banco banco;

    public MostrarSucursalesCommand(Banco banco) {
        this.banco = banco;
    }

    @Override
    public void execute() {
        System.out.println(this.banco.getNombre() + " tiene sucurales: ");
        for (Sucursal sucursal : this.banco.getSucursales()) {
            System.out.println("    " + sucursal.getNombre());
        }
    }
}

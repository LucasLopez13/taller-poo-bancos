package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.model.Banco;
import bancodaniel.model.Sucursal;

public class MostrarBalanceCommand implements Command {
    private Banco banco;

    public MostrarBalanceCommand(Banco banco) {
        this.banco = banco;
    }

    @Override
    public void ejecutar() {
        if(this.banco.getSucursales().isEmpty()) {
            System.out.println("No hay sucursales en el banco " + this.banco.getNombre());
            return;
        }

        int total = 0;

        for (Sucursal sucursal: this.banco.getSucursales()){
            int balanceSucursal = sucursal.getBalance();
            System.out.println("Sucural " + sucursal.getNombre() + " tiene un balance de: " + balanceSucursal);
            total += balanceSucursal;
        }

        System.out.println("Total banco: " + total);
    }
}

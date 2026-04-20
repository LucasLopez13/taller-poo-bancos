package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.logic.interfaces.ICeoStrategy;

public class AsignarAdminASucursalCommand implements Command  {
    private String nombreSucursal;
    private String correo;
    private ICeoStrategy strategy;

    public AsignarAdminASucursalCommand(String nombreSucursal, String correo, ICeoStrategy strategy) {
        this.nombreSucursal = nombreSucursal;
        this.correo = correo;
        this.strategy = strategy;
    }

    @Override
    public void ejecutar() {
        this.strategy.asignarAdminASucursal(nombreSucursal, correo);
    }
}

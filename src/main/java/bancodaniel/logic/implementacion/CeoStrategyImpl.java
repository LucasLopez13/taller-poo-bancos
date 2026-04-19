package bancodaniel.logic.implementacion;

import bancodaniel.enums.Rol;
import bancodaniel.logic.interfaces.ICeoStrategy;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;
import bancodaniel.model.Sucursal;

import java.util.List;

public class CeoStrategyImpl implements ICeoStrategy {
    private Banco banco;
    private List<Persona> personas;

    public CeoStrategyImpl(Banco banco, List<Persona> personas) {
        this.banco = banco;
        this.personas = personas;
    }

    @Override
    public void crearSucursal(String nombreSucursal) {
        if (buscarSucursal(nombreSucursal) != null) {
            System.out.println("La Sucursal ya existe");
            return;
        }

        Sucursal nuevaSucursal = new Sucursal(nombreSucursal);
        this.banco.getSucursales().add(nuevaSucursal);
        System.out.println("Sucursal nueva agregada al banco " + this.banco.getNombre() + ": " + nuevaSucursal.getNombre());
    }

    @Override
    public void mostrarBalance() {

    }

    @Override
    public void asignarAdminASucursal(String nombreSucursal, String correo) {

    }

    @Override
    public void mostrarSucursales() {

    }

    @Override
    public void verPersonasRegistradas() {

    }

    @Override
    public void solicitarBalanceExterno() {

    }


    public Sucursal buscarSucursal(String nombreSucursal) {
        for (Sucursal sucursal : this.banco.getSucursales()) {
            if (sucursal.getNombre().equalsIgnoreCase(nombreSucursal)) {
                return sucursal;
            }
        }
        return null;
    }

}

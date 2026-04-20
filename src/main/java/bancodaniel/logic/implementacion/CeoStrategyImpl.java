package bancodaniel.logic.implementacion;

import bancodaniel.enums.Rol;
import bancodaniel.logic.interfaces.ICeoStrategy;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;
import bancodaniel.model.Sucursal;

import java.util.List;

public class CeoStrategyImpl implements ICeoStrategy {
    private Banco banco;
    private List<Persona> personasRegistradas;

    public CeoStrategyImpl(Banco banco, List<Persona> personasRegistradas) {
        this.banco = banco;
        this.personasRegistradas = personasRegistradas;
    }

    @Override
    public void crearSucursal(String nombreSucursal) {
        if (buscarSucursal(nombreSucursal) != null) {
            System.out.println("Error: La Sucursal ya existe");
            return;
        }

        Sucursal nuevaSucursal = new Sucursal(nombreSucursal);
        this.banco.getSucursales().add(nuevaSucursal);
        System.out.println("Sucursal nueva agregada al banco " + this.banco.getNombre() + ": " + nuevaSucursal.getNombre());
    }

    @Override
    public void mostrarBalance() {
        if(this.banco.getSucursales().isEmpty()) {
            System.out.println("Errror: No hay sucursales en el banco " + this.banco.getNombre());
            return;
        }

        System.out.println("El banco " + this.banco.getNombre() + " tiene " + this.banco.getSucursales().size() + " sucursales");

        int total = 0;

        for (Sucursal sucursal: this.banco.getSucursales()){
            int balanceSucursal = getBalanceSucursal(sucursal);
            System.out.println(sucursal.getNombre() + " tiene un balance de: $" + balanceSucursal);
            total += balanceSucursal;
        }

        System.out.println("Total general del banco " +  this.banco.getNombre() + ": $" + total);
    }

    @Override
    public void asignarAdminASucursal(String nombreSucursal, String correo) {
        Sucursal existeSucursal = buscarSucursal(nombreSucursal);

        if (existeSucursal == null){
            System.out.println("Error: La sucursal " + nombreSucursal  + " no existe");
            return;
        }

        if (sucursalTieneAdmin(existeSucursal)) {
            System.out.println("Error: La sucursal " + nombreSucursal + " ya tiene admin");
            return;
        }

        Persona existePersona = buscarPersonaPorCorreo(correo);

        if (existePersona == null) {
            System.out.println("Error:  Persona no existe");
            return;
        }

        if (existePersona.getRol() == Rol.CEO) {
            System.out.println("Error: El usuario es ceo");
            return;
        }

        if (existePersona.getRol() == Rol.ADMIN) {
            System.out.println("Error: El usuario ya es admin");
            return;
        }

        for (Sucursal sucursal : banco.getSucursales()) {
            if (sucursal.getPersonas().contains(existePersona)) {
                System.out.println("Error: " + existePersona.getNombre() + " ya esta asignado a la sucursal: " + sucursal.getNombre());
                return;
            }
        }

        existePersona.setRol(Rol.ADMIN);
        existeSucursal.getPersonas().add(existePersona);
        System.out.println("Usuario " + existePersona.getNombre() + " promovido a Admin y asignado a la sucursal " + nombreSucursal);
    }

    @Override
    public void mostrarSucursales() {
        if (this.banco.getSucursales().isEmpty()){
            System.out.println("Error: El banco actualmente no tiene sucursales");
            return;
        }

        System.out.println("Mostrando sucursales del banco  " + this.banco.getNombre() + ":");
        for (Sucursal sucursal: banco.getSucursales()){
            System.out.println(sucursal.getNombre());
        }
    }

    @Override
    public void verPersonasRegistradas() {
        if (this.personasRegistradas.isEmpty()) {
            System.out.println("Error: No hay usuarios registrados.");
            return;
        }

        System.out.println("Personas registradas en  el banco " + this.banco.getNombre() + ": " + this.personasRegistradas.size());

        for (Persona persona: this.personasRegistradas){
            System.out.println("Nombre: " + persona.getNombre());
            if (persona.getCuenta() != null) {
                System.out.println("Tipo de cuenta: " + persona.getCuenta().getTipo());
                System.out.println("Saldo en cuenta: $" + persona.getCuenta().getSaldo());
            }
            System.out.println("Correo electronico: " + persona.getCorreo());
            System.out.println("Rol: " + persona.getRol());
        }
    }

    @Override
    public void solicitarBalanceExterno(String codigoDestino, String identificadorDestino) {
        if (this.banco.getMediator() == null) {
            System.out.println("Error: El banco no está registrado en ninguna red interbancaria.");
            return;
        }

        double saldo = this.banco.getMediator().solicitarBalance(codigoDestino, identificadorDestino);

        if (saldo >= 0) {
            System.out.println("El saldo de " + identificadorDestino + " en el banco " + codigoDestino + " es de: $" + saldo);
        } else {
            System.out.println("Error: No se pudo obtener el balance externo para " + identificadorDestino);
        }
    }

    private Persona buscarPersonaPorCorreo(String correo) {
        for (Persona persona : this.personasRegistradas) {
            if (persona.getCorreo().equalsIgnoreCase(correo)) {
                return persona;
            }
        }
        return null;
    }

    private boolean sucursalTieneAdmin(Sucursal sucursal) {
        for (Persona persona : sucursal.getPersonas()) {
            if (persona.getRol() == Rol.ADMIN) {
                return true;
            }
        }
        return false;
    }

    private int getBalanceSucursal(Sucursal sucursal) {
        int total = 0;
        for (Persona persona : sucursal.getPersonas()){
            if (persona.getCuenta() != null) {
                total += persona.getCuenta().getSaldo();
            }
        }
        return total;
    }

    private Sucursal buscarSucursal(String nombreSucursal) {
        for (Sucursal sucursal : this.banco.getSucursales()) {
            if (sucursal.getNombre().equalsIgnoreCase(nombreSucursal)) {
                return sucursal;
            }
        }
        return null;
    }
}

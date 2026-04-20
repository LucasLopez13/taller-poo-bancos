package bancodaniel.model;

import interbancario.BancoRed;
import interbancario.MediatorInterbancario;

import java.util.ArrayList;
import java.util.List;

public class Banco implements BancoRed {
    private String nombre;
    private Persona ceo;
    private List<Sucursal> sucursales = new ArrayList<>();
    private MediatorInterbancario mediator;
    private String codigoBanco = "BANCO_DANIEL";

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public void setCeo(Persona ceo) {
        this.ceo = ceo;
    }

    public Persona getCeo() {
        return this.ceo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Sucursal> getSucursales() {
        return this.sucursales;
    }

    @Override
    public String getCodigoBanco() {
        return codigoBanco;
    }

    @Override
    public void setMediator(MediatorInterbancario mediator) {
        this.mediator = mediator;
    }

    @Override
    public boolean recibirTransferenciaExterna(String identificadorDestino, double monto) {
        Persona persona = buscarPersonaPorCorreoGlobal(identificadorDestino);
        if (persona.getCuenta() != null) {
            persona.getCuenta().setSaldo(persona.getCuenta().getSaldo() + (int) monto);
            return true;
        }
        return false;
    }

    @Override
    public double obtenerBalanceExterno(String identificadorDestino) {
        Persona persona = buscarPersonaPorCorreoGlobal(identificadorDestino);
        if (persona.getCuenta() != null) {
            return (double) persona.getCuenta().getSaldo();
        }
        return -1;
    }

    public MediatorInterbancario getMediator() {
        return this.mediator;
    }

    private Persona buscarPersonaPorCorreoGlobal(String correo) {
        for (Sucursal sucursal: this.sucursales) {
            for (Persona persona: sucursal.getPersonas()) {
                if (persona.getCorreo().equalsIgnoreCase(correo)) {
                    return persona;
                }
            }
        }
        return null;
    }
}

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
        // for (Sucursal sucursal: this.sucursales) {
        // Persona persona = sucursal.buscarPersonaPorCorreo(identificadorDestino);
        // if (persona != null) {
        // persona.getCuenta().depositar((int) monto);
        // return true;
        // }
        // }
        return false;
    }

    @Override
    public double obtenerBalanceExterno(String identificadorDestino) {
        // for (Sucursal sucursal: this.sucursales) {
        // Persona persona = sucursal.buscarPersonaPorCorreo(identificadorDestino);
        // if (persona != null) {
        // return persona.getCuenta().getSaldo();
        // }
        // }
        return -1;
    }

    public boolean enviarTransferenciaExterna(String codigoDestino, String identifadorDestino, int monto) {
        // if (this.mediator != null) {
        // return this.mediator.enviarTransferencia(this.nombre, codigoDestino,
        // identifadorDestino, monto);
        // }
        // System.out.println("No se pudo realizar la transferencia");
        return false;
    }

    public MediatorInterbancario getMediator() {
        return this.mediator;
    }

    public double solicitarBalanceExterno(String codigoDestino, String identificadorDestino) {
        // if (this.mediator != null) {
        // return this.mediator.solicitarBalance(codigoDestino, identificadorDestino);
        // }
        //
        // System.out.println("Mediador no existe");
        return -1;
    }
}

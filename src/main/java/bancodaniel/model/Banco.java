package bancodaniel.model;


import bancodaniel.enums.Rol;
import interbancario.BancoRed;
import interbancario.MediatorInterbancario;

import java.util.ArrayList;
import java.util.List;

public class Banco implements BancoRed {
    private String nombre;
    private Persona ceo;
    private List<Sucursal> sucursales = new ArrayList<>();
    private MediatorInterbancario mediator;

    public void setCeo(Persona ceo) {
        if (ceo.getRol() == Rol.CEO) {
            this.ceo = ceo;
        } else {
            System.out.println("La persona debe tener un rol de Ceo para utilizar este comando");
        }
    }

    public Persona getCeo() {
        return this.ceo;
    }

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public boolean agregarSucursal(Sucursal sucursal) {
        if (buscarSucursal(sucursal.getNombre()) != null){
            return false;
        }

        this.sucursales.add(sucursal);
        return true;
    }

    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal sucursal : this.sucursales) {
            if (sucursal.getNombre().equalsIgnoreCase(nombre)) {
                return sucursal;
            }
        }
        return null;
    }

    public String getNombre(){
        return this.nombre;
    }
    public List<Sucursal> getSucursales() {
        return this.sucursales;
    }

    @Override
    public String getCodigoBanco() {
        // TODO poner codigo en comun de intermunacion con lucas luego
        return this.getNombre();
    }

    @Override
    public void setMediator(MediatorInterbancario mediator) {
        this.mediator = mediator;
    }

    @Override
    public boolean recibirTransferenciaExterna(String identificadorDestino, double monto) {
        for (Sucursal sucursal: this.sucursales) {
            Persona persona = sucursal.buscarPersona(identificadorDestino);
            if (persona != null) {
                persona.getCuenta().depositar((int) monto);
                return true;
            }
        }
        return false;
    }

    @Override
    public double obtenerBalanceExterno(String identificadorDestino) {
        for (Sucursal sucursal: this.sucursales) {
            Persona persona = sucursal.buscarPersona(identificadorDestino);
            if (persona != null) {
                return persona.getCuenta().getSaldo();
            }
        }
        return -1;
    }

    public boolean enviarTransferenciaExterna(String codigoDestino, String identifadorDestino, int monto) {
        if (this.mediator != null) {
            return this.mediator.enviarTransferencia(this.nombre, codigoDestino, identifadorDestino, monto);
        }
        System.out.println("No se pudo realizar la transferencia");
        return false;
    }


}

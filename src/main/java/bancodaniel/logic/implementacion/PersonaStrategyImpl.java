package bancodaniel.logic.implementacion;

import bancodaniel.logic.interfaces.IPersonaStrategy;
import bancodaniel.model.Banco;
import bancodaniel.model.Cuenta;
import bancodaniel.model.Persona;
import bancodaniel.model.Sucursal;

public class PersonaStrategyImpl implements IPersonaStrategy {
    private Banco banco;

    public PersonaStrategyImpl(Banco banco) {
        this.banco = banco;
    }

    @Override
    public void depositar(Cuenta cuenta, int monto) {
        if (monto > 0) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            System.out.println("Se acredito un monto de $" + monto + ", su saldo es de $" + cuenta.getSaldo());
        } else {
            System.out.println("Error: El monto debe ser mayor a 0.");
        }
    }

    @Override
    public void retirar(Cuenta cuenta, int monto) {
        if (monto > 0 && monto <= cuenta.getSaldo()) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            System.out.println("Se retiro un monto de $" + monto + ", su saldo es de " + cuenta.getSaldo());
        } else {
            System.out.println("Error: No se pudo realizar el retiro, verifique su saldo.");
        }
    }

    @Override
    public void transferir(Persona origen, String nombreSucursal, String nombreDestinatario, int monto) {
        Sucursal sucursalDestino = buscarSucursal(nombreSucursal);

        if (sucursalDestino == null) {
            System.out.println("Error: La sucursal " + nombreSucursal  + " no existe");
            return;
        }

        Persona destino = buscarPersona(nombreDestinatario, sucursalDestino);

        if (destino == null) {
            System.out.println("Error: Destinatario no existe");
            return;
        }

        Cuenta cuentaOrigen = origen.getCuenta();
        Cuenta cuentaDestino = destino.getCuenta();

        if (monto > 0 && cuentaOrigen.getSaldo() >= monto) {
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
            System.out.println("El dinero fue enviado correctamente " + nombreDestinatario + ", por un monto de  $" + monto);
        } else {
            System.out.println("Error: No se puedo enviar el dinero");
        }
    }

    @Override
    public void verSaldo(Persona persona) {
        System.out.println("Su saldo actual es de: $" + persona.getCuenta().getSaldo());
    }

    @Override
    public void transferirExterno(Persona origen, String codigoDestino, String identificadorDestino, int monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto debe ser mayor a 0.");
            return;
        }

        if (origen.getCuenta().getSaldo() < monto) {
            System.out.println("Error: Saldo insuficiente para realizar la transferencia externa.");
            return;
        }

        if (this.banco.getMediator() == null) {
            System.out.println("Error: El banco no está registrado en ninguna red interbancaria.");
            return;
        }

        boolean exito = this.banco.getMediator().enviarTransferencia(
                this.banco.getCodigoBanco(),
                codigoDestino,
                identificadorDestino,
                monto
        );

        if (exito) {
            this.retirar(origen.getCuenta(), monto);
            System.out.println("Transferencia externa de $" + monto + " a " + identificadorDestino + " (" + codigoDestino + ") exitosa.");
        } else {
            System.out.println("Error: La transferencia externa no pudo ser completada.");
        }
    }

    private Sucursal buscarSucursal(String nombre) {
        for (Sucursal sucursal : this.banco.getSucursales()) {
            if (sucursal.getNombre().equalsIgnoreCase(nombre)) {
                return sucursal;
            }
        }
        return null;
    }

    private Persona buscarPersona(String nombre, Sucursal sucursal) {
        for (Persona persona : sucursal.getPersonas()) {
            if (persona.getNombre().equalsIgnoreCase(nombre)) {
                return persona;
            }
        }
        return null;
    }
}

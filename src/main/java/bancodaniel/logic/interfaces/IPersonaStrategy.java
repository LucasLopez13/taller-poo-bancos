package bancodaniel.logic.interfaces;

import bancodaniel.model.Cuenta;
import bancodaniel.model.Persona;

public interface IPersonaStrategy {
    void depositar(Cuenta cuenta, int monto);
    void retirar(Cuenta cuenta, int monto);
    void transferir(Persona origen, String nombreSucursal, String nombreDestinatario, int monto);
    void verSaldo(Persona persona);
    void transferirExterno();
}

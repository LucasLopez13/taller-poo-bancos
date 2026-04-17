package bancolucas.estrategias;

import bancolucas.dominio.Banco;
import bancolucas.dominio.Cuenta;

public interface TransaccionStrategy {
    /*
    Patron de diseño Strategy para realizar transacciones
    Metodo ejecutar que van a implementar las diferentes estrategias
    */
    void ejecutar(Cuenta origen, String emailDestino, double monto, Banco banco);
}

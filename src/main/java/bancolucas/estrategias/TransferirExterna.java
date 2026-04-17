package bancolucas.estrategias;

import bancolucas.dominio.Banco;
import bancolucas.dominio.Cuenta;

public class TransferirExterna implements TransaccionStrategy{
    private String codigoBancoDestino;

    public TransferirExterna(String codigoBancoDestino) {
        this.codigoBancoDestino = codigoBancoDestino;
    }

    @Override
    public void ejecutar(Cuenta origen, String emailDestino, double monto, Banco banco) {
        if (monto > 0 && monto <= origen.getSaldo()) {
            boolean exito = banco.getMediator().enviarTransferencia(banco.getCodigoBanco(), codigoBancoDestino, emailDestino, monto);

            if (exito) {
                origen.restarSaldo(monto);
                System.out.println("Éxito: Transferencia interbancaria enviada a " + codigoBancoDestino);
            } else {
                System.out.println("Error: Operación rechazada por la red o cuenta inexistente.");
            }
        } else {
            System.out.println("Error: Saldo insuficiente.");
        }
    }
}

package interbancario;

import java.util.HashMap;
import java.util.Map;

public class RedCentral implements MediatorInterbancario{
    private Map<String, BancoRed> bancosAdheridos = new HashMap<>();

    @Override
    public void registrarBanco(BancoRed banco) {
        bancosAdheridos.put(banco.getCodigoBanco(), banco);
        System.out.println("[RED] Banco " + banco.getCodigoBanco() + " adherido exitosamente.");
    }

    @Override
    public boolean enviarTransferencia(String codigoOrigen, String codigoDestino, String identificadorDestino, double monto) {
        System.out.println("[RED] " + codigoOrigen + " solicita transferir $" + monto + " a " + codigoDestino);

        BancoRed bancoDestino = bancosAdheridos.get(codigoDestino);
        if (bancoDestino != null) {
            return bancoDestino.recibirTransferenciaExterna(identificadorDestino, monto);
        } else {
            System.out.println("[RED] Error: El banco destino no está en la red.");
            return false;
        }
    }

    @Override
    public double solicitarBalance(String codigoDestino, String identificadorDestino) {
        BancoRed bancoDestino = bancosAdheridos.get(codigoDestino);
        if (bancoDestino != null) {
            return bancoDestino.obtenerBalanceExterno(identificadorDestino);
        }
        return -1;
    }
}

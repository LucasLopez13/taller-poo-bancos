package interbancario;

public interface MediatorInterbancario {
    void registrarBanco(BancoRed banco);
    boolean enviarTransferencia(String codigoOrigen, String codigoDestino, String identificadorDestino, double monto);
    double solicitarBalance(String codigoDestino, String identificadorDestino);
}

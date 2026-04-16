package interbancario;

public interface BancoRed {
    String getCodigoBanco();
    void setMediator(MediatorInterbancario mediator);

    boolean recibirTransferenciaExterna(String identificadorDestino, double monto);
    double obtenerBalanceExterno(String identificadorDestino);
}

package bancodaniel.model;

import bancodaniel.enums.TipoCuenta;

public class Cuenta {
    private TipoCuenta tipo;
    private int saldo;

    public Cuenta(TipoCuenta tipo) {
        this.tipo = tipo;
        this.saldo = 0;
    }

    public void depositar(int monto) {
        if (monto > 0) this.saldo += monto;
    }

    public void retirar(int monto) {
        if (monto <= 0) return;

        if (monto <= this.saldo) {
            this.saldo -= monto;
        }
    }

    public void enviarDinero(Cuenta destino, int monto) {
        if (monto > 0 && this.saldo >= monto) {
            saldo -= monto;
            destino.depositar(monto);
            System.out.println("Dinero enviado correctamente. Por un monto de: " + monto);
            return;
        }
        System.out.println("No se puedo enviar el dinero, verifique bien los datos");
    }

    public int getSaldo() {
        return this.saldo;
    }
    public TipoCuenta  getTipo(){
        return this.tipo;
    }
}

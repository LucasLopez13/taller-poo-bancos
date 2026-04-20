package bancodaniel.model;

import bancodaniel.enums.TipoCuenta;

public class Cuenta {
    private TipoCuenta tipo;
    private int saldo;

    public Cuenta(TipoCuenta tipo) {
        this.tipo = tipo;
        this.saldo = 0;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public TipoCuenta getTipo() {
        return this.tipo;
    }
}

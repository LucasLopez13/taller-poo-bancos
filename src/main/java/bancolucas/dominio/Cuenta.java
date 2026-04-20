package bancolucas.dominio;

public class Cuenta {
    private String nombre;
    private int edad;
    private String email;
    private String direccion;
    private TipoDeCuenta tipoDeCuenta;
    private double saldo;
    private Sucursal sucursal;
    private boolean solicitoBaja;

    public Cuenta(String nombre, int edad, String email, String direccion, TipoDeCuenta tipoDeCuenta, Sucursal sucursal) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.direccion = direccion;
        this.tipoDeCuenta = tipoDeCuenta;
        this.sucursal = sucursal;
        this.saldo = 0;
        this.solicitoBaja = false;
    }

    public void sumarSaldo(double monto) {
        this.saldo += monto;
    }

    public void restarSaldo(double monto) {
        this.saldo = Math.max(0.0, this.saldo - monto);
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public double getSaldo() {
        return saldo;
    }

    public void solicitarBaja() {
        this.solicitoBaja = true;
    }

    public boolean isSolicitoBaja() {
        return solicitoBaja;
    }
}

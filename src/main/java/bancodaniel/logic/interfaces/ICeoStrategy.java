package bancodaniel.logic.interfaces;

public interface ICeoStrategy {
    void crearSucursal(String nombreSucursal);
    void mostrarBalance();
    void asignarAdminASucursal(String nombreSucursal, String correo);
    void mostrarSucursales();
    void verPersonasRegistradas();
    void solicitarBalanceExterno(String codigoDestino, String identificadorDestino);
}

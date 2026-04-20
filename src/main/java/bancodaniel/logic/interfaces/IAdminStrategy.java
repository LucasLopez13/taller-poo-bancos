package bancodaniel.logic.interfaces;

public interface IAdminStrategy {
    void agregarPersonaASucursal(String nombreSucursal, String correo);
    void verPersonasDeSucursal(String nombreSucursal);
}

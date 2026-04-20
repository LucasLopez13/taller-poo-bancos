package bancodaniel.menu;

import bancodaniel.command.administrativo.*;
import bancodaniel.logic.implementacion.AdminStrategyImpl;
import bancodaniel.logic.interfaces.IAdminStrategy;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;

import java.util.List;
import java.util.Scanner;

public class MenuAdmin implements MenuStrategy {
    private Persona admin;
    private IAdminStrategy strategy;


    public MenuAdmin(Persona admin, Banco banco, List<Persona> personasRegistradas) {
        this.admin = admin;
        this.strategy = new AdminStrategyImpl(banco, personasRegistradas);
    }

    @Override
    public void mostrar() {
        System.out.println("1. Agregar usuario a sucursal");
        System.out.println("2. Ver usuarios de sucursal");
        System.out.println("0. Salir");
    }

    @Override
    public void manejarOpcion(String opcion, Scanner sc) {
        String nombreSucursal;
        switch (opcion) {
            case "1":
                System.out.println("Nombre de la sucursal: ");
                nombreSucursal = sc.nextLine();
                System.out.println("Correo del usuario:");
                String correo = sc.nextLine();
                new AgregarPersonaASucursalCommand(nombreSucursal, correo, strategy).ejecutar();
                break;
            case "2":
                System.out.println("Nombre de la sucursal: ");
                nombreSucursal = sc.nextLine();
                new VerPersonaPorSucursalCommand(nombreSucursal, strategy).ejecutar();
                break;
            case "0":
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }
}

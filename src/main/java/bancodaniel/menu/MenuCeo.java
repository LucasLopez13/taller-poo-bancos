package bancodaniel.menu;

import bancodaniel.command.administrativo.*;
import bancodaniel.logic.implementacion.AdminStrategyImpl;
import bancodaniel.logic.implementacion.CeoStrategyImpl;
import bancodaniel.logic.interfaces.IAdminStrategy;
import bancodaniel.logic.interfaces.ICeoStrategy;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;

import java.util.List;
import java.util.Scanner;

public class MenuCeo implements MenuStrategy {
    private ICeoStrategy strategy;
    private IAdminStrategy adminStrategy;

    public MenuCeo(Banco banco, List<Persona> personasRegistradas) {
        this.strategy = new CeoStrategyImpl(banco, personasRegistradas);
        this.adminStrategy = new AdminStrategyImpl(banco, personasRegistradas);
    }

    @Override
    public void mostrar() {
        System.out.println("1. Crear nueva sucursal");
        System.out.println("2. Ver todas las sucursales");
        System.out.println("3. Ver balance general");
        System.out.println("4. Ver usuarios registrados");
        System.out.println("5. Agregar admin a sucursal");
        System.out.println("6. Ver usuarios de sucursal");
        System.out.println("7. Solicitar balance externo");
        System.out.println("0. Salir");
    }

    @Override
    public void manejarOpcion(String opcion, Scanner sc) {
        String nombreSucursal;
        switch (opcion) {
            case "1":
                System.out.print("Nombre de la sucursal: ");
                nombreSucursal = sc.nextLine();
                new CrearSucursalCommand(nombreSucursal, strategy).ejecutar();
                break;
            case "2":
                new MostrarSucursalesCommand(strategy).ejecutar();
                break;
            case "3":
                new MostrarBalanceCommand(strategy).ejecutar();
                break;
            case "4":
                new VerPersonasRegistradasCommand(strategy).ejecutar();
                break;
            case "5":
                System.out.println("Nombre de la sucursal: ");
                nombreSucursal = sc.nextLine();
                System.out.println("Correo del usuario:");
                String correo = sc.nextLine();
                new AsignarAdminASucursalCommand(nombreSucursal, correo, strategy).ejecutar();
                break;
            case "6":
                System.out.println("Nombre de la sucursal: ");
                nombreSucursal = sc.nextLine();
                new VerPersonaPorSucursalCommand(nombreSucursal, adminStrategy).ejecutar();
                break;
            case "7":
                System.out.println("Identificador de banco destino: ");
                String codigoBanco = sc.nextLine();
                System.out.println("Identificador usuario: ");
                String identificadorDestino = sc.nextLine();
                new SolicitarBalanceExternoCommand(codigoBanco, identificadorDestino, strategy).ejecutar();
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

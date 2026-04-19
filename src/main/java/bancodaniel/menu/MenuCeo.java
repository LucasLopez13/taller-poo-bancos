package bancodaniel.menu;

import bancodaniel.command.administrativo.*;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;

import java.util.List;
import java.util.Scanner;

public class MenuCeo implements MenuStrategy {
    private Banco banco;
    private List<Persona> personasRegistradas;

    public MenuCeo(Banco banco, List<Persona> personasRegistradas) {
        this.banco = banco;
        this.personasRegistradas = personasRegistradas;
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
        switch (opcion) {
            case "1":
                System.out.print("Nombre de la sucursal: ");
                String nombreSucursal = sc.nextLine();
                new CrearSucursalCommand(this.banco, nombreSucursal).ejecutar();
                break;
            case "2":
                new MostrarSucursalesCommand(this.banco).ejecutar();
                break;
            case "3":
                new MostrarBalanceCommand(this.banco).ejecutar();
                break;
            case "4":
                new VerPersonasRegistradasCommand(personasRegistradas).ejecutar();
                break;
            case "5":
                System.out.println("Nombre de la sucursal: ");
                nombreSucursal = sc.nextLine();
                System.out.println("Correo del usuario:");
                String correo = sc.nextLine();
                new AsignarAdminASucursalCommand(banco, personasRegistradas, nombreSucursal, correo).ejecutar();
                break;
            case "6":
                System.out.println("Nombre de la sucursal: ");
                nombreSucursal = sc.nextLine();
                new VerPersonaPorSucursalCommand(this.banco, nombreSucursal).ejecutar();
                break;
            case "7":
                System.out.println("Identificador de banco destino: ");
                String codigoBanco = sc.nextLine();
                System.out.println("Identificador usuario: ");
                String idenficadorDestino = sc.nextLine();
                new SolicitarBalanceExternoCommand(this.banco, codigoBanco, idenficadorDestino).ejecutar();
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

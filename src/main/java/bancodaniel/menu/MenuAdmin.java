package bancodaniel.menu;

import bancodaniel.command.administrativo.*;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;

import java.util.List;
import java.util.Scanner;

public class MenuAdmin implements MenuStrategy {
    private Persona admin;
    private Banco banco;

    public MenuAdmin(Persona admin, Banco banco, List<Persona> personasRegistradas) {
        this.admin = admin;
        this.banco = banco;
        this.personasRegistradas = personasRegistradas;
    }

    private List<Persona> personasRegistradas;

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
                new AgregarPersonaASucursalCommand(banco, personasRegistradas, nombreSucursal, correo).execute();
                break;
            case "2":
                System.out.println("Nombre de la sucursal: ");
                nombreSucursal = sc.nextLine();
                new VerPersonaPorSucursalCommand(this.banco, nombreSucursal).execute();
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

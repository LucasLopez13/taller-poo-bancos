package bancodaniel.menu;

import bancodaniel.command.usuario.DepositarCommand;
import bancodaniel.command.usuario.RetirarCommand;
import bancodaniel.command.usuario.TransferirCommand;
import bancodaniel.command.usuario.VerSaldoCommand;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;

import java.util.Scanner;

public class MenuUsuario implements MenuStrategy{
    private Persona persona;

    public MenuUsuario(Persona persona, Banco banco) {
        this.persona = persona;
        this.banco = banco;
    }

    private Banco banco;

    @Override
    public void mostrar() {
        System.out.println("1. Depositar");
        System.out.println("2. Retirar");
        System.out.println("3. Transferir");
        System.out.println("4. Saldo");
        System.out.println("0. Salir");
    }

    @Override
    public void manejarOpcion(String opcion, Scanner sc) {
        switch (opcion){
            case "1":
                System.out.print("Monto a depositar: ");
                int depositar = Integer.parseInt(sc.nextLine());
                new DepositarCommand(persona, depositar).execute();
                break;
            case "2":
                System.out.print("Monto a retirar: ");
                int retirar = Integer.parseInt(sc.nextLine());
                new RetirarCommand(persona, retirar).execute();
                break;
            case "3":
                System.out.println("Sucursal destino: ");
                String nombreSucursal = sc.nextLine();
                System.out.println("Nombre del destinatario: ");
                String nombreDestinatario = sc.nextLine();
                System.out.println("Monto a transferir: ");
                int monto = Integer.parseInt(sc.nextLine());
                new TransferirCommand(persona, banco, nombreSucursal, nombreDestinatario, monto).execute();
                break;
            case "4":
                new VerSaldoCommand(persona).execute();
                break;
            case "0":
                System.out.println("Saliendo");
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}

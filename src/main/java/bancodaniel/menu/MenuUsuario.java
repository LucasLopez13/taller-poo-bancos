package bancodaniel.menu;

import bancodaniel.command.usuario.*;
import bancodaniel.logic.implementacion.PersonaStrategyImpl;
import bancodaniel.logic.interfaces.IPersonaStrategy;
import bancodaniel.model.Banco;
import bancodaniel.model.Persona;

import java.util.Scanner;

public class MenuUsuario implements MenuStrategy{
    private Persona persona;
    private IPersonaStrategy strategy;

    public MenuUsuario(Persona persona, Banco banco) {
        this.persona = persona;
        this.strategy = new PersonaStrategyImpl(banco);
    }

    @Override
    public void mostrar() {
        System.out.println("1. Depositar");
        System.out.println("2. Retirar");
        System.out.println("3. Transferir");
        System.out.println("4. Saldo");
        System.out.println("5. Transferir internacional");
        System.out.println("0. Salir");
    }

    @Override
    public void manejarOpcion(String opcion, Scanner sc) {
        switch (opcion){
            case "1":
                System.out.print("Monto a depositar: ");
                int depositar = Integer.parseInt(sc.nextLine());
                new DepositarCommand(persona, depositar, strategy).ejecutar();
                break;
            case "2":
                System.out.print("Monto a retirar: ");
                int retirar = Integer.parseInt(sc.nextLine());
                new RetirarCommand(persona, retirar, strategy).ejecutar();
                break;
            case "3":
                System.out.println("Sucursal destino: ");
                String nombreSucursal = sc.nextLine();
                System.out.println("Nombre del destinatario: ");
                String nombreDestinatario = sc.nextLine();
                System.out.println("Monto a transferir: ");
                int monto = Integer.parseInt(sc.nextLine());
                new TransferirCommand(persona, nombreSucursal, nombreDestinatario, monto, strategy).ejecutar();
                break;
            case "4":
                new VerSaldoCommand(persona, strategy).ejecutar();
                break;
            case "5":
                System.out.println("Codigo del banco externo: ");
                String codigoDestino = sc.nextLine();
                System.out.println("Codigo identifcador: ");
                String identificadorDestino = sc.nextLine();
                System.out.println("Monto a transferir: ");
                monto = Integer.parseInt(sc.nextLine());
                new TransferirExternoCommand(persona, codigoDestino, identificadorDestino, monto, strategy).ejecutar();
                break;
            case "0":
                System.out.println("Saliendo");
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}

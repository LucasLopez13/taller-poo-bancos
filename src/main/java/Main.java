import bancodaniel.model.PortalBancarioDaniel;
import bancolucas.interfazUsuario.PortalBancario;
import interbancario.MediatorInterbancario;
import interbancario.RedCentral;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MediatorInterbancario redCentral = new RedCentral();

        bancolucas.dominio.Banco bancoLucas = new bancolucas.dominio.Banco();
        bancodaniel.model.Banco bancoDaniel = new bancodaniel.model.Banco("BANCO_DANIEL");

        redCentral.registrarBanco(bancoLucas);
        bancoLucas.setMediator(redCentral);

        redCentral.registrarBanco(bancoDaniel);
        bancoDaniel.setMediator(redCentral);

        PortalBancarioDaniel portalDaniel = new PortalBancarioDaniel(bancoDaniel);

        Scanner sc = new Scanner(System.in);
        String seleccion = "";

        while (!seleccion.equals("0")) {
            System.out.println("\n=======================================");
            System.out.println("  --- RED INTERBANCARIA UNIFICADA ---  ");
            System.out.println("=======================================");
            System.out.println("¿En qué banco desea operar?");
            System.out.println("1. Banco Lucas (" + bancoLucas.getCodigoBanco() + ")");
            System.out.println("2. Banco Daniel (" + bancoDaniel.getCodigoBanco() + ")");
            System.out.println("0. Apagar Sistema");
            System.out.print("Selección: ");

            seleccion = sc.nextLine();

            if (seleccion.equals("1")) {
                PortalBancario portalLucas = new PortalBancario(bancoLucas);
                portalLucas.iniciar();

            } else if (seleccion.equals("2")) {
                portalDaniel.iniciar(sc);

            } else if (seleccion.equals("0")) {
                System.out.println("Apagando la Red Interbancaria... ¡Hasta luego!");
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        sc.close();
    }
    }

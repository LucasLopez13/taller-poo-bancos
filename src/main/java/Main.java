import bancodaniel.model.Persona;
import bancolucas.interfazUsuario.PortalBancario;
import interbancario.MediatorInterbancario;
import interbancario.RedCentral;

import java.util.ArrayList;
import java.util.List;
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

        Scanner sc = new Scanner(System.in);
        System.out.println("--- RED INTERBANCARIA UNIFICADA ---");
        System.out.println("¿En qué banco desea operar?");
        System.out.println("1. Banco Lucas (" + bancoLucas.getCodigoBanco() + ")");
        System.out.println("2. Banco Daniel (" + bancoDaniel.getCodigoBanco() + ")");
        System.out.print("Selección: ");

        String seleccion = sc.nextLine();

        List<Persona> personasDaniel = new ArrayList<>();

        bancodaniel.model.Persona ceoDaniel = new bancodaniel.model.Persona.Builder()
                .setNombre("Agustin")
                .setDireccion("Calle Admin 1")
                .setCorreo("test")
                .setCuenta(new bancodaniel.model.Cuenta(bancodaniel.enums.TipoCuenta.CUENTA_CORRIENTE))
                .setPassword("123")
                .setRol(bancodaniel.enums.Rol.CEO)
                .build();
        personasDaniel.add(ceoDaniel);
        bancoDaniel.setCeo(ceoDaniel);

        bancodaniel.model.Persona usuarioPruebaDaniel = new bancodaniel.model.Persona.Builder()
                .setNombre("Usuario Prueba")
                .setDireccion("Calle 123")
                .setCorreo("asd@mail.com")
                .setCuenta(new bancodaniel.model.Cuenta(bancodaniel.enums.TipoCuenta.CUENTA_CORRIENTE))
                .setPassword("asd")
                .setRol(bancodaniel.enums.Rol.USUARIO)
                .build();

        bancodaniel.model.Sucursal sucursalDaniel = new bancodaniel.model.Sucursal("Sucursal Central");
        sucursalDaniel.agregarPersona(usuarioPruebaDaniel);
        bancoDaniel.agregarSucursal(sucursalDaniel);
        personasDaniel.add(usuarioPruebaDaniel);

        if (seleccion.equals("1")) {
            PortalBancario portal = new PortalBancario(bancoLucas);
            portal.iniciar();

        } else if (seleccion.equals("2")) {
            System.out.println("\n--- INICIANDO SISTEMA BANCO DANIEL ---");

            bancodaniel.model.Persona personaLogueada = bancodaniel.command.autenticacion.IniciarSesionCommand.iniciarSecion(sc, personasDaniel);

            if (personaLogueada != null) {
                System.out.println("Conectado: " + personaLogueada.getNombre());

                bancodaniel.menu.MenuStrategy menuDaniel = bancodaniel.menu.MenuFactory.crearMenu(personaLogueada, bancoDaniel, personasDaniel);

                if (menuDaniel != null) {
                    String opcionDaniel;
                    do {
                        menuDaniel.mostrar();
                        opcionDaniel = sc.nextLine();
                        menuDaniel.manejarOpcion(opcionDaniel, sc);
                    } while (!opcionDaniel.equals("0"));
                }
            } else {
                System.out.println("Saliendo del sistema de Daniel...");
            }

        } else {
            System.out.println("Opción no válida.");
        }
    }
    }

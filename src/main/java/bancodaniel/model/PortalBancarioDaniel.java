package bancodaniel.model;

import bancodaniel.command.autenticacion.IniciarSesionCommand;
import bancodaniel.enums.Rol;
import bancodaniel.enums.TipoCuenta;
import bancodaniel.menu.MenuFactory;
import bancodaniel.menu.MenuStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortalBancarioDaniel {
    private Banco banco;
    private List<Persona> personasRegistradas;

    public PortalBancarioDaniel(Banco banco) {
        this.banco = banco;
        this.personasRegistradas = new ArrayList<>();
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        Persona ceo = new Persona.Builder()
                .setNombre("Agustin")
                .setDireccion("Calle Admin 1")
                .setCorreo("test")
                .setCuenta(new Cuenta(TipoCuenta.CUENTA_CORRIENTE))
                .setPassword("123")
                .setRol(Rol.CEO)
                .build();
        personasRegistradas.add(ceo);
        banco.setCeo(ceo);

        Persona usuarioPrueba = new Persona.Builder()
                .setNombre("Usuario Prueba")
                .setDireccion("Calle 123")
                .setCorreo("asd@mail.com")
                .setCuenta(new Cuenta(TipoCuenta.CUENTA_CORRIENTE))
                .setPassword("asd")
                .setRol(Rol.USUARIO)
                .build();

        Sucursal sucursal = new Sucursal("Sucursal Central");
        sucursal.agregarPersona(usuarioPrueba);
        banco.agregarSucursal(sucursal);
        personasRegistradas.add(usuarioPrueba);
    }

    public void iniciar(Scanner sc) {
        System.out.println("\n--- INICIANDO SISTEMA BANCO DANIEL ---");

        Persona personaLogueada = IniciarSesionCommand.iniciarSecion(sc, personasRegistradas);

        if (personaLogueada != null) {
            System.out.println("Conectado: " + personaLogueada.getNombre());
            MenuStrategy menuDaniel = MenuFactory.crearMenu(personaLogueada, banco, personasRegistradas);

            if (menuDaniel != null) {
                String opcionDaniel;
                do {
                    menuDaniel.mostrar();
                    opcionDaniel = sc.nextLine();
                    menuDaniel.manejarOpcion(opcionDaniel, sc);
                } while (!opcionDaniel.equals("0"));
            }
        } else {
            System.out.println("Volviendo a la Red Interbancaria...");
        }
    }
}

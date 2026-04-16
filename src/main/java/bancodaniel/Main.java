package bancodaniel;

import bancodaniel.enums.Rol;
import bancodaniel.enums.TipoCuenta;
import bancodaniel.menu.MenuFactory;
import bancodaniel.menu.MenuStrategy;
import bancodaniel.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static bancodaniel.command.autenticacion.IniciarSesionCommand.iniciarSecion;

public class Main {
    public static void main(String[] args) {

        Persona ceo = new Persona.Builder()
                .setNombre("Agustin")
                .setDireccion("Calle Admin 1")
                .setCorreo("test")
                .setCuenta(new Cuenta(TipoCuenta.CUENTA_CORRIENTE))
                .setPassword("123")
                .setRol(Rol.CEO)
                .build();
        Persona prueba1 = new Persona.Builder()
                .setNombre("asd")
                .setDireccion("Calle Admin 1")
                .setCorreo("asd")
                .setCuenta(new Cuenta(TipoCuenta.CUENTA_CORRIENTE))
                .setPassword("asd")
                .setRol(Rol.USUARIO)
                .build();
        Persona prueba2 = new Persona.Builder()
                .setNombre("dsa")
                .setDireccion("Calle Admin 1")
                .setCorreo("dsa")
                .setCuenta(new Cuenta(TipoCuenta.CUENTA_CORRIENTE))
                .setPassword("dsa")
                .setRol(Rol.USUARIO)
                .build();
        Sucursal prueba3 = new Sucursal("zzz");

        prueba3.agregarPersona(prueba2);
        prueba3.agregarPersona(prueba1);

        ceo.mostrarDatosPersona();

        Banco banco = new Banco("Prueba");
        banco.setCeo(ceo);

        banco.agregarSucursal(prueba3);
        System.out.println("El CEO del banco es: " + banco.getCeo().getNombre());

        List<Persona> personasRegistradas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        personasRegistradas.add(ceo);
        personasRegistradas.add(prueba1);
        personasRegistradas.add(prueba2);

        Persona personaLogueada = iniciarSecion(sc, personasRegistradas);

        if (personaLogueada == null)
            return;

        System.out.println("Conectado: " + personaLogueada.getNombre());

        MenuStrategy menu = MenuFactory.crearMenu(personaLogueada, banco, personasRegistradas);
        String opcion;

        if (menu == null) {
            return;
        }

        do {
            menu.mostrar();
            opcion = sc.nextLine();
            menu.manejarOpcion(opcion, sc);
        } while (!opcion.equals("0"));

        sc.close();
    }
}

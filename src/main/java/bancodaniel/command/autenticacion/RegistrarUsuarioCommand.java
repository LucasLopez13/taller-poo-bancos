package bancodaniel.command.autenticacion;

import bancodaniel.enums.Rol;
import bancodaniel.enums.TipoCuenta;
import bancodaniel.model.Cuenta;
import bancodaniel.model.Persona;

import java.util.List;
import java.util.Scanner;

public class RegistrarUsuarioCommand {


    static void registrarNuevoUsuario(Scanner sc, List<Persona> personas) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Direccion: ");
        String direccion = sc.nextLine();
        System.out.println("Seleccione tipo de cuenta:");
        System.out.println("1. Caja de Ahorro");
        System.out.println("2. Cuenta Corriente");
        String tipoCuenta = sc.nextLine();

        TipoCuenta tipo = tipoCuenta.equals("1") ?
                TipoCuenta.CUENTA_CORRIENTE : TipoCuenta.CAJA_AHORRO;

        Cuenta nuevaCuenta = new Cuenta(tipo);

        Persona nuevoUsuario = new Persona.Builder()
                .setNombre(nombre)
                .setCorreo(correo)
                .setDireccion(direccion)
                .setPassword(password)
                .setRol(Rol.USUARIO)
                .setCuenta(nuevaCuenta)
                .build();

        personas.add(nuevoUsuario);
        System.out.println("Usuario registrado");
    }
}

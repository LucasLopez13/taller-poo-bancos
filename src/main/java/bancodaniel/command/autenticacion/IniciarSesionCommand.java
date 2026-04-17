package bancodaniel.command.autenticacion;

import bancodaniel.model.Persona;

import java.util.List;
import java.util.Scanner;

import static bancodaniel.command.autenticacion.RegistrarUsuarioCommand.registrarNuevoUsuario;

public class IniciarSesionCommand {
    public static Persona iniciarSecion(Scanner sc, List<Persona> personas) {
        Persona personaLogueada = null;
        String opcion;
        do {
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesion");
            System.out.println("0. Salir");
            opcion = sc.nextLine();

            if (opcion.equals("1")) {
                registrarNuevoUsuario(sc, personas);
            }

            if (opcion.equals("2")) {
                System.out.print("Correo: "); String cor = sc.nextLine();
                System.out.print("Password: "); String pass = sc.nextLine();

                for (Persona p : personas) {
                    if (p.getCorreo().equals(cor) && p.getPassword().equals(pass)) {
                        personaLogueada = p;
                        break;
                    }
                }
                if (personaLogueada == null) System.out.println("Credenciales incorrectas.");
            }
        } while (personaLogueada == null && !opcion.equals("0"));

        return personaLogueada;
    }
}

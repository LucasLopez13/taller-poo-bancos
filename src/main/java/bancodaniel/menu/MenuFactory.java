package bancodaniel.menu;

import bancodaniel.model.Banco;
import bancodaniel.model.Persona;

import java.util.List;

public class MenuFactory {
    public static MenuStrategy crearMenu(Persona persona, Banco banco, List<Persona> personasRegistradas) {
        switch (persona.getRol()) {
            case CEO:
                return new MenuCeo(banco, personasRegistradas);
            case USUARIO:
                return new MenuUsuario(persona, banco);
            case ADMIN:
                return new MenuAdmin(persona, banco, personasRegistradas);
            default:
                System.out.println("Rol no existente");
        }
        return null;
    }
}

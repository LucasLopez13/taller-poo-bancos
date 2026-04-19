package bancodaniel.command.administrativo;

import bancodaniel.command.Command;
import bancodaniel.model.Persona;

import java.util.List;

public class VerPersonasRegistradasCommand implements Command {
    private List<Persona> personasRegistradas;

    public VerPersonasRegistradasCommand(List<Persona> personasRegistradas) {
        this.personasRegistradas = personasRegistradas;
    }

    @Override
    public void ejecutar() {
        if (this.personasRegistradas.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("Personas registradas: ");
        for (Persona persona: this.personasRegistradas){
            System.out.println("    " + persona.getNombre());
        }
    }
}

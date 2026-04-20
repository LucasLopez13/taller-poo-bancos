package bancolucas.interfazUsuario;

import java.util.Scanner;

public class LectorConsola {
    /*
     * PREVENCIÓN DE FALLOS: Se lee la entrada como String con nextLine() para limpiar
     * el salto de línea (\n) del buffer de memoria. Luego se convierte a número dentro
     * de un bloque try-catch, evitando que el sistema crashee por errores de tipeo.
     */
    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
    }

    public static int leerEnteroEnRango(Scanner scanner, String mensaje, int min, int max) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor >= min && valor <= max) {
                return valor;
            } else {
                System.out.println("Error: Ingrese un número entre " + min + " y " + max + ".");
            }
        }
    }

    public static double leerDoublePositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(scanner.nextLine());
                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("Error: El monto debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un monto numérico válido.");
            }
        }
    }
}

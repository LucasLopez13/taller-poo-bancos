package bancolucas.interfazUsuario;

import bancolucas.dominio.Banco;
import bancolucas.dominio.Cuenta;
import bancolucas.dominio.Sucursal;

import java.util.List;
import java.util.Scanner;

public class PanelAdminCentral extends PanelBase {
    private Banco banco;

    public PanelAdminCentral(Banco banco, Scanner scanner) {
        super(new MenuGenerico("Panel Banco", scanner), scanner);
        this.banco = banco;
    }

    @Override
    protected void configurarOpciones() {
        menu.agregarOpcion(1, "Auditoria de sucursales y balances", () -> mostrarAuditoriaGlobal() );
        menu.agregarOpcion(2, "Ver todas las cuentas", () -> listarTodoElSistema());
        menu.agregarOpcion(3, "Ver balance general del banco", () -> System.out.println("Balance TOTAL del banco: $" + banco.consultarSaldoTotalDelBanco()));
        menu.agregarOpcion(4, "Consultar saldo en OTRO banco", () -> consultarSaldoDeOtroBanco());
        menu.agregarOpcion(5, "Asignar admin a sucursal", () -> asignarNuevoAdmin());
        menu.agregarOpcionSalir(6, "Salir");

    }

    public void listarTodoElSistema() {
        System.out.println("\n---LISTADO GLOBAL---");
        for (Sucursal sucursal : banco.getSucursales()) {
            System.out.println(sucursal.getNombre() + ": " + "$" + sucursal.consultarSaldoTotal());
            for (Cuenta cuenta : sucursal.getCuentas()) {
                System.out.println("  - " + cuenta.getEmail() + ": " + "$" + cuenta.getSaldo());
            }
        }
    }

    public void mostrarAuditoriaGlobal() {
        System.out.println("---AUDITORIA GLOBAL DE LAS SUCURSALES---");
        for (Sucursal sucursal : banco.getSucursales()) {
            System.out.println("Sucursal: " + sucursal.getNombre() + " | Admin local: " + sucursal.getEmailAdmin());
            System.out.println("Cuentas registradas: " + sucursal.getCuentas().size());
            System.out.println("Saldo total: $" + sucursal.consultarSaldoTotal());
        }
    }

    private void asignarNuevoAdmin() {
        System.out.println("\n--- SUCURSALES SIN ADMINISTRADOR ---");

        List<Sucursal> sucursalesHuerfanas = banco.obtenerSucursalesSinAdmin();

        if (sucursalesHuerfanas.isEmpty()) {
            System.out.println("¡Excelente! Todas las sucursales tienen un administrador asignado.");
            return;
        }

        for (int i = 0; i < sucursalesHuerfanas.size(); i++) {
            System.out.println((i + 1) + ". " + sucursalesHuerfanas.get(i).getNombre());
        }

        int opcion = LectorConsola.leerEnteroEnRango(scanner, "Seleccione el número de la sucursal: ", 1, sucursalesHuerfanas.size()) - 1;

        Sucursal sucursalElegida = sucursalesHuerfanas.get(opcion);

        System.out.print("Ingrese el email del nuevo Administrador: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese una contraseña temporal: ");
        String password = scanner.nextLine();

        banco.registrarNuevoAdminLocal(sucursalElegida, email, password);

        System.out.println("¡Éxito! El administrador " + email + " ha sido asignado a " + sucursalElegida.getNombre() + ".");
    }

    public void consultarSaldoDeOtroBanco() {
        System.out.println("Ingrese el codigo del banco a consultar: ");
        String codBanco = scanner.nextLine();

        System.out.println("Ingrese el email del usuario: ");
        String email = scanner.nextLine();
        double saldoExterno = banco.getMediator().solicitarBalance(codBanco, email);

        if (saldoExterno != -1) {
            System.out.println("El saldo de " + email + " es: $" + saldoExterno);
        } else {
            System.out.println("Error: El usuario no existe en el banco o el banco no esta conectado a la red.");
        }
    }


}

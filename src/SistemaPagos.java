import java.util.Scanner;

public class SistemaPagos {

    public static void pagar(double importe, int idFactura) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su número de tarjeta: ");
        int nroTarjeta = scanner.nextInt();
        System.out.println("Confirmar pago? s/n: ");
        String choice = scanner.next().toLowerCase();

        switch (choice) {
            case "s":
                System.out.println("Pago confirmado.");
                AdministradorSistema.getInstance().actualizarFactura(idFactura);
                break;

            case "n":
                System.out.println("Pago cancelado.");
                break;

            default:
                System.out.println("Opción inválida.");
                break;

        }

    }

}

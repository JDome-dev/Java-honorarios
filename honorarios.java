import java.util.Scanner;

public class honorarios {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese valor total de pago de su contrato: ");
        double ingreso = scanner.nextDouble();

        System.out.print("Ingrese tiempo de contrato en meses: ");
        int tiempo = scanner.nextInt();

        double SMMLV = 1750905.0;
        double ingresoMensual = ingreso / tiempo;
        double UVT = 52374.0;

        // Verificar si no paga aportes
        if (ingresoMensual < SMMLV) {

            System.out.println(
                "Ingreso mensual menor al salario mínimo legal vigente, no se realizan aportes"
            );

        } else {

            System.out.println(
                "Ingreso mayor o igual al salario mínimo legal vigente, se pagan aportes"
            );

            // Descuento pensiones (AFP)
            ingresoMensual = ingresoMensual - ingresoMensual * 0.16;

            // Descuento de salud (EPS)
            ingresoMensual = ingresoMensual - ingresoMensual * 0.125;

            // Descuento ICBF
            System.out.print("¿Aporta al ICBF? (si/no): ");
            String icbf = scanner.next();

            if (icbf.equalsIgnoreCase("si")) {
                ingresoMensual = ingresoMensual - ingresoMensual * 0.03;
            } else {
                System.out.println("Sin aporte al ICBF");
            }

            // Descuento SENA
            System.out.print("¿Aporta al SENA? (si/no): ");
            String sena = scanner.next();

            if (sena.equalsIgnoreCase("si")) {
                ingresoMensual = ingresoMensual - ingresoMensual * 0.02;
            } else {
                System.out.println("Sin aporte al SENA");
            }

            // ARL
            int opcion = 0;

            while (opcion < 1 || opcion > 5) {

                System.out.println("Seleccione el nivel de riesgo de ARL:");
                System.out.println("1. Nivel I - 0.522%");
                System.out.println("2. Nivel II - 1.044%");
                System.out.println("3. Nivel III - 2.436%");
                System.out.println("4. Nivel IV - 4.350%");
                System.out.println("5. Nivel V - 6.960%");

                System.out.print(
                    "Ingrese el número correspondiente al nivel de riesgo: "
                );

                opcion = scanner.nextInt();
            }

            //  Descuento según el nivel de ARL
            if (opcion == 1) {

                ingresoMensual = ingresoMensual - ingresoMensual * 0.00522;
                System.out.println("Se descontó ARL Nivel I: 0.522%");

            } else if (opcion == 2) {

                ingresoMensual = ingresoMensual - ingresoMensual * 0.01044;
                System.out.println("Se descontó ARL Nivel II: 1.044%");

            } else if (opcion == 3) {

                ingresoMensual = ingresoMensual - ingresoMensual * 0.02436;
                System.out.println("Se descontó ARL Nivel III: 2.436%");

            } else if (opcion == 4) {

                System.out.println(
                    "ARL Nivel IV: no se realiza descuento. Paga el empleador"
                );

            } else {

                System.out.println(
                    "ARL Nivel V: no se realiza descuento. Paga el empleador"
                );
            }
        }

        // Validación de retención en la fuente
        double retencion;

        if (ingresoMensual > 3300 *UVT) {
            retencion = ingresoMensual * 0.11;
        } else {
            retencion = 0;
        }

        double saldo = ingresoMensual - retencion;

        // Validación de obligación a declarar renta
        double renta;

        if (ingresoMensual > 1400 * UVT) {
            System.out.println("Declara renta");
            renta = saldo * 0.19;
        } else {
            System.out.println("No declara renta");
            renta = 0;
        }

        // Pago final
        double honorarioFinal = saldo - renta;

        System.out.printf(
            "El honorario es igual a: $%,.2f%n",
            honorarioFinal
        );

        scanner.close();
    }
}
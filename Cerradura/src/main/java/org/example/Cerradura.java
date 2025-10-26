
package org.example;
import java.util.Scanner;
public class Cerradura {
    private Integer clave;
    private int intentos;
    private boolean bloqueada;

    public Cerradura() {
        this.intentos = 0;
        this.bloqueada = false;
    }

    public void configurarClave(Integer clave) {
        if (clave >= 1000 && clave <= 9999) {
            this.clave = clave;
            System.out.println("Clave configurada correctamente.");
        } else {
            System.out.println("La clave debe tener 4 digitos.");
        }
    }

    public boolean validarClave(Integer claveIngresada) {
        if (bloqueada) {
            System.out.println("Cerradura bloqueada por demasiados intentos");
            return false;
        }
        if (claveIngresada.equals(this.clave)) {
            System.out.println("Cerradura abierta correctamente");
            intentos = 0;
            return true;
        } else {
            intentos++;
            System.out.println("Clave incorrecta. Intento " + intentos + " de 3.");
            if (intentos >= 3) {
                bloqueada = true;
                System.out.println("Cerradura bloqueada por seguridad");
            }
            return false;
        }
    }

    public boolean estaBloqueada() {
        return bloqueada;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cerradura cerradura = new Cerradura();
        int opcion = 0;

        do {
            System.out.println("\n--- MENU CERRADURA ELECTRONICA ---");
            System.out.println("1. Configurar clave");
            System.out.println("2. Ingresar clave");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opcion");

            String inputLine = sc.nextLine();
            try {
                opcion = Integer.parseInt(inputLine.trim());
            } catch (NumberFormatException e) {
                System.out.println("Opcion no valida. Debe ingresar un numero.");
                continue;
            }


            switch (opcion) {
                case 1:
                    System.out.println("Ingrese nueva clave de 4 digitos:");
                    if (sc.hasNextInt()) {
                        int nuevaClave = sc.nextInt();
                        sc.nextLine();
                        cerradura.configurarClave(nuevaClave);
                    } else {
                        System.out.println("Clave no valida");
                        sc.nextLine();

                    }
                    break;
                case 2:
                    if (cerradura.estaBloqueada()) {
                        System.out.println("Cerradura bloqueada. Reinicie el sistema.");
                        break;

                    }
                    System.out.println("Ingrese la clave: ");
                    if (sc.hasNextInt()) {
                        int claveIngresada = sc.nextInt();
                        sc.nextLine();
                        cerradura.validarClave(claveIngresada);
                    } else {
                        System.out.println("Clave no valida");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 3);
        sc.close();
    }
}


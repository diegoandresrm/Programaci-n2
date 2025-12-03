package dulcearte;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    private static final Scanner lector = new Scanner(System.in); // Usando 'lector'
    private static InventarioManager gestor;

    public static void main(String[] args) {
        final int CAPACIDAD_MAXIMA_BODEGA = 200;
        final double PRESUPUESTO_MENSUAL = 500.00;
        gestor = new InventarioManager(PRESUPUESTO_MENSUAL, CAPACIDAD_MAXIMA_BODEGA);

        System.out.println("Gestor Dulcearte");

        int opcion = 0;
        do {
            mostrarMenu();
            // Lectura simple de la opción
            try {
                opcion = Integer.parseInt(lector.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException error) {
                System.out.println("Error: Ingrese un número para la opción.");
                opcion = 0;
            }
        } while (opcion != 4);

        System.out.println("Programa finalizado.");
    }

    private static void mostrarMenu() {
        System.out.println("Menú");
        System.out.println("1. Crear artículo (Herencia)");
        System.out.println("2. Mover stock (Encapsulamiento y Restricciones)");
        System.out.println("3. Revisar alertas (Polimorfismo)");
        System.out.println("4. Salir");
        System.out.print("Opción: ");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearArticulo();
                break;
            case 2:
                registrarTransaccion();
                break;
            case 3:
                gestor.obtenerListaAlertas();
                break;
            case 4:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void crearArticulo() {
        System.out.println("\nCrear artículo");
        System.out.print("Tipo (D=Duradero / A=Dañable): ");
        String tipo = lector.nextLine().toUpperCase();

        if (!tipo.equals("D") && !tipo.equals("A")) {
            System.out.println("Tipo no válido.");
            return;
        }

        try {
            System.out.print("Código: ");
            String codigo = lector.nextLine();
            System.out.print("Nombre: ");
            String nombre = lector.nextLine();
            System.out.print("Costo unitario: ");
            double costo = Double.parseDouble(lector.nextLine());
            System.out.print("Punto de reorden: ");
            int puntoReorden = Integer.parseInt(lector.nextLine());

            if (tipo.equals("D")) {
                System.out.print("Tiempo de espera (días): ");
                int diasEspera = Integer.parseInt(lector.nextLine());
                ArticuloDuradero nuevoArticulo = new ArticuloDuradero(codigo, nombre, costo, puntoReorden, diasEspera);
                gestor.agregarArticulo(nuevoArticulo);
            } else { // Tipo A=Dañable
                System.out.print("Fecha límite (AAAA-MM-DD): ");
                LocalDate fechaLimite = LocalDate.parse(lector.nextLine());
                System.out.print("Días de alerta previa: ");
                int diasAlerta = Integer.parseInt(lector.nextLine());
                ArticuloDanable nuevoArticulo = new ArticuloDanable(codigo, nombre, costo, puntoReorden, fechaLimite, diasAlerta);
                gestor.agregarArticulo(nuevoArticulo);
            }

            System.out.println("Artículo " + nombre + " creado.");
        } catch (NumberFormatException | DateTimeParseException error) {
            System.out.println("Error de formato: Asegúrese de usar números y fechas AAAA-MM-DD.");
        }
    }

    private static void registrarTransaccion() {
        System.out.println("\nMover stock");
        if (gestor.listaArticulos.isEmpty()) {
            System.out.println("No hay artículos registrados.");
            return;
        }

        System.out.print("Nombre del artículo: ");
        String nombre = lector.nextLine();

        ArticuloInventario articulo = gestor.getArticuloPorNombre(nombre);

        if (articulo == null) {
            System.out.println("Artículo '" + nombre + "' no encontrado.");
            return;
        }

        System.out.print("Movimiento (E=Entrada/Compra / S=Salida/Uso): ");
        String movimiento = lector.nextLine().toUpperCase();

        try {
            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(lector.nextLine());

            if (movimiento.equals("E")) {
                gestor.procesarCompra(articulo, cantidad);
            } else if (movimiento.equals("S")) {
                articulo.registrarSalida(cantidad);
            } else {
                System.out.println("Movimiento no reconocido.");
            }
        } catch (NumberFormatException error) {
            System.out.println("Error: La cantidad debe ser un número entero.");
        }
    }
}
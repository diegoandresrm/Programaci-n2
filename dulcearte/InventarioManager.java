package dulcearte;

import java.util.ArrayList;
import java.util.List;

public class InventarioManager {

    public List<ArticuloInventario> listaArticulos;
    private final double presupuestoMaximo;
    private final int limiteEspacio;
    private double presupuestoGastadoActual;
    private int espacioOcupadoActual;

    public InventarioManager(double presupuestoMaximo, int limiteEspacio) {
        this.listaArticulos = new ArrayList<>();
        this.presupuestoMaximo = presupuestoMaximo;
        this.limiteEspacio = limiteEspacio;
        this.presupuestoGastadoActual = 0.0;
        this.espacioOcupadoActual = 0;
    }

    public void agregarArticulo(ArticuloInventario articulo) {
        listaArticulos.add(articulo);
    }

    public void procesarCompra(ArticuloInventario articulo, int cantidadAPedir) {
        double costoTotal = cantidadAPedir * articulo.getCostoCompra();
        int espacioNecesario = cantidadAPedir * 1;

        if ((presupuestoGastadoActual + costoTotal) > presupuestoMaximo) {
            System.out.println("Compra rechazada: Excede el presupuesto.");
            return;
        }

        if ((espacioOcupadoActual + espacioNecesario) > limiteEspacio) {
            System.out.println("Compra rechazada: Excede el límite de espacio.");
            return;
        }

        articulo.registrarEntrada(cantidadAPedir);
        presupuestoGastadoActual += costoTotal;
        espacioOcupadoActual += espacioNecesario;
        System.out.println("Compra de " + articulo.getNombre() + " procesada. Costo: " + costoTotal);
    }

    public void obtenerListaAlertas() {
        System.out.println("Reporte de Alertas");
        System.out.println(" ");
        boolean alertaEncontrada = false;
        for (ArticuloInventario articulo : listaArticulos) {
            String alerta = articulo.verificarAlerta();
            if (!alerta.isEmpty()) {
                System.out.println(alerta);
                alertaEncontrada = true;
            }
        }

        if (!alertaEncontrada) {
            System.out.println("No hay alertas urgentes.");
        }
        System.out.println(" ");
    }

    public ArticuloInventario getArticuloPorNombre(String nombre) {
        for (ArticuloInventario articulo : listaArticulos) {
            if (articulo.getNombre().equalsIgnoreCase(nombre)) {
                return articulo;
            }
        }
        return null;
    }
}
package dulcearte;

public class ArticuloInventario {

    private String codigo;
    private String nombre;
    private double costoCompra;
    private int puntoReorden;
    private int stockActual;

    public ArticuloInventario(String codigo, String nombre, double costoCompra, int puntoReorden) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costoCompra = costoCompra;
        this.puntoReorden = puntoReorden;
        this.stockActual = 0;
    }

    public void registrarEntrada(int cantidad) {
        if (cantidad > 0) {
            this.stockActual += cantidad;
            System.out.println("Stock de " + this.nombre + " actualizado a: " + this.stockActual);
        } else {
            System.out.println("Error: Cantidad de entrada no válida.");
        }
    }

    public void registrarSalida(int cantidad) {
        if (this.stockActual >= cantidad) {
            this.stockActual -= cantidad;
            System.out.println("Stock de " + this.nombre + " actualizado a: " + this.stockActual);
        } else {
            System.out.println("Alerta: Stock insuficiente para " + this.nombre + ".");
        }
    }

    public String verificarAlerta() {
        return "";
    }

    public String getNombre() { return nombre; }
    public int getStockActual() { return stockActual; }
    public double getCostoCompra() { return costoCompra; }
    public int getPuntoReorden() { return puntoReorden; }
}
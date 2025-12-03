package dulcearte;

public class ArticuloDuradero extends ArticuloInventario {

    private int diasEspera;

    public ArticuloDuradero(String codigo, String nombre, double costoCompra, int puntoReorden, int diasEspera) {
        super(codigo, nombre, costoCompra, puntoReorden);
        this.diasEspera = diasEspera;
    }

    @Override
    public String verificarAlerta() {
        if (getStockActual() <= getPuntoReorden()) {
            return "Alerta: " + getNombre() + " está bajo. (Duradero)";
        }
        return "";
    }
}
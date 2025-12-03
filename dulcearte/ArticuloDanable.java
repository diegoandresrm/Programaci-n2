package dulcearte;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ArticuloDanable extends ArticuloInventario {

    private LocalDate fechaLimite;
    private int diasAlertaPrevia;

    public ArticuloDanable(String codigo, String nombre, double costoCompra, int puntoReorden, LocalDate fechaLimite, int diasAlertaPrevia) {
        super(codigo, nombre, costoCompra, puntoReorden);
        this.fechaLimite = fechaLimite;
        this.diasAlertaPrevia = diasAlertaPrevia;
    }

    @Override
    public String verificarAlerta() {
        String alerta = "";
        LocalDate hoy = LocalDate.now();
        long diasHastaLimite = ChronoUnit.DAYS.between(hoy, fechaLimite);

        if (diasHastaLimite <= diasAlertaPrevia) {
            alerta += "Advertencia: " + getNombre() + " vence pronto (" + diasHastaLimite + " días).";
        }

        if (getStockActual() <= getPuntoReorden()) {
            if (!alerta.isEmpty()) {
                alerta += " y ";
            }
            alerta += "Alerta: " + getNombre() + " está bajo.";
        }

        return alerta;
    }
}
package medihome;

import java.time.LocalDateTime;

/** Medición de signos vitales tomada durante una atención médica. */
public class MedicionSignosVitales {
    private final LocalDateTime fechaHora;
    private final double temperatura;          // °C
    private final int frecuenciaCardiaca;      // latidos por minuto
    private final int presionSistolica;        // mmHg
    private final int presionDiastolica;       // mmHg
    private final int saturacionOxigeno;       // %

    MedicionSignosVitales(LocalDateTime fechaHora, double temperatura, int frecuenciaCardiaca,
                          int presionSistolica, int presionDiastolica, int saturacionOxigeno) {
        this.fechaHora = fechaHora;
        this.temperatura = temperatura;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public double getTemperatura() { return temperatura; }
    public int getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public int getPresionSistolica() { return presionSistolica; }
    public int getPresionDiastolica() { return presionDiastolica; }
    public int getSaturacionOxigeno() { return saturacionOxigeno; }
}

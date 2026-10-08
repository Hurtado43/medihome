package medihome;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Atención médica: existe exclusivamente como resultado de un servicio domiciliario. */
public class AtencionMedica {
    private final ServicioDomiciliario servicio;
    private final LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String observacionesClinicas;
    private String recomendaciones;
    private final List<MedicionSignosVitales> mediciones = new ArrayList<>();

    /** Visibilidad de paquete: solo ServicioDomiciliario puede crear una atención. */
    AtencionMedica(ServicioDomiciliario servicio, LocalDateTime fechaHoraInicio) {
        this.servicio = servicio;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /** Registra una medición de signos vitales; pertenece solo a esta atención. */
    public MedicionSignosVitales registrarMedicion(LocalDateTime fechaHora, double temperatura,
                                                   int frecuenciaCardiaca, int presionSistolica,
                                                   int presionDiastolica, int saturacionOxigeno) {
        MedicionSignosVitales medicion = new MedicionSignosVitales(fechaHora, temperatura,
                frecuenciaCardiaca, presionSistolica, presionDiastolica, saturacionOxigeno);
        mediciones.add(medicion);
        return medicion;
    }

    void finalizar(LocalDateTime fin, String observacionesClinicas, String recomendaciones) {
        if (fin.isBefore(fechaHoraInicio)) {
            throw new IllegalArgumentException("La finalización no puede ser anterior al inicio");
        }
        this.fechaHoraFin = fin;
        this.observacionesClinicas = observacionesClinicas;
        this.recomendaciones = recomendaciones;
    }

    public ServicioDomiciliario getServicio() { return servicio; }
    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public String getObservacionesClinicas() { return observacionesClinicas; }
    public String getRecomendaciones() { return recomendaciones; }
    public List<MedicionSignosVitales> getMediciones() { return Collections.unmodifiableList(mediciones); }
}

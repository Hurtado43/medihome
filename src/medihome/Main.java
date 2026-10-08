package medihome;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Demostración del sistema MediHome: instancia el modelo y presenta el reporte de la atención. */
public class Main {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        // 1. Paciente, profesional y equipo
        Paciente paciente = new Paciente("1020304050", "María Fernanda Gómez",
                "maria.gomez@correo.com", "3001234567", "Calle 45 # 12-30, Bogotá");
        ProfesionalSalud profesional = new ProfesionalSalud("79888777", "Dr. Carlos Ramírez",
                "carlos.ramirez@medihome.com", "RM-123456", "Medicina General");
        EquipoAtencion equipo = new EquipoAtencion("EQ-01", "Equipo Norte", "Chapinero - Usaquén");
        equipo.agregarProfesional(profesional);

        // 2. Servicio domiciliario: solicitado -> programado
        System.out.println("=== Ciclo de vida del servicio ===");
        ServicioDomiciliario servicio = paciente.solicitarServicio("SD-0001",
                LocalDateTime.of(2026, 10, 8, 9, 0), "Calle 45 # 12-30, Bogotá",
                "Control por fiebre y malestar general");
        servicio.programar(profesional);

        // 3. Atención médica: en atención
        AtencionMedica atencion = servicio.iniciarAtencion(LocalDateTime.of(2026, 10, 8, 9, 5));

        // 4. Mediciones de signos vitales
        atencion.registrarMedicion(LocalDateTime.of(2026, 10, 8, 9, 10),
                37.8, 92, 125, 82, 96);

        // 5. Finalización
        servicio.finalizar(LocalDateTime.of(2026, 10, 8, 9, 50),
                "Paciente con fiebre leve y buen estado general. Sin signos de alarma.",
                "Hidratación abundante, reposo, acetaminofén cada 8 horas y control en 48 horas.");

        // 6. Reporte final
        System.out.println();
        imprimirReporte(servicio, atencion);
    }

    private static void imprimirReporte(ServicioDomiciliario servicio, AtencionMedica atencion) {
        Paciente p = servicio.getPaciente();
        ProfesionalSalud pr = servicio.getProfesional();
        long minutos = Duration.between(atencion.getFechaHoraInicio(), atencion.getFechaHoraFin()).toMinutes();
        String linea = "=".repeat(60);

        System.out.println(linea);
        System.out.println("            MEDIHOME - REPORTE DE ATENCIÓN MÉDICA");
        System.out.println(linea);
        System.out.println("PACIENTE");
        System.out.println("  Identificación : " + p.getIdentificacion());
        System.out.println("  Nombre         : " + p.getNombre());
        System.out.println("  Correo         : " + p.getCorreoElectronico());
        System.out.println("  Teléfono       : " + p.getTelefono());
        System.out.println("  Dirección ppal : " + p.getDireccionPrincipal());
        System.out.println("PROFESIONAL");
        System.out.println("  Nombre         : " + pr.getNombre());
        System.out.println("  Reg. profesional: " + pr.getRegistroProfesional());
        System.out.println("  Especialidad   : " + pr.getEspecialidad());
        System.out.println("  Equipo         : " + pr.getEquipo().getNombre()
                + " (" + pr.getEquipo().getCodigo() + ") - Zona: " + pr.getEquipo().getZonaCobertura());
        System.out.println("SERVICIO DOMICILIARIO");
        System.out.println("  Código         : " + servicio.getCodigo());
        System.out.println("  Programado para: " + servicio.getFechaHoraProgramada().format(FMT));
        System.out.println("  Dirección      : " + servicio.getDireccionAtencion());
        System.out.println("  Motivo         : " + servicio.getMotivo());
        System.out.println("  Estado         : " + servicio.getEstado());
        System.out.println("ATENCIÓN MÉDICA");
        System.out.println("  Inicio         : " + atencion.getFechaHoraInicio().format(FMT));
        System.out.println("  Fin            : " + atencion.getFechaHoraFin().format(FMT)
                + " (" + minutos + " min)");
        System.out.println("  Observaciones  : " + atencion.getObservacionesClinicas());
        System.out.println("  Recomendaciones: " + atencion.getRecomendaciones());
        System.out.println("SIGNOS VITALES (" + atencion.getMediciones().size() + " medición/es)");
        int i = 1;
        for (MedicionSignosVitales m : atencion.getMediciones()) {
            System.out.printf("  #%d %s | Temp: %.1f °C | FC: %d lpm | PA: %d/%d mmHg | SatO2: %d%%%n",
                    i++, m.getFechaHora().format(FMT), m.getTemperatura(), m.getFrecuenciaCardiaca(),
                    m.getPresionSistolica(), m.getPresionDiastolica(), m.getSaturacionOxigeno());
        }
        System.out.println(linea);
    }
}

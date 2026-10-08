package medihome;

import java.time.LocalDateTime;

/** Servicio domiciliario solicitado por un único paciente. */
public class ServicioDomiciliario {
    private final String codigo;
    private LocalDateTime fechaHoraProgramada;
    private String direccionAtencion;
    private String motivo;
    private EstadoServicio estado;
    private final Paciente paciente;
    private ProfesionalSalud profesional;   // se asigna al programar
    private AtencionMedica atencion;        // resultado del servicio (0..1)

    ServicioDomiciliario(String codigo, LocalDateTime fechaHoraProgramada,
                         String direccionAtencion, String motivo, Paciente paciente) {
        this.codigo = codigo;
        this.fechaHoraProgramada = fechaHoraProgramada;
        this.direccionAtencion = direccionAtencion;
        this.motivo = motivo;
        this.paciente = paciente;
        this.estado = EstadoServicio.SOLICITADO;
    }

    /** SOLICITADO -> PROGRAMADO, asignando el profesional. */
    public void programar(ProfesionalSalud profesional) {
        if (estado != EstadoServicio.SOLICITADO) {
            throw new IllegalStateException("Solo se puede programar un servicio solicitado (estado actual: " + estado + ")");
        }
        this.profesional = profesional;
        profesional.agregarServicio(this);
        this.estado = EstadoServicio.PROGRAMADO;
        paciente.recibirNotificacion("Su servicio " + codigo + " fue programado con "
                + profesional.getNombre() + " (" + profesional.getEspecialidad() + ").");
        profesional.recibirNotificacion("Se le asignó el servicio " + codigo + " para el paciente "
                + paciente.getNombre() + ".");
    }

    /** PROGRAMADO -> EN_ATENCION. La atención solo existe como resultado de este servicio. */
    public AtencionMedica iniciarAtencion(LocalDateTime inicio) {
        if (estado != EstadoServicio.PROGRAMADO) {
            throw new IllegalStateException("Solo se puede iniciar la atención de un servicio programado (estado actual: " + estado + ")");
        }
        this.atencion = new AtencionMedica(this, inicio);
        this.estado = EstadoServicio.EN_ATENCION;
        paciente.recibirNotificacion("El profesional inició la atención del servicio " + codigo + ".");
        return atencion;
    }

    /** EN_ATENCION -> FINALIZADO. */
    public void finalizar(LocalDateTime fin, String observaciones, String recomendaciones) {
        if (estado != EstadoServicio.EN_ATENCION) {
            throw new IllegalStateException("Solo se puede finalizar un servicio en atención (estado actual: " + estado + ")");
        }
        atencion.finalizar(fin, observaciones, recomendaciones);
        this.estado = EstadoServicio.FINALIZADO;
        paciente.recibirNotificacion("El servicio " + codigo + " ha finalizado. Revise las recomendaciones.");
    }

    /** Cancela el servicio si aún no ha finalizado. */
    public void cancelar() {
        if (estado == EstadoServicio.FINALIZADO || estado == EstadoServicio.CANCELADO) {
            throw new IllegalStateException("No se puede cancelar un servicio " + estado);
        }
        this.estado = EstadoServicio.CANCELADO;
        paciente.recibirNotificacion("El servicio " + codigo + " fue cancelado.");
        if (profesional != null) {
            profesional.recibirNotificacion("El servicio " + codigo + " fue cancelado.");
        }
    }

    public String getCodigo() { return codigo; }
    public LocalDateTime getFechaHoraProgramada() { return fechaHoraProgramada; }
    public void setFechaHoraProgramada(LocalDateTime fechaHoraProgramada) { this.fechaHoraProgramada = fechaHoraProgramada; }
    public String getDireccionAtencion() { return direccionAtencion; }
    public void setDireccionAtencion(String direccionAtencion) { this.direccionAtencion = direccionAtencion; }
    public String getMotivo() { return motivo; }
    public EstadoServicio getEstado() { return estado; }
    public Paciente getPaciente() { return paciente; }
    public ProfesionalSalud getProfesional() { return profesional; }
    public AtencionMedica getAtencion() { return atencion; }
}

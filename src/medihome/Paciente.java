package medihome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Paciente que puede solicitar varios servicios domiciliarios. */
public class Paciente extends Usuario {
    private String telefono;
    private String direccionPrincipal;
    private final List<ServicioDomiciliario> servicios = new ArrayList<>();

    public Paciente(String identificacion, String nombre, String correoElectronico,
                    String telefono, String direccionPrincipal) {
        super(identificacion, nombre, correoElectronico);
        this.telefono = telefono;
        this.direccionPrincipal = direccionPrincipal;
    }

    /** Crea y registra un nuevo servicio domiciliario en estado SOLICITADO. */
    public ServicioDomiciliario solicitarServicio(String codigo, java.time.LocalDateTime fechaHoraProgramada,
                                                  String direccionAtencion, String motivo) {
        ServicioDomiciliario servicio =
                new ServicioDomiciliario(codigo, fechaHoraProgramada, direccionAtencion, motivo, this);
        servicios.add(servicio);
        recibirNotificacion("Su solicitud de servicio " + codigo + " fue registrada.");
        return servicio;
    }

    public List<ServicioDomiciliario> getServicios() { return Collections.unmodifiableList(servicios); }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccionPrincipal() { return direccionPrincipal; }
    public void setDireccionPrincipal(String direccionPrincipal) { this.direccionPrincipal = direccionPrincipal; }
}

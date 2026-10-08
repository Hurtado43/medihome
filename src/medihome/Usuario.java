package medihome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Usuario del sistema (paciente o profesional). Puede recibir notificaciones. */
public abstract class Usuario implements Notificable {
    private final String identificacion;
    private String nombre;
    private String correoElectronico;
    private final List<String> notificaciones = new ArrayList<>();

    protected Usuario(String identificacion, String nombre, String correoElectronico) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void recibirNotificacion(String mensaje) {
        notificaciones.add(mensaje);
        System.out.println("[Notificación a " + correoElectronico + "] " + mensaje);
    }

    public List<String> getNotificaciones() {
        return Collections.unmodifiableList(notificaciones);
    }

    public String getIdentificacion() { return identificacion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
}

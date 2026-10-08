package medihome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Profesional de salud; puede atender múltiples servicios y pertenecer a un equipo. */
public class ProfesionalSalud extends Usuario {
    private final String registroProfesional;
    private String especialidad;
    private EquipoAtencion equipo;
    private final List<ServicioDomiciliario> servicios = new ArrayList<>();

    public ProfesionalSalud(String identificacion, String nombre, String correoElectronico,
                            String registroProfesional, String especialidad) {
        super(identificacion, nombre, correoElectronico);
        this.registroProfesional = registroProfesional;
        this.especialidad = especialidad;
    }

    /** Uso interno: lo invoca EquipoAtencion para mantener la asociación bidireccional. */
    void setEquipo(EquipoAtencion equipo) { this.equipo = equipo; }

    /** Uso interno: lo invoca ServicioDomiciliario al programarse. */
    void agregarServicio(ServicioDomiciliario servicio) {
        if (!servicios.contains(servicio)) {
            servicios.add(servicio);
        }
    }

    public List<ServicioDomiciliario> getServicios() { return Collections.unmodifiableList(servicios); }
    public EquipoAtencion getEquipo() { return equipo; }
    public String getRegistroProfesional() { return registroProfesional; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}

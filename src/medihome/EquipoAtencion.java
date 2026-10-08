package medihome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Equipo de atención domiciliaria. Un profesional puede cambiar de equipo sin dejar de existir. */
public class EquipoAtencion {
    private final String codigo;
    private String nombre;
    private String zonaCobertura;
    private final List<ProfesionalSalud> profesionales = new ArrayList<>();

    public EquipoAtencion(String codigo, String nombre, String zonaCobertura) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.zonaCobertura = zonaCobertura;
    }

    /** Agrega al profesional; si venía de otro equipo, lo retira de éste. */
    public void agregarProfesional(ProfesionalSalud profesional) {
        EquipoAtencion anterior = profesional.getEquipo();
        if (anterior == this) {
            return;
        }
        if (anterior != null) {
            anterior.profesionales.remove(profesional);
        }
        profesionales.add(profesional);
        profesional.setEquipo(this);
    }

    public void retirarProfesional(ProfesionalSalud profesional) {
        if (profesionales.remove(profesional)) {
            profesional.setEquipo(null);
        }
    }

    public List<ProfesionalSalud> getProfesionales() { return Collections.unmodifiableList(profesionales); }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getZonaCobertura() { return zonaCobertura; }
    public void setZonaCobertura(String zonaCobertura) { this.zonaCobertura = zonaCobertura; }
}

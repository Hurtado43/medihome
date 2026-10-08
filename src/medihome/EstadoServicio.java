package medihome;

/** Estados posibles de un servicio domiciliario. */
public enum EstadoServicio {
    SOLICITADO("Solicitado"),
    PROGRAMADO("Programado"),
    EN_ATENCION("En atención"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String etiqueta;

    EstadoServicio(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}

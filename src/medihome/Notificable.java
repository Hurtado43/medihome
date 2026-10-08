package medihome;

/**
 * Contrato de todo elemento que puede ser notificado.
 * La forma concreta de recibir el mensaje la define cada implementación.
 */
public interface Notificable {
    void recibirNotificacion(String mensaje);
}

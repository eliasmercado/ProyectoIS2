package py.una.pol.ejb.utils;

import py.una.pol.ejb.enums.GenericMessage;

public class AgileSysException extends Exception {

    private static final long serialVersionUID = -4874223099605192065L;

    private String descripcion;

    public AgileSysException() {
        super();
    }

    public AgileSysException(String message) {
        super(message);
    }

    public AgileSysException(Throwable cause) {
        super(cause);
    }

    public AgileSysException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgileSysException(GenericMessage message) {
        super(message.getDescripcion());
        this.descripcion = message.getDescripcion();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

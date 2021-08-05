package py.una.pol.ejb.enums;

public enum GenericMessage {
    
    USER_NOT_FOUND("El usuario no existe"),
    PASSWORD_INVALID("El password es invalido"),
    USER_DISABLE("El usuario se encuentra inactivo"),
    PROYECTO_NOT_FOUND("El proyecto no existe");

    private final String descripcion;

    GenericMessage(String dsc){
        this.descripcion = dsc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    
}
package py.una.pol.ejb.enums;

public enum GenericMessage {

    USER_NOT_FOUND("El usuario no existe"), PASSWORD_INVALID("El password es invalido"),
    USER_DISABLE("El usuario se encuentra inactivo"), PROYECTO_NOT_FOUND("El proyecto no existe"),
    USERS_LIST_EMPTY("No hay usuarios registrados en el sistema"),
    PERMISOS_LIST_EMPTY("No hay permisos registrados en el sistema"),
    USER_NOT_CREATED("No se pudo crear el usuario"),
    USER_NOT_UPDATED("No se pudo modificar el usuario"),
    USER_NOT_DELETED("No se pudo eliminar el usuario"),
    PERMISO_CREATED("Permiso creado con exito"),
    PERMISO_UPDATED("Permiso actualizado con exito"),
    PERMISO_NOT_UPDATED("No se pudo actualizar el permiso"),
    PERMISO_NOT_CREATED("No se pudo crear el permiso"),
    PERMISO_NOT_FOUND("El permiso no existe en el sistema"),
    ROLPERMISO_NOT_FOUND("No existe el registro en el sistema"),
    PERMISO_NOT_DELETED("No se pudo eliminar el permiso"),
    ROLPERMISO_NOT_DELETED("No se pudo desasignar el permiso"),
    PERMISO_DELETED("Permiso eliminado con exito"),
    ROLPERMISO_DELETED("Permiso desasignado con exito"),
    USER_CREATED("Usuario creado con exito"),
    USER_UPDATED("Usuario modificado con exito"),
    USER_DELETED("Usuario eliminado con exito"),
    PROYECTO_FINALIZADO("El proyecto se encuentra finalizado"),
    USER_ALREADY_EXISTS("El usuario ya existe en el sistema"),
    PROYECTO_CANCELADO("El proyecto se encuentra cancelado"),
    ROLES_LIST_EMPTY("No existe ningun rol"),
    MODULOS_LIST_EMPTY("No existe ningun modulo"),
    ROLPERMISO_CREATED("Se asigno el permiso con exito"),
    ROL_NOT_CREATED("NO se pudo crear el rol"),
    ROL_NOT_UPDATED("NO se pudo actualizar el rol"),
    ROL_NOT_FOUND("Rol inexistente"),
    ROLPERMISO_ALREADY_EXISTS("Ya existe el RolPermiso en el sistema"),
    ROLPERMISO_NOT_CREATED("No se pudo crear el RolUsuario");

    private final String descripcion;

    GenericMessage(String dsc) {
        this.descripcion = dsc;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
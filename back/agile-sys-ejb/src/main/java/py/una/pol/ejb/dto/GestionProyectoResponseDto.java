package py.una.pol.ejb.dto;

public class GestionProyectoResponseDto {

    private Integer idRol;
    private String descripcionRol;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    @Override
    public String toString() {
        return "GestionProyectoResponseDto [descripcionRol=" + descripcionRol + ", idRol=" + idRol + "]";
    }

}

package py.una.pol.ejb.dto;

public class ModuloDto {

    private int idModulo;
    private String nombreModulo;

    public ModuloDto(int idModulo, String nombreModulo) {
        this.idModulo = idModulo;
        this.nombreModulo = nombreModulo;
    }

    public ModuloDto() {

    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    @Override
    public String toString() {
        return "ModuloDto{" +
                "idModulo=" + idModulo +
                ", nombreModulo='" + nombreModulo + '\'' +
                '}';
    }
}

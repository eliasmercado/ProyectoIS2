package py.una.pol.ejb.dto;

public class FaseResponseDto {

    private Integer idFase;
    private String fase;
    public Integer getIdFase() {
        return idFase;
    }
    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }
    public String getFase() {
        return fase;
    }
    public void setFase(String fase) {
        this.fase = fase;
    }
    @Override
    public String toString() {
        return "FaseResponseDto [fase=" + fase + ", idFase=" + idFase + "]";
    }

    
    
}

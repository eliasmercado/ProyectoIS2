package py.una.pol.ejb.bean;


import py.una.pol.ejb.dao.FaseDao;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.model.Fase;
import py.una.pol.ejb.utils.AgileSysException;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class FaseBean {
    @EJB
    FaseDao faseDao;


    public List<FaseResponseDto> getFases() throws AgileSysException {
        List<FaseResponseDto> response = new ArrayList<>();
        List<Fase> fases = faseDao.findAll();

        if (fases != null) {
            for (Fase fase : fases) {
                FaseResponseDto faseResponseDto = new FaseResponseDto();
                faseResponseDto.setIdFase(fase.getIdFase());
                faseResponseDto.setFase(fase.getDescripcion());
                response.add(faseResponseDto);
            }
        } else {
            throw new AgileSysException(GenericMessage.FASES_LIST_EMPTY);
        }
        return response;
    }

   
}

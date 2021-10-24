package py.una.pol.web.rest;

import py.una.pol.ejb.bean.FaseBean;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/fase")
public class FaseRest {
    @EJB
    FaseBean faseBean;
    
    @GET
    @Produces("application/json")
    public ResponseDto getFases() {
        ResponseDto response;
        try {
            List<FaseResponseDto> listaFases = faseBean.getFases();
            response = new ResponseDto<List<FaseResponseDto>>();
            response.setData(listaFases);
        } catch(AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
        return response;
    }

   
}

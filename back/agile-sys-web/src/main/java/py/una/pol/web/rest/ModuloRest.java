package py.una.pol.web.rest;


import py.una.pol.ejb.bean.ModuloBean;
import py.una.pol.ejb.dto.MessageDto;
import py.una.pol.ejb.dto.ModuloDto;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/modulo")
public class ModuloRest {
    @EJB
    ModuloBean moduloBean;

    @GET
    @Produces("application/json")
    public ResponseDto getModulos() {

        ResponseDto response;

        try {
            List<ModuloDto> listaModulos = moduloBean.getModulos();
            response = new ResponseDto<List<ModuloDto>>();
            response.setData(listaModulos);

        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }

        return response;
    }


    
}

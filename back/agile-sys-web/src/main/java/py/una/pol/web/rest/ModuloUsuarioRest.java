package py.una.pol.web.rest;


import py.una.pol.ejb.bean.ModuloBean;
import py.una.pol.ejb.dto.MessageDto;
import py.una.pol.ejb.dto.ModuloDto;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/modulo-usuario")
public class ModuloUsuarioRest {
    @EJB
    ModuloBean moduloBean;

    @GET
    @Produces("application/json")
    @Path("{idRol}")
    public ResponseDto getModulosPorRol(@PathParam("idRol") Integer idRol) {

        ResponseDto response;

        try {
            List<ModuloDto> listaModulos = moduloBean.getModuloByIdUsuarioRol(idRol);
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

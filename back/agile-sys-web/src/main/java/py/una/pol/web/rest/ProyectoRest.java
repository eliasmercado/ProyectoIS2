package py.una.pol.web.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.bean.ProyectoBean;
import py.una.pol.ejb.dto.ProyectoResponseDto;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.dto.MessageDto;

@Path("/proyecto/{idProyecto}")
public class ProyectoRest {
    @EJB
    ProyectoBean proyectoBean;

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public ResponseDto enviarProyecto(@PathParam("idProyecto") Integer idProyecto) {
        ResponseDto response;
        try {
            ProyectoResponseDto proyectoResponse = proyectoBean.enviarProyecto(idProyecto);
            response = new ResponseDto<ProyectoResponseDto>();
            response.setData(proyectoResponse);
        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
        return response;
    }
}
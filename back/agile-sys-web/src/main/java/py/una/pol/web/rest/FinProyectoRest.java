package py.una.pol.web.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.bean.ProyectoBean;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.dto.DtoProyecto.ProyectoPostResponseDto;
import py.una.pol.ejb.dto.DtoProyecto.ProyectoResponseDto;
import py.una.pol.ejb.dto.MessageDto;
import py.una.pol.ejb.dto.ProyectoGenericDto;

@Path("/finProyecto")
public class FinProyectoRest {
    @EJB
    ProyectoBean proyectoBean;

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{idProyecto}")
    public ResponseDto updateProyecto(@PathParam("idProyecto") Integer id_proyecto,
            ProyectoGenericDto proyectoGenericDto) {

        ResponseDto response;

        try {
            ProyectoPostResponseDto proyectoResp = proyectoBean.updateProyecto(id_proyecto, proyectoGenericDto);
            response = new ResponseDto<ProyectoResponseDto>();
            response.setData(proyectoResp);

        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }

        return response;
    }

}
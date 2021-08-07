package py.una.pol.web.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.dto.ProyectoResponseDto;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.bean.GestionProyectoBean;
import py.una.pol.ejb.dto.GestionProyectoResponseDto;
import py.una.pol.ejb.dto.MessageDto;

@Path("/rol-proyecto/{id-proyecto}/{id-usuario}")
public class GestionProyectoRest {
    @EJB
    GestionProyectoBean proyectoBean;

    @GET
    @Produces("application/json")
    public ResponseDto enviarProyecto(@PathParam("id-proyecto") Integer idProyecto,
            @PathParam("id-usuario") Integer idUsuario) {
        ResponseDto response;
        try {
            GestionProyectoResponseDto proyectoResponse = proyectoBean.enviarGestionProyecto(idProyecto, idUsuario);
            response = new ResponseDto<ProyectoResponseDto>();
            response.setData(proyectoResponse);
        } catch (AgileSysException e) {
            response = new ResponseDto<Error>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
        return response;
    }
}
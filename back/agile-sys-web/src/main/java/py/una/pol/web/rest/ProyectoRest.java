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

@Path("/proyecto")
public class ProyectoRest {
    @EJB
    ProyectoBean proyectoBean;

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{idProyecto}")
    public ResponseDto enviarProyecto(@PathParam("idProyecto") Integer idProyecto) {
        ResponseDto response;
        try {
            ProyectoGenericDto proyectoResponse = proyectoBean.enviarProyecto(idProyecto);
            response = new ResponseDto<ProyectoGenericDto>();
            response.setData(proyectoResponse);
        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
        return response;
    }

    @GET
    @Produces("application/json")
    public ResponseDto getProyectos() {

        ResponseDto response;

        try {
            List<ProyectoResponseDto> listaProyectos = proyectoBean.getProyectos();
            response = new ResponseDto<List<ProyectoResponseDto>>();
            response.setData(listaProyectos);

        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }

        return response;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public ResponseDto postProyecto(ProyectoGenericDto proyectoGenericDto) {

        ResponseDto response;

        try {
            ProyectoPostResponseDto proyectoResp = proyectoBean.postProyecto(proyectoGenericDto);
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

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{idProyecto}")
    public ResponseDto updateProyecto(@PathParam("id_proyecto") Integer id_proyecto,
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

    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{idProyecto}")
    public ResponseDto updateProyecto(@PathParam("id_proyecto") Integer id_proyecto) {

        ResponseDto response;

        try {
            ProyectoPostResponseDto proyectoResp = proyectoBean.deleteProyecto(id_proyecto);
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
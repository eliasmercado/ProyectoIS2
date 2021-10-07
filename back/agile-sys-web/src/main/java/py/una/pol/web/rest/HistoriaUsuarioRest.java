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
import py.una.pol.ejb.bean.HistoriaUsuarioBean;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.dto.HistoriaUsuarioPutRequest;
import py.una.pol.ejb.dto.HistoriaUsuarioRequest;
import py.una.pol.ejb.dto.HistoriaUsuarioResponse;
import py.una.pol.ejb.dto.HistoriaUsuarioResponseDto;
import py.una.pol.ejb.dto.MessageDto;

@Path("/historiaUsuario")
public class HistoriaUsuarioRest {
    @EJB
    HistoriaUsuarioBean historiaUsuarioBean;

    @GET
    @Produces("application/json")
    @Path("/{idProyecto}")
    public ResponseDto getHistoriaUsuario(@PathParam("idProyecto") Integer idProyecto) {
        ResponseDto response;
        try {
            List<HistoriaUsuarioResponseDto> listaHistorias = historiaUsuarioBean.getHistoriaUsuarioProyecto(idProyecto);
            response = new ResponseDto<List<HistoriaUsuarioResponseDto>>();
            response.setData(listaHistorias);
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
    public ResponseDto postProyecto(HistoriaUsuarioRequest historiaUsuarioRequest) {

        ResponseDto response;

        try {
            HistoriaUsuarioResponse historiaUsuarioResponse = historiaUsuarioBean.postHistoriaUsuario(historiaUsuarioRequest);
            response = new ResponseDto<HistoriaUsuarioResponse>();
            response.setData(historiaUsuarioResponse);

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
    @Path("/{idHistoriaUsuario}")
    public ResponseDto updateProyecto(@PathParam("idHistoriaUsuario") Integer idHistoriaUsuario,
            HistoriaUsuarioPutRequest historiaUsuarioPutRequest) {

        ResponseDto response;

        try {
            HistoriaUsuarioResponse historiaUsuarioResponse = historiaUsuarioBean.updateHistoriaUsuario(idHistoriaUsuario, historiaUsuarioPutRequest);
            response = new ResponseDto<HistoriaUsuarioResponse>();
            response.setData(historiaUsuarioResponse);

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
    @Path("/{idHistoriaUsuario}")
    public ResponseDto deleteHistoriaUsuario(@PathParam("idHistoriaUsuario") Integer idHistoriaUsuario) {
        ResponseDto response;
        MessageDto messageDto;
        try {
            messageDto = historiaUsuarioBean.deleteHistoriaUsuario(idHistoriaUsuario);
            response = new ResponseDto<MessageDto>();
            response.setData(messageDto);
        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            messageDto = new MessageDto();
            messageDto.setMessage(e.getDescripcion());
            response.setError(messageDto);
        }
        return response;
    }
}
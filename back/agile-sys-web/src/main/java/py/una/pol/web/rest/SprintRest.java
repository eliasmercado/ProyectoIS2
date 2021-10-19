package py.una.pol.web.rest;

import py.una.pol.ejb.bean.RolBean;
import py.una.pol.ejb.bean.SprintBean;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/sprint")
public class SprintRest {
    @EJB
    SprintBean sprintBean;
    

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseDto createSprint(SprintRequestDto sprintRequestDto) {
        ResponseDto response;
        try {
            SprintResponseDto sprintResponseDto = sprintBean.createSprint(sprintRequestDto);
            response = new ResponseDto<RolPostResponseDto>();
            response.setData(sprintResponseDto);
        } catch(AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
        return response;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{idSprint}")
    public ResponseDto updateSprint(@PathParam("idSprint") Integer idSprint, SprintRequestDto sprintRequestDto) {
        ResponseDto response;
        MessageDto messageDto;
        try {
            messageDto = sprintBean.updateSprint(idSprint, sprintRequestDto);
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

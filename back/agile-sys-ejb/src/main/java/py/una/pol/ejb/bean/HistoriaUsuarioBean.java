package py.una.pol.ejb.bean;


import java.util.Date;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.una.pol.ejb.dao.HistoriaUsuarioDao;


import py.una.pol.ejb.dto.HistoriaUsuarioPutRequest;
import py.una.pol.ejb.dto.HistoriaUsuarioRequest;
import py.una.pol.ejb.dto.HistoriaUsuarioResponse;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.model.Fase;
import py.una.pol.ejb.model.HistoriaUsuario;
import py.una.pol.ejb.model.Proyecto;
import py.una.pol.ejb.model.Sprint;
import py.una.pol.ejb.model.Usuario;
import py.una.pol.ejb.dto.HistoriaUsuarioResponseDto;
import py.una.pol.ejb.dto.MessageDto;

import java.util.List;
import java.util.ArrayList;


@Stateless
public class HistoriaUsuarioBean {

    @EJB
    HistoriaUsuarioDao historiaUsuarioDao;
  

    public HistoriaUsuarioResponse postHistoriaUsuario(HistoriaUsuarioRequest request) throws AgileSysException {
        HistoriaUsuarioResponse response = new HistoriaUsuarioResponse();
        HistoriaUsuario historiaUsuario = new HistoriaUsuario();
  
        try{
            historiaUsuario = new HistoriaUsuario();
            historiaUsuario.setNombreHistoria(request.getNombre());
            historiaUsuario.setDescripcionHistoria(request.getDescripcion());
            historiaUsuario.setFechaCreacion(new Date());
            historiaUsuario.setIdProyecto(new Proyecto(request.getIdProyecto()));;
            response.setIdHistoriaUsuario(historiaUsuario.getIdHistoriaUsuario());
            response.setMessage(GenericMessage.USER_CREATED.getDescripcion());
        }catch (Exception e){
            throw new AgileSysException(GenericMessage.US_NOT_CREATED, e.getMessage());
        }
        return response;
    }

    public HistoriaUsuarioResponse updateHistoriaUsuario(Integer idHistoriaUsuario, HistoriaUsuarioPutRequest request) throws AgileSysException {
        HistoriaUsuarioResponse response = new HistoriaUsuarioResponse();
        HistoriaUsuario historiaUsuario = historiaUsuarioDao.findByIdHistoriaUsuario(idHistoriaUsuario);
       
        if(historiaUsuario != null){
            try{
                historiaUsuario.setDescripcionHistoria(request.getDescripcion());
                historiaUsuario.setNombreHistoria(request.getNombre());
                if(request.getIdSprint() != null)
                    historiaUsuario.setIdSprint(new Sprint(request.getIdSprint()));
                else
                    historiaUsuario.setIdSprint(null);
                if(request.getIdFase() != null)
                    historiaUsuario.setIdFase(new Fase(request.getIdFase()));
                else
                    historiaUsuario.setIdFase(null);

                if(request.getIdUsuarioResponsable() != null)
                    historiaUsuario.setIdUsuarioResponsable(new Usuario(request.getIdUsuarioResponsable()));
                else
                    historiaUsuario.setIdUsuarioResponsable(null);
                historiaUsuarioDao.edit(historiaUsuario);
                response.setMessage(GenericMessage.PROJECT_UPDATED.getDescripcion());
                return response;
        }catch (Exception e){
            throw new AgileSysException(GenericMessage.US_NOT_UPDATED, e.getMessage());
        } 
    }else   
        throw new AgileSysException(GenericMessage.US_NOT_FOUND);
    }

    public List<HistoriaUsuarioResponseDto> getHistoriaUsuarioProyecto(Integer idProyecto) throws AgileSysException {
        List<HistoriaUsuarioResponseDto> response = new ArrayList<>();
        List<HistoriaUsuario> listHistorias = historiaUsuarioDao.findByIdProyectoSprintNull(idProyecto);
        if (listHistorias != null) {
            for (HistoriaUsuario historia : listHistorias) {
                HistoriaUsuarioResponseDto historiaResponseDto = new HistoriaUsuarioResponseDto();
                historiaResponseDto.setIdHistoriaUsuario(historia.getIdHistoriaUsuario());
                historiaResponseDto.setNombre(historia.getNombreHistoria());
                historiaResponseDto.setDescripcion(historia.getDescripcionHistoria());
                response.add(historiaResponseDto);
            }
        } else {
            throw new AgileSysException("No existen historias de usuario");
        }
        return response;
    }

    public MessageDto deleteHistoriaUsuario(Integer idHistoriaUsuario) throws AgileSysException{
        MessageDto response = new MessageDto();
        HistoriaUsuario historiaUsuario = historiaUsuarioDao.findByIdHistoriaUsuario(idHistoriaUsuario);
        if (historiaUsuario != null) {
            try {
                historiaUsuarioDao.remove(historiaUsuario);
                response.setMessage("Historia de Usuario eliminada con exito");
            } catch (Exception e) {
                throw new AgileSysException("No se puede eliminar la historia: " + e.getMessage());
            }
        } else {
            throw new AgileSysException("No se encontro la Historia de Usuario");
        }
        return response;
    }
}
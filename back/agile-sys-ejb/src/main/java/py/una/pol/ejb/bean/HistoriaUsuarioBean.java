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

   

}
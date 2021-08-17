package py.una.pol.ejb.bean;


import py.una.pol.ejb.dao.PermisoDao;
import py.una.pol.ejb.dao.UsuarioDao;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.model.Modulo;
import py.una.pol.ejb.model.Permiso;
import py.una.pol.ejb.model.Rol;
import py.una.pol.ejb.model.Usuario;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class PermisoBean {
    @EJB
    PermisoDao permisoDao;

    public List<PermisoResponseDto> getPermisos() throws AgileSysException {
        List<PermisoResponseDto> response = new ArrayList<>();
        List<Permiso> permisos = permisoDao.findPermisos();

        if (permisos != null) {
            for (Permiso permiso : permisos){
                PermisoResponseDto permisoResponseDto = new PermisoResponseDto();
                permisoResponseDto.setIdPermiso(permiso.getIdPermiso());
                permisoResponseDto.setDescripcion(permiso.getDescripcion());
                permisoResponseDto.setModulo(new ModuloDto(permiso.getIdModulo().getIdModulo(),
                        permiso.getIdModulo().getNombreModulo()));
            }
        } else {
            throw new AgileSysException(GenericMessage.PERMISOS_LIST_EMPTY);
        }

        return response;
    }

    public PermisoPostResponseDto postPermiso(PermisoRequestDto permisoRequestDto) throws AgileSysException {
        PermisoPostResponseDto response = new PermisoPostResponseDto();

        try{
            Permiso permiso = new Permiso();
            permiso.setDescripcion(permisoRequestDto.getDescripcion());
            permiso.setIdModulo(new Modulo(permisoRequestDto.getIdModulo()));
            permisoDao.create(permiso);
            response.setIdPermiso(permiso.getIdPermiso());
            response.setMessage(GenericMessage.PERMISO_CREATED.getDescripcion());
        }catch (Exception e){
            throw new AgileSysException(GenericMessage.PERMISO_NOT_CREATED);
        }
        return response;
    }

    public MessageDto updatePermiso(Integer idPermiso, PermisoRequestDto permisoRequestDto) throws AgileSysException {
        MessageDto response = new MessageDto();
        Permiso permiso = permisoDao.findByIDPermiso(idPermiso);
        if(permiso != null){
           try{
               permiso.setDescripcion(permisoRequestDto.getDescripcion());
               permiso.setIdModulo(new Modulo(permisoRequestDto.getIdModulo()));
               permisoDao.create(permiso);
               response.setMessage(GenericMessage.PERMISO_NOT_UPDATED.getDescripcion());
           }catch (Exception e){
               throw new AgileSysException(GenericMessage.PERMISO_UPDATED);
           }

        }else{
            throw new AgileSysException(GenericMessage.PERMISO_NOT_FOUND);
        }
        return response;
    }

    public MessageDto deletePermiso(Integer idPermiso) throws AgileSysException {
        MessageDto response = new MessageDto();
        Permiso permiso = permisoDao.findByIDPermiso(idPermiso);
        if(permiso != null){

            try{
               permisoDao.remove(permiso);
            }catch (Exception e){
                throw new AgileSysException(GenericMessage.PERMISO_NOT_DELETED);
            }

        }else{
            throw new AgileSysException(GenericMessage.USER_NOT_FOUND);
        }
        response.setMessage(GenericMessage.PERMISO_DELETED.getDescripcion());

        return response;
    }

}
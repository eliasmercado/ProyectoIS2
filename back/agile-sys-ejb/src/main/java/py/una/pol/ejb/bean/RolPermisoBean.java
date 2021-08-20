package py.una.pol.ejb.bean;



import py.una.pol.ejb.dao.RolPermisoDao;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.enums.GenericMessage;

import py.una.pol.ejb.model.Permiso;
import py.una.pol.ejb.model.Rol;
import py.una.pol.ejb.model.RolPermiso;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class RolPermisoBean {
    @EJB
    RolPermisoDao rolpermisoDao;

    public MessageDto asignarPermiso(RolPermisoRequestDto rolPermisoRequestDto) throws AgileSysException {
        RolPermiso rolPermiso = rolpermisoDao.findRolPermiso(rolPermisoRequestDto.getIdRol(), rolPermisoRequestDto.getIdPermiso());
        MessageDto messageDto = new MessageDto();
        try {
           if (rolPermiso == null) {
               rolPermiso = new RolPermiso();
               rolPermiso.setIdRol(new Rol(rolPermisoRequestDto.getIdRol()));
               rolPermiso.setIdPermiso(new Permiso(rolPermisoRequestDto.getIdRol()));
               rolpermisoDao.create(rolPermiso);
               messageDto.setMessage(GenericMessage.ROLPERMISO_CREATED.getDescripcion());
           } else
               throw new AgileSysException(GenericMessage.ROLPERMISO_ALREADY_EXISTS);

        }catch (AgileSysException e){
           throw new AgileSysException(GenericMessage.ROLPERMISO_ALREADY_EXISTS);
        }

        return messageDto;
    }


    public MessageDto deleteRolPermiso(Integer idRol, Integer idPermiso) throws AgileSysException {
        MessageDto response = new MessageDto();
        RolPermiso rolPermiso = rolpermisoDao.findRolPermiso(idRol, idPermiso);

        if(rolPermiso != null) {
            try {
                rolpermisoDao.remove(rolPermiso);
                response.setMessage(GenericMessage.ROLPERMISO_DELETED.getDescripcion());
            } catch (Exception e) {
                throw new AgileSysException(GenericMessage.PERMISO_NOT_DELETED);
            }
        } else
            throw new AgileSysException(GenericMessage.ROLPERMISO_NOT_FOUND);


        return response;
    }

}
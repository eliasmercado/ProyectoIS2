package py.una.pol.ejb.bean;


import py.una.pol.ejb.dao.RolDao;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.model.Permiso;
import py.una.pol.ejb.model.Rol;
import py.una.pol.ejb.model.RolPermiso;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.dao.RolPermisoDao;
import py.una.pol.ejb.dto.RolPostResponseDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class RolBean {
    @EJB
    RolDao rolDao;

    @EJB
    RolPermisoDao rolPermisoDao;


    public List<RolResponseDto> getRoles() throws AgileSysException {
        List<RolResponseDto> response = new ArrayList<>();
        List<Rol> roles = rolDao.findAll();

        if (roles != null) {
            for (Rol rol : roles) {
                RolResponseDto rolResponseDto = new RolResponseDto();
                rolResponseDto.setIdRol(rol.getIdRol());
                rolResponseDto.setDescripcion(rol.getDescripcionRol());
                List<PermisoResponseDto> permisos = new ArrayList<>();
                List<RolPermiso> permisosDB = rolPermisoDao.findPermisos(rol.getIdRol());
                if (permisosDB != null) {
                    for (RolPermiso permisoDB : permisosDB) {
                        Permiso p = permisoDB.getIdPermiso();
                        PermisoResponseDto permiso = new PermisoResponseDto();
                        permiso.setIdPermiso(p.getIdPermiso());
                        permiso.setDescripcion(p.getDescripcion());
                        permisos.add(permiso);
                    }
                }
                rolResponseDto.setPermisos(permisos);
                response.add(rolResponseDto);
            }
        } else {
            throw new AgileSysException(GenericMessage.ROLES_LIST_EMPTY);
        }
        return response;
    }

    public RolPostResponseDto createRol(RolRequestDto rolRequestDto) throws AgileSysException {
        RolPostResponseDto response = new RolPostResponseDto();
        try {
            Rol rol = new Rol();
            if(rolDao.findByDescripcion(rolRequestDto.getDescripcion()) != null)
                throw new AgileSysException(GenericMessage.PERMISO_NOT_CREATED,
                        "Ya existe un rol con la descripcion ingresada.");
            rol.setDescripcionRol(rolRequestDto.getDescripcion());
            rolDao.create(rol);
            response.setIdRol(rol.getIdRol());
            response.setMessage("Se creo correctamente el rol");
        } catch (AgileSysException e) {
            throw new AgileSysException(GenericMessage.ROL_NOT_CREATED);
        }
        return response;
    }

    public MessageDto updateRol(Integer idRol, RolRequestDto rolRequestDto) throws AgileSysException {
        MessageDto response = new MessageDto();
        Rol rol = rolDao.findByIdRol(idRol);

        if (rol != null) {
            try {
                if(rolDao.findByDescripcion(rolRequestDto.getDescripcion()) != null)
                    throw new AgileSysException(GenericMessage.PERMISO_NOT_CREATED,
                        "Ya existe un rol con la descripcion ingresada.");
                rol.setDescripcionRol(rolRequestDto.getDescripcion());
                rolDao.edit(rol);
                response.setMessage("Se actualizo correctamente el rol");
            } catch (AgileSysException e) {
                throw new AgileSysException(GenericMessage.ROL_NOT_UPDATED);
            }
        } else {
            throw new AgileSysException(GenericMessage.ROL_NOT_FOUND);
        }
        return response;
    }

    public MessageDto deleteRol(Integer idRol) throws AgileSysException {
        MessageDto response = new MessageDto();
        Rol rol = rolDao.findByIdRol(idRol);
        if (rol != null) {
            try {
                rolDao.remove(rol);
                response.setMessage("Rol eliminado con exito");
            } catch (Exception e) {
                throw new AgileSysException("No se pudo eliminar el Rol");
            }
        } else {
            throw new AgileSysException(GenericMessage.ROL_NOT_FOUND);
        }
        return response;
    }
}

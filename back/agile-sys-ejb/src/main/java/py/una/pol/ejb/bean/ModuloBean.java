package py.una.pol.ejb.bean;

import py.una.pol.ejb.dao.ModuloDao;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.model.Modulo;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ModuloBean {
    @EJB
    ModuloDao moduloDao;



    public List<ModuloDto> getModulos() throws AgileSysException {
        List<ModuloDto> response = new ArrayList<>();
        List<Modulo> modulos = moduloDao.findAll();

        if (modulos != null) {
            for (Modulo modulo : modulos) {
                ModuloDto moduloDto = new ModuloDto();
                moduloDto.setIdModulo(modulo.getIdModulo());
                moduloDto.setNombreModulo(modulo.getNombreModulo());
                response.add(moduloDto);
            }
        } else {
            throw new AgileSysException(GenericMessage.MODULOS_LIST_EMPTY);
        }
        return response;
    }

    public List<ModuloDto> getModuloByIdUsuarioRol(Integer idRol) throws AgileSysException {
        List<ModuloDto> response = new ArrayList<>();
        List<Modulo> modulos = moduloDao.findModulosByIdRolUsuario(idRol);

        if (modulos != null) {
            for (Modulo modulo : modulos) {
                ModuloDto moduloDto = new ModuloDto();
                moduloDto.setIdModulo(modulo.getIdModulo());
                moduloDto.setNombreModulo(modulo.getNombreModulo());
                response.add(moduloDto);
            }
        } else {
            throw new AgileSysException(GenericMessage.MODULOS_LIST_EMPTY);
        }
        return response;
    }
}

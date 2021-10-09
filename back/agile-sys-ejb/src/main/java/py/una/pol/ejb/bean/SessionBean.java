package py.una.pol.ejb.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.una.pol.ejb.dao.UsuarioDao;
import py.una.pol.ejb.dao.UsuarioProyectoDao;
import py.una.pol.ejb.dto.LoginDto;
import py.una.pol.ejb.dto.LoginResponseDto;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.model.Usuario;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.model.UsuarioProyecto;

@Stateless
public class SessionBean {

    @EJB
    UsuarioDao usuarioDao;

    @EJB
    UsuarioProyectoDao usuarioProyectoDao;

    

    public LoginResponseDto login(LoginDto loginDto) throws AgileSysException {
        LoginResponseDto response;
        Usuario usuario = usuarioDao.findByUsuarioPassword(loginDto.getUsuario(), loginDto.getPassword());

        if (usuario != null) {
            if (!usuario.getEstado())
                throw new AgileSysException(GenericMessage.USER_DISABLE);
            response = new LoginResponseDto();
            response.setUsuario(usuario.getUsuario());
            response.setIdUsuario(usuario.getIdUsuario());
            response.setNombres(usuario.getNombres());
            response.setApellidos(usuario.getApellidos());
            if(usuario.getIdRol() == null){
                response.setIdRol(0);
                response.setRol("");
            }else{
                response.setIdRol(usuario.getIdRol().getIdRol());
                response.setRol(usuario.getIdRol().getDescripcionRol());
            }
            response.setEmail(usuario.getEmail());
            UsuarioProyecto usuarioProyecto = usuarioProyectoDao.findProyectoIniciadoByIdUsuario(usuario.getIdUsuario());
            System.out.println("PROYECTO --- " + usuarioProyecto.getIdProyecto());
            if (usuarioProyecto != null)
                if(usuarioProyecto.getIdProyecto().getIdEstado().getDescripcionEstado().equals("Iniciado"))
                    response.setIdProyecto(usuarioProyecto.getIdProyecto().getIdProyecto());
           
        } else if (usuarioDao.findByUsuario(loginDto.getUsuario()) == null) {
            throw new AgileSysException(GenericMessage.USER_NOT_FOUND);
        } else
            throw new AgileSysException(GenericMessage.PASSWORD_INVALID);

        return response;
    }
}

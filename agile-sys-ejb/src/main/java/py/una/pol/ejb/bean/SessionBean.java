package py.una.pol.ejb.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.una.pol.ejb.dao.UsuarioDao;
import py.una.pol.ejb.dao.UsuarioProyectoDao;
import py.una.pol.ejb.dto.LoginDto;
import py.una.pol.ejb.dto.LoginResponseDto;
import py.una.pol.ejb.model.Usuarios;
import py.una.pol.ejb.model.UsuarioProyecto;

@Stateless
public class SessionBean {

    @EJB
    UsuarioDao usuarioDao;

    @EJB
    UsuarioProyectoDao usuarioProyectoDao;

    public LoginResponseDto login(LoginDto loginDto){
        LoginResponseDto response = null;
        Usuarios usuario = usuarioDao.findByUsuarioPassword(loginDto.getUsuario(), loginDto.getPassword());

        if(usuario != null){
            response = new LoginResponseDto();
            response.setUsuario(usuario.getUsuario());
            response.setIdUsuario(usuario.getIdUsuario());
            response.setNombres(usuario.getNombres());
            response.setApellidos(usuario.getApellidos());
            response.setEmail(usuario.getEmail());
            response.setEsAdmin(usuario.getAdministrador());
            UsuarioProyecto usuarioProyecto = usuarioProyectoDao.findProyectoByIdUsuario(usuario.getIdUsuario());
            if(usuarioProyecto != null)
                response.setIdProyecto(usuarioProyecto.getIdProyecto().getIdProyecto());
            else
                response.setIdProyecto(0);
        }
        
        return response;
    }
}

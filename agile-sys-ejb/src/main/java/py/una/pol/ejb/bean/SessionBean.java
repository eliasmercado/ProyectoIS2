package py.una.pol.ejb.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.una.pol.ejb.dao.UsuarioDao;
import py.una.pol.ejb.dto.LoginDto;
import py.una.pol.ejb.dto.LoginResponseDto;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.model.Usuarios;

@Stateless
public class SessionBean {

    @EJB
    UsuarioDao usuarioDao;

    public LoginResponseDto login(LoginDto loginDto){
        LoginResponseDto response = null;
        Usuarios usuario = usuarioDao.findByUsuario(loginDto.getUsuario());

        if(usuario != null){
            response = new LoginResponseDto();
            response.setUsuario(usuario.getUsuario());
            response.setIdUsuario(usuario.getIdUsuario());
            response.setNombres(usuario.getNombres());
            response.setApellidos(usuario.getApellidos());
            response.setEmail(usuario.getEmail());
            response.setEsAdmin(usuario.getAdministrador());
        }
        
        return response;
    }
}

package py.una.pol.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import py.una.pol.ejb.model.Usuario;

@Stateless
public class UsuarioDao extends GenericDao<Usuario> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDao() {
        super(Usuario.class);
    }

    public Usuario findByUsuarioPassword(String usuario, String password) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUsuarioPassword", Usuario.class)
                .setParameter("usuario", usuario).setParameter("password", password);
        List<Usuario> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public Usuario findByUsuario(String usuario) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUsuario", Usuario.class)
                .setParameter("usuario", usuario);
        List<Usuario> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public Usuario findByIDUsuario(Integer idUsuario) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                .setParameter("idUsuario", idUsuario);
        List<Usuario> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public List<Usuario> findUsuarios() {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
        List<Usuario> results = query.getResultList();
        if (results.size() > 0)
            return results;
        else
            return null;
    }


}
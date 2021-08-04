package py.una.pol.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import py.una.pol.ejb.model.Usuarios;

@Stateless
public class UsuarioDao extends GenericDao<Usuarios> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDao() {
        super(Usuarios.class);
    }

    public Usuarios findByUsuarioPassword(String usuario, String password) {
        TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.findByUsuarioPassword", Usuarios.class)
                .setParameter("usuario", usuario).setParameter("password", password);
        List<Usuarios> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public Usuarios findByUsuario(String usuario) {
        TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.findByUsuario", Usuarios.class)
                .setParameter("usuario", usuario);
        List<Usuarios> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

}
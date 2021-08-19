package py.una.pol.ejb.dao;

import py.una.pol.ejb.model.Rol;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class RolDao extends GenericDao<Rol> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolDao() {
        super(Rol.class);
    }
    
    public Rol findByIdRol(Integer idRol) {
        TypedQuery<Rol> query = em.createNamedQuery("Rol.findByIdRol", Rol.class)
            .setParameter("idRol", idRol);
        List<Rol> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public Rol findByDescripcion(String descripcion) {
        TypedQuery<Rol> query = em.createNamedQuery("rol.findByDescripcion", Rol.class)
                .setParameter("descripcion", descripcion);
        List<Rol> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }
}
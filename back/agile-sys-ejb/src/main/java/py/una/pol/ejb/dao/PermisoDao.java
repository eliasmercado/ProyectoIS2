package py.una.pol.ejb.dao;

import py.una.pol.ejb.model.Permiso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PermisoDao extends GenericDao<Permiso> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisoDao() {
        super(Permiso.class);
    }



    public Permiso findByIDPermiso(Integer idPermiso) {
        TypedQuery<Permiso> query = em.createNamedQuery("Permiso.findByIdPermiso", Permiso.class)
                .setParameter("idPermiso", idPermiso);
        List<Permiso> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public Permiso findByDescripcion(String descripcion) {
        TypedQuery<Permiso> query = em.createNamedQuery("Permiso.findByDescripcion", Permiso.class)
                .setParameter("descripcion", descripcion);
        List<Permiso> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }


    public List<Permiso> findPermisos() {
        TypedQuery<Permiso> query = em.createNamedQuery("Permiso.findAll", Permiso.class);
        List<Permiso> results = query.getResultList();
        if (results.size() > 0)
            return results;
        else
            return null;
    }
}
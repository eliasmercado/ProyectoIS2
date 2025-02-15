package py.una.pol.ejb.dao;

import py.una.pol.ejb.model.RolPermiso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class RolPermisoDao extends GenericDao<RolPermiso> {
    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolPermisoDao() {
        super(RolPermiso.class);
    }

    public List<RolPermiso> findPermisos(Integer idRol) {
        TypedQuery<RolPermiso> query = em.createNamedQuery(
            "RolPermiso.findByIdRol", RolPermiso.class
        ).setParameter("idRol", idRol);
        List<RolPermiso> results = query.getResultList();
        if (results.size() > 0)
            return results;
        else
            return null;
    }

    public RolPermiso findRolPermiso(Integer idRol, Integer idPermiso) {
        TypedQuery<RolPermiso> query = em.createNamedQuery(
                "RolPermiso.findByIdRolIdPermiso", RolPermiso.class
        ).setParameter("idRol", idRol).setParameter("idPermiso", idPermiso);
        List<RolPermiso> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }
}

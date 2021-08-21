package py.una.pol.ejb.dao;

import py.una.pol.ejb.model.Modulo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ModuloDao extends GenericDao{

    private static String QUERY_FINDMODULOS = "select " +
            "m.id_modulo, " +
            " m.nombre_modulo " +
            "from " +
            " rol r " +
            "inner join rol_permiso rp on " +
            " r.id_rol = rp.id_rol " +
            "inner join permiso p on " +
            " p.id_permiso = rp.id_permiso " +
            "inner join modulo m on " +
            " m.id_modulo = p.id_modulo " +
            "where " +
            " r.id_rol = :idRolUsuario ;";

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloDao() {
        super(Modulo.class);
    }

    public Modulo findModuloByIdModulo(Integer idModulo) {
        TypedQuery<Modulo> query = em.createNamedQuery("Modulo.findByIdModulo", Modulo.class)
                .setParameter("idModulo", idModulo);;

        List<Modulo> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public List<Modulo> findModulosByIdRolUsuario(Integer idRolUsuario) {
        Query query = em.createNativeQuery(QUERY_FINDMODULOS, Modulo.class)
                .setParameter("idRolUsuario", idRolUsuario);;

        List<Modulo> results = query.getResultList();

        return results;

    }
}

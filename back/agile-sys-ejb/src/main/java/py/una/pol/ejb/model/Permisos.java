/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ejb.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author isaux
 */
@Entity
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p"),
    @NamedQuery(name = "Permisos.findByIdPermisos", query = "SELECT p FROM Permisos p WHERE p.idPermisos = :idPermisos"),
    @NamedQuery(name = "Permisos.findByDescripcionPermiso", query = "SELECT p FROM Permisos p WHERE p.descripcionPermiso = :descripcionPermiso"),
    @NamedQuery(name = "Permisos.findByIdPermisoInterfaz", query = "SELECT p FROM Permisos p WHERE p.idPermisoInterfaz = :idPermisoInterfaz")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permisos")
    private Integer idPermisos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion_permiso")
    private String descripcionPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_permiso_interfaz")
    private int idPermisoInterfaz;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPermisos")
    private Collection<RolPermiso> rolPermisoCollection;

    public Permisos() {
    }

    public Permisos(Integer idPermisos) {
        this.idPermisos = idPermisos;
    }

    public Permisos(Integer idPermisos, String descripcionPermiso, int idPermisoInterfaz) {
        this.idPermisos = idPermisos;
        this.descripcionPermiso = descripcionPermiso;
        this.idPermisoInterfaz = idPermisoInterfaz;
    }

    public Integer getIdPermisos() {
        return idPermisos;
    }

    public void setIdPermisos(Integer idPermisos) {
        this.idPermisos = idPermisos;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    public int getIdPermisoInterfaz() {
        return idPermisoInterfaz;
    }

    public void setIdPermisoInterfaz(int idPermisoInterfaz) {
        this.idPermisoInterfaz = idPermisoInterfaz;
    }

    @XmlTransient
    public Collection<RolPermiso> getRolPermisoCollection() {
        return rolPermisoCollection;
    }

    public void setRolPermisoCollection(Collection<RolPermiso> rolPermisoCollection) {
        this.rolPermisoCollection = rolPermisoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermisos != null ? idPermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idPermisos == null && other.idPermisos != null) || (this.idPermisos != null && !this.idPermisos.equals(other.idPermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.una.pol.ejb.model.Permisos[ idPermisos=" + idPermisos + " ]";
    }
    
}

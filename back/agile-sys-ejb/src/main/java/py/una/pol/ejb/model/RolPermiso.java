/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ejb.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author isaux
 */
@Entity
@Table(name = "rol_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolPermiso.findAll", query = "SELECT r FROM RolPermiso r"),
    @NamedQuery(name = "RolPermiso.findByIdRolPermiso", query = "SELECT r FROM RolPermiso r WHERE r.idRolPermiso = :idRolPermiso")})
public class RolPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Size(min = 1, max = 2147483647)
    @Column(name = "id_rol_permiso")
    private String idRolPermiso;
    @JoinColumn(name = "id_permisos", referencedColumnName = "id_permisos")
    @ManyToOne(optional = false)
    private Permisos idPermisos;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Roles idRol;

    public RolPermiso() {
    }

    public RolPermiso(String idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public String getIdRolPermiso() {
        return idRolPermiso;
    }

    public void setIdRolPermiso(String idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public Permisos getIdPermisos() {
        return idPermisos;
    }

    public void setIdPermisos(Permisos idPermisos) {
        this.idPermisos = idPermisos;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolPermiso != null ? idRolPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolPermiso)) {
            return false;
        }
        RolPermiso other = (RolPermiso) object;
        if ((this.idRolPermiso == null && other.idRolPermiso != null) || (this.idRolPermiso != null && !this.idRolPermiso.equals(other.idRolPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.una.pol.ejb.model.RolPermiso[ idRolPermiso=" + idRolPermiso + " ]";
    }
    
}

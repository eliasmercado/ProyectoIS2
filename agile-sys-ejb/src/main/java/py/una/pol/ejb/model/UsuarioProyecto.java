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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author isaux
 */
@Entity
@Table(name = "usuario_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioProyecto.findAll", query = "SELECT u FROM UsuarioProyecto u"),
    @NamedQuery(name = "UsuarioProyecto.findByIdUsuarioProyecto", query = "SELECT u FROM UsuarioProyecto u WHERE u.idUsuarioProyecto = :idUsuarioProyecto")})
public class UsuarioProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario_proyecto")
    private Integer idUsuarioProyecto;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyectos idProyecto;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Roles idRol;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public UsuarioProyecto() {
    }

    public UsuarioProyecto(Integer idUsuarioProyecto) {
        this.idUsuarioProyecto = idUsuarioProyecto;
    }

    public Integer getIdUsuarioProyecto() {
        return idUsuarioProyecto;
    }

    public void setIdUsuarioProyecto(Integer idUsuarioProyecto) {
        this.idUsuarioProyecto = idUsuarioProyecto;
    }

    public Proyectos getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioProyecto != null ? idUsuarioProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioProyecto)) {
            return false;
        }
        UsuarioProyecto other = (UsuarioProyecto) object;
        if ((this.idUsuarioProyecto == null && other.idUsuarioProyecto != null) || (this.idUsuarioProyecto != null && !this.idUsuarioProyecto.equals(other.idUsuarioProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.una.pol.ejb.model.UsuarioProyecto[ idUsuarioProyecto=" + idUsuarioProyecto + " ]";
    }
    
}

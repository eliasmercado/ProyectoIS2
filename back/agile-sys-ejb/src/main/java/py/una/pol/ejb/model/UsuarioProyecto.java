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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author isaux
 */
@Entity
@Table(name = "usuario_proyecto")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "UsuarioProyecto.findAll", query = "SELECT u FROM UsuarioProyecto u"),
        @NamedQuery(name = "UsuarioProyecto.findProyectoIniciadoByIdUsuario", query = "SELECT u FROM UsuarioProyecto u WHERE u.idUsuario.idUsuario = :idUsuario and u.idProyecto.idEstado.descripcionEstado like 'Iniciado'"),
        @NamedQuery(name = "UsuarioProyecto.findProyectoTerminadoByIdUsuario", query = "SELECT u FROM UsuarioProyecto u WHERE u.idUsuario.idUsuario = :idUsuario and u.idProyecto.idEstado.descripcionEstado like 'Finalizado'"),
        @NamedQuery(name = "UsuarioProyecto.findUsuariosByIdProyecto", query = "SELECT u FROM UsuarioProyecto u WHERE u.idProyecto.idProyecto = :idProyecto"),
        @NamedQuery(name = "UsuarioProyecto.findProyectoByIdUsuarioIdProyecto", query = "SELECT u FROM UsuarioProyecto u WHERE u.idUsuario.idUsuario = :idUsuario and u.idProyecto.idProyecto = :idProyecto"),
        @NamedQuery(name = "UsuarioProyecto.findByIdUsuarioProyecto", query = "SELECT u FROM UsuarioProyecto u WHERE u.idUsuarioProyecto = :idUsuarioProyecto") })
public class UsuarioProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_proyecto")
    private Integer idUsuarioProyecto;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

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

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
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
        if ((this.idUsuarioProyecto == null && other.idUsuarioProyecto != null)
                || (this.idUsuarioProyecto != null && !this.idUsuarioProyecto.equals(other.idUsuarioProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.una.pol.ejb.model.UsuarioProyecto[ idUsuarioProyecto=" + idUsuarioProyecto + " ]";
    }

}

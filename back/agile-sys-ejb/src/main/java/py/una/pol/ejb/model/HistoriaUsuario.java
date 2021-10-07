/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ejb.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author isaux
 */
@Entity
@Table(name = "historia_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoriaUsuario.findAll", query = "SELECT h FROM HistoriaUsuario h"),
    @NamedQuery(name = "HistoriaUsuario.findByIdHistoriaUsuario", query = "SELECT h FROM HistoriaUsuario h WHERE h.idHistoriaUsuario = :idHistoriaUsuario"),
    @NamedQuery(name = "HistoriaUsuario.findByNombreHistoria", query = "SELECT h FROM HistoriaUsuario h WHERE h.nombreHistoria = :nombreHistoria"),
    @NamedQuery(name = "HistoriaUsuario.findByDescripcionHistoria", query = "SELECT h FROM HistoriaUsuario h WHERE h.descripcionHistoria = :descripcionHistoria"),
    @NamedQuery(name = "HistoriaUsuario.findByFechaCreacion", query = "SELECT h FROM HistoriaUsuario h WHERE h.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "HistoriaUsuario.findByIdProyectoSprintNull", query = "SELECT h FROM HistoriaUsuario h WHERE h.idProyecto = :idProyecto and h.idSprint is NULL")
})
public class HistoriaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historia_usuario")
    private Integer idHistoriaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_historia")
    private String nombreHistoria;
    @Size(max = 2147483647)
    @Column(name = "descripcion_historia")
    private String descripcionHistoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @JoinColumn(name = "id_fase", referencedColumnName = "id_fase")
    @ManyToOne
    private Fase idFase;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;
    @JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint")
    @ManyToOne
    private Sprint idSprint;
    @JoinColumn(name = "id_usuario_responsable", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioResponsable;

    public HistoriaUsuario() {
    }

    public HistoriaUsuario(Integer idHistoriaUsuario) {
        this.idHistoriaUsuario = idHistoriaUsuario;
    }

    public HistoriaUsuario(Integer idHistoriaUsuario, String nombreHistoria, Date fechaCreacion) {
        this.idHistoriaUsuario = idHistoriaUsuario;
        this.nombreHistoria = nombreHistoria;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdHistoriaUsuario() {
        return idHistoriaUsuario;
    }

    public void setIdHistoriaUsuario(Integer idHistoriaUsuario) {
        this.idHistoriaUsuario = idHistoriaUsuario;
    }

    public String getNombreHistoria() {
        return nombreHistoria;
    }

    public void setNombreHistoria(String nombreHistoria) {
        this.nombreHistoria = nombreHistoria;
    }

    public String getDescripcionHistoria() {
        return descripcionHistoria;
    }

    public void setDescripcionHistoria(String descripcionHistoria) {
        this.descripcionHistoria = descripcionHistoria;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Fase getIdFase() {
        return idFase;
    }

    public void setIdFase(Fase idFase) {
        this.idFase = idFase;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Sprint getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Sprint idSprint) {
        this.idSprint = idSprint;
    }

    public Usuario getIdUsuarioResponsable() {
        return idUsuarioResponsable;
    }

    public void setIdUsuarioResponsable(Usuario idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoriaUsuario != null ? idHistoriaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriaUsuario)) {
            return false;
        }
        HistoriaUsuario other = (HistoriaUsuario) object;
        if ((this.idHistoriaUsuario == null && other.idHistoriaUsuario != null) || (this.idHistoriaUsuario != null && !this.idHistoriaUsuario.equals(other.idHistoriaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.una.pol.ejb.model.HistoriaUsuario[ idHistoriaUsuario=" + idHistoriaUsuario + " ]";
    }
    
}

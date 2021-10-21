/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ejb.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author isaux
 */
@Entity
@Table(name = "sprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprint.findAll", query = "SELECT s FROM Sprint s"),
    @NamedQuery(name = "Sprint.findByIdSprint", query = "SELECT s FROM Sprint s WHERE s.idSprint = :idSprint"),
    @NamedQuery(name = "Sprint.findByCodigo", query = "SELECT s FROM Sprint s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Sprint.findByNombreSprint", query = "SELECT s FROM Sprint s WHERE s.nombreSprint = :nombreSprint"),
    @NamedQuery(name = "Sprint.findByDescripcion", query = "SELECT s FROM Sprint s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Sprint.findByFechaInicio", query = "SELECT s FROM Sprint s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Sprint.findByFechaFin", query = "SELECT s FROM Sprint s WHERE s.fechaFin = :fechaFin")})
public class Sprint implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_sprint")
    private String nombreSprint;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Size(max = 2147483647)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "completado")
    private Boolean completado;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;
    @OneToMany(mappedBy = "idSprint")
    private Collection<HistoriaUsuario> historiaUsuarioCollection;

    public Sprint() {
    }

    public Sprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Sprint(Integer idSprint, String codigo, String nombreSprint, String descripcion, Date fechaInicio) {
        this.idSprint = idSprint;
        this.codigo = codigo;
        this.nombreSprint = nombreSprint;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }


    public String getNombreSprint() {
        return nombreSprint;
    }

    public void setNombreSprint(String nombreSprint) {
        this.nombreSprint = nombreSprint;
    }


    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @XmlTransient
    public Collection<HistoriaUsuario> getHistoriaUsuarioCollection() {
        return historiaUsuarioCollection;
    }

    public void setHistoriaUsuarioCollection(Collection<HistoriaUsuario> historiaUsuarioCollection) {
        this.historiaUsuarioCollection = historiaUsuarioCollection;
    }

    public String formatDateISO(Date date) {
        if(date == null){
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return dateFormat.format(date);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSprint != null ? idSprint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idSprint == null && other.idSprint != null) || (this.idSprint != null && !this.idSprint.equals(other.idSprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.una.pol.ejb.model.Sprint[ idSprint=" + idSprint + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
    
}
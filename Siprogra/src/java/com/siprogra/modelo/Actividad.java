/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author jalber
 */
@Entity
@Table(name = "ACTIVIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByActid", query = "SELECT a FROM Actividad a WHERE a.actid = :actid"),
    @NamedQuery(name = "Actividad.findByActnombre", query = "SELECT a FROM Actividad a WHERE a.actnombre = :actnombre")})
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTID")
    private BigDecimal actid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ACTNOMBRE")
    private String actnombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actid", fetch = FetchType.EAGER)
    private List<Restriccion> restriccionList;
    @OneToMany(mappedBy = "actActid", fetch = FetchType.EAGER)
    private List<Actividad> actividadList;
    @JoinColumn(name = "ACT_ACTID", referencedColumnName = "ACTID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Actividad actActid;
    @JoinColumn(name = "FASID", referencedColumnName = "FASID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Fase fasid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actid", fetch = FetchType.EAGER)
    private List<Productodetrabajo> productodetrabajoList;

    public Actividad() {
    }

    public Actividad(BigDecimal actid) {
        this.actid = actid;
    }

    public Actividad(BigDecimal actid, String actnombre) {
        this.actid = actid;
        this.actnombre = actnombre;
    }

    public BigDecimal getActid() {
        return actid;
    }

    public void setActid(BigDecimal actid) {
        this.actid = actid;
    }

    public String getActnombre() {
        return actnombre;
    }

    public void setActnombre(String actnombre) {
        this.actnombre = actnombre;
    }

    @XmlTransient
    public List<Restriccion> getRestriccionList() {
        return restriccionList;
    }

    public void setRestriccionList(List<Restriccion> restriccionList) {
        this.restriccionList = restriccionList;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    public Actividad getActActid() {
        return actActid;
    }

    public void setActActid(Actividad actActid) {
        this.actActid = actActid;
    }

    public Fase getFasid() {
        return fasid;
    }

    public void setFasid(Fase fasid) {
        this.fasid = fasid;
    }

    @XmlTransient
    public List<Productodetrabajo> getProductodetrabajoList() {
        return productodetrabajoList;
    }

    public void setProductodetrabajoList(List<Productodetrabajo> productodetrabajoList) {
        this.productodetrabajoList = productodetrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actid != null ? actid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.actid == null && other.actid != null) || (this.actid != null && !this.actid.equals(other.actid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Actividad[ actid=" + actid + " ]";
    }
    
}

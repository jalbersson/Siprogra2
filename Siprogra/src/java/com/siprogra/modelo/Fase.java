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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "FASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fase.findAll", query = "SELECT f FROM Fase f"),
    @NamedQuery(name = "Fase.maximaId", query = "SELECT MAX(f.fasid) FROM Fase f"),
    @NamedQuery(name = "Fase.findByFasid", query = "SELECT f FROM Fase f WHERE f.fasid = :fasid"),
    @NamedQuery(name = "Fase.sacarFasnombre", query = "SELECT f.fasnombre FROM Fase f WHERE f.fasid = :fasid"),
    @NamedQuery(name = "Fase.findByFasnombre", query = "SELECT f FROM Fase f WHERE f.fasnombre = :fasnombre"),
    @NamedQuery(name = "Fase.findByFasestado", query = "SELECT f FROM Fase f WHERE f.fasestado = :fasestado")})
public class Fase implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FASID")
    private BigDecimal fasid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FASNOMBRE")
    private String fasnombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FASESTADO")
    private String fasestado;
    @JoinTable(name = "CONSTA", joinColumns = {
        @JoinColumn(name = "FASID", referencedColumnName = "FASID")}, inverseJoinColumns = {
        @JoinColumn(name = "FLUID", referencedColumnName = "FLUID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Flujo> flujoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fasid", fetch = FetchType.EAGER)
    private List<Actividad> actividadList;
    @OneToMany(mappedBy = "fasFasid", fetch = FetchType.LAZY)
    private List<Fase> faseList;
    @JoinColumn(name = "FAS_FASID", referencedColumnName = "FASID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Fase fasFasid;

    public Fase() {
    }

    public Fase(BigDecimal fasid) {
        this.fasid = fasid;
    }

    public Fase(BigDecimal fasid, String fasnombre, String fasestado) {
        this.fasid = fasid;
        this.fasnombre = fasnombre;
        this.fasestado = fasestado;
    }

    public BigDecimal getFasid() {
        return fasid;
    }

    public void setFasid(BigDecimal fasid) {
        this.fasid = fasid;
    }

    public String getFasnombre() {
        return fasnombre;
    }

    public void setFasnombre(String fasnombre) {
        this.fasnombre = fasnombre;
    }

    public String getFasestado() {
        return fasestado;
    }

    public void setFasestado(String fasestado) {
        this.fasestado = fasestado;
    }

    @XmlTransient
    public List<Flujo> getFlujoList() {
        return flujoList;
    }

    public void setFlujoList(List<Flujo> flujoList) {
        this.flujoList = flujoList;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    @XmlTransient
    public List<Fase> getFaseList() {
        return faseList;
    }

    public void setFaseList(List<Fase> faseList) {
        this.faseList = faseList;
    }

    public Fase getFasFasid() {
        return fasFasid;
    }

    public void setFasFasid(Fase fasFasid) {
        this.fasFasid = fasFasid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fasid != null ? fasid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fase)) {
            return false;
        }
        Fase other = (Fase) object;
        if ((this.fasid == null && other.fasid != null) || (this.fasid != null && !this.fasid.equals(other.fasid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Fase[ fasid=" + fasid + " ]";
    }
    
}

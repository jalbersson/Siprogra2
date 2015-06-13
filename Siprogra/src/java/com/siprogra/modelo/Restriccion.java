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
@Table(name = "RESTRICCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restriccion.findAll", query = "SELECT r FROM Restriccion r"),
    @NamedQuery(name = "Restriccion.maximoId", query = "SELECT MAX(r.resid) FROM Restriccion r"),
    @NamedQuery(name = "Restriccion.findByResid", query = "SELECT r FROM Restriccion r WHERE r.resid = :resid"),
    @NamedQuery(name = "Restriccion.findByResdescripcion", query = "SELECT r FROM Restriccion r WHERE r.resdescripcion = :resdescripcion")})
public class Restriccion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESID")
    private BigDecimal resid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "RESDESCRIPCION")
    private String resdescripcion;
    @JoinColumn(name = "ACTID", referencedColumnName = "ACTID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Actividad actid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restriccion", fetch = FetchType.EAGER)
    private List<Afecta> afectaList;

    public Restriccion() {
    }

    public Restriccion(BigDecimal resid) {
        this.resid = resid;
    }

    public Restriccion(BigDecimal resid, String resdescripcion) {
        this.resid = resid;
        this.resdescripcion = resdescripcion;
    }

    public BigDecimal getResid() {
        return resid;
    }

    public void setResid(BigDecimal resid) {
        this.resid = resid;
    }

    public String getResdescripcion() {
        return resdescripcion;
    }

    public void setResdescripcion(String resdescripcion) {
        this.resdescripcion = resdescripcion;
    }

    public Actividad getActid() {
        return actid;
    }

    public void setActid(Actividad actid) {
        this.actid = actid;
    }

    @XmlTransient
    public List<Afecta> getAfectaList() {
        return afectaList;
    }

    public void setAfectaList(List<Afecta> afectaList) {
        this.afectaList = afectaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resid != null ? resid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restriccion)) {
            return false;
        }
        Restriccion other = (Restriccion) object;
        if ((this.resid == null && other.resid != null) || (this.resid != null && !this.resid.equals(other.resid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Restriccion[ resid=" + resid + " ]";
    }
    
}

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
@Table(name = "PRODUCTODETRABAJO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productodetrabajo.findAll", query = "SELECT p FROM Productodetrabajo p"),
    @NamedQuery(name = "Productodetrabajo.findByProid", query = "SELECT p FROM Productodetrabajo p WHERE p.proid = :proid"),
    @NamedQuery(name = "Productodetrabajo.findByPronombre", query = "SELECT p FROM Productodetrabajo p WHERE p.pronombre = :pronombre")})
public class Productodetrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROID")
    private BigDecimal proid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PRONOMBRE")
    private String pronombre;
    @ManyToMany(mappedBy = "productodetrabajoList", fetch = FetchType.LAZY)
    private List<Procesodegrado> procesodegradoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productodetrabajo", fetch = FetchType.LAZY)
    private List<Afecta> afectaList;
    @JoinColumn(name = "ACTID", referencedColumnName = "ACTID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Actividad actid;
    @JoinColumn(name = "ROLID", referencedColumnName = "ROLID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rolid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proid", fetch = FetchType.EAGER)
    private List<Parametro> parametroList;

    public Productodetrabajo() {
    }

    public Productodetrabajo(BigDecimal proid) {
        this.proid = proid;
    }

    public Productodetrabajo(BigDecimal proid, String pronombre) {
        this.proid = proid;
        this.pronombre = pronombre;
    }

    public BigDecimal getProid() {
        return proid;
    }

    public void setProid(BigDecimal proid) {
        this.proid = proid;
    }

    public String getPronombre() {
        return pronombre;
    }

    public void setPronombre(String pronombre) {
        this.pronombre = pronombre;
    }

    @XmlTransient
    public List<Procesodegrado> getProcesodegradoList() {
        return procesodegradoList;
    }

    public void setProcesodegradoList(List<Procesodegrado> procesodegradoList) {
        this.procesodegradoList = procesodegradoList;
    }

    @XmlTransient
    public List<Afecta> getAfectaList() {
        return afectaList;
    }

    public void setAfectaList(List<Afecta> afectaList) {
        this.afectaList = afectaList;
    }

    public Actividad getActid() {
        return actid;
    }

    public void setActid(Actividad actid) {
        this.actid = actid;
    }

    public Rol getRolid() {
        return rolid;
    }

    public void setRolid(Rol rolid) {
        this.rolid = rolid;
    }

    @XmlTransient
    public List<Parametro> getParametroList() {
        return parametroList;
    }

    public void setParametroList(List<Parametro> parametroList) {
        this.parametroList = parametroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proid != null ? proid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productodetrabajo)) {
            return false;
        }
        Productodetrabajo other = (Productodetrabajo) object;
        if ((this.proid == null && other.proid != null) || (this.proid != null && !this.proid.equals(other.proid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Productodetrabajo[ proid=" + proid + " ]";
    }
    
}

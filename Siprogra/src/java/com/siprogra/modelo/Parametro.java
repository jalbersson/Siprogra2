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
@Table(name = "PARAMETRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
    @NamedQuery(name = "Parametro.findByParid", query = "SELECT p FROM Parametro p WHERE p.parid = :parid"),
    @NamedQuery(name = "Parametro.findByParnombre", query = "SELECT p FROM Parametro p WHERE p.parnombre = :parnombre"),
    @NamedQuery(name = "Parametro.findByPartipo", query = "SELECT p FROM Parametro p WHERE p.partipo = :partipo")})
public class Parametro implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARID")
    private BigDecimal parid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PARNOMBRE")
    private String parnombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PARTIPO")
    private String partipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parid", fetch = FetchType.EAGER)
    private List<Contenido> contenidoList;
    @JoinColumn(name = "PROID", referencedColumnName = "PROID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Productodetrabajo proid;

    public Parametro() {
    }

    public Parametro(BigDecimal parid) {
        this.parid = parid;
    }

    public Parametro(BigDecimal parid, String parnombre, String partipo) {
        this.parid = parid;
        this.parnombre = parnombre;
        this.partipo = partipo;
    }

    public BigDecimal getParid() {
        return parid;
    }

    public void setParid(BigDecimal parid) {
        this.parid = parid;
    }

    public String getParnombre() {
        return parnombre;
    }

    public void setParnombre(String parnombre) {
        this.parnombre = parnombre;
    }

    public String getPartipo() {
        return partipo;
    }

    public void setPartipo(String partipo) {
        this.partipo = partipo;
    }

    @XmlTransient
    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    public Productodetrabajo getProid() {
        return proid;
    }

    public void setProid(Productodetrabajo proid) {
        this.proid = proid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parid != null ? parid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.parid == null && other.parid != null) || (this.parid != null && !this.parid.equals(other.parid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Parametro[ parid=" + parid + " ]";
    }
    
}

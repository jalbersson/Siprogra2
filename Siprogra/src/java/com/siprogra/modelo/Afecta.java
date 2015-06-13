/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jalber
 */
@Entity
@Table(name = "AFECTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Afecta.findAll", query = "SELECT a FROM Afecta a"),
    @NamedQuery(name = "Afecta.findByProid", query = "SELECT a FROM Afecta a WHERE a.afectaPK.proid = :proid"),
    @NamedQuery(name = "Afecta.findByResid", query = "SELECT a FROM Afecta a WHERE a.afectaPK.resid = :resid"),
    @NamedQuery(name = "Afecta.findByValid", query = "SELECT a FROM Afecta a WHERE a.valid = :valid"),
    @NamedQuery(name = "Afecta.findByValfecha", query = "SELECT a FROM Afecta a WHERE a.valfecha = :valfecha"),
    @NamedQuery(name = "Afecta.findByValconteo", query = "SELECT a FROM Afecta a WHERE a.valconteo = :valconteo")})
public class Afecta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AfectaPK afectaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID")
    private BigInteger valid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALFECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valfecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALCONTEO")
    private BigInteger valconteo;
    @JoinColumn(name = "PROID", referencedColumnName = "PROID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productodetrabajo productodetrabajo;
    @JoinColumn(name = "RESID", referencedColumnName = "RESID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restriccion restriccion;

    public Afecta() {
    }

    public Afecta(AfectaPK afectaPK) {
        this.afectaPK = afectaPK;
    }

    public Afecta(AfectaPK afectaPK, BigInteger valid, Date valfecha, BigInteger valconteo) {
        this.afectaPK = afectaPK;
        this.valid = valid;
        this.valfecha = valfecha;
        this.valconteo = valconteo;
    }

    public Afecta(BigInteger proid, BigInteger resid) {
        this.afectaPK = new AfectaPK(proid, resid);
    }

    public AfectaPK getAfectaPK() {
        return afectaPK;
    }

    public void setAfectaPK(AfectaPK afectaPK) {
        this.afectaPK = afectaPK;
    }

    public BigInteger getValid() {
        return valid;
    }

    public void setValid(BigInteger valid) {
        this.valid = valid;
    }

    public Date getValfecha() {
        return valfecha;
    }

    public void setValfecha(Date valfecha) {
        this.valfecha = valfecha;
    }

    public BigInteger getValconteo() {
        return valconteo;
    }

    public void setValconteo(BigInteger valconteo) {
        this.valconteo = valconteo;
    }

    public Productodetrabajo getProductodetrabajo() {
        return productodetrabajo;
    }

    public void setProductodetrabajo(Productodetrabajo productodetrabajo) {
        this.productodetrabajo = productodetrabajo;
    }

    public Restriccion getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(Restriccion restriccion) {
        this.restriccion = restriccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afectaPK != null ? afectaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Afecta)) {
            return false;
        }
        Afecta other = (Afecta) object;
        if ((this.afectaPK == null && other.afectaPK != null) || (this.afectaPK != null && !this.afectaPK.equals(other.afectaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Afecta[ afectaPK=" + afectaPK + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jalber
 */
@Entity
@Table(name = "CONTENIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenido.findAll", query = "SELECT c FROM Contenido c"),
    @NamedQuery(name = "Contenido.findByConid", query = "SELECT c FROM Contenido c WHERE c.conid = :conid"),
    @NamedQuery(name = "Contenido.findByCondatos", query = "SELECT c FROM Contenido c WHERE c.condatos = :condatos")})
public class Contenido implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONID")
    private BigDecimal conid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "CONDATOS")
    private String condatos;
    @JoinColumn(name = "PARID", referencedColumnName = "PARID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Parametro parid;
    @JoinColumn(name = "PROCID", referencedColumnName = "PROCID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Procesodegrado procid;

    public Contenido() {
    }

    public Contenido(BigDecimal conid) {
        this.conid = conid;
    }

    public Contenido(BigDecimal conid, String condatos) {
        this.conid = conid;
        this.condatos = condatos;
    }

    public BigDecimal getConid() {
        return conid;
    }

    public void setConid(BigDecimal conid) {
        this.conid = conid;
    }

    public String getCondatos() {
        return condatos;
    }

    public void setCondatos(String condatos) {
        this.condatos = condatos;
    }

    public Parametro getParid() {
        return parid;
    }

    public void setParid(Parametro parid) {
        this.parid = parid;
    }

    public Procesodegrado getProcid() {
        return procid;
    }

    public void setProcid(Procesodegrado procid) {
        this.procid = procid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conid != null ? conid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenido)) {
            return false;
        }
        Contenido other = (Contenido) object;
        if ((this.conid == null && other.conid != null) || (this.conid != null && !this.conid.equals(other.conid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Contenido[ conid=" + conid + " ]";
    }
    
}

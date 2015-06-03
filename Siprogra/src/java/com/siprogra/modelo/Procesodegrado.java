/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "PROCESODEGRADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procesodegrado.findAll", query = "SELECT p FROM Procesodegrado p"),
    @NamedQuery(name = "Procesodegrado.findByProcid", query = "SELECT p FROM Procesodegrado p WHERE p.procid = :procid"),
    @NamedQuery(name = "Procesodegrado.findByProctitulo", query = "SELECT p FROM Procesodegrado p WHERE p.proctitulo = :proctitulo"),
    @NamedQuery(name = "Procesodegrado.findByProcestado", query = "SELECT p FROM Procesodegrado p WHERE p.procestado = :procestado")})
public class Procesodegrado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROCID")
    private BigDecimal procid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "PROCTITULO")
    private String proctitulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROCESTADO")
    private BigInteger procestado;
    @JoinTable(name = "PARTICIPA", joinColumns = {
        @JoinColumn(name = "PROCID", referencedColumnName = "PROCID")}, inverseJoinColumns = {
        @JoinColumn(name = "USUID", referencedColumnName = "USUID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Usuario> usuarioList;
    @JoinTable(name = "INVOLUCRA", joinColumns = {
        @JoinColumn(name = "PROCID", referencedColumnName = "PROCID")}, inverseJoinColumns = {
        @JoinColumn(name = "PROID", referencedColumnName = "PROID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Productodetrabajo> productodetrabajoList;
    @JoinColumn(name = "FLUID", referencedColumnName = "FLUID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Flujo fluid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procid", fetch = FetchType.EAGER)
    private List<Contenido> contenidoList;

    public Procesodegrado() {
    }

    public Procesodegrado(BigDecimal procid) {
        this.procid = procid;
    }

    public Procesodegrado(BigDecimal procid, String proctitulo, BigInteger procestado) {
        this.procid = procid;
        this.proctitulo = proctitulo;
        this.procestado = procestado;
    }

    public BigDecimal getProcid() {
        return procid;
    }

    public void setProcid(BigDecimal procid) {
        this.procid = procid;
    }

    public String getProctitulo() {
        return proctitulo;
    }

    public void setProctitulo(String proctitulo) {
        this.proctitulo = proctitulo;
    }

    public BigInteger getProcestado() {
        return procestado;
    }

    public void setProcestado(BigInteger procestado) {
        this.procestado = procestado;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Productodetrabajo> getProductodetrabajoList() {
        return productodetrabajoList;
    }

    public void setProductodetrabajoList(List<Productodetrabajo> productodetrabajoList) {
        this.productodetrabajoList = productodetrabajoList;
    }

    public Flujo getFluid() {
        return fluid;
    }

    public void setFluid(Flujo fluid) {
        this.fluid = fluid;
    }

    @XmlTransient
    public List<Contenido> getContenidoList() {
        return contenidoList;
    }

    public void setContenidoList(List<Contenido> contenidoList) {
        this.contenidoList = contenidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procid != null ? procid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesodegrado)) {
            return false;
        }
        Procesodegrado other = (Procesodegrado) object;
        if ((this.procid == null && other.procid != null) || (this.procid != null && !this.procid.equals(other.procid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Procesodegrado[ procid=" + procid + " ]";
    }
    
}

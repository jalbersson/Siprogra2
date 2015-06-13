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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "ADMINISTRADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
    @NamedQuery(name = "Administrador.findByAdmid", query = "SELECT a FROM Administrador a WHERE a.admid = :admid"),
    @NamedQuery(name = "Administrador.findByAdmnombreusuario", query = "SELECT a FROM Administrador a WHERE a.admnombreusuario = :admnombreusuario"),
    @NamedQuery(name = "Administrador.findByAdmcontrasenia", query = "SELECT a FROM Administrador a WHERE a.admcontrasenia = :admcontrasenia")})
public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADMID")
    private BigDecimal admid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ADMNOMBREUSUARIO")
    private String admnombreusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ADMCONTRASENIA")
    private String admcontrasenia;
    @ManyToMany(mappedBy = "administradorList", fetch = FetchType.LAZY)
    private List<Flujo> flujoList;

    public Administrador() {
    }

    public Administrador(BigDecimal admid) {
        this.admid = admid;
    }

    public Administrador(BigDecimal admid, String admnombreusuario, String admcontrasenia) {
        this.admid = admid;
        this.admnombreusuario = admnombreusuario;
        this.admcontrasenia = admcontrasenia;
    }

    public BigDecimal getAdmid() {
        return admid;
    }

    public void setAdmid(BigDecimal admid) {
        this.admid = admid;
    }

    public String getAdmnombreusuario() {
        return admnombreusuario;
    }

    public void setAdmnombreusuario(String admnombreusuario) {
        this.admnombreusuario = admnombreusuario;
    }

    public String getAdmcontrasenia() {
        return admcontrasenia;
    }

    public void setAdmcontrasenia(String admcontrasenia) {
        this.admcontrasenia = admcontrasenia;
    }

    @XmlTransient
    public List<Flujo> getFlujoList() {
        return flujoList;
    }

    public void setFlujoList(List<Flujo> flujoList) {
        this.flujoList = flujoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (admid != null ? admid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.admid == null && other.admid != null) || (this.admid != null && !this.admid.equals(other.admid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Administrador[ admid=" + admid + " ]";
    }
    
}

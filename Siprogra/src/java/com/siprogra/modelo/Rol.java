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
@Table(name = "ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.maximoId", query = "SELECT MAX(r.rolid) FROM Rol r"),
    @NamedQuery(name = "Rol.findByRolid", query = "SELECT r FROM Rol r WHERE r.rolid = :rolid"),
    @NamedQuery(name = "Rol.findByRolnombre", query = "SELECT r FROM Rol r WHERE r.rolnombre = :rolnombre"),
    @NamedQuery(name = "Rol.findByRolestado", query = "SELECT r FROM Rol r WHERE r.rolestado = :rolestado")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLID")
    private BigDecimal rolid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ROLNOMBRE")
    private String rolnombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ROLESTADO")
    private String rolestado;
    @JoinTable(name = "DESEMPENIA", joinColumns = {
        @JoinColumn(name = "ROLID", referencedColumnName = "ROLID")}, inverseJoinColumns = {
        @JoinColumn(name = "USUID", referencedColumnName = "USUID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolid", fetch = FetchType.EAGER)
    private List<Productodetrabajo> productodetrabajoList;

    public Rol() {
    }

    public Rol(BigDecimal rolid) {
        this.rolid = rolid;
    }

    public Rol(BigDecimal rolid, String rolnombre, String rolestado) {
        this.rolid = rolid;
        this.rolnombre = rolnombre;
        this.rolestado = rolestado;
    }

    public BigDecimal getRolid() {
        return rolid;
    }

    public void setRolid(BigDecimal rolid) {
        this.rolid = rolid;
    }

    public String getRolnombre() {
        return rolnombre;
    }

    public void setRolnombre(String rolnombre) {
        this.rolnombre = rolnombre;
    }

    public String getRolestado() {
        return rolestado;
    }

    public void setRolestado(String rolestado) {
        this.rolestado = rolestado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolid != null ? rolid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolid == null && other.rolid != null) || (this.rolid != null && !this.rolid.equals(other.rolid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Rol[ rolid=" + rolid + " ]";
    }
    
}

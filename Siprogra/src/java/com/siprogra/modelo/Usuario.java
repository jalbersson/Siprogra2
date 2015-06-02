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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuid", query = "SELECT u FROM Usuario u WHERE u.usuid = :usuid"),
    @NamedQuery(name = "Usuario.findByUsunombreusuario", query = "SELECT u FROM Usuario u WHERE u.usunombreusuario = :usunombreusuario"),
    @NamedQuery(name = "Usuario.findByUsucontrasenia", query = "SELECT u FROM Usuario u WHERE u.usucontrasenia = :usucontrasenia"),
    @NamedQuery(name = "Usuario.findByUsunombres", query = "SELECT u FROM Usuario u WHERE u.usunombres = :usunombres"),
    @NamedQuery(name = "Usuario.findByUsuapellidos", query = "SELECT u FROM Usuario u WHERE u.usuapellidos = :usuapellidos"),
    @NamedQuery(name = "Usuario.findByUsucedula", query = "SELECT u FROM Usuario u WHERE u.usucedula = :usucedula"),
    @NamedQuery(name = "Usuario.findByUsucorreo", query = "SELECT u FROM Usuario u WHERE u.usucorreo = :usucorreo"),
    @NamedQuery(name = "Usuario.findByUsuarea", query = "SELECT u FROM Usuario u WHERE u.usuarea = :usuarea")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUID")
    private BigDecimal usuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUNOMBREUSUARIO")
    private String usunombreusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUCONTRASENIA")
    private String usucontrasenia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUNOMBRES")
    private String usunombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUAPELLIDOS")
    private String usuapellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUCEDULA")
    private BigInteger usucedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USUCORREO")
    private String usucorreo;
    @Size(max = 200)
    @Column(name = "USUAREA")
    private String usuarea;
    @ManyToMany(mappedBy = "usuarioList", fetch = FetchType.EAGER)
    private List<Procesodegrado> procesodegradoList;
    @ManyToMany(mappedBy = "usuarioList", fetch = FetchType.EAGER)
    private List<Rol> rolList;

    public Usuario() {
    }

    public Usuario(BigDecimal usuid) {
        this.usuid = usuid;
    }

    public Usuario(BigDecimal usuid, String usunombreusuario, String usucontrasenia, String usunombres, String usuapellidos, BigInteger usucedula, String usucorreo) {
        this.usuid = usuid;
        this.usunombreusuario = usunombreusuario;
        this.usucontrasenia = usucontrasenia;
        this.usunombres = usunombres;
        this.usuapellidos = usuapellidos;
        this.usucedula = usucedula;
        this.usucorreo = usucorreo;
    }

    public BigDecimal getUsuid() {
        return usuid;
    }

    public void setUsuid(BigDecimal usuid) {
        this.usuid = usuid;
    }

    public String getUsunombreusuario() {
        return usunombreusuario;
    }

    public void setUsunombreusuario(String usunombreusuario) {
        this.usunombreusuario = usunombreusuario;
    }

    public String getUsucontrasenia() {
        return usucontrasenia;
    }

    public void setUsucontrasenia(String usucontrasenia) {
        this.usucontrasenia = usucontrasenia;
    }

    public String getUsunombres() {
        return usunombres;
    }

    public void setUsunombres(String usunombres) {
        this.usunombres = usunombres;
    }

    public String getUsuapellidos() {
        return usuapellidos;
    }

    public void setUsuapellidos(String usuapellidos) {
        this.usuapellidos = usuapellidos;
    }

    public BigInteger getUsucedula() {
        return usucedula;
    }

    public void setUsucedula(BigInteger usucedula) {
        this.usucedula = usucedula;
    }

    public String getUsucorreo() {
        return usucorreo;
    }

    public void setUsucorreo(String usucorreo) {
        this.usucorreo = usucorreo;
    }

    public String getUsuarea() {
        return usuarea;
    }

    public void setUsuarea(String usuarea) {
        this.usuarea = usuarea;
    }

    @XmlTransient
    public List<Procesodegrado> getProcesodegradoList() {
        return procesodegradoList;
    }

    public void setProcesodegradoList(List<Procesodegrado> procesodegradoList) {
        this.procesodegradoList = procesodegradoList;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuid != null ? usuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuid == null && other.usuid != null) || (this.usuid != null && !this.usuid.equals(other.usuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.Usuario[ usuid=" + usuid + " ]";
    }
    
}

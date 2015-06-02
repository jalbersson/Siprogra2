/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jalber
 */
@Embeddable
public class AfectaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROID")
    private BigInteger proid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESID")
    private BigInteger resid;

    public AfectaPK() {
    }

    public AfectaPK(BigInteger proid, BigInteger resid) {
        this.proid = proid;
        this.resid = resid;
    }

    public BigInteger getProid() {
        return proid;
    }

    public void setProid(BigInteger proid) {
        this.proid = proid;
    }

    public BigInteger getResid() {
        return resid;
    }

    public void setResid(BigInteger resid) {
        this.resid = resid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proid != null ? proid.hashCode() : 0);
        hash += (resid != null ? resid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfectaPK)) {
            return false;
        }
        AfectaPK other = (AfectaPK) object;
        if ((this.proid == null && other.proid != null) || (this.proid != null && !this.proid.equals(other.proid))) {
            return false;
        }
        if ((this.resid == null && other.resid != null) || (this.resid != null && !this.resid.equals(other.resid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.siprogra.modelo.AfectaPK[ proid=" + proid + ", resid=" + resid + " ]";
    }
    
}

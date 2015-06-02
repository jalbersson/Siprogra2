/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.clases;

import java.util.Date;

/**
 *
 * @author jalber
 */
public class ActaAprobacionAnteproyecto 
{
    private int numeroActa;
    private Date fechaActa;

    public ActaAprobacionAnteproyecto() 
    {
    }
    public int getNumeroActa() 
    {
        return numeroActa;
    }
    public void setNumeroActa(int numeroActa) 
    {
        this.numeroActa = numeroActa;
    }
    public Date getFechaActa() 
    {
        return fechaActa;
    }
    public void setFechaActa(Date fechaActa) 
    {
        this.fechaActa = fechaActa;
    }
}

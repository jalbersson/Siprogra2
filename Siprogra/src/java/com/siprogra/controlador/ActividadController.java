/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.controlador;

import com.siprogra.DAO.*;
import com.siprogra.clases.*;
import com.siprogra.modelo.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author elkin
 */
@Named(value = "actividadController")
@SessionScoped
public class ActividadController implements Serializable {

    @EJB
    private ProductodetrabajoFacade ejbActividad;
    private Productodetrabajo objActividad;
    private FormatoA formatoA;
    /**
     * Creates a new instance of ActividadController
     */
    public ActividadController() {
    }

    public FormatoA getFormatoA() {
        return formatoA;
    }

    public void setFormatoA(FormatoA formatoA) {
        this.formatoA = formatoA;
    }
    
}

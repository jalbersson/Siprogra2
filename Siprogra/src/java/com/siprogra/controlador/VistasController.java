/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import org.primefaces.event.CloseEvent;
/**
 *
 * @author jalber
 */
@Named(value = "vistasController")
@SessionScoped
public class VistasController implements Serializable 
{

    private boolean mostrarPanel=false;
    private boolean mostrarPanel2=false;
    private boolean mostrarInput=true;
    private boolean mostrarBoton=true;
    private boolean mostrarPanelAnteproyecto=false;
    
    public VistasController() 
    {
    }
    public void mostrarP()
    {
        mostrarPanel=true;
    }
    public void mostrarP2()
    {
        mostrarPanel=false;
        mostrarPanel2=true;
        mostrarInput=false;
        mostrarBoton=false;
    }
    public void mostrarInicial()
    {
        mostrarInput=true;
        mostrarBoton=true;
        mostrarPanel=false;
        mostrarPanel2=false;
    }
    public void mostrarPanelAnteproyectos()
    {
        mostrarPanelAnteproyecto=true;
    }
    public boolean isMostrarPanel() 
    {
        return mostrarPanel;
    }
    public void setMostrarPanel(boolean mostrarPanel) 
    {
        this.mostrarPanel = mostrarPanel;
    }
    public boolean isMostrarPanel2() 
    {
        return mostrarPanel2;
    }
    public void setMostrarPanel2(boolean mostrarPanel2) 
    {
        this.mostrarPanel2 = mostrarPanel2;
    }
    public boolean isMostrarInput() 
    {
        return mostrarInput;
    }
    public void setMostrarInput(boolean mostrarInput) 
    {
        this.mostrarInput = mostrarInput;
    }
    public boolean isMostrarBoton() 
    {
        return mostrarBoton;
    }
    public void setMostrarBoton(boolean mostrarBoton) 
    {
        this.mostrarBoton = mostrarBoton;
    }
    public boolean isMostrarPanelAnteproyecto() 
    {
        return mostrarPanelAnteproyecto;
    }
    public void setMostrarPanelAnteproyecto(boolean mostrarPanelAnteproyecto) 
    {
        this.mostrarPanelAnteproyecto = mostrarPanelAnteproyecto;
    }
    
}

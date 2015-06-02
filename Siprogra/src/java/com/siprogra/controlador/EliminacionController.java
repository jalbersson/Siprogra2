package com.siprogra.controlador;

import com.siprogra.modelo.Fase;
import com.siprogra.modelo.Rol;
import com.siprogra.DAO.FaseFacade;
import com.siprogra.DAO.RolFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author jalber
 */
@ManagedBean
@Named(value = "eliminacionController")
@SessionScoped
public class EliminacionController implements Serializable
{
    @EJB
    private RolFacade rolFX;
    @EJB
    private FaseFacade faseFX;
    
    private List<Rol> listaRolesX;
    private List<Fase> listaFasesX;
    
    public EliminacionController() 
    {
    }
    @PostConstruct
    private void init() 
    {
        listaRolesX=rolFX.findAll();
        listaFasesX=faseFX.findAll();
    }
    public void eliminarRol(Rol rol) throws Exception
    {
       rolFX.remove(rol);     
       FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha eliminado el rol correctamente.",""));
    }
    public void eliminarFase(Fase fase) throws Exception
    {
       faseFX.remove(fase);     
       FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha eliminado la fase correctamente.",""));
    }
}

package com.siprogra.controlador;

import com.siprogra.modelo.Fase;
import com.siprogra.modelo.Restriccion;
import com.siprogra.modelo.Rol;
import com.siprogra.DAO.FaseFacade;
import com.siprogra.DAO.RestriccionFacade;
import com.siprogra.DAO.RolFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jalber
 */
@ManagedBean
@Named(value = "edicionController")
@SessionScoped
public class EdicionController implements Serializable
{
    @EJB
    private RolFacade rolFE;
    @EJB
    private FaseFacade faseFE;
    @EJB
    private RestriccionFacade restriccionFE;
    
    public EdicionController() 
    {
    }
    public void editarRol(RowEditEvent event) throws  Exception
    {
        Rol rol=(Rol) event.getObject();
        rolFE.edit(rol);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha Editado Correctamente.",""));
    }
    public void CancelarEdicion(RowEditEvent event) throws  Exception 
    {           
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha Cancelado la edicion.",""));        
    }
    public void editarEtapa(RowEditEvent event) throws  Exception
    {
        Fase fase=(Fase) event.getObject();
        faseFE.edit(fase);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha Editado Correctamente.",""));
    }
    public void editarRestriccion(RowEditEvent event) throws  Exception
    {
        Restriccion restriccion=(Restriccion) event.getObject();
        restriccionFE.edit(restriccion);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha Editado Correctamente.",""));
    }
}

package com.siprogra.controlador;

import com.siprogra.modelo.Fase;
import com.siprogra.modelo.Productodetrabajo;
import com.siprogra.modelo.Restriccion;
import com.siprogra.modelo.Rol;
import com.siprogra.DAO.FaseFacade;
import com.siprogra.DAO.ProductodetrabajoFacade;
import com.siprogra.DAO.RestriccionFacade;
import com.siprogra.DAO.RolFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author jalber
 */
@ManagedBean
@Named(value = "consultasController")
@SessionScoped
public class ConsultasController implements Serializable
{
    @EJB
    private RolFacade rolF;
    @EJB
    private FaseFacade faseF;
    @EJB
    private RestriccionFacade restriccionF;
    @EJB
    private ProductodetrabajoFacade documentoF;
    private List<Rol> listaRoles;
    private List<Fase> listaFases;
    private List<Restriccion> listaRestricciones;
    private List<Productodetrabajo> listaDocumentos;
    private boolean seMuestra;
    private boolean ocultarBoton;
    private List<String> listaFasesNombres;

    public List<String> getListaFasesNombres() {
        return listaFasesNombres;
    }

    public void setListaFasesNombres(List<String> listaFasesNombres) {
        this.listaFasesNombres = listaFasesNombres;
    }

    public boolean isOcultarBoton() {
        return ocultarBoton;
    }

    public void setOcultarBoton(boolean ocultarBoton) {
        this.ocultarBoton = ocultarBoton;
    }

    public boolean isSeMuestra() {
        return seMuestra;
    }

    public void setSeMuestra(boolean seMuestra) {
        this.seMuestra = seMuestra;
    }
    
    public ConsultasController() 
    {
    }
    @PostConstruct
    private void init() 
    {
        listaRoles=rolF.findAll();
        listaFases=faseF.findAll();
        listaRestricciones=restriccionF.findAll();
        listaDocumentos=documentoF.findAll();
        seMuestra=false;
        ocultarBoton=true;
    }
    public List<Rol> listarRoles() 
    {
        return rolF.findAll();
    }
    public List<Fase> listarFases() 
    {
        return faseF.findAll();
    }
    public List<Restriccion> listarRestricciones() 
    {
        return restriccionF.findAll();
    }
    public List<Productodetrabajo> listarDocumentos() 
    {
        return documentoF.findAll();
    }

    public List<Rol> getListaRoles() 
    {
        return listaRoles;
    }
    public void setListaRoles(List<Rol> listaRoles) 
    {
        this.listaRoles = listaRoles;
    }
    public List<Fase> getListaFases() 
    {
        return listaFases;
    }
    public void setListaFases(List<Fase> listaFases) 
    {
        this.listaFases = listaFases;
    }
    public List<Restriccion> getListaRestricciones() 
    {
        return listaRestricciones;
    }
    public void setListaRestricciones(List<Restriccion> listaRestricciones) 
    {
        this.listaRestricciones = listaRestricciones;
    }
    public void mostrarTexto()
    {
        if(this.getListaFases().get(0)!=null)
        {
            seMuestra=true;
            ocultarBoton=false;
        }
    }
    public String listarFasesNombres(int id)
    {
        
        return faseF.nombreFaseID(id);
    }

    public List<Productodetrabajo> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<Productodetrabajo> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }
    
}

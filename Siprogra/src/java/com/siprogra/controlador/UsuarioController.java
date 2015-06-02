/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.controlador;

import com.siprogra.DAO.*;
import com.siprogra.modelo.*;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author elkin
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacade ejbUsuario;
    private Usuario objUsuario;
    private List<Usuario> items;
    private Usuario selected;

    private Rol objRol;

    //variabes entrada texto
    private String login;
    private String password;

    //variables inicio sesion
    private boolean sesionIniciada;
    private String rol;

    /**
     * Creates a new instance of usuarioController
     */
    public UsuarioController() {
        this.sesionIniciada = false;
    }

    public List<Usuario> getItems() {
        
        return ejbUsuario.findAll(); 
    }

    public void setItems(List<Usuario> items) {
        this.items = items;
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }
    
    public void seleccionarRol() {
        List<Usuario> lstUsuarios = ejbUsuario.findAll();
        FacesContext context = FacesContext.getCurrentInstance();

        for (Usuario usu : lstUsuarios) {
            if (usu.getUsunombreusuario().equals(login) && usu.getUsucontrasenia().equals(password)) {
                objUsuario = usu;
            }
        }

        if (objUsuario != null) {
            this.sesionIniciada = true;
            context.getExternalContext().getSessionMap().put("login", objUsuario.getUsunombreusuario());
            context.getExternalContext().getSessionMap().put("password", objUsuario.getUsucontrasenia());
            context.getExternalContext().getSessionMap().put("activo", sesionIniciada);
            RequestContext.getCurrentInstance().execute("PF('IniciarSesionDialog').hide()");
            RequestContext.getCurrentInstance().execute("PF('SelRolDialog').show()");
        } else {
            this.sesionIniciada = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Datos Incorrectos", null));
            this.objUsuario = new Usuario();
        }

    }

    public void iniciarSesion() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        this.rol = objRol.getRolnombre();
        context.getExternalContext().getSessionMap().put("rol", rol);

        if (this.sesionIniciada) {

            if (this.rol.equals("Admin")) {
                context.getExternalContext().redirect("./administrador/GestionWorkflow.xhtml");
            }

            if (this.rol.equals("Estudiante")) {
                context.getExternalContext().redirect("./estudiante/GestionProyectoEstudiante.xhtml");
            }

            if (this.rol.equals("Docente")) {
                context.getExternalContext().redirect("./profesor/indexDocente.xhtml");
            }

            if (this.rol.equals("Docente jurado")) {
                context.getExternalContext().redirect("./profesor/indexDocente.xhtml");
            }

            if (this.rol.equals("Docente director")) {
                context.getExternalContext().redirect("./profesor/indexDocente.xhtml");
            }

            if (this.rol.equals("Docente evaluador")) {
                context.getExternalContext().redirect("./profesor/indexDocente.xhtml");
            }

            if (this.rol.equals("Jefe de departamento")) {
                context.getExternalContext().redirect("./profesor/indexJefeDepto.xhtml");
            }
            
             if (this.rol.equals("Departamento")) {
                context.getExternalContext().redirect("./profesor/indexDepto.xhtml");
            }
             
            if (this.rol.equals("Coordinador de programa")) {
                context.getExternalContext().redirect("./profesor/indexDocente.xhtml");
            }
            
            if (this.rol.equals("Secretaria general")) {
                context.getExternalContext().redirect("./secretaria/GestionProyectoSecretaria.xhtml");
            }

        }
    }

    public void cerrarSesion() throws IOException {
        this.objUsuario = new Usuario();
        this.login = new String();
        this.password = new String();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public Rol getObjRol() {
        return objRol;
    }

    public void setObjRol(Rol objRol) {
        this.objRol = objRol;
    }

}

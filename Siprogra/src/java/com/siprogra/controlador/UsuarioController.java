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
import java.util.ArrayList;
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
    private List<Usuario> lstUsuario;
    private List<Usuario> lstDocente;

    //variabes entrada texto
    private String login;
    private String password;

    //variables inicio sesion
    private boolean sesionIniciada;
    private String rol;

    private boolean esDirector;
    private boolean esEvaluador;
    private boolean esJurado;
    private boolean esJefe;
    private boolean esDepto;
    private boolean esCoordinador;

    /**
     * Creates a new instance of usuarioController
     */
    public UsuarioController() {
        this.sesionIniciada = false;
    }

    public void iniciarSesion() throws IOException {

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
            this.rol = objUsuario.getRolList().get(0).getRolnombre();
            context.getExternalContext().getSessionMap().put("rol", rol);
        } else {
            this.sesionIniciada = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Datos Incorrectos", null));
            this.objUsuario = new Usuario();
        }

        if (this.sesionIniciada) {

            if (rol.equals("Admin")) {
                context.getExternalContext().redirect("./administrador/GestionWorkflow.xhtml");
            }

            if (rol.equals("Estudiante")) {
                context.getExternalContext().redirect("./estudiante/GestionProyectoEstudiante.xhtml");
            }

            if (rol.toLowerCase().contains("docente") || rol.toLowerCase().contains("departamento")
                    || rol.toLowerCase().contains("coordinador")) {
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

    public List<Usuario> getLstDocente() {
        lstUsuario = ejbUsuario.findAll();
        lstDocente = new ArrayList<>();
        for(Usuario usu : lstUsuario) {
            if(!usu.getRolList().isEmpty()) {
                if(usu.getRolList().get(0).getRolnombre().toLowerCase().contains("docente") ||
                   usu.getRolList().get(0).getRolnombre().toLowerCase().contains("departamento") ) {
                lstDocente.add(usu);
            }
            }            
        }
        return lstDocente;
    }

    public void setLstDocente(List<Usuario> lstDocente) {
        this.lstDocente = lstDocente;
    }

    public boolean isEsDirector() {
        return perteneceRol("Docente director");
    }

    public void setEsDirector(boolean esDirector) {
        this.esDirector = esDirector;
    }

    public boolean isEsEvaluador() {
        return perteneceRol("Docente evaluador");
    }

    public void setEsEvaluador(boolean esEvaluador) {
        this.esEvaluador = esEvaluador;
    }

    public boolean isEsJurado() {
        return perteneceRol("Docente jurado");
    }

    public void setEsJurado(boolean esJurado) {
        this.esJurado = esJurado;
    }

    public boolean isEsJefe() {
        return perteneceRol("Jefe de departamento");
    }

    public void setEsJefe(boolean esJefe) {
        this.esJefe = esJefe;
    }

    public boolean isEsDepto() {
        return perteneceRol("Departamento");
    }

    public void setEsDepto(boolean esDepto) {
        this.esDepto = esDepto;
    }

    public boolean isEsCoordinador() {
        return perteneceRol("Coordinador de programa");
    }

    public void setEsCoordinador(boolean esCoordinador) {
        this.esCoordinador = esCoordinador;
    }

    public boolean perteneceRol(String ro) {
        obtenerUsuario();
        for (Rol r : objUsuario.getRolList()) {
            if (r.getRolnombre().equalsIgnoreCase(ro)) {
                return true;
            }
        }
        return false;
    }

    public void obtenerUsuario() {
        String login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        lstUsuario = ejbUsuario.findAll();
        for (Usuario usr : lstUsuario) {
            if (usr.getUsunombreusuario().equals(login)) {
                objUsuario = usr;
            }
        }
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

}
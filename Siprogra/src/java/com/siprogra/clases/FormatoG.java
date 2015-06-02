package com.siprogra.clases;

import java.util.Date;

/**
 *
 * @author jalber
 */
public class FormatoG 
{
    private String titulo;
    private Date fechaSustentacion;
    private Date fechaConstancia;
    private String aprobado;
    private String ajustes;
    private String director;
    private String juradoCoordinador;
    private String jurado;
    private int numeroRecibo;

    public FormatoG() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaSustentacion() {
        return fechaSustentacion;
    }

    public void setFechaSustentacion(Date fechaSustentacion) {
        this.fechaSustentacion = fechaSustentacion;
    }

    public Date getFechaConstancia() {
        return fechaConstancia;
    }

    public void setFechaConstancia(Date fechaConstancia) {
        this.fechaConstancia = fechaConstancia;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public String getAjustes() {
        return ajustes;
    }

    public void setAjustes(String ajustes) {
        this.ajustes = ajustes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getJuradoCoordinador() {
        return juradoCoordinador;
    }

    public void setJuradoCoordinador(String juradoCoordinador) {
        this.juradoCoordinador = juradoCoordinador;
    }

    public String getJurado() {
        return jurado;
    }

    public void setJurado(String jurado) {
        this.jurado = jurado;
    }

    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(int numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.DAO;

import com.siprogra.modelo.Procesodegrado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jalber
 */
@Stateless
public class ProcesodegradoFacade extends AbstractFacade<Procesodegrado> {
    @PersistenceContext(unitName = "SiprograPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesodegradoFacade() {
        super(Procesodegrado.class);
    }
    public Procesodegrado buscarPorTitulo(String titulo)
    {
        Query query = getEntityManager().createNamedQuery("Procesodegrado.findByProctitulo"); 
        query.setParameter("proctitulo", titulo);
        Procesodegrado result= (Procesodegrado)query.getSingleResult();
        return result;
    }
    public List<Procesodegrado> buscarPorEstado(String estado)
    {
        Query query = getEntityManager().createNamedQuery("Procesodegrado.findByEstado"); 
        query.setParameter("estado", estado);
        List<Procesodegrado> result= (List<Procesodegrado>) query.getResultList();
        return result;
    }
}

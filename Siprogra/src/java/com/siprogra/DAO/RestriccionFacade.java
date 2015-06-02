/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.DAO;

import com.siprogra.modelo.Restriccion;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jalber
 */
@Stateless
public class RestriccionFacade extends AbstractFacade<Restriccion> {
    @PersistenceContext(unitName = "SiprograPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RestriccionFacade() {
        super(Restriccion.class);
    }
    public BigDecimal maximaRestriccion() 
    {
        Query query = getEntityManager().createNamedQuery("Restriccion.maximoId");
        BigDecimal result = (BigDecimal) query.getSingleResult();
        return result;
    }
}

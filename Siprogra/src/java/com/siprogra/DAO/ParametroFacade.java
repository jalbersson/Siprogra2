/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siprogra.DAO;

import com.siprogra.modelo.Parametro;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author elkin
 */
@Stateless
public class ParametroFacade extends AbstractFacade<Parametro> {

    @PersistenceContext(unitName = "SiprograPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroFacade() {
        super(Parametro.class);
    }

    public boolean buscarPorProdutoTrabajoId(BigDecimal proid) {
        Query query = getEntityManager().createNamedQuery("Parametro.findByProid");
        query.setParameter("proid", proid);
        List<Parametro> resultList = query.getResultList();

        return !resultList.isEmpty();
    }

    public List<Parametro> buscarRetornarPorProdutoTrabajoId(BigDecimal proid) {
        Query query = getEntityManager().createNamedQuery("Parametro.findByProid");
        query.setParameter("proid", proid);
        List<Parametro> resultList = query.getResultList();

        return resultList;
    }

    public BigInteger maximoParmetroID() {
        Query query = getEntityManager().createNamedQuery("Parametro.findMaxParid");
        BigInteger result = (BigInteger) query.getSingleResult();
        return result;
    }
}

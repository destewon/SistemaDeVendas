/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.ContasPagar;
import br.com.foursys.vendas.model.ContasPagar;
import br.com.foursys.vendas.util.HibernateUtil;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author amendes
 */
public class ContasPagarDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<ContasPagar> buscarTodos() throws Exception {

        ArrayList<ContasPagar> listaRetorno = new ArrayList<ContasPagar>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(ContasPagar.class);

        criteria.addOrder(Order.asc("idContasPagar"));

        listaRetorno = (ArrayList<ContasPagar>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public ContasPagar buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        ContasPagar contasPagar = (ContasPagar) sessao.get(ContasPagar.class, codigo);

        sessao.close();
        return contasPagar;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.ContasReceber;
import br.com.foursys.vendas.model.ContasReceber;
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
public class ContasReceberDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<ContasReceber> buscarTodos() throws Exception {

        ArrayList<ContasReceber> listaRetorno = new ArrayList<ContasReceber>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(ContasReceber.class);

        criteria.addOrder(Order.asc("idContasReceber"));

        listaRetorno = (ArrayList<ContasReceber>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public ContasReceber buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        ContasReceber contasReceber = (ContasReceber) sessao.get(ContasReceber.class, codigo);

        sessao.close();
        return contasReceber;
    }

}

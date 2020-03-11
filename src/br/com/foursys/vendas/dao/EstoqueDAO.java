/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.Estoque;
import br.com.foursys.vendas.model.Estoque;
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
public class EstoqueDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Estoque> buscarTodos() throws Exception {

        ArrayList<Estoque> listaRetorno = new ArrayList<Estoque>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(Estoque.class);

        criteria.addOrder(Order.asc("idEstoque"));

        listaRetorno = (ArrayList<Estoque>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public Estoque buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Estoque estoque = (Estoque) sessao.get(Estoque.class, codigo);

        sessao.close();
        return estoque;
    }

}

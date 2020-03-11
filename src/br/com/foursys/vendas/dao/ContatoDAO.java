/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Contato;
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
public class ContatoDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Contato> buscarTodos() throws Exception {

        ArrayList<Contato> listaRetorno = new ArrayList<Contato>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(Contato.class);

        criteria.addOrder(Order.asc("idContato"));

        listaRetorno = (ArrayList<Contato>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public Contato buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Contato contato = (Contato) sessao.get(Contato.class, codigo);

        sessao.close();
        return contato;
    }

}

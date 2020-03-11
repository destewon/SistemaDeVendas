/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.Venda;
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
public class VendaDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Venda> buscarTodos() throws Exception {

        ArrayList<Venda> listaRetorno = new ArrayList<Venda>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(Venda.class);

        criteria.addOrder(Order.asc("idVenda"));

        listaRetorno = (ArrayList<Venda>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public Venda buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Venda venda = (Venda) sessao.get(Venda.class, codigo);
        sessao.close();
        return venda;
    }

}

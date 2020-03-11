/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.ItemVenda;
import br.com.foursys.vendas.model.ItemVenda;
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
public class ItemVendaDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<ItemVenda> buscarTodos() throws Exception {

        ArrayList<ItemVenda> listaRetorno = new ArrayList<ItemVenda>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(ItemVenda.class);

        criteria.addOrder(Order.asc("idItemVenda"));

        listaRetorno = (ArrayList<ItemVenda>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public ItemVenda buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        ItemVenda itemVenda = (ItemVenda) sessao.get(ItemVenda.class, codigo);
        sessao.close();
        return itemVenda;
    }

}

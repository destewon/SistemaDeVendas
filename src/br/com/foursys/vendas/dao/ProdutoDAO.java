/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.Produto;
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
public class ProdutoDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Produto> buscarTodos() throws Exception {

        ArrayList<Produto> listaRetorno = new ArrayList<Produto>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(Produto.class);

        criteria.addOrder(Order.asc("idProduto"));

        listaRetorno = (ArrayList<Produto>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public Produto buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Produto produto = (Produto) sessao.get(Produto.class, codigo);
        sessao.close();
        return produto;
    }

}

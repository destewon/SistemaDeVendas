/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.Fornecedor;
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
public class FornecedorDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Fornecedor> buscarTodos() throws Exception {

        ArrayList<Fornecedor> listaRetorno = new ArrayList<Fornecedor>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(Fornecedor.class);

        criteria.addOrder(Order.asc("idFornecedor"));

        listaRetorno = (ArrayList<Fornecedor>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public Fornecedor buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Fornecedor fornecedor = (Fornecedor) sessao.get(Fornecedor.class, codigo);
        sessao.close();
        return fornecedor;
    }

}

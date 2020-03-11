/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.Estado;
import br.com.foursys.vendas.model.Estado;
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
public class EstadoDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Estado> buscarTodos() throws Exception {

        ArrayList<Estado> listaRetorno = new ArrayList<Estado>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(Estado.class);

        criteria.addOrder(Order.asc("idEstado"));

        listaRetorno = (ArrayList<Estado>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public Estado buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Estado estado = (Estado) sessao.get(Estado.class, codigo);

        sessao.close();
        return estado;
    }

}

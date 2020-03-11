/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.Cliente;
import br.com.foursys.vendas.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author amendes
 */
public class ClienteDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Cliente> buscarTodos() throws Exception {

        ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(Cliente.class);

        criteria.addOrder(Order.asc("idCliente"));

        listaRetorno = (ArrayList<Cliente>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public Cliente buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Cliente cliente = (Cliente) sessao.get(Cliente.class, codigo);
        sessao.close();
        return cliente;
    }

}

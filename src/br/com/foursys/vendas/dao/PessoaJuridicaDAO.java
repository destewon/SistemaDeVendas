/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;

import br.com.foursys.vendas.model.PessoaJuridica;
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
public class PessoaJuridicaDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<PessoaJuridica> buscarTodos() throws Exception {

        ArrayList<PessoaJuridica> listaRetorno = new ArrayList<PessoaJuridica>();

        Session sessao = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = sessao.createCriteria(PessoaJuridica.class);

        criteria.addOrder(Order.asc("idPessoaJuridica"));

        listaRetorno = (ArrayList<PessoaJuridica>) criteria.list();

        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public PessoaJuridica buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        PessoaJuridica pessoaJuridica = (PessoaJuridica) sessao.get(PessoaJuridica.class, codigo);

        sessao.close();
        return pessoaJuridica;
    }

}

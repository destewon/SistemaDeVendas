/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;


import br.com.foursys.vendas.model.Cidade;
import br.com.foursys.vendas.model.Cidade;
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
public class CidadeDAO extends GenericDAO {
    
   @SuppressWarnings("unchecked")
    public ArrayList<Cidade> buscarTodos() throws Exception{

        ArrayList<Cidade> listaRetorno = new ArrayList<Cidade>();
        
        Session sessao= HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = sessao.createCriteria(Cidade.class);
        
        criteria.addOrder(Order.asc("idCidade"));
        
        listaRetorno = (ArrayList<Cidade>) criteria.list();
        
        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }

    public Cidade buscarPorCodigo(int codigo) throws Exception{
        Session sessao= HibernateUtil.getSessionFactory().openSession();
        
        Cidade cidade = (Cidade) sessao.get(Cidade.class, codigo);
        
        sessao.close();
        return cidade;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;


import br.com.foursys.vendas.model.Endereco;
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
public class EnderecoDAO extends GenericDAO {
    
    @SuppressWarnings("unchecked")
    public ArrayList<Endereco> buscarTodos() throws Exception{

        ArrayList<Endereco> listaRetorno = new ArrayList<Endereco>();
        
        Session sessao= HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = sessao.createCriteria(Endereco.class);
        
        criteria.addOrder(Order.asc("idEndereco"));
        
        listaRetorno = (ArrayList<Endereco>) criteria.list();
        
        //Transaction transacao = sessao.beginTransaction();

        return listaRetorno;
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList<Endereco> pesquisaLogradouro(String logradouro) throws Exception{

        ArrayList<Endereco> listaRetorno = new ArrayList<Endereco>();
        
        Session sessao= HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = sessao.createCriteria(Endereco.class);
        
        criteria.add(Restrictions.ilike("nome", logradouro +"%"));
        
        criteria.addOrder(Order.asc("nome"));
        
        listaRetorno = (ArrayList<Endereco>) criteria.list();
        
        //Transaction transacao = sessao.beginTransaction();
sessao.close();
        return listaRetorno;
    }

    public Endereco buscarPorCodigo(int codigo) throws Exception{
        Session sessao= HibernateUtil.getSessionFactory().openSession();
        
        Endereco endereco = (Endereco) sessao.get(Endereco.class, codigo);
        
        sessao.close();
        return endereco;
    }
    
     
    
    
}

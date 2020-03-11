/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.dao;


import br.com.foursys.vendas.model.PessoaFisica;
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
public class PessoaFisicaDAO extends GenericDAO {
    
    @SuppressWarnings("unchecked")
    public ArrayList<PessoaFisica> buscarTodos() throws Exception{

        ArrayList<PessoaFisica> listaRetorno = new ArrayList<PessoaFisica>();
        
        Session sessao= HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = sessao.createCriteria(PessoaFisica.class);
        
        criteria.addOrder(Order.asc("idPessoaFisica"));
        
        listaRetorno = (ArrayList<PessoaFisica>) criteria.list();
        
        //Transaction transacao = sessao.beginTransaction();
        sessao.close();
        return listaRetorno;
    }
    
  

    public PessoaFisica buscarPorCodigo(int codigo) throws Exception{
        Session sessao= HibernateUtil.getSessionFactory().openSession();
        
        PessoaFisica pessoaFisica = (PessoaFisica) sessao.get(PessoaFisica.class, codigo);
        
        sessao.close();
        return pessoaFisica;
    }
    
     
    
    
}

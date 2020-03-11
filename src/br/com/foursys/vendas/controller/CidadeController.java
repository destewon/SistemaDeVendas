/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.dao.CidadeDAO;
import br.com.foursys.vendas.model.Cidade;
import java.util.List;

/**
 *
 * @author amendes
 */
public class CidadeController {
    private List<Cidade> listaCidade;
    private CidadeDAO dao = new CidadeDAO();
    
    public List<Cidade> buscarCidades() throws Exception{
       
        listaCidade=dao.buscarTodos();
        return listaCidade;
    }
    
}

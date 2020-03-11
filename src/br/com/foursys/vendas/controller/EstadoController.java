/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.controller;

import br.com.foursys.vendas.dao.EstadoDAO;
import br.com.foursys.vendas.model.Estado;
import java.util.List;

/**
 *
 * @author amendes
 */
public class EstadoController {
    
   
    private List<Estado> listaEstado;
    private EstadoDAO dao = new EstadoDAO();
    
    public List<Estado> buscarEstados() throws Exception{
       
        listaEstado=dao.buscarTodos();
        return listaEstado;
    }
    
}


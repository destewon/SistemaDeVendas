/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.EstadoDAO;
import br.com.foursys.vendas.model.Estado;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteEstado {

    public void salvarEstado() {

        EstadoDAO dao = new EstadoDAO();
        Estado estado = new Estado();

        
        estado.setNome("São Paulo");
        estado.setUf("SP");
        dao.salvar(estado);
        System.out.println("Estado salvo com sucesso");
        System.exit(0);
    }

    public void buscarCodigo() throws Exception {
        EstadoDAO dao = new EstadoDAO();
        Estado estado = dao.buscarPorCodigo(1);
        System.out.println("Estado:" + estado.getNome().toString());
        System.out.println("UF:" + estado.getUf().toString());

    }

    public void buscarTodos() throws Exception {
        EstadoDAO dao = new EstadoDAO();
        ArrayList<Estado> lista = dao.buscarTodos();

        for (Estado estado1 : lista) {
            System.out.println("Estado:" + estado1.getNome());
            System.out.println("UF:" + estado1.getUf());
            System.out.println("");
        }

    }

    public static void main(String[] args) throws Exception {
        new TesteEstado().salvarEstado();
    }

}

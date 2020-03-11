/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.CidadeDAO;
import br.com.foursys.vendas.model.Cidade;
import br.com.foursys.vendas.model.Estado;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteCidade {

    public void salvarCidade() {

        CidadeDAO dao = new CidadeDAO();
        Cidade cidade = new Cidade();
        Estado estado = new Estado();
        
        
        cidade.setNome("Osasco");

        estado.setIdEstado(1);
        
        cidade.setEstadoIdEstado(estado);

        dao.salvar(cidade);
        System.out.println("Cidade salvo com sucesso");
        System.exit(0);
    }

    public void buscarCodigo() throws Exception {
        CidadeDAO dao = new CidadeDAO();
        Cidade cidade = dao.buscarPorCodigo(1);
        System.out.println("Cidade:" + cidade.getNome().toString());
        System.out.println("Estado:" + cidade.getEstadoIdEstado().getNome());
        System.exit(0);
    }

    public void buscarTodos() throws Exception {
        CidadeDAO dao = new CidadeDAO();
        ArrayList<Cidade> lista = dao.buscarTodos();

        for (Cidade cidade1 : lista) {
            System.out.println("Cidade:" + cidade1.getNome());
            System.out.println("Estado:" + cidade1.getEstadoIdEstado().getNome());
            System.out.println("");
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteCidade().salvarCidade();
    }

}

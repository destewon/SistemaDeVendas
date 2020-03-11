/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.ContatoDAO;
import br.com.foursys.vendas.model.Contato;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteContato {

    public void salvarContato() {

        ContatoDAO dao = new ContatoDAO();
        Contato contato = new Contato();

        contato.setTelefone("11 123456789");
        contato.setCelular("11 987654321");
        contato.setEmail("123456@789.321");
        dao.salvar(contato);
        System.out.println("Contato salvo com sucesso");
        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        ContatoDAO dao = new ContatoDAO();
        Contato contato = dao.buscarPorCodigo(1);
        System.out.println("Telefone:" + contato.getTelefone());
        System.out.println("Celular:" + contato.getCelular());
        System.out.println("Email:" + contato.getEmail());
        System.exit(0);
    }

    public void buscarTodos() throws Exception {
        ContatoDAO dao = new ContatoDAO();
        ArrayList<Contato> lista = dao.buscarTodos();

        for (Contato contato1 : lista) {
            System.out.println("Telefone:" + contato1.getTelefone());
            System.out.println("Celular:" + contato1.getCelular());
            System.out.println("Email:" + contato1.getEmail());
            System.out.println("");
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteContato().buscarTodos();
    }

}

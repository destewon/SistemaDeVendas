/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.PessoaFisicaDAO;
import br.com.foursys.vendas.model.PessoaFisica;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TestePessoaFisica {

    public void salvarPessoaFisica() {

        PessoaFisicaDAO dao = new PessoaFisicaDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setNome("Matheus Poda");
        pessoaFisica.setRg("66.666.666-6");
        pessoaFisica.setCpf("666.666.666-6");
        pessoaFisica.setDataNascimento("23/10/2000");
        dao.salvar(pessoaFisica);
        System.out.println("PessoaFisica salvo com sucesso");

    }

    public void buscarCodigo() throws Exception {
        PessoaFisicaDAO dao = new PessoaFisicaDAO();
        PessoaFisica pessoaFisica = dao.buscarPorCodigo(1);
        System.out.println("Nome:" + pessoaFisica.getNome());
        System.out.println("RG:" + pessoaFisica.getRg());
        System.out.println("CPF:" + pessoaFisica.getCpf());
        System.out.println("Data de Nascimento:" + pessoaFisica.getDataNascimento());
        System.out.println("");

    }

    public void buscarTodos() throws Exception {
        PessoaFisicaDAO dao = new PessoaFisicaDAO();
        ArrayList<PessoaFisica> lista = dao.buscarTodos();

        for (PessoaFisica pessoaFisica1 : lista) {
            System.out.println("PessoaFisica:" + pessoaFisica1.getNome());
            System.out.println("RG:" + pessoaFisica1.getRg());
            System.out.println("CPF:" + pessoaFisica1.getCpf());
            System.out.println("Data de Nascimento:" + pessoaFisica1.getDataNascimento());
            System.out.println("");
        }

    }

    public static void main(String[] args) throws Exception {
        new TestePessoaFisica().buscarTodos();
    }

}

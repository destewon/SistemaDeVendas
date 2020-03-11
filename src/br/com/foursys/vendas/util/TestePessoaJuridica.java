/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.PessoaJuridicaDAO;
import br.com.foursys.vendas.model.PessoaJuridica;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TestePessoaJuridica {

    public void salvarPessoaJuridica() {

        PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setRazaoSocial("Brasileirinha");
        
        pessoaJuridica.setCnpj("43.331.371/0001-81");
        pessoaJuridica.setInscricaoEstadual("118.429.152.195");
        pessoaJuridica.setDataFundacao("23/10/2000");
        dao.salvar(pessoaJuridica);
        System.out.println("PessoaJuridica salvo com sucesso");

    }

    public void buscarCodigo() throws Exception {
        PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
        PessoaJuridica pessoaJuridica = dao.buscarPorCodigo(4);
        System.out.println("Razão Social:" + pessoaJuridica.getRazaoSocial());
        
        System.out.println("Cnpj:" + pessoaJuridica.getCnpj());
        System.out.println("Inscrição Estadual:" + pessoaJuridica.getInscricaoEstadual());
        System.out.println("Data de Fundação:" + pessoaJuridica.getDataFundacao());
        System.out.println("");

    }

    public void buscarTodos() throws Exception {
        PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
        ArrayList<PessoaJuridica> lista = dao.buscarTodos();

        for (PessoaJuridica pessoaJuridica1 : lista) {
            System.out.println("Razão Social:" + pessoaJuridica1.getRazaoSocial());
            
            System.out.println("Cnpj:" + pessoaJuridica1.getCnpj());
            System.out.println("Inscrição Estadual:" + pessoaJuridica1.getInscricaoEstadual());
            System.out.println("Data de Fundação:" + pessoaJuridica1.getDataFundacao());
            System.out.println("");
        }

    }

    public static void main(String[] args) throws Exception {
        new TestePessoaJuridica().buscarTodos();
    }

}

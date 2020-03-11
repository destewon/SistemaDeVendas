/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.FornecedorDAO;
import br.com.foursys.vendas.dao.PessoaJuridicaDAO;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.Fornecedor;
import br.com.foursys.vendas.model.PessoaJuridica;
import java.util.ArrayList;

/**
 *
 * @author amendes
 */
public class FornecedorPj {

    public void salvarFornecedor() {

        //FornecedorDAO dao = new FornecedorDAO();
        //Fornecedor fornecedor = new Fornecedor();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = new Fornecedor();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        Endereco endereco = new Endereco();
        Contato contato = new Contato();
        contato.setIdContato(1);
        //PessoaJuridica pessoaJuridica= new PessoaJuridica();

        pessoaJuridica.setRazaoSocial("Vendas Ltda");
        
        pessoaJuridica.setCnpj("43.331.371/0001-80");
        pessoaJuridica.setInscricaoEstadual("118.429.152.194");
        pessoaJuridica.setDataFundacao("27/07/2018");
        pessoaJuridicaDAO.salvar(pessoaJuridica);
        System.out.println("PessoaJuridica salvo com sucesso");
        endereco.setIdEndereco(2);

        fornecedor.setContato("569245698");
        fornecedor.setTipoPessoa("j");
        fornecedor.setEnderecoIdEndereco(endereco);
        fornecedor.setContatoIdContato(contato);
        fornecedor.setPessoaJuridicaIdPessoaJuridica(pessoaJuridica);
        fornecedorDAO.salvar(fornecedor);
        System.out.println("Fornecedor salvo com sucesso");
        System.exit(0);
    }

    public void buscarCodigo() throws Exception {
        FornecedorDAO dao = new FornecedorDAO();
        Fornecedor fornecedor = dao.buscarPorCodigo(3);
        if (fornecedor.getTipoPessoa().trim().equals("f")) {
            System.out.println("Contato:" + fornecedor.getContato());
            System.out.println("Logradouro:" + fornecedor.getEnderecoIdEndereco().getLogradouro());

            System.out.println("Numero:" + fornecedor.getEnderecoIdEndereco().getNumero());
            System.out.println("Complemento:" + fornecedor.getEnderecoIdEndereco().getComplemento());
            System.out.println("Bairro:" + fornecedor.getEnderecoIdEndereco().getBairro());
            System.out.println("cep:" + fornecedor.getEnderecoIdEndereco().getCep());

            System.out.println("Cidade:" + fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
            System.out.println("Estado:" + fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());

            System.out.println("Telefone:" + fornecedor.getContatoIdContato().getTelefone());
            System.out.println("Celular:" + fornecedor.getContatoIdContato().getCelular());
            System.out.println("Email:" + fornecedor.getContatoIdContato().getEmail());

            System.out.println("Razão Social:" + fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
           
            System.out.println("Cnpj:" + fornecedor.getPessoaJuridicaIdPessoaJuridica().getCnpj());
            System.out.println("Inscrição Estadual:" + fornecedor.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
            System.out.println("Data de Fundação:" + fornecedor.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());
            System.out.println("");
        }
        System.exit(0);
    }

    public void buscarTodos() throws Exception {
        FornecedorDAO dao = new FornecedorDAO();
        ArrayList<Fornecedor> lista = dao.buscarTodos();

        for (Fornecedor fornecedor1 : lista) {
            if (fornecedor1.getTipoPessoa().trim().equals("j")) {
                System.out.println("Contato:" + fornecedor1.getContato());
                System.out.println("Logradouro:" + fornecedor1.getEnderecoIdEndereco().getLogradouro());

                System.out.println("Numero:" + fornecedor1.getEnderecoIdEndereco().getNumero());
                System.out.println("Complemento:" + fornecedor1.getEnderecoIdEndereco().getComplemento());
                System.out.println("Bairro:" + fornecedor1.getEnderecoIdEndereco().getBairro());
                System.out.println("cep:" + fornecedor1.getEnderecoIdEndereco().getCep());

                System.out.println("Cidade:" + fornecedor1.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
                System.out.println("Estado:" + fornecedor1.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());

                System.out.println("Telefone:" + fornecedor1.getContatoIdContato().getTelefone());
                System.out.println("Celular:" + fornecedor1.getContatoIdContato().getCelular());
                System.out.println("Email:" + fornecedor1.getContatoIdContato().getEmail());

                System.out.println("Razão Social:" + fornecedor1.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
                
                System.out.println("Cnpj:" + fornecedor1.getPessoaJuridicaIdPessoaJuridica().getCnpj());
                System.out.println("Inscrição Estadual:" + fornecedor1.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
                System.out.println("Data de Fundação:" + fornecedor1.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());
                System.out.println("");
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new FornecedorPj().buscarTodos();
    }

}

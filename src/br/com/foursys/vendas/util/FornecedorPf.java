/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.FornecedorDAO;
import br.com.foursys.vendas.dao.FornecedorDAO;
import br.com.foursys.vendas.dao.PessoaFisicaDAO;
import br.com.foursys.vendas.model.Fornecedor;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.Fornecedor;
import br.com.foursys.vendas.model.PessoaFisica;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class FornecedorPf {

    public void salvarFornecedor() {

        //FornecedorDAO dao = new FornecedorDAO();
        //Fornecedor fornecedor = new Fornecedor();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = new Fornecedor();
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();
        Endereco endereco = new Endereco();
        Contato contato = new Contato();
        contato.setIdContato(1);
        //PessoaJuridica pessoaJuridica= new PessoaJuridica();

        pessoaFisica.setNome("Leonardo");
        pessoaFisica.setRg("66.666.666-7");
        pessoaFisica.setCpf("666.666.666-7");
        pessoaFisica.setDataNascimento("23/10/2007");
        pessoaFisicaDAO.salvar(pessoaFisica);
        System.out.println("PessoaFisica salvo com sucesso");
        endereco.setIdEndereco(2);

        fornecedor.setContato("569245677");
        fornecedor.setTipoPessoa("f");
        fornecedor.setEnderecoIdEndereco(endereco);
        fornecedor.setContatoIdContato(contato);
        fornecedor.setPessoaFisicaIdPessoaFisica(pessoaFisica);
        fornecedorDAO.salvar(fornecedor);
        System.out.println("Fornecedor salvo com sucesso");
        System.exit(0);
    }

    public void buscarCodigo() throws Exception {
        FornecedorDAO dao = new FornecedorDAO();
        Fornecedor fornecedor = dao.buscarPorCodigo(4);
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

            System.out.println("Nome:" + fornecedor.getPessoaFisicaIdPessoaFisica().getNome());
            System.out.println("Rg:" + fornecedor.getPessoaFisicaIdPessoaFisica().getRg());
            System.out.println("Cpf:" + fornecedor.getPessoaFisicaIdPessoaFisica().getCpf());
            System.out.println("Data de nascimento:" + fornecedor.getPessoaFisicaIdPessoaFisica().getDataNascimento());

        }
        System.exit(0);
    }

    public void buscarTodos() throws Exception {
        FornecedorDAO dao = new FornecedorDAO();
        ArrayList<Fornecedor> lista = dao.buscarTodos();

        for (Fornecedor fornecedor1 : lista) {
            if (fornecedor1.getTipoPessoa().trim().equals("f")) {
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

                System.out.println("Nome:" + fornecedor1.getPessoaFisicaIdPessoaFisica().getNome());
                System.out.println("Rg:" + fornecedor1.getPessoaFisicaIdPessoaFisica().getRg());
                System.out.println("Cpf:" + fornecedor1.getPessoaFisicaIdPessoaFisica().getCpf());
                System.out.println("Data de nascimento:" + fornecedor1.getPessoaFisicaIdPessoaFisica().getDataNascimento());
                System.out.println("");
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new FornecedorPf().buscarTodos();
    }

}

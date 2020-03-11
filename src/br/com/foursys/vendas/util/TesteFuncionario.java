/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.FuncionarioDAO;
import br.com.foursys.vendas.dao.PessoaFisicaDAO;
import br.com.foursys.vendas.model.Funcionario;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.PessoaFisica;
import br.com.foursys.vendas.model.PessoaJuridica;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteFuncionario {

    public void salvarFuncionario() {

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario();
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();
        Endereco endereco = new Endereco();
        Contato contato = new Contato();
        contato.setIdContato(1);
        //PessoaJuridica pessoaJuridica= new PessoaJuridica();

        pessoaFisica.setNome("Matheus Poda");
        pessoaFisica.setRg("66.666.666-6");
        pessoaFisica.setCpf("666.666.666-6");
        pessoaFisica.setDataNascimento("23/10/2000");
        pessoaFisicaDAO.salvar(pessoaFisica);
        System.out.println("PessoaFisica salvo com sucesso");
        endereco.setIdEndereco(2);

        //funcionario.setContato("569245698");
        funcionario.setSenha("1234");
        funcionario.setLogin("asdf");
        funcionario.setEnderecoIdEndereco(endereco);
        funcionario.setContatoIdContato(contato);
        funcionario.setPessoaFisicaIdPessoaFisica(pessoaFisica);

        funcionarioDAO.salvar(funcionario);
        System.out.println("Funcionario salvo com sucesso");

        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = dao.buscarPorCodigo(1);

        System.out.println("Logradouro:" + funcionario.getEnderecoIdEndereco().getLogradouro());

        System.out.println("Numero:" + funcionario.getEnderecoIdEndereco().getNumero());
        System.out.println("Complemento:" + funcionario.getEnderecoIdEndereco().getComplemento());
        System.out.println("Bairro:" + funcionario.getEnderecoIdEndereco().getBairro());
        System.out.println("cep:" + funcionario.getEnderecoIdEndereco().getCep());

        System.out.println("Cidade:" + funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
        System.out.println("Estado:" + funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());

        System.out.println("Telefone:" + funcionario.getContatoIdContato().getTelefone());
        System.out.println("Celular:" + funcionario.getContatoIdContato().getCelular());
        System.out.println("Email:" + funcionario.getContatoIdContato().getEmail());

        System.out.println("Nome:" + funcionario.getPessoaFisicaIdPessoaFisica().getNome());
        System.out.println("Rg:" + funcionario.getPessoaFisicaIdPessoaFisica().getRg());
        System.out.println("Cpf:" + funcionario.getPessoaFisicaIdPessoaFisica().getCpf());
        System.out.println("Data de nascimento:" + funcionario.getPessoaFisicaIdPessoaFisica().getDataNascimento());
        System.exit(0);

    }

    public void buscarTodos() throws Exception {
        FuncionarioDAO dao = new FuncionarioDAO();
        ArrayList<Funcionario> lista = dao.buscarTodos();

        for (Funcionario funcionario1 : lista) {
            

            System.out.println("Logradouro:" + funcionario1.getEnderecoIdEndereco().getLogradouro());

            System.out.println("Numero:" + funcionario1.getEnderecoIdEndereco().getNumero());
            System.out.println("Complemento:" + funcionario1.getEnderecoIdEndereco().getComplemento());
            System.out.println("Bairro:" + funcionario1.getEnderecoIdEndereco().getBairro());
            System.out.println("cep:" + funcionario1.getEnderecoIdEndereco().getCep());

            System.out.println("Cidade:" + funcionario1.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
            System.out.println("Estado:" + funcionario1.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());

            System.out.println("Telefone:" + funcionario1.getContatoIdContato().getTelefone());
            System.out.println("Celular:" + funcionario1.getContatoIdContato().getCelular());
            System.out.println("Email:" + funcionario1.getContatoIdContato().getEmail());
            
                System.out.println("Nome:" + funcionario1.getPessoaFisicaIdPessoaFisica().getNome());
                System.out.println("Rg:" + funcionario1.getPessoaFisicaIdPessoaFisica().getRg());
                System.out.println("Cpf:" + funcionario1.getPessoaFisicaIdPessoaFisica().getCpf());
                System.out.println("Data de nascimento:" + funcionario1.getPessoaFisicaIdPessoaFisica().getDataNascimento());
            
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteFuncionario().buscarTodos();
    }

}

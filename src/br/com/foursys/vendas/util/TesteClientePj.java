/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foursys.vendas.util;

import br.com.foursys.vendas.dao.ClienteDAO;
import br.com.foursys.vendas.dao.PessoaJuridicaDAO;
import br.com.foursys.vendas.model.Cliente;
import br.com.foursys.vendas.model.Contato;
import br.com.foursys.vendas.model.Endereco;
import br.com.foursys.vendas.model.PessoaJuridica;
import br.com.foursys.vendas.model.PessoaJuridica;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author amendes
 */
public class TesteClientePj {

    public void salvarCliente() {

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
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

        cliente.setTipoPessoa("j");
        cliente.setEnderecoIdEndereco(endereco);
        cliente.setContatoIdContato(contato);
        cliente.setPessoaJuridicaIdPessoaJuridica(pessoaJuridica);
        clienteDAO.salvar(cliente);
        System.out.println("Cliente salvo com sucesso");

        System.exit(0);

    }

    public void buscarCodigo() throws Exception {
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = dao.buscarPorCodigo(9);
        if (cliente.getTipoPessoa().trim().equals("j")) {
            System.out.println("Logradouro:" + cliente.getEnderecoIdEndereco().getLogradouro());

            System.out.println("Numero:" + cliente.getEnderecoIdEndereco().getNumero());
            System.out.println("Complemento:" + cliente.getEnderecoIdEndereco().getComplemento());
            System.out.println("Bairro:" + cliente.getEnderecoIdEndereco().getBairro());
            System.out.println("cep:" + cliente.getEnderecoIdEndereco().getCep());

            System.out.println("Cidade:" + cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
            System.out.println("Estado:" + cliente.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());

            System.out.println("Telefone:" + cliente.getContatoIdContato().getTelefone());
            System.out.println("Celular:" + cliente.getContatoIdContato().getCelular());
            System.out.println("Email:" + cliente.getContatoIdContato().getEmail());

            System.out.println("Razão Social:" + cliente.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
            
            System.out.println("Cnpj:" + cliente.getPessoaJuridicaIdPessoaJuridica().getCnpj());
            System.out.println("Inscrição Estadual:" + cliente.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
            System.out.println("Data de Fundação:" + cliente.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());
            System.out.println("");
        }
        System.exit(0);
    }

    public void buscarTodos() throws Exception {
        ClienteDAO dao = new ClienteDAO();
        ArrayList<Cliente> lista = dao.buscarTodos();

        for (Cliente cliente2 : lista) {
            if (cliente2.getTipoPessoa().trim().equals("j")) {
                System.out.println("Logradouro:" + cliente2.getEnderecoIdEndereco().getLogradouro());

                System.out.println("Numero:" + cliente2.getEnderecoIdEndereco().getNumero());
                System.out.println("Complemento:" + cliente2.getEnderecoIdEndereco().getComplemento());
                System.out.println("Bairro:" + cliente2.getEnderecoIdEndereco().getBairro());
                System.out.println("cep:" + cliente2.getEnderecoIdEndereco().getCep());

                System.out.println("Cidade:" + cliente2.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
                System.out.println("Estado:" + cliente2.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());

                System.out.println("Telefone:" + cliente2.getContatoIdContato().getTelefone());
                System.out.println("Celular:" + cliente2.getContatoIdContato().getCelular());
                System.out.println("Email:" + cliente2.getContatoIdContato().getEmail());

                System.out.println("Razão Social:" + cliente2.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
                
                System.out.println("Cnpj:" + cliente2.getPessoaJuridicaIdPessoaJuridica().getCnpj());
                System.out.println("Inscrição Estadual:" + cliente2.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
                System.out.println("Data de Fundação:" + cliente2.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());
                System.out.println("");
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new TesteClientePj().buscarTodos();
    }

}
